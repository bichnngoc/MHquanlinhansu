package com.example.demo5.data.mapper;

import com.example.demo5.data.response.DepartmentResponse;
import org.mapstruct.Mapper;
import test.generated.tables.pojos.Department;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {
    DepartmentResponse toDepartmentResponse(Department department);
}

