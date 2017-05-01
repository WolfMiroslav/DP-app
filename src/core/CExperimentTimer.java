package core;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingUtilities;
import javax.swing.Timer;

import ui.CMyEmotionDialog;
import utils.CAppConstants;


public class CExperimentTimer {	
    Timer countdownTimer;
    int timeRemaining;
    
    Timer countdownTimer2;
    int timeRemaining2;
    
    boolean firstTimeStoped = false;
    
    private static CExperimentTimer diz;
    
    public static CExperimentTimer getInstance()
    {
    	return diz;
    }

    public CExperimentTimer() {
    	int time = CAppConstants.DIALOG_TIMER;
    	countdownTimer = new Timer(time/(time/1000), new CountdownTimerListener());       
    	countdownTimer.start();
    	timeRemaining = time/100;
    	
    	int time2 = CAppConstants.SEND_TIMER;
    	countdownTimer2 = new Timer(time2/(time2/1000), new CountdownTimerListener());
    	countdownTimer2.start();
    	timeRemaining2 = time2/100;
       
    	diz = this;
    }

     class CountdownTimerListener implements ActionListener {
         public void actionPerformed(ActionEvent e) {
        	 SwingUtilities.invokeLater(new Runnable() {
   				 	@Override
   	                public void run() {    	        
		    	        if (--timeRemaining == 0) {
		    	        	
		    	        	countdownTimer.stop();
		    	        	countdownTimer2.stop();	
		    	        	
		    	        	CMyEmotionDialog emotionDialog = new CMyEmotionDialog(diz);
		    	        	emotionDialog.setVisible(true);
		    	        	emotionDialog.requestFocusInWindow();		    	        	
		                }    
		    	        
		    	        if((--timeRemaining2) == 0)
		    	        {
		    	        	countdownTimer.stop();
		    	        	countdownTimer2.stop();		    	        	
		    	        	
		    	        	if(!firstTimeStoped)
		    	        	{	
		    	        		firstTimeStoped = true;
		    	        		
		    	        		CMyEmotionDialog emotionDialog = new CMyEmotionDialog(diz);
			    	        	emotionDialog.setVisible(true);
			    	        	emotionDialog.requestFocusInWindow();
		    	        	}
		    	        	else 
		    	        	{
		    	        		CDataCollecting.getInstance();
								CDataCollecting.sendLogs(false);
								CDataCollecting.initWritter();

		    	        		countdownTimer.start();
		    	        		timeRemaining2 = CAppConstants.SEND_TIMER / 100;
		    	            	countdownTimer2.restart();
		    	        	}
		    	        }
   				 	}
				 });
         }
     }
     
     public int getTimeRemaining ()
     {
    	 return timeRemaining;
     }
     
     public void stopTimer()
     {
    	 countdownTimer.stop();
     }
     
     public void continueTimer()
     {
    	 countdownTimer.start();
     }
     
     public void resetTimer(int time)
     {    	 
    	 timeRemaining = time/100;
    	 countdownTimer.restart();	    
    	 
    	 timeRemaining2 = CAppConstants.SEND_TIMER / 100;
     	 countdownTimer2.restart();
     }
     
     
}
