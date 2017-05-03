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
		
	public static void calculateCharacteristics(List<CEvent> keyboardEventsList, List<Long> pauseTimeList, List<Long> windowSwitchedTimeList) throws IOException
	{
		double 	dwellTimeAvg = 0.0,
				dwellTimeMin = Double.MAX_VALUE,
				dwellTimeMax = 0.0;
		
		long 	digraphPP = 0, 
				digraphPR = 0, 
				digraphRP = 0, 
				digraphRR = 0,
		
				trigraphPP = 0, 
				trigraphPR = 0, 
				trigraphRP = 0, 
				trigraphRR = 0,
		
				wordDigraphPP = 0, 
				wordDigraphPR = 0, 
				wordDigraphRP = 0, 
				wordDigraphRR = 0,
		
				minDigraphPP = Long.MAX_VALUE, 
				minDigraphPR = Long.MAX_VALUE, 
				minDigraphRP = Long.MAX_VALUE, 
				minDigraphRR = Long.MAX_VALUE,
		
				minTrigraphPP = Long.MAX_VALUE, 
				minTrigraphPR = Long.MAX_VALUE, 
				minTrigraphRP = Long.MAX_VALUE, 
				minTrigraphRR = Long.MAX_VALUE,
		
				minWordDigraphPP = Long.MAX_VALUE, 
				minWordDigraphPR = Long.MAX_VALUE, 
				minWordDigraphRP = Long.MAX_VALUE, 
				minWordDigraphRR = Long.MAX_VALUE,
		
				maxDigraphPP = 0, 
				maxDigraphPR = 0, 
				maxDigraphRP = 0, 
				maxDigraphRR = 0,
		
				maxTrigraphPP = 0, 
				maxTrigraphPR = 0, 
				maxTrigraphRP = 0, 
				maxTrigraphRR = 0,
		
				maxWordDigraphPP = 0, 
				maxWordDigraphPR = 0, 
				maxWordDigraphRP = 0, 
				maxWordDigraphRR = 0,
		
				avgDigraphPP = 0, 
				avgDigraphPR = 0, 
				avgDigraphRP = 0, 
				avgDigraphRR = 0,
		
				avgTrigraphPP = 0, 
				avgTrigraphPR = 0, 
				avgTrigraphRP = 0, 
				avgTrigraphRR = 0,
		
				avgWordDigraphPP = 0, 
				avgWordDigraphPR = 0, 
				avgWordDigraphRP = 0, 
				avgWordDigraphRR = 0,
		
				startTimeStamp = 0,
				
				pauseTimesTotal = 0,
				maxPauseTime = 0,
				minPauseTime = Long.MAX_VALUE,
				avgPauseTime = 0,
				
				maxWindowTime = 0,
				minWindowTime = Long.MAX_VALUE,
				avgWindowTime = 0;
		
		int 	charPerSecond = 0,
		
				charPerSecondMax = 0,
				charPerSecondAvg = 0,
		
				timesCharPerSecond = 0,
				timesDigraph = 0,
				timesTrigraph = 0,
				timesWordgraph = 0,
				
				modifiersMax = 0,
				modifiersTotal = 0,
				
				misstakesTotal = 0,
				misstakesMax = 0,
		
				pauseCount = pauseTimeList.size(),
				windowSwitchedCount = windowSwitchedTimeList.size();
		
		
		for(int i=0; i<pauseTimeList.size(); i++)
		{
			pauseTimesTotal += pauseTimeList.get(i);
			maxPauseTime = CParserUtils.getMax(maxPauseTime, pauseTimeList.get(i));
			minPauseTime = CParserUtils.getMin(minPauseTime, pauseTimeList.get(i));
		}		
		
		for(int i=0; i<windowSwitchedTimeList.size(); i++)
		{
			avgWindowTime += windowSwitchedTimeList.get(i);
			maxWindowTime = CParserUtils.getMax(maxWindowTime, windowSwitchedTimeList.get(i));
			minWindowTime = CParserUtils.getMin(minWindowTime, windowSwitchedTimeList.get(i));
		}
		
		if(!pauseTimeList.isEmpty())
		{
			avgPauseTime = pauseTimesTotal / pauseTimeList.size();
		}
		
		if(!windowSwitchedTimeList.isEmpty())
		{
			avgWindowTime = avgWindowTime/ windowSwitchedTimeList.size();
		}		
		
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
					
					minDigraphPP = CParserUtils.getMin(digraphPP, minDigraphPP);
					minDigraphPR = CParserUtils.getMin(digraphPR, minDigraphPR);
					minDigraphRP = CParserUtils.getMin(digraphRP, minDigraphRP);
					minDigraphRR = CParserUtils.getMin(digraphRR, minDigraphRR);
					
					maxDigraphPP = CParserUtils.getMax(digraphPP, maxDigraphPP);
					maxDigraphPR = CParserUtils.getMax(digraphPR, maxDigraphPR);
					maxDigraphRP = CParserUtils.getMax(digraphRP, maxDigraphRP);
					maxDigraphRR = CParserUtils.getMax(digraphRR, maxDigraphRR);
					
					avgDigraphPP += digraphPP;
					avgDigraphPR += digraphPR;
					avgDigraphRP += digraphRP;
					avgDigraphRR += digraphRR;					
					
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
					
					minTrigraphPP = CParserUtils.getMin(trigraphPP, minTrigraphPP);
					minTrigraphPR = CParserUtils.getMin(trigraphPR, minTrigraphPR);
					minTrigraphRP = CParserUtils.getMin(trigraphRP, minTrigraphRP);
					minTrigraphRR = CParserUtils.getMin(trigraphRR, minTrigraphRR);
					
					maxTrigraphPP = CParserUtils.getMax(trigraphPP, maxTrigraphPP);
					maxTrigraphPR = CParserUtils.getMax(trigraphPR, maxTrigraphPR);
					maxTrigraphRP = CParserUtils.getMax(trigraphRP, maxTrigraphRP);
					maxTrigraphRR = CParserUtils.getMax(trigraphRR, maxTrigraphRR);
					
					avgTrigraphPP += trigraphPP;
					avgTrigraphPR += trigraphPR;
					avgTrigraphRP += trigraphRP;
					avgTrigraphRR += trigraphRR;
					
					
				}
