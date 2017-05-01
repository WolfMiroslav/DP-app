package core;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import entities.CEvent;
import entities.CKeyAction;
import entities.CKeyEvent;
import entities.CMouseEvent;
import entities.CMousePair;
import entities.CMyPoint;
import enums.EEmotionEnum;
import enums.EMouseActionEnum;
import utils.CKeysConstants;
import utils.CParserConstants;

public class CDataParserSingleton {
	
	private static CDataParserSingleton dataParser;
	
	private static String name;
	private static String session;
	private static long timeTotal;
	private static long inactivityTimeTotal;
		
	private static List<CEvent> mouseEventsList;
	private static List<CEvent> keyboardEventsList;
	
	private static List<Long> pauseTimeList;
	private static List<Long> windowSwitchedTimeList;
	
	private static CKeyEvent keyEvent;
	private static HashMap<String, CKeyAction> pressedKeysMap;
	
	private static int neutral, positive, negative;
	private static boolean emotionSwitched;
	
	private static CMousePair<CMyPoint, CMyPoint> startPointPair;
	private static CMousePair<CMyPoint, CMyPoint> clicksPair;
	
	private static boolean mouseActivity;
	private static boolean clicked;		
	private static long currentTimeStamp;
	private static long previousTimeStamp;
	private static EEmotionEnum currentEmotion;
	private static int clickCounter;
	private static int pauseCounter;
	private static int switchedWindowCounter;
	
	private CDataParserSingleton ()
	{
		initParser();
	}
	
	private void initParser()
	{
		timeTotal = 0;
		inactivityTimeTotal = 0;
			
		mouseEventsList = new ArrayList<>();
		keyboardEventsList = new ArrayList<>();
		
		pauseTimeList = new ArrayList<>();
		windowSwitchedTimeList = new ArrayList<>();
		
		keyEvent = new CKeyEvent();
		pressedKeysMap = new HashMap<>();
		
		neutral=0; positive=0; negative=0;
		emotionSwitched = false;
		
		startPointPair = new CMousePair<CMyPoint, CMyPoint>(null, null);
		clicksPair = new CMousePair<CMyPoint, CMyPoint>(null, null);
		
		mouseActivity = false;
		clicked = false;		
		currentTimeStamp = 0;
		previousTimeStamp = 0;
		currentEmotion = null;
		clickCounter = 0;
		pauseCounter = 0;
		switchedWindowCounter = 0;
	}
	
	public static CDataParserSingleton getInstance()
	{
		if(dataParser == null)
		{
			dataParser = new CDataParserSingleton();
		}
		return dataParser;
	}	
	
