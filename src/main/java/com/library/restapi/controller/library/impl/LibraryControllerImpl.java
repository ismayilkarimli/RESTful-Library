package com.library.restapi.controller.library.impl;

import com.library.restapi.controller.library.LibraryController;
import com.library.restapi.model.dto.BookDTO;
import com.library.restapi.model.dto.BookResponse;
import com.library.restapi.service.library.LibraryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v0.1/lib")
public class LibraryControllerImpl implements LibraryController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final LibraryService libraryService;


    LibraryControllerImpl(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @Override
    @GetMapping("/")
    @ResponseBody
    public ResponseEntity<?> findAllBooks() {
        logger.debug("find all books");
        List<BookResponse> books = libraryService.findAllBooks();
        if (books != null) {
            return new ResponseEntity<>(books, HttpStatus.OK);
        }
        return new ResponseEntity<>("No books found", HttpStatus.NOT_FOUND);
    }

    @Override
    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> findBookByName(@PathVariable String name) {
        logger.debug("finding name {}", name);
        BookDTO book = libraryService.findBookByName(name);
        if (book != null) {
            return new ResponseEntity<>(book, HttpStatus.OK);
        }
        return new ResponseEntity<>("Book not found", HttpStatus.NOT_FOUND);
    }

    @Override
    @RequestMapping(value = "/categories/{category}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> findBooksByCategory(@PathVariable String category) {
        logger.debug("finding cateogry {}", category);
        List<BookDTO> books = libraryService.findBooksByCategory(category);
        if (books != null) {
            return new ResponseEntity<>(books, HttpStatus.OK);
        }
        return new ResponseEntity<>("No books found", HttpStatus.NOT_FOUND);
    }

    @Override
    @GetMapping("/authors/{author}")
    @ResponseBody
    public ResponseEntity<?> findBooksByAuthor(@PathVariable String author,
                                               @RequestParam(required = false) String category) {
        logger.debug("finding author {}", author);
        logger.debug("finding category {}", category);
        List<BookDTO> books =  libraryService.findBooksByAuthorAndCategory(author, category);
        System.out.println(books);
        if (books != null) {
            return new ResponseEntity<>(books, HttpStatus.OK);
        }
        return new ResponseEntity<>("No books found", HttpStatus.NOT_FOUND);
    }
}
