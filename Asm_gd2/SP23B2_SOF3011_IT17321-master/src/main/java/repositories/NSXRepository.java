package repositories;

import DomainModels.NSX;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import utils.HibernateUtil;
import view_model.QLNSX;

import java.util.ArrayList;
import java.util.List;

public class NSXRepository {
    private ArrayList<QLNSX> list;
    private Session hSession;

    public NSXRepository()
    {
        this.hSession = HibernateUtil.getFACTORY().openSession();
        this.list = new ArrayList<>();
    }

    public void insert(NSX nsx)
    {
        try {
            this.hSession.getTransaction().begin();
            this.hSession.persist(nsx);
            this.hSession.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            this.hSession.getTransaction().rollback();
        }
    }

    public void update(NSX nsx)
    {
        try {
            this.hSession.getTransaction().begin();
            this.hSession.merge(nsx);
            this.hSession.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            this.hSession.getTransaction().rollback();
        }
    }

    public void delete(NSX nsx)
    {
        try {
            this.hSession.getTransaction().begin();
            this.hSession.delete(nsx);
            this.hSession.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            this.hSession.getTransaction().rollback();
        }
    }

    public NSX findById(String id)
    {
        return this.hSession.find(NSX.class, id);
    }

    public List<NSX> findAll()
    {
        String hql = "SELECT nsxObj FROM NSX nsxObj";
        TypedQuery<NSX> query =
                this.hSession.createQuery(hql, NSX.class);
        return query.getResultList();
    }

    public NSX findByMa(String ma)
    {
        String hql = "SELECT nsxObj FROM NSX nsxObj WHERE nsxObj.ma = ?1";
        TypedQuery<NSX> query =
                this.hSession.createQuery(hql, NSX.class);

        query.setParameter(1, ma);
        return query.getSingleResult();
    }
}
