package services.impl;

import DomainModels.MauSac;
import repositories.MauSacRepository;
import services.MauSacService;

import java.util.List;

public class MauSacServiceImpl implements MauSacService {
    MauSacRepository msRepo = new MauSacRepository();

    @Override
    public List<MauSac> getAll() {
        return msRepo.findAll();
    }

    @Override
    public Boolean add(MauSac ms) {
        return msRepo.insert(ms);
    }

    @Override
    public Boolean update(MauSac ms) {
        return msRepo.update(ms);
    }

    @Override
    public Boolean delete(MauSac ms) {
        return msRepo.delete(ms);
    }

    @Override
    public MauSac findByMa(String ma) {
        return msRepo.findByMa(ma);
    }
}
