package core;

import org.jnativehook.NativeInputEvent;

import utils.CAppConstants;
import utils.CUtils;

public class CWritter {
	
	private static CWritter writter;
	
	private static String focusedWindow = null;
	private static String emotion = null;
	private static Long lastAction = null;
	private static Long startTimeTotal = null;
	
	private CWritter() {		
		//startTime = System.currentTimeMillis();
		//lastAction = System.currentTimeMillis();
		
		CDataParserSingleton.getInstance();		
		CDataParserSingleton.write("SESSION_STARTS="+startTimeTotal);		
	}
	
	public static CWritter getInstance()
	{
		if(writter == null)
		{
			writter = new CWritter();
		}
		
		return writter;
	}
	
	public static synchronized void writeToFile (NativeInputEvent e)
	{
		CDataParserSingleton.getInstance();		
		CDataParserSingleton.write(e.paramString()+",focusedWindow=\'"+focusedWindow+"\'"+",emotion="+emotion+",timestamp="+e.getWhen());
	}
	
	public static synchronized void writeText(String text)
	{
		CDataParserSingleton.getInstance();		
		CDataParserSingleton.write(text);
	}
	
	public void setFocusedWindow(String focusedWindow) {
		CWritter.focusedWindow = CUtils.MD5(focusedWindow.toCharArray());
	}	
	
	private static synchronized void checkInactivity ()
	{
		Long timeFromLastActivity = (System.currentTimeMillis() - lastAction);
		if(timeFromLastActivity > CAppConstants.INACTIVITY_TIMER)
		{
			//sendLogs(false);
			//initWritter();
			
			writeText("INACTIVITY_TIME="+timeFromLastActivity);
		}
		lastAction = System.currentTimeMillis();
	}
	
	public void setEmotion(String emotion) {
		
		this.emotion = emotion;
		
	}
	
}
