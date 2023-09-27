package com.gl.lib.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gl.lib.entity.Book;
import com.gl.lib.entity.Student;
import com.gl.lib.service.BookService;

//@RestController
@Controller
//@RequestMapping("/library")
@RequestMapping("/books")
public class LibraryController {
	@Autowired
	BookService bookService;

	@GetMapping("/greet")
	public String greet() {
		return "Hello Mouli";
	}

	@GetMapping("/getStudent")
	public Student getStudent() {
		return new Student(100, 98.7, "Alice");
	}

	@GetMapping("/getBook")
	public Book getBook(@RequestParam("bno") int bno) {

		return bookService.getBook(bno);
	}

	@PostMapping("/addBookReqParam")
	public String addBook(@RequestParam("bno") int bno, @RequestParam("bname") String bname,
			@RequestParam("author") String author, @RequestParam("category") String category) {
		Book b1 = new Book(bno, bname, author, category);
		bookService.addBook(b1);

		return "Book Added Successfully";
	}

	@PostMapping("/addBookReqBody")
	public String addBookReqBody(@RequestBody Book b1) {
		System.out.println(b1.getAuthor());
		bookService.addBook(b1);
		return "Book Added Successfully";

	}

	@DeleteMapping("/deleteBook")
	public String deleteBook(@RequestParam("bno") int bno) {

		bookService.deleteBook(bno);

		return "Book deleted Successfully";
	}

	@PutMapping("/updateBook")
	public String updateBook(@RequestParam("bno") int bno, @RequestBody Book newBook) {

		bookService.updateBook(bno, newBook);
		return "Book updated Successfully";

	}
	
	//----> Display the API's in HTML
	@GetMapping("/list")
	public String listBook(Model theModel){
		List<Book> listBooks = bookService.getAllBooks();
		theModel.addAttribute("books", listBooks);
		return "books/list-books";
		//return bookService.getAllBooks();
		
	}
	
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {
		Book b1 = new Book();
		theModel.addAttribute("book", b1);
		return "books/book-form";
	}
	
	@PostMapping("/save")
	public String saveBook(@ModelAttribute("book") Book b1) {
		bookService.addBook(b1);
		return "redirect:/books/list";
	}
	
	@PostMapping("/delete")
	public String deleteMyBook(@RequestParam("bookId") int bno) {
		bookService.deleteBook(bno);
		return "redirect:/books/list";

	}
	
	@PostMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("bookId") int bno, Model theModel) {

		Book bookdb = bookService.getBook(bno);
		theModel.addAttribute("book", bookdb);
		return "books/book-form";

	}
	

}
