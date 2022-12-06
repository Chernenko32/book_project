package com.example.book_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.book_project.service.ifs.BookService;
import com.example.book_project.vo.BookRequest;
import com.example.book_project.vo.BookResponse;
import com.example.book_project.vo.BookSalesRequest;
import com.example.book_project.vo.BookSalesResponse;
@CrossOrigin
@RestController
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	@PostMapping(value = "/api/createBook")
	public BookResponse createBook(@RequestBody BookRequest req) {
		return bookService.createBook(req.getBookName(),req.getIsbn(),req.getAuthor()
				,req.getPrice(),req.getStock(),req.getSales(),req.getType());
	}
	
	@PostMapping(value = "/api/reviseCourse")
	public BookResponse reviseCourse(@RequestBody BookRequest req) {
		return bookService.reviseBook(req);
	}
	
	@GetMapping(value = "/api/getBookType")
	public BookResponse getBookType(@RequestParam String type) {
		return bookService.getBookType(type);
	}
	
	@GetMapping(value = "/api/getBook")
	public BookResponse getBook(@RequestParam(required = false) String mode,@RequestParam(required = false)String isbn,@RequestParam(required = false)String bookName,@RequestParam(required = false)String author) {
		return bookService.getBook(mode,isbn,bookName, author);
	}
	
	@PostMapping(value = "/api/updateStock")
	public BookResponse updateStock(@RequestBody BookRequest req) {
		return bookService.updateStock(req);
	}
	
	@PostMapping(value = "/api/updatePrice")
	public BookResponse updatePrice(@RequestBody BookRequest req) {
		return bookService.updatePrice(req);
	}
	
	@PostMapping(value = "/api/bookSales")
	public BookSalesResponse bookSales(@RequestBody BookSalesRequest bookSalesRequest) {
		return bookService.bookSales(bookSalesRequest);
	}
	@GetMapping(value = "/api/top5")
	public BookResponse findTop5ByOrderBySalesDesc() {
		return bookService.findTop5ByOrderBySalesDesc();
	}
	
}
