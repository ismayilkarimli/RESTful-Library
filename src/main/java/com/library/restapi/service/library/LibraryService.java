package com.library.restapi.service.library;

import com.library.restapi.model.dto.BookDTO;
import com.library.restapi.model.dto.BookResponse;

import java.util.List;

public interface LibraryService {

    List<BookResponse> findAllBooks();

    BookDTO findBookByName(String name);

    List<BookDTO> findBooksByCategory(String category);

    List<BookDTO> findBooksByAuthor(String author);

    List<BookDTO> findBooksByAuthorAndCategory(String author, String category);

}
