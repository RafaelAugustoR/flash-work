package com.rafaelaugustor.flashwork.rest.handlers.auth;

import com.rafaelaugustor.flashwork.domain.entities.User;
import com.rafaelaugustor.flashwork.domain.enums.UserRole;
import com.rafaelaugustor.flashwork.repositories.UserRepository;
import com.rafaelaugustor.flashwork.services.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class OAuthAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final UserRepository userRepository;
    private final TokenService tokenService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        log.info("OAuthAuthenticationSuccessHandler triggered");

        OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
        String registrationId = oauthToken.getAuthorizedClientRegistrationId();
        DefaultOAuth2User oauthUser = (DefaultOAuth2User) authentication.getPrincipal();

        if (!registrationId.equalsIgnoreCase("google")) {
            response.sendRedirect("/login");
            throw new RuntimeException("Invalid registration id");
        }

        String email = oauthUser.getAttribute("email").toString();
        Optional<User> optionalUser = Optional.ofNullable(userRepository.findByEmail(email));

        User user = optionalUser.orElseGet(() -> {
            User newUser = User.builder()
                    .name(oauthUser.getAttribute("name").toString())
                    .email(email)
                    .password("password")
                    .profilePicture(oauthUser.getAttribute("picture").toString())
                    .role(UserRole.CUSTOMER)
                    .build();
            return userRepository.save(newUser);
        });

        String token = tokenService.generateToken(user);

        String redirectUrl = "http://localhost:5173/?token=" + token;
        response.sendRedirect(redirectUrl);

        log.info("User {} successfully authenticated w/ token {}", user.getEmail(), token);
    }
}
