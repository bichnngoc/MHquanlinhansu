package com.example.demo5.Service;

import com.example.demo5.data.request.NhanVienRequest;
import com.example.demo5.data.response.NhanVienResponse;

import java.util.List;

public interface NhanVienService {
    void save(NhanVienRequest nhanVienRequest);
    NhanVienResponse getById(Long id);
    List<NhanVienResponse> getAllNhanVien();
    List<NhanVienResponse> listNhanVien();
}
