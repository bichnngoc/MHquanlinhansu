package com.example.demo5.repository.impl;

import com.example.demo5.repository.PhongBanRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import test.generated.Tables;
import test.generated.tables.pojos.PhongBan;

import java.util.List;

import static test.generated.Tables.PHONG_BAN;

@Repository
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
public class PhongBanRepositoryImpl implements PhongBanRepository {
    DSLContext dslContext;

    @Override
    public void save(PhongBan phongBan) {

    }

    @Override
    public List<PhongBan> findAll() {
        return List.of();
    }

    @Override
    public PhongBan findById(Long id) {
        return dslContext.selectFrom(PHONG_BAN)
                .where(PHONG_BAN.ID.eq(id))
                .fetchOneInto(PhongBan.class);
    }

    @Override
    public List<PhongBan> findAllByListPBId(List<Long> phongBanIds) {
        return dslContext.selectFrom(PHONG_BAN)
                .where(PHONG_BAN.ID.in(phongBanIds))
                .fetchInto(PhongBan.class);
    }
}
