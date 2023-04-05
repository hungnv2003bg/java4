package services;

import DomainModels.DongSP;

import java.util.List;

public interface DongSPService {
    public List<DongSP> getAll();
    public Boolean add(DongSP dsp);
    public Boolean update(DongSP dsp);
    public Boolean delete(DongSP dsp);
    public DongSP findByMa(String ma);
}
