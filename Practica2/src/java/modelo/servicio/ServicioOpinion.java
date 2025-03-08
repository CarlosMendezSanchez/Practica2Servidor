/*
 * ServicioOpinion.
 */
package modelo.servicio;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import modelo.entidades.ExperienciaViaje;
import modelo.entidades.Opinion;
import modelo.servicio.exceptions.NonexistentEntityException;

/**
 *
 * @author jose
 */
public class ServicioOpinion implements Serializable {

    public ServicioOpinion(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Opinion opinion) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(opinion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Opinion opinion) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            opinion = em.merge(opinion);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = opinion.getId();
                if (findOpinion(id) == null) {
                    throw new NonexistentEntityException("The opinion with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Opinion opinion;
            try {
                opinion = em.getReference(Opinion.class, id);
                opinion.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The opinion with id " + id + " no longer exists.", enfe);
            }
            em.remove(opinion);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Opinion> findOpinionEntities() {
        return findOpinionEntities(true, -1, -1);
    }

    public List<Opinion> findOpinionEntities(int maxResults, int firstResult) {
        return findOpinionEntities(false, maxResults, firstResult);
    }

    private List<Opinion> findOpinionEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Opinion.class));
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

    public Opinion findOpinion(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Opinion.class, id);
        } finally {
            em.close();
        }
    }

    public int getOpinionCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Opinion> rt = cq.from(Opinion.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public void eliminarOpiniones(ExperienciaViaje exp) throws NonexistentEntityException {
    // EntityManager se utilizar� para interactuar con la base de datos
    EntityManager em = null;
    try {
        // Obtener instancia del EntityManager e iniciar transacci�n para realizar operaciones en la base de datos.
        em = getEntityManager();
        em.getTransaction().begin();

        /*
        * Crear una consulta a la base de datos para obtener todas las opiniones asociadas a la experiencia de viaje.
        * Establecer el par�metro "experiencia" en la consulta con el objeto ExperienciaViaje exp.
        */
        Query query = em.createQuery("SELECT o FROM Opinion o WHERE o.experiencia = :experiencia");
        query.setParameter("experiencia", exp);

        //Obtener la lista de las opiniones
        List<Opinion> opiniones = query.getResultList();
        if (opiniones.isEmpty()) {
            return;
        }
        
        // Recorrer esa lista para eliminar cada opinion
        for (Opinion opinion : opiniones) {
            try {
                opinion = em.getReference(Opinion.class, opinion.getId());
                opinion.getId();
                em.remove(opinion);
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("Esta opinion con el id " + opinion.getId() + " ya no existe.", enfe);
            }
        }

        // Guardar los cambios en la base de datos
        em.getTransaction().commit();
    } catch (Exception ex) {
        // Si ha ocurrido un error, revierte los cambios.
        if (em != null && em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
        throw new RuntimeException("Error al eliminar las opiniones de la experiencia", ex);
    } finally {
        if (em != null) {
            em.close();
        }
    }
    }
}