	public static void write(String line)
	{		
		
		System.out.println(line);
		if(line.matches(CParserConstants.EMOTION_REGEXP))
		{
			currentEmotion = getCurrentEmotion(line, currentEmotion);
			
			if (currentEmotion == null) 
			{
				//line = "DELETED";
			}
		}				
						
		getTimes(line);				
		
		if(line.matches(CParserConstants.TIMESTAMP_REGEXP))
		{
			if(currentTimeStamp != 0)
			{
				previousTimeStamp = currentTimeStamp;
			}
			currentTimeStamp = Long.parseLong(line.split(CParserConstants.TIMESTAMP_ATTR)[1]);
		}				
		
		if(currentTimeStamp != 0 && previousTimeStamp != 0)
		{
			long pauseLength = (currentTimeStamp - previousTimeStamp);
			if(pauseLength > 5000)
			{
				pauseCounter++;
				
				if(!mouseEventsList.isEmpty())
				{
					mouseEventsList.remove(mouseEventsList.size()-1);
				}
				
				pauseTimeList.add(pauseLength);
			}
		}
		/*
		if(line.matches(CParserConstants.INACTIVITY_TIME_REGEXP))
		{
			pauseCounter++;
			mouseEventsList.remove(mouseEventsList.size()-1);
			
			pauseTimeList.add(Long.parseLong(line.split(CParserConstants.INACTIVITY_TIME_ATTR)[1]));					
		}*/
		if(line.matches(CParserConstants.ACTIVE_WINDOW_TITLE_REGEXP))
		{
			switchedWindowCounter++;
			
			if(currentTimeStamp != 0)
			{
				windowSwitchedTimeList.add(currentTimeStamp);
			}
			
		/*	if(!mouseEventsList.isEmpty()){						
				CMouseEvent me = (CMouseEvent) mouseEventsList.get(mouseEventsList.size()-1);
				me.incSwitedWindowNum();
				mouseEventsList.remove(mouseEventsList.size()-1);
				mouseEventsList.add(me);
			}
			if(!keyboardEventsList.isEmpty())
			{
				CKeyEvent ke = (CKeyEvent) keyboardEventsList.get(keyboardEventsList.size() -1);
				ke.incSwitedWindowNum();
				keyboardEventsList.remove(keyboardEventsList.size() -1);
				keyboardEventsList.add(ke);
				
			}*/
		}
		
		if(line.matches(CParserConstants.MOUSE_PRESSED_REGEXP))
		{
			mouseActivity = true;
			
			int clickcountStartIndex = line.indexOf(CParserConstants.CLICK_COUNT_ATTR) + CParserConstants.CLICK_COUNT_ATTR.length();
			String clickcountSubString = line.substring(clickcountStartIndex);
			clickcountSubString = clickcountSubString.substring(0, clickcountSubString.indexOf(','));
			
			int clickCount = Integer.parseInt(clickcountSubString);
			
			if(clickCount == 1)
			{
				startPointPair = new CMousePair<CMyPoint, CMyPoint>(createPoint(line, currentTimeStamp), null);						
			}
			else
			{							
				clicksPair = new CMousePair<CMyPoint, CMyPoint>(createPoint(line, currentTimeStamp), null);						
			}
			
			clicked = true;
			clickCounter = clickCount;					
		}
		else if((line.matches(CParserConstants.MOUSE_RELEASED_REGEXP)) || (line.matches(CParserConstants.MOUSE_DRAGGED_REGEXP) && clicked))
		{
			mouseActivity = true;
			
			if(clickCounter > 1)
			{
				clicksPair.setR(createPoint(line, currentTimeStamp));
				((CMouseEvent)mouseEventsList.get(mouseEventsList.size()-1)).addClicksToList(clicksPair);
			}
			/*else if((line.matches("NATIVE_MOUSE_RELEASED.*") && !clicked))
			{
				CMyMousePair<CMyPoint, CMyPoint> endPointPair = new CMyMousePair<CMyPoint, CMyPoint>(null, createPoint(line, currentTimeStamp));		
				((CMouseEvent)mouseEventsList.get(mouseEventsList.size()-1)).setEndPoint(endPointPair);
			}*/
			else if(clicked)
			{						
				CMouseEvent mouseEvent = new CMouseEvent();
				startPointPair.setR(createPoint(line, currentTimeStamp));
				
				if(!mouseEventsList.isEmpty())
				{							
					if(((CMouseEvent)mouseEventsList.get(mouseEventsList.size()-1)).getEndPoint() == null)
					{
						((CMouseEvent)mouseEventsList.get(mouseEventsList.size()-1)).setEndPoint(startPointPair);
						
						if(((CMouseEvent)mouseEventsList.get(mouseEventsList.size()-1)).getPoints().isEmpty())
						{
							mouseEventsList.remove(mouseEventsList.size()-1);									
						}
						else
						{
							((CMouseEvent) mouseEventsList.get(mouseEventsList.size()-1)).calculateCharacteristics();
						}
					}
				}						
				
				mouseEvent.setEmotion(currentEmotion);
				mouseEvent.setStartPoint(startPointPair);		
				
				mouseEvent.setSession(session);
				mouseEvent.setUserName(name);
				mouseEventsList.add(mouseEvent);
			}
			clicked = false;
		}				
		else if((line.matches(CParserConstants.MOUSE_MOVED_REGEXP)						
				|| line.matches(CParserConstants.MOUSE_WHEEL_REGEXP))
				|| (line.matches(CParserConstants.MOUSE_DRAGGED_REGEXP) && !clicked)) {
			
			mouseActivity = true;
			
			if(mouseEventsList != null && !mouseEventsList.isEmpty())
			{															
				((CMouseEvent)mouseEventsList.get(mouseEventsList.size()-1)).addPointToPointsList(createMoveOrScrollPoint(line, currentTimeStamp));
			}
		}
				
		else if(line.matches(CParserConstants.KEY_PRESSED_REGEXP))
		{					
			CKeyAction keyAction;
			String myCode = getValueFromLog(CParserConstants.MY_CODE_ATTR, line);
			String keyText = getValueFromLog(CParserConstants.KEY_TEXT_ATTR, line);
			
			if(!keyEvent.keysListEmpty() && keyEvent.isComplete())
			{						
				if(!keyText.equals(CKeysConstants.CHAR_KEY)
						|| !keyText.equals(CKeysConstants.DIGIT_KEY)
						|| !keyText.equals(CKeysConstants.NUMPAD_DIGIT_KEY)
						|| (currentTimeStamp - keyEvent.getLastKey().getReleasedTime()) > 1000)
				{
					keyboardEventsList.add(keyEvent);
					keyEvent = new CKeyEvent();
				}
			}
			
			if(pressedKeysMap.isEmpty() || !pressedKeysMap.containsKey(myCode))
			{
				if(keyText.equals(CKeysConstants.CHAR_KEY)
					|| keyText.equals(CKeysConstants.DIGIT_KEY)
					|| keyText.equals(CKeysConstants.NUMPAD_DIGIT_KEY))
				{
					keyAction = new CKeyAction();
					keyAction.setKeyCode(myCode);
					keyAction.setModifiers(getValueFromLog(CParserConstants.MODIFIERS_ATTR, line));
					keyAction.setKeyText(getValueFromLog(CParserConstants.KEY_TEXT_ATTR, line));						
					keyAction.setKeyLocation(getValueFromLog(CParserConstants.KEY_LOCATION_ATTR, line));
					keyAction.setPressedTime(currentTimeStamp);
					keyAction.setReleased(false);
					pressedKeysMap.put(myCode, keyAction);
				}						
			}
		}
		else if(line.matches(CParserConstants.KEY_TYPED_REGEXP) && line.matches(CParserConstants.KEY_CHAR_REGEXP))
		{
			String myCode = getValueFromLog(CParserConstants.KEY_ORDER_ATTR, line);
			
			if(!pressedKeysMap.isEmpty() && pressedKeysMap.containsKey(myCode))
			{
				CKeyAction keyAction = pressedKeysMap.get(myCode);
				keyAction.setKeyChar(getValueFromLog(CParserConstants.KEY_CHAR_ATTR, line));
				pressedKeysMap.put(myCode, keyAction);
			}
		}
		else if(line.matches(CParserConstants.KEY_RELEASED_REGEXP))
		{
			String myCode = getValueFromLog(CParserConstants.MY_CODE_ATTR, line);
			String keyText = getValueFromLog(CParserConstants.KEY_TEXT_ATTR, line);
			String modifiers = getValueFromLog(CParserConstants.MODIFIERS_ATTR, line);
			
			if(!pressedKeysMap.isEmpty() && pressedKeysMap.containsKey(myCode))
			{
				CKeyAction keyAction = pressedKeysMap.get(myCode);
				keyAction.setReleasedTime(currentTimeStamp);
				keyAction.setReleased(true);
				keyEvent.addActionsToList(keyAction);
				pressedKeysMap.remove(myCode);
										
				if(!modifiers.equals("null"))
				{
					int modifiersNumber = 0;
					for(int i=0; i<modifiers.length(); i++)
					{
						if(modifiers.charAt(i) == '+')
						{
							modifiersNumber++;
						}
					}
					keyEvent.setModifiersCount(modifiersNumber+1);						
				}						
			}
			
			if (keyText.equals(CKeysConstants.BACKSPACE_KEY) || keyText.contains(CKeysConstants.DELETE_KEY))
			{
				keyEvent.incMisstakes();
			}					
			
			mouseActivity = false;
		}
	}
	
