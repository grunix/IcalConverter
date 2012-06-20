/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gruner.ical.icalconvertergu.model;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import junit.framework.TestCase;
//import org.apache.log4j.BasicConfigurator;

/**
 *
 * @author ladmin
 */
public class SaveProcessTest extends TestCase {

    public SaveProcessTest(String testName) {
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
    // TODO add test methods here. The name must begin with 'test'. For example:
    // public void testHello() {}

    public void testRun() {
        try {
            LogManager.getLogManager().readConfiguration();
            Logger logger = Logger.getLogger("TESTs");
            logger.setLevel(Level.ALL);

            SaveProcess saveProcess = new SaveProcess();
            saveProcess.run();
            if(true)
                return;
            ExecutorService executorService = Executors.newSingleThreadExecutor();
            //Future future = executorService.submit(saveProcess);
            //Future future = new FutureTask();
            executorService.execute(saveProcess);
            executorService.shutdown();
            //while (!future.isDone()) {
                try {
                    /* synchronized (this){
                        Thread.currentThread().wait(1000);
                    }*/
                    System.out.println("waiting..");
                    
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("HHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHEEEEEEEEEERE");
                }
            //}
        } catch (Exception e1) {
            e1.printStackTrace();
            System.out.println("Failed");
            fail("e1");
        }

    }
}
