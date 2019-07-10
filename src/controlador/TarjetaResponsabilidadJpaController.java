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
import entidades.Empleado;
import entidades.Bien;
import entidades.TarjetaResponsabilidad;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;

/**
 *
 * @author Alex de León Véliz <alexdlveliz@hotmail.com>
 */
public class TarjetaResponsabilidadJpaController implements Serializable {

    public TarjetaResponsabilidadJpaController(EntityManager em) {
        this.em = em;
    }
    private EntityManager em = null;

    public EntityManager getEntityManager() {
        return this.em;
    }

    public void create(TarjetaResponsabilidad tarjetaResponsabilidad) {
        if (tarjetaResponsabilidad.getBienCollection() == null) {
            tarjetaResponsabilidad.setBienCollection(new ArrayList<Bien>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empleado empleadoId = tarjetaResponsabilidad.getEmpleadoId();
            if (empleadoId != null) {
                empleadoId = em.getReference(empleadoId.getClass(), empleadoId.getId());
                tarjetaResponsabilidad.setEmpleadoId(empleadoId);
            }
            Collection<Bien> attachedBienCollection = new ArrayList<Bien>();
            for (Bien bienCollectionBienToAttach : tarjetaResponsabilidad.getBienCollection()) {
                bienCollectionBienToAttach = em.getReference(bienCollectionBienToAttach.getClass(), bienCollectionBienToAttach.getId());
                attachedBienCollection.add(bienCollectionBienToAttach);
            }
            tarjetaResponsabilidad.setBienCollection(attachedBienCollection);
            em.persist(tarjetaResponsabilidad);
            if (empleadoId != null) {
                empleadoId.getTarjetaResponsabilidadCollection().add(tarjetaResponsabilidad);
                empleadoId = em.merge(empleadoId);
            }
            for (Bien bienCollectionBien : tarjetaResponsabilidad.getBienCollection()) {
                TarjetaResponsabilidad oldTarjetaResponsabilidadIdOfBienCollectionBien = bienCollectionBien.getTarjetaResponsabilidadId();
                bienCollectionBien.setTarjetaResponsabilidadId(tarjetaResponsabilidad);
                bienCollectionBien = em.merge(bienCollectionBien);
                if (oldTarjetaResponsabilidadIdOfBienCollectionBien != null) {
                    oldTarjetaResponsabilidadIdOfBienCollectionBien.getBienCollection().remove(bienCollectionBien);
                    oldTarjetaResponsabilidadIdOfBienCollectionBien = em.merge(oldTarjetaResponsabilidadIdOfBienCollectionBien);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TarjetaResponsabilidad tarjetaResponsabilidad) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TarjetaResponsabilidad persistentTarjetaResponsabilidad = em.find(TarjetaResponsabilidad.class, tarjetaResponsabilidad.getId());
            Empleado empleadoIdOld = persistentTarjetaResponsabilidad.getEmpleadoId();
            Empleado empleadoIdNew = tarjetaResponsabilidad.getEmpleadoId();
            Collection<Bien> bienCollectionOld = persistentTarjetaResponsabilidad.getBienCollection();
            Collection<Bien> bienCollectionNew = tarjetaResponsabilidad.getBienCollection();
            if (empleadoIdNew != null) {
                empleadoIdNew = em.getReference(empleadoIdNew.getClass(), empleadoIdNew.getId());
                tarjetaResponsabilidad.setEmpleadoId(empleadoIdNew);
            }
            Collection<Bien> attachedBienCollectionNew = new ArrayList<Bien>();
            for (Bien bienCollectionNewBienToAttach : bienCollectionNew) {
                bienCollectionNewBienToAttach = em.getReference(bienCollectionNewBienToAttach.getClass(), bienCollectionNewBienToAttach.getId());
                attachedBienCollectionNew.add(bienCollectionNewBienToAttach);
            }
            bienCollectionNew = attachedBienCollectionNew;
            tarjetaResponsabilidad.setBienCollection(bienCollectionNew);
            tarjetaResponsabilidad = em.merge(tarjetaResponsabilidad);
            if (empleadoIdOld != null && !empleadoIdOld.equals(empleadoIdNew)) {
                empleadoIdOld.getTarjetaResponsabilidadCollection().remove(tarjetaResponsabilidad);
                empleadoIdOld = em.merge(empleadoIdOld);
            }
            if (empleadoIdNew != null && !empleadoIdNew.equals(empleadoIdOld)) {
                empleadoIdNew.getTarjetaResponsabilidadCollection().add(tarjetaResponsabilidad);
                empleadoIdNew = em.merge(empleadoIdNew);
            }
            for (Bien bienCollectionOldBien : bienCollectionOld) {
                if (!bienCollectionNew.contains(bienCollectionOldBien)) {
                    bienCollectionOldBien.setTarjetaResponsabilidadId(null);
                    bienCollectionOldBien = em.merge(bienCollectionOldBien);
                }
            }
            for (Bien bienCollectionNewBien : bienCollectionNew) {
                if (!bienCollectionOld.contains(bienCollectionNewBien)) {
                    TarjetaResponsabilidad oldTarjetaResponsabilidadIdOfBienCollectionNewBien = bienCollectionNewBien.getTarjetaResponsabilidadId();
                    bienCollectionNewBien.setTarjetaResponsabilidadId(tarjetaResponsabilidad);
                    bienCollectionNewBien = em.merge(bienCollectionNewBien);
                    if (oldTarjetaResponsabilidadIdOfBienCollectionNewBien != null && !oldTarjetaResponsabilidadIdOfBienCollectionNewBien.equals(tarjetaResponsabilidad)) {
                        oldTarjetaResponsabilidadIdOfBienCollectionNewBien.getBienCollection().remove(bienCollectionNewBien);
                        oldTarjetaResponsabilidadIdOfBienCollectionNewBien = em.merge(oldTarjetaResponsabilidadIdOfBienCollectionNewBien);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tarjetaResponsabilidad.getId();
                if (findTarjetaResponsabilidad(id) == null) {
                    throw new NonexistentEntityException("The tarjetaResponsabilidad with id " + id + " no longer exists.");
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
            TarjetaResponsabilidad tarjetaResponsabilidad;
            try {
                tarjetaResponsabilidad = em.getReference(TarjetaResponsabilidad.class, id);
                tarjetaResponsabilidad.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tarjetaResponsabilidad with id " + id + " no longer exists.", enfe);
            }
            Empleado empleadoId = tarjetaResponsabilidad.getEmpleadoId();
            if (empleadoId != null) {
                empleadoId.getTarjetaResponsabilidadCollection().remove(tarjetaResponsabilidad);
                empleadoId = em.merge(empleadoId);
            }
            Collection<Bien> bienCollection = tarjetaResponsabilidad.getBienCollection();
            for (Bien bienCollectionBien : bienCollection) {
                bienCollectionBien.setTarjetaResponsabilidadId(null);
                bienCollectionBien = em.merge(bienCollectionBien);
            }
            em.remove(tarjetaResponsabilidad);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TarjetaResponsabilidad> findTarjetaResponsabilidadEntities() {
        return findTarjetaResponsabilidadEntities(true, -1, -1);
    }

    public List<TarjetaResponsabilidad> findTarjetaResponsabilidadEntities(int maxResults, int firstResult) {
        return findTarjetaResponsabilidadEntities(false, maxResults, firstResult);
    }

    private List<TarjetaResponsabilidad> findTarjetaResponsabilidadEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TarjetaResponsabilidad.class));
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

    public TarjetaResponsabilidad findTarjetaResponsabilidad(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TarjetaResponsabilidad.class, id);
        } finally {
            em.close();
        }
    }

    public int getTarjetaResponsabilidadCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TarjetaResponsabilidad> rt = cq.from(TarjetaResponsabilidad.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
