package com.library.restapi.service.user;

import java.util.List;

public interface UserService {

    boolean takeBook(Long userId, String book);

    boolean dropBook(Long userId, String book);

    List<String> getBookHistory(String token);

    List<String> getCurrentBooks(String token);
}
