package repositories;

import view_model.NSX;
import view_model.QLChucVu;

import java.util.ArrayList;

public class NSXRepository {
    private ArrayList<NSX> list;

    public NSXRepository() {
        this.list = new ArrayList<>();
    }
    public void insert(NSX nsx){
        list.add(nsx);
    }
    public void update(NSX nsx){
        // Update ChucVu SET ... Where ma = ?
        for (int i = 0; i < this.list.size(); i++) {
            NSX vm = this.list.get(i);
            if (vm.getMa().equals(nsx.getMa())) {
                this.list.set(i, nsx);
            }
        }
    }
    public void delete(NSX nsx)
    {
        // DELETE FROM ChucVu WHERE ma = ?
        for (int i = 0; i < this.list.size(); i++) {
            NSX vm = this.list.get(i);
            if (vm.getMa().equals(nsx.getMa())) {
                this.list.remove(i);
            }
        }
    }

    public ArrayList<NSX> findAll()
    {
        return this.list;
    }

    public NSX findByMa(String ma)
    {
        // SELECT * FROM ChucVu WHERE ma = ?
        for (int i = 0; i < this.list.size(); i++) {
            NSX vm = this.list.get(i);
            if (vm.getMa().equals(ma)) {
                return vm;
            }
        }

        return null;
    }

}
