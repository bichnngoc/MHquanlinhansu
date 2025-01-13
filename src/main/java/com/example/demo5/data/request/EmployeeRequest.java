package com.example.demo5.data.request;

import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmployeeRequest {
    Long id;

    @NotBlank(message = "Address must not be empty")
    String address;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email must not be empty")
    String email;

    @NotBlank(message = "Gender must not be empty")
    String gender;

    @NotBlank(message = "Education level must not be empty")
    String education;

    @NotNull(message = "Date of birth must not be null")
    @Past(message = "Date of birth must be in the past")
    LocalDate birthDate;

    @NotNull(message = "Hire date must not be null")
    @PastOrPresent(message = "Hire date must be today or in the past")
    LocalDate recruitmentDate;

    @NotBlank(message = "Phone number must not be empty")
    @Pattern(regexp = "^(\\+84|0)\\d{9,10}$", message = "Invalid phone number")
    String phoneNumber;

    @NotBlank(message = "Employee name must not be empty")
    String employeeName;

    @NotNull(message = "Position ID must not be null")
    @Positive(message = "Position ID must be a positive number")
    Long idPosition;

    @NotNull(message = "Department ID must not be null")
    @Positive(message = "Department ID must be a positive number")
    Long idDepartment;

    @NotNull(message = "Salary ID must not be null")
    @Positive(message = "Salary ID must be a positive number")
    Long idSalary;
}
