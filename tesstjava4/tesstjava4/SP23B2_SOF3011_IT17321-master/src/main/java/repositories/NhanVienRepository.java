package repositories;

import view_model.QLKhachHang;
import view_model.QLNhanVien;

import java.util.ArrayList;

public class NhanVienRepository {
    private ArrayList<QLNhanVien> list;

    public NhanVienRepository() {
        this.list = new ArrayList<>();
    }
    public void insert(QLNhanVien qlnv){
        list.add(qlnv);
    }
    public void update(QLNhanVien qlnv){
        // Update KhachHang SET ... Where ma = ?
        for (int i = 0; i < this.list.size(); i++) {
            QLNhanVien vm = this.list.get(i);
            if (vm.getMa().equals(qlnv.getMa())) {
                this.list.set(i, qlnv);
            }
        }
    }
    public void delete(QLNhanVien qlnv)
    {
        // DELETE FROM KhachHang WHERE ma = ?
        for (int i = 0; i < this.list.size(); i++) {
            QLNhanVien vm = this.list.get(i);
            if (vm.getMa().equals(qlnv.getMa())) {
                this.list.remove(i);
            }
        }
    }

    public ArrayList<QLNhanVien> findAll()
    {
        return this.list;
    }

    public QLNhanVien findByMa(String ma)
    {
        // SELECT * FROM KhachHang WHERE ma = ?
        for (int i = 0; i < this.list.size(); i++) {
            QLNhanVien vm = this.list.get(i);
            if (vm.getMa().equals(ma)) {
                return vm;
            }
        }

        return null;
    }

}
