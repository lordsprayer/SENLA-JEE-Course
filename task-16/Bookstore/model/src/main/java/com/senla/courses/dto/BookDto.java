package com.senla.courses.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class BookDto {
    private Integer id;
    private String title;
    private String author;
    private Integer publicationYear;
    private Double cost;
    private LocalDate receiptDate;
    private String description;
}
