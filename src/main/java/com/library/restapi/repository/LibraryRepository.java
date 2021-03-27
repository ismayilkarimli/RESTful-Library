package com.library.restapi.repository;

import com.library.restapi.model.dto.BookResponse;
import com.library.restapi.model.entity.Book;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LibraryRepository extends CrudRepository<Book, Long> {

    @Query(value = "select first_name takenBy, name bookName, is_available isAvailable, isbn, category, year, author from books b left join borrowed br on b.id=br.book_id left join users u on br.user_id=u.id;", nativeQuery = true)
    Optional<List<BookResponse>> findAllBooks();

    @Query(value = "SELECT * FROM books WHERE category=:category", nativeQuery = true)
    Optional<List<Book>> findBooksByCategory(@Param("category") String category);

    @Query(value = "SELECT * FROM books WHERE author=:author", nativeQuery = true)
    Optional<List<Book>> findBooksByAuthor(@Param("author") String author);

    @Query(value = "SELECT * FROM books WHERE name=:book_name", nativeQuery = true)
    Optional<Book> findBookByName(@Param("book_name") String bookName);

    @Query(value = "SELECT * FROM books WHERE author=?1 AND category=?2", nativeQuery = true)
    Optional<List<Book>> findBooksByAuthorAndCategory(String author, String category);

    @Query(value = "UPDATE books SET is_available=false WHERE name=:book ;" +
            "INSERT INTO borrowed(user_id, book_id) VALUES(:userId, (SELECT id FROM books WHERE name=:book));" +
            "INSERT INTO history(user_id, picked_on, book_id) VALUES (:userId, NOW(), (SELECT id FROM books WHERE name = :book))",
            nativeQuery = true)
    @Modifying
    void takeBook(@Param("userId") Long userId, @Param("book") String book);

    @Query(value = "SELECT COUNT(*) FROM borrowed WHERE user_id = :id AND book_id=(SELECT id FROM books WHERE name=:book)", nativeQuery = true)
    Integer checkIfUserHasTheBook(@Param("id") Long userId, @Param("book") String book);

    @Query(value = "DELETE FROM borrowed WHERE user_id=?1 AND book_id=(SELECT id FROM books WHERE name=?2);" +
            "UPDATE books SET is_available=true WHERE id=(SELECT id FROM books WHERE name=?2);" +
            "UPDATE history SET returned_on=NOW() WHERE user_id=?1 AND book_id=(SELECT id FROM books WHERE name=?2);", nativeQuery = true)
    @Modifying
    void dropBook(Long userId, String book);

    @Query(value = "SELECT name FROM borrowed br JOIN books b ON br.book_id=b.id JOIN users u ON br.user_id=u.id WHERE u.token=:token",
            nativeQuery = true)
    Optional<List<String>> findCurrentBooksOfUser(@Param("token") String token);

    @Query(value = "SELECT name FROM history h JOIN users u ON h.user_id=u.id JOIN books b ON h.book_id = b.id WHERE token=?1",
            nativeQuery = true)
    Optional<List<String>> findHistoricalRecordsOfUser(String token);
}
