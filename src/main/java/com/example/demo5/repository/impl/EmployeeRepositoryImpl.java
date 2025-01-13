package com.example.demo5.repository.impl;

import com.example.demo5.data.request.FilterCondition;
import com.example.demo5.repository.EmployeeRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.impl.DSL;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import test.generated.tables.pojos.Employee;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static test.generated.Tables.EMPLOYEE;


@Repository
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
public class EmployeeRepositoryImpl implements EmployeeRepository {
    DSLContext dslContext;

    @Override
    public Void save(Employee employee) {
        dslContext.insertInto(EMPLOYEE)
                .set(EMPLOYEE.EMPLOYEE_NAME,employee.getEmployeeName())
                .set(EMPLOYEE.GENDER, employee.getGender())
                .set(EMPLOYEE.ADDRESS, employee.getAddress())
                .set(EMPLOYEE.EMAIL, employee.getEmail())
                .set(EMPLOYEE.PHONE_NUMBER, employee.getPhoneNumber())
                .set(EMPLOYEE.EDUCATION, employee.getEducation())
                .set(EMPLOYEE.RECRUITMENT_DATE, employee.getRecruitmentDate())
                .set(EMPLOYEE.BIRTH_DATE, employee.getBirthDate())
                .set(EMPLOYEE.ID_DEPARTMENT, employee.getIdDepartment())
                .set(EMPLOYEE.ID_POSITION, employee.getIdPosition())
                .set(EMPLOYEE.ID_SALARY, employee.getIdSalary())
                .execute();
        return null;
    }

    @Override
    public List<Employee> findAll() {
        return dslContext.selectFrom(EMPLOYEE)
                .fetchInto(Employee.class);
    }

    @Override
    public Employee findById(Long id) {
        return dslContext.selectFrom(EMPLOYEE)
                .where(EMPLOYEE.ID.eq(id))
                .fetchOneInto(Employee.class);
    }

    @Override
    public Void deleteById(Long id) {
        dslContext.deleteFrom(EMPLOYEE)
                .where(EMPLOYEE.ID.eq(id))
                .execute();
        return null;
    }

    @Override
    public Void update(Employee employee) {
            dslContext.update(EMPLOYEE)
                    .set(EMPLOYEE.EMPLOYEE_NAME,employee.getEmployeeName())
                    .set(EMPLOYEE.GENDER, employee.getGender())
                    .set(EMPLOYEE.ADDRESS, employee.getAddress())
                    .set(EMPLOYEE.EMAIL, employee.getEmail())
                    .set(EMPLOYEE.PHONE_NUMBER, employee.getPhoneNumber())
                    .set(EMPLOYEE.EDUCATION, employee.getEducation())
                    .set(EMPLOYEE.RECRUITMENT_DATE, employee.getRecruitmentDate())
                    .set(EMPLOYEE.BIRTH_DATE, employee.getBirthDate())
                    .set(EMPLOYEE.ID_DEPARTMENT, employee.getIdDepartment())
                    .set(EMPLOYEE.ID_POSITION, employee.getIdPosition())
                    .set(EMPLOYEE.ID_SALARY, employee.getIdSalary())
                .where(EMPLOYEE.ID.eq(employee.getId()))
                .execute();
        return null;
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Employee> getEmployee(Pageable pageable) {
        List<Employee> nhanVienList = dslContext.selectFrom(EMPLOYEE)
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .fetchInto(Employee.class);
        long total = countEmployee();
        return new PageImpl<>(nhanVienList,pageable,total); //dem tổng số nhân viên, so trang,

    }

    @Override
    public long countEmployee() {
        return dslContext.fetchCount(dslContext.select().from(EMPLOYEE));
    }
    public Page<Employee> searchEmployee(List<FilterCondition> conditions, Pageable pageable) {
        Condition condition = buildCondition(conditions);
        List<Employee> employees = dslContext.selectFrom(EMPLOYEE)
                .where(condition)
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .fetchInto(Employee.class);

        Long totalCount = dslContext.selectCount().from(EMPLOYEE).where(condition)
                .fetchOptional()
                .map(result -> result.get(0, Long.class))
                .orElse(0L);

        return new PageImpl<>(employees, pageable, totalCount);
    }

    private Condition buildCondition(List<FilterCondition> filters) {
        if (filters == null || filters.isEmpty()) {
            return DSL.noCondition();
        }

        Condition condition = createCondition(filters.get(0));
        for (int i = 1; i < filters.size(); i++) {  // Start from index 1
            condition = condition.and(createCondition(filters.get(i)));  // Create new condition
        }
        return condition;
    }

    private Condition createCondition(FilterCondition filter) {
        Field<Object> field = EMPLOYEE.field(filter.getField(), Object.class);
        if (field == null) {
            throw new IllegalArgumentException("invalid field key:" + filter.getField());
        }

        Object castedValue = castToRequireType(filter.getField(), filter.getValue());

        // Change to use getOperation() instead of getOperator()
        return switch (filter.getOperation()) {  // Changed from getOperator() to getOperation()
            case ":" -> field.eq(castedValue);
            case "<" -> field.lt(castedValue);
            case ">" -> field.gt(castedValue);
            case ">=" -> field.ge(castedValue);
            case "<=" -> field.le(castedValue);
            default -> throw new IllegalArgumentException("invalid operator:" + filter.getOperation());
        };
    }

    private Object castToRequireType(String key, String value) {
        if (value == null) return null;
        return switch (key) {
            case "birthDate", "recruitmentDate" -> LocalDate.parse(value, DateTimeFormatter.ISO_DATE);
            case "id", "idDepartment", "idPosition", "idSalary" -> Long.parseLong(value);
            default -> value;
        };
    }

}
