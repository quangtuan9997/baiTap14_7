package baitap14_07.service.impl;

import baitap14_07.domain.Role;
import baitap14_07.repository.RoleRepository;
import baitap14_07.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RoleServiceIMPL implements RoleService {
    private final RoleRepository roleRepository;

    public RoleServiceIMPL(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }
}
