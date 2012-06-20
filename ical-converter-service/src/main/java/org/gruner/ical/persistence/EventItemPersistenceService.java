/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gruner.ical.persistence;

import java.util.List;
import javax.persistence.EntityManager;


import org.gruner.ical.icalconverter.EventItem;
import org.gruner.ical.persistenceimpl.exceptions.NonexistentEntityException;

/**
 *
 * @author ladmin
 */
public interface EventItemPersistenceService {

    void create(EventItem eventItem) throws Exception;
    
    void create(EventItem eventItem, EntityManager entityManager) throws Exception;
    
    void destroy(Long id) throws NonexistentEntityException;

    void edit(EventItem eventItem) throws NonexistentEntityException, Exception;

    EventItem findEventItem(Long id);

    List<EventItem> findEventItemEntities();

    List<EventItem> findEventItemEntities(int maxResults, int firstResult);

    EntityManager getEntityManager();

    int getEventItemCount();
    
}
