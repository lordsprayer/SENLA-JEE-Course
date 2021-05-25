package com.senla.courses.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {
    private Integer id;
    private String title;
    private String author;
    private Integer publicationYear;
    private Boolean availability;
    private Double cost;
    private LocalDate receiptDate;
    private String description;
}
