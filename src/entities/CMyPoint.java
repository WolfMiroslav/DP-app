package entities;

//import java.awt.Point;
import java.awt.geom.Point2D;

import enums.EMouseActionEnum;

public class CMyPoint extends Point2D {
	
	private Long timeStamp;
	private EMouseActionEnum action;	
	private int clickCount;	
	private double x;
	private double y;
	private String modifiers;
	
	public Long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Long timeStamp) {
		this.timeStamp = timeStamp;
	}

	public static double getAngle(Point2D p1, Point2D p2) {
		if(p1.getX() == p2.getX() && p1.getY() == p2.getY())
		{
			return 0;
		}
		
		double angle = (double) Math.toDegrees(Math.atan2(p1.getY() - p2.getY(), p1.getX() - p2.getX()));

	    if(angle < 0){
	        angle += 360;
	    }	    
	    
	    return angle;
	}

	public CMyPoint() {
		super();		
	}

	public int getClickCout() {
		return clickCount;
	}

	public void setClickCount(int clickCount) {
		this.clickCount = clickCount;
	}

	public String getModifiers() {
		return modifiers;
	}

	public void setModifiers(String modifiers) {
		this.modifiers = modifiers;
	}

	@Override
	public double getX() {
		return x;
	}

	@Override
	public double getY() {
		return y;
	}

	@Override
	public void setLocation(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public EMouseActionEnum getAction() {
		return action;
	}

	public void setAction(EMouseActionEnum action) {
		this.action = action;
	}
}
