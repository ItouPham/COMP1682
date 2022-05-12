package com.final_project.chriscosmetic.controller;

import com.final_project.chriscosmetic.dto.req.EditAccountReqDTO;
import com.final_project.chriscosmetic.dto.req.RegisterReqDTO;
import com.final_project.chriscosmetic.entity.Account;
import com.final_project.chriscosmetic.entity.Role;
import com.final_project.chriscosmetic.exception.EmailAlreadyExistException;
import com.final_project.chriscosmetic.exception.RoleNotFoundException;
import com.final_project.chriscosmetic.security.CustomUserDetails;
import com.final_project.chriscosmetic.service.AccountService;
import com.final_project.chriscosmetic.service.AuthService;
import com.final_project.chriscosmetic.service.RoleService;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Objects;

@Controller
public class AuthController {

    private AuthService authService;
    private AccountService accountService;
    private RoleService roleService;

    public AuthController(AuthService authService,
                          AccountService accountService,
                          RoleService roleService) {
        this.authService = authService;
        this.accountService = accountService;
        this.roleService = roleService;
    }

    @GetMapping("/login")
    public String showLoginPage() {
        if (isAuthenticated()) {
            return "redirect:/";
        }
        return "login";
    }

    @GetMapping("/register")
    public String signup(Model model) {
        if (isAuthenticated()) {
            return "redirect:/";
        }
        model.addAttribute("account", new RegisterReqDTO());
        return "register";
    }

    @PostMapping("/register")
    public String processRegister(@ModelAttribute("account") @Valid RegisterReqDTO registerReqDTO,
                                  BindingResult result, RedirectAttributes redirect) {
        if (result.hasErrors()) {
            return "/register";
        }

        try {
            authService.register(registerReqDTO);
        } catch (EmailAlreadyExistException e) {
            result.addError(new FieldError("account", "email", "Email address already in use"));
            return "/register";
        }

        redirect.addFlashAttribute("successMessage", "Register account successfully");
        return "redirect:/login";
    }

    @GetMapping("/profile")
    public String viewProfilePage(@AuthenticationPrincipal CustomUserDetails loggedUser,
                                  Model model) {
        String email = loggedUser.getUsername();
        Account account = accountService.findByEmail(email);
        model.addAttribute("account", account);
        return "profile";
    }

    @GetMapping("/profile/edit")
    public String viewUpdateProfilePage(@AuthenticationPrincipal CustomUserDetails loggedUser,
                                  Model model) {
        String email = loggedUser.getUsername();
        Account account = accountService.findByEmail(email);
        EditAccountReqDTO reqDTO = new EditAccountReqDTO();
        reqDTO.setId(account.getId());
        reqDTO.setLastName(account.getLastName());
        reqDTO.setFirstName(account.getFirstName());
        reqDTO.setEmail(account.getEmail());
        reqDTO.setPassword(account.getPassword());
        reqDTO.setRoleID(account.getRoles().stream().map(Role::getId).findFirst().orElseThrow(RoleNotFoundException::new));
        reqDTO.setAddress(account.getAddress());
        reqDTO.setTelephone(account.getTelephone());

        model.addAttribute("account", reqDTO);
        model.addAttribute("roles", roleService.findAllRole());
        return "update-profile";
    }

    @PostMapping("/profile/update")
    public String updateProfile(@ModelAttribute("account") @Valid EditAccountReqDTO accountDTO,
                                RedirectAttributes redirect,
                                BindingResult result, Model model){
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
            return "/update-profile";
        }

        accountService.editAccount(accountDTO);
        redirect.addFlashAttribute("successMessage", "Update profile successfully");

        return "redirect:/profile";
    }

    @RequestMapping("/403")
    public String show403Page() {
        return "403";
    }

    private boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || AnonymousAuthenticationToken.class.
                isAssignableFrom(authentication.getClass())) {
            return false;
        }
        return authentication.isAuthenticated();
    }
}
