package repositories;

import view_model.QLChucVu;
import view_model.QLSanPham;

import java.util.ArrayList;

public class SanPhamRepository {
    private ArrayList<QLSanPham> list;

    public SanPhamRepository() {
        this.list = new ArrayList<>();
    }
    public void insert(QLSanPham qlsp){
        list.add(qlsp);
    }
    public void update(QLSanPham qlsp){
        // Update ChucVu SET ... Where ma = ?
        for (int i = 0; i < this.list.size(); i++) {
            QLSanPham vm = this.list.get(i);
            if (vm.getMa().equals(qlsp.getMa())) {
                this.list.set(i, qlsp);
            }
        }
    }
    public void delete(QLSanPham qlsp)
    {
        // DELETE FROM ChucVu WHERE ma = ?
        for (int i = 0; i < this.list.size(); i++) {
            QLSanPham vm = this.list.get(i);
            if (vm.getMa().equals(qlsp.getMa())) {
                this.list.remove(i);
            }
        }
    }

    public ArrayList<QLSanPham> findAll()
    {
        return this.list;
    }

    public QLSanPham findByMa(String ma)
    {
        // SELECT * FROM ChucVu WHERE ma = ?
        for (int i = 0; i < this.list.size(); i++) {
            QLSanPham vm = this.list.get(i);
            if (vm.getMa().equals(ma)) {
                return vm;
            }
        }

        return null;
    }

}
