package com.example.demo5.repository.impl;

import com.example.demo5.repository.TienLuongRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import test.generated.Tables;
import test.generated.tables.pojos.TienLuong;

import java.util.List;

import static test.generated.Tables.TIEN_LUONG;

@Repository
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
public class TienLuongRepositoryImpl implements TienLuongRepository {
    DSLContext dslContext;

    @Override
    public void save(TienLuong tienLuong) {

    }

    @Override
    public TienLuong findById(Long id) {
        return dslContext.selectFrom(TIEN_LUONG)
                .where(TIEN_LUONG.ID.eq(id))
                .fetchOneInto(TienLuong.class);
    }

    @Override
    public List<TienLuong> findAll() {
        return List.of();
    }

    @Override
    public List<TienLuong> findAllByListTLId(List<Long> tienLuongIds) {
        return dslContext.selectFrom(TIEN_LUONG)
                .where(TIEN_LUONG.ID.in(tienLuongIds))
                .fetchInto(TienLuong.class);
    }
}
