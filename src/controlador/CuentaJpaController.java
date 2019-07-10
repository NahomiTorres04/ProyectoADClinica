/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import controlador.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.Bien;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import entidades.Cuenta;

/**
 *
 * @author Alex de León Véliz <alexdlveliz@hotmail.com>
 */
public class CuentaJpaController implements Serializable {

    public CuentaJpaController(EntityManager em) {
        this.em = em;
    }
    private EntityManager em;

    public EntityManager getEntityManager() {
        return this.em;
    }

    public void create(Cuenta cuenta) {
        if (cuenta.getBienCollection() == null) {
            cuenta.setBienCollection(new ArrayList<Bien>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Bien> attachedBienCollection = new ArrayList<Bien>();
            for (Bien bienCollectionBienToAttach : cuenta.getBienCollection()) {
                bienCollectionBienToAttach = em.getReference(bienCollectionBienToAttach.getClass(), bienCollectionBienToAttach.getId());
                attachedBienCollection.add(bienCollectionBienToAttach);
            }
            cuenta.setBienCollection(attachedBienCollection);
            em.persist(cuenta);
            for (Bien bienCollectionBien : cuenta.getBienCollection()) {
                Cuenta oldCuentaIdOfBienCollectionBien = bienCollectionBien.getCuentaId();
                bienCollectionBien.setCuentaId(cuenta);
                bienCollectionBien = em.merge(bienCollectionBien);
                if (oldCuentaIdOfBienCollectionBien != null) {
                    oldCuentaIdOfBienCollectionBien.getBienCollection().remove(bienCollectionBien);
                    oldCuentaIdOfBienCollectionBien = em.merge(oldCuentaIdOfBienCollectionBien);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cuenta cuenta) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cuenta persistentCuenta = em.find(Cuenta.class, cuenta.getId());
            Collection<Bien> bienCollectionOld = persistentCuenta.getBienCollection();
            Collection<Bien> bienCollectionNew = cuenta.getBienCollection();
            Collection<Bien> attachedBienCollectionNew = new ArrayList<Bien>();
            for (Bien bienCollectionNewBienToAttach : bienCollectionNew) {
                bienCollectionNewBienToAttach = em.getReference(bienCollectionNewBienToAttach.getClass(), bienCollectionNewBienToAttach.getId());
                attachedBienCollectionNew.add(bienCollectionNewBienToAttach);
            }
            bienCollectionNew = attachedBienCollectionNew;
            cuenta.setBienCollection(bienCollectionNew);
            cuenta = em.merge(cuenta);
            for (Bien bienCollectionOldBien : bienCollectionOld) {
                if (!bienCollectionNew.contains(bienCollectionOldBien)) {
                    bienCollectionOldBien.setCuentaId(null);
                    bienCollectionOldBien = em.merge(bienCollectionOldBien);
                }
            }
            for (Bien bienCollectionNewBien : bienCollectionNew) {
                if (!bienCollectionOld.contains(bienCollectionNewBien)) {
                    Cuenta oldCuentaIdOfBienCollectionNewBien = bienCollectionNewBien.getCuentaId();
                    bienCollectionNewBien.setCuentaId(cuenta);
                    bienCollectionNewBien = em.merge(bienCollectionNewBien);
                    if (oldCuentaIdOfBienCollectionNewBien != null && !oldCuentaIdOfBienCollectionNewBien.equals(cuenta)) {
                        oldCuentaIdOfBienCollectionNewBien.getBienCollection().remove(bienCollectionNewBien);
                        oldCuentaIdOfBienCollectionNewBien = em.merge(oldCuentaIdOfBienCollectionNewBien);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = cuenta.getId();
                if (findCuenta(id) == null) {
                    throw new NonexistentEntityException("The cuenta with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cuenta cuenta;
            try {
                cuenta = em.getReference(Cuenta.class, id);
                cuenta.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cuenta with id " + id + " no longer exists.", enfe);
            }
            Collection<Bien> bienCollection = cuenta.getBienCollection();
            for (Bien bienCollectionBien : bienCollection) {
                bienCollectionBien.setCuentaId(null);
                bienCollectionBien = em.merge(bienCollectionBien);
            }
            em.remove(cuenta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cuenta> findCuentaEntities() {
        return findCuentaEntities(true, -1, -1);
    }

    public List<Cuenta> findCuentaEntities(int maxResults, int firstResult) {
        return findCuentaEntities(false, maxResults, firstResult);
    }

    private List<Cuenta> findCuentaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cuenta.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Cuenta findCuenta(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cuenta.class, id);
        } finally {
            em.close();
        }
    }
    
    public Cuenta findCuenta(String nombre)
    {
        EntityManager em = getEntityManager();
        try
        {
            TypedQuery<Cuenta> query = em.createNamedQuery("Cuenta.findByNombre", Cuenta.class);
            query.setParameter("nombre", nombre);
            List<Cuenta> arreglo = query.getResultList();
            return arreglo.remove(0);
        }finally{
            em.close();
        }
    }

    public int getCuentaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cuenta> rt = cq.from(Cuenta.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