	private static EEmotionEnum getCurrentEmotion (String line, EEmotionEnum currentEmotion)
	{		
		if(line.matches(CParserConstants.NEUTRAL_EMOTION_REGEXP))
		{
			if(currentEmotion == null || !currentEmotion.equals(EEmotionEnum.NEUTRAL))
			{
				neutral++;
			}
			
			if(currentEmotion != null && !currentEmotion.equals(EEmotionEnum.NEUTRAL))
			{
				emotionSwitched = true;
			}
			else
			{
				emotionSwitched = false;
			}
			currentEmotion = EEmotionEnum.NEUTRAL;
		}
		else if(line.matches(CParserConstants.POSITIVE_EMOTION_REGEXP))
		{
			if(currentEmotion == null || !currentEmotion.equals(EEmotionEnum.POSITIVE))
			{
				positive++;
			}
			
			if(currentEmotion != null && !currentEmotion.equals(EEmotionEnum.POSITIVE))
			{
				emotionSwitched = true;
			}
			else
			{
				emotionSwitched = false;
			}
			
			currentEmotion = EEmotionEnum.POSITIVE;
		}
		else if(line.matches(CParserConstants.NEGATIVE_EMOTION_REGEXP))
		{
			if(currentEmotion == null || !currentEmotion.equals(EEmotionEnum.NEGATIVE))
			{
				negative++;
			}
			
			if(currentEmotion != null && !currentEmotion.equals(EEmotionEnum.NEGATIVE))
			{
				emotionSwitched = true;
			}
			else
			{
				emotionSwitched = false;
			}
			
			currentEmotion = EEmotionEnum.NEGATIVE;
		}
		
		return currentEmotion;
	}
	
	private static void getTimes (String line)
	{
		if(line.matches(CParserConstants.SESSION_TIME_REGEXP))
		{				
			timeTotal += Long.parseLong(line.split("=")[1]);
		}
		else if(line.matches(CParserConstants.INACTIVITY_TIME_REGEXP))
		{
			timeTotal -= Long.parseLong(line.split("=")[1]);
			inactivityTimeTotal += Long.parseLong(line.split("=")[1]); 
		}
	}
	
