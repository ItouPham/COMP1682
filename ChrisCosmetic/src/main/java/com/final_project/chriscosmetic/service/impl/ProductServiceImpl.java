package com.final_project.chriscosmetic.service.impl;

import com.final_project.chriscosmetic.dto.req.AddProductReqDTO;
import com.final_project.chriscosmetic.dto.req.EditProductReqDTO;
import com.final_project.chriscosmetic.entity.Product;
import com.final_project.chriscosmetic.entity.SubCategory;
import com.final_project.chriscosmetic.exception.ProductNotFoundException;
import com.final_project.chriscosmetic.repository.ProductRepository;
import com.final_project.chriscosmetic.service.ProductService;
import com.final_project.chriscosmetic.service.StorageService;
import com.final_project.chriscosmetic.service.SubCategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;
    private StorageService storageService;
    private SubCategoryService subCategoryService;

    public ProductServiceImpl(ProductRepository productRepository, StorageService storageService, SubCategoryService subCategoryService) {
        this.productRepository = productRepository;
        this.storageService = storageService;
        this.subCategoryService = subCategoryService;
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
    }

    @Override
    public Page<Product> findAll(int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber - 1,10);
        return productRepository.findAll(pageable);
    }

    @Override
    public List<Product> findBySubCategoryID(Long id) {
        return productRepository.findBySubCategoryId(id);
    }

    @Override
    public List<Product> findByCategoryID(Long id) {
        return productRepository.findBySubCategoryCategoryId(id);
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public void deleteById(Long id) {
        Product entity = findById(id);
        storageService.deleteProductImage(entity.getProductImage());
        productRepository.delete(entity);
    }

    @Override
    public void addNewProduct(AddProductReqDTO reqDTO) {
        Product entity = new Product();
        entity.setSubCategory(subCategoryService.findById(reqDTO.getSubCategoryID()));
        MultipartFile fileImage = reqDTO.getFileImage();
        String productImage = storageService.saveProductImage(fileImage);
        entity.setProductImage(productImage);

        entity.setProductName(reqDTO.getProductName());
        entity.setPrice(reqDTO.getPrice());
        entity.setProductDetailDesc(reqDTO.getProductDetailDesc());
        entity.setProductShortDesc(reqDTO.getProductShortDesc());
        entity.setQuantity(reqDTO.getQuantity());

        productRepository.save(entity);
    }

    @Override
    public void editProduct(EditProductReqDTO reqDTO) {
        Product entity = findById(reqDTO.getId());
        SubCategory subCategory = subCategoryService.findById(reqDTO.getSubCategoryID());
        if (subCategory.getId() != entity.getSubCategory().getId()) {
            entity.setSubCategory(subCategory);
        }

        if (!reqDTO.getFileImage().isEmpty()) {
            String productImage = storageService.saveProductImage(reqDTO.getFileImage());
            storageService.deleteProductImage(entity.getProductImage());

            entity.setProductImage(productImage);
        }

        entity.setProductName(reqDTO.getProductName());
        entity.setPrice(reqDTO.getPrice());
        entity.setProductDetailDesc(reqDTO.getProductDetailDesc());
        entity.setProductShortDesc(reqDTO.getProductShortDesc());
        entity.setQuantity(reqDTO.getQuantity());

        productRepository.save(entity);
    }
}
