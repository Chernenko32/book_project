package com.example.book_project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.book_project.entity.Book;


@Repository
public interface BookDao extends JpaRepository<Book, String>{

	public List<Book> findByType(String type);
	
	public List<Book> findByAuthor(String author);
	
	public List<Book> findByStock(Integer stock);
	
	public List<Book> findByPrice(Integer price);
	
	public List<Book> findBybookName(String bookName);
	
	public List<Book> findTop5ByOrderBySalesDesc();
}
