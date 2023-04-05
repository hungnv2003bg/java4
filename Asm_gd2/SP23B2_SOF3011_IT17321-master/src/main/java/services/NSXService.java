package services;

import DomainModels.NSX;

import java.util.List;

public interface NSXService {
    public List<NSX> getAll();
    public Boolean add(NSX nsx);
    public Boolean update(NSX nsx);
    public Boolean delete(NSX nsx);
    public NSX findByMa(String ma);
}
