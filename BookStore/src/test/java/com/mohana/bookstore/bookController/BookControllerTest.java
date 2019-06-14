package com.mohana.bookstore.bookController;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.mohana.bookstore.message.BookStoreMessage;
import com.mohana.bookstore.model.Book;
import com.mohana.bookstore.service.BookService;
import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookControllerTest {
	@Mock 
	BookService bookService;
	
	@InjectMocks
	BookController bookController;
	
	private String bookAuthor;
	
	private String bookName;
	
	private int bookPrice;
	private Book objectBook;
	
	
	@Before
	public void setUp() {
		bookAuthor="joms";
		bookName="java";
		bookPrice=456;
	 objectBook= new Book("java","joms", "usa", 456);
	}
   
	@Test
	public void testSaveBook() {
		when(bookService.create(objectBook)).thenReturn(objectBook);
		String result=bookController.saveBook(objectBook);
		assertEquals(result, null);
		
		verify(bookService).create(objectBook);

	}
	
	@Test
	public void testGetByBookAuthor() {
		List<Book> listBook= new ArrayList<Book>();
		listBook.add(objectBook);
		when(bookService.getByBookAuthor(bookAuthor)).thenReturn(listBook);
		
		List<Book> result=bookController.getByBookAuthor(bookAuthor);
		assertEquals(1, result.size());
        
		verify(bookService).getByBookAuthor(bookAuthor);
		
	}
	

	@Test
	public void testGetBook() {	
		when(bookService.getByBookName(bookName)).thenReturn(objectBook);
		
		Book result=bookController.getBook(bookName);
		
		assertEquals(result.getBookAuthor(),"joms");
		assertEquals(result.getBookCountry(),"usa");
		assertEquals(result.getBookPrice(),456);
		verify(bookService).getByBookName(bookName);
	}
	
	@Test
	public void testGetBookPrice() {
		List<Book> listBook= new ArrayList<Book>();
		listBook.add(objectBook);
		when(bookService.getByBookPrice(bookPrice)).thenReturn(listBook);
		
		List<Book> result=bookController.getBookPrice(bookPrice);
		
         assertEquals(1, result.size());
        
		verify(bookService).getByBookPrice(bookPrice);
	}
	
	
	
	
	@Test
	public void testGetAll() {
		List<Book> listBook= new ArrayList<Book>();
		listBook.add(objectBook);
		when(bookService.getAll()).thenReturn(listBook);
		
		List<Book> result=bookController.getAll();
		
         assertEquals(1, result.size());
        
		verify(bookService).getAll();
	}
	
	
	
	
	@Test
	public void testUpdate() {
		when(bookService.update(objectBook)).thenReturn(objectBook);
		Book result=bookController.update(objectBook);	
		assertEquals(result.getBookAuthor(),"joms");
		assertEquals(result.getBookCountry(),"usa");
		assertEquals(result.getBookPrice(),456);
		verify(bookService).update(objectBook);
	}
	
	
	@Test
	public void testDelete() {
		String result=bookController.delete(bookName);
		assertEquals(result,BookStoreMessage.DELETE_BY_BOOK_NAME);		
	}

	@Test
	public void testDeleteAll() {
		String result=bookController.deleteAll();
		assertEquals(result,BookStoreMessage.DELETED_ALL_RECORDS);
		
	}
}
