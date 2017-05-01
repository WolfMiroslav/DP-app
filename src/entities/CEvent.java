package entities;

import enums.EEmotionEnum;

public class CEvent {
	private EEmotionEnum emotion;
	private String userName;	
	private String session;
	private long pauseTotalLength;
	private long pauseMaxLength;
	private long pauseMinLength;
	private int pauseNum = 0;
	private int switchedWindowNum = 0;
	
	private long timeStart;
	private long timeEnd;
	private long timeTotal;
	
	private long minTimeInWindow;
	private long avgTimeInWindow;
	private long maxTimeInWindow;	
		
	public EEmotionEnum getEmotion() {
		return emotion;
	}
	public void setEmotion(EEmotionEnum emotion) {
		this.emotion = emotion;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getSession() {
		return session;
	}
	public void setSession(String session) {
		this.session = session;
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
	public void incPauseNum() {
		pauseNum++;
	}	
	public void incSwitedWindowNum() {
		switchedWindowNum++;
	}	
	public int getSwitedWindowNum() {
		return switchedWindowNum;
	}	
	public long getTimeStart() {
		return timeStart;
	}
	public void setTimeStart(long timeStart) {
		this.timeStart = timeStart;
	}
	public long getTimeEnd() {
		return timeEnd;
	}
	public void setTimeEnd(long timeEnd) {
		this.timeEnd = timeEnd;
	}
	public long getTimeTotal() {
		return timeTotal;
	}
	public void setTimeTotal(long timeTotal) {
		this.timeTotal = timeTotal;
	}
	
	
}
