package com.mohana.bookstore.bookController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mohana.bookstore.message.BookStoreMessage;
import com.mohana.bookstore.model.Book;
import com.mohana.bookstore.service.BookService;


@RestController
@RequestMapping("/book")
public class BookController {
	
	@Autowired
	private BookService bookService;
	
	//create methods
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public String saveBook(@RequestBody Book book) {
		Book objectBook= bookService.create(book);	
		if(objectBook!=null)
			return objectBook.getId();
		else
			return BookStoreMessage.BOOK_NAME_EXIST;
	}
	
	//Get by bookAuthor
	@RequestMapping(value="/getBookAuthor", method=RequestMethod.GET)
	public List<Book> getByBookAuthor(@RequestParam String bookAuthor) {
		return bookService.getByBookAuthor(bookAuthor);
	}
	//Get by bookName
	@RequestMapping(value="/getBookName", method=RequestMethod.GET)
	public Book getBook(@RequestParam String bookName) {
		return bookService.getByBookName(bookName);
	}
	
	//Get by bookPrice 
	@RequestMapping(value="/getBookPrice", method=RequestMethod.GET)	
	public List<Book> getBookPrice(@RequestParam int bookPrice){
		return bookService.getByBookPrice(bookPrice);
	}
	
	//Get by all data from database
	@RequestMapping(value="/getAll", method=RequestMethod.GET)
	public List<Book> getAll(){
		return bookService.getAll();
	}
	
	
	//Update data
	@RequestMapping(value="/update", method=RequestMethod.PUT)
	public Book update(@RequestBody Book book) {
		return bookService.update(book);
		
	}
	
	//Delete by bookName
	@RequestMapping(value="/deleteBookName", method=RequestMethod.DELETE)
	public String delete(@RequestParam String bookName){
		bookService.delete(bookName);
		return BookStoreMessage.DELETE_BY_BOOK_NAME;
		
	}
	
	//Delete all records
	@RequestMapping(value="/deleteAll", method=RequestMethod.DELETE)
	public String deleteAll() {
		bookService.deleteAll();
		return  BookStoreMessage.DELETED_ALL_RECORDS;
	}
	
}
