package com.final_project.chriscosmetic.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id")
	private Long id;
	@ManyToOne
	@JoinColumn(name = "account_id")
	private Account account;
	@OneToMany(mappedBy = "order")
	private List<OrderDetail> orderDetails;

	public Order() {
	}

	public Order(Long id, Account account, List<OrderDetail> orderDetails) {
		this.id = id;
		this.account = account;
		this.orderDetails = orderDetails;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}
}
