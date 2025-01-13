package com.example.demo5.repository;
import test.generated.tables.pojos.Salary;

import java.util.List;

public interface SalaryRepository {
    void save(Salary tienLuong);
    Salary findById(Long id);
    List<Salary> findAll();
    List<Salary> findAllByListSalaryId(List<Long> salaryIds);
}
