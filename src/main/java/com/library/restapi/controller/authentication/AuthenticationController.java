package com.library.restapi.controller.authentication;

import com.library.restapi.model.dto.UserDTO;
import org.springframework.http.ResponseEntity;

public interface AuthenticationController {

    ResponseEntity<?> login(String email, String password);

    ResponseEntity<String> register(UserDTO formData);
}
