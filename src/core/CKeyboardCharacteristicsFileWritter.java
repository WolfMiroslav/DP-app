package core;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import entities.CEvent;
import entities.CKeyEvent;
import entities.CMouseEvent;
import utils.CParserUtils;

public class CKeyboardCharacteristicsFileWritter {
	
	private final static int EVENTS_COUNT = 10;
		
	public static void calculateCharacteristics(List<CEvent> keyboardEventsList) throws IOException
	{
		double 	dwellTimeAvg = 0.0,
				dwellTimeMin = Double.MAX_VALUE,
				dwellTimeMax = 0.0;
		
		long 	digraphPP = 0, 
				digraphPR = 0, 
				digraphRP = 0, 
				digraphRR = 0;
		
		long 	trigraphPP = 0, 
				trigraphPR = 0, 
				trigraphRP = 0, 
				trigraphRR = 0;
		
		long 	wordDigraphPP = 0, 
				wordDigraphPR = 0, 
				wordDigraphRP = 0, 
				wordDigraphRR = 0;
		
		long 	minDigraphPP = Long.MAX_VALUE, 
				minDigraphPR = Long.MAX_VALUE, 
				minDigraphRP = Long.MAX_VALUE, 
				minDigraphRR = Long.MAX_VALUE;
		
		long 	minTrigraphPP = Long.MAX_VALUE, 
				minTrigraphPR = Long.MAX_VALUE, 
				minTrigraphRP = Long.MAX_VALUE, 
				minTrigraphRR = Long.MAX_VALUE;
		
		long 	minWordDigraphPP = Long.MAX_VALUE, 
				minWordDigraphPR = Long.MAX_VALUE, 
				minWordDigraphRP = Long.MAX_VALUE, 
				minWordDigraphRR = Long.MAX_VALUE;
		
		long	maxDigraphPP = 0, 
				maxDigraphPR = 0, 
				maxDigraphRP = 0, 
				maxDigraphRR = 0;
		
		long 	maxTrigraphPP = 0, 
				maxTrigraphPR = 0, 
				maxTrigraphRP = 0, 
				maxTrigraphRR = 0;
		
		long 	maxWordDigraphPP = 0, 
				maxWordDigraphPR = 0, 
				maxWordDigraphRP = 0, 
				maxWordDigraphRR = 0;
		
		long 	avgDigraphPP = 0, 
				avgDigraphPR = 0, 
				avgDigraphRP = 0, 
				avgDigraphRR = 0;
		
		long 	avgTrigraphPP = 0, 
				avgTrigraphPR = 0, 
				avgTrigraphRP = 0, 
				avgTrigraphRR = 0;
		
		long 	avgWordDigraphPP = 0, 
				avgWordDigraphPR = 0, 
				avgWordDigraphRP = 0, 
				avgWordDigraphRR = 0;
		
		long 	startTimeStamp = 0;
		int 	charPerSecond = 0;
		
		int 	charPerSecondMax = 0,
				charPerSecondAvg = 0;
		
		int 	timesCharPerSecond = 0,
				timesDigraph = 0,
				timesTrigraph = 0;
		
		
		for(int i=0;i<keyboardEventsList.size();i++)
		{
			for(int j=0;j<((CKeyEvent) keyboardEventsList.get(i)).getAllKeys().size();j++)
			{
				dwellTimeAvg += ((CKeyEvent) keyboardEventsList.get(i)).getKey(j).getDwellTime();
				dwellTimeMax = CParserUtils.getMax(dwellTimeMax, ((CKeyEvent) keyboardEventsList.get(i)).getKey(j).getDwellTime());
				dwellTimeMin = CParserUtils.getMin(dwellTimeMin, ((CKeyEvent) keyboardEventsList.get(i)).getKey(j).getDwellTime());
				
				if((j+1) < ((CKeyEvent) keyboardEventsList.get(i)).getAllKeys().size())
				{
					timesDigraph++;
					
					digraphPP = CParserUtils.getTimeBetween(((CKeyEvent) keyboardEventsList.get(i)).getKey(j).getPressedTime(),
							((CKeyEvent) keyboardEventsList.get(i)).getKey(j+1).getPressedTime());
					
					digraphPR = CParserUtils.getTimeBetween(((CKeyEvent) keyboardEventsList.get(i)).getKey(j).getPressedTime(),
							((CKeyEvent) keyboardEventsList.get(i)).getKey(j+1).getReleasedTime());
					
					digraphRP = CParserUtils.getTimeBetween(((CKeyEvent) keyboardEventsList.get(i)).getKey(j).getReleasedTime(),
							((CKeyEvent) keyboardEventsList.get(i)).getKey(j+1).getPressedTime());
					
					digraphRR = CParserUtils.getTimeBetween(((CKeyEvent) keyboardEventsList.get(i)).getKey(j).getReleasedTime(),
							((CKeyEvent) keyboardEventsList.get(i)).getKey(j+1).getReleasedTime());
					
					if(startTimeStamp == 0)
					{
						startTimeStamp = ((CKeyEvent) keyboardEventsList.get(i)).getKey(j).getPressedTime();
					}
					
					if ((startTimeStamp - ((CKeyEvent) keyboardEventsList.get(i)).getKey(j).getPressedTime()) < 1000)
					{
						charPerSecond++;
					}
					else 
					{
						timesCharPerSecond++;
						charPerSecondAvg += charPerSecond;
						charPerSecondMax = CParserUtils.getMax(charPerSecondMax, charPerSecond);
						
						startTimeStamp = 0;
					}
				}
				if((j+2) < ((CKeyEvent) keyboardEventsList.get(i)).getAllKeys().size())
				{
					timesTrigraph++;
					
					trigraphPP = CParserUtils.getTimeBetween(((CKeyEvent) keyboardEventsList.get(i)).getKey(j).getPressedTime(),
							((CKeyEvent) keyboardEventsList.get(i)).getKey(j+2).getPressedTime());
					
					trigraphPR = CParserUtils.getTimeBetween(((CKeyEvent) keyboardEventsList.get(i)).getKey(j).getPressedTime(),
							((CKeyEvent) keyboardEventsList.get(i)).getKey(j+2).getReleasedTime());
					
					trigraphRP = CParserUtils.getTimeBetween(((CKeyEvent) keyboardEventsList.get(i)).getKey(j).getReleasedTime(),
							((CKeyEvent) keyboardEventsList.get(i)).getKey(j+2).getPressedTime());
					
					trigraphRR = CParserUtils.getTimeBetween(((CKeyEvent) keyboardEventsList.get(i)).getKey(j).getReleasedTime(),
							((CKeyEvent) keyboardEventsList.get(i)).getKey(j+2).getReleasedTime());
					
					
				}
//				((CKeyEvent) keyboardEventsList.get(i)).getKey(j).getPressedTime();
//				((CKeyEvent) keyboardEventsList.get(i)).getKey(j).getReleasedTime();
								
			}
			
			if((i+1) < keyboardEventsList.size())
			{
				wordDigraphPP = CParserUtils.getTimeBetween(((CKeyEvent) keyboardEventsList.get(i)).getLastKey().getPressedTime()
						, ((CKeyEvent) keyboardEventsList.get(i+1)).getFirstKey().getPressedTime());
				
				wordDigraphPR = CParserUtils.getTimeBetween(((CKeyEvent) keyboardEventsList.get(i)).getLastKey().getPressedTime()
						, ((CKeyEvent) keyboardEventsList.get(i+1)).getFirstKey().getReleasedTime());
				
				wordDigraphRP = CParserUtils.getTimeBetween(((CKeyEvent) keyboardEventsList.get(i)).getLastKey().getReleasedTime()
						, ((CKeyEvent) keyboardEventsList.get(i+1)).getFirstKey().getPressedTime());
				
				wordDigraphRR = CParserUtils.getTimeBetween(((CKeyEvent) keyboardEventsList.get(i)).getLastKey().getReleasedTime()
						, ((CKeyEvent) keyboardEventsList.get(i+1)).getFirstKey().getReleasedTime());
			}
			
			keyboardEventsList.size();
			
			CParserUtils.getTimeBetween(((CKeyEvent) keyboardEventsList.get(i)).getLastKey().getReleasedTime()
					, ((CKeyEvent) keyboardEventsList.get(i)).getFirstKey().getPressedTime());
			
			((CKeyEvent) keyboardEventsList.get(i)).getMisstakesCount();
			((CKeyEvent) keyboardEventsList.get(i)).getModifiersCount();
			
			// pocet znakov za sekundu
			
		}
		
		
		
		
		
		
		
		int count = 0;
		//boolean incomplete = false;
		
		String personName = null;
		String wsession = null;
		String emotion = null;
		
		
		double avgAcceleration = 0;
		double maxAcceleration = 0;
		double minAcceleration = Double.MAX_VALUE;
		
		double avgAngleDifference = 0;
		double maxAngleDifference = 0;
		double minAngleDifference = Double.MAX_VALUE;
		double maxAvgAngleDifference = 0;
		
		double avgLineDistance = 0;
		double maxLineDistance = 0;
		double minAvgLineDistance = Double.MAX_VALUE;
		double maxAvgLineDistance = 0;
		
		double avgSpeed = 0;
		double maxSpeed = 0;
		double minSpeed = Double.MAX_VALUE;
		
		double avgDirectionChanges = 0;
		int maxDirectionChanges = 0;
		int minDirectionChanges = Integer.MAX_VALUE;
		
		double avgFirstClickHoldTime = 0;
		long maxFirstClickHoldTime = 0;
		long minFirstClickHoldTime = Long.MAX_VALUE;
		
		double avgIntersections = 0;
		int maxIntersections = 0;
		int minIntersections = Integer.MAX_VALUE;
		
		int maxMultiClicks = 0;
		double avgMultiClicks = 0;
		
		double timeAvg = 0;
		double timeMax = 0;
		double timeMin = Double.MAX_VALUE;
		
		double avgClickMoveTime = 0;
		long maxClickMoveTime = 0;
		long minClickMoveTime = Long.MAX_VALUE;
		
		double avgMoveClickTime = 0;
		long maxMoveClickTime = 0;
		long minMoveClickTime = Long.MAX_VALUE;
		
		double avgMoveReleaseTime = 0;
		long maxMoveReleaseTime = 0;
		long minMoveReleaseTime = Long.MAX_VALUE;
		
		double avgReleaseMoveTime = 0;
		long maxReleaseMoveTime = 0;
		long minReleaseMoveTime = Long.MAX_VALUE;
				
		FileWriter writer = new FileWriter("result/newdata.csv");
		writeFileHeaders(writer);
		
		for(int i=0; i<keyboardEventsList.size(); i++)
		{
			
			if( i == 0 
					|| (i>0	&& keyboardEventsList.get(i).getSession().equals(keyboardEventsList.get(i-1).getSession())
					&& keyboardEventsList.get(i).getUserName().equals(keyboardEventsList.get(i-1).getUserName())
					&& count <= EVENTS_COUNT))
			{
				personName = keyboardEventsList.get(i).getUserName();
				wsession = keyboardEventsList.get(i).getSession();
				emotion = keyboardEventsList.get(i).getEmotion().toString();				
				
				avgAcceleration += ((CMouseEvent) keyboardEventsList.get(i)).getAcceleration();
				maxAcceleration = CParserUtils.getMax(maxAcceleration, ((CMouseEvent) keyboardEventsList.get(i)).getAcceleration());
				minAcceleration = CParserUtils.getMin(minAcceleration, ((CMouseEvent) keyboardEventsList.get(i)).getAcceleration());
				
				avgAngleDifference += ((CMouseEvent) keyboardEventsList.get(i)).getAvgAngleDiff();
				maxAngleDifference = CParserUtils.getMax(maxAngleDifference, ((CMouseEvent) keyboardEventsList.get(i)).getMaxAngleDiff());
				minAngleDifference = CParserUtils.getMin(minAngleDifference, ((CMouseEvent) keyboardEventsList.get(i)).getAvgAngleDiff());
				maxAvgAngleDifference = CParserUtils.getMax(maxAvgAngleDifference, ((CMouseEvent) keyboardEventsList.get(i)).getAvgAngleDiff());
				
				avgLineDistance += ((CMouseEvent) keyboardEventsList.get(i)).getAvgLineDistance();
				maxLineDistance = CParserUtils.getMax(maxLineDistance, ((CMouseEvent) keyboardEventsList.get(i)).getMaxLineDistance());
				minAvgLineDistance = CParserUtils.getMin(minAvgLineDistance, ((CMouseEvent) keyboardEventsList.get(i)).getMinLineDistance());
				maxAvgLineDistance = CParserUtils.getMax(maxAvgLineDistance, ((CMouseEvent) keyboardEventsList.get(i)).getAvgLineDistance());
				
				avgSpeed += ((CMouseEvent) keyboardEventsList.get(i)).getAvgSpeed();
				maxSpeed = CParserUtils.getMax(maxSpeed, ((CMouseEvent) keyboardEventsList.get(i)).getAvgSpeed());
				minSpeed = CParserUtils.getMin(minSpeed, ((CMouseEvent) keyboardEventsList.get(i)).getAvgSpeed());
				
				avgDirectionChanges += ((CMouseEvent) keyboardEventsList.get(i)).getDirectionChanges();
				maxDirectionChanges = CParserUtils.getMax(maxDirectionChanges, ((CMouseEvent) keyboardEventsList.get(i)).getDirectionChanges());
				minDirectionChanges = CParserUtils.getMin(minDirectionChanges, ((CMouseEvent) keyboardEventsList.get(i)).getDirectionChanges());
				
				avgFirstClickHoldTime += ((CMouseEvent) keyboardEventsList.get(i)).getButtonHoldTime();
				maxFirstClickHoldTime = CParserUtils.getMax(maxFirstClickHoldTime, ((CMouseEvent) keyboardEventsList.get(i)).getButtonHoldTime());
				minFirstClickHoldTime = CParserUtils.getMin(minFirstClickHoldTime, ((CMouseEvent) keyboardEventsList.get(i)).getButtonHoldTime());
				
				avgIntersections += ((CMouseEvent) keyboardEventsList.get(i)).getIntersections();
				maxIntersections = CParserUtils.getMax(maxIntersections, ((CMouseEvent) keyboardEventsList.get(i)).getIntersections());
				minIntersections = CParserUtils.getMin(minIntersections, ((CMouseEvent) keyboardEventsList.get(i)).getIntersections());
				
				avgMultiClicks += ((CMouseEvent) keyboardEventsList.get(i)).getMultiClicks().size();
				maxMultiClicks = CParserUtils.getMax(maxMultiClicks, ((CMouseEvent) keyboardEventsList.get(i)).getMultiClicks().size());
				
				timeAvg += ((CMouseEvent) keyboardEventsList.get(i)).getTime();
				timeMax = CParserUtils.getMax(timeMax, ((CMouseEvent) keyboardEventsList.get(i)).getTime());
				timeMin = CParserUtils.getMin(timeMin, ((CMouseEvent) keyboardEventsList.get(i)).getTime());
				
				avgClickMoveTime += ((CMouseEvent) keyboardEventsList.get(i)).getTimeClickMove();
				maxClickMoveTime = CParserUtils.getMax(maxClickMoveTime, ((CMouseEvent) keyboardEventsList.get(i)).getTimeClickMove());
				minClickMoveTime = CParserUtils.getMin(minClickMoveTime, ((CMouseEvent) keyboardEventsList.get(i)).getTimeClickMove());
				
				avgMoveClickTime += ((CMouseEvent) keyboardEventsList.get(i)).getTimeMoveClick();
				maxMoveClickTime = CParserUtils.getMax(maxMoveClickTime, ((CMouseEvent) keyboardEventsList.get(i)).getTimeMoveClick());
				minMoveClickTime = CParserUtils.getMin(minMoveClickTime, ((CMouseEvent) keyboardEventsList.get(i)).getTimeMoveClick());
				
				avgMoveReleaseTime += ((CMouseEvent) keyboardEventsList.get(i)).getTimeMoveRelease();
				maxMoveReleaseTime = CParserUtils.getMax(maxMoveReleaseTime, ((CMouseEvent) keyboardEventsList.get(i)).getTimeMoveRelease());
				minMoveReleaseTime = CParserUtils.getMin(minMoveReleaseTime, ((CMouseEvent) keyboardEventsList.get(i)).getTimeMoveRelease());
				
				avgReleaseMoveTime += ((CMouseEvent) keyboardEventsList.get(i)).getTimeReleaseMove();
				maxReleaseMoveTime = CParserUtils.getMax(maxReleaseMoveTime, ((CMouseEvent) keyboardEventsList.get(i)).getTimeReleaseMove());
				minReleaseMoveTime = CParserUtils.getMin(minReleaseMoveTime, ((CMouseEvent) keyboardEventsList.get(i)).getTimeReleaseMove());
				
				count++;
			}
			else
			{
				if(count < EVENTS_COUNT)
				{
					//incomplete = true;
				}
				else
				{
					
					writer.append(""+personName);
					writer.append(",");
					writer.append(""+wsession);
					writer.append(",");
					writer.append(""+emotion);
					writer.append(",");
					
					writer.append(""+avgAcceleration / EVENTS_COUNT);
					writer.append(",");
					writer.append(""+maxAcceleration);
					writer.append(",");
					writer.append(""+minAcceleration);
					writer.append(",");
					writer.append(""+avgAngleDifference / EVENTS_COUNT);
					writer.append(",");
					writer.append(""+maxAngleDifference);
					writer.append(",");
					writer.append(""+minAngleDifference);
					writer.append(",");
					writer.append(""+maxAvgAngleDifference);
					writer.append(",");
					writer.append(""+avgLineDistance / EVENTS_COUNT);
					writer.append(",");
					writer.append(""+maxLineDistance);
					writer.append(",");
					writer.append(""+minAvgLineDistance);
					writer.append(",");
					writer.append(""+maxAvgLineDistance);
					writer.append(",");
					writer.append(""+avgSpeed / EVENTS_COUNT);
					writer.append(",");
					writer.append(""+maxSpeed);
					writer.append(",");
					writer.append(""+minSpeed);
					writer.append(",");
					writer.append(""+avgDirectionChanges / EVENTS_COUNT);
					writer.append(",");
					writer.append(""+maxDirectionChanges);
					writer.append(",");
					writer.append(""+minDirectionChanges);
					writer.append(",");
					writer.append(""+avgFirstClickHoldTime / EVENTS_COUNT);
					writer.append(",");
					writer.append(""+maxFirstClickHoldTime);
					writer.append(",");
					writer.append(""+minFirstClickHoldTime);
					writer.append(",");
					writer.append(""+avgIntersections / EVENTS_COUNT);
					writer.append(",");
					writer.append(""+maxIntersections);
					writer.append(",");
					writer.append(""+minIntersections);
					writer.append(",");
					writer.append(""+maxMultiClicks);
					writer.append(",");
					writer.append(""+avgMultiClicks / EVENTS_COUNT);
					writer.append(",");
					writer.append(""+timeAvg);
					writer.append(",");
					writer.append(""+timeMax);
					writer.append(",");
					writer.append(""+timeMin);
					writer.append(",");
					writer.append(""+avgClickMoveTime / EVENTS_COUNT);
					writer.append(",");
					writer.append(""+maxClickMoveTime);
					writer.append(",");
					writer.append(""+minClickMoveTime);
					writer.append(",");
					writer.append(""+avgMoveClickTime / EVENTS_COUNT);
					writer.append(",");
					writer.append(""+maxMoveClickTime);
					writer.append(",");
					writer.append(""+minMoveClickTime);
					writer.append(",");
					writer.append(""+avgMoveReleaseTime / EVENTS_COUNT);
					writer.append(",");
					writer.append(""+maxMoveReleaseTime);
					writer.append(",");
					writer.append(""+minMoveReleaseTime);
					writer.append(",");
					writer.append(""+avgReleaseMoveTime / EVENTS_COUNT);
					writer.append(",");
					writer.append(""+maxReleaseMoveTime);
					writer.append(",");
					writer.append(""+minReleaseMoveTime);
					writer.append('\n');
					
					personName = null;
					wsession = null;
					emotion = null;
					
					avgAcceleration = 0;
					maxAcceleration = 0;
					minAcceleration = Double.MAX_VALUE;
						
					avgAngleDifference = 0;
					maxAngleDifference = 0;
					minAngleDifference = Double.MAX_VALUE;
					maxAvgAngleDifference = 0;

					avgLineDistance = 0;
					maxLineDistance = 0;
					minAvgLineDistance = Double.MAX_VALUE;
					maxAvgLineDistance = 0;

					avgSpeed = 0;
					maxSpeed = 0;
					minSpeed = Double.MAX_VALUE;

					avgDirectionChanges = 0;
					maxDirectionChanges = 0;
					minDirectionChanges = Integer.MAX_VALUE;

					avgFirstClickHoldTime = 0;
					maxFirstClickHoldTime = 0;
					minFirstClickHoldTime = Long.MAX_VALUE;

					avgIntersections = 0;
					maxIntersections = 0;
					minIntersections = Integer.MAX_VALUE;

					maxMultiClicks = 0;
					avgMultiClicks = 0;

					timeAvg = 0;
					timeMax = 0;
					timeMin = Double.MAX_VALUE;

					avgClickMoveTime = 0;
					maxClickMoveTime = 0;
					minClickMoveTime = Long.MAX_VALUE;
						
					avgMoveClickTime = 0;
					maxMoveClickTime = 0;
					minMoveClickTime = Long.MAX_VALUE;;
						
					avgMoveReleaseTime = 0;
					maxMoveReleaseTime = 0;
					minMoveReleaseTime = Long.MAX_VALUE;;
						
					avgReleaseMoveTime = 0;
					maxReleaseMoveTime = 0;
					minReleaseMoveTime = Long.MAX_VALUE;
					
				}
					
				count = 0;
			}									
		}
		
		writer.close();
	}
	