	private static String getValueFromLog (String name, String line)
	{
		int nameLength = name.length();
		int nameIndex = line.indexOf(name) + nameLength;
		
		String value = line.substring(nameIndex);
		if(value.indexOf(',') == -1)
		{
			return value.substring(0);
		}
		return value.substring(0, value.indexOf(','));
		
	}

	private static CMyPoint createPoint (String line, long clickedTime)
	{
		CMyPoint startPoint = new CMyPoint();
		String[] coordinates = line.substring(line.indexOf("(")+1, line.indexOf(")")).split(",");
								
		startPoint.setLocation(Double.parseDouble(coordinates[0]), Double.parseDouble(coordinates[1]));
		if(line.matches(CParserConstants.LEFT_CLICK_REGEXP)){
			startPoint.setAction(EMouseActionEnum.LEFT_CLICK);
		}
		else if(line.matches(CParserConstants.RIGHT_CLICK_REGEXP)){
			startPoint.setAction(EMouseActionEnum.RIGHT_CLICK);
		}
		else if(line.matches(CParserConstants.MIDDLE_CLICK_REGEXP)){
			startPoint.setAction(EMouseActionEnum.MIDDLE_CLICK);
		}
		else if(line.matches(CParserConstants.MOUSE_DRAGGED_REGEXP)){						
			startPoint.setAction(EMouseActionEnum.DRAG_AND_DROP);
		}
		startPoint.setTimeStamp(clickedTime);
		
		int modifiersStartIndex = line.indexOf(CParserConstants.MODIFIERS_ATTR) + CParserConstants.MODIFIERS_ATTR.length();
		//int modifiersEndIndex = line.indexOf(modifiersStartIndex + CConstants.MODIFIERS.length());
		int clickcountStartIndex = line.indexOf(CParserConstants.CLICK_COUNT_ATTR) + CParserConstants.CLICK_COUNT_ATTR.length();
		//int clickcountEndIndex = line.indexOf(clickcountStartIndex + CConstants.CLICKCOUNT.length());
		
		String modifiersSubString = line.substring(modifiersStartIndex);
		String clickcountSubString = line.substring(clickcountStartIndex);
						
		modifiersSubString = modifiersSubString.substring(0, modifiersSubString.indexOf(','));
		clickcountSubString = clickcountSubString.substring(0, clickcountSubString.indexOf(','));
		
		startPoint.setClickCount(Integer.parseInt(clickcountSubString));
		startPoint.setModifiers(modifiersSubString);
		
		return startPoint;
	}
	
	private static CMyPoint createMoveOrScrollPoint (String line, long currentTimeStamp)
	{
		String[] coordinates = line.substring(line.indexOf("(")+1, line.indexOf(")")).split(",");						
		CMyPoint movePoint = new CMyPoint();
		movePoint.setLocation(Double.parseDouble(coordinates[0]), Double.parseDouble(coordinates[1]));
		movePoint.setTimeStamp(currentTimeStamp);
		
		if(line.matches(CParserConstants.MOUSE_MOVED_REGEXP)){					
			//((CMouseEvent)mouseEventsList.get(mouseEventsList.size()-1)).setEndPoint(null);
			movePoint.setAction(EMouseActionEnum.MOVEMENT);
		}						
		else if(line.matches(CParserConstants.MOUSE_WHEEL_REGEXP)){	
			if(line.matches(CParserConstants.MOUSE_SCROLL_UP_REGEXP))
			{
				movePoint.setAction(EMouseActionEnum.SCROLL_UP);
			}
			else if(line.matches(CParserConstants.MOUSE_SCROLL_DOWN_REGEXP))
			{
				movePoint.setAction(EMouseActionEnum.SCROLL_DOWN);
			}	
			else
			{
				movePoint.setAction(EMouseActionEnum.UNKNOWN_SCROLL_ACTION);
			}
		}
		else if(line.matches(CParserConstants.MOUSE_DRAGGED_REGEXP)){						
			movePoint.setAction(EMouseActionEnum.DRAG_AND_DROP);
		}
		
		return movePoint;
	}	
	
	private static void testingPrint()
	{
		System.out.println("neutral "+neutral);
		System.out.println("negative "+negative);
		System.out.println("positive "+positive);
		System.out.println("");
		
		long minutes = TimeUnit.MILLISECONDS.toMinutes(timeTotal);		
		
		System.out.println("time "+minutes);
		
		minutes = TimeUnit.MILLISECONDS.toMinutes(inactivityTimeTotal);
		
		System.out.println("inactivity "+minutes);
	}	
	
}
