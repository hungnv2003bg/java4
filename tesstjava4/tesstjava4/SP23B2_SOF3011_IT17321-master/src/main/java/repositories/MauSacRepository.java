package repositories;

import view_model.QLMauSac;

import java.util.ArrayList;

public class MauSacRepository {
    private ArrayList<QLMauSac> list;

    public MauSacRepository() {
        this.list = new ArrayList<>();
    }
    public void insert(QLMauSac qlms){
        list.add(qlms);
    }
    public void update(QLMauSac qlms){
        // Update ChucVu SET ... Where ma = ?
        for (int i = 0; i < this.list.size(); i++) {
            QLMauSac vm = this.list.get(i);
            if (vm.getMa().equals(qlms.getMa())) {
                this.list.set(i, qlms);
            }
        }
    }
    public void delete(QLMauSac qlms)
    {
        // DELETE FROM ChucVu WHERE ma = ?
        for (int i = 0; i < this.list.size(); i++) {
            QLMauSac vm = this.list.get(i);
            if (vm.getMa().equals(qlms.getMa())) {
                this.list.remove(i);
            }
        }
    }

    public ArrayList<QLMauSac> findAll()
    {
        return this.list;
    }

    public QLMauSac findByMa(String ma)
    {
        // SELECT * FROM ChucVu WHERE ma = ?
        for (int i = 0; i < this.list.size(); i++) {
            QLMauSac vm = this.list.get(i);
            if (vm.getMa().equals(ma)) {
                return vm;
            }
        }

        return null;
    }

}
