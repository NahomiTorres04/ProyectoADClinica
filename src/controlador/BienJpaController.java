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
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import vista.Bien;
import vista.Cuenta;
import vista.Contenido;
import vista.Departamento;

/**
 *
 * @author Alex de León Véliz <alexdlveliz@hotmail.com>
 */
public class BienJpaController implements Serializable {

    public BienJpaController(EntityManager em) {
        this.em = em;
    }
    private EntityManager em;

    public EntityManager getEntityManager() {
        return this.em;
    }

    public void create(Bien bien) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cuenta cuentaId = bien.getCuentaId();
            if (cuentaId != null) {
                cuentaId = em.getReference(cuentaId.getClass(), cuentaId.getId());
                bien.setCuentaId(cuentaId);
            }
            Contenido contenidoId = bien.getContenidoId();
            if (contenidoId != null) {
                contenidoId = em.getReference(contenidoId.getClass(), contenidoId.getId());
                bien.setContenidoId(contenidoId);
            }
            Departamento departamentoId = bien.getDepartamentoId();
            if (departamentoId != null) {
                departamentoId = em.getReference(departamentoId.getClass(), departamentoId.getId());
                bien.setDepartamentoId(departamentoId);
            }
            em.persist(bien);
            if (cuentaId != null) {
                cuentaId.getBienCollection().add(bien);
                cuentaId = em.merge(cuentaId);
            }
            if (contenidoId != null) {
                contenidoId.getBienCollection().add(bien);
                contenidoId = em.merge(contenidoId);
            }
            if (departamentoId != null) {
                departamentoId.getBienCollection().add(bien);
                departamentoId = em.merge(departamentoId);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Bien bien) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Bien persistentBien = em.find(Bien.class, bien.getId());
            Cuenta cuentaIdOld = persistentBien.getCuentaId();
            Cuenta cuentaIdNew = bien.getCuentaId();
            Contenido contenidoIdOld = persistentBien.getContenidoId();
            Contenido contenidoIdNew = bien.getContenidoId();
            Departamento departamentoIdOld = persistentBien.getDepartamentoId();
            Departamento departamentoIdNew = bien.getDepartamentoId();
            if (cuentaIdNew != null) {
                cuentaIdNew = em.getReference(cuentaIdNew.getClass(), cuentaIdNew.getId());
                bien.setCuentaId(cuentaIdNew);
            }
            if (contenidoIdNew != null) {
                contenidoIdNew = em.getReference(contenidoIdNew.getClass(), contenidoIdNew.getId());
                bien.setContenidoId(contenidoIdNew);
            }
            if (departamentoIdNew != null) {
                departamentoIdNew = em.getReference(departamentoIdNew.getClass(), departamentoIdNew.getId());
                bien.setDepartamentoId(departamentoIdNew);
            }
            bien = em.merge(bien);
            if (cuentaIdOld != null && !cuentaIdOld.equals(cuentaIdNew)) {
                cuentaIdOld.getBienCollection().remove(bien);
                cuentaIdOld = em.merge(cuentaIdOld);
            }
            if (cuentaIdNew != null && !cuentaIdNew.equals(cuentaIdOld)) {
                cuentaIdNew.getBienCollection().add(bien);
                cuentaIdNew = em.merge(cuentaIdNew);
            }
            if (contenidoIdOld != null && !contenidoIdOld.equals(contenidoIdNew)) {
                contenidoIdOld.getBienCollection().remove(bien);
                contenidoIdOld = em.merge(contenidoIdOld);
            }
            if (contenidoIdNew != null && !contenidoIdNew.equals(contenidoIdOld)) {
                contenidoIdNew.getBienCollection().add(bien);
                contenidoIdNew = em.merge(contenidoIdNew);
            }
            if (departamentoIdOld != null && !departamentoIdOld.equals(departamentoIdNew)) {
                departamentoIdOld.getBienCollection().remove(bien);
                departamentoIdOld = em.merge(departamentoIdOld);
            }
            if (departamentoIdNew != null && !departamentoIdNew.equals(departamentoIdOld)) {
                departamentoIdNew.getBienCollection().add(bien);
                departamentoIdNew = em.merge(departamentoIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = bien.getId();
                if (findBien(id) == null) {
                    throw new NonexistentEntityException("The bien with id " + id + " no longer exists.");
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
            Bien bien;
            try {
                bien = em.getReference(Bien.class, id);
                bien.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The bien with id " + id + " no longer exists.", enfe);
            }
            Cuenta cuentaId = bien.getCuentaId();
            if (cuentaId != null) {
                cuentaId.getBienCollection().remove(bien);
                cuentaId = em.merge(cuentaId);
            }
            Contenido contenidoId = bien.getContenidoId();
            if (contenidoId != null) {
                contenidoId.getBienCollection().remove(bien);
                contenidoId = em.merge(contenidoId);
            }
            Departamento departamentoId = bien.getDepartamentoId();
            if (departamentoId != null) {
                departamentoId.getBienCollection().remove(bien);
                departamentoId = em.merge(departamentoId);
            }
            em.remove(bien);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Bien> findBienEntities() {
        return findBienEntities(true, -1, -1);
    }

    public List<Bien> findBienEntities(int maxResults, int firstResult) {
        return findBienEntities(false, maxResults, firstResult);
    }

    private List<Bien> findBienEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Bien.class));
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

    public Bien findBien(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Bien.class, id);
        } finally {
            em.close();
        }
    }

    public int getBienCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Bien> rt = cq.from(Bien.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
