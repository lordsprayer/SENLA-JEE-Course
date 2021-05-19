package com.senla.courses.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerDto {
    private Integer id;
    private String name;
    private String surname;
    private String phone;
}
