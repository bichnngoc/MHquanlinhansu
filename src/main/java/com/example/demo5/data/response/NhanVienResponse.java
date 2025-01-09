package com.example.demo5.data.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NhanVienResponse {
    Long id;
    String diaChi;
    String email;
    String gioiTinh;
    String hocVan;
    LocalDate ngaySinh;
    LocalDate ngayTuyenDung;
    String soDienThoai;
    String tenNhanVien;
    PhongBanResponse phongBan;
    ChucVuResponse chucVu;
    TienLuongResponse tienLuong;
}
