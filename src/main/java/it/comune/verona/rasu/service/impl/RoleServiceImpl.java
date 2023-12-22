package it.comune.verona.rasu.service.impl;

import it.comune.verona.rasu.domain.Role;
import it.comune.verona.rasu.repository.RoleRepository;
import it.comune.verona.rasu.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    public static final String DEFAULT_ROLE = "USER";

    private final RoleRepository repository;

    @Override
    public Role getByName(String name) {
        if (!StringUtils.hasText(name)) {
            return null;
        }

        return repository.findByName(name).orElse(null);
    }

    @Override
    public Role getDefaultRole() {
        return getByName(DEFAULT_ROLE);
    }

    @Override
    public List<Role> getAllRoles() {
        return (List<Role>) repository.findAll();
    }
}
