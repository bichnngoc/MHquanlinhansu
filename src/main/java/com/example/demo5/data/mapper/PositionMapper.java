package com.example.demo5.data.mapper;

import com.example.demo5.data.response.PositionResponse;
import org.mapstruct.Mapper;
import test.generated.tables.pojos.Position;

@Mapper(componentModel = "spring")
public interface PositionMapper {
    PositionResponse toPositionResponse(Position position);
}
