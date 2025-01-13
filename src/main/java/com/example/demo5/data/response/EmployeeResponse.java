package com.example.demo5.data.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeeResponse {
    Long id;
    String address;
    String email;
    String gender;
    String education;
    LocalDate birthDate;
    LocalDate recruitmentDate;
    String phoneNumber;
    String employeeName;
    PositionResponse position;
    DepartmentResponse department;
    SalaryResponse salary;
}
