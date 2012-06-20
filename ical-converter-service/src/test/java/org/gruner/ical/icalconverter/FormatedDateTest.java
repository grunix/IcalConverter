/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gruner.ical.icalconverter;

import java.text.SimpleDateFormat;
import java.util.Date;
import junit.framework.TestCase;

/**
 *
 * @author ladmin
 */
public class FormatedDateTest extends TestCase {
    
    public FormatedDateTest(String testName) {
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

    public void testSomeMethod() {
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }
    
    public void testDate(){
        try{
            String test = "20111003";
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            Date datum = sdf.parse(test);
            System.out.println("test: " + test);
            System.out.println("datum: " + datum);
        }
        catch(Exception e)
        {
            
        }
    }
}
