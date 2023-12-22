package it.comune.verona.rasu.service.impl;

import it.comune.verona.rasu.domain.User;
import it.comune.verona.rasu.security.CustomUserDetails;
import it.comune.verona.rasu.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("Unable to found user: " + username);
        }

        if(!user.isActive())
            throw new UsernameNotFoundException("Unable to found active user with username: " + username);

        return new CustomUserDetails(user);
    }

}
