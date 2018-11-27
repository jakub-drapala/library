package com.drapala.library.controller;

import com.drapala.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.drapala.library.entity.Book;

import java.util.List;


@RestController
@RequestMapping("/library")
public class AdminController {

    @Autowired
    BookRepository repository;

    @GetMapping("/TestPage")
    public String hello() {
        return "hello user";
    }

    @GetMapping("/books")
    public List getBooks() {
        return repository.getAllBooks();
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping("/books")
    public Book addBook(@RequestBody Book book) {
        repository.addBook(book);

        return book;//"redirect:/library/MainPage";
    }

    @GetMapping("/books/{bookId}")
    public Book showSingleBook(@PathVariable(value = "bookId") Long id) {
        return repository.findBookById(id);
    }

    @PutMapping("/books/{bookId}")
    public Book updateBook(@PathVariable(value = "bookId") Long id ,@RequestBody Book book) {
        repository.editBook(id, book);

        return repository.findBookById(id);
    }

    @DeleteMapping("/books/{bookId}")
    public String deleteBook(@PathVariable(value = "bookId") Long id) {
        String name = repository.findBookById(id).getName();
        repository.deleteById(id);
        return name + " has been removed";
    }




}
