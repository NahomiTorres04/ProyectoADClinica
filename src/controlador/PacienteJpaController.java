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
import vista.Consulta;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import vista.Paciente;

/**
 *
 * @author Alex de León Véliz <alexdlveliz@hotmail.com>
 */
public class PacienteJpaController implements Serializable {

    public PacienteJpaController(EntityManager em) {
        this.em = em;
    }
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProyectoAD");
    private EntityManager em = emf.createEntityManager();
    private static PacienteJpaController controladorPaciente = null;

    public EntityManager getEntityManager() {
        return this.em;
    }

    private PacienteJpaController()
    {}
    
    public PacienteJpaController getInstancia()
    {
        if(controladorPaciente == null) controladorPaciente = new PacienteJpaController();
        return controladorPaciente;
    }

    public void create(Paciente paciente) {
        if (paciente.getConsultaCollection() == null) {
            paciente.setConsultaCollection(new ArrayList<Consulta>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Consulta> attachedConsultaCollection = new ArrayList<Consulta>();
            for (Consulta consultaCollectionConsultaToAttach : paciente.getConsultaCollection()) {
                consultaCollectionConsultaToAttach = em.getReference(consultaCollectionConsultaToAttach.getClass(), consultaCollectionConsultaToAttach.getId());
                attachedConsultaCollection.add(consultaCollectionConsultaToAttach);
            }
            paciente.setConsultaCollection(attachedConsultaCollection);
            em.persist(paciente);
            for (Consulta consultaCollectionConsulta : paciente.getConsultaCollection()) {
                Paciente oldPacienteIdOfConsultaCollectionConsulta = consultaCollectionConsulta.getPacienteId();
                consultaCollectionConsulta.setPacienteId(paciente);
                consultaCollectionConsulta = em.merge(consultaCollectionConsulta);
                if (oldPacienteIdOfConsultaCollectionConsulta != null) {
                    oldPacienteIdOfConsultaCollectionConsulta.getConsultaCollection().remove(consultaCollectionConsulta);
                    oldPacienteIdOfConsultaCollectionConsulta = em.merge(oldPacienteIdOfConsultaCollectionConsulta);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Paciente paciente) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Paciente persistentPaciente = em.find(Paciente.class, paciente.getId());
            Collection<Consulta> consultaCollectionOld = persistentPaciente.getConsultaCollection();
            Collection<Consulta> consultaCollectionNew = paciente.getConsultaCollection();
            Collection<Consulta> attachedConsultaCollectionNew = new ArrayList<Consulta>();
            for (Consulta consultaCollectionNewConsultaToAttach : consultaCollectionNew) {
                consultaCollectionNewConsultaToAttach = em.getReference(consultaCollectionNewConsultaToAttach.getClass(), consultaCollectionNewConsultaToAttach.getId());
                attachedConsultaCollectionNew.add(consultaCollectionNewConsultaToAttach);
            }
            consultaCollectionNew = attachedConsultaCollectionNew;
            paciente.setConsultaCollection(consultaCollectionNew);
            paciente = em.merge(paciente);
            for (Consulta consultaCollectionOldConsulta : consultaCollectionOld) {
                if (!consultaCollectionNew.contains(consultaCollectionOldConsulta)) {
                    consultaCollectionOldConsulta.setPacienteId(null);
                    consultaCollectionOldConsulta = em.merge(consultaCollectionOldConsulta);
                }
            }
            for (Consulta consultaCollectionNewConsulta : consultaCollectionNew) {
                if (!consultaCollectionOld.contains(consultaCollectionNewConsulta)) {
                    Paciente oldPacienteIdOfConsultaCollectionNewConsulta = consultaCollectionNewConsulta.getPacienteId();
                    consultaCollectionNewConsulta.setPacienteId(paciente);
                    consultaCollectionNewConsulta = em.merge(consultaCollectionNewConsulta);
                    if (oldPacienteIdOfConsultaCollectionNewConsulta != null && !oldPacienteIdOfConsultaCollectionNewConsulta.equals(paciente)) {
                        oldPacienteIdOfConsultaCollectionNewConsulta.getConsultaCollection().remove(consultaCollectionNewConsulta);
                        oldPacienteIdOfConsultaCollectionNewConsulta = em.merge(oldPacienteIdOfConsultaCollectionNewConsulta);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = paciente.getId();
                if (findPaciente(id) == null) {
                    throw new NonexistentEntityException("The paciente with id " + id + " no longer exists.");
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
            Paciente paciente;
            try {
                paciente = em.getReference(Paciente.class, id);
                paciente.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The paciente with id " + id + " no longer exists.", enfe);
            }
            Collection<Consulta> consultaCollection = paciente.getConsultaCollection();
            for (Consulta consultaCollectionConsulta : consultaCollection) {
                consultaCollectionConsulta.setPacienteId(null);
                consultaCollectionConsulta = em.merge(consultaCollectionConsulta);
            }
            em.remove(paciente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Paciente> findPacienteEntities() {
        return findPacienteEntities(true, -1, -1);
    }

    public List<Paciente> findPacienteEntities(int maxResults, int firstResult) {
        return findPacienteEntities(false, maxResults, firstResult);
    }

    private List<Paciente> findPacienteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Paciente.class));
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

    public Paciente findPaciente(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Paciente.class, id);
        } finally {
            em.close();
        }
    }

    public int getPacienteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Paciente> rt = cq.from(Paciente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
