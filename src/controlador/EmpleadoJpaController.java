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
import vista.TarjetaResponsabilidad;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import vista.Empleado;

/**
 *
 * @author Alex de León Véliz <alexdlveliz@hotmail.com>
 */
public class EmpleadoJpaController implements Serializable {

    public EmpleadoJpaController(EntityManager em) {
        this.em = em;
    }
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoAD");
    private EntityManager em = emf.createEntityManager();
    private static EmpleadoJpaController controladorEmpleado = null;

    public EntityManager getEntityManager() {
        return this.em;
    }

    private EmpleadoJpaController()
    {}
    
    public static EmpleadoJpaController getInstancia()
    {
        if(controladorEmpleado == null) controladorEmpleado = new EmpleadoJpaController();
        return controladorEmpleado;
    }

    public void create(Empleado empleado) {
        if (empleado.getTarjetaResponsabilidadCollection() == null) {
            empleado.setTarjetaResponsabilidadCollection(new ArrayList<TarjetaResponsabilidad>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<TarjetaResponsabilidad> attachedTarjetaResponsabilidadCollection = new ArrayList<TarjetaResponsabilidad>();
            for (TarjetaResponsabilidad tarjetaResponsabilidadCollectionTarjetaResponsabilidadToAttach : empleado.getTarjetaResponsabilidadCollection()) {
                tarjetaResponsabilidadCollectionTarjetaResponsabilidadToAttach = em.getReference(tarjetaResponsabilidadCollectionTarjetaResponsabilidadToAttach.getClass(), tarjetaResponsabilidadCollectionTarjetaResponsabilidadToAttach.getId());
                attachedTarjetaResponsabilidadCollection.add(tarjetaResponsabilidadCollectionTarjetaResponsabilidadToAttach);
            }
            empleado.setTarjetaResponsabilidadCollection(attachedTarjetaResponsabilidadCollection);
            em.persist(empleado);
            for (TarjetaResponsabilidad tarjetaResponsabilidadCollectionTarjetaResponsabilidad : empleado.getTarjetaResponsabilidadCollection()) {
                Empleado oldEmpleadoIdOfTarjetaResponsabilidadCollectionTarjetaResponsabilidad = tarjetaResponsabilidadCollectionTarjetaResponsabilidad.getEmpleadoId();
                tarjetaResponsabilidadCollectionTarjetaResponsabilidad.setEmpleadoId(empleado);
                tarjetaResponsabilidadCollectionTarjetaResponsabilidad = em.merge(tarjetaResponsabilidadCollectionTarjetaResponsabilidad);
                if (oldEmpleadoIdOfTarjetaResponsabilidadCollectionTarjetaResponsabilidad != null) {
                    oldEmpleadoIdOfTarjetaResponsabilidadCollectionTarjetaResponsabilidad.getTarjetaResponsabilidadCollection().remove(tarjetaResponsabilidadCollectionTarjetaResponsabilidad);
                    oldEmpleadoIdOfTarjetaResponsabilidadCollectionTarjetaResponsabilidad = em.merge(oldEmpleadoIdOfTarjetaResponsabilidadCollectionTarjetaResponsabilidad);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Empleado empleado) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Empleado persistentEmpleado = em.find(Empleado.class, empleado.getId());
            Collection<TarjetaResponsabilidad> tarjetaResponsabilidadCollectionOld = persistentEmpleado.getTarjetaResponsabilidadCollection();
            Collection<TarjetaResponsabilidad> tarjetaResponsabilidadCollectionNew = empleado.getTarjetaResponsabilidadCollection();
            List<String> illegalOrphanMessages = null;
            for (TarjetaResponsabilidad tarjetaResponsabilidadCollectionOldTarjetaResponsabilidad : tarjetaResponsabilidadCollectionOld) {
                if (!tarjetaResponsabilidadCollectionNew.contains(tarjetaResponsabilidadCollectionOldTarjetaResponsabilidad)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain TarjetaResponsabilidad " + tarjetaResponsabilidadCollectionOldTarjetaResponsabilidad + " since its empleadoId field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<TarjetaResponsabilidad> attachedTarjetaResponsabilidadCollectionNew = new ArrayList<TarjetaResponsabilidad>();
            for (TarjetaResponsabilidad tarjetaResponsabilidadCollectionNewTarjetaResponsabilidadToAttach : tarjetaResponsabilidadCollectionNew) {
                tarjetaResponsabilidadCollectionNewTarjetaResponsabilidadToAttach = em.getReference(tarjetaResponsabilidadCollectionNewTarjetaResponsabilidadToAttach.getClass(), tarjetaResponsabilidadCollectionNewTarjetaResponsabilidadToAttach.getId());
                attachedTarjetaResponsabilidadCollectionNew.add(tarjetaResponsabilidadCollectionNewTarjetaResponsabilidadToAttach);
            }
            tarjetaResponsabilidadCollectionNew = attachedTarjetaResponsabilidadCollectionNew;
            empleado.setTarjetaResponsabilidadCollection(tarjetaResponsabilidadCollectionNew);
            empleado = em.merge(empleado);
            for (TarjetaResponsabilidad tarjetaResponsabilidadCollectionNewTarjetaResponsabilidad : tarjetaResponsabilidadCollectionNew) {
                if (!tarjetaResponsabilidadCollectionOld.contains(tarjetaResponsabilidadCollectionNewTarjetaResponsabilidad)) {
                    Empleado oldEmpleadoIdOfTarjetaResponsabilidadCollectionNewTarjetaResponsabilidad = tarjetaResponsabilidadCollectionNewTarjetaResponsabilidad.getEmpleadoId();
                    tarjetaResponsabilidadCollectionNewTarjetaResponsabilidad.setEmpleadoId(empleado);
                    tarjetaResponsabilidadCollectionNewTarjetaResponsabilidad = em.merge(tarjetaResponsabilidadCollectionNewTarjetaResponsabilidad);
                    if (oldEmpleadoIdOfTarjetaResponsabilidadCollectionNewTarjetaResponsabilidad != null && !oldEmpleadoIdOfTarjetaResponsabilidadCollectionNewTarjetaResponsabilidad.equals(empleado)) {
                        oldEmpleadoIdOfTarjetaResponsabilidadCollectionNewTarjetaResponsabilidad.getTarjetaResponsabilidadCollection().remove(tarjetaResponsabilidadCollectionNewTarjetaResponsabilidad);
                        oldEmpleadoIdOfTarjetaResponsabilidadCollectionNewTarjetaResponsabilidad = em.merge(oldEmpleadoIdOfTarjetaResponsabilidadCollectionNewTarjetaResponsabilidad);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = empleado.getId();
                if (findEmpleado(id) == null) {
                    throw new NonexistentEntityException("The empleado with id " + id + " no longer exists.");
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
            Empleado empleado;
            try {
                empleado = em.getReference(Empleado.class, id);
                empleado.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The empleado with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<TarjetaResponsabilidad> tarjetaResponsabilidadCollectionOrphanCheck = empleado.getTarjetaResponsabilidadCollection();
            for (TarjetaResponsabilidad tarjetaResponsabilidadCollectionOrphanCheckTarjetaResponsabilidad : tarjetaResponsabilidadCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Empleado (" + empleado + ") cannot be destroyed since the TarjetaResponsabilidad " + tarjetaResponsabilidadCollectionOrphanCheckTarjetaResponsabilidad + " in its tarjetaResponsabilidadCollection field has a non-nullable empleadoId field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(empleado);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Empleado> findEmpleadoEntities() {
        return findEmpleadoEntities(true, -1, -1);
    }

    public List<Empleado> findEmpleadoEntities(int maxResults, int firstResult) {
        return findEmpleadoEntities(false, maxResults, firstResult);
    }

    private List<Empleado> findEmpleadoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Empleado.class));
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

    public Empleado findEmpleado(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Empleado.class, id);
        } finally {
            em.close();
        }
    }

    public int getEmpleadoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Empleado> rt = cq.from(Empleado.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
