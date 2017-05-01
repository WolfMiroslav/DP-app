package entities;

import java.awt.geom.Line2D;
import java.util.ArrayList;

import enums.EMouseActionEnum;
import enums.EMouseDirectionEnum;
import utils.CParserUtils;

public class CMouseEvent extends CEvent
{	
	private CMousePair<CMyPoint, CMyPoint> startPoint;
	private CMousePair<CMyPoint, CMyPoint> endPoint;
	
	private ArrayList<CMousePair<CMyPoint, CMyPoint>> multiclicks = new ArrayList<CMousePair<CMyPoint,CMyPoint>>();
	
	private Double time;
	private ArrayList<CMyPoint> points = new ArrayList<CMyPoint>();
				
	//private double startSpeed;
	//private double midSpeed;
	//private double endSpeed;
	private double avgSpeed;
	private double acceleration;
	//private double accelerationStart;
	//private double accelerationMid;
	//private double accelerationEnd;
	private double flySpeed;
	private double flyDistance;
	
	private long firstClickHoldTime;
	private long timeReleaseMove;
	private long timeMoveClick;
	private long timeMoveRelease;
	private long timeClickMove;	
	
	private long pauseTotalLength;
	private long pauseMaxLength;
	private long pauseMinLength;
	private int pauseNum;
	
	private double moveLength;
	private double minLineDistance;
	private double maxLineDistance;
	private double avgLineDistance;
		
	private double minAngleDiff;
	private double maxAngleDiff;
	private double avgAngleDiff;
	private double flyAngle;
	
	private int directionChanges;	
	private int intersections;	
		
	public CMousePair<CMyPoint, CMyPoint> getStartPoint() 
	{
		/*CMyPoint startPoint = new CMyPoint();
		
		startPoint.setLocation(this.startPoint.getP().getX(), this.startPoint.getP().getY());
		startPoint.setTimeStamp(this.startPoint.getP().getTimeStamp());*/
		
		return startPoint;
	}

	public void setStartPoint(CMousePair<CMyPoint, CMyPoint> startPoint) 
	{
		this.startPoint = startPoint;
		firstClickHoldTime = CParserUtils.getTimeBetween(startPoint.getR().getTimeStamp(), startPoint.getP().getTimeStamp());
	}

	public CMousePair<CMyPoint, CMyPoint> getEndPoint() 
	{
		//CMyPoint endPoint = new CMyPoint();
		
		/*endPoint.setLocation(this.endPoint.getX(), this.endPoint.getY());
		endPoint.setTimeStamp(this.endPoint.getTimeStamp());*/
		
		return endPoint;
	}

	public void setEndPoint(CMousePair<CMyPoint, CMyPoint> endPoint) 
	{
		this.endPoint = endPoint;
	}

	public Double getTotalTime () 
	{		
		return time;
	}
	
	public long getButtonHoldTime() {
		return firstClickHoldTime;
	}

	public long getTimeReleaseMove() {
		return timeReleaseMove;
	}

	public void setTimeReleaseMove(long timeReleaseMove) {
		this.timeReleaseMove = timeReleaseMove;
	}

	public long getTimeMoveClick() {
		return timeMoveClick;
	}

	public void setTimeMoveClick(long timeMoveClick) {
		this.timeMoveClick = timeMoveClick;
	}

	public long getTimeMoveRelease() {
		return timeMoveRelease;
	}

	public void setTimeLastMoveRelease(long timeMoveRelease) {
		this.timeMoveRelease = timeMoveRelease;
	}

	public long getTimeClickMove() {
		return timeClickMove;
	}

	public void setTimeClickMove(long timeClickMove) {
		this.timeClickMove = timeClickMove;
	}

	public Double getTime() {
		return time;
	}

	public void setTime(Double time) {
		this.time = time;
	}

	public long getPauseTotalLength() {
		return pauseTotalLength;
	}

	public void setPauseTotalLength(long pauseTotalLength) {
		this.pauseTotalLength = pauseTotalLength;
	}

	public long getPauseMaxLength() {
		return pauseMaxLength;
	}

	public void setPauseMaxLength(long pauseMaxLength) {
		this.pauseMaxLength = pauseMaxLength;
	}

	public long getPauseMinLength() {
		return pauseMinLength;
	}

	public void setPauseMinLength(long pauseMinLength) {
		this.pauseMinLength = pauseMinLength;
	}

	public int getPauseNum() {
		return pauseNum;
	}

	public void setPauseNum(int pauseNum) {
		this.pauseNum = pauseNum;
	}

