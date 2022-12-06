package com.example.book_project.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BookRequest {

	@JsonProperty("isbn")
	private String isbn;
	@JsonProperty("book_name")
	private String bookName;
	@JsonProperty("author")
	private String author;
	@JsonProperty("price")
	private Integer price;
	@JsonProperty("stock")
	private Integer stock;
	@JsonProperty("sales")
	private Integer sales;
	@JsonProperty("type")
	private String type;

	public BookRequest() {

	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public Integer getSales() {
		return sales;
	}

	public void setSales(Integer sales) {
		this.sales = sales;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
