package services.impl;

import DomainModels.ChucVu;
import repositories.ChucVuRepository;
import services.ChucVuService;

import java.util.List;

public class ChucVuServiceImpl implements ChucVuService {
    private ChucVuRepository cvRepo = new ChucVuRepository();

    @Override
    public List<ChucVu> getAll() {
        return cvRepo.findAll();
    }

    @Override
    public Boolean add(ChucVu cv) {
        return cvRepo.insert(cv);
    }

    @Override
    public Boolean update(ChucVu cv) {
        return cvRepo.update(cv);
    }

    @Override
    public Boolean delete(ChucVu cv) {
        return cvRepo.delete(cv);
    }

    @Override
    public ChucVu findByMa(String ma) {
        return cvRepo.findByMa(ma);
    }
}
