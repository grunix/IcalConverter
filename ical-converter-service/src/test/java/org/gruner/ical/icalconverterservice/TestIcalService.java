
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gruner.ical.icalconverterservice;

import edu.emory.mathcs.backport.java.util.Collections;
import java.util.Date;
import org.gruner.ical.icalconverter.IcalService;
import org.gruner.ical.icalconverter.EventItem;
import java.util.Iterator;
import java.util.List;
import junit.framework.TestCase;
import org.gruner.ical.icalconverterimpl.DateComparator;
import org.gruner.ical.icalconverterimpl.IcalImporterServiceImpl;

/**
 *
 * @author ladmin
 */
public class TestIcalService extends TestCase {
    
    public TestIcalService(String testName) {
        super(testName);
    }
    
    public void testTest()
    {
        try{
            IcalService service = new IcalImporterServiceImpl();
            //service.test();
            List<Date> returnList = service.getDatePeriodFromIcalFile("C:/users/mg/workspace-myeclipse-2ßß0/ical-Importer/Mausi's - Wünsche-2011-10-09.ics");
    
            System.out.println(returnList);
            
            Collections.sort(returnList, DateComparator.getInstance());
            
            System.out.println(returnList);
        }
        catch(Exception e)
        {
            e.printStackTrace();
            fail(e.getLocalizedMessage());
        }
    }
    
   
    public void htestGetEventsFromIcalFile()
    {
        try{
            IcalService service = new IcalImporterServiceImpl();
            //service.test();
            List<EventItem> returnList = service.getEventsFromIcalFile("C:/users/mg/workspace-myeclipse-2ßß0/ical-Importer/Mausi's - Wünsche-2011-10-09.ics");
            for (Iterator<EventItem> it = returnList.iterator(); it.hasNext();) {
                EventItem eventItem = it.next();
                System.out.print(eventItem.getStartDate() + " ");
                System.out.print(eventItem.getSummary() + " ");
                System.out.println(eventItem.getDescription() + " ");
            }
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
            fail(e.getLocalizedMessage());
        }
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    // TODO add test methods here. The name must begin with 'test'. For example:
    // public void testHello() {}
}
