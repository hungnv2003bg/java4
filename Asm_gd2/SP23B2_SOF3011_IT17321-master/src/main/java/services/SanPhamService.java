package services;

import DomainModels.SanPham;

import java.util.List;

public interface SanPhamService {
    public List<SanPham> getAll();
    public Boolean add(SanPham sp);
    public Boolean update(SanPham sp);
    public Boolean delete(SanPham sp);
    public SanPham findByMa(String ma);
}
