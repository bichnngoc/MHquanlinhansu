package com.example.demo5.repository.impl;

import com.example.demo5.repository.DepartmentRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import test.generated.tables.pojos.Department;

import java.util.List;

import static test.generated.Tables.DEPARTMENT;


@Repository
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
public class DepartmentRepositoryImpl implements DepartmentRepository {
    DSLContext dslContext;

    @Override
    public void save(Department department) {

    }

    @Override
    public List<Department> findAll() {
        return List.of();
    }

    @Override
    public Department findById(Long id) {
        return dslContext.selectFrom(DEPARTMENT)
                .where(DEPARTMENT.ID.eq(id))
                .fetchOneInto(Department.class);
    }

    @Override
    public List<Department> findAllByListDepartmentId(List<Long> departmentIds) {
        return dslContext.selectFrom(DEPARTMENT)
                .where(DEPARTMENT.ID.in(departmentIds))
                .fetchInto(Department.class);
    }
}
