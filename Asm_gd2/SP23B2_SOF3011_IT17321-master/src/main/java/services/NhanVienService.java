package services;

import DomainModels.NhanVien;

import java.util.List;

public interface NhanVienService {
    public List<NhanVien> getAll();
    public Boolean add(NhanVien nv);
    public Boolean update(NhanVien nv, String ma);
    public Boolean delete(String ma);
}
