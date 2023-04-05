package services.impl;

import DomainModels.SanPham;
import repositories.SanPhamRepository;
import services.SanPhamService;

import java.util.List;

public class SanPhamServiceImpl implements SanPhamService {
    SanPhamRepository spRepo = new SanPhamRepository();

    @Override
    public List<SanPham> getAll() {
        return spRepo.findAll();
    }

    @Override
    public Boolean add(SanPham sp) {
        return spRepo.insert(sp);
    }

    @Override
    public Boolean update(SanPham sp) {
        return spRepo.update(sp);
    }

    @Override
    public Boolean delete(SanPham sp) {
        return spRepo.delete(sp);
    }

    @Override
    public SanPham findByMa(String ma) {
        return spRepo.findByMa(ma);
    }
}
