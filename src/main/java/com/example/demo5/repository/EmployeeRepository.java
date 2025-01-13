package com.example.demo5.repository;

import com.example.demo5.data.request.FilterCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import test.generated.tables.pojos.Employee;

import java.util.List;

public interface EmployeeRepository {
    Void save(Employee employee);
    List<Employee> findAll();
    Employee findById(Long id);
    Void update(Employee employee);
    Void deleteById(Long id);
    Page<Employee> getEmployee(Pageable pageable);
    Page<Employee> searchEmployee(List<FilterCondition> conditions, Pageable pageable);
    long countEmployee();

}
