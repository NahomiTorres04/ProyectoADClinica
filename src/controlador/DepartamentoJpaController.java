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
import vista.Contenido;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import vista.Bien;
import vista.Departamento;

/**
 *
 * @author Alex de León Véliz <alexdlveliz@hotmail.com>
 */
public class DepartamentoJpaController implements Serializable {

    public DepartamentoJpaController(EntityManager em) {
        this.em = em;
    }
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoAD");
    private EntityManager em = emf.createEntityManager();
    private static DepartamentoJpaController controladorDepartamento = null;

    public EntityManager getEntityManager() {
        return this.em;
    }

    private DepartamentoJpaController()
    {}
    
    public static DepartamentoJpaController getInstancia()
    {
        if(controladorDepartamento == null) controladorDepartamento = new DepartamentoJpaController();
        return controladorDepartamento;
    }

    public void create(Departamento departamento) {
        if (departamento.getContenidoCollection() == null) {
            departamento.setContenidoCollection(new ArrayList<Contenido>());
        }
        if (departamento.getBienCollection() == null) {
            departamento.setBienCollection(new ArrayList<Bien>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Contenido> attachedContenidoCollection = new ArrayList<Contenido>();
            for (Contenido contenidoCollectionContenidoToAttach : departamento.getContenidoCollection()) {
                contenidoCollectionContenidoToAttach = em.getReference(contenidoCollectionContenidoToAttach.getClass(), contenidoCollectionContenidoToAttach.getId());
                attachedContenidoCollection.add(contenidoCollectionContenidoToAttach);
            }
            departamento.setContenidoCollection(attachedContenidoCollection);
            Collection<Bien> attachedBienCollection = new ArrayList<Bien>();
            for (Bien bienCollectionBienToAttach : departamento.getBienCollection()) {
                bienCollectionBienToAttach = em.getReference(bienCollectionBienToAttach.getClass(), bienCollectionBienToAttach.getId());
                attachedBienCollection.add(bienCollectionBienToAttach);
            }
            departamento.setBienCollection(attachedBienCollection);
            em.persist(departamento);
            for (Contenido contenidoCollectionContenido : departamento.getContenidoCollection()) {
                Departamento oldDepartamentoIdOfContenidoCollectionContenido = contenidoCollectionContenido.getDepartamentoId();
                contenidoCollectionContenido.setDepartamentoId(departamento);
                contenidoCollectionContenido = em.merge(contenidoCollectionContenido);
                if (oldDepartamentoIdOfContenidoCollectionContenido != null) {
                    oldDepartamentoIdOfContenidoCollectionContenido.getContenidoCollection().remove(contenidoCollectionContenido);
                    oldDepartamentoIdOfContenidoCollectionContenido = em.merge(oldDepartamentoIdOfContenidoCollectionContenido);
                }
            }
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
            Collection<Contenido> contenidoCollectionOld = persistentDepartamento.getContenidoCollection();
            Collection<Contenido> contenidoCollectionNew = departamento.getContenidoCollection();
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
            Collection<Contenido> attachedContenidoCollectionNew = new ArrayList<Contenido>();
            for (Contenido contenidoCollectionNewContenidoToAttach : contenidoCollectionNew) {
                contenidoCollectionNewContenidoToAttach = em.getReference(contenidoCollectionNewContenidoToAttach.getClass(), contenidoCollectionNewContenidoToAttach.getId());
                attachedContenidoCollectionNew.add(contenidoCollectionNewContenidoToAttach);
            }
            contenidoCollectionNew = attachedContenidoCollectionNew;
            departamento.setContenidoCollection(contenidoCollectionNew);
            Collection<Bien> attachedBienCollectionNew = new ArrayList<Bien>();
            for (Bien bienCollectionNewBienToAttach : bienCollectionNew) {
                bienCollectionNewBienToAttach = em.getReference(bienCollectionNewBienToAttach.getClass(), bienCollectionNewBienToAttach.getId());
                attachedBienCollectionNew.add(bienCollectionNewBienToAttach);
            }
            bienCollectionNew = attachedBienCollectionNew;
            departamento.setBienCollection(bienCollectionNew);
            departamento = em.merge(departamento);
            for (Contenido contenidoCollectionOldContenido : contenidoCollectionOld) {
                if (!contenidoCollectionNew.contains(contenidoCollectionOldContenido)) {
                    contenidoCollectionOldContenido.setDepartamentoId(null);
                    contenidoCollectionOldContenido = em.merge(contenidoCollectionOldContenido);
                }
            }
            for (Contenido contenidoCollectionNewContenido : contenidoCollectionNew) {
                if (!contenidoCollectionOld.contains(contenidoCollectionNewContenido)) {
                    Departamento oldDepartamentoIdOfContenidoCollectionNewContenido = contenidoCollectionNewContenido.getDepartamentoId();
                    contenidoCollectionNewContenido.setDepartamentoId(departamento);
                    contenidoCollectionNewContenido = em.merge(contenidoCollectionNewContenido);
                    if (oldDepartamentoIdOfContenidoCollectionNewContenido != null && !oldDepartamentoIdOfContenidoCollectionNewContenido.equals(departamento)) {
                        oldDepartamentoIdOfContenidoCollectionNewContenido.getContenidoCollection().remove(contenidoCollectionNewContenido);
                        oldDepartamentoIdOfContenidoCollectionNewContenido = em.merge(oldDepartamentoIdOfContenidoCollectionNewContenido);
                    }
                }
            }
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
            Collection<Contenido> contenidoCollection = departamento.getContenidoCollection();
            for (Contenido contenidoCollectionContenido : contenidoCollection) {
                contenidoCollectionContenido.setDepartamentoId(null);
                contenidoCollectionContenido = em.merge(contenidoCollectionContenido);
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
