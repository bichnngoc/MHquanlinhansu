package com.example.demo5.repository.impl;

import com.example.demo5.repository.ChucVuRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import test.generated.Tables;
import test.generated.tables.pojos.ChucVu;

import java.util.List;

import static test.generated.tables.ChucVu.CHUC_VU;

@Repository
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
public class ChucVuRepositoryImpl implements ChucVuRepository {
    DSLContext dslContext;

    @Override
    public void save(ChucVu chucVu) {

    }

    @Override
    public ChucVu findById(Long id) {
        return dslContext.selectFrom(Tables.CHUC_VU)
                .where(Tables.CHUC_VU.ID.eq(id))
                .fetchOneInto(ChucVu.class);
    }

    @Override
    public List<ChucVu> findAll() {
        return List.of();
    }

    @Override
    public List<ChucVu> findAllByListChucVuId(List<Long> chucVuIds) {
        return dslContext.selectFrom(CHUC_VU)
                .where(CHUC_VU.ID.in(chucVuIds))
                .fetchInto(ChucVu.class);
    }
}
