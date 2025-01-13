package com.example.demo5.repository;
import test.generated.tables.pojos.Department;
import java.util.List;


public interface DepartmentRepository {
    void save(Department department);
    List<Department> findAll();
    Department findById(Long id);
    List<Department> findAllByListDepartmentId(List<Long> departmentIds);
}
