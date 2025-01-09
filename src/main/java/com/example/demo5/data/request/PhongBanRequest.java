package com.example.demo5.data.request;

import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PhongBanRequest {
    @NotEmpty(message = "Department name cannot be empty.")
    @Size(min = 1, max = 255, message = "Department name must be between 1 and 255 characters.")
    String tenPhongBan;
}
