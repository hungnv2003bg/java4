package services;

import DomainModels.KhachHang;
import DomainModels.NhanVien;

import java.util.List;

public interface NhanVienService {
    public List<NhanVien> getAll();
    public Boolean add(NhanVien nv);
    public Boolean update(NhanVien nv);
    public Boolean delete(NhanVien nv);
    public NhanVien findByMa(String ma);
}
