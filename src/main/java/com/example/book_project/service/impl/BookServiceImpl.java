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
			res.setMessage("���y��ƿ��~");
			return res;
		}
		Optional<Book> optional = bookDao.findById(isbn);
		if (!optional.isPresent()) {
			res.setMessage("���y�w�s�b");
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
		res.setMessage("���y�s�W���\");
		return res;
	}

	@Override
	public BookResponse reviseBook(BookRequest req) {
		BookResponse res = new BookResponse();
		if (req.getBookName().isEmpty()) {
			res.setMessage("�ѦW���~");
			return res;
		}
		if (req.getIsbn().isEmpty()) {
			res.setMessage("isbn���~");
			return res;
		}
		if (req.getAuthor().isEmpty()) {
			res.setMessage("�@�̿��~");
			return res;
		}
		if (req.getPrice() == null) {
			res.setMessage("������~");
			return res;
		}
		if (req.getStock() == null) {
			res.setMessage("�w�s�q���~");
			return res;
		}
		if (req.getSales() == null) {
			res.setMessage("�P��q���~");
			return res;
		}
		if (req.getType().isEmpty()) {
			res.setMessage("�������~");
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
			res.setMessage("���y��ƭק令�\");
			return res;
		} else {
			res.setMessage("�L�����y");
			return res;
		}
	}

	@Override
	public BookResponse getBookType(String type) {
		BookResponse res = new BookResponse();
		if (!StringUtils.hasText(type)) {
			res.setMessage("�������~");
			return res;
		}
		List<Book> bookList = bookDao.findByType(type);
		if (bookList == null || bookList.isEmpty()) {
			res.setMessage("�L������");
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
				res.setMessage("isbn���~");
				return res;
			}
			Optional<Book> bookOp = bookDao.findById(isbn);
			Book book = bookOp.orElse(null);
			if (book == null) {
				res.setMessage("isbn���s�b");
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
				res.setMessage("�ѦW���~");
				return res;
			}
			List<Book> listBook = bookDao.findBybookName(bookName);
			if (listBook == null || listBook.isEmpty()) {
				res.setMessage("�ѦW���s�b");
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
				res.setMessage("�@�̿��~");
				return res;
			}
			List<Book> listBook = bookDao.findByAuthor(author);
			if (listBook == null || listBook.isEmpty()) {
				res.setMessage("�@�̤��s�b");
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
			res.setMessage("�ѦW���~");
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
			res.setMessage("�w�s�ק令�\");
			return res;
		}
		res.setMessage("�L���ѦW");
		return res;
	}

	@Override
	public BookResponse updatePrice(BookRequest req) {
		BookResponse res = new BookResponse();
		if (req.getIsbn().isEmpty()) {
			res.setMessage("�ѦW���~");
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
			res.setMessage("����ק令�\");
			return res;
		}
		res.setMessage("�L���ѦW");
		return res;
	}

	@Override
	public BookSalesResponse bookSales(BookSalesRequest bookSalesRequest) {
		BookSalesResponse res = new BookSalesResponse();
		Optional<Book> bookOp = bookDao.findById(bookSalesRequest.getIsbn());
		if (!bookOp.isPresent()) {
			res.setMessage("���Ѹ����s�b");
			return res;
		}
		// ��s�w�s�q&�P��q
		Book book = bookOp.get();
		book.setStock(book.getStock() - bookSalesRequest.getAmount());
		book.setSales(book.getSales() + bookSalesRequest.getAmount());
		bookDao.save(book);
		// �]��,���i��Ʈw
		BuyBook buyBook = new BuyBook();
		buyBook.setId(bookSalesRequest.getId());
		buyBook.setIsbn(bookSalesRequest.getIsbn());
		buyBook.setAmount(bookSalesRequest.getAmount());
		buyBook.setTotalPrice(book.getPrice() * bookSalesRequest.getAmount());
		buyBookDao.save(buyBook);
		// ���
		res.setBookName(book.getBookName());
		res.setIsbn(bookSalesRequest.getIsbn());
		res.setAuthor(book.getAuthor());
		res.setPrice(book.getPrice());
		res.setAmount(bookSalesRequest.getAmount());
		res.setTotalPrice(book.getPrice() * bookSalesRequest.getAmount());
		res.setMessage("�ʶR���\");
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
