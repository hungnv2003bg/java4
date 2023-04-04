package repositories;

import DomainModels.SanPham;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import utils.HibernateUtil;
import view_model.QLSanPham;

import java.util.ArrayList;
import java.util.List;

public class SanPhamRepository {
    private ArrayList<QLSanPham> list;
    private Session hSession;

    public SanPhamRepository()
    {
        this.hSession = HibernateUtil.getFACTORY().openSession();
        this.list = new ArrayList<>();
    }

    public void insert(SanPham sp)
    {
        try {
            this.hSession.getTransaction().begin();
            this.hSession.persist(sp);
            this.hSession.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            this.hSession.getTransaction().rollback();
        }
    }

    public void update(SanPham sp)
    {
        try {
            this.hSession.getTransaction().begin();
            this.hSession.merge(sp);
            this.hSession.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            this.hSession.getTransaction().rollback();
        }
    }

    public void delete(SanPham sp)
    {
        try {
            this.hSession.getTransaction().begin();
            this.hSession.delete(sp);
            this.hSession.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            this.hSession.getTransaction().rollback();
        }
    }

    public SanPham findById(String id)
    {
        return this.hSession.find(SanPham.class, id);
    }

    public List<SanPham> findAll()
    {
        String hql = "SELECT spObj FROM SanPham spObj";
        TypedQuery<SanPham> query =
                this.hSession.createQuery(hql, SanPham.class);
        return query.getResultList();
    }

    public SanPham findByMa(String ma)
    {
        String hql = "SELECT spObj FROM SanPham spObj WHERE spObj.ma = ?1";
        TypedQuery<SanPham> query =
                this.hSession.createQuery(hql, SanPham.class);

        query.setParameter(1, ma);
        return query.getSingleResult();
    }
}