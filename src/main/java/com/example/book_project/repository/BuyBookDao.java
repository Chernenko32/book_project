package com.example.book_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.book_project.entity.BuyBook;
@Repository
public interface BuyBookDao extends JpaRepository<BuyBook, String>{

}
