package com.final_project.chriscosmetic.controller;

import com.final_project.chriscosmetic.dto.req.AddNewAccountReqDTO;
import com.final_project.chriscosmetic.dto.req.AddProductReqDTO;
import com.final_project.chriscosmetic.dto.req.EditAccountReqDTO;
import com.final_project.chriscosmetic.dto.req.EditProductReqDTO;
import com.final_project.chriscosmetic.entity.Account;
import com.final_project.chriscosmetic.entity.Product;
import com.final_project.chriscosmetic.entity.Role;
import com.final_project.chriscosmetic.exception.RoleNotFoundException;
import com.final_project.chriscosmetic.service.AccountService;
import com.final_project.chriscosmetic.service.ProductService;
import com.final_project.chriscosmetic.service.RoleService;
import com.final_project.chriscosmetic.service.SubCategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Objects;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private AccountService accountService;
    private RoleService roleService;
    private SubCategoryService subCategoryService;
    private ProductService productService;

    public AdminController(AccountService accountService,
                           RoleService roleService,
                           SubCategoryService subCategoryService,
                           ProductService productService) {
        this.accountService = accountService;
        this.roleService = roleService;
        this.subCategoryService = subCategoryService;
        this.productService = productService;
    }

    @GetMapping
    public String admin() {
        return "home-admin";
    }

    @GetMapping("/account")
    public String userList(Model model) {
        return listAccountByPage(model, 1);
    }

    @GetMapping("/account/page/{pageNumber}")
    public String listAccountByPage(Model model, @PathVariable("pageNumber") int currentPage) {
        Page<Account> page = accountService.findAll(currentPage);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("accounts", page.getContent());

        return "user-management";
    }

    @GetMapping("/account/add")
    public String add(Model model) {
        model.addAttribute("account", new AddNewAccountReqDTO());
        model.addAttribute("roles", roleService.findAllRole());
        return "add-new-user";
    }

    @PostMapping("/account/add")
    public String save(@ModelAttribute("account") @Valid AddNewAccountReqDTO accountDTO,
                       BindingResult result, RedirectAttributes redirect, Model model) {
        model.addAttribute("roles", roleService.findAllRole());
        // check if the account existed
        if (accountService.isExistsByEmail(accountDTO.getEmail())) {
            result.addError(new FieldError("account", "email", "Email address already in use"));
        }

        if (result.hasErrors()) {
            return "/add-new-user";
        }

        accountService.addNewAccount(accountDTO);

        redirect.addFlashAttribute("successMessage", "Save account successfully");
        return "redirect:/admin/account";
    }

    @GetMapping("/account/edit/{id}")
    public String update(@PathVariable("id") String id, Model model) {
        Account acc = accountService.findById(id);
        EditAccountReqDTO reqDTO = new EditAccountReqDTO();
        reqDTO.setId(acc.getId());
        reqDTO.setLastName(acc.getLastName());
        reqDTO.setFirstName(acc.getFirstName());
        reqDTO.setEmail(acc.getEmail());
        reqDTO.setPassword(acc.getPassword());
        reqDTO.setRoleID(acc.getRoles().stream().map(Role::getId).findFirst().orElseThrow(RoleNotFoundException::new));
        reqDTO.setAddress(acc.getAddress());
        reqDTO.setTelephone(acc.getTelephone());

        model.addAttribute("account", reqDTO);
        model.addAttribute("roles", roleService.findAllRole());

        return "update-user";
    }

    @PostMapping("/account/edit")
    public String updateUser(@ModelAttribute("account") @Valid EditAccountReqDTO accountDTO,
                             BindingResult result, RedirectAttributes redirect, Model model) {
        model.addAttribute("roles", roleService.findAllRole());
        if (!Objects.equals(accountService.findByEmail(accountDTO.getEmail()).getId(), accountDTO.getId())) {
            result.addError(new FieldError("account", "email", "Email address already in use"));
        }

        if (!accountDTO.getPassword().isEmpty()) {
            if (accountDTO.getPassword().length() < 6) {
                result.addError(new FieldError("account", "password", "Password must be at least 6 characters"));
            }
        }

        if (result.hasErrors()) {
            return "/update-user";
        }

        accountService.editAccount(accountDTO);
        redirect.addFlashAttribute("successMessage", "Save account successfully");
        return "redirect:/admin/account";
    }

    @GetMapping("/account/delete/{id}")
    public String delete(@PathVariable String id, RedirectAttributes redirect) {
        accountService.deleteAccount(id);
        redirect.addFlashAttribute("successMessage", "Delete account successfully");
        return "redirect:/admin/account";
    }

    @RequestMapping("/product")
    public String productList(Model model) {
        return listProductByPage(model, 1);
    }

    @GetMapping("/product/page/{pageNumber}")
    public String listProductByPage(Model model, @PathVariable("pageNumber") int currentPage) {
        Page<Product> page = productService.findAll(currentPage);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("products", page.getContent());

        return "product-management";
    }

    @GetMapping("/product/add")
    public String viewAddProductPage(Model model) {
        model.addAttribute("product", new AddProductReqDTO());
        model.addAttribute("categories", subCategoryService.findAll());
        return "add-new-product";
    }

    @PostMapping("/product/add")
    public String processAddNewProduct(@Valid @ModelAttribute("product") AddProductReqDTO addProductReqDTO,
                                       BindingResult result,
                                       RedirectAttributes redirect,
                                       Model model) {
        if (result.hasErrors()) {
            model.addAttribute("categories", subCategoryService.findAll());

            return "/add-new-product";
        }

        productService.addNewProduct(addProductReqDTO);
        redirect.addFlashAttribute("successMessage", "Add product successfully");
        return "redirect:/admin/product";
    }

    @GetMapping("/product/edit/{id}")
    public String updateProduct(@PathVariable("id") Long id, Model model) {
        Product opt = productService.findById(id);
        EditProductReqDTO reqDTO = new EditProductReqDTO();
        reqDTO.setId(opt.getId());
        reqDTO.setProductName(opt.getProductName());
        reqDTO.setPrice(opt.getPrice());
        reqDTO.setProductDetailDesc(opt.getProductDetailDesc());
        reqDTO.setProductShortDesc(opt.getProductShortDesc());
        reqDTO.setQuantity(opt.getQuantity());
        reqDTO.setSubCategoryID(opt.getSubCategory().getId());
        reqDTO.setProductImage(opt.getProductImage());

        model.addAttribute("categories", subCategoryService.findAll());
        model.addAttribute("product", reqDTO);

        return "update-product";
    }

    @PostMapping("/product/edit")
    public String processEditProduct(@Valid @ModelAttribute("product") EditProductReqDTO editProductReqDTO,
                                     BindingResult result,
                                     RedirectAttributes redirect,
                                     Model model) {
        if (result.hasErrors()) {
            model.addAttribute("categories", subCategoryService.findAll());

            return "/add-new-product";
        }

        productService.editProduct(editProductReqDTO);
        redirect.addFlashAttribute("successMessage", "Edit product successfully");
        return "redirect:/admin/product";
    }

    @GetMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteById(id);
        return "redirect:/admin/product";
    }
}
