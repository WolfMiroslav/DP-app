package entities;

import java.util.ArrayList;

import enums.EEmotionEnum;

public class CFinalMouseEvent {
	private Double time;	
	private int missclicks;
	private ArrayList<CMouseEvent> events = new ArrayList<CMouseEvent>();
	private EEmotionEnum emotion;
	private CUser user;
	
	private double totalAvgSpeed;
	private double totalMoveLength;
	private double totalFlySpeed;
	private double totalFlyDistance;
	private double totalPauseLength;
	private int totalDirectionChanges;
	private int totalPauseNum;
	
	private double startSpeedMax;
	private double startSpeedMin;
	private double startSpeedAvg;
	
	private double midSpeedMax;
	private double midSpeedMin;
	private double midSpeedAvg;
	
	private double speedMin;
	private double speedMax;
	private double speedAvg;
		
	private double accelerationMax;
	private double accelerationMin;
	private double accelerationAvg;
	
	private double accelerationMidMax;
	private double accelerationMidMin;
	private double accelerationMidAvg;
	
	private double flySpeedMax;
	private double flySpeedMin;
	private double flySpeedAvg;
		
	private double flyDistanceMax;
	private double flyDistanceMin;
	private double flyDistanceAvg;
	
	private long buttonHoldTimeMax;
	private long buttonHoldTimeMin;
	private long buttonHoldTimeAvg;
	
	private long timeReleaseMoveMax;
	private long timeReleaseMoveMin;
	private long timeReleaseMoveAvg;
	
	private long timeMoveClickMax;
	private long timeMoveClickMin;
	private long timeMoveClickAvg;
	
	private long timeMoveReleaseMax;
	private long timeMoveReleaseMin;
	private long timeMoveReleaseAvg;
	
	private long timeClickMoveMax;
	private long timeClickMoveMin;
	private long timeClickMoveAvg;
	
	private double pauseLengthMax;
	private double pauseLengthMin;
	private double pauseLengthAvg;
	
	private int pauseNumMax;
	private int pauseNumMin;
	private int pauseNumTotal;
	
	private double moveLengthMax;
	private double moveLengthMin;
	private double moveLengthAvg;
	
	private double maxTimeLength;
	private double minTimeLength;
	private double avgTimeLength;
	
	private double lineDistanceMax;
	private double lineDistanceMin;
	private double lineDistanceAvg;
	
	private double angleDiffMax;
	private double angleDiffMin;
	private double angleDiffAvg;
	
	private int directionChangesMax;
	private int directionChangesMin;
	private double directionChangesAvg;
	
	private int intersectionsMax;
	private int intersectionsMin;
	private double intersetionAvg;
	private int intersectionsTotal;
		
	//private double flyAngle;
	
