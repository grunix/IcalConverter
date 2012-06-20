/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gruner.ical.persistence;

import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import junit.framework.TestCase;
import org.gruner.ical.icalconverter.EventItem;
import org.gruner.ical.persistenceimpl.EventItemJpaController;

/**
 *
 * @author ladmin
 */
public class EventItemPersistenceServiceTest extends TestCase {

    private EventItemPersistenceService service = null;

    public EventItemPersistenceServiceTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        try{
        service = new EventItemJpaController(Persistence.createEntityManagerFactory("IcalPersistencePU"));
        }
        catch (Exception e)
        {
            System.out.println("creation of service failed:" + e);
            e.printStackTrace();
        }
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    
        public void testCreate() {
        // TODO review the generated test code and remove the default call to fail.
        try {
            for(int i=0;i<10;i++)
            {
                EventItem ei = new EventItem(new Date(), "Sum"+i, "des"+i);
                service.create(ei);
            }
        
        } catch (Exception e) {
            fail("Failed " + e);
        }
    }

    public void apersist(Object object) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("IcalPersistencePU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }
}

