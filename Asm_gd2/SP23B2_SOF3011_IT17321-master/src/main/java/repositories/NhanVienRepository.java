
package repositories;

import DomainModels.NhanVien;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateUtil;

import java.util.List;

public class NhanVienRepository {
    private Session hSession;

    public NhanVienRepository()
    {
        this.hSession = HibernateUtil.getFACTORY().openSession();
    }
    public Boolean insert(NhanVien nv)
    {
        try {
            this.hSession.getTransaction().begin();
            this.hSession.persist(nv);
            this.hSession.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            this.hSession.getTransaction().rollback();
        }
        return null;
    }

    public Boolean update(NhanVien nv)
    {
        try {
            this.hSession.getTransaction().begin();
            this.hSession.merge(nv);
            this.hSession.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            this.hSession.getTransaction().rollback();
        }
        return null;
    }

    public Boolean delete(NhanVien nv)
    {
        try {
            this.hSession.getTransaction().begin();
            this.hSession.delete(nv);
            this.hSession.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            this.hSession.getTransaction().rollback();
        }
        return null;
    }

    public NhanVien findById(String id)
    {
        return this.hSession.find(NhanVien.class, id);
    }

    public List<NhanVien> findAll()
    {
        String hql = "SELECT nvObj FROM NhanVien nvObj";
        TypedQuery<NhanVien> query =
                this.hSession.createQuery(hql, NhanVien.class);
        return query.getResultList();
    }

    public NhanVien findByMa(String ma)
    {
        String hql = "SELECT nvObj FROM NhanVien nvObj WHERE nvObj.ma = ?1";
        TypedQuery<NhanVien> query =
                this.hSession.createQuery(hql, NhanVien.class);

        query.setParameter(1, ma);
        try {
            NhanVien nv = query.getSingleResult();
            return nv;
        }catch (NoResultException e){
            e.printStackTrace();
            return null;
        }
    }

    public NhanVien login(String ma, String matKhau)
    {
        String hql = "SELECT nv FROM NhanVien nv WHERE nv.ma = ?1 AND nv.matKhau = ?2";
        TypedQuery<NhanVien> query = this.hSession.createQuery(hql, NhanVien.class);
        query.setParameter(1, ma);
        query.setParameter(2, matKhau);

        try {
            NhanVien nv = query.getSingleResult();
            return nv;
        } catch (NoResultException e) {
            e.printStackTrace();
            return null;
        }
    }
}