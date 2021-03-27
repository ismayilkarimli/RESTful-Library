package com.library.restapi.service.authentication.impl;

import com.library.restapi.model.entity.Book;
import com.library.restapi.model.entity.User;
import com.library.restapi.model.dto.UserDTO;
import com.library.restapi.repository.UserRepository;
import com.library.restapi.service.authentication.AuthenticationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service(value = "DatabaseAuthentication")
public class AuthenticationServiceImpl implements AuthenticationService {

    private final Logger logger = LoggerFactory.getLogger(AuthenticationServiceImpl.class);
    private final UserRepository userRepository;

    AuthenticationServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Converts submitted UserDTO object to User object and
     * creates a database record
     *
     * @param userDto UserDTO to be converted to User
     * @return <code>true</code> if no error occurs during registration
     *         <code>false</code> if exception occurs during saving to the database
     * @see UserDTO
     * @see User
     */
    @Override
    public boolean register(UserDTO userDto) {
        logger.debug("AuthenticationServiceImpl -> register()");
        try {
            userDto.setPassword(new BCryptPasswordEncoder().encode(userDto.getPassword()));
            userRepository.save(new User(userDto));
        } catch (Exception e) {
            logger.error("Error => ", e);
            return false;
        }
        return true;
    }

    /**
     * Returns a UserDTO with the specified email and password.
     * Hashes the submitted password using BCrypt and compares the hash
     * to the hash in the database.
     * <p>
     * The method returns a UserDTO if the credentials match. Otherwise returns null.
     * </p>
     * @param email submitted email to login
     * @param password submitted password to login
     * @return user DTO with the specified credentials
     * @see UserDTO
     */
    @Override
    public UserDTO login(String email, String password) {
        logger.debug("AuthenticationServiceImpl -> login({}, {})", email, password);
        Optional<User> optionalUser = userRepository.findUserByEmail(email);
        if (optionalUser.isEmpty()) {
            return null;
        }
        logger.debug("{} exists", email);
        boolean passwordMatches = new BCryptPasswordEncoder().matches(password, optionalUser.get().getPassword());
        if (!passwordMatches) {
            logger.debug("password did not match");
            return null;
        }
        logger.debug("password matched");
        return optionalUser.get().toUserDTO();
    }
}
