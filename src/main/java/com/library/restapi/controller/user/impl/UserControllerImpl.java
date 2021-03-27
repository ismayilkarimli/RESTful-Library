package com.library.restapi.controller.user.impl;

import com.library.restapi.controller.user.UserController;
import com.library.restapi.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v0.1/user")
public class UserControllerImpl implements UserController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final UserService userService;

    public UserControllerImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    @RequestMapping(value = "/{id}/take/{book}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> takeBook(@PathVariable Long id, @PathVariable(value = "book") String bookName) {
        logger.debug("UserController -> takeBook({}, {})", id, bookName);
        boolean queryResult = userService.takeBook(id, bookName);
        return queryResult ? new ResponseEntity<>("success", HttpStatus.OK)
                : new ResponseEntity<>("Could not take the book", HttpStatus.BAD_REQUEST);
    }

    @Override
    @RequestMapping(value = "/{id}/drop/{book}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> dropBook(@PathVariable Long id, @PathVariable(value = "book") String bookName) {
        logger.debug("UserController -> dropBook({}, {})", id, bookName);
        boolean queryResult = userService.dropBook(id, bookName);
        return queryResult ? new ResponseEntity<>("success", HttpStatus.OK)
                : new ResponseEntity<>("Could not drop the book", HttpStatus.BAD_REQUEST);
    }

    @Override
    @RequestMapping(value = "/history", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<?> getBookHistory(@RequestHeader String token) {
        logger.debug("UserController -> getHistory({})", token);
        List<String> bookHistory = userService.getBookHistory(token);
        return new ResponseEntity<>(List.of(bookHistory), HttpStatus.OK);
    }

    @Override
    @GetMapping("/books")
    @ResponseBody
    public ResponseEntity<?> getCurrentBooks(@RequestHeader String token) {
        logger.debug("UserController -> currentBooks({})", token);
        List<String> currentBooks = userService.getCurrentBooks(token);
        return new ResponseEntity<>(List.of(currentBooks), HttpStatus.OK);
    }
}