	public void addClicksToList (CMousePair<CMyPoint, CMyPoint> click)
	{
		multiclicks.add(click);
	}
	
	public ArrayList<CMousePair<CMyPoint, CMyPoint>> getMultiClicks ()
	{
		return multiclicks;
	}
	
	public void addPointToPointsList(CMyPoint point)
	{
		points.add(point);
	}
	
	
	

	public CMyPoint getLastPoint() 
	{
		if(points.isEmpty())
		{
			return null;
		}
		else
		{
			CMyPoint lastPoint = new CMyPoint();
			
			lastPoint.setLocation(points.get(points.size()-1).getX(), points.get(points.size()-1).getY());
			lastPoint.setTimeStamp(points.get(points.size()-1).getTimeStamp());
			
			return lastPoint;
		}		
	}
		
	private Line2D getEventLine () 
	{			
		return new Line2D.Double(startPoint.getP(), endPoint.getR());		
	}
	
	public void calculateCharacteristics ()
	{
		super.setTimeStart(startPoint.getP().getTimeStamp());
		super.setTimeEnd(endPoint.getR().getTimeStamp());
		
		if(!points.isEmpty() && points.size() > 1)
		{	
			if(points.get(points.size()-1).getAction().equals(EMouseActionEnum.DRAG_AND_DROP))
			{
				timeMoveClick = 0;
			}
			else
			{
				timeMoveClick = CParserUtils.getTimeBetween(endPoint.getP().getTimeStamp(), points.get(points.size()-1).getTimeStamp());
			}			
			
			timeMoveRelease = CParserUtils.getTimeBetween(endPoint.getR().getTimeStamp(), points.get(points.size()-1).getTimeStamp());
			
			if(!multiclicks.isEmpty())
			{
				timeReleaseMove = CParserUtils.getTimeBetween(points.get(0).getTimeStamp(), multiclicks.get(multiclicks.size()-1).getR().getTimeStamp());
				timeClickMove = CParserUtils.getTimeBetween(points.get(0).getTimeStamp(), multiclicks.get(multiclicks.size()-1).getP().getTimeStamp());				
			}
			else
			{
				if(!startPoint.getR().getAction().equals(EMouseActionEnum.DRAG_AND_DROP))
				{
					timeReleaseMove = CParserUtils.getTimeBetween(points.get(0).getTimeStamp(), startPoint.getR().getTimeStamp());
					timeClickMove = CParserUtils.getTimeBetween(points.get(0).getTimeStamp(), startPoint.getP().getTimeStamp());
				}
				else
				{
					timeReleaseMove = 0; // TODO
					timeClickMove = CParserUtils.getTimeBetween(points.get(points.size()-1).getTimeStamp(), startPoint.getP().getTimeStamp());
				}
			}
		}
		
		
		if(!multiclicks.isEmpty() && !points.isEmpty())
		{
			timeReleaseMove = CParserUtils.getTimeBetween(points.get(0).getTimeStamp(), multiclicks.get(multiclicks.size()-1).getR().getTimeStamp());
		}
		
		if(startPoint == null || endPoint == null)
		{
			System.out.println("");
		}
		
		this.flyDistance = startPoint.getP().distance(endPoint.getR());
		this.flyAngle = CMyPoint.getAngle(startPoint.getP(), endPoint.getR());
		
		double totalLength = 0;		
		double minSpeed = Double.MAX_VALUE;
		double maxSpeed = 0;
		int intersections = 0;
		
		double minDistance = Double.MAX_VALUE;
		double maxDistance = 0;		
		double allDistances = 0;
		
		double minAngleDiff = Double.MAX_VALUE;
		double maxAngleDiff = 0;
		double totalAngleDiff = 0;
		
		double angle = 0;
		int directionChanges = 0;
		
		EMouseDirectionEnum newDirection = null;
		
		for(int i = 0; i < points.size()-1; i++)
		{	
			double speed = 0;
			double timeBetween = 0;
			double length = points.get(i).distance(points.get(i+1));
			double angleDiff = Math.abs(CMyPoint.getAngle(points.get(i), points.get(i+1)) - flyAngle);
			
			
			if((i+5) < points.size())
			{
				angle = CMyPoint.getAngle(points.get(i), points.get(i+5));
			}
			
			EMouseDirectionEnum oldDirection = newDirection;			
			
			if(angle <= 45.0){ newDirection = EMouseDirectionEnum.DEGREE_0_45; }
			else if(angle > 45.0 && angle <= 90.0){ newDirection = EMouseDirectionEnum.DEGREE_45_90; }
			else if(angle > 90.0 && angle <= 135.0){ newDirection = EMouseDirectionEnum.DEGREE_90_135; }
			else if(angle > 135.0 && angle <= 180.0){ newDirection = EMouseDirectionEnum.DEGREE_135_180; }
			else if(angle > 180.0 && angle <= 225.0){ newDirection = EMouseDirectionEnum.DEGREE_180_225; }
			else if(angle > 225.0 && angle <= 270.0){ newDirection = EMouseDirectionEnum.DEGREE_225_270; }
			else if(angle > 270.0 && angle <= 315.0){ newDirection = EMouseDirectionEnum.DEGREE_270_315; }
			else if(angle > 315.0 && angle <= 359.0){ newDirection = EMouseDirectionEnum.DEGREE_315_360; }
					
			if(oldDirection != null && !oldDirection.equals(newDirection))
			{
				directionChanges++;
			}
			
			totalAngleDiff += angleDiff;
			totalLength += length;
			
			timeBetween = CParserUtils.getTimeBetween(points.get(i+1).getTimeStamp(), points.get(i).getTimeStamp());
			speed = length / (timeBetween/1000);
			
			if(minSpeed > speed)
			{
				minSpeed = speed;
			}
			
			if(maxSpeed < speed)
			{
				maxSpeed = speed;
			}
			
			if(new Line2D.Double(points.get(i), points.get(i+1)).intersectsLine(getEventLine()))
			{
				intersections++;
			}	
			
			
			
			if(minAngleDiff > angleDiff)
			{
				minAngleDiff = angleDiff;
			}
			
			if(maxAngleDiff < angleDiff)
			{
				maxAngleDiff = angleDiff;				
			}
			
			
			
			double dist = getEventLine().ptLineDist(points.get(i));
			
			if(dist < minDistance) 
			{
				minDistance = dist;
			}
			
			if(dist > maxDistance)
			{
				maxDistance = dist;
			}
			allDistances += getEventLine().ptLineDist(points.get(i));
			
			if(i == points.size()-2)
			{
				dist = getEventLine().ptLineDist(points.get(i+1));
				
				if(dist < minDistance) 
				{
					minDistance = dist;
				}
				
				if(dist > maxDistance)
				{
					maxDistance = dist;
				}
				allDistances += getEventLine().ptLineDist(points.get(i+1));
			}
			
		}
		
		this.time = (double) ((endPoint.getR().getTimeStamp().doubleValue() - startPoint.getP().getTimeStamp().doubleValue())/1000); 
			
		this.moveLength = totalLength/flyDistance;
		this.avgSpeed = totalLength / this.time;
		this.flySpeed = this.flyDistance / this.time;
		this.intersections = intersections;
		
		if(!this.getPoints().isEmpty())
		{
			this.acceleration =  this.avgSpeed / this.time;
		}
		else {
			this.acceleration = 0;
		}
		this.minLineDistance = minDistance;
		this.maxLineDistance = maxDistance;
		this.avgLineDistance = (allDistances / points.size());
		
		this.minAngleDiff = minAngleDiff;
		this.maxAngleDiff = maxAngleDiff;
		this.avgAngleDiff = (totalAngleDiff / points.size());
		this.directionChanges = directionChanges;
	}
		
	public ArrayList<CMyPoint> getPoints() {
		return points;
	}

	public double getAvgSpeed() {
		return avgSpeed;
	}
	
	public double getAcceleration() {
		return acceleration;
	}

	public double getFlySpeed() {
		return flySpeed;
	}

	public double getFlyDistance() {
		return flyDistance;
	}

	public double getMoveLength() {
		return moveLength;
	}

	public double getMinLineDistance() {
		return minLineDistance;
	}

	public double getMaxLineDistance() {
		return maxLineDistance;
	}

	public double getAvgLineDistance() {
		return avgLineDistance;
	}

	public double getMinAngleDiff() {
		return minAngleDiff;
	}

	public double getMaxAngleDiff() {
		return maxAngleDiff;
	}

	public double getAvgAngleDiff() {
		return avgAngleDiff;
	}

	public double getFlyAngle() {
		return flyAngle;
	}

	public int getIntersections() {
		return intersections;
	}

	public int getDirectionChanges() {
		return directionChanges;
	}
	
	
	public static double round(double value) {
		int places = 3;
		
	    if (places < 0) throw new IllegalArgumentException();

	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}
	
}
