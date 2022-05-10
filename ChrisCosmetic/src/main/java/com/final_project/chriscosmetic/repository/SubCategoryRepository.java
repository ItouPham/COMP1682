package com.final_project.chriscosmetic.repository;

import com.final_project.chriscosmetic.entity.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategory, Long>{
	Optional<SubCategory> findBySubCategoryName(String subCategoryName);
}