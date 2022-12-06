package com.example.book_project.service.ifs;

import com.example.book_project.vo.BookRequest;
import com.example.book_project.vo.BookResponse;
import com.example.book_project.vo.BookSalesRequest;
import com.example.book_project.vo.BookSalesResponse;


public interface BookService {

	public BookResponse createBook(String bookName, String isbn, String author, Integer price, Integer stock,
			Integer sales, String type);

	public BookResponse reviseBook(BookRequest req);
	
	public BookResponse getBookType(String type);
	
	public BookResponse getBook(String mode,String isbn,String bookName,String author);
	
	public BookResponse updateStock(BookRequest req);
	
	public BookResponse updatePrice(BookRequest req);
	
	public BookSalesResponse bookSales(BookSalesRequest bookSalesRequest);
	
	public BookResponse findTop5ByOrderBySalesDesc();
	
}
