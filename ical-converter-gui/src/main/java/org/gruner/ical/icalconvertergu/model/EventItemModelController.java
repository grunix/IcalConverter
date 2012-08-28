/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gruner.ical.icalconvertergu.model;

import java.io.File;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import org.gruner.ical.icalconverter.EventItem;
import org.gruner.ical.icalconverter.IcalService;
import org.gruner.ical.icalconvertergu.helper.DateComparator;
import org.gruner.ical.icalconvertergu.helper.EventItemComparator;
import org.gruner.ical.icalconverterimpl.IcalImporterServiceImpl;
import org.gruner.ical.persistence.EventItemPersistenceService;
import org.gruner.ical.persistenceimpl.EventItemJpaController;
import org.gruner.ical.tool.Completion;

/**
 *
 * @author ladmin
 */
public class EventItemModelController implements ListDataListener{
    

    //sprivate static final Logger logger = Logger.getLogger(EventItemModelController.class);
    
    private int massUpdateCompletion = 0;

    public int getMassUpdateCompletion() {
        return massUpdateCompletion;
    }

    public void setMassUpdateCompletion(int massUpdateCompletion) {
        this.massUpdateCompletion = massUpdateCompletion;
        
    }
    
    private static Logger logger = Logger.getLogger(EventItemModelController.class.getName());//Logger(EventItemModelController.class);
    private static EventItemModelController instance = null;
    
    private EventItemPersistenceService persistenceService = null;
    
    private final Vector<Date> dateVector = new Vector<Date>();


    private boolean interrupted = false;

    public boolean isInterrupted() {
        return interrupted;
    }

    public void setInterrupted(boolean interrupted) {
        synchronized(this){
            this.interrupted = interrupted;
        }
    }
    
    public static EventItemModelController getInstance(){
    
        if(instance ==null)
            instance = new EventItemModelController();
        return instance;
    }
    
    @Override
    public void contentsChanged(ListDataEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void intervalAdded(ListDataEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void intervalRemoved(ListDataEvent e) {
        
        throw new UnsupportedOperationException("Not supported yet.");
    }

    
    /**
     * model of all Event - Dates (sorted by Date)
     */
    private DefaultComboBoxModel eventDateModel = new DefaultComboBoxModel(); // dcbmFrom

    public DefaultComboBoxModel getEventDateModel() {
        return eventDateModel;
    }

    public DefaultListModel getEventItemListModel() {
        return eventItemListModel;
    }
            
    
    /**
     * model of all EventItem-Objecs resistent in memory
     */
    private DefaultListModel eventItemListModel = new DefaultListModel(); // dlm
    
    private EventItemModelController() {
        // init all Data from IcalImporterService
        eventItemListModel.addListDataListener(this);
        
        persistenceService = new EventItemJpaController(Persistence.createEntityManagerFactory("IcalPersistencePU"));
    }
    
    public void updateModell(String filename){
       File file = new File(filename);
       
       if(logger.isLoggable(Level.FINE))
            logger.fine("Update with file:" + filename);
       
        if(file.exists())
        {
            IcalService icalService = new IcalImporterServiceImpl();
            List<Date> dateList = null; 
            List<EventItem> eventItemList = null;
            try{
                 dateList = icalService.getDatePeriodFromIcalFile(filename);
                dateVector.addAll(dateList);
                Collections.sort(dateVector, DateComparator.getInstance());
                
                eventDateModel = new DefaultComboBoxModel(dateVector);
                
                
                
                eventItemList = icalService.getEventsFromIcalFile(filename);
                Collections.sort(eventItemList, new EventItemComparator());
                
                eventItemListModel = new DefaultListModel();
                
                for (Iterator<EventItem> it = eventItemList.iterator(); it.hasNext();) {
                    EventItem eventItem = it.next();
                    eventItemListModel.addElement(eventItem);
                }
            }catch(Exception e)
            {
                
            }
        }
     
    }
 
    public synchronized void saveEvents(Completion completion)
    {
        Object[] list = eventItemListModel.toArray();
        
        logger.log(Level.FINE, "Entering");
        EntityManager em = persistenceService.getEntityManager();
        em.getTransaction().begin();

        //percentComplete =new Integer(0);
        int count = list.length;
        Float percentOfOneElement = 100 / (float)count;
        Float excatlyPersentage = new Float(0);
        for (Object object : list) {
            
            
            // check for "cancel"-command of user
            if(isInterrupted())
                break;
            
            
            
            EventItem eventItem = (EventItem)object;
            try{
                //System.out.println("Save Event from:" + eventItem.getStartDate());
                logger.log(Level.FINER, "Save Event from:" + eventItem.getStartDate() );
                System.out.println(eventItem.getDescription());
                if(eventItem.getDescription().length()>=128)
                {
                    System.out.println("SETTTTTTING DESC TO NULL");
                    //eventItem.setDescription("NULL");
                }
                persistenceService.create(eventItem, em);
                excatlyPersentage+=percentOfOneElement;
                completion.setPersentage(Integer.valueOf(excatlyPersentage.intValue()));
                System.out.println("Percentage: " +  excatlyPersentage);
            }
            catch(Exception e)
            {
                logger.log(Level.WARNING, "Failed to create EventItem :" + eventItem,e );
            }
        }
        if(isInterrupted())
        {
            logger.log(Level.FINE, "rollback Exiting");
            em.getTransaction().rollback();
        }else{
            logger.log(Level.FINE, "commit Exiting");
            em.getTransaction().commit();
            
        }
    
    }
    
    public void showInfos()
    {
        System.out.println("No add Element to modell");
        this.dateVector.add(new Date());
        System.out.println("now add element to vector");
    }
}
