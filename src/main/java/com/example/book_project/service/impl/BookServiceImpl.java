package com.example.book_project.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.book_project.entity.Book;
import com.example.book_project.entity.BuyBook;
import com.example.book_project.repository.BookDao;
import com.example.book_project.repository.BuyBookDao;
import com.example.book_project.service.ifs.BookService;
import com.example.book_project.vo.BookRequest;
import com.example.book_project.vo.BookResponse;
import com.example.book_project.vo.BookSalesRequest;
import com.example.book_project.vo.BookSalesResponse;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	private BookDao bookDao;

	@Autowired
	private BuyBookDao buyBookDao;

	@Override
	public BookResponse createBook(String isbn, String bookName, String author, Integer price, Integer stock,
			Integer sales, String type) {
		BookResponse res = new BookResponse();
		if (!StringUtils.hasText(isbn) || !StringUtils.hasText(bookName) || !StringUtils.hasText(author)
				|| price == null || stock == null || sales == null || !StringUtils.hasText(type)) {
			res.setMessage("書籍資料錯誤");
			return res;
		}
		Optional<Book> optional = bookDao.findById(isbn);
		if (!optional.isPresent()) {
			res.setMessage("書籍已存在");
		}

		Book book = new Book();
		book.setIsbn(isbn);
		book.setBookName(bookName);
		book.setAuthor(author);
		book.setPrice(price);
		book.setStock(stock);
		book.setSales(sales);
		book.setType(type);

		res.setIsbn(book.getIsbn());
		res.setBookName(book.getBookName());
		res.setAuthor(book.getAuthor());
		res.setPrice(book.getPrice());
		res.setStock(book.getStock());
		res.setSales(book.getSales());
		res.setType(book.getType());
		bookDao.save(book);
		res.setMessage("書籍新增成功");
		return res;
	}

	@Override
	public BookResponse reviseBook(BookRequest req) {
		BookResponse res = new BookResponse();
		if (req.getBookName().isEmpty()) {
			res.setMessage("書名錯誤");
			return res;
		}
		if (req.getIsbn().isEmpty()) {
			res.setMessage("isbn錯誤");
			return res;
		}
		if (req.getAuthor().isEmpty()) {
			res.setMessage("作者錯誤");
			return res;
		}
		if (req.getPrice() == null) {
			res.setMessage("價格錯誤");
			return res;
		}
		if (req.getStock() == null) {
			res.setMessage("庫存量錯誤");
			return res;
		}
		if (req.getSales() == null) {
			res.setMessage("銷售量錯誤");
			return res;
		}
		if (req.getType().isEmpty()) {
			res.setMessage("種類錯誤");
			return res;
		}
		Optional<Book> bookOp = bookDao.findById(req.getIsbn());
		if (!bookOp.isEmpty()) {
			Book book = bookOp.get();
			book.setIsbn(book.getIsbn());
			book.setBookName(req.getBookName());
			book.setAuthor(req.getAuthor());
			book.setPrice(req.getPrice());
			book.setStock(req.getStock());
			book.setSales(req.getSales());
			book.setType(req.getType());

			res.setIsbn(book.getIsbn());
			res.setBookName(req.getBookName());
			res.setAuthor(req.getAuthor());
			res.setPrice(req.getPrice());
			res.setStock(req.getStock());
			res.setSales(req.getSales());
			res.setType(req.getType());
			bookDao.save(book);
			res.setMessage("書籍資料修改成功");
			return res;
		} else {
			res.setMessage("無此書籍");
			return res;
		}
	}

	@Override
	public BookResponse getBookType(String type) {
		BookResponse res = new BookResponse();
		if (!StringUtils.hasText(type)) {
			res.setMessage("種類錯誤");
			return res;
		}
		List<Book> bookList = bookDao.findByType(type);
		if (bookList == null || bookList.isEmpty()) {
			res.setMessage("無此種類");
			return res;
		}
		List<Book> listBook = new ArrayList<>();
		for (Book book : bookList) {
			Book books = new Book();
			books.setBookName(book.getBookName());
			books.setIsbn(book.getIsbn());
			books.setAuthor(book.getAuthor());
			books.setPrice(book.getPrice());
			books.setStock(book.getStock());
			books.setSales(book.getSales());
			books.setType(book.getType());
			listBook.add(books);
		}
		res.setBookList(listBook);
		return res;
	}

	@Override
	public BookResponse getBook(String mode, String isbn, String bookName, String author) {
		BookResponse res = new BookResponse();
		if (bookName == null && author == null) {
			if (!StringUtils.hasText(isbn)) {
				res.setMessage("isbn錯誤");
				return res;
			}
			Optional<Book> bookOp = bookDao.findById(isbn);
			Book book = bookOp.orElse(null);
			if (book == null) {
				res.setMessage("isbn不存在");
				return res;
			}
			if (mode==null||!mode.equalsIgnoreCase("123")) {
				res.setBookName(book.getBookName());
				res.setIsbn(book.getIsbn());
				res.setAuthor(book.getAuthor());
				res.setPrice(book.getPrice());
				res.setSales(null);
				res.setStock(null);
			} else {
				res.setBookName(book.getBookName());
				res.setIsbn(book.getIsbn());
				res.setAuthor(book.getAuthor());
				res.setPrice(book.getPrice());
				res.setSales(book.getSales());
				res.setStock(book.getStock());
			}
			return res;
		} else if (isbn == null && author == null) {
			if (!StringUtils.hasText(bookName)) {
				res.setMessage("書名錯誤");
				return res;
			}
			List<Book> listBook = bookDao.findBybookName(bookName);
			if (listBook == null || listBook.isEmpty()) {
				res.setMessage("書名不存在");
				return res;
			}
			List<Book> bookList = new ArrayList<>();
			for (Book book : listBook) {
				Book books = new Book();
				if (mode==null||!mode.equalsIgnoreCase("123")) {
					books.setBookName(book.getBookName());
					books.setIsbn(book.getIsbn());
					books.setAuthor(book.getAuthor());
					books.setPrice(book.getPrice());
					books.setSales(null);
					books.setStock(null);
				} else {
					books.setBookName(book.getBookName());
					books.setIsbn(book.getIsbn());
					books.setAuthor(book.getAuthor());
					books.setPrice(book.getPrice());
					books.setSales(book.getSales());
					books.setStock(book.getStock());
					
				}
				bookList.add(books);
			}
			res.setBookList(bookList);
			return res;
		} else {
			if (!StringUtils.hasText(author)) {
				res.setMessage("作者錯誤");
				return res;
			}
			List<Book> listBook = bookDao.findByAuthor(author);
			if (listBook == null || listBook.isEmpty()) {
				res.setMessage("作者不存在");
				return res;
			}
			List<Book> bookList = new ArrayList<>();
			for (Book book : listBook) {
				Book books = new Book();
				if (mode==null||!mode.equalsIgnoreCase("123")) {
					books.setBookName(book.getBookName());
					books.setIsbn(book.getIsbn());
					books.setAuthor(book.getAuthor());
					books.setPrice(book.getPrice());
					books.setSales(null);
					books.setStock(null);
				} else {
					books.setBookName(book.getBookName());
					books.setIsbn(book.getIsbn());
					books.setAuthor(book.getAuthor());
					books.setPrice(book.getPrice());
					books.setSales(book.getSales());
					books.setStock(book.getStock());

				}
				bookList.add(books);
			}
			res.setBookList(bookList);
			return res;
		}
	}

	@Override
	public BookResponse updateStock(BookRequest req) {
		BookResponse res = new BookResponse();
		if (req.getIsbn().isEmpty()) {
			res.setMessage("書名錯誤");
			return res;
		}
		Optional<Book> bookOp = bookDao.findById(req.getIsbn());
		if (bookOp.isPresent()) {
			Book book = bookOp.get();
			book.setStock(req.getStock());

			res.setIsbn(req.getIsbn());
			res.setBookName(req.getBookName());
			res.setAuthor(req.getAuthor());
			res.setPrice(req.getPrice());
			res.setStock(req.getStock());
			bookDao.save(book);
			bookDao.save(book);
			res.setMessage("庫存修改成功");
			return res;
		}
		res.setMessage("無此書名");
		return res;
	}

	@Override
	public BookResponse updatePrice(BookRequest req) {
		BookResponse res = new BookResponse();
		if (req.getIsbn().isEmpty()) {
			res.setMessage("書名錯誤");
			return res;
		}
		Optional<Book> bookOp = bookDao.findById(req.getIsbn());
		if (bookOp.isPresent()) {
			Book book = bookOp.get();
			book.setPrice(req.getPrice());

			res.setIsbn(req.getIsbn());
			res.setBookName(req.getBookName());
			res.setAuthor(req.getAuthor());
			res.setPrice(req.getPrice());
			res.setStock(req.getStock());
			bookDao.save(book);
			res.setMessage("價格修改成功");
			return res;
		}
		res.setMessage("無此書名");
		return res;
	}

	@Override
	public BookSalesResponse bookSales(BookSalesRequest bookSalesRequest) {
		BookSalesResponse res = new BookSalesResponse();
		Optional<Book> bookOp = bookDao.findById(bookSalesRequest.getIsbn());
		if (!bookOp.isPresent()) {
			res.setMessage("此書號不存在");
			return res;
		}
		// 更新庫存量&銷售量
		Book book = bookOp.get();
		book.setStock(book.getStock() - bookSalesRequest.getAmount());
		book.setSales(book.getSales() + bookSalesRequest.getAmount());
		bookDao.save(book);
		// 設值,插進資料庫
		BuyBook buyBook = new BuyBook();
		buyBook.setId(bookSalesRequest.getId());
		buyBook.setIsbn(bookSalesRequest.getIsbn());
		buyBook.setAmount(bookSalesRequest.getAmount());
		buyBook.setTotalPrice(book.getPrice() * bookSalesRequest.getAmount());
		buyBookDao.save(buyBook);
		// 顯示
		res.setBookName(book.getBookName());
		res.setIsbn(bookSalesRequest.getIsbn());
		res.setAuthor(book.getAuthor());
		res.setPrice(book.getPrice());
		res.setAmount(bookSalesRequest.getAmount());
		res.setTotalPrice(book.getPrice() * bookSalesRequest.getAmount());
		res.setMessage("購買成功");
		return res;
	}

	@Override
	public BookResponse findTop5ByOrderBySalesDesc() {
		BookResponse res = new BookResponse();
		List<Book> top5 = bookDao.findTop5ByOrderBySalesDesc();
		List<Book> top5List=new ArrayList<>();
		for(Book book:top5) {
			Book books = new Book();
			books.setSales(book.getSales());
			books.setBookName(book.getBookName());
			books.setIsbn(book.getIsbn());
			books.setAuthor(book.getAuthor());
			books.setPrice(book.getPrice());
			top5List.add(books);
		}
		res.setBookList(top5List);
		return res;
	}

}
