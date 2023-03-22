package repositories;

import view_model.DongSP;

import java.util.ArrayList;

public class DongSPRepository {
    private ArrayList<DongSP> list;

    public DongSPRepository() {
        this.list = new ArrayList<>();
    }
    public void insert(DongSP dsp){
        list.add(dsp);
    }
    public void update(DongSP dsp){
        // Update ChucVu SET ... Where ma = ?
        for (int i = 0; i < this.list.size(); i++) {
            DongSP vm = this.list.get(i);
            if (vm.getMa().equals(dsp.getMa())) {
                this.list.set(i, dsp);
            }
        }
    }
    public void delete(DongSP dsp)
    {
        // DELETE FROM ChucVu WHERE ma = ?
        for (int i = 0; i < this.list.size(); i++) {
            DongSP vm = this.list.get(i);
            if (vm.getMa().equals(dsp.getMa())) {
                this.list.remove(i);
            }
        }
    }

    public ArrayList<DongSP> findAll()
    {
        return this.list;
    }

    public DongSP findByMa(String ma)
    {
        // SELECT * FROM ChucVu WHERE ma = ?
        for (int i = 0; i < this.list.size(); i++) {
            DongSP vm = this.list.get(i);
            if (vm.getMa().equals(ma)) {
                return vm;
            }
        }

        return null;
    }

}
