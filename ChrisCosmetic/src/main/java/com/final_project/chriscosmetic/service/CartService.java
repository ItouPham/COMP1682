package com.final_project.chriscosmetic.service;

import com.final_project.chriscosmetic.dto.req.AddProductToCartReqDTO;
import com.final_project.chriscosmetic.dto.req.UpdateCartReqDTO;
import com.final_project.chriscosmetic.entity.CartItem;

import java.util.List;

public interface CartService {

    List<CartItem> listCartItem(Long cartId);

    void addProductToCart(Long cartId, AddProductToCartReqDTO reqDTO);

    void updateCart(Long cartId, UpdateCartReqDTO reqDTO);

    void clearShoppingCart(Long cartId);

    void deleteCartItem(Long cartId, Long cartItemId);
}
