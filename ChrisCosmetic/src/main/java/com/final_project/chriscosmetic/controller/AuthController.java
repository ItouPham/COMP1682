package com.final_project.chriscosmetic.controller;

import com.final_project.chriscosmetic.dto.req.RegisterReqDTO;
import com.final_project.chriscosmetic.exception.EmailAlreadyExistException;
import com.final_project.chriscosmetic.service.AuthService;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

@Controller
public class AuthController {

    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/login")
    public String showLoginPage(){
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

    @RequestMapping("/403")
    public String show403Page()
    {
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
