package com.example.demo5.data.mapper;

import com.example.demo5.data.request.NhanVienRequest;
import com.example.demo5.data.response.ChucVuResponse;
import com.example.demo5.data.response.NhanVienResponse;
import com.example.demo5.data.response.PhongBanResponse;
import com.example.demo5.data.response.TienLuongResponse;
import org.mapstruct.*;

import test.generated.tables.pojos.NhanVien;

@Mapper(componentModel = "spring")
public abstract class NhanVienMapper {

    // Ánh xạ từ NhanVienRequest sang NhanVien entity
    public abstract NhanVien toEntity(NhanVienRequest request);

    // Ánh xạ cơ bản từ NhanVien sang NhanVienResponse
    @Mapping(target = "phongBan", ignore = true) // Ignore vì sẽ gắn thủ công sau
    @Mapping(target = "chucVu", ignore = true)   // Ignore vì sẽ gắn thủ công sau
    @Mapping(target = "tienLuong", ignore = true)// Ignore vì sẽ gắn thủ công sau
    public abstract NhanVienResponse toNhanVienResponse(NhanVien nhanVien);

    // Phương thức hỗ trợ cập nhật thông tin từ NhanVienRequest vào NhanVien entity
    public abstract void updateNhanVien(@MappingTarget NhanVien nhanVien, NhanVienRequest nhanVienRequest);

    // Sau khi ánh xạ cơ bản, gắn thêm thông tin từ PhongBanResponse, ChucVuResponse, TienLuongResponse
    @AfterMapping
    protected void afterMapping(@MappingTarget NhanVienResponse response,
                                @Context PhongBanResponse phongBanResponse,
                                @Context ChucVuResponse chucVuResponse,
                                @Context TienLuongResponse tienLuongResponse) {
        response.setPhongBan(phongBanResponse);
        response.setChucVu(chucVuResponse);
        response.setTienLuong(tienLuongResponse);
    }

    // Phương thức ánh xạ với tham số bổ sung
    public NhanVienResponse toNhanVienResponse(NhanVien nhanVien,
                                               PhongBanResponse phongBanResponse,
                                               ChucVuResponse chucVuResponse,
                                               TienLuongResponse tienLuongResponse) {
        NhanVienResponse response = toNhanVienResponse(nhanVien); // Gọi ánh xạ cơ bản
        afterMapping(response, phongBanResponse, chucVuResponse, tienLuongResponse); // Gắn thêm thông tin
        return response;
    }
}
