package com.example.demo5.repository;
import test.generated.tables.pojos.TienLuong;

import java.util.List;

public interface TienLuongRepository {
    void save(TienLuong tienLuong);
    TienLuong findById(Long id);
    List<TienLuong> findAll();
}
