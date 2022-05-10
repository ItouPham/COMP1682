package com.final_project.chriscosmetic.service.impl;

import com.final_project.chriscosmetic.entity.SubCategory;
import com.final_project.chriscosmetic.exception.SubCategoryNotFoundException;
import com.final_project.chriscosmetic.repository.SubCategoryRepository;
import com.final_project.chriscosmetic.service.SubCategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubCategoryServiceImpl implements SubCategoryService {

	private SubCategoryRepository subCategoryRepository;

	public SubCategoryServiceImpl(SubCategoryRepository subCategoryRepository) {
		this.subCategoryRepository = subCategoryRepository;
	}

	@Override
	public List<SubCategory> findAll() {
		return subCategoryRepository.findAll();
	}

	@Override
	public SubCategory findById(Long id) {
		return subCategoryRepository.findById(id).orElseThrow(SubCategoryNotFoundException::new);
	}
}
