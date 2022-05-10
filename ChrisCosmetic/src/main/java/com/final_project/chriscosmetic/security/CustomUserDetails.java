package com.final_project.chriscosmetic.security;

import com.final_project.chriscosmetic.entity.Account;
import com.final_project.chriscosmetic.entity.Cart;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

public class CustomUserDetails implements UserDetails {

	private Account account;
	private Cart cart;

	public CustomUserDetails(Account account, Cart cart) {
		this.account = account;
		this.cart = cart;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return account.getRoles().stream().map(r ->new SimpleGrantedAuthority(r.getRoleName())).collect(Collectors.toList());
	}

	@Override
	public String getPassword() {
		return account.getPassword();
	}

	@Override
	public String getUsername() {
		return account.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public Account getAccount() {
		return account;
	}

	public Cart getCart() {
		return cart;
	}
}
