package com.final_project.chriscosmetic.exception;

public class RoleNotFoundException extends ResourceNotFoundException {

    public RoleNotFoundException() {
        super("Role not found");
    }
}
