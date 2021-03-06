/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gruner.ical.icalconvertergu.model;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.gruner.ical.icalconvertergu.MessageJDialog;
import org.gruner.ical.icalconvertergu.SaveToDbDialog;
import org.gruner.ical.tool.Completion;

/**
 *
 * @author ladmin
 */
public class SaveProcess implements Runnable{

    private Completion completion = null;
    private Integer perscentage = new Integer(0);
    private static final Logger logger= Logger.getLogger(SaveProcess.class.getName());

    public Integer getPerscentage() {
        return perscentage;
    }

    public void setPerscentage(Integer perscentage) {
        this.perscentage = perscentage;
    }
    
    private EventItemModelController controller;

    public EventItemModelController getController() {
        return controller;
    }

    public void setController(EventItemModelController controller) {
        this.controller = controller;
    }
    
    
    public  SaveProcess(EventItemModelController controller)
    {
       // super();
        this.controller = controller;
    }

    public  SaveProcess()
    {
       // super();
       //this.controller = controller;
    }

    public  SaveProcess(Completion completion)
    {
       // super();
        this.completion = completion;
    }
    private SaveToDbDialog dialog;
    
    public void setSaveDialog(SaveToDbDialog dialog)
    {
        this.dialog = dialog;
    } 
    
    //@Override
    public void run() {
        /*try {
            //throw new UnsupportedOperationException("Not supported yet.");
            //SaveToDbDialog 
            
            Thread.currentThread().wait(5000L);
        } catch (InterruptedException ex) {
            Logger.getLogger(SaveProcess.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        //dialog = new SaveToDbDialog(null, true);
        try{
            if(controller==null){
                controller = EventItemModelController.getInstance();
                controller.updateModell("c:/users/mg/workspace-myeclipse-2ßß0/ical-Importer/Mausi's - Wünsche-2011-10-09.ics");
                //controller.updateModell("C:/Users/en/Documents/Wünsche/EM - Qualifikation.ics");
            }
                        
            controller.saveEvents(completion);
            
            System.out.println("SUCCEEDEDSUCCEEDEDSUCCEEDEDSUCCEEDEDSUCCEEDED");
            
           MessageJDialog.showMessage("Save Entries to DB", "All entries successfully saved to Database", null);
        }
        catch (Exception e)
        {
            MessageJDialog.showMessage("Save Entries to DB", "ATTENTION failed to save calender entries to Database", e);
            e.printStackTrace();
            logger.log(Level.INFO, "FAIL-FAIL-FAIL");
            System.out.println("FAILED_FAILED_FAILED");
        }
        //dialog.isDone(true);
    }

    
}
