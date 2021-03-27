package com.library.restapi.controller.user;

import org.springframework.http.ResponseEntity;

public interface UserController {

    ResponseEntity<?> takeBook(Long id, String book);

    ResponseEntity<?> dropBook(Long id, String book);

    ResponseEntity<?> getBookHistory(String token);

    ResponseEntity<?> getCurrentBooks(String token);
}
