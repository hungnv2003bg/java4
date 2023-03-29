package repositories;

import view_model.ChiTietSP;

import java.util.ArrayList;

public class ChiTietSPRepository {
    private ArrayList<ChiTietSP> list;

    public ChiTietSPRepository() {
        this.list = new ArrayList<>();
    }
    public void insert(ChiTietSP ctsp){
        list.add(ctsp);
    }
    public void update(ChiTietSP ctsp){
        // Update KhachHang SET ... Where ma = ?
        for (int i = 0; i < this.list.size(); i++) {
            ChiTietSP vm = this.list.get(i);
            if (vm.getSanPham().equals(ctsp.getSanPham())) {
                this.list.set(i, ctsp);
            }
        }
    }
    public void delete(ChiTietSP ctsp)
    {
        // DELETE FROM KhachHang WHERE ma = ?
        for (int i = 0; i < this.list.size(); i++) {
            ChiTietSP vm = this.list.get(i);
            if (vm.getSanPham().equals(ctsp.getSanPham())) {
                this.list.remove(i);
            }
        }
    }

    public ArrayList<ChiTietSP> findAll()
    {
        return this.list;
    }

    public ChiTietSP findByMa(String sp)
    {
        // SELECT * FROM KhachHang WHERE ma = ?
        for (int i = 0; i < this.list.size(); i++) {
            ChiTietSP vm = this.list.get(i);
            if (vm.getSanPham().equals(sp)) {
                return vm;
            }
        }

        return null;
    }

}
