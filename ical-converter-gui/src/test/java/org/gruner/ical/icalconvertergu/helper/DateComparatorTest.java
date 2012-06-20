/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gruner.ical.icalconvertergu.helper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import junit.framework.TestCase;

/**
 *
 * @author ladmin
 */
public class DateComparatorTest extends TestCase {
    
    public DateComparatorTest(String testName) {
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
     * Test of getInstance method, of class DateComparator.
     */
    public void testGetInstance() {
        System.out.println("getInstance");
        DateComparator expResult = null;
        DateComparator result = DateComparator.getInstance();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
        
        Date date1 = new Date(2010,12,31);
        Date date2 = new Date(1998,04,14);
        Date date3 = new Date(1999,03,12);
        
        List<Date> dateVector = new ArrayList<Date>();
        
        dateVector.add(date1);
        dateVector.add(date2);
        dateVector.add(date3);
        
        System.out.println(dateVector);
        
        
    }

    /**
     * Test of compare method, of class DateComparator.
     */
    public void testCompare() {
        System.out.println("compare");
        Object o1 = null;
        Object o2 = null;
        DateComparator instance = new DateComparator();
        int expResult = 0;
        int result = instance.compare(o1, o2);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
