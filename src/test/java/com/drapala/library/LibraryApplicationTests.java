package com.drapala.library;

import com.drapala.library.entity.Book;
import com.drapala.library.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

@Transactional
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class LibraryApplicationTests {


	@Autowired
	BookRepository repository;


	@Autowired
	EntityManager em;

	@Test
	public void contextLoads() {
	}

	@Test
	public void getPerId() {

		Book b = new Book("Pan Tadeusz");

		repository.addBook(b);

		Long id = 1l;
		Query query = em.createQuery("Select b from Book b where id = :id")
				.setParameter("id", id);
		Book book = (Book) query.getSingleResult();

		log.info("{}", book);
	}

}
