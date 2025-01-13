package com.example.demo5.repository;

import com.example.demo5.data.request.FilterCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import test.generated.tables.pojos.NhanVien;

import java.util.List;
import java.util.logging.Filter;

public interface NhanVienRepository {
    Void save(NhanVien nhanVien);
    List<NhanVien> findAll();
    NhanVien findById(Long id);
    Void update(NhanVien nhanVien);
    Void deleteById(Long id);
    Page<NhanVien> getNhanVien(Pageable pageable);
    Page<NhanVien> searchNhanVien(List<FilterCondition> conditions, Pageable pageable);
    long countNhanVien();

}
