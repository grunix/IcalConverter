/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gruner.ical.icalconvertergu.helper;

import java.util.Comparator;
import java.util.Date;
import org.gruner.ical.icalconverter.EventItem;

/**
 *
 * @author ladmin
 */
public class EventItemComparator implements Comparator<EventItem>{

    private int test;
    private int test2;
    @Override
    public int compare(EventItem o1, EventItem o2) {
        Date dateO1 = o1.getStartDate();
        Date dateO2 = o2.getStartDate();
        return dateO1.compareTo(dateO2);
    }
    
}
