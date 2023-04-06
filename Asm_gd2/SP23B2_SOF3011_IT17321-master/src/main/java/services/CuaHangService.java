package services;

import DomainModels.CuaHang;

import java.util.List;

public interface CuaHangService {
    public List<CuaHang> getAll();
    public Boolean add(CuaHang ch);
    public Boolean update(CuaHang ch);
    public Boolean delete(CuaHang ch);
    public CuaHang findByMa(String ma);
    public CuaHang fingById(String id);
}
