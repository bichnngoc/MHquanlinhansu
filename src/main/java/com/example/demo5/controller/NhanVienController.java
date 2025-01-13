package com.example.demo5.controller;

import com.example.demo5.Service.NhanVienService;
import com.example.demo5.data.request.FilterCondition;
import com.example.demo5.data.request.NhanVienRequest;
import com.example.demo5.data.response.ApiResponse;
import com.example.demo5.data.response.NhanVienResponse;
import com.example.demo5.data.response.PageResponse;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/nhanvien")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class NhanVienController {
    NhanVienService nhanVienService;

    @GetMapping("/{id}")
    public ApiResponse<NhanVienResponse> getById(@PathVariable long id) {
        ApiResponse<NhanVienResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(nhanVienService.getById(id));
        return apiResponse;
    }

    @GetMapping("/page-nhanvien")
    public ApiResponse<PageResponse<NhanVienResponse>> getNhanVienPage(Pageable pageable) {
        ApiResponse<PageResponse<NhanVienResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setResult(nhanVienService.getPageNhanVien(pageable));
        return apiResponse;
    }

    @GetMapping("/search")
    public ApiResponse<PageResponse<NhanVienResponse>> searchNhanVien(@RequestBody List<FilterCondition> filterConditions, Pageable pageable) {
        ApiResponse<PageResponse<NhanVienResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setResult(nhanVienService.searchNhanVien(filterConditions, pageable));
        return apiResponse;
    }


    @PostMapping("/add")
    public ApiResponse<Void> addNhanVien(@RequestBody  @Valid NhanVienRequest nhanVienRequest) {
        nhanVienService.save(nhanVienRequest);
        ApiResponse<Void> apiResponse = new ApiResponse<>();
        apiResponse.setMessage("Employee created successfully");
        return apiResponse;
    }

    @PutMapping("/{id}")
    public ApiResponse<NhanVienResponse> updateNhanVien(@PathVariable Long id, @RequestBody NhanVienRequest nhanVienRequest) {
        NhanVienResponse nhanVienResponse = nhanVienService.update(nhanVienRequest,id);
        ApiResponse<NhanVienResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(nhanVienResponse);
        return apiResponse;
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteNhanVien(@PathVariable Long id) {
        nhanVienService.delete(id);
        ApiResponse<Void> apiResponse = new ApiResponse<>();
        apiResponse.setMessage("Employee deleted successfully");
        return apiResponse;
    }
}
