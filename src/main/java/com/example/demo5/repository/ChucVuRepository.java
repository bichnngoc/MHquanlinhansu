package com.example.demo5.repository;
import test.generated.tables.pojos.ChucVu;

import java.util.List;


public interface ChucVuRepository {
    void save(ChucVu chucVu);
    ChucVu findById(Long id);
    List<ChucVu> findAll();
    List<ChucVu> findAllByListChucVuId(List<Long> chucVuIds);
}
