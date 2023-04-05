package services.impl;

import DomainModels.NSX;
import repositories.NSXRepository;
import services.NSXService;

import java.util.List;

public class NSXServiceImpl implements NSXService {
    NSXRepository nsxRepo = new NSXRepository();

    @Override
    public List<NSX> getAll() {
        return nsxRepo.findAll();
    }

    @Override
    public Boolean add(NSX nsx) {
        return nsxRepo.insert(nsx);
    }

    @Override
    public Boolean update(NSX nsx) {
        return nsxRepo.update(nsx);
    }

    @Override
    public Boolean delete(NSX nsx) {
        return nsxRepo.delete(nsx);
    }

    @Override
    public NSX findByMa(String ma) {
        return nsxRepo.findByMa(ma);
    }
}
