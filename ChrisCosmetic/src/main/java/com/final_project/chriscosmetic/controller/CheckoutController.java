package com.final_project.chriscosmetic.controller;

import com.final_project.chriscosmetic.entity.CartItem;
import com.final_project.chriscosmetic.service.CartService;
import com.final_project.chriscosmetic.util.SecurityUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/checkout")
public class CheckoutController {
    private final CartService cartService;

    public CheckoutController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public String checkout(Model model){
        List<CartItem> cartItems = cartService.listCartItem(SecurityUtil.getCurrentUser().getCart().getId());
        BigDecimal itemCost  = BigDecimal.ZERO;
        BigDecimal totalCost = BigDecimal.ZERO;
        for (CartItem item : cartItems){
            itemCost = item.getProduct().getPrice().multiply(BigDecimal.valueOf(item.getQuantity()));
            totalCost = totalCost.add(itemCost);
        }
        model.addAttribute("total", totalCost);
        model.addAttribute("cartItems", cartItems);
        return "checkout";
    }
}
