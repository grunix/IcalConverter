package org.gruner.ical.icalconverterimpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import java.util.Vector;
import java.util.logging.Logger;
import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.DateTime;
import net.fortuna.ical4j.model.Period;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.property.Description;
import net.fortuna.ical4j.model.property.DtStart;
import org.gruner.ical.icalconverter.EventItem;
import org.gruner.ical.icalconverter.FormatedDate;
import org.gruner.ical.icalconverter.IcalService;

public class IcalImporterServiceImpl implements IcalService {
    private Logger logger = Logger.getLogger(IcalImporterServiceImpl.class.getName());

    public List<Date> getDatePeriodFromIcalFile(String icalFileName) throws Exception {
        if (icalFileName == null) {
            throw new IllegalArgumentException("icalFileName should not be null");
        }
        if (!(new File(icalFileName)).exists()) {
            throw new IllegalArgumentException("ical-File:" + icalFileName + " does not exist!");
        }
        List<Date> returnList = new LinkedList<Date>();
        try {
            Calendar calendar = readCalendarFile(icalFileName);
            // For each VEVENT in the ICS
            for (Object o : calendar.getComponents()) {
                Component c = (Component) o;
                if (c instanceof VEvent) {
                    VEvent vevent = (VEvent) c;
                    logger.finer("Start:" + vevent.getStartDate().getDate());
                    //returnList.add(new FormatedDate(vevent.getStartDate().getDate()));
                    DateFormat df = new SimpleDateFormat("yyyyMMdd");
                    
;                    java.util.Date datum = df.parse(vevent.getStartDate().getDate().toString());
                    returnList.add(datum);
                }
            }
            logger.exiting(this.getClass().getSimpleName(),"getDatePeriodFromFile");
            
            
            return returnList;
        } catch (Exception e) {
            throw e;
        }
    }

    public List<FormatedDate> getDatePeriodFromIcalFile1(String icalFileName) throws Exception {
        if (icalFileName == null) {
            throw new IllegalArgumentException("icalFileName should not be null");
        }
        if (!(new File(icalFileName)).exists()) {
            throw new IllegalArgumentException("ical-File:" + icalFileName + " does not exist!");
        }
        List<FormatedDate> returnList = new Vector<FormatedDate>();
        try {
            Calendar calendar = readCalendarFile(icalFileName);
            // For each VEVENT in the ICS
            for (Object o : calendar.getComponents()) {
                Component c = (Component) o;
                if (c instanceof VEvent) {
                    VEvent vevent = (VEvent) c;
                    logger.finest("Start:" + vevent.getStartDate().getDate());
                    //returnList.add(new FormatedDate(vevent.getStartDate().getDate()));
                    //DateFormat df = new DateFormat("")
;                    //java.util.Date datum = df.parse()
                    returnList.add(new FormatedDate(vevent.getStartDate().getDate().toString()));
                }
            }
            logger.info("Finish");
            return returnList;
        } catch (Exception e) {
            throw e;
        }
    }

    public Calendar readCalendarFile(String filename) throws IOException, ParserException {
        // Reading the file and creating the calendar
        CalendarBuilder builder = new CalendarBuilder();
        Calendar cal = null;
        cal = builder.build(new FileInputStream(filename));
        return cal;
    }

    public void test() {
        try {
            Calendar cal = readCalendarFile("C:/Users/mg/workspace-myeclipse-2ßß0/ical-Importer/Mausi's - Wünsche-2011-10-09.ics");

            // Create the date range which is desired.
            DateTime from = new DateTime("20100101T070000Z");
            DateTime to = new DateTime("20101231T070000Z");
            ;
            Period period = new Period(from, to);



            // For each VEVENT in the ICS
            for (Object o : cal.getComponents()) {
                Component c = (Component) o;
                if (c instanceof VEvent) {
                    VEvent vevent = (VEvent) c;
                    logger.finest("Start:" + vevent.getStartDate().getDate());
                    logger.finest("Titel:" + vevent.getProperty("SUMMARY").getValue());
                    Description desc=null;
                    logger.finest("Desc.:" + vevent.getDescription());
                    DtStart dtStart = vevent.getStartDate();
                    dtStart.getDate();
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public List<EventItem> getEventsFromIcalFile(String icalFileName) throws Exception {
        if (icalFileName == null) {
            throw new IllegalArgumentException("icalFileName should not be null");
        }
        if (!(new File(icalFileName)).exists()) {
            throw new IllegalArgumentException("ical-File:" + icalFileName + " does not exist!");
        }
        List<EventItem> returnList = new Vector<EventItem>();
        try {
            Calendar calendar = readCalendarFile(icalFileName);
            // For each VEVENT in the ICS
            for (Object o : calendar.getComponents()) {
                Component c = (Component) o;
                if (c instanceof VEvent){
                    VEvent vevent = (VEvent) c;
                    logger.finest("Start:" + vevent.getStartDate().getDate());
                    Description description = vevent.getDescription();
                    String sdescription = "";
                    if(description!=null)
                        sdescription=description.getValue();
                    
                    // convert ical date to java.util.Date
                    DateFormat df = new SimpleDateFormat("yyyyMMdd");
                    
                    java.util.Date datum = df.parse(vevent.getStartDate().getDate().toString());

                    returnList.add(new EventItem(
                            datum, 
                            vevent.getProperty("SUMMARY").getValue(), 
                            sdescription));
                }
            }
            System.out.println("Finish");
            return returnList;
        } catch (Exception e) {
            throw e;
        }
    }
}
