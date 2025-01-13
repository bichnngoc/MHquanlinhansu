package com.example.demo5.Service.impl;

import com.example.demo5.Service.EmployeeService;
import com.example.demo5.data.mapper.PositionMapper;
import com.example.demo5.data.mapper.EmployeeMapper;
import com.example.demo5.data.mapper.DepartmentMapper;
import com.example.demo5.data.mapper.SalaryMapper;
import com.example.demo5.data.request.FilterCondition;
import com.example.demo5.data.request.EmployeeRequest;
import com.example.demo5.data.response.*;
import com.example.demo5.repository.PositionRepository;
import com.example.demo5.repository.EmployeeRepository;
import com.example.demo5.repository.DepartmentRepository;
import com.example.demo5.repository.SalaryRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import test.generated.tables.pojos.Department;
import test.generated.tables.pojos.Employee;
import test.generated.tables.pojos.Position;
import test.generated.tables.pojos.Salary;


import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@SuppressWarnings("ALL")
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    DepartmentMapper departmentMapper;
    PositionMapper positionMapper;
    SalaryMapper salaryMapper;
    EmployeeMapper employeeMapper;
    EmployeeRepository employeeRepository;
    SalaryRepository salaryRepository;
    PositionRepository positionRepository;
    DepartmentRepository departmentRepository;

    @Override
    public Void save(EmployeeRequest employeeRequest) {
        Department department = departmentRepository.findById(employeeRequest.getIdDepartment());
        Position position = positionRepository.findById(employeeRequest.getIdPosition());
        Salary salary = salaryRepository.findById(employeeRequest.getIdSalary());
        Employee employee = employeeMapper.toEntity(employeeRequest);
        employee.setIdPosition(position.getId());
        employee.setIdSalary(salary.getId());
        employee.setIdDepartment(department.getId());
        employeeRepository.save(employee);
        return null;
    }
    public EmployeeResponse toResponse(Employee employee, DepartmentResponse departmentResponse, PositionResponse positionResponse, SalaryResponse salaryResponse) {
        // Chuyển đối tượng NhanVien thành NhanVienResponse


        return employeeMapper.toEmployeeResponse(employee, departmentResponse, positionResponse, salaryResponse);
    }
    private PageResponse<EmployeeResponse> toPageResponse(Pageable pageable, List<EmployeeResponse> employeeResponses, Page<Employee> page) {
        return PageResponse.<EmployeeResponse>builder()
                .content(employeeResponses)
                .pageNo(pageable.getPageNumber())
                .pageSize(pageable.getPageSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .build();

    }

    @Override
    public EmployeeResponse getById(Long id) {
        Employee employee = employeeRepository.findById(id);

       Department department = departmentRepository.findById(employee.getIdDepartment());
       DepartmentResponse departmentResponse = departmentMapper.toDepartmentResponse(department);

       Position position= positionRepository.findById(employee.getIdPosition());
       PositionResponse positionResponse = positionMapper.toPositionResponse(position);

       Salary salary = salaryRepository.findById(employee.getIdSalary());
       SalaryResponse salaryResponse = salaryMapper.toSalaryResponse(salary);

        // Gọi phương thức toResponse để trả về NhanVienResponse
        return toResponse(employee, departmentResponse, positionResponse, salaryResponse);
    }


    @Override
    public EmployeeResponse update(EmployeeRequest employeeRequest, Long id) {
        Employee employee = employeeRepository.findById(id);
        employeeMapper.updateEmployee(employee,employeeRequest);
        Department department = departmentRepository.findById(employeeRequest.getIdDepartment());
        Position position= positionRepository.findById(employeeRequest.getIdPosition());
        Salary salary = salaryRepository.findById(employeeRequest.getIdSalary());

        DepartmentResponse departmentResponse = departmentMapper.toDepartmentResponse(department);
        PositionResponse positionResponse= positionMapper.toPositionResponse(position);
        SalaryResponse salaryResponse = salaryMapper.toSalaryResponse(salary);

        employeeRepository.update(employee);
        return employeeMapper.toEmployeeResponse(employee, departmentResponse, positionResponse, salaryResponse);
    }


    @Override
    public Void delete(Long id) {
        Employee employee = employeeRepository.findById(id);
        employeeRepository.deleteById(id);
        return null;
    }

    @Override
    public PageResponse<EmployeeResponse> getPageEmployee(Pageable pageable) {
        Page<Employee> page = employeeRepository.getEmployee(pageable);

        List<Employee> employees = page.getContent();
        List<Long> idDepartments = employees.stream().map(Employee::getIdDepartment).toList();
        Map<Long, DepartmentResponse> departmentResponseMap= departmentRepository.findAllByListDepartmentId(idDepartments).stream()
                .collect(Collectors.toMap(Department::getId, departmentMapper::toDepartmentResponse));

        List<Long> idPositions=employees.stream().map(Employee::getIdPosition).toList();
        Map<Long, PositionResponse> positionResponseMap= positionRepository.findAllByListPositionId(idPositions).stream()
                .collect(Collectors.toMap(Position::getId, positionMapper::toPositionResponse));

        List<Long> idSalarys = employees.stream().map(Employee::getIdSalary).toList();
        Map<Long, SalaryResponse> salaryResponseMap=salaryRepository.findAllByListSalaryId(idSalarys).stream()
                .collect(Collectors.toMap(Salary::getId, salaryMapper::toSalaryResponse));

        List<EmployeeResponse> employeeResponses = employees.stream().map(employee -> {
            DepartmentResponse departmentResponse = departmentResponseMap.get(employee.getIdDepartment());
            PositionResponse positionResponse = positionResponseMap.get(employee.getIdPosition());
            SalaryResponse salaryResponse= salaryResponseMap.get(employee.getIdSalary());

            return toResponse(employee, departmentResponse, positionResponse, salaryResponse);

        }).toList();
        return toPageResponse(pageable,employeeResponses,page);
    }

    @Override
    public PageResponse<EmployeeResponse> searchEmployee(List<FilterCondition> filterConditions, Pageable pageable) {
        // Truy vấn danh sách nhân viên theo các điều kiện lọc và phân trang
        Page<Employee> employeesPage = employeeRepository.searchEmployee(filterConditions, pageable);
        List<Employee> employees = employeesPage.getContent();

        // Lấy danh sách id phòng ban từ các nhân viên
        List<Long> idDepartments = employees.stream().map(Employee::getIdDepartment).toList();
        // Tạo Map ánh xạ id phòng ban sang PhongBanResponse
        Map<Long, DepartmentResponse> departmentResponseMap = departmentRepository.findAllByListDepartmentId(idDepartments).stream()
                .collect(Collectors.toMap(Department::getId, departmentMapper::toDepartmentResponse));

        // Lấy danh sách id chức vụ từ các nhân viên
        List<Long> idPositions = employees.stream().map(Employee::getIdPosition).toList();
        // Tạo Map ánh xạ id chức vụ sang ChucVuResponse
        Map<Long, PositionResponse> positionResponseMap = positionRepository.findAllByListPositionId(idPositions).stream()
                .collect(Collectors.toMap(Position::getId, positionMapper::toPositionResponse));

        // Lấy danh sách id tiền lương từ các nhân viên
        List<Long> idSalarys = employees.stream().map(Employee::getIdSalary).toList();
        // Tạo Map ánh xạ id tiền lương sang TienLuongResponse
        Map<Long, SalaryResponse> salaryResponseMap= salaryRepository.findAllByListSalaryId(idSalarys).stream()
                .collect(Collectors.toMap(Salary::getId, salaryMapper::toSalaryResponse));

        // Duyệt qua danh sách nhân viên và tạo danh sách NhanVienResponse
        List<EmployeeResponse> employeeResponses = employees.stream().map(employee -> {
            // Lấy thông tin phòng ban, chức vụ, tiền lương từ các Map
            DepartmentResponse departmentResponse = departmentResponseMap.get(employee.getIdDepartment());
            PositionResponse positionResponse= positionResponseMap.get(employee.getIdDepartment());
            SalaryResponse salaryResponse = salaryResponseMap.get(employee.getIdDepartment());

            // Tạo NhanVienResponse từ các thông tin đã có
            return toResponse(employee, departmentResponse, positionResponse, salaryResponse);
        }).toList();

        // Trả về kết quả phân trang chứa danh sách NhanVienResponse và thông tin phân trang
        return toPageResponse(pageable, employeeResponses, employeesPage);
    }


}
