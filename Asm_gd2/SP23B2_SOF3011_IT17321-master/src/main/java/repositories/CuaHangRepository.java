package repositories;

import DomainModels.CuaHang;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import utils.HibernateUtil;
import view_model.QLCuaHang;

import java.util.ArrayList;
import java.util.List;

public class CuaHangRepository {
    private ArrayList<QLCuaHang> list;
    private Session hSession;

    public CuaHangRepository()
    {
        this.hSession = HibernateUtil.getFACTORY().openSession();
        this.list = new ArrayList<>();
    }

    public Boolean insert(CuaHang ch)
    {
        try {
            this.hSession.getTransaction().begin();
            this.hSession.persist(ch);
            this.hSession.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            this.hSession.getTransaction().rollback();
        }
        return null;
    }

    public Boolean update(CuaHang ch)
    {
        try {
            this.hSession.getTransaction().begin();
            this.hSession.merge(ch);
            this.hSession.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            this.hSession.getTransaction().rollback();
        }
        return null;
    }

    public Boolean delete(CuaHang ch)
    {
        try {
            this.hSession.getTransaction().begin();
            this.hSession.delete(ch);
            this.hSession.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            this.hSession.getTransaction().rollback();
        }
        return null;
    }

    public CuaHang findById(String id)
    {
        return this.hSession.find(CuaHang.class, id);
    }

    public List<CuaHang> findAll()
    {
        String hql = "SELECT chObj FROM CuaHang chObj";
        TypedQuery<CuaHang> query =
                this.hSession.createQuery(hql, CuaHang.class);
        return query.getResultList();
    }

    public CuaHang findByMa(String ma)
    {
        String hql = "SELECT chObj FROM CuaHang chObj WHERE chObj.ma = ?1";
        TypedQuery<CuaHang> query =
                this.hSession.createQuery(hql, CuaHang.class);

        query.setParameter(1, ma);
        return query.getSingleResult();
    }
}