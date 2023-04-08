package repositories;




import DomainModels.ChiTietSanPham;

import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtil;
import view_model.QLChiTietSanPham;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ChiTietSanPhamRepository {
    private ArrayList<QLChiTietSanPham> list;
    private Session hSession;

    public ChiTietSanPhamRepository()
    {
        this.list = new ArrayList<>();
        this.hSession = HibernateUtil.getFACTORY().openSession();
    }

    public void insert(ChiTietSanPham ctsp)
    {
        Transaction transaction = this.hSession.getTransaction();
        try {
            transaction.begin();
            this.hSession.persist(ctsp);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
    }

    public void update(ChiTietSanPham ctsp)
    {
        Transaction transaction = this.hSession.getTransaction();
        try {
            transaction.begin();
            this.hSession.save(ctsp);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
    }

    public void delete(ChiTietSanPham ctsp)
    {
        Transaction transaction = this.hSession.getTransaction();
        try {
            transaction.begin();
            this.hSession.delete(ctsp);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
    }

    public List<ChiTietSanPham> findAll()
    {
        try {
            Session session = HibernateUtil.getFACTORY().openSession();
            String hql = "SELECT ctspobj FROM ChiTietSanPham ctspobj";
            TypedQuery<ChiTietSanPham> query =
                    session.createQuery(hql, ChiTietSanPham.class);
            return query.getResultList();
        }catch (NoResultException e){
            e.printStackTrace();
            return null;
        }

    }
    public ChiTietSanPham findByMa(UUID id)
    {
        String hql = "SELECT c FROM ChiTietSanPham c WHERE c.id = ?1";
        TypedQuery<ChiTietSanPham> query = this.hSession.createQuery(hql, ChiTietSanPham.class);
        query.setParameter(1, id);
        return query.getSingleResult();
    }
}