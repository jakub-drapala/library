package com.drapala.library.service;

import com.drapala.library.entity.Book;
import com.drapala.library.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class AdminService {

    @Autowired
    BookRepository repository;

    public List getAllBooks () {
        return repository.getAllBooks();
    }

    public Book getSingleBook(Long id) {
        return repository.findBookById(id);
    }

    public Book addBook(Book book) {
        repository.addBook(book);
        return book;
    }

    public Book updateBook(Long id, Book book) {
        repository.editBook(id, book);
        return repository.findBookById(id);
    }

    public String deleteBook(Long id) {
        String name = repository.findBookById(id).getTitle();
        repository.deleteById(id);
        return name + " has been removed.";
    }


    public int adjustStock(Long id, int quantity) {

        repository.adjustStock(id, quantity);

        return quantity;
    }




}
