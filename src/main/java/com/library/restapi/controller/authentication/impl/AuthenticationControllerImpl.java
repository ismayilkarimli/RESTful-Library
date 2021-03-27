package com.library.restapi.controller.authentication.impl;

import com.library.restapi.controller.authentication.AuthenticationController;
import com.library.restapi.model.dto.UserDTO;
import com.library.restapi.service.authentication.AuthenticationService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/api/v0.1/auth")
public class AuthenticationControllerImpl implements AuthenticationController {

    private final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    @Qualifier(value = "DatabaseAuthentication")
    private final AuthenticationService authenticationService;

    AuthenticationControllerImpl(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    /**
     * @param email Request header that describes the email to be used for logging in
     * @param password Request header that describes the password to be used for logging in
     * @return <code>ResponseEntity</code> containing the result of the log-in process
     *
     * @see RequestHeader
     * @see ResponseEntity
     */
    @Override
    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> login(
            @RequestHeader String email,
            @RequestHeader String password) {
        logger.debug("Email :: {}", email);
        logger.debug("Password :: {}", password);
        UserDTO userDTO = authenticationService.login(email, password);
        if (userDTO == null) {
            return new ResponseEntity<>("No such user", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(Collections.singletonMap("token", userDTO.getToken()), HttpStatus.OK);
    }

    /**
     * @param formData UserDTO that has been submitted as a <code>RequestBody</code>
     * @return <code>ResponseEntity</code> containing the result of the registration process
     *
     * @see ResponseEntity
     * @see RequestBody
     */
    @Override
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> register(
            @RequestBody UserDTO formData) {
        logger.debug("Form data :: {}", formData);
        if (authenticationService.register(formData)) {
            return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Error occurred during registration", HttpStatus.BAD_REQUEST);
    }
}
