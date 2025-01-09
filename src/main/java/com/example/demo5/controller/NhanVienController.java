package com.example.demo5.controller;

import com.example.demo5.Service.NhanVienService;
import com.example.demo5.data.response.NhanVienResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/nhanvien")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class NhanVienController {
    NhanVienService nhanVienService;

    @GetMapping("/list")
    public List<NhanVienResponse> getAllNhanVien() {
        return nhanVienService.getAllNhanVien();
    }
    @GetMapping("/list2")
    public List<NhanVienResponse> getAllNhanVien2() {
        return  nhanVienService.listNhanVien();
    }
}
