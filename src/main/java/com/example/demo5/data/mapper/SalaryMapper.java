package com.example.demo5.data.mapper;

import com.example.demo5.data.response.SalaryResponse;
import org.mapstruct.Mapper;
import test.generated.tables.pojos.Salary;

@Mapper(componentModel = "spring")
public interface SalaryMapper {
    SalaryResponse toSalaryResponse(Salary salary);
}
