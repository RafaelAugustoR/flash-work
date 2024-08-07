package com.rafaelaugustor.flashwork.rest.handlers;

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
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class OAuthAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final UserRepository userRepository;

    private final DefaultRedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    private final TokenService tokenService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        log.info("OAuthAuthenticationSuccessHandler triggered");

        OAuth2AuthenticationToken oauthToken = (OAuth2AuthenticationToken) authentication;
        String registrationId = oauthToken.getAuthorizedClientRegistrationId();
        log.info("Authorized client registration ID: {}", registrationId);

        DefaultOAuth2User oauthUser = (DefaultOAuth2User) authentication.getPrincipal();

        oauthUser.getAttributes().forEach((key, value) -> {
            log.info("{}: {}", key, value);
        });

        User user = new User();
        user.setRole(UserRole.CUSTOMER);
        user.setPassword("dummy");

        if (registrationId.equalsIgnoreCase("google")) {
            user.setEmail(oauthUser.getAttribute("email").toString());
            user.setProfilePicture(oauthUser.getAttribute("picture").toString());
            user.setName(oauthUser.getAttribute("name").toString());
        } else {
            log.error("Unknown registration ID: {}", registrationId);
            response.sendRedirect("/login?error");
            return;
        }

        Optional<User> existingUser = Optional.ofNullable(userRepository.findByEmail(user.getEmail()));
        if (existingUser.isPresent()) {
            log.info("User already exists: {}", existingUser.get().getEmail());
        } else {
            userRepository.save(user);
            log.info("User saved: {}", user.getEmail());
        }

        String token = tokenService.generateToken(user);
        response.setContentType("application/json");
        response.getWriter().write("{\"token\": \"" + token + "\"}");
    }
}
