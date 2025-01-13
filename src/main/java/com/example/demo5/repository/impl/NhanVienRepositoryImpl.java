package com.example.demo5.repository.impl;

import com.example.demo5.data.request.FilterCondition;
import com.example.demo5.repository.NhanVienRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.impl.DSL;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import test.generated.Tables;
import test.generated.tables.pojos.NhanVien;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static test.generated.Tables.NHAN_VIEN;

@Repository
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
@RequiredArgsConstructor
public class NhanVienRepositoryImpl implements NhanVienRepository {
    DSLContext dslContext;

    @Override
    public Void save(NhanVien nhanVien) {
        dslContext.insertInto(NHAN_VIEN)
                .set(NHAN_VIEN.TEN_NHAN_VIEN,nhanVien.getTenNhanVien())
                .set(NHAN_VIEN.GIOI_TINH, nhanVien.getGioiTinh())
                .set(NHAN_VIEN.DIA_CHI, nhanVien.getDiaChi())
                .set(NHAN_VIEN.EMAIL, nhanVien.getEmail())
                .set(NHAN_VIEN.SO_DIEN_THOAI, nhanVien.getSoDienThoai())
                .set(NHAN_VIEN.HOC_VAN, nhanVien.getHocVan())
                .set(NHAN_VIEN.NGAY_TUYEN_DUNG, nhanVien.getNgayTuyenDung())
                .set(NHAN_VIEN.NGAY_SINH, nhanVien.getNgaySinh())
                .set(NHAN_VIEN.ID_PHONG_BAN, nhanVien.getIdPhongBan())
                .set(NHAN_VIEN.ID_CHUC_VU, nhanVien.getIdChucVu())
                .set(NHAN_VIEN.ID_TIEN_LUONG, nhanVien.getIdTienLuong())
                .execute();
        return null;
    }

    @Override
    public List<NhanVien> findAll() {
        return dslContext.selectFrom(NHAN_VIEN)
                .fetchInto(NhanVien.class);
    }

    @Override
    public NhanVien findById(Long id) {
        return dslContext.selectFrom(NHAN_VIEN)
                .where(NHAN_VIEN.ID.eq(id))
                .fetchOneInto(NhanVien.class);
    }

    @Override
    public Void deleteById(Long id) {
        dslContext.deleteFrom(NHAN_VIEN)
                .where(NHAN_VIEN.ID.eq(id))
                .execute();
        return null;
    }

    @Override
    public Void update(NhanVien nhanVien) {
        dslContext.update(NHAN_VIEN)
                .set(NHAN_VIEN.TEN_NHAN_VIEN, nhanVien.getTenNhanVien())
                .set(NHAN_VIEN.DIA_CHI, nhanVien.getDiaChi())
                .set(NHAN_VIEN.EMAIL, nhanVien.getEmail())
                .set(NHAN_VIEN.HOC_VAN, nhanVien.getHocVan())
                .set(NHAN_VIEN.GIOI_TINH, nhanVien.getGioiTinh())
                .set(NHAN_VIEN.NGAY_SINH, nhanVien.getNgaySinh())
                .set(NHAN_VIEN.NGAY_TUYEN_DUNG, nhanVien.getNgayTuyenDung())
                .set(NHAN_VIEN.SO_DIEN_THOAI, nhanVien.getSoDienThoai())
                .set(NHAN_VIEN.ID_TIEN_LUONG, nhanVien.getIdTienLuong())
                .set(NHAN_VIEN.ID_PHONG_BAN, nhanVien.getIdPhongBan())
                .set(NHAN_VIEN.ID_CHUC_VU, nhanVien.getIdChucVu())
                .where(NHAN_VIEN.ID.eq(nhanVien.getId()))
                .execute();
        return null;
    }

    @Transactional(readOnly = true)
    @Override
    public Page<NhanVien> getNhanVien(Pageable pageable) {
        List<NhanVien> nhanVienList = dslContext.selectFrom(NHAN_VIEN)
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .fetchInto(NhanVien.class);
        long total = countNhanVien();
        return new PageImpl<>(nhanVienList,pageable,total); //dem tổng số nhân viên, so trang,

    }

    @Override
    public long countNhanVien() {
        return dslContext.fetchCount(dslContext.select().from(NHAN_VIEN));
    }
    public Page<NhanVien> searchNhanVien(List<FilterCondition> conditions, Pageable pageable) {
        Condition condition = buildCondition(conditions);
        List<NhanVien> nhanViens = dslContext.selectFrom(NHAN_VIEN)
                .where(condition)
                .limit(pageable.getPageSize())
                .offset(pageable.getOffset())
                .fetchInto(NhanVien.class);

        Long totalCount = dslContext.selectCount().from(NHAN_VIEN).where(condition)
                .fetchOptional()
                .map(result -> result.get(0, Long.class))
                .orElse(0L);

        return new PageImpl<>(nhanViens, pageable, totalCount);
    }

    private Condition buildCondition(List<FilterCondition> filters) {
        if (filters == null || filters.isEmpty()) {
            return DSL.noCondition();
        }

        Condition condition = createCondition(filters.get(0));
        for (int i = 1; i < filters.size(); i++) {  // Start from index 1
            condition = condition.and(createCondition(filters.get(i)));  // Create new condition
        }
        return condition;
    }

    private Condition createCondition(FilterCondition filter) {
        Field<Object> field = NHAN_VIEN.field(filter.getField(), Object.class);
        if (field == null) {
            throw new IllegalArgumentException("invalid field key:" + filter.getField());
        }

        Object castedValue = castToRequireType(filter.getField(), filter.getValue());

        // Change to use getOperation() instead of getOperator()
        return switch (filter.getOperation()) {  // Changed from getOperator() to getOperation()
            case ":" -> field.eq(castedValue);
            case "<" -> field.lt(castedValue);
            case ">" -> field.gt(castedValue);
            case ">=" -> field.ge(castedValue);
            case "<=" -> field.le(castedValue);
            default -> throw new IllegalArgumentException("invalid operator:" + filter.getOperation());
        };
    }

    private Object castToRequireType(String key, String value) {
        if (value == null) return null;
        return switch (key) {
            case "ngaySinh", "ngayTuyenDung" -> LocalDate.parse(value, DateTimeFormatter.ISO_DATE);
            case "id", "idPhongBan", "idChucVu", "idTienLuong" -> Long.parseLong(value);
            default -> value;
        };
    }

}
