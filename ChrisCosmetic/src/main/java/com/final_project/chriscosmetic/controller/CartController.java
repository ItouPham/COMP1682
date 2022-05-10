package com.final_project.chriscosmetic.controller;

import com.final_project.chriscosmetic.dto.req.UpdateCartReqDTO;
import com.final_project.chriscosmetic.entity.CartItem;
import com.final_project.chriscosmetic.service.CartService;
import com.final_project.chriscosmetic.util.SecurityUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping
    public String showShoppingCart(Model model){
        List<CartItem> cartItems = cartService.listCartItem(SecurityUtil.getCurrentUser().getCart().getId());
        BigDecimal itemCost  = BigDecimal.ZERO;
        BigDecimal totalCost = BigDecimal.ZERO;
        for (CartItem item : cartItems){
            itemCost = item.getProduct().getPrice().multiply(BigDecimal.valueOf(item.getQuantity()));
            totalCost = totalCost.add(itemCost);
        }
        model.addAttribute("total", totalCost);
        model.addAttribute("cartItems", cartItems);
        return "shopping-cart";
    }

    @PostMapping("/update")
    public String updateShoppingCart(@Valid @ModelAttribute UpdateCartReqDTO reqDTO,
                                     BindingResult result,
                                     RedirectAttributes redirect) {
        if (result.hasErrors()) {
            redirect.addFlashAttribute("errorMessage", "Update cart failed");
            return "redirect:/cart";
        }

        cartService.updateCart(SecurityUtil.getCurrentUser().getCart().getId(), reqDTO);
        redirect.addFlashAttribute("successMessage", "Update cart successfully");
        return "redirect:/cart";
    }

    @GetMapping("/clear")
    public String clearShoppingCart() {
        cartService.clearShoppingCart(SecurityUtil.getCurrentUser().getCart().getId());

        return "redirect:/cart";
    }

    @GetMapping("/delete/{id}")
    public String deleteCartItem(@PathVariable Long id) {
        cartService.deleteCartItem(SecurityUtil.getCurrentUser().getCart().getId(), id);

        return "redirect:/cart";
    }
}
