package it.comune.verona.rasu.service;

import it.comune.verona.rasu.domain.User;

import java.util.List;

public interface UserService {
    User getByUsername(String username);

    void save(User user);

    List<User> getAllUsers();

}
