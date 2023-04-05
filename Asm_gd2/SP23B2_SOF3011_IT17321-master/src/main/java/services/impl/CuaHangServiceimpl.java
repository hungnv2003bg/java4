package services.impl;

import DomainModels.CuaHang;
import repositories.CuaHangRepository;
import services.CuaHangService;

import java.util.List;

public class CuaHangServiceimpl implements CuaHangService {
CuaHangRepository chRepo = new CuaHangRepository();
    @Override
    public List<CuaHang> getAll() {
        return chRepo.findAll();
    }

    @Override
    public Boolean add(CuaHang ch) {
        return chRepo.insert(ch);
    }

    @Override
    public Boolean update(CuaHang ch) {
        return chRepo.update(ch);
    }

    @Override
    public Boolean delete(CuaHang ch) {
        return chRepo.delete(ch);
    }

    @Override
    public CuaHang findByMa(String ma) {
        return chRepo.findByMa(ma);
    }
}
