package com.final_project.chriscosmetic.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "payments")
public class Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "payment_id")
	private Long id;
	@Column
	private BigDecimal paymentAmount;
	@Column
	private String status;
	@OneToOne
	@JoinColumn(name = "order_id")
	private Order order;

	public Payment() {
	}

	public Payment(Long id, BigDecimal paymentAmount, String status, Order order) {
		this.id = id;
		this.paymentAmount = paymentAmount;
		this.status = status;
		this.order = order;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(BigDecimal paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
}
