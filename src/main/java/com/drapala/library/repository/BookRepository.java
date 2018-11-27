package com.drapala.library.repository;

import com.drapala.library.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class BookRepository {

    @Autowired
    EntityManager em;


    public Book addBook(Book book) {
        em.persist(book);
        return book;
    }

    public Book findBookById(Long id) {
        Query query = em.createQuery("Select b from Book b where id = :id")
                .setParameter("id", id);
        Book book = (Book) query.getSingleResult();
        return book;
    }

    public List getAllBooks() {
        Query query = em.createQuery("Select b from Book b");
        List resultList = query.getResultList();
        return resultList;
    }


    public Book editBook(Long id, Book book) {
        Book beforeUpdating = findBookById(id);
        beforeUpdating.setName(book.getName());
        return book;
    }

    public void deleteById(Long id) {
        Book toDelete = findBookById(id);
        em.remove(toDelete);
    }










}
