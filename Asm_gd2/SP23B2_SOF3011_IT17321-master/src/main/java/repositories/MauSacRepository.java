package repositories;

import DomainModels.MauSac;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import utils.HibernateUtil;
import view_model.QLMauSac;

import java.util.ArrayList;
import java.util.List;

public class MauSacRepository {
    private ArrayList<QLMauSac> list;
    private Session hSession;

    public MauSacRepository()
    {
        this.hSession = HibernateUtil.getFACTORY().openSession();
        this.list = new ArrayList<>();
    }

    public void insert(MauSac ms)
    {
        try {
            this.hSession.getTransaction().begin();
            this.hSession.persist(ms);
            this.hSession.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            this.hSession.getTransaction().rollback();
        }
    }

    public void update(MauSac ms)
    {
        try {
            this.hSession.getTransaction().begin();
            this.hSession.merge(ms);
            this.hSession.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            this.hSession.getTransaction().rollback();
        }
    }

    public void delete(MauSac ms)
    {
        try {
            this.hSession.getTransaction().begin();
            this.hSession.delete(ms);
            this.hSession.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            this.hSession.getTransaction().rollback();
        }
    }

    public MauSac findById(String id)
    {
        return this.hSession.find(MauSac.class, id);
    }

    public List<MauSac> findAll()
    {
        String hql = "SELECT msObj FROM MauSac msObj";
        TypedQuery<MauSac> query =
                this.hSession.createQuery(hql, MauSac.class);
        return query.getResultList();
    }

    public MauSac findByMa(String ma)
    {
        String hql = "SELECT msObj FROM MauSac msObj WHERE msObj.ma = ?1";
        TypedQuery<MauSac> query =
                this.hSession.createQuery(hql, MauSac.class);

        query.setParameter(1, ma);
        return query.getSingleResult();
    }
}
