package repositories;

import view_model.QLChucVu;
import view_model.QLCuaHang;

import java.util.ArrayList;

public class CuaHangRepository {
    private ArrayList<QLCuaHang> list;

    public CuaHangRepository() {
        this.list = new ArrayList<>();
    }
    public void insert(QLCuaHang qlch){
        list.add(qlch);
    }
    public void update(QLCuaHang qlch){
        // Update ChucVu SET ... Where ma = ?
        for (int i = 0; i < this.list.size(); i++) {
            QLCuaHang vm = this.list.get(i);
            if (vm.getMa().equals(qlch.getMa())) {
                this.list.set(i, qlch);
            }
        }
    }
    public void delete(QLCuaHang qlch)
    {
        // DELETE FROM ChucVu WHERE ma = ?
        for (int i = 0; i < this.list.size(); i++) {
            QLCuaHang vm = this.list.get(i);
            if (vm.getMa().equals(qlch.getMa())) {
                this.list.remove(i);
            }
        }
    }

    public ArrayList<QLCuaHang> findAll()
    {
        return this.list;
    }

    public QLCuaHang findByMa(String ma)
    {
        // SELECT * FROM ChucVu WHERE ma = ?
        for (int i = 0; i < this.list.size(); i++) {
            QLCuaHang vm = this.list.get(i);
            if (vm.getMa().equals(ma)) {
                return vm;
            }
        }

        return null;
    }

}
