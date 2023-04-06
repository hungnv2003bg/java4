package services.impl;

import DomainModels.NhanVien;
import repositories.NhanVienRepository;
import services.NhanVienService;

import java.util.List;

public class NhanVienServiceImpl implements NhanVienService {
    NhanVienRepository nvRepo = new NhanVienRepository();

    @Override
    public List<NhanVien> getAll() {
        return nvRepo.findAll();
    }

    @Override
    public Boolean add(NhanVien nv) {
        return nvRepo.insert(nv);
    }

    @Override
    public Boolean update(NhanVien nv) {
        return nvRepo.update(nv);
    }

    @Override
    public Boolean delete(NhanVien nv) {
        return nvRepo.delete(nv);
    }

    @Override
    public NhanVien findByMa(String ma) {
        return nvRepo.findByMa(ma);
    }
}
