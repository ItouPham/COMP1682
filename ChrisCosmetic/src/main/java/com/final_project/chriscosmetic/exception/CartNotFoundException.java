package com.final_project.chriscosmetic.exception;

public class CartNotFoundException extends ResourceNotFoundException {

    public CartNotFoundException() {
        super("Cart not found");
    }
}
