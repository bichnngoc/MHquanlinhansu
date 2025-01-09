package com.example.demo5.data.mapper;

import com.example.demo5.data.response.PhongBanResponse;
import org.mapstruct.Mapper;
import test.generated.tables.pojos.PhongBan;

@Mapper(componentModel = "spring")
public interface PhongBanMapper {
    PhongBanResponse toPhongBanResponse(PhongBan phongBan);
}

