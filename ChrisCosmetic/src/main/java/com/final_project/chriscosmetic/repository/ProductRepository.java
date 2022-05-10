package com.final_project.chriscosmetic.repository;

import com.final_project.chriscosmetic.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findBySubCategoryId(Long id);

    List<Product> findBySubCategoryCategoryId(Long id);
}
