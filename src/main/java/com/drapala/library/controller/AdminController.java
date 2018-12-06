package com.drapala.library.controller;

import com.drapala.library.repository.BookRepository;
import com.drapala.library.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.drapala.library.entity.Book;

import java.util.List;


@RestController
@RequestMapping("/library")
public class AdminController {

    @Autowired
    AdminService service;

    @GetMapping("/TestPage")
    public String hello() {
        return "hello user";
    }

    @GetMapping("/books")
    public List getBooks() {
        return service.getAllBooks();
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping("/books")
    public Book addBook(@RequestBody Book book) {
        return service.addBook(book);


    }

    @GetMapping("/books/{bookId}")
    public Book showSingleBook(@PathVariable(value = "bookId") Long id) {
        return service.getSingleBook(id);
    }

    @PutMapping("/books/{bookId}")
    public Book updateBook(@PathVariable(value = "bookId") Long id ,@RequestBody Book book) {
        return service.updateBook(id, book);

    }


    @PutMapping("books/{bookId}/amount")
    public int adjustStock(@PathVariable(value = "bookId") Long id, @RequestBody int amount) {

        return service.adjustStock(id, amount);
    }

    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @DeleteMapping("/books/{bookId}")
    public String deleteBook(@PathVariable(value = "bookId") Long id) {
        return service.deleteBook(id);
    }




}
