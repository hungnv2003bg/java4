package repositories;

import view_model.QLChucVu;

import java.util.ArrayList;

public class ChucVuRepository {
    private ArrayList<QLChucVu> list;

    public ChucVuRepository() {
        this.list = new ArrayList<>();
    }
    public void insert(QLChucVu qlcv){
        list.add(qlcv);
    }
    public void update(QLChucVu qlcv){
        // Update ChucVu SET ... Where ma = ?
        for (int i = 0; i < this.list.size(); i++) {
            QLChucVu vm = this.list.get(i);
            if (vm.getMa().equals(qlcv.getMa())) {
                this.list.set(i, qlcv);
            }
        }
    }
    public void delete(QLChucVu qlcv)
    {
        // DELETE FROM ChucVu WHERE ma = ?
        for (int i = 0; i < this.list.size(); i++) {
            QLChucVu vm = this.list.get(i);
            if (vm.getMa().equals(qlcv.getMa())) {
                this.list.remove(i);
            }
        }
    }

    public ArrayList<QLChucVu> findAll()
    {
        return this.list;
    }

    public QLChucVu findByMa(String ma)
    {
        // SELECT * FROM ChucVu WHERE ma = ?
        for (int i = 0; i < this.list.size(); i++) {
            QLChucVu vm = this.list.get(i);
            if (vm.getMa().equals(ma)) {
                return vm;
            }
        }

        return null;
    }

}
