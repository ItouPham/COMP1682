package com.final_project.chriscosmetic.dto.req;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class AddProductToCartReqDTO {
    @NotNull(message = "ProductId can not empty")
    private Long productId;
    @NotNull(message = "Quantity can not empty")
    @Min(value = 0)
    private Integer quantity;

    public AddProductToCartReqDTO(Long productId, Integer quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public AddProductToCartReqDTO() {
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
