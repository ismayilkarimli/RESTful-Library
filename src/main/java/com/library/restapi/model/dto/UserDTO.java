package com.library.restapi.model.dto;

import lombok.*;
import org.apache.commons.lang3.RandomStringUtils;

import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO implements Serializable {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private LocalDate dob;
    private String token = RandomStringUtils.randomAlphabetic(32);
}
