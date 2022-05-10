package com.final_project.chriscosmetic.dto.req;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class EditProductReqDTO {
    @NotNull
    private Long id;
    @NotBlank(message = "Product name can not empty")
    private String productName;
    private String productShortDesc;
    private String productDetailDesc;
    private String productImage;
    @NotNull
    private Long subCategoryID;
    @NotNull(message = "Price can not empty")
    @Min(value = 0)
    private BigDecimal price;
    @NotNull(message = "Quantity can not empty")
    @Min(value = 0)
    private Integer quantity;
    private MultipartFile fileImage;

    public EditProductReqDTO() {
    }

    public EditProductReqDTO(Long id, String productName, String productShortDesc, String productDetailDesc, String productImage, Long subCategoryID, BigDecimal price, Integer quantity, MultipartFile fileImage) {
        this.id = id;
        this.productName = productName;
        this.productShortDesc = productShortDesc;
        this.productDetailDesc = productDetailDesc;
        this.productImage = productImage;
        this.subCategoryID = subCategoryID;
        this.price = price;
        this.quantity = quantity;
        this.fileImage = fileImage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductShortDesc() {
        return productShortDesc;
    }

    public void setProductShortDesc(String productShortDesc) {
        this.productShortDesc = productShortDesc;
    }

    public String getProductDetailDesc() {
        return productDetailDesc;
    }

    public void setProductDetailDesc(String productDetailDesc) {
        this.productDetailDesc = productDetailDesc;
    }

    public Long getSubCategoryID() {
        return subCategoryID;
    }

    public void setSubCategoryID(Long subCategoryID) {
        this.subCategoryID = subCategoryID;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public MultipartFile getFileImage() {
        return fileImage;
    }

    public void setFileImage(MultipartFile fileImage) {
        this.fileImage = fileImage;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    @Transient
    public String getProductImagePath() {
        if (productImage == null) {
            return null;
        }
        return "/product-images/" + productImage;
    }
}
