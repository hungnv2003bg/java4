package services.impl;

import DomainModels.DongSP;
import repositories.DongSPRepository;
import services.DongSPService;

import java.util.List;

public class DongSPServiceImpl implements DongSPService {
    private DongSPRepository dspRepo = new DongSPRepository();

    @Override
    public List<DongSP> getAll() {
        return dspRepo.findAll();
    }

    @Override
    public Boolean add(DongSP dsp) {
        return dspRepo.insert(dsp);
    }

    @Override
    public Boolean update(DongSP dsp) {
        return dspRepo.update(dsp);
    }

    @Override
    public Boolean delete(DongSP dsp) {
        return dspRepo.delete(dsp);
    }

    @Override
    public DongSP findByMa(String ma) {
        return dspRepo.findByMa(ma);
    }
}
