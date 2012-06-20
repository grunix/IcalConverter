/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gruner.ical.persistenceimpl;

import org.gruner.ical.persistence.EventItemPersistenceService;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.gruner.ical.icalconverter.EventItem;
import org.gruner.ical.persistenceimpl.exceptions.NonexistentEntityException;

/**
 *
 * @author ladmin
 */
public class EventItemJpaController implements Serializable, EventItemPersistenceService {

    public EventItemJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    @Override
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public void create(EventItem eventItem, EntityManager entityManager) throws Exception{
        entityManager.persist(eventItem);
    }

    @Override
    public void create(EventItem eventItem) throws Exception{
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(eventItem);
            em.getTransaction().commit();
        }catch(Exception e)
        {
            throw e;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }


    @Override
    public void edit(EventItem eventItem) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            eventItem = em.merge(eventItem);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = eventItem.getId();
                if (findEventItem(id) == null) {
                    throw new NonexistentEntityException("The eventItem with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EventItem eventItem;
            try {
                eventItem = em.getReference(EventItem.class, id);
                eventItem.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The eventItem with id " + id + " no longer exists.", enfe);
            }
            em.remove(eventItem);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public List<EventItem> findEventItemEntities() {
        return findEventItemEntities(true, -1, -1);
    }

    @Override
    public List<EventItem> findEventItemEntities(int maxResults, int firstResult) {
        return findEventItemEntities(false, maxResults, firstResult);
    }

    private List<EventItem> findEventItemEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EventItem.class));
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

    @Override
    public EventItem findEventItem(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EventItem.class, id);
        } finally {
            em.close();
        }
    }

    @Override
    public int getEventItemCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EventItem> rt = cq.from(EventItem.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    
}
