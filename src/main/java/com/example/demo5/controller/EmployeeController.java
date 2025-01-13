package com.example.demo5.controller;

import com.example.demo5.Service.EmployeeService;
import com.example.demo5.data.request.FilterCondition;
import com.example.demo5.data.request.EmployeeRequest;
import com.example.demo5.data.response.ApiResponse;
import com.example.demo5.data.response.EmployeeResponse;
import com.example.demo5.data.response.PageResponse;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class EmployeeController {
    EmployeeService employeeService;

    @GetMapping("/{id}")
    public ApiResponse<EmployeeResponse> getById(@PathVariable long id) {
        ApiResponse<EmployeeResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(employeeService.getById(id));
        return apiResponse;
    }

    @GetMapping("/page-employee")
    public ApiResponse<PageResponse<EmployeeResponse>> getEmployeePage(Pageable pageable) {
        ApiResponse<PageResponse<EmployeeResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setResult(employeeService.getPageEmployee(pageable));
        return apiResponse;
    }

    @GetMapping("/search")
    public ApiResponse<PageResponse<EmployeeResponse>> searchEmployee(@RequestBody List<FilterCondition> filterConditions, Pageable pageable) {
        ApiResponse<PageResponse<EmployeeResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setResult(employeeService.searchEmployee(filterConditions, pageable));
        return apiResponse;
    }


    @PostMapping("/add")
    public ApiResponse<Void> addEmployee(@RequestBody  @Valid EmployeeRequest employeeRequest) {
        employeeService.save(employeeRequest);
        ApiResponse<Void> apiResponse = new ApiResponse<>();
        apiResponse.setMessage("Employee created successfully");
        return apiResponse;
    }

    @PutMapping("/{id}")
    public ApiResponse<EmployeeResponse> updateEmployee(@PathVariable Long id, @RequestBody EmployeeRequest nhanVienRequest) {
        EmployeeResponse employeeResponse = employeeService.update(nhanVienRequest,id);
        ApiResponse<EmployeeResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(employeeResponse);
        return apiResponse;
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteEmployee(@PathVariable Long id) {
        employeeService.delete(id);
        ApiResponse<Void> apiResponse = new ApiResponse<>();
        apiResponse.setMessage("Employee deleted successfully");
        return apiResponse;
    }
}
