package services;

import DomainModels.MauSac;

import java.util.List;

public interface MauSacService {
    public List<MauSac> getAll();
    public Boolean add(MauSac ms);
    public Boolean update(MauSac ms);
    public Boolean delete(MauSac ms);
    public MauSac findByMa(String ma);
}
