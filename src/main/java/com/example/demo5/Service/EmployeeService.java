package com.example.demo5.Service;

import com.example.demo5.data.request.FilterCondition;
import com.example.demo5.data.request.EmployeeRequest;
import com.example.demo5.data.response.EmployeeResponse;
import com.example.demo5.data.response.PageResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmployeeService {
    Void save(EmployeeRequest nhanVienRequest);
    EmployeeResponse getById(Long id);
    EmployeeResponse update(EmployeeRequest nhanVienRequest, Long id);
    Void delete(Long id);
    PageResponse<EmployeeResponse> getPageEmployee(Pageable pageable);
    PageResponse<EmployeeResponse> searchEmployee(List<FilterCondition> filterConditions, Pageable pageable);
}
