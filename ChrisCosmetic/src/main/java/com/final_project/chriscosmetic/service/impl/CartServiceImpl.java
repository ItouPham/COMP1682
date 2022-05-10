package com.final_project.chriscosmetic.service.impl;

import com.final_project.chriscosmetic.dto.req.AddProductToCartReqDTO;
import com.final_project.chriscosmetic.dto.req.UpdateCartReqDTO;
import com.final_project.chriscosmetic.entity.Cart;
import com.final_project.chriscosmetic.entity.CartItem;
import com.final_project.chriscosmetic.entity.Product;
import com.final_project.chriscosmetic.exception.CartNotFoundException;
import com.final_project.chriscosmetic.exception.UnauthorizedException;
import com.final_project.chriscosmetic.repository.CartItemRepository;
import com.final_project.chriscosmetic.repository.CartRepository;
import com.final_project.chriscosmetic.service.CartService;
import com.final_project.chriscosmetic.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductService productService;

    public CartServiceImpl(CartRepository cartRepository, CartItemRepository cartItemRepository, ProductService productService) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.productService = productService;
    }

    @Override
    public List<CartItem> listCartItem(Long cartId){
        return cartItemRepository.findByCartId(cartId);
    }

    @Override
    public void addProductToCart(Long cartId, AddProductToCartReqDTO reqDTO) {
        Cart cart = findCartById(cartId);
        Product product = productService.findById(reqDTO.getProductId());
        CartItem cartItem = cartItemRepository.
                findByCartIdAndProductId(cartId, reqDTO.getProductId()).
                orElseGet(() -> new CartItem(product, cart));

        cartItem.setQuantity(cartItem.getQuantity() + reqDTO.getQuantity());

        cartItemRepository.save(cartItem);
    }

    @Override
    public void updateCart(Long cartId, UpdateCartReqDTO reqDTO) {
        List<CartItem> cartItems = cartItemRepository.findByCartId(cartId);
        if (cartItems.size() != reqDTO.getQuantity().size()) {
            throw new RuntimeException("Invalid request");
        }
        List<Integer> quantity = reqDTO.getQuantity();

        for (int i = 0; i < cartItems.size(); i++) {
            cartItems.get(i).setQuantity(quantity.get(i));
        }

        cartItemRepository.saveAll(cartItems);
    }

    @Override
    public void clearShoppingCart(Long cartId) {
        cartItemRepository.deleteByCartId(cartId);
    }

    @Override
    public void deleteCartItem(Long cartId, Long cartItemId) {
        CartItem cartItem = cartItemRepository.findById(cartItemId).orElseThrow(CartNotFoundException::new);
        if (cartItem.getCart().getId() != cartId) {
            throw new UnauthorizedException();
        }

        cartItemRepository.delete(cartItem);
    }

    private Cart findCartById(Long id) {
     return cartRepository.findById(id).orElseThrow(CartNotFoundException::new);
    }
}
