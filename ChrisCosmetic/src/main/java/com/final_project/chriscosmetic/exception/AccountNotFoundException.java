package com.final_project.chriscosmetic.exception;

public class AccountNotFoundException extends ResourceNotFoundException {

    public AccountNotFoundException() {
        super("Account not found");
    }
}
