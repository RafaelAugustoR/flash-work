package com.rafaelaugustor.flashwork.config;

import com.rafaelaugustor.flashwork.domain.enums.UserRole;
import com.rafaelaugustor.flashwork.rest.handlers.OAuthAuthenticationSuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final SecurityFilter securityFilter;
    private final OAuthAuthenticationSuccessHandler oAuthAuthenticationSuccessHandler;

    @Bean
    @Profile("test")
    public SecurityFilterChain h2SecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.securityMatcher(PathRequest.toH2Console()).csrf(csrf -> csrf.disable())
                .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable()));
        return httpSecurity.build();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**", "/oauth2/**", "/api/forgot-password/**").permitAll()
                        .requestMatchers("/api/user/all").hasRole(UserRole.ADMIN.getRole())
                        .requestMatchers("/api/user/**").authenticated()
                        .requestMatchers(HttpMethod.POST, "/api/categories").hasRole(UserRole.ADMIN.getRole())
                        .requestMatchers(HttpMethod.PUT, "/api/categories/{id}").hasRole(UserRole.ADMIN.getRole())
                        .requestMatchers(HttpMethod.DELETE, "/api/categories/{id}").hasRole(UserRole.ADMIN.getRole())
                        .requestMatchers(HttpMethod.GET, "api/categories").permitAll()
                        .requestMatchers(HttpMethod.GET, "api/categories/{id}").permitAll()
                        .requestMatchers(HttpMethod.POST, "api/services").authenticated()
                        .requestMatchers(HttpMethod.PUT, "api/services/{id}").authenticated()
                        .requestMatchers(HttpMethod.DELETE, "api/services/{id}").authenticated()
                        .requestMatchers(HttpMethod.GET, "api/services/all").permitAll()
                        .requestMatchers(HttpMethod.GET, "api/services/{id}").permitAll()
                        .requestMatchers("api/proposal/**").authenticated()
                        .anyRequest().authenticated()
                )
                .oauth2Login(oauth2 -> oauth2
                        .successHandler(oAuthAuthenticationSuccessHandler)
                )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
