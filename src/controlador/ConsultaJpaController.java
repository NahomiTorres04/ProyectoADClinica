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
import vista.Consulta;
import vista.Paciente;

/**
 *
 * @author Alex de León Véliz <alexdlveliz@hotmail.com>
 */
public class ConsultaJpaController implements Serializable {

    public ConsultaJpaController(EntityManager em) {
        this.em = em;
    }
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoBD");
    private EntityManager em = emf.createEntityManager();
    private static ConsultaJpaController controladorConsulta = null;
    
    public EntityManager getEntityManager() {
        return this.em;
    }
    
    private ConsultaJpaController()
    {}
    
    public static ConsultaJpaController getInstancia()
    {
        if(controladorConsulta == null) controladorConsulta = new ConsultaJpaController();
        return controladorConsulta;
    }

    public void create(Consulta consulta) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Paciente pacienteId = consulta.getPacienteId();
            if (pacienteId != null) {
                pacienteId = em.getReference(pacienteId.getClass(), pacienteId.getId());
                consulta.setPacienteId(pacienteId);
            }
            em.persist(consulta);
            if (pacienteId != null) {
                pacienteId.getConsultaCollection().add(consulta);
                pacienteId = em.merge(pacienteId);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Consulta consulta) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Consulta persistentConsulta = em.find(Consulta.class, consulta.getId());
            Paciente pacienteIdOld = persistentConsulta.getPacienteId();
            Paciente pacienteIdNew = consulta.getPacienteId();
            if (pacienteIdNew != null) {
                pacienteIdNew = em.getReference(pacienteIdNew.getClass(), pacienteIdNew.getId());
                consulta.setPacienteId(pacienteIdNew);
            }
            consulta = em.merge(consulta);
            if (pacienteIdOld != null && !pacienteIdOld.equals(pacienteIdNew)) {
                pacienteIdOld.getConsultaCollection().remove(consulta);
                pacienteIdOld = em.merge(pacienteIdOld);
            }
            if (pacienteIdNew != null && !pacienteIdNew.equals(pacienteIdOld)) {
                pacienteIdNew.getConsultaCollection().add(consulta);
                pacienteIdNew = em.merge(pacienteIdNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = consulta.getId();
                if (findConsulta(id) == null) {
                    throw new NonexistentEntityException("The consulta with id " + id + " no longer exists.");
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
            Consulta consulta;
            try {
                consulta = em.getReference(Consulta.class, id);
                consulta.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The consulta with id " + id + " no longer exists.", enfe);
            }
            Paciente pacienteId = consulta.getPacienteId();
            if (pacienteId != null) {
                pacienteId.getConsultaCollection().remove(consulta);
                pacienteId = em.merge(pacienteId);
            }
            em.remove(consulta);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Consulta> findConsultaEntities() {
        return findConsultaEntities(true, -1, -1);
    }

    public List<Consulta> findConsultaEntities(int maxResults, int firstResult) {
        return findConsultaEntities(false, maxResults, firstResult);
    }

    private List<Consulta> findConsultaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Consulta.class));
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

    public Consulta findConsulta(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Consulta.class, id);
        } finally {
            em.close();
        }
    }

    public int getConsultaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Consulta> rt = cq.from(Consulta.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
