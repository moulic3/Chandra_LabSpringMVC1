package com.gl.lib.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.lib.entity.Book;
import com.gl.lib.repository.BookRepository;
@Service
public class BookService {
	@Autowired
	BookRepository bookRep;
	
	public void addBook(Book b1) {
		bookRep.save(b1);
	}
	
	public void deleteBook(int bno) {
		bookRep.deleteById(bno);
	}

	public Book getBook(int bno) {
		return bookRep.findById(bno).get();
	}

	public void updateBook(int bno, Book newBook) {
		Book dbbook = getBook(bno);
		dbbook.setAuthor(newBook.getAuthor());
		dbbook.setTitle(newBook.getTitle());
		dbbook.setCategory(newBook.getCategory());
		bookRep.save(dbbook);
		
	}

	public List<Book> getAllBooks() {
		return bookRep.findAll();
	}
	
}
