package com.library.restapi.controller.library;

import com.library.restapi.model.entity.Book;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface LibraryController {

    ResponseEntity<?> findAllBooks();

    ResponseEntity<?> findBookByName(String name);

    ResponseEntity<?> findBooksByCategory(String category);

    ResponseEntity<?> findBooksByAuthor(String author, String category);
}
