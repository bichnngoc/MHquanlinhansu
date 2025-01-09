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
public class NhanVienRequest {
    @NotBlank(message = "Address must not be empty")
    String diaChi;
    @Email(message = "Invalid email format")
    @NotBlank(message = "Email must not be empty")
    String email;
    @NotBlank(message = "Gender must not be empty")
    String gioiTinh;
    @NotBlank(message = "Education level must not be empty")
    String hocVan;
    @NotNull(message = "Date of birth must not be null")
    @Past(message = "Date of birth must be in the past")
    LocalDate ngaySinh;
    @NotNull(message = "Hire date must not be null")
    @PastOrPresent(message = "Hire date must be today or in the past")
    LocalDate ngayTuyenDung;
    @NotBlank(message = "Phone number must not be empty")
    @Pattern(regexp = "^(\\+84|0)\\d{9,10}$", message = "Invalid phone number")
    String soDienThoai;
    @NotBlank(message = "Employee name must not be empty")
    String tenNhanVien;
    @NotNull(message = "Position ID must not be null")
    @Positive(message = "Position ID must be a positive number")
    Long idChucVu;
    @NotNull(message = "Department ID must not be null")
    @Positive(message = "Department ID must be a positive number")
    Long idPhongBan;
    @NotNull(message = "Salary ID must not be null")
    @Positive(message = "Salary ID must be a positive number")
    Long idTienLuong;
}
