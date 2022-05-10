package com.final_project.chriscosmetic.exception;

public class EmailAlreadyExistException extends RuntimeException {

    public EmailAlreadyExistException() {
        super("Email already exist");
    }
}
