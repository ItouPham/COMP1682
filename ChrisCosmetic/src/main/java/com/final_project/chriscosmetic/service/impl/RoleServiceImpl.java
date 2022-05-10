package com.final_project.chriscosmetic.service.impl;

import com.final_project.chriscosmetic.entity.Role;
import com.final_project.chriscosmetic.exception.RoleNotFoundException;
import com.final_project.chriscosmetic.repository.RoleRepository;
import com.final_project.chriscosmetic.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> findAllRole() {
        return roleRepository.findAll();
    }

    @Override
    public Role findById(String id) {
        return roleRepository.findById(id).orElseThrow(RoleNotFoundException::new);
    }

    @Override
    public Role findByRoleName(String roleName) {
        return roleRepository.findByRoleName(roleName).orElseThrow(RoleNotFoundException::new);
    }
}
