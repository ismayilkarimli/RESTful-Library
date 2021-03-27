package com.library.restapi.model.dto;

public interface BookResponse {

    String getTakenBy();
    String getBookName();
    String getAuthor();
    Integer getYear();
    String getCategory();
    String getISBN();
    Boolean getIsAvailable();

}
