package com.mohana.bookstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mohana.bookstore.model.Book;
import com.mohana.bookstore.repository.BookRepository;

@Service
public class BookService {
	
	@Autowired
	private BookRepository bookRepository;
	
	//create operations
	//Before inserting database checking this by bookname exist or not if it is bookname exist, then it is not inserted into the database
	public Book create(Book book ) {
		Book bookObject=bookRepository.findByBookName(book.getBookName());
		if(bookObject != null) {
			if(!bookObject.getBookName().equals(book.getBookName())) {
			return bookRepository.save(new Book(book.getBookName(),  book.getBookAuthor(), book.getBookCountry(), book.getBookPrice()));
			}else {
				return null;
			}
		}
		return bookRepository.save(new Book(book.getBookName(),  book.getBookAuthor(), book.getBookCountry(), book.getBookPrice()));
	}

	//Retrieve operations
	public List<Book> getAll(){	
		return bookRepository.findAll();
	}
	
	
	public List<Book>getByBookAuthor(String bookAuthor){
		return bookRepository.findByBookAuthor(bookAuthor);
	}
	
	
	public Book getByBookName(String bookName) {
		return bookRepository.findByBookName(bookName);
	}
	
	public List<Book> getByBookPrice(int bookPrice) {
		return bookRepository.findByBookPrice(bookPrice);
	}
	
     
	//Update operation
	//The bookName is unique, so based on bookName updating data
	public Book update(Book book) {	
		Book b=bookRepository.findByBookName(book.getBookName());
		b.setBookAuthor(book.getBookAuthor());
		b.setBookPrice(book.getBookPrice());
		b.setBookCountry(book.getBookCountry());
		return bookRepository.save(b);
	}
	
	//Delete operation
	public void deleteAll() {
		bookRepository.deleteAll();
	}
	
	//Book name is unique, so based on bookName delete data
	public void delete(String bookName) {
	 bookRepository.deleteByBookName(bookName);
					
	}
}
