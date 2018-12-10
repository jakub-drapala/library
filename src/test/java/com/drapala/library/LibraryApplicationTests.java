package com.drapala.library;

import com.drapala.library.entity.Book;
import com.drapala.library.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.*;


import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

import static org.junit.Assert.assertEquals;

@Transactional
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class LibraryApplicationTests {



	@Autowired
	BookRepository repository;



	@Autowired
	EntityManager em;

	 Book book1;
	 Book book2;


	@Test
	@Before
	public void addBook() {
		if (repository.getAllBooks().isEmpty()) {
			log.info("addBook() method is running");
			book1 = new Book("Adam", "Mickiewicz", "Pan Tadeusz",
					2014, 21.55);
			log.info("Added book where {}", book1);
			repository.addBook(book1);
			book2 = new Book("Henryk", "Sienkiewicz", "Quo Vadis",
					2011, 39.99);
			repository.addBook(book2);
		}
	}

	@Test
	public void findBookById() {
		Book book1 = repository.findBookById(1l);
		log.info("Book where id = 1 :{}", book1);
		Book book2 = repository.findBookById(2l);
		log.info("Book where id = 2 :{}", book2);
	}

	@Test
	public void getAllBooks() {
		List allBooks = repository.getAllBooks();
		log.info("All books: {}", allBooks);

		Query query = em.createQuery("Select count(*) from Book b");
		Long booksAmount =  (Long) query.getSingleResult();
		assertEquals((long)booksAmount, (long)allBooks.size());

	}








}