	public Double getTime() {
		return time;
	}
	public void setTime(Double time) {
		this.time = time;
	}
	public int getMissclicks() {
		return missclicks;
	}
	public void setMissclicks(int missclicks) {
		this.missclicks = missclicks;
	}
	public ArrayList<CMouseEvent> getEvents() {
		return events;
	}
	public void addEvent(CMouseEvent event) {
		this.events.add(event);
	}
	public EEmotionEnum getEmotion() {
		return emotion;
	}
	public void setEmotion(EEmotionEnum emotion) {
		this.emotion = emotion;
	}
	public CUser getUser() {
		return user;
	}
	public void setUser(CUser user) {
		this.user = user;
	}
	public double getTotalAvgSpeed() {
		return totalAvgSpeed;
	}
	public void setTotalAvgSpeed(double totalAvgSpeed) {
		this.totalAvgSpeed = totalAvgSpeed;
	}
	public double getTotalMoveLength() {
		return totalMoveLength;
	}
	public void setTotalMoveLength(double totalMoveLength) {
		this.totalMoveLength = totalMoveLength;
	}
	public double getTotalFlySpeed() {
		return totalFlySpeed;
	}
	public void setTotalFlySpeed(double totalFlySpeed) {
		this.totalFlySpeed = totalFlySpeed;
	}
	public double getTotalFlyDistance() {
		return totalFlyDistance;
	}
	public void setTotalFlyDistance(double totalFlyDistance) {
		this.totalFlyDistance = totalFlyDistance;
	}
	public double getTotalPauseLength() {
		return totalPauseLength;
	}
	public void setTotalPauseLength(double totalPauseLength) {
		this.totalPauseLength = totalPauseLength;
	}
	public int getTotalDirectionChanges() {
		return totalDirectionChanges;
	}
	public void setTotalDirectionChanges(int totalDirectionChanges) {
		this.totalDirectionChanges = totalDirectionChanges;
	}
	public int getTotalPauseNum() {
		return totalPauseNum;
	}
	public void setTotalPauseNum(int totalPauseNum) {
		this.totalPauseNum = totalPauseNum;
	}
	public double getStartSpeedMax() {
		return startSpeedMax;
	}
	public void setStartSpeedMax(double startSpeedMax) {
		this.startSpeedMax = startSpeedMax;
	}
	public double getStartSpeedMin() {
		return startSpeedMin;
	}
	public void setStartSpeedMin(double startSpeedMin) {
		this.startSpeedMin = startSpeedMin;
	}
	public double getMidSpeedMax() {
		return midSpeedMax;
	}
	public void setMidSpeedMax(double midSpeedMax) {
		this.midSpeedMax = midSpeedMax;
	}
	public double getMidSpeedMin() {
		return midSpeedMin;
	}
	public void setMidSpeedMin(double midSpeedMin) {
		this.midSpeedMin = midSpeedMin;
	}
	public double getMidSpeedAvg() {
		return midSpeedAvg;
	}
	public void setMidSpeedAvg(double midSpeedAvg) {
		this.midSpeedAvg = midSpeedAvg;
	}
	public double getAccelerationMidMax() {
		return accelerationMidMax;
	}
	public void setAccelerationMidMax(double accelerationMidMax) {
		this.accelerationMidMax = accelerationMidMax;
	}
	public double getAccelerationMidMin() {
		return accelerationMidMin;
	}
	public void setAccelerationMidMin(double accelerationMidMin) {
		this.accelerationMidMin = accelerationMidMin;
	}
	public double getAccelerationMidAvg() {
		return accelerationMidAvg;
	}
	public void setAccelerationMidAvg(double accelerationMidAvg) {
		this.accelerationMidAvg = accelerationMidAvg;
	}
	public double getStartSpeedAvg() {
		return startSpeedAvg;
	}
	public void setStartSpeedAvg(double startSpeedAvg) {
		this.startSpeedAvg = startSpeedAvg;
	}
	public double getSpeedMin() {
		return speedMin;
	}
	public void setSpeedMin(double speedMin) {
		this.speedMin = speedMin;
	}
	public double getSpeedMax() {
		return speedMax;
	}
	public void setSpeedMax(double speedMax) {
		this.speedMax = speedMax;
	}
	public double getSpeedAvg() {
		return speedAvg;
	}
	public void setSpeedAvg(double speedAvg) {
		this.speedAvg = speedAvg;
	}
	public double getAccelerationMax() {
		return accelerationMax;
	}
	public void setAccelerationMax(double accelerationMax) {
		this.accelerationMax = accelerationMax;
	}
	public double getAccelerationMin() {
		return accelerationMin;
	}
	public void setAccelerationMin(double accelerationMin) {
		this.accelerationMin = accelerationMin;
	}
	public double getAccelerationAvg() {
		return accelerationAvg;
	}
	public void setAccelerationAvg(double accelerationAvg) {
		this.accelerationAvg = accelerationAvg;
	}
	public double getFlySpeedMax() {
		return flySpeedMax;
	}
	public void setFlySpeedMax(double flySpeedMax) {
		this.flySpeedMax = flySpeedMax;
	}
	public double getFlySpeedMin() {
		return flySpeedMin;
	}
	public void setFlySpeedMin(double flySpeedMin) {
		this.flySpeedMin = flySpeedMin;
	}
	public double getFlySpeedAvg() {
		return flySpeedAvg;
	}
	public void setFlySpeedAvg(double flySpeedAvg) {
		this.flySpeedAvg = flySpeedAvg;
	}
	public double getFlyDistanceMax() {
		return flyDistanceMax;
	}
	public void setFlyDistanceMax(double flyDistanceMax) {
		this.flyDistanceMax = flyDistanceMax;
	}
	public double getFlyDistanceMin() {
		return flyDistanceMin;
	}
	public void setFlyDistanceMin(double flyDistanceMin) {
		this.flyDistanceMin = flyDistanceMin;
	}
	public double getFlyDistanceAvg() {
		return flyDistanceAvg;
	}
	public void setFlyDistanceAvg(double flyDistanceAvg) {
		this.flyDistanceAvg = flyDistanceAvg;
	}
	public long getButtonHoldTimeMax() {
		return buttonHoldTimeMax;
	}
	public void setButtonHoldTimeMax(long buttonHoldTimeMax) {
		this.buttonHoldTimeMax = buttonHoldTimeMax;
	}
	public long getButtonHoldTimeMin() {
		return buttonHoldTimeMin;
	}
	public void setButtonHoldTimeMin(long buttonHoldTimeMin) {
		this.buttonHoldTimeMin = buttonHoldTimeMin;
	}
	public long getButtonHoldTimeAvg() {
		return buttonHoldTimeAvg;
	}
	public void setButtonHoldTimeAvg(long buttonHoldTimeAvg) {
		this.buttonHoldTimeAvg = buttonHoldTimeAvg;
	}
	public long getTimeReleaseMoveMax() {
		return timeReleaseMoveMax;
	}
	public void setTimeReleaseMoveMax(long timeReleaseMoveMax) {
		this.timeReleaseMoveMax = timeReleaseMoveMax;
	}
	public long getTimeReleaseMoveMin() {
		return timeReleaseMoveMin;
	}
	public void setTimeReleaseMoveMin(long timeReleaseMoveMin) {
		this.timeReleaseMoveMin = timeReleaseMoveMin;
	}
	public long getTimeReleaseMoveAvg() {
		return timeReleaseMoveAvg;
	}
	public void setTimeReleaseMoveAvg(long timeReleaseMoveAvg) {
		this.timeReleaseMoveAvg = timeReleaseMoveAvg;
	}
	public long getTimeMoveClickMax() {
		return timeMoveClickMax;
	}
	public void setTimeMoveClickMax(long timeMoveClickMax) {
		this.timeMoveClickMax = timeMoveClickMax;
	}
	public long getTimeMoveClickMin() {
		return timeMoveClickMin;
	}
	public void setTimeMoveClickMin(long timeMoveClickMin) {
		this.timeMoveClickMin = timeMoveClickMin;
	}
	public long getTimeMoveClickAvg() {
		return timeMoveClickAvg;
	}
	public void setTimeMoveClickAvg(long timeMoveClickAvg) {
		this.timeMoveClickAvg = timeMoveClickAvg;
	}
	public long getTimeMoveReleaseMax() {
		return timeMoveReleaseMax;
	}
	public void setTimeMoveReleaseMax(long timeMoveReleaseMax) {
		this.timeMoveReleaseMax = timeMoveReleaseMax;
	}
	public long getTimeMoveReleaseMin() {
		return timeMoveReleaseMin;
	}
	public void setTimeMoveReleaseMin(long timeMoveReleaseMin) {
		this.timeMoveReleaseMin = timeMoveReleaseMin;
	}
	public long getTimeMoveReleaseAvg() {
		return timeMoveReleaseAvg;
	}
	public void setTimeMoveReleaseAvg(long timeMoveReleaseAvg) {
		this.timeMoveReleaseAvg = timeMoveReleaseAvg;
	}
	public long getTimeClickMoveMax() {
		return timeClickMoveMax;
	}
	public void setTimeClickMoveMax(long timeClickMoveMax) {
		this.timeClickMoveMax = timeClickMoveMax;
	}
	public long getTimeClickMoveMin() {
		return timeClickMoveMin;
	}
	public void setTimeClickMoveMin(long timeClickMoveMin) {
		this.timeClickMoveMin = timeClickMoveMin;
	}
	public long getTimeClickMoveAvg() {
		return timeClickMoveAvg;
	}
	public void setTimeClickMoveAvg(long timeClickMoveAvg) {
		this.timeClickMoveAvg = timeClickMoveAvg;
	}
	public double getPauseLengthMax() {
		return pauseLengthMax;
	}
	public void setPauseLengthMax(double pauseLengthMax) {
		this.pauseLengthMax = pauseLengthMax;
	}
	public double getPauseLengthMin() {
		return pauseLengthMin;
	}
	public void setPauseLengthMin(double pauseLengthMin) {
		this.pauseLengthMin = pauseLengthMin;
	}
	public double getPauseLengthAvg() {
		return pauseLengthAvg;
	}
	public void setPauseLengthAvg(double pauseLengthAvg) {
		this.pauseLengthAvg = pauseLengthAvg;
	}
	public int getPauseNumMax() {
		return pauseNumMax;
	}
	public void setPauseNumMax(int pauseNumMax) {
		this.pauseNumMax = pauseNumMax;
	}
	public int getPauseNumMin() {
		return pauseNumMin;
	}
	public void setPauseNumMin(int pauseNumMin) {
		this.pauseNumMin = pauseNumMin;
	}
	public int getPauseNumTotal() {
		return pauseNumTotal;
	}
	public void setPauseNumTotal(int pauseNumTotal) {
		this.pauseNumTotal = pauseNumTotal;
	}
	public double getMoveLengthMax() {
		return moveLengthMax;
	}
	public void setMoveLengthMax(double moveLengthMax) {
		this.moveLengthMax = moveLengthMax;
	}
	public double getMoveLengthMin() {
		return moveLengthMin;
	}
	public void setMoveLengthMin(double moveLengthMin) {
		this.moveLengthMin = moveLengthMin;
	}
	public double getMoveLengthAvg() {
		return moveLengthAvg;
	}
	public void setMoveLengthAvg(double moveLengthAvg) {
		this.moveLengthAvg = moveLengthAvg;
	}
	public double getMaxTimeLength() {
		return maxTimeLength;
	}
	public void setMaxTimeLength(double maxTimeLength) {
		this.maxTimeLength = maxTimeLength;
	}
	public double getMinTimeLength() {
		return minTimeLength;
	}
	public void setMinTimeLength(double minTimeLength) {
		this.minTimeLength = minTimeLength;
	}
	public double getAvgTimeLength() {
		return avgTimeLength;
	}
	public void setAvgTimeLength(double avgTimeLength) {
		this.avgTimeLength = avgTimeLength;
	}
	public double getLineDistanceMax() {
		return lineDistanceMax;
	}
	public void setLineDistanceMax(double lineDistanceMax) {
		this.lineDistanceMax = lineDistanceMax;
	}
	public double getLineDistanceMin() {
		return lineDistanceMin;
	}
	public void setLineDistanceMin(double lineDistanceMin) {
		this.lineDistanceMin = lineDistanceMin;
	}
	public double getLineDistanceAvg() {
		return lineDistanceAvg;
	}
	public void setLineDistanceAvg(double lineDistanceAvg) {
		this.lineDistanceAvg = lineDistanceAvg;
	}
	public double getAngleDiffMax() {
		return angleDiffMax;
	}
	public void setAngleDiffMax(double angleDiffMax) {
		this.angleDiffMax = angleDiffMax;
	}
	public double getAngleDiffMin() {
		return angleDiffMin;
	}
	public void setAngleDiffMin(double angleDiffMin) {
		this.angleDiffMin = angleDiffMin;
	}
	public double getAngleDiffAvg() {
		return angleDiffAvg;
	}
	public void setAngleDiffAvg(double angleDiffAvg) {
		this.angleDiffAvg = angleDiffAvg;
	}
	public int getDirectionChangesMax() {
		return directionChangesMax;
	}
	public void setDirectionChangesMax(int directionChangesMax) {
		this.directionChangesMax = directionChangesMax;
	}
	public int getDirectionChangesMin() {
		return directionChangesMin;
	}
	public void setDirectionChangesMin(int directionChangesMin) {
		this.directionChangesMin = directionChangesMin;
	}
	public double getDirectionChangesAvg() {
		return directionChangesAvg;
	}
	public void setDirectionChangesAvg(double directionChangesAvg) {
		this.directionChangesAvg = directionChangesAvg;
	}
	public int getIntersectionsMax() {
		return intersectionsMax;
	}
	public void setIntersectionsMax(int intersectionsMax) {
		this.intersectionsMax = intersectionsMax;
	}
	public int getIntersectionsMin() {
		return intersectionsMin;
	}
	public void setIntersectionsMin(int intersectionsMin) {
		this.intersectionsMin = intersectionsMin;
	}
	public double getIntersetionAvg() {
		return intersetionAvg;
	}
	public void setIntersetionAvg(double intersetionAvg) {
		this.intersetionAvg = intersetionAvg;
	}
	public int getIntersectionsTotal() {
		return intersectionsTotal;
	}
	public void setIntersectionsTotal(int intersectionsTotal) {
		this.intersectionsTotal = intersectionsTotal;
	}
}
