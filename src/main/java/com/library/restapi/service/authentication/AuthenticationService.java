package com.library.restapi.service.authentication;

import com.library.restapi.model.entity.User;
import com.library.restapi.model.dto.UserDTO;

import java.util.Optional;

public interface AuthenticationService {

    UserDTO login(String email, String password);

    boolean register(UserDTO user);
}
