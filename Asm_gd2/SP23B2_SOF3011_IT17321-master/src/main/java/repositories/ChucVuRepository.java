package repositories;

import DomainModels.ChucVu;
import DomainModels.NhanVien;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import utils.HibernateUtil;
import view_model.QLChucVu;

import java.util.ArrayList;
import java.util.List;

public class ChucVuRepository {
    private ArrayList<QLChucVu> list;
    private Session hSession;

    public ChucVuRepository()
    {
        this.hSession = HibernateUtil.getFACTORY().openSession();
        this.list = new ArrayList<>();
    }

    public Boolean insert(ChucVu cv)
    {
        try {
            this.hSession.getTransaction().begin();
            this.hSession.persist(cv);
            this.hSession.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            this.hSession.getTransaction().rollback();
        }
        return null;
    }

    public Boolean update(ChucVu cv)
    {
        try {
            this.hSession.getTransaction().begin();
            this.hSession.merge(cv);
            this.hSession.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            this.hSession.getTransaction().rollback();
        }
        return null;
    }

    public Boolean delete(ChucVu cv)
    {
        try {
            this.hSession.getTransaction().begin();
            this.hSession.delete(cv);
            this.hSession.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            this.hSession.getTransaction().rollback();
        }
        return null;
    }

    public ChucVu findById(String id)
    {
        return this.hSession.find(ChucVu.class, id);
    }

    public List<ChucVu> findAll()
    {
        String hql = "SELECT cvObj FROM ChucVu cvObj";
        TypedQuery<ChucVu> query =
                this.hSession.createQuery(hql, ChucVu.class);
        return query.getResultList();
    }

    public ChucVu findByMa(String ma)
    {
        String hql = "SELECT cvObj FROM ChucVu cvObj WHERE cvObj.ma = ?1";
        TypedQuery<ChucVu> query =
                this.hSession.createQuery(hql, ChucVu.class);

        query.setParameter(1, ma);
        try {
            ChucVu cv = query.getSingleResult();
            return cv;
        }catch (NoResultException e){
            e.printStackTrace();
            return null;
        }
    }
}