package com.final_project.chriscosmetic.service;

import com.final_project.chriscosmetic.entity.SubCategory;

import java.util.List;

public interface SubCategoryService {

    List<SubCategory> findAll();
    SubCategory findById(Long id);
}
