package it.comune.verona.rasu.service;

import it.comune.verona.rasu.domain.Role;

import java.util.List;

public interface RoleService {
    Role getByName(String name);
    Role getDefaultRole();
    List<Role> getAllRoles();
}
