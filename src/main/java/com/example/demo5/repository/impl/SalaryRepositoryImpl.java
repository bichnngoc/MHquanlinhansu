package com.example.demo5.repository.impl;

import com.example.demo5.repository.SalaryRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import test.generated.tables.pojos.Salary;

import java.util.List;

import static test.generated.Tables.SALARY;


@Repository
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
public class SalaryRepositoryImpl implements SalaryRepository {
    DSLContext dslContext;

    @Override
    public void save(Salary salary) {

    }

    @Override
    public Salary findById(Long id) {
        return dslContext.selectFrom(SALARY)
                .where(SALARY.ID.eq(id))
                .fetchOneInto(Salary.class);
    }

    @Override
    public List<Salary> findAll() {
        return List.of();
    }

    @Override
    public List<Salary> findAllByListSalaryId(List<Long> salaryIds) {
        return dslContext.selectFrom(SALARY)
                .where(SALARY.ID.in(salaryIds))
                .fetchInto(Salary.class);
    }
}
