package com.library.restapi.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.library.restapi.model.dto.BookDTO;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "books")
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Book name cannot be blank")
    @NotNull
    private String name;

    private String author;

    @NotNull
    private Integer year;

    @NotBlank(message = "Category cannot be blank")
    @Size(min = 2, message = "Category name cannot be less than 2 in length")
    private String category;

    private String ISBN;

    @NotNull
    private Boolean isAvailable;

    public BookDTO toBookDTO() {
        return new BookDTO(this.name, this.author, this.year, this.category,
                this.ISBN, this.isAvailable);
    }
}
