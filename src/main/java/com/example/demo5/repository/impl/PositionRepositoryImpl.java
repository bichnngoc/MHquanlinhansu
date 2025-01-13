package com.example.demo5.repository.impl;

import com.example.demo5.repository.PositionRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import test.generated.tables.pojos.Position;

import java.util.List;

import static test.generated.Tables.POSITION;

@Repository
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
public class PositionRepositoryImpl implements PositionRepository {
    DSLContext dslContext;

    @Override
    public void save(Position position) {

    }

    @Override
    public Position findById(Long id) {
        return dslContext.selectFrom(POSITION)
                .where(POSITION.ID.eq(id))
                .fetchOneInto(Position.class);
    }

    @Override
    public List<Position> findAll() {
        return List.of();
    }

    @Override
    public List<Position> findAllByListPositionId(List<Long> positionIds) {
        return dslContext.selectFrom(POSITION)
                .where(POSITION.ID.in(positionIds))
                .fetchInto(Position.class);
    }
}
