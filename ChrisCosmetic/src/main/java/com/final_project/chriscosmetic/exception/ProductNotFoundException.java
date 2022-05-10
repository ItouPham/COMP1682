package com.final_project.chriscosmetic.exception;

public class ProductNotFoundException extends ResourceNotFoundException {

    public ProductNotFoundException() {
        super("Product not found");
    }
}
