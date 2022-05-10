package com.final_project.chriscosmetic.exception;

public class SubCategoryNotFoundException extends ResourceNotFoundException {

    public SubCategoryNotFoundException() {
        super("SubCategory not found");
    }
}
