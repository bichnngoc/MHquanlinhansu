package com.example.demo5.data.mapper;

import com.example.demo5.data.response.TienLuongResponse;
import org.mapstruct.Mapper;
import test.generated.tables.pojos.TienLuong;

@Mapper(componentModel = "spring")
public interface TienLuongMapper {
    TienLuongResponse toTienLuongResponse(TienLuong tienLuong);
}
