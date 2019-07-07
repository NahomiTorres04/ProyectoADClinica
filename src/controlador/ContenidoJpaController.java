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
import vista.Departamento;
import vista.TarjetaResponsabilidad;
import vista.Bien;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import vista.Contenido;

/**
 *
 * @author Alex de León Véliz <alexdlveliz@hotmail.com>
 */
public class ContenidoJpaController implements Serializable {

    public ContenidoJpaController(EntityManager em) {
        this.em = em;
    }
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoAD");
    private EntityManager em = emf.createEntityManager();
    private static ContenidoJpaController controladorContenido = null;

    public EntityManager getEntityManager() {
        return this.em;
    }
    
    private ContenidoJpaController()
    {}
    
    public static ContenidoJpaController getInstancia()
    {
        if(controladorContenido == null) controladorContenido = new ContenidoJpaController();
        return controladorContenido;
    }

    public void create(Contenido contenido) {
        if (contenido.getBienCollection() == null) {
            contenido.setBienCollection(new ArrayList<Bien>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Departamento departamentoId = contenido.getDepartamentoId();
            if (departamentoId != null) {
                departamentoId = em.getReference(departamentoId.getClass(), departamentoId.getId());
                contenido.setDepartamentoId(departamentoId);
            }
            TarjetaResponsabilidad tarjetaResponsabilidadId = contenido.getTarjetaResponsabilidadId();
            if (tarjetaResponsabilidadId != null) {
                tarjetaResponsabilidadId = em.getReference(tarjetaResponsabilidadId.getClass(), tarjetaResponsabilidadId.getId());
                contenido.setTarjetaResponsabilidadId(tarjetaResponsabilidadId);
            }
            Collection<Bien> attachedBienCollection = new ArrayList<Bien>();
            for (Bien bienCollectionBienToAttach : contenido.getBienCollection()) {
                bienCollectionBienToAttach = em.getReference(bienCollectionBienToAttach.getClass(), bienCollectionBienToAttach.getId());
                attachedBienCollection.add(bienCollectionBienToAttach);
            }
            contenido.setBienCollection(attachedBienCollection);
            em.persist(contenido);
            if (departamentoId != null) {
                departamentoId.getContenidoCollection().add(contenido);
                departamentoId = em.merge(departamentoId);
            }
            if (tarjetaResponsabilidadId != null) {
                tarjetaResponsabilidadId.getContenidoCollection().add(contenido);
                tarjetaResponsabilidadId = em.merge(tarjetaResponsabilidadId);
            }
            for (Bien bienCollectionBien : contenido.getBienCollection()) {
                Contenido oldContenidoIdOfBienCollectionBien = bienCollectionBien.getContenidoId();
                bienCollectionBien.setContenidoId(contenido);
                bienCollectionBien = em.merge(bienCollectionBien);
                if (oldContenidoIdOfBienCollectionBien != null) {
                    oldContenidoIdOfBienCollectionBien.getBienCollection().remove(bienCollectionBien);
                    oldContenidoIdOfBienCollectionBien = em.merge(oldContenidoIdOfBienCollectionBien);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Contenido contenido) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Contenido persistentContenido = em.find(Contenido.class, contenido.getId());
            Departamento departamentoIdOld = persistentContenido.getDepartamentoId();
            Departamento departamentoIdNew = contenido.getDepartamentoId();
            TarjetaResponsabilidad tarjetaResponsabilidadIdOld = persistentContenido.getTarjetaResponsabilidadId();
            TarjetaResponsabilidad tarjetaResponsabilidadIdNew = contenido.getTarjetaResponsabilidadId();
            Collection<Bien> bienCollectionOld = persistentContenido.getBienCollection();
            Collection<Bien> bienCollectionNew = contenido.getBienCollection();
            if (departamentoIdNew != null) {
                departamentoIdNew = em.getReference(departamentoIdNew.getClass(), departamentoIdNew.getId());
                contenido.setDepartamentoId(departamentoIdNew);
            }
            if (tarjetaResponsabilidadIdNew != null) {
                tarjetaResponsabilidadIdNew = em.getReference(tarjetaResponsabilidadIdNew.getClass(), tarjetaResponsabilidadIdNew.getId());
                contenido.setTarjetaResponsabilidadId(tarjetaResponsabilidadIdNew);
            }
            Collection<Bien> attachedBienCollectionNew = new ArrayList<Bien>();
            for (Bien bienCollectionNewBienToAttach : bienCollectionNew) {
                bienCollectionNewBienToAttach = em.getReference(bienCollectionNewBienToAttach.getClass(), bienCollectionNewBienToAttach.getId());
                attachedBienCollectionNew.add(bienCollectionNewBienToAttach);
            }
            bienCollectionNew = attachedBienCollectionNew;
            contenido.setBienCollection(bienCollectionNew);
            contenido = em.merge(contenido);
            if (departamentoIdOld != null && !departamentoIdOld.equals(departamentoIdNew)) {
                departamentoIdOld.getContenidoCollection().remove(contenido);
                departamentoIdOld = em.merge(departamentoIdOld);
            }
            if (departamentoIdNew != null && !departamentoIdNew.equals(departamentoIdOld)) {
                departamentoIdNew.getContenidoCollection().add(contenido);
                departamentoIdNew = em.merge(departamentoIdNew);
            }
            if (tarjetaResponsabilidadIdOld != null && !tarjetaResponsabilidadIdOld.equals(tarjetaResponsabilidadIdNew)) {
                tarjetaResponsabilidadIdOld.getContenidoCollection().remove(contenido);
                tarjetaResponsabilidadIdOld = em.merge(tarjetaResponsabilidadIdOld);
            }
            if (tarjetaResponsabilidadIdNew != null && !tarjetaResponsabilidadIdNew.equals(tarjetaResponsabilidadIdOld)) {
                tarjetaResponsabilidadIdNew.getContenidoCollection().add(contenido);
                tarjetaResponsabilidadIdNew = em.merge(tarjetaResponsabilidadIdNew);
            }
            for (Bien bienCollectionOldBien : bienCollectionOld) {
                if (!bienCollectionNew.contains(bienCollectionOldBien)) {
                    bienCollectionOldBien.setContenidoId(null);
                    bienCollectionOldBien = em.merge(bienCollectionOldBien);
                }
            }
            for (Bien bienCollectionNewBien : bienCollectionNew) {
                if (!bienCollectionOld.contains(bienCollectionNewBien)) {
                    Contenido oldContenidoIdOfBienCollectionNewBien = bienCollectionNewBien.getContenidoId();
                    bienCollectionNewBien.setContenidoId(contenido);
                    bienCollectionNewBien = em.merge(bienCollectionNewBien);
                    if (oldContenidoIdOfBienCollectionNewBien != null && !oldContenidoIdOfBienCollectionNewBien.equals(contenido)) {
                        oldContenidoIdOfBienCollectionNewBien.getBienCollection().remove(bienCollectionNewBien);
                        oldContenidoIdOfBienCollectionNewBien = em.merge(oldContenidoIdOfBienCollectionNewBien);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = contenido.getId();
                if (findContenido(id) == null) {
                    throw new NonexistentEntityException("The contenido with id " + id + " no longer exists.");
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
            Contenido contenido;
            try {
                contenido = em.getReference(Contenido.class, id);
                contenido.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The contenido with id " + id + " no longer exists.", enfe);
            }
            Departamento departamentoId = contenido.getDepartamentoId();
            if (departamentoId != null) {
                departamentoId.getContenidoCollection().remove(contenido);
                departamentoId = em.merge(departamentoId);
            }
            TarjetaResponsabilidad tarjetaResponsabilidadId = contenido.getTarjetaResponsabilidadId();
            if (tarjetaResponsabilidadId != null) {
                tarjetaResponsabilidadId.getContenidoCollection().remove(contenido);
                tarjetaResponsabilidadId = em.merge(tarjetaResponsabilidadId);
            }
            Collection<Bien> bienCollection = contenido.getBienCollection();
            for (Bien bienCollectionBien : bienCollection) {
                bienCollectionBien.setContenidoId(null);
                bienCollectionBien = em.merge(bienCollectionBien);
            }
            em.remove(contenido);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Contenido> findContenidoEntities() {
        return findContenidoEntities(true, -1, -1);
    }

    public List<Contenido> findContenidoEntities(int maxResults, int firstResult) {
        return findContenidoEntities(false, maxResults, firstResult);
    }

    private List<Contenido> findContenidoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Contenido.class));
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

    public Contenido findContenido(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Contenido.class, id);
        } finally {
            em.close();
        }
    }

    public int getContenidoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Contenido> rt = cq.from(Contenido.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