//				((CKeyEvent) keyboardEventsList.get(i)).getKey(j).getPressedTime();
//				((CKeyEvent) keyboardEventsList.get(i)).getKey(j).getReleasedTime();
								
			}
			
			if((i+1) < keyboardEventsList.size())
			{
				timesWordgraph++;
				
				wordDigraphPP = CParserUtils.getTimeBetween(((CKeyEvent) keyboardEventsList.get(i)).getLastKey().getPressedTime()
						, ((CKeyEvent) keyboardEventsList.get(i+1)).getFirstKey().getPressedTime());
				
				wordDigraphPR = CParserUtils.getTimeBetween(((CKeyEvent) keyboardEventsList.get(i)).getLastKey().getPressedTime()
						, ((CKeyEvent) keyboardEventsList.get(i+1)).getFirstKey().getReleasedTime());
				
				wordDigraphRP = CParserUtils.getTimeBetween(((CKeyEvent) keyboardEventsList.get(i)).getLastKey().getReleasedTime()
						, ((CKeyEvent) keyboardEventsList.get(i+1)).getFirstKey().getPressedTime());
				
				wordDigraphRR = CParserUtils.getTimeBetween(((CKeyEvent) keyboardEventsList.get(i)).getLastKey().getReleasedTime()
						, ((CKeyEvent) keyboardEventsList.get(i+1)).getFirstKey().getReleasedTime());
				
				minWordDigraphPP = CParserUtils.getMin(wordDigraphPP, minWordDigraphPP);
				minWordDigraphPR = CParserUtils.getMin(wordDigraphPR, minWordDigraphPR);
				minWordDigraphRP = CParserUtils.getMin(wordDigraphRP, minWordDigraphRP);
				minWordDigraphRR = CParserUtils.getMin(wordDigraphRR, minWordDigraphRR);
				
				maxWordDigraphPP = CParserUtils.getMax(wordDigraphPP, maxWordDigraphPP);
				maxWordDigraphPR = CParserUtils.getMax(wordDigraphPR, maxWordDigraphPR);
				maxWordDigraphRP = CParserUtils.getMax(wordDigraphRP, maxWordDigraphRP);
				maxWordDigraphRR = CParserUtils.getMax(wordDigraphRR, maxWordDigraphRR);
				
				avgWordDigraphPP += wordDigraphPP;
				avgWordDigraphPR += wordDigraphPR;
				avgWordDigraphRP += wordDigraphRP;
				avgWordDigraphRR += wordDigraphRR;
			}
			
			keyboardEventsList.size();
			
			CParserUtils.getTimeBetween(((CKeyEvent) keyboardEventsList.get(i)).getLastKey().getReleasedTime()
					, ((CKeyEvent) keyboardEventsList.get(i)).getFirstKey().getPressedTime());
			
			misstakesTotal += ((CKeyEvent) keyboardEventsList.get(i)).getMisstakesCount();
			misstakesMax = CParserUtils.getMax(misstakesMax, ((CKeyEvent) keyboardEventsList.get(i)).getMisstakesCount());
			
			modifiersTotal += ((CKeyEvent) keyboardEventsList.get(i)).getModifiersCount();
			modifiersMax = CParserUtils.getMax(modifiersMax, ((CKeyEvent) keyboardEventsList.get(i)).getModifiersCount());
									
		}
				
		
				
		FileWriter writer = new FileWriter("result/newdata.csv");
		writeFileHeaders(writer);
		
		writer.append(""+keyboardEventsList.get(0).getUserName());
		writer.append(",");
		/*writer.append(""+wsession);
		writer.append(",");*/
		writer.append(""+keyboardEventsList.get(0).getEmotion());
		writer.append(",");
		
		writer.append(""+dwellTimeAvg/keyboardEventsList.size());
		writer.append(""+dwellTimeMin);
		writer.append(""+dwellTimeMax);
		writer.append(""+minDigraphPP);
		writer.append(""+minDigraphPR);
		writer.append(""+minDigraphRP);
		writer.append(""+minDigraphRR);
		writer.append(""+minTrigraphPP);
		writer.append(""+minTrigraphPR);
		writer.append(""+minTrigraphRP);
		writer.append(""+minTrigraphRR);
		writer.append(""+minWordDigraphPP);
		writer.append(""+minWordDigraphPR);
		writer.append(""+minWordDigraphRP);
		writer.append(""+minWordDigraphRR);
		writer.append(""+maxDigraphPP);
		writer.append(""+maxDigraphPR);
		writer.append(""+maxDigraphRP);
		writer.append(""+maxDigraphRR);
		writer.append(""+maxTrigraphPP);
		writer.append(""+maxTrigraphPR);
		writer.append(""+maxTrigraphRP);
		writer.append(""+maxTrigraphRR);
		writer.append(""+maxWordDigraphPP);
		writer.append(""+maxWordDigraphPR);
		writer.append(""+maxWordDigraphRP);
		writer.append(""+maxWordDigraphRR);
		writer.append(""+avgDigraphPP/timesDigraph);
		writer.append(""+avgDigraphPR/timesDigraph);
		writer.append(""+avgDigraphRP/timesDigraph);
		writer.append(""+avgDigraphRR/timesDigraph);
		writer.append(""+avgTrigraphPP/timesTrigraph);
		writer.append(""+avgTrigraphPR/timesTrigraph);
		writer.append(""+avgTrigraphRP/timesTrigraph);
		writer.append(""+avgTrigraphRR/timesTrigraph);
		writer.append(""+avgWordDigraphPP/timesWordgraph);
		writer.append(""+avgWordDigraphPR/timesWordgraph);
		writer.append(""+avgWordDigraphRP/timesWordgraph);
		writer.append(""+avgWordDigraphRR/timesWordgraph);
		writer.append(""+pauseTimesTotal);
		writer.append(""+maxPauseTime);
		writer.append(""+minPauseTime);
		writer.append(""+avgPauseTime/pauseCount);
		writer.append(""+maxWindowTime);
		writer.append(""+minWindowTime);
		writer.append(""+avgWindowTime);
		writer.append(""+charPerSecond);
		writer.append(""+charPerSecondMax);
		writer.append(""+charPerSecondAvg/timesCharPerSecond);
		writer.append(""+modifiersMax);
		writer.append(""+modifiersTotal);
		writer.append(""+misstakesTotal);
		writer.append(""+misstakesMax);
		writer.append(""+pauseCount);
		writer.append(""+windowSwitchedCount);
		
		
		writer.append('\n');
			
		writer.close();
	}
	
	private static void writeFileHeaders (FileWriter writter) throws IOException
	{		
		
		writter.append("name");
		writter.append(",");
		writter.append("emotion");
		writter.append(",");
		
		writter.append("dwellTimeAvg");
		writter.append(",");
		writter.append("dwellTimeMin");
		writter.append(",");
		writter.append("dwellTimeMax");
		writter.append(",");		
		writter.append("minDigraphPP");
		writter.append(",");
		writter.append("minDigraphPR");
		writter.append(",");
		writter.append("minDigraphRP");
		writter.append(",");
		writter.append("minDigraphRR");
		writter.append(",");
		writter.append("minTrigraphPP");
		writter.append(",");
		writter.append("minTrigraphPR");
		writter.append(",");
		writter.append("minTrigraphRP");
		writter.append(",");
		writter.append("minTrigraphRR");
		writter.append(",");
		writter.append("minWordDigraphPP");
		writter.append(",");
		writter.append("minWordDigraphPR");
		writter.append(",");
		writter.append("minWordDigraphRP");
		writter.append(",");
		writter.append("minWordDigraphRR");
		writter.append(",");
		writter.append("maxDigraphPP");
		writter.append(",");
		writter.append("maxDigraphPR");
		writter.append(",");
		writter.append("maxDigraphRP");
		writter.append(",");
		writter.append("maxDigraphRR");
		writter.append(",");
		writter.append("maxTrigraphPP");
		writter.append(",");
		writter.append("maxTrigraphPR");
		writter.append(",");
		writter.append("maxTrigraphRP");
		writter.append(",");
		writter.append("maxTrigraphRR");
		writter.append(",");
		writter.append("maxWordDigraphPP");
		writter.append(",");
		writter.append("maxWordDigraphPR");
		writter.append(",");
		writter.append("maxWordDigraphRP");
		writter.append(",");
		writter.append("maxWordDigraphRR");
		writter.append(",");
		writter.append("avgDigraphPP");
		writter.append(",");
		writter.append("avgDigraphPR");
		writter.append(",");
		writter.append("avgDigraphRP");
		writter.append(",");
		writter.append("avgDigraphRR");
		writter.append(",");
		writter.append("avgTrigraphPP");
		writter.append(",");
		writter.append("avgTrigraphPR");
		writter.append(",");
		writter.append("avgTrigraphRP");
		writter.append(",");
		writter.append("avgTrigraphRR");
		writter.append(",");
		writter.append("avgWordDigraphPP");
		writter.append(",");
		writter.append("avgWordDigraphPR");
		writter.append(",");
		writter.append("avgWordDigraphRP");
		writter.append(",");
		writter.append("avgWordDigraphRR");
		writter.append(",");
		writter.append("pauseTimesTotal");
		writter.append(",");
		writter.append("maxPauseTime");
		writter.append(",");
		writter.append("minPauseTime");
		writter.append(",");
		writter.append("avgPauseTime");
		writter.append(",");
		writter.append("maxWindowTime");
		writter.append(",");
		writter.append("minWindowTime");
		writter.append(",");
		writter.append("avgWindowTime");
		writter.append(",");
		writter.append("charPerSecond");
		writter.append(",");
		writter.append("charPerSecondMax");
		writter.append(",");
		writter.append("charPerSecondAvg");
		writter.append(",");
		writter.append("modifiersMax");
		writter.append(",");
		writter.append("modifiersAvg");
		writter.append(",");
		writter.append("misstakesTotal");
		writter.append(",");
		writter.append("misstakesMax");
		writter.append(",");
		writter.append("pauseCount");
		writter.append(",");
		writter.append("windowSwitchedCount");
		writter.append('\n');
	}
}
