package com.example.demo5.Service;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import test.generated.Tables;
import test.generated.tables.pojos.PhongBan;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PhongBanService {
    @Autowired
    private DSLContext dslContext;

    public void insertPhongBan(PhongBan phongBan) {
        dslContext.insertInto(Tables.PHONG_BAN,Tables.PHONG_BAN.TEN_PHONG_BAN)
                .execute();
    }
    public List<PhongBan> getPhongBans() {
        return dslContext.selectFrom(Tables.PHONG_BAN)
                .fetchInto(PhongBan.class);
    }
}
