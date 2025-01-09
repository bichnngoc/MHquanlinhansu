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
        return dslContext.selectFrom(Tables.PHONG_BAN)
                .where(Tables.PHONG_BAN.ID.eq(id))
                .fetchOneInto(PhongBan.class);
    }
}
