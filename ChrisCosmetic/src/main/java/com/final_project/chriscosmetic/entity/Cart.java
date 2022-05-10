package com.final_project.chriscosmetic.entity;

import javax.persistence.*;

@Entity
@Table(name = "carts")
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cart_id")
	private Long id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "account_id")
	private Account account;

	public Long getId() {
		return id;
	}

	public Account getAccount() {
		return account;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Cart(Account account) {
		this.account = account;
	}

	public Cart() {
		super();
	}

	@Override
	public String toString() {
		return "Cart{" +
				"id=" + id +
				", account=" + account +
				'}';
	}
}
