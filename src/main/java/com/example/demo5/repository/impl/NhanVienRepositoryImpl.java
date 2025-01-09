package com.example.demo5.repository.impl;

import com.example.demo5.repository.NhanVienRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;
import test.generated.Tables;
import test.generated.tables.pojos.NhanVien;

import java.util.List;

@Repository
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
public class NhanVienRepositoryImpl implements NhanVienRepository {
    DSLContext dslContext;

    @Override
    public void save(NhanVien nhanVien) {

    }

    @Override
    public List<NhanVien> findAll() {
        return dslContext.selectFrom(Tables.NHAN_VIEN)
                .fetchInto(NhanVien.class);
    }

    @Override
    public NhanVien findById(Long id) {
        return dslContext.selectFrom(Tables.NHAN_VIEN)
                .where(Tables.NHAN_VIEN.ID.eq(id))
                .fetchOneInto(NhanVien.class);
    }

    @Override
    public NhanVien update(NhanVien nhanVien) {
        return null;
    }

    @Override
    public List<NhanVien> findAllByNhanVien() {
        return dslContext.select(
                        Tables.NHAN_VIEN.TEN_NHAN_VIEN, Tables.NHAN_VIEN.DIA_CHI,Tables.NHAN_VIEN.GIOI_TINH,Tables.NHAN_VIEN.EMAIL,
                        Tables.NHAN_VIEN.HOC_VAN, Tables.NHAN_VIEN.NGAY_SINH, Tables.NHAN_VIEN.SO_DIEN_THOAI, Tables.NHAN_VIEN.NGAY_TUYEN_DUNG,
                        Tables.PHONG_BAN.TEN_PHONG_BAN, Tables.CHUC_VU.TEN_CHUC_VU,Tables.TIEN_LUONG.BAC_LUONG, Tables.TIEN_LUONG.BAC_LUONG
                )
                .from(Tables.NHAN_VIEN)
                .join(Tables.PHONG_BAN)
                .on(Tables.NHAN_VIEN.ID_PHONG_BAN.eq(Tables.PHONG_BAN.ID))
                .join(Tables.CHUC_VU)
                .on(Tables.NHAN_VIEN.ID_CHUC_VU.eq(Tables.CHUC_VU.ID))
                .join(Tables.TIEN_LUONG)
                .on(Tables.NHAN_VIEN.ID_TIEN_LUONG.eq(Tables.TIEN_LUONG.ID))
                .fetchInto(NhanVien.class);
    }
}
