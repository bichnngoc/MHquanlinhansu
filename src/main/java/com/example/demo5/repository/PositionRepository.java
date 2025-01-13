package com.example.demo5.repository;
import test.generated.tables.pojos.Position;

import java.util.List;


public interface PositionRepository {
    void save(Position position);
    Position findById(Long id);
    List<Position> findAll();
    List<Position> findAllByListPositionId(List<Long> positionIds);
}
