package com.final_project.chriscosmetic.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "sub_categories")
public class SubCategory {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sub_category_id")
	private Long id;
	@Column
	private String subCategoryName;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id")
	private Category category;

	@OneToMany(mappedBy = "subCategory", fetch = FetchType.LAZY)
	private Set<Product> products;

	public Long getId() {
		return id;
	}

	public String getSubCategoryName() {
		return subCategoryName;
	}

	public Category getCategory() {
		return category;
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}

	public SubCategory() {
		super();
	}

	public SubCategory(String subCategoryName, Category category) {
		super();
		this.subCategoryName = subCategoryName;
		this.category = category;
	}

	public SubCategory(Long id, String subCategoryName, Category category, Set<Product> products) {
		super();
		this.id = id;
		this.subCategoryName = subCategoryName;
		this.category = category;
		this.products = products;
	}

	@Override
	public String toString() {
		return "SubCategory{" +
				"id=" + id +
				", subCategoryName='" + subCategoryName + '\'' +
				", category=" + category +
				", products=" + products +
				'}';
	}
}
