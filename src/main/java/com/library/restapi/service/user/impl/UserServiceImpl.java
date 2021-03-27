package com.library.restapi.service.user.impl;

import com.library.restapi.model.entity.Book;
import com.library.restapi.repository.LibraryRepository;
import com.library.restapi.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final LibraryRepository libraryRepository;

    public UserServiceImpl(LibraryRepository libraryRepository) {
        this.libraryRepository = libraryRepository;
    }

    /**
     * Takes book with the specified name
     * in the name of the user with specified id
     *
     * @param userId id of the user who takes the book
     * @param bookName name of the book to be taken
     * @return <code>true</code> if the book can be taken
     *         <code>false</code> if the book cannot be taken
     * @see Book
     *
     */
    @Override
    @Transactional
    public boolean takeBook(Long userId, String bookName) {
        logger.debug("LibraryServiceImpl -> takeBook({}, {})", userId, bookName);
        Optional<Book> book = libraryRepository.findBookByName(bookName);
        if (book.isEmpty() || !book.get().getIsAvailable()) {
            return false;
        }
        libraryRepository.takeBook(userId, bookName);
        return true;
    }

    /**
     * Drops book with the specified name
     * in the name of the user with specified id
     * <p>
     *     If user does not have the book
     *     the user cannot drop the book
     * </p>
     *
     * @param userId id of the user who drops the book
     * @param book name of the book to be dropped
     * @return <code>true</code> if the book can be taken
     *         <code>false</code> if the book cannot be taken
     * @see Book
     *
     */
    @Override
    @Transactional
    public boolean dropBook(Long userId, String book) {
        logger.debug("LibraryServiceImpl -> takeBook({}, {})", userId, book);
        Integer userHasTheBook = libraryRepository.checkIfUserHasTheBook(userId, book);
        if (userHasTheBook <= 0) {
            return false;
        }
        libraryRepository.dropBook(userId, book);
        return true;
    }

    /**
     * Fetches the list of all the books that
     * user has taken since registration
     *
     * @param token token of the logged in user
     * @return List of the names of all the books that
     * user has taken
     */
    @Override
    public List<String> getBookHistory(String token) {
        Optional<List<String>> historicalRecordsOfUser = libraryRepository.findHistoricalRecordsOfUser(token);
        return historicalRecordsOfUser.orElse(null);
    }
    /**
     * Fetches the list of all the books that
     * user currently has
     *
     * @param token token of the logged in user
     * @return List of the names of all the books that
     * user has
     * <code>null</code> if the ResultSet is null
     */
    @Override
    public List<String> getCurrentBooks(String token) {
        logger.debug("UserServiceImpl -> getCurrentBooks({})", token);
        Optional<List<String>> books = libraryRepository.findCurrentBooksOfUser(token);
        System.out.println(books);
        return books.orElse(null);
    }
}
