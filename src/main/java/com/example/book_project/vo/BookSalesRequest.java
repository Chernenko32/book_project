package com.example.book_project.vo;

public class BookSalesRequest {

	private String id;

	private String bookName;

	private String isbn;

	private Integer amount;

	private Integer passeord;

	public BookSalesRequest() {

	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Integer getPasseord() {
		return passeord;
	}

	public void setPasseord(Integer passeord) {
		this.passeord = passeord;
	}

}
