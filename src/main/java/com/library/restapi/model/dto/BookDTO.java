package com.library.restapi.model.dto;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BookDTO implements Serializable {

    private String name;
    private String author;
    private Integer year;
    private String category;
    private String ISBN;
    private Boolean isAvailable;
}
