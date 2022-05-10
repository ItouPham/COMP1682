package com.final_project.chriscosmetic.service;

import com.final_project.chriscosmetic.dto.req.AddProductReqDTO;
import com.final_project.chriscosmetic.dto.req.EditProductReqDTO;
import com.final_project.chriscosmetic.entity.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    Product findById(Long id);

    Page<Product> findAll(int pageNumber);

    List<Product> findBySubCategoryID(Long id);

    List<Product> findByCategoryID(Long id);

    void save(Product product);

    void deleteById(Long id);

    void addNewProduct(AddProductReqDTO reqDTO);

    void editProduct(EditProductReqDTO reqDTO);
}
