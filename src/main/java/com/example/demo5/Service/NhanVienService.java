package com.example.demo5.Service;

import com.example.demo5.data.request.FilterCondition;
import com.example.demo5.data.request.NhanVienRequest;
import com.example.demo5.data.response.NhanVienResponse;
import com.example.demo5.data.response.PageResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface NhanVienService {
    Void save(NhanVienRequest nhanVienRequest);
    NhanVienResponse getById(Long id);
    NhanVienResponse update(NhanVienRequest nhanVienRequest, Long id);
    Void delete(Long id);
    PageResponse<NhanVienResponse> getPageNhanVien(Pageable pageable);
    PageResponse<NhanVienResponse> searchNhanVien(List<FilterCondition> filterConditions, Pageable pageable);
}
