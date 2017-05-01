package core;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import entities.CEvent;
import entities.CMouseEvent;
import utils.CParserUtils;

public class CMouseCharacteristicsFileWritter {
	
	private final static int EVENTS_COUNT = 10;
	
	
	public static void calculateCharacteristics(List<CEvent> mouseEventsList) throws IOException
	{
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
		
		for(int i=0; i<mouseEventsList.size(); i++)
		{
			
			if( i == 0 
					|| (i>0	&& mouseEventsList.get(i).getSession().equals(mouseEventsList.get(i-1).getSession())
					&& mouseEventsList.get(i).getUserName().equals(mouseEventsList.get(i-1).getUserName())
					&& count <= EVENTS_COUNT))
			{
				personName = mouseEventsList.get(i).getUserName();
				wsession = mouseEventsList.get(i).getSession();
				emotion = mouseEventsList.get(i).getEmotion().toString();				
				
				avgAcceleration += ((CMouseEvent) mouseEventsList.get(i)).getAcceleration();
				maxAcceleration = CParserUtils.getMax(maxAcceleration, ((CMouseEvent) mouseEventsList.get(i)).getAcceleration());
				minAcceleration = CParserUtils.getMin(minAcceleration, ((CMouseEvent) mouseEventsList.get(i)).getAcceleration());
				
				avgAngleDifference += ((CMouseEvent) mouseEventsList.get(i)).getAvgAngleDiff();
				maxAngleDifference = CParserUtils.getMax(maxAngleDifference, ((CMouseEvent) mouseEventsList.get(i)).getMaxAngleDiff());
				minAngleDifference = CParserUtils.getMin(minAngleDifference, ((CMouseEvent) mouseEventsList.get(i)).getAvgAngleDiff());
				maxAvgAngleDifference = CParserUtils.getMax(maxAvgAngleDifference, ((CMouseEvent) mouseEventsList.get(i)).getAvgAngleDiff());
				
				avgLineDistance += ((CMouseEvent) mouseEventsList.get(i)).getAvgLineDistance();
				maxLineDistance = CParserUtils.getMax(maxLineDistance, ((CMouseEvent) mouseEventsList.get(i)).getMaxLineDistance());
				minAvgLineDistance = CParserUtils.getMin(minAvgLineDistance, ((CMouseEvent) mouseEventsList.get(i)).getMinLineDistance());
				maxAvgLineDistance = CParserUtils.getMax(maxAvgLineDistance, ((CMouseEvent) mouseEventsList.get(i)).getAvgLineDistance());
				
				avgSpeed += ((CMouseEvent) mouseEventsList.get(i)).getAvgSpeed();
				maxSpeed = CParserUtils.getMax(maxSpeed, ((CMouseEvent) mouseEventsList.get(i)).getAvgSpeed());
				minSpeed = CParserUtils.getMin(minSpeed, ((CMouseEvent) mouseEventsList.get(i)).getAvgSpeed());
				
				avgDirectionChanges += ((CMouseEvent) mouseEventsList.get(i)).getDirectionChanges();
				maxDirectionChanges = CParserUtils.getMax(maxDirectionChanges, ((CMouseEvent) mouseEventsList.get(i)).getDirectionChanges());
				minDirectionChanges = CParserUtils.getMin(minDirectionChanges, ((CMouseEvent) mouseEventsList.get(i)).getDirectionChanges());
				
				avgFirstClickHoldTime += ((CMouseEvent) mouseEventsList.get(i)).getButtonHoldTime();
				maxFirstClickHoldTime = CParserUtils.getMax(maxFirstClickHoldTime, ((CMouseEvent) mouseEventsList.get(i)).getButtonHoldTime());
				minFirstClickHoldTime = CParserUtils.getMin(minFirstClickHoldTime, ((CMouseEvent) mouseEventsList.get(i)).getButtonHoldTime());
				
				avgIntersections += ((CMouseEvent) mouseEventsList.get(i)).getIntersections();
				maxIntersections = CParserUtils.getMax(maxIntersections, ((CMouseEvent) mouseEventsList.get(i)).getIntersections());
				minIntersections = CParserUtils.getMin(minIntersections, ((CMouseEvent) mouseEventsList.get(i)).getIntersections());
				
				avgMultiClicks += ((CMouseEvent) mouseEventsList.get(i)).getMultiClicks().size();
				maxMultiClicks = CParserUtils.getMax(maxMultiClicks, ((CMouseEvent) mouseEventsList.get(i)).getMultiClicks().size());
				
				timeAvg += ((CMouseEvent) mouseEventsList.get(i)).getTime();
				timeMax = CParserUtils.getMax(timeMax, ((CMouseEvent) mouseEventsList.get(i)).getTime());
				timeMin = CParserUtils.getMin(timeMin, ((CMouseEvent) mouseEventsList.get(i)).getTime());
				
				avgClickMoveTime += ((CMouseEvent) mouseEventsList.get(i)).getTimeClickMove();
				maxClickMoveTime = CParserUtils.getMax(maxClickMoveTime, ((CMouseEvent) mouseEventsList.get(i)).getTimeClickMove());
				minClickMoveTime = CParserUtils.getMin(minClickMoveTime, ((CMouseEvent) mouseEventsList.get(i)).getTimeClickMove());
				
				avgMoveClickTime += ((CMouseEvent) mouseEventsList.get(i)).getTimeMoveClick();
				maxMoveClickTime = CParserUtils.getMax(maxMoveClickTime, ((CMouseEvent) mouseEventsList.get(i)).getTimeMoveClick());
				minMoveClickTime = CParserUtils.getMin(minMoveClickTime, ((CMouseEvent) mouseEventsList.get(i)).getTimeMoveClick());
				
				avgMoveReleaseTime += ((CMouseEvent) mouseEventsList.get(i)).getTimeMoveRelease();
				maxMoveReleaseTime = CParserUtils.getMax(maxMoveReleaseTime, ((CMouseEvent) mouseEventsList.get(i)).getTimeMoveRelease());
				minMoveReleaseTime = CParserUtils.getMin(minMoveReleaseTime, ((CMouseEvent) mouseEventsList.get(i)).getTimeMoveRelease());
				
				avgReleaseMoveTime += ((CMouseEvent) mouseEventsList.get(i)).getTimeReleaseMove();
				maxReleaseMoveTime = CParserUtils.getMax(maxReleaseMoveTime, ((CMouseEvent) mouseEventsList.get(i)).getTimeReleaseMove());
				minReleaseMoveTime = CParserUtils.getMin(minReleaseMoveTime, ((CMouseEvent) mouseEventsList.get(i)).getTimeReleaseMove());
				
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
