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
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@Transactional
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class BookRepositoryTests {

	@Autowired
	BookRepository repository;

	@Autowired
	EntityManager em;

	@Test
	@Before
	public void addBook() {
		if (repository.getAllBooks().isEmpty()) {
			log.info("addBook() method is running");
			Query query = em.createQuery("SELECT COUNT(*) FROM Book");
			Long booksAmount = (Long) query.getSingleResult();
			assertEquals(0, (long)booksAmount);
			Book book1 = new Book("Adam", "Mickiewicz", "Pan Tadeusz",
					2014, 21.55);
			log.info("Added book where {}", book1);
			repository.addBook(book1);
			query = em.createQuery("SELECT COUNT(*) FROM Book");
			booksAmount = (Long) query.getSingleResult();
			assertEquals(1, (long)booksAmount);

			Book book2 = new Book("Henryk", "Sienkiewicz", "Quo Vadis",
					2011, 39.99);
			repository.addBook(book2);
			query = em.createQuery("SELECT COUNT(*) FROM Book");
			booksAmount = (Long) query.getSingleResult();
			assertEquals(2, (long)booksAmount);
		}
	}

	@Test
	public void findBookById() {
		Book book1 = repository.findBookById(1L);
		assertNotNull(book1);
		log.info("Book where id = 1 :{}", book1);
		Book book2 = repository.findBookById(2L);
		assertNotNull(book2);
		log.info("Book where id = 2 :{}", book2);
	}

	@Test
	public void getAllBooks() {
		List allBooks = repository.getAllBooks();
		log.info("All books: {}", allBooks);

		Query query = em.createQuery("SELECT COUNT(*) FROM Book");
		Long booksAmount =  (Long) query.getSingleResult();
		assertEquals((long)booksAmount, (long)allBooks.size());
	}

	@Test
	public void editBook() {
		Query query = em.createQuery("SELECT id FROM Book WHERE title = 'Pan Tadeusz' ");
		Long id = (Long) query.getSingleResult();
		Book changedData = new Book("Adam", "Mickiewicz", "Pan Tadeusz",
				2016, 29.99);
		repository.editBook(id, changedData);
		assertEquals(changedData.getPublicationsYear(), repository.findBookById(id).getPublicationsYear());
		assertEquals(changedData.getPrice(), repository.findBookById(id).getPrice(), 0);
	}

/*	@Test
	public void deleteById() {
		Query query = em.createQuery("SELECT COUNT(*) FROM Book");
		Long startAmount = (Long) query.getSingleResult();

		query = em.createQuery("SELECT id FROM Book WHERE title = 'Pan Tadeusz' ");
		Long id = (Long) query.getSingleResult();

		repository.deleteById(id);

		query = em.createQuery("SELECT COUNT(*) FROM Book");
		Long endAmount = (Long) query.getSingleResult();

		assertEquals((long)startAmount-1L, (long) endAmount);
	}*/

	@Test
	public void deleteById() {
		Query query = em.createQuery("SELECT id FROM Book WHERE title = 'Pan Tadeusz' ");
		long id = (Long) query.getSingleResult();

		assertNotNull(repository.findBookById(id));

		repository.deleteById(id);

		assertNull(repository.findBookById(id));

	}



	@Test
	public void adjustStock() {
		Query query = em.createQuery("SELECT id FROM Book WHERE title = 'Pan Tadeusz'");
		Long id = (Long) query.getSingleResult();

		query = em.createQuery("SELECT quantityInStock FROM Book  WHERE id = :id")
				.setParameter("id", id);

		int startQuantity = (int) query.getSingleResult();
		int amount = 77;

		repository.adjustStock(id, amount);

		query = em.createQuery("SELECT quantityInStock FROM Book  WHERE id = :id")
				.setParameter("id", id);
		int endQuantity = (int) query.getSingleResult();

		assertEquals(startQuantity + amount, endQuantity);
	}



}
