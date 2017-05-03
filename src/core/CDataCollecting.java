package core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import org.jnativehook.NativeInputEvent;

import utils.CAppConstants;
import utils.CUtils;

public class CDataCollecting {
	private static CDataCollecting dataCollecting;
	private static PrintWriter writer;	
	
	private String focusedWindow = null;
	private String emotion = null;
	
	private static String fileName = null;
	
	private static Long lastAction = null;
	
	private static Long startTimeTotal = null;
	private static Long endTimeTotal = null;
	
	private static Long startTime = null;
	private static Long endTime = null;
	
	private CDataCollecting() {		
		initWritter();	
		
		startTimeTotal = System.currentTimeMillis();
		writer.println("SESSION_STARTS="+startTimeTotal);		
	}
	
	public static void initWritter() {
		File currentFolder = new File(".");
		File[] files = currentFolder.listFiles();
		
		for (int fileNum = 0; fileNum < files.length; fileNum++)
		{
			if (files[fileNum].getName().startsWith("LOGS"))
			{
				
					
					fileName =  files[fileNum].getName() +"/"							
							+System.getProperty("user.name")							
							+ Long.toString(System.currentTimeMillis());
				
				
				break;
			}
		}
		
		try 
		{
			if(fileName != null)
			{
				writer = new PrintWriter(fileName+"", "UTF-8");
			}
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (UnsupportedEncodingException e) 
		{
			e.printStackTrace();
		}
		startTime = System.currentTimeMillis();
		lastAction = System.currentTimeMillis();
	}
	
	public static CDataCollecting getInstance()
	{
		if(dataCollecting == null)
		{			
			dataCollecting = new CDataCollecting();
		}
		
		return dataCollecting;
	}
	
	private void checkInactivity ()
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
	
	public synchronized void writeToFile (NativeInputEvent e)
	{
		checkInactivity();
		writer.println(e.paramString()+",focusedWindow=\'"+focusedWindow+"\'"+",emotion="+emotion+",timestamp="+e.getWhen());
	}
	
	public synchronized void writeText(String text)
	{
		writer.println(text);
	}

	public synchronized static void closeFile()
	{
		endTime = System.currentTimeMillis();
		
		writer.println("SESSION_TIME="+(endTime - startTime));
		
		writer.close();
	}
	
	
	public String getFocusedWindow() {
		return focusedWindow;
	}
	
	public String getEmotion() {
		return emotion;
	}
	
	public void setFocusedWindow(String focusedWindow) {
		this.focusedWindow = CUtils.MD5(focusedWindow.toCharArray());
	}	
	
	
	public void setEmotion(String emotion) {
		
		sendLogs(false);
		
		this.emotion = emotion;
		
		initWritter();
	}
	
	public static void sendLogs (boolean appClosed) {
		
		if(appClosed)
		{
			endTimeTotal = System.currentTimeMillis();
			
			writer.println("SESSION_TIME_TOTAL="+(endTimeTotal-startTimeTotal));
			writer.println("SESSION_ENDS="+endTimeTotal);
		}
		
		closeFile();		
		
		Thread t = new Thread(new Runnable() {				
			@Override
			public void run() {
				try {
					File f = new File(fileName);
					FileInputStream inputStream = new FileInputStream(f);				
				}
				catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
			}
		});		
		
		t.start();
		
		try {
			if(appClosed){
				t.join();
			}
			else{
				t.join(500);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

}
