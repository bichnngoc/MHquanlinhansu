package com.example.demo5.repository;

import test.generated.tables.pojos.NhanVien;

import java.util.List;

public interface NhanVienRepository {
    void save(NhanVien nhanVien);
    List<NhanVien> findAll();
    NhanVien findById(Long id);
    NhanVien update(NhanVien nhanVien);
    List<NhanVien> findAllByNhanVien();
}
