package com.final_project.chriscosmetic.controller;

import com.final_project.chriscosmetic.dto.req.AddProductToCartReqDTO;
import com.final_project.chriscosmetic.service.CartService;
import com.final_project.chriscosmetic.service.ProductService;
import com.final_project.chriscosmetic.util.SecurityUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/product")
public class ProductController {

    private ProductService productService;
    private CartService cartService;

    public ProductController(ProductService productService, CartService cartService) {
        this.productService = productService;
        this.cartService = cartService;
    }

    @GetMapping("/{id}")
    public String viewProductPage(@PathVariable("id") Long id, Model model){
        model.addAttribute("products", productService.findBySubCategoryID(id));
        return "product";
    }

    @GetMapping("/category/{id}")
    public String viewProductCategoryID(@PathVariable("id") Long id, Model model){
        model.addAttribute("products", productService.findByCategoryID(id));
        return "product";
    }

    @GetMapping("/product-detail/{id}")
    public String ViewProductDetail(@PathVariable("id") Long id, Model model){
        model.addAttribute("product", productService.findById(id));
        model.addAttribute("req", new AddProductToCartReqDTO(id, 1));
        return "product-detail";
    }
    
    @GetMapping("/product-detail/addToCart")
    public String addProductToCart(@Valid @ModelAttribute("req") AddProductToCartReqDTO reqDTO,
                                   BindingResult result,
                                   Model model){
        model.addAttribute("product", productService.findById(reqDTO.getProductId()));

        if (result.hasErrors()) {
            return "product-detail";
        }

        cartService.addProductToCart(SecurityUtil.getCurrentUser().getCart().getId(), reqDTO);
        model.addAttribute("successMessage", "Add product to cart successfully");
        return "product-detail";
    }

}
