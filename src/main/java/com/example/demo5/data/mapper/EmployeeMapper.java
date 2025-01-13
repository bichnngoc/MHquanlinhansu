package com.example.demo5.data.mapper;

import com.example.demo5.data.request.EmployeeRequest;
import com.example.demo5.data.response.PositionResponse;
import com.example.demo5.data.response.EmployeeResponse;
import com.example.demo5.data.response.DepartmentResponse;
import com.example.demo5.data.response.SalaryResponse;
import org.mapstruct.*;

import test.generated.tables.pojos.Employee;

@Mapper(componentModel = "spring")
public abstract class EmployeeMapper {

    // Ánh xạ từ EmployeeRequest sang Employee entity
    public abstract Employee toEntity(EmployeeRequest request);

    // Ánh xạ cơ bản từ Employee sang EmployeeResponse
    @Mapping(target = "department", ignore = true) // Ignore vì sẽ gắn thủ công sau
    @Mapping(target = "position", ignore = true)   // Ignore vì sẽ gắn thủ công sau
    @Mapping(target = "salary", ignore = true)// Ignore vì sẽ gắn thủ công sau
    public abstract EmployeeResponse toEmployeeResponse(Employee employee);

    // Phương thức hỗ trợ cập nhật thông tin từ EmployeeRequest vào Employee entity
    public abstract void updateEmployee(@MappingTarget Employee employee, EmployeeRequest employeeRequest);

    // Sau khi ánh xạ cơ bản, gắn thêm thông tin từ DepartmentResponse, PositionResponse, SalaryResponse
    @AfterMapping
    protected void afterMapping(@MappingTarget EmployeeResponse response,
                                @Context DepartmentResponse departmentResponse,
                                @Context PositionResponse positionResponse,
                                @Context SalaryResponse salaryResponse) {
        response.setDepartment(departmentResponse);
        response.setPosition(positionResponse);
        response.setSalary(salaryResponse);
    }

    // Phương thức ánh xạ với tham số bổ sung
    public EmployeeResponse toEmployeeResponse(Employee employee,
                                               DepartmentResponse departmentResponse,
                                               PositionResponse positionResponse,
                                               SalaryResponse salaryResponse) {
        EmployeeResponse response = toEmployeeResponse(employee); // Gọi ánh xạ cơ bản
        afterMapping(response, departmentResponse, positionResponse, salaryResponse); // Gắn thêm thông tin
        return response;
    }
}
