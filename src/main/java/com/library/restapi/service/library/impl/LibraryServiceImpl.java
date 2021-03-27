package com.library.restapi.service.library.impl;

import com.library.restapi.model.dto.BookDTO;
import com.library.restapi.model.dto.BookResponse;
import com.library.restapi.model.entity.Book;
import com.library.restapi.repository.LibraryRepository;
import com.library.restapi.service.library.LibraryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LibraryServiceImpl implements LibraryService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final LibraryRepository libraryRepository;

    public LibraryServiceImpl(LibraryRepository libraryRepository) {
        this.libraryRepository = libraryRepository;
    }

    /**
     * Fetches all books from the database.
     *
     * @return List of Books alongside the User who has the book
     * @see BookResponse
     * @see Book
     */
    @Override
    public List<BookResponse> findAllBooks() {
        logger.debug("LibraryServiceImpl -> findAllBooks()");
        Optional<List<BookResponse>> books = libraryRepository.findAllBooks();
        return books.orElse(List.of());
    }

    /**
     * Finds book from the database by submitted name.
     * <p>
     *     Returns <code>null</code> if no book exists with the specified name.
     * </p>
     *
     * @param name the title of the book queried
     * @return BookDTO object corresponding to the found result
     * @see BookDTO
     */
    @Override
    public BookDTO findBookByName(String name) {
        logger.debug("LibraryServiceImpl -> findBookByName({})", name);
        Optional<Book> book = libraryRepository.findBookByName(name);
        return book.map(Book::toBookDTO).orElse(null);
    }

    /**
     * Finds books from the database by the submitted category.
     * <p>
     *     Returns <code>null</code> if no books exist
     *     with the specified category.
     * </p>
     *
     * @param category the category to be queried
     * @return List of BookDTOs
     * @see List
     * @see BookDTO
     */
    @Override
    public List<BookDTO> findBooksByCategory(String category) {
        logger.debug("LibraryServiceImpl -> findBooksByCategory({})", category);
        Optional<List<Book>> books = libraryRepository.findBooksByCategory(category);
        return books.map(bookList -> bookList.stream().map(Book::toBookDTO)
                .collect(Collectors.toList())).orElse(null);
    }

    /**
     * Finds books from the database by the submitted author and category.
     * <p>
     *     Returns <code>null</code> if no books exist
     *     with the specified author and category.
     * </p>
     *
     * @param author the author whose books are to be queried
     * @param category the category to be queried
     * @return List of BookDTOs
     * @see List
     * @see BookDTO
     */
    @Override
    public List<BookDTO> findBooksByAuthorAndCategory(String author, String category) {
        if (category != null)  {
            logger.debug("LibraryServiceImpl -> findBooksByAuthorAndCategory({}, {})", author, category);
            Optional<List<Book>> books = libraryRepository.findBooksByAuthorAndCategory(author, category);
            return books.map(bookList -> bookList.stream().map(Book::toBookDTO)
                    .collect(Collectors.toList())).orElse(null);
        }
        return findBooksByAuthor(author);
    }

    /**
     * Finds books from the database by the submitted author.
     * <p>
     *     Returns <code>null</code> if no books exist
     *     from the specified author.
     * </p>
     *
     * @param author the author whose books are to be queried
     * @return List of BookDTOs
     * @see List
     * @see BookDTO
     */
    @Override
    public List<BookDTO> findBooksByAuthor(String author) {
        logger.debug("LibraryServiceImpl -> findBooksByAuthor({})", author);
        Optional<List<Book>> books = libraryRepository.findBooksByAuthor(author);
        return books.map(bookList -> bookList.stream().map(Book::toBookDTO)
                .collect(Collectors.toList())).orElse(null);
    }

}
