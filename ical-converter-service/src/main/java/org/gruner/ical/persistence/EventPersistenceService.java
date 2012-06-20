/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gruner.ical.persistence;

import java.io.Serializable;
import java.util.List;
import org.gruner.ical.persistence.Event;
import org.gruner.ical.persistenceimpl.exceptions.NonexistentEntityException;

/**
 *
 * @author ladmin
 */
public interface EventPersistenceService extends Serializable {

    void create(Event event);

    void destroy(Long id) throws NonexistentEntityException;

    void edit(Event event) throws NonexistentEntityException, Exception;

    Event findEvent(Long id);

    List<Event> findEventEntities();

    List<Event> findEventEntities(int maxResults, int firstResult);

    int getEventCount();
    
}
