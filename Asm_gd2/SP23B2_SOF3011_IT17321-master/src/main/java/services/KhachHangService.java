package services;

import DomainModels.KhachHang;

import java.util.List;

public interface KhachHangService {
    public List<KhachHang> getAll();
    public Boolean add(KhachHang kh);
    public Boolean update(KhachHang kh);
    public Boolean delete(KhachHang kh);
    public KhachHang findByMa(String ma);
}
