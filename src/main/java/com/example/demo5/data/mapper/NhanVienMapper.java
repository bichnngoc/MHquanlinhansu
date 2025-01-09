package com.example.demo5.data.mapper;

import com.example.demo5.data.request.NhanVienRequest;
import com.example.demo5.data.response.NhanVienResponse;
import org.mapstruct.*;
import test.generated.tables.pojos.NhanVien;
@Mapper(componentModel = "spring")
public interface NhanVienMapper {

    @Mapping(target = "id", ignore = true)
    NhanVien toNhanVienRequest(NhanVienRequest nhanVienRequest);
    NhanVienResponse toNhanVienResponse(NhanVien nhanVien);

}