package it.comune.verona.rasu.service.impl;

import it.comune.verona.rasu.domain.User;
import it.comune.verona.rasu.security.socialLogin.mapper.OidcUserMapper;
import it.comune.verona.rasu.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class CustomOidcUserService extends OidcUserService {

    private final UserService userService;
    private final Map<String, OidcUserMapper> mappers;

    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
        OidcUser oidcUser = super.loadUser(userRequest);
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        Assert.isTrue(mappers.containsKey(registrationId), "No mapper defined for such registrationId");
        OidcUserMapper mapper = mappers.get(userRequest.getClientRegistration().getRegistrationId());
        String email = userRequest.getIdToken().getEmail();

        if(registrationId.equals("netiq")) {
            email = oidcUser.getUserInfo().getEmail();
        }

        User localUser = userService.getByUsername(email);

        if (localUser != null) {
            if(!localUser.isActive())
                throw new UsernameNotFoundException("Unable to found active user with username: " + oidcUser.getPreferredUsername());

            return mapper.map(oidcUser.getIdToken(), oidcUser.getUserInfo(), localUser);
        }

        // Map unregistered user
        // return mapper.map(oidcUser);
        throw new UsernameNotFoundException("Unable to found user: " + oidcUser.getPreferredUsername());
    }

}
