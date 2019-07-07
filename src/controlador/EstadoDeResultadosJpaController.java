/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import controlador.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import vista.EstadoDeResultados;

/**
 *
 * @author Alex de León Véliz <alexdlveliz@hotmail.com>
 */
public class EstadoDeResultadosJpaController implements Serializable {

    public EstadoDeResultadosJpaController(EntityManager em) {
        this.em = em;
    }
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoAD");
    private EntityManager em = emf.createEntityManager();
    private static EstadoDeResultadosJpaController controladorEstadoResultados = null;

    public EntityManager getEntityManager() {
        return this.em;
    }

    private EstadoDeResultadosJpaController()
    {}
    
    public static EstadoDeResultadosJpaController getInstancia()
    {
        if(controladorEstadoResultados == null) controladorEstadoResultados = new EstadoDeResultadosJpaController();
        return controladorEstadoResultados;
    }

    public void create(EstadoDeResultados estadoDeResultados) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(estadoDeResultados);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EstadoDeResultados estadoDeResultados) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            estadoDeResultados = em.merge(estadoDeResultados);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = estadoDeResultados.getId();
                if (findEstadoDeResultados(id) == null) {
                    throw new NonexistentEntityException("The estadoDeResultados with id " + id + " no longer exists.");
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
            EstadoDeResultados estadoDeResultados;
            try {
                estadoDeResultados = em.getReference(EstadoDeResultados.class, id);
                estadoDeResultados.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The estadoDeResultados with id " + id + " no longer exists.", enfe);
            }
            em.remove(estadoDeResultados);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<EstadoDeResultados> findEstadoDeResultadosEntities() {
        return findEstadoDeResultadosEntities(true, -1, -1);
    }

    public List<EstadoDeResultados> findEstadoDeResultadosEntities(int maxResults, int firstResult) {
        return findEstadoDeResultadosEntities(false, maxResults, firstResult);
    }

    private List<EstadoDeResultados> findEstadoDeResultadosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EstadoDeResultados.class));
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

    public EstadoDeResultados findEstadoDeResultados(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EstadoDeResultados.class, id);
        } finally {
            em.close();
        }
    }

    public int getEstadoDeResultadosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EstadoDeResultados> rt = cq.from(EstadoDeResultados.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
