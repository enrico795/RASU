package it.comune.verona.rasu.service.impl;

import it.comune.verona.rasu.domain.User;
import it.comune.verona.rasu.repository.UserRepository;
import it.comune.verona.rasu.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Override
    public User getByUsername(String username) {
        if (!StringUtils.hasText(username)) {
            return null;
        }

        return repository.findByUsername(username).orElse(null);
    }

    @Override
    public void save(User entity) {
        repository.save(entity);
    }

    @Override
    public List<User> getAllUsers() {
        return (List<User>) repository.findAll();
    }

}
