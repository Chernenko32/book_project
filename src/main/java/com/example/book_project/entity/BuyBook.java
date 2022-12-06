package com.example.book_project.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "buy_book")
public class BuyBook {
	@Id
	@Column(name = "id")
	private String id;
	@Column(name = "isbn")
	private String isbn;
	@Column(name = "amount")
	private Integer amount;
	@Column(name = "total_price")
	private Integer totalPrice;

	public BuyBook() {

	}

	public BuyBook(String id, String bookName, String isbn, String author, Integer price, Integer amount,
			Integer totalPrice) {
		this.id = id;
		this.isbn = isbn;
		this.amount = amount;
		this.totalPrice = totalPrice;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Integer getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}

}
