package org.gruner.ical.icalconverter;

import java.io.File;
import java.util.Date;
import java.util.List;

public interface IcalService {
	public List<Date> getDatePeriodFromIcalFile(String icalFileName) throws Exception;
	public List<EventItem> getEventsFromIcalFile(String icalFileName) throws Exception;
	public void test();
}
