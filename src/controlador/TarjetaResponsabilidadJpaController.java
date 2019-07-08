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
import vista.Empleado;
import vista.Contenido;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import vista.TarjetaResponsabilidad;

/**
 *
 * @author Alex de León Véliz <alexdlveliz@hotmail.com>
 */
public class TarjetaResponsabilidadJpaController implements Serializable {

    public TarjetaResponsabilidadJpaController(EntityManager em) {
        this.em = em;
    }
    private EntityManager em;

    public EntityManager getEntityManager() {
        return this.em;
    }

    public void create(TarjetaResponsabilidad tarjetaResponsabilidad) {
        if (tarjetaResponsabilidad.getContenidoCollection() == null) {
            tarjetaResponsabilidad.setContenidoCollection(new ArrayList<Contenido>());
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
            Collection<Contenido> attachedContenidoCollection = new ArrayList<Contenido>();
            for (Contenido contenidoCollectionContenidoToAttach : tarjetaResponsabilidad.getContenidoCollection()) {
                contenidoCollectionContenidoToAttach = em.getReference(contenidoCollectionContenidoToAttach.getClass(), contenidoCollectionContenidoToAttach.getId());
                attachedContenidoCollection.add(contenidoCollectionContenidoToAttach);
            }
            tarjetaResponsabilidad.setContenidoCollection(attachedContenidoCollection);
            em.persist(tarjetaResponsabilidad);
            if (empleadoId != null) {
                empleadoId.getTarjetaResponsabilidadCollection().add(tarjetaResponsabilidad);
                empleadoId = em.merge(empleadoId);
            }
            for (Contenido contenidoCollectionContenido : tarjetaResponsabilidad.getContenidoCollection()) {
                TarjetaResponsabilidad oldTarjetaResponsabilidadIdOfContenidoCollectionContenido = contenidoCollectionContenido.getTarjetaResponsabilidadId();
                contenidoCollectionContenido.setTarjetaResponsabilidadId(tarjetaResponsabilidad);
                contenidoCollectionContenido = em.merge(contenidoCollectionContenido);
                if (oldTarjetaResponsabilidadIdOfContenidoCollectionContenido != null) {
                    oldTarjetaResponsabilidadIdOfContenidoCollectionContenido.getContenidoCollection().remove(contenidoCollectionContenido);
                    oldTarjetaResponsabilidadIdOfContenidoCollectionContenido = em.merge(oldTarjetaResponsabilidadIdOfContenidoCollectionContenido);
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
            Collection<Contenido> contenidoCollectionOld = persistentTarjetaResponsabilidad.getContenidoCollection();
            Collection<Contenido> contenidoCollectionNew = tarjetaResponsabilidad.getContenidoCollection();
            if (empleadoIdNew != null) {
                empleadoIdNew = em.getReference(empleadoIdNew.getClass(), empleadoIdNew.getId());
                tarjetaResponsabilidad.setEmpleadoId(empleadoIdNew);
            }
            Collection<Contenido> attachedContenidoCollectionNew = new ArrayList<Contenido>();
            for (Contenido contenidoCollectionNewContenidoToAttach : contenidoCollectionNew) {
                contenidoCollectionNewContenidoToAttach = em.getReference(contenidoCollectionNewContenidoToAttach.getClass(), contenidoCollectionNewContenidoToAttach.getId());
                attachedContenidoCollectionNew.add(contenidoCollectionNewContenidoToAttach);
            }
            contenidoCollectionNew = attachedContenidoCollectionNew;
            tarjetaResponsabilidad.setContenidoCollection(contenidoCollectionNew);
            tarjetaResponsabilidad = em.merge(tarjetaResponsabilidad);
            if (empleadoIdOld != null && !empleadoIdOld.equals(empleadoIdNew)) {
                empleadoIdOld.getTarjetaResponsabilidadCollection().remove(tarjetaResponsabilidad);
                empleadoIdOld = em.merge(empleadoIdOld);
            }
            if (empleadoIdNew != null && !empleadoIdNew.equals(empleadoIdOld)) {
                empleadoIdNew.getTarjetaResponsabilidadCollection().add(tarjetaResponsabilidad);
                empleadoIdNew = em.merge(empleadoIdNew);
            }
            for (Contenido contenidoCollectionOldContenido : contenidoCollectionOld) {
                if (!contenidoCollectionNew.contains(contenidoCollectionOldContenido)) {
                    contenidoCollectionOldContenido.setTarjetaResponsabilidadId(null);
                    contenidoCollectionOldContenido = em.merge(contenidoCollectionOldContenido);
                }
            }
            for (Contenido contenidoCollectionNewContenido : contenidoCollectionNew) {
                if (!contenidoCollectionOld.contains(contenidoCollectionNewContenido)) {
                    TarjetaResponsabilidad oldTarjetaResponsabilidadIdOfContenidoCollectionNewContenido = contenidoCollectionNewContenido.getTarjetaResponsabilidadId();
                    contenidoCollectionNewContenido.setTarjetaResponsabilidadId(tarjetaResponsabilidad);
                    contenidoCollectionNewContenido = em.merge(contenidoCollectionNewContenido);
                    if (oldTarjetaResponsabilidadIdOfContenidoCollectionNewContenido != null && !oldTarjetaResponsabilidadIdOfContenidoCollectionNewContenido.equals(tarjetaResponsabilidad)) {
                        oldTarjetaResponsabilidadIdOfContenidoCollectionNewContenido.getContenidoCollection().remove(contenidoCollectionNewContenido);
                        oldTarjetaResponsabilidadIdOfContenidoCollectionNewContenido = em.merge(oldTarjetaResponsabilidadIdOfContenidoCollectionNewContenido);
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
            Collection<Contenido> contenidoCollection = tarjetaResponsabilidad.getContenidoCollection();
            for (Contenido contenidoCollectionContenido : contenidoCollection) {
                contenidoCollectionContenido.setTarjetaResponsabilidadId(null);
                contenidoCollectionContenido = em.merge(contenidoCollectionContenido);
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
