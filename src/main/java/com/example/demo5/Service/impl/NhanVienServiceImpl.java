package com.example.demo5.Service.impl;

import com.example.demo5.Service.NhanVienService;
import com.example.demo5.data.mapper.ChucVuMapper;
import com.example.demo5.data.mapper.NhanVienMapper;
import com.example.demo5.data.mapper.PhongBanMapper;
import com.example.demo5.data.mapper.TienLuongMapper;
import com.example.demo5.data.request.FilterCondition;
import com.example.demo5.data.request.NhanVienRequest;
import com.example.demo5.data.response.*;
import com.example.demo5.repository.ChucVuRepository;
import com.example.demo5.repository.NhanVienRepository;
import com.example.demo5.repository.PhongBanRepository;
import com.example.demo5.repository.TienLuongRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import test.generated.tables.pojos.ChucVu;
import test.generated.tables.pojos.NhanVien;
import test.generated.tables.pojos.PhongBan;
import test.generated.tables.pojos.TienLuong;


import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@SuppressWarnings("ALL")
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class NhanVienServiceImpl implements NhanVienService {
    PhongBanMapper phongBanMapper;
    ChucVuMapper chucVuMapper;
    TienLuongMapper tienLuongMapper;
    NhanVienMapper nhanVienMapper;
    NhanVienRepository nhanVienRepository;
    TienLuongRepository tienLuongRepository;
    ChucVuRepository chucVuRepository;
    PhongBanRepository phongBanRepository;

    @Override
    public Void save(NhanVienRequest nhanVienRequest) {
        PhongBan phongBan = phongBanRepository.findById(nhanVienRequest.getIdPhongBan());
        ChucVu chucVu = chucVuRepository.findById(nhanVienRequest.getIdChucVu());
        TienLuong tienLuong = tienLuongRepository.findById(nhanVienRequest.getIdTienLuong());
        NhanVien nhanVien = nhanVienMapper.toEntity(nhanVienRequest);
        nhanVien.setIdChucVu(chucVu.getId());
        nhanVien.setIdTienLuong(tienLuong.getId());
        nhanVien.setIdPhongBan(phongBan.getId());
        nhanVienRepository.save(nhanVien);
        return null;
    }
    public NhanVienResponse toResponse(NhanVien nhanVien, PhongBanResponse phongBanResponse, ChucVuResponse chucVuResponse, TienLuongResponse tienLuongResponse) {
        // Chuyển đối tượng NhanVien thành NhanVienResponse


        return nhanVienMapper.toNhanVienResponse(nhanVien, phongBanResponse, chucVuResponse, tienLuongResponse);
    }
    private PageResponse<NhanVienResponse> toPageResponse(Pageable pageable, List<NhanVienResponse> nhanViens, Page<NhanVien> page) {
        return PageResponse.<NhanVienResponse>builder()
                .content(nhanViens)
                .pageNo(pageable.getPageNumber())
                .pageSize(pageable.getPageSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .build();

    }

    @Override
    public NhanVienResponse getById(Long id) {
        NhanVien nhanVien = nhanVienRepository.findById(id);

       PhongBan phongBan = phongBanRepository.findById(nhanVien.getIdPhongBan());
       PhongBanResponse phongBanResponse = phongBanMapper.toPhongBanResponse(phongBan);

       ChucVu chucVu = chucVuRepository.findById(nhanVien.getIdChucVu());
       ChucVuResponse chucVuResponse = chucVuMapper.toChucVuResponse(chucVu);

       TienLuong tienLuong = tienLuongRepository.findById(nhanVien.getIdTienLuong());
       TienLuongResponse tienLuongResponse = tienLuongMapper.toTienLuongResponse(tienLuong);

        // Gọi phương thức toResponse để trả về NhanVienResponse
        return toResponse(nhanVien, phongBanResponse, chucVuResponse, tienLuongResponse);
    }


    @Override
    public NhanVienResponse update(NhanVienRequest nhanVienRequest, Long id) {
        NhanVien nhanVien = nhanVienRepository.findById(id);
        nhanVienMapper.updateNhanVien(nhanVien, nhanVienRequest);
        PhongBan phongBan = phongBanRepository.findById(nhanVienRequest.getIdPhongBan());
        ChucVu chucVu = chucVuRepository.findById(nhanVienRequest.getIdChucVu());
        TienLuong tienLuong = tienLuongRepository.findById(nhanVienRequest.getIdTienLuong());

        PhongBanResponse phongBanResponse = phongBanMapper.toPhongBanResponse(phongBan);
        ChucVuResponse chucVuResponse = chucVuMapper.toChucVuResponse(chucVu);
        TienLuongResponse tienLuongResponse = tienLuongMapper.toTienLuongResponse(tienLuong);

        nhanVienRepository.update(nhanVien);
        return nhanVienMapper.toNhanVienResponse(nhanVien, phongBanResponse, chucVuResponse, tienLuongResponse);
    }


    @Override
    public Void delete(Long id) {
        NhanVien nhanVien = nhanVienRepository.findById(id);
        nhanVienRepository.deleteById(id);
        return null;
    }

    @Override
    public PageResponse<NhanVienResponse> searchNhanVien(List<FilterCondition> filterConditions, Pageable pageable) {
        // Truy vấn danh sách nhân viên theo các điều kiện lọc và phân trang
        Page<NhanVien> nhanVienPage = nhanVienRepository.searchNhanVien(filterConditions, pageable);
        List<NhanVien> nhanViens = nhanVienPage.getContent();

        // Lấy danh sách id phòng ban từ các nhân viên
        List<Long> idPhongBans = nhanViens.stream().map(NhanVien::getIdPhongBan).toList();
        // Tạo Map ánh xạ id phòng ban sang PhongBanResponse
        Map<Long, PhongBanResponse> phongBanResponseMap = phongBanRepository.findAllByListPBId(idPhongBans).stream()
                .collect(Collectors.toMap(PhongBan::getId, phongBanMapper::toPhongBanResponse));

        // Lấy danh sách id chức vụ từ các nhân viên
        List<Long> idChucVus = nhanViens.stream().map(NhanVien::getIdChucVu).toList();
        // Tạo Map ánh xạ id chức vụ sang ChucVuResponse
        Map<Long, ChucVuResponse> chucVuResponseMap = chucVuRepository.findAllByListChucVuId(idChucVus).stream()
                .collect(Collectors.toMap(ChucVu::getId, chucVuMapper::toChucVuResponse));

        // Lấy danh sách id tiền lương từ các nhân viên
        List<Long> idTienLuongs = nhanViens.stream().map(NhanVien::getIdTienLuong).toList();
        // Tạo Map ánh xạ id tiền lương sang TienLuongResponse
        Map<Long, TienLuongResponse> tienLuongResponseMap = tienLuongRepository.findAllByListTLId(idTienLuongs).stream()
                .collect(Collectors.toMap(TienLuong::getId, tienLuongMapper::toTienLuongResponse));

        // Duyệt qua danh sách nhân viên và tạo danh sách NhanVienResponse
        List<NhanVienResponse> nhanVienResponses = nhanViens.stream().map(nhanVien -> {
            // Lấy thông tin phòng ban, chức vụ, tiền lương từ các Map
            PhongBanResponse phongBanResponse = phongBanResponseMap.get(nhanVien.getIdPhongBan());
            ChucVuResponse chucVuResponse = chucVuResponseMap.get(nhanVien.getIdChucVu());
            TienLuongResponse tienLuongResponse = tienLuongResponseMap.get(nhanVien.getIdTienLuong());

            // Tạo NhanVienResponse từ các thông tin đã có
            return toResponse(nhanVien, phongBanResponse, chucVuResponse, tienLuongResponse);
        }).toList();

        // Trả về kết quả phân trang chứa danh sách NhanVienResponse và thông tin phân trang
        return toPageResponse(pageable, nhanVienResponses, nhanVienPage);
    }

    @Override
    public PageResponse<NhanVienResponse> getPageNhanVien(Pageable pageable) {
        Page<NhanVien> page = nhanVienRepository.getNhanVien(pageable);

        List<NhanVien> nhanViens = page.getContent();
        List<Long> idPhongBans = nhanViens.stream().map(NhanVien::getIdPhongBan).toList();
        Map<Long,PhongBanResponse> phongBanResponseMap = phongBanRepository.findAllByListPBId(idPhongBans).stream().collect(Collectors.toMap(PhongBan::getId, phongBanMapper::toPhongBanResponse));

        List<Long> idChucVus=nhanViens.stream().map(NhanVien::getIdChucVu).toList();
        Map<Long,ChucVuResponse> chucVuResponseMap = chucVuRepository.findAllByListChucVuId(idChucVus).stream().collect(Collectors.toMap(ChucVu::getId, chucVuMapper::toChucVuResponse));

        List<Long> idTienLuongs = nhanViens.stream().map(NhanVien::getIdTienLuong).toList();
        Map<Long,TienLuongResponse> tienLuongResponseMap=tienLuongRepository.findAllByListTLId(idTienLuongs).stream().collect(Collectors.toMap(TienLuong::getId, tienLuongMapper::toTienLuongResponse));

        List<NhanVienResponse> nhanVienResponses = nhanViens.stream().map(nhanVien -> {
            PhongBanResponse phongBanResponse = phongBanResponseMap.get(nhanVien.getIdPhongBan());
            ChucVuResponse chucVuResponse = chucVuResponseMap.get(nhanVien.getIdChucVu());
            TienLuongResponse tienLuongResponse = tienLuongResponseMap.get(nhanVien.getIdTienLuong());

            return toResponse(nhanVien, phongBanResponse, chucVuResponse, tienLuongResponse);

        }).toList();
        return toPageResponse(pageable,nhanVienResponses,page);
    }
}
