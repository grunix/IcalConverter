/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gruner.ical.persistenceimpl;

import java.util.List;
import javax.persistence.EntityManager;
import junit.framework.TestCase;
import org.gruner.ical.persistence.Event;

/**
 *
 * @author ladmin
 */
public class EventJpaControllerTest extends TestCase {
    
    public EventJpaControllerTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }


    /**
     * Test of create method, of class EventJpaController.
     */
    public void atestCreate() {
        System.out.println("create");
        Event event = null;
        EventJpaController instance = null;
        instance.create(event);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of edit method, of class EventJpaController.
     */
    public void atestEdit() throws Exception {
        System.out.println("edit");
        Event event = null;
        EventJpaController instance = null;
        instance.edit(event);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of destroy method, of class EventJpaController.
     */
    public void atestDestroy() throws Exception {
        System.out.println("destroy");
        Long id = null;
        EventJpaController instance = null;
        instance.destroy(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findEventEntities method, of class EventJpaController.
     */
    public void atestFindEventEntities_0args() {
        System.out.println("findEventEntities");
        EventJpaController instance = null;
        List expResult = null;
        List result = instance.findEventEntities();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findEventEntities method, of class EventJpaController.
     */
    public void atestFindEventEntities_int_int() {
        System.out.println("findEventEntities");
        int maxResults = 0;
        int firstResult = 0;
        EventJpaController instance = null;
        List expResult = null;
        List result = instance.findEventEntities(maxResults, firstResult);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findEvent method, of class EventJpaController.
     */
    public void atestFindEvent() {
        System.out.println("findEvent");
        Long id = null;
        EventJpaController instance = null;
        Event expResult = null;
        Event result = instance.findEvent(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEventCount method, of class EventJpaController.
     */
    public void atestGetEventCount() {
        System.out.println("getEventCount");
        EventJpaController instance = null;
        int expResult = 0;
        int result = instance.getEventCount();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
