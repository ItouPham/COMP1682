package com.final_project.chriscosmetic.service;

import com.final_project.chriscosmetic.entity.Role;

import java.util.List;

public interface RoleService {

    List<Role> findAllRole();
    Role findById(String id);
    Role findByRoleName(String roleName);
}