	private static void writeFileHeaders (FileWriter writer) throws IOException
	{		
		
		writer.append("name");
		writer.append(",");
		writer.append("session");
		writer.append(",");
		writer.append("emotion");
		writer.append(",");
		
		writer.append("avgAcceleration");
		writer.append(",");
		writer.append("maxAcceleration");
		writer.append(",");
		writer.append("minAcceleration");
		writer.append(",");
		writer.append("avgAngleDifference");
		writer.append(",");
		writer.append("maxAngleDifference");
		writer.append(",");
		writer.append("minAngleDifference");
		writer.append(",");
		writer.append("maxAvgAngleDifference");
		writer.append(",");
		writer.append("avgLineDistance");
		writer.append(",");
		writer.append("maxLineDistance");
		writer.append(",");
		writer.append("minAvgLineDistance");
		writer.append(",");
		writer.append("maxAvgLineDistance");
		writer.append(",");
		writer.append("avgSpeed");
		writer.append(",");
		writer.append("maxSpeed");
		writer.append(",");
		writer.append("minSpeed");
		writer.append(",");
		writer.append("avgDirectionChanges");
		writer.append(",");
		writer.append("maxDirectionChanges");
		writer.append(",");
		writer.append("minDirectionChanges");
		writer.append(",");
		writer.append("avgFirstClickHoldTime");
		writer.append(",");
		writer.append("maxFirstClickHoldTime");
		writer.append(",");
		writer.append("minFirstClickHoldTime");
		writer.append(",");
		writer.append("avgIntersections");
		writer.append(",");
		writer.append("maxIntersections");
		writer.append(",");
		writer.append("minIntersections");
		writer.append(",");
		writer.append("maxMultiClicks");
		writer.append(",");
		writer.append("avgMultiClicks");
		writer.append(",");
		writer.append("timeAvg");
		writer.append(",");
		writer.append("timeMax");
		writer.append(",");
		writer.append("timeMin");
		writer.append(",");
		writer.append("avgClickMoveTime");
		writer.append(",");
		writer.append("maxClickMoveTime");
		writer.append(",");
		writer.append("minClickMoveTime");
		writer.append(",");
		writer.append("avgMoveClickTime");
		writer.append(",");
		writer.append("maxMoveClickTime");
		writer.append(",");
		writer.append("minMoveClickTime");
		writer.append(",");
		writer.append("avgMoveReleaseTime");
		writer.append(",");
		writer.append("maxMoveReleaseTime");
		writer.append(",");
		writer.append("minMoveReleaseTime");
		writer.append(",");
		writer.append("avgReleaseMoveTime");
		writer.append(",");
		writer.append("maxReleaseMoveTime");
		writer.append(",");
		writer.append("minReleaseMoveTime");
		writer.append('\n');
	}
}
