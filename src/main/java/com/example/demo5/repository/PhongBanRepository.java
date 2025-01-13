package com.example.demo5.repository;
import test.generated.tables.pojos.PhongBan;

import java.util.List;


public interface PhongBanRepository {
    void save(PhongBan phongBan);
    List<PhongBan> findAll();
    PhongBan findById(Long id);
    List<PhongBan> findAllByListPBId(List<Long> phongBanIds);
}
