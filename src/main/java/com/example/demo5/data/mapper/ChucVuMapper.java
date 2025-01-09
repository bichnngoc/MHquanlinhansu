package com.example.demo5.data.mapper;

import com.example.demo5.data.response.ChucVuResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import test.generated.tables.pojos.ChucVu;

@Mapper(componentModel = "spring")
public interface ChucVuMapper {
    ChucVuResponse toChucVuResponse(ChucVu chucVu);
}
