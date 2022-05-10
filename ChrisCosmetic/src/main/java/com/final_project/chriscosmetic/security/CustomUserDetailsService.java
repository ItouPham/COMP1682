package com.final_project.chriscosmetic.security;

import com.final_project.chriscosmetic.entity.Account;
import com.final_project.chriscosmetic.entity.Cart;
import com.final_project.chriscosmetic.repository.AccountRepository;
import com.final_project.chriscosmetic.repository.CartRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private AccountRepository accountRepository;
    private CartRepository cartRepository;

    public CustomUserDetailsService(AccountRepository accountRepository, CartRepository cartRepository) {
        this.accountRepository = accountRepository;
        this.cartRepository = cartRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account acc = accountRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Account not found"));
        Cart cart = cartRepository.findByAccountId(acc.getId()).orElseGet(() -> cartRepository.save(new Cart(acc)));
        return new CustomUserDetails(acc, cart);
    }
}
