package services;

import DomainModels.ChucVu;
import java.util.List;

public interface ChucVuService {
    public List<ChucVu> getAll();
    public Boolean add(ChucVu cv);
    public Boolean update(ChucVu cv);
    public Boolean delete(ChucVu cv);
    public ChucVu findByMa(String ma);
}
