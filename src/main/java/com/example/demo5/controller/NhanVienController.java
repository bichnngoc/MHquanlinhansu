package com.example.demo5.controller;

import com.example.demo5.Service.NhanVienService;
import com.example.demo5.data.request.FilterCondition;
import com.example.demo5.data.request.NhanVienRequest;
import com.example.demo5.data.response.NhanVienResponse;
import com.example.demo5.data.response.PageResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import org.springframework.data.domain.Page;
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
    public ResponseEntity<NhanVienResponse> getById(@PathVariable long id) {
        return ResponseEntity.ok(nhanVienService.getById(id));
    }

    @GetMapping("/page-nhanvien")
    public ResponseEntity<PageResponse<NhanVienResponse>> getNhanVienPage(Pageable pageable) {
        return ResponseEntity.ok(nhanVienService.getPageNhanVien(pageable));
    }

    @GetMapping("/search")
    public ResponseEntity<PageResponse<NhanVienResponse>> searchNhanVien(@RequestBody List<FilterCondition> filterConditions, Pageable pageable) {
        return ResponseEntity.ok(nhanVienService.searchNhanVien(filterConditions, pageable));
    }


    @PostMapping("/add")
    public ResponseEntity<Void> addNhanVien(@RequestBody NhanVienRequest nhanVienRequest) {
        nhanVienService.save(nhanVienRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<NhanVienResponse> updateNhanVien(@PathVariable Long id, @RequestBody NhanVienRequest nhanVienRequest) {
        NhanVienResponse nhanVienResponse = nhanVienService.update(nhanVienRequest,id);
        return ResponseEntity.ok(nhanVienResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNhanVien(@PathVariable Long id) {
        nhanVienService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
