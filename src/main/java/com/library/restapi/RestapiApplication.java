package com.library.restapi;

import com.library.restapi.model.entity.Book;
import com.library.restapi.repository.LibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class RestapiApplication implements CommandLineRunner {

	@Autowired
	LibraryRepository libraryRepository;

	public static void main(String[] args) {
		SpringApplication.run(RestapiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		libraryRepository.save(new Book(1L, "Sherlock Holmes", "Conan Doyle", 1999, "Fiction", "12345678", true));
		libraryRepository.save(new Book(2L, "Clean Code", "Robert Cecil Martin", 2003, "Programming", "12345679", true));
		libraryRepository.save(new Book(3L, "Cracking the Coding Interview", "Gayle Laakmann McDowell", 2008, "Programming", "12345680", true));
		libraryRepository.save(new Book(4L, "The Lord Of The Rings", "Tolkien", 1999, "Sci-Fi", "12345681", true));
		libraryRepository.save(new Book(5L, "The Dune Chronicles", "Frank Herbert", 1999, "Sci-Fi", "12345682", true));
		libraryRepository.save(new Book(6L, "Think and Grow Rich", "Napoleon Hill", 1937, "Self-help", "12345683", true));
		libraryRepository.save(new Book(7L, "How to Win Friends and Influence People", "Dale Carnegie", 1936, "Self-help", "12345684", true));
		libraryRepository.save(new Book(8L, "See You in the Cosmos", "Jack Cheng", 1999, "STEM", "12345685", true));
		libraryRepository.save(new Book(9L, "The Boy Who Harnessed the Wind", "Bryan Mealer", 2002, "STEM", "12345686", true));
		libraryRepository.save(new Book(10L, "Principles", "Ray Dalio", 2017, "Biography", "12345687", true));
	}
}
