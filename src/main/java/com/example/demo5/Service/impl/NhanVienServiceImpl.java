package com.example.demo5.Service.impl;

import com.example.demo5.Service.NhanVienService;
import com.example.demo5.data.mapper.ChucVuMapper;
import com.example.demo5.data.mapper.NhanVienMapper;
import com.example.demo5.data.mapper.PhongBanMapper;
import com.example.demo5.data.mapper.TienLuongMapper;
import com.example.demo5.data.request.NhanVienRequest;
import com.example.demo5.data.response.ChucVuResponse;
import com.example.demo5.data.response.NhanVienResponse;
import com.example.demo5.data.response.PhongBanResponse;
import com.example.demo5.data.response.TienLuongResponse;
import com.example.demo5.repository.ChucVuRepository;
import com.example.demo5.repository.NhanVienRepository;
import com.example.demo5.repository.PhongBanRepository;
import com.example.demo5.repository.TienLuongRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import test.generated.tables.pojos.ChucVu;
import test.generated.tables.pojos.NhanVien;
import test.generated.tables.pojos.PhongBan;
import test.generated.tables.pojos.TienLuong;


import java.util.List;
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
    public void save(NhanVienRequest nhanVienRequest) {
    }

    @Override
    public NhanVienResponse getById(Long id) {
        return null;
    }

    @Override
    public List<NhanVienResponse> getAllNhanVien() {
        List<NhanVien> nhanVienList = nhanVienRepository.findAll();

        return nhanVienList.stream()
                .map(nhanVien -> {
                    NhanVienResponse nhanVienResponse = nhanVienMapper.toNhanVienResponse(nhanVien);

                    PhongBan phongBan = phongBanRepository.findById(nhanVien.getIdPhongBan());
                    PhongBanResponse phongBanResponse = phongBanMapper.toPhongBanResponse(phongBan);

                    ChucVu chucVu = chucVuRepository.findById(nhanVien.getIdChucVu());
                    ChucVuResponse chucVuResponse = chucVuMapper.toChucVuResponse(chucVu);

                    TienLuong tienLuong = tienLuongRepository.findById(nhanVien.getIdTienLuong());
                    TienLuongResponse tienLuongResponse = tienLuongMapper.toTienLuongResponse(tienLuong);

                    nhanVienResponse.setPhongBan(phongBanResponse);
                    nhanVienResponse.setChucVu(chucVuResponse);
                    nhanVienResponse.setTienLuong(tienLuongResponse);
                    return nhanVienResponse;

                })
                .collect(Collectors.toList());
    }

    @Override
    public List<NhanVienResponse> listNhanVien() {
        //lấy danh sách nhn viên từ repository
        List<NhanVien> nhanVienList1 = nhanVienRepository.findAllByNhanVien();
        //Chuyển đổi NhanVien thành NhanVienResponse
        return nhanVienList1.stream()
                .map(nhanVien -> {
                    //chuyển đổi đối tượng NhanVien thành NhanVienResponse
                    NhanVienResponse nhanVienResponse = nhanVienMapper.toNhanVienResponse(nhanVien);
                    //lấy thông tin của phòng ban từ repository
                    PhongBan phongBan = phongBanRepository.findById(nhanVien.getIdPhongBan());
                    PhongBanResponse phongBanResponse = phongBanMapper.toPhongBanResponse(phongBan);

                    ChucVu chucVu = chucVuRepository.findById(nhanVien.getIdChucVu());
                    ChucVuResponse chucVuResponse = chucVuMapper.toChucVuResponse(chucVu);

                    TienLuong tienLuong = tienLuongRepository.findById(nhanVien.getIdTienLuong());
                    TienLuongResponse tienLuongResponse = tienLuongMapper.toTienLuongResponse(tienLuong);

                    //set các thông tin vào NhanVienRespnse
                    nhanVienResponse.setPhongBan(phongBanResponse);
                    nhanVienResponse.setChucVu(chucVuResponse);
                    nhanVienResponse.setTienLuong(tienLuongResponse);

                    return nhanVienResponse;


                })
                .collect(Collectors.toList());
    }
}
