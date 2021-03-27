package com.library.restapi.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import com.library.restapi.model.dto.UserDTO;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "First name cannot be blank")
    @Size(min = 2, max = 64, message = "First name cannot be less than 2 in length (max 64)")
    private String firstName;

    @NotBlank(message = "Last name cannot be blank")
    @Size(min = 2, max = 64, message = "First name cannot be less than 2 in length (max 64)")
    private String lastName;

    @NotBlank(message = "Email cannot be blank")
    @Size(min = 2, max = 64, message = "First name cannot be less than 2 in length (max 64)")
    private String email;

    @NotBlank(message = "Password cannot be blank")
    @Size(min = 2, max = 64, message = "First name cannot be less than 2 in length (max 64)")
    private String password;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate dob;

    private String token;

    public User(UserDTO userDto) {
        this.firstName = userDto.getFirstName();
        this.lastName = userDto.getLastName();
        this.email = userDto.getEmail();
        this.password = userDto.getPassword();
        this.dob = userDto.getDob();
        this.token = userDto.getToken();
    }

    public UserDTO toUserDTO() {
        return new UserDTO(this.firstName, this.lastName, this.email, this.password, this.dob, this.token);
    }
}
