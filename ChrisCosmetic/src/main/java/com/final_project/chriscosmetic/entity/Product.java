package com.final_project.chriscosmetic.entity;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.math.BigDecimal;

@DynamicUpdate
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;
    @Column
    private String productName;
    @Column
    private String productImage;
    @Column
    private String productShortDesc;
    @Column
    private String productDetailDesc;
    @ManyToOne
    @JoinColumn(name = "sub_category_id")
    private SubCategory subCategory;
    @Column
    private BigDecimal price;
    @Column
    private Integer quantity;

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

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
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

    public SubCategory getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(SubCategory subCategory) {
        this.subCategory = subCategory;
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

    public Product() {
        super();
    }

    public Product(Long id,
                   String productName,
                   String productImage,
                   String productShortDesc,
                   String productDetailDesc,
                   SubCategory subCategory,
                   BigDecimal price,
                   Integer quantity) {
        this.id = id;
        this.productName = productName;
        this.productImage = productImage;
        this.productShortDesc = productShortDesc;
        this.productDetailDesc = productDetailDesc;
        this.subCategory = subCategory;
        this.price = price;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", productImage='" + productImage + '\'' +
                ", productShortDesc='" + productShortDesc + '\'' +
                ", productDetailDesc='" + productDetailDesc + '\'' +
                ", subCategory=" + subCategory +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }

    @Transient
    public String getProductImagePath() {
        if (productImage == null) {
            return null;
        }
        return "/product-images/" + productImage;
    }
}
