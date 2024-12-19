package com.taskmates.authorization_server.service;

import com.taskmates.authorization_server.model.Authority;
import com.taskmates.authorization_server.model.ProviderEntity;
import com.taskmates.authorization_server.model.UserEntity;
import com.taskmates.authorization_server.repository.AuthorityRepository;
import com.taskmates.authorization_server.repository.ProviderRepository;
import com.taskmates.authorization_server.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.function.Consumer;


@Component
public class OAuth2UserHandler implements Consumer<OAuth2User> {

    private static final Logger log = LoggerFactory.getLogger(OAuth2UserHandler.class);
    private final UserRepository userRepository;
    private final ProviderRepository providerRepository;
    private final AuthorityRepository authorityRepository;

    public OAuth2UserHandler(UserRepository userRepository, ProviderRepository providerRepository, AuthorityRepository authorityRepository) {
        this.userRepository = userRepository;
        this.providerRepository = providerRepository;
        this.authorityRepository = authorityRepository;
    }

    @Override
    public void accept(OAuth2User oAuth2User) {
        String email = extractEmail(oAuth2User);

        if (userRepository.findByEmail(email).isPresent()) {
            log.info("User already exists: {}", email);
            return;
        }


        UserEntity userEntity = new UserEntity(
                email,
                Set.of(
                        authorityRepository.findByName(Authority.ROLE_USER).orElseThrow(
                                () -> new RuntimeException("Authority not found")
                        )
                ),
                Set.of(getProvider())
        );

        userRepository.save(userEntity);

        log.info("New user registered: {}", email);

    }

    private String extractEmail(OAuth2User oAuth2User) {
        if (oAuth2User instanceof OidcUser oidcUser) {
            return oidcUser.getEmail();
        }
        return oAuth2User.getAttribute("email");
    }

    private ProviderEntity getProvider() {
        OAuth2AuthenticationToken authentication = (OAuth2AuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        return providerRepository.findByName(authentication.getAuthorizedClientRegistrationId()).orElseThrow(
                () -> new RuntimeException("Provider not found")
        );
    }
}
