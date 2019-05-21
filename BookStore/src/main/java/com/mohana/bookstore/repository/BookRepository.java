package com.mohana.bookstore.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mohana.bookstore.model.Book;


@Repository
public interface BookRepository extends MongoRepository<Book, String>{
	
	public Book findByBookName(String bookName);
	
	public List<Book> findByBookPrice(int bookPrice);
	
	public List<Book> findByBookAuthor(String bookAuthor);
	
	void deleteByBookName(String bookName);
	
}