package repositories;

import DomainModels.DongSP;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import utils.HibernateUtil;
import view_model.QLDongSP;

import java.util.ArrayList;
import java.util.List;

public class DongSPRepository {
    private ArrayList<QLDongSP> list;
    private Session hSession;

    public DongSPRepository()
    {
        this.hSession = HibernateUtil.getFACTORY().openSession();
        this.list = new ArrayList<>();
    }

    public Boolean insert(DongSP dsp)
    {
        try {
            this.hSession.getTransaction().begin();
            this.hSession.persist(dsp);
            this.hSession.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            this.hSession.getTransaction().rollback();
        }
        return null;
    }

    public Boolean update(DongSP dsp)
    {
        try {
            this.hSession.getTransaction().begin();
            this.hSession.merge(dsp);
            this.hSession.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            this.hSession.getTransaction().rollback();
        }
        return null;
    }

    public Boolean delete(DongSP dsp)
    {
        try {
            this.hSession.getTransaction().begin();
            this.hSession.delete(dsp);
            this.hSession.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            this.hSession.getTransaction().rollback();
        }
        return null;
    }

    public DongSP findById(String id)
    {
        return this.hSession.find(DongSP.class, id);
    }

    public List<DongSP> findAll()
    {
        String hql = "SELECT dspObj FROM DongSP dspObj";
        TypedQuery<DongSP> query =
                this.hSession.createQuery(hql, DongSP.class);
        return query.getResultList();
    }

    public DongSP findByMa(String ma)
    {
        String hql = "SELECT dspObj FROM DongSP dspObj WHERE dspObj.ma = ?1";
        TypedQuery<DongSP> query =
                this.hSession.createQuery(hql, DongSP.class);

        query.setParameter(1, ma);
        return query.getSingleResult();
    }
}