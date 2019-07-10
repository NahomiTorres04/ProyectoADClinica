/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import controlador.exceptions.IllegalOrphanException;
import controlador.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entidades.Bien;
import entidades.Departamento;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

/**
 *
 * @author Alex de León Véliz <alexdlveliz@hotmail.com>
 */
public class DepartamentoJpaController implements Serializable {

    public DepartamentoJpaController(EntityManager em) {
        this.em = em;
    }
    private EntityManager em = null;

    public EntityManager getEntityManager() {
        return this.em;
    }

    public void create(Departamento departamento) {
        if (departamento.getBienCollection() == null) {
            departamento.setBienCollection(new ArrayList<Bien>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Bien> attachedBienCollection = new ArrayList<Bien>();
            for (Bien bienCollectionBienToAttach : departamento.getBienCollection()) {
                bienCollectionBienToAttach = em.getReference(bienCollectionBienToAttach.getClass(), bienCollectionBienToAttach.getId());
                attachedBienCollection.add(bienCollectionBienToAttach);
            }
            departamento.setBienCollection(attachedBienCollection);
            em.persist(departamento);
            for (Bien bienCollectionBien : departamento.getBienCollection()) {
                Departamento oldDepartamentoIdOfBienCollectionBien = bienCollectionBien.getDepartamentoId();
                bienCollectionBien.setDepartamentoId(departamento);
                bienCollectionBien = em.merge(bienCollectionBien);
                if (oldDepartamentoIdOfBienCollectionBien != null) {
                    oldDepartamentoIdOfBienCollectionBien.getBienCollection().remove(bienCollectionBien);
                    oldDepartamentoIdOfBienCollectionBien = em.merge(oldDepartamentoIdOfBienCollectionBien);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Departamento departamento) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Departamento persistentDepartamento = em.find(Departamento.class, departamento.getId());
            Collection<Bien> bienCollectionOld = persistentDepartamento.getBienCollection();
            Collection<Bien> bienCollectionNew = departamento.getBienCollection();
            List<String> illegalOrphanMessages = null;
            for (Bien bienCollectionOldBien : bienCollectionOld) {
                if (!bienCollectionNew.contains(bienCollectionOldBien)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Bien " + bienCollectionOldBien + " since its departamentoId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Bien> attachedBienCollectionNew = new ArrayList<Bien>();
            for (Bien bienCollectionNewBienToAttach : bienCollectionNew) {
                bienCollectionNewBienToAttach = em.getReference(bienCollectionNewBienToAttach.getClass(), bienCollectionNewBienToAttach.getId());
                attachedBienCollectionNew.add(bienCollectionNewBienToAttach);
            }
            bienCollectionNew = attachedBienCollectionNew;
            departamento.setBienCollection(bienCollectionNew);
            departamento = em.merge(departamento);
            for (Bien bienCollectionNewBien : bienCollectionNew) {
                if (!bienCollectionOld.contains(bienCollectionNewBien)) {
                    Departamento oldDepartamentoIdOfBienCollectionNewBien = bienCollectionNewBien.getDepartamentoId();
                    bienCollectionNewBien.setDepartamentoId(departamento);
                    bienCollectionNewBien = em.merge(bienCollectionNewBien);
                    if (oldDepartamentoIdOfBienCollectionNewBien != null && !oldDepartamentoIdOfBienCollectionNewBien.equals(departamento)) {
                        oldDepartamentoIdOfBienCollectionNewBien.getBienCollection().remove(bienCollectionNewBien);
                        oldDepartamentoIdOfBienCollectionNewBien = em.merge(oldDepartamentoIdOfBienCollectionNewBien);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = departamento.getId();
                if (findDepartamento(id) == null) {
                    throw new NonexistentEntityException("The departamento with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Departamento departamento;
            try {
                departamento = em.getReference(Departamento.class, id);
                departamento.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The departamento with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Bien> bienCollectionOrphanCheck = departamento.getBienCollection();
            for (Bien bienCollectionOrphanCheckBien : bienCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Departamento (" + departamento + ") cannot be destroyed since the Bien " + bienCollectionOrphanCheckBien + " in its bienCollection field has a non-nullable departamentoId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(departamento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Departamento> findDepartamentoEntities() {
        return findDepartamentoEntities(true, -1, -1);
    }
    
    public Departamento findeDepartamento(String descripcion)
    {
        EntityManager em = getEntityManager();
        try
        {
        TypedQuery<Departamento> query = em.createNamedQuery("Departamento.findByDescripcion", Departamento.class);
        query.setParameter("descripcion", descripcion);
        List<Departamento> arreglo = query.getResultList();
        return arreglo.remove(0);
        }finally{
            em.close();
        }
    }

    public List<Departamento> findDepartamentoEntities(int maxResults, int firstResult) {
        return findDepartamentoEntities(false, maxResults, firstResult);
    }

    private List<Departamento> findDepartamentoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Departamento.class));
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

    public Departamento findDepartamento(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Departamento.class, id);
        } finally {
            em.close();
        }
    }

    public int getDepartamentoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Departamento> rt = cq.from(Departamento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
