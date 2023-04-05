package services.impl;

import DomainModels.KhachHang;
import repositories.KhachHangRepository;
import services.KhachHangService;

import java.util.List;

public class KhachHangServiceImpl implements KhachHangService {
    KhachHangRepository khRepo = new KhachHangRepository();
    @Override
    public List<KhachHang> getAll() {
        return khRepo.findAll();
    }

    @Override
    public Boolean add(KhachHang kh) {
        return khRepo.insert(kh);
    }

    @Override
    public Boolean update(KhachHang kh) {
        return khRepo.update(kh);
    }

    @Override
    public Boolean delete(KhachHang kh) {
        return khRepo.delete(kh);
    }

    @Override
    public KhachHang findByMa(String ma) {
        return khRepo.findByMa(ma);
    }
}
