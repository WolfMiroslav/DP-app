package entities;

public class CKeyAction {
	
	private Long pressedTime;
	private Long releasedTime;
	private String keyCode;
	private String modifiers;
	private String keyText;
	private String keyChar;
	private String keyLocation;
	private boolean isReleased;
	
	public Long getPressedTime() 
	{
		return pressedTime;
	}
	
	public void setPressedTime(Long pressedTime) 
	{
		this.pressedTime = pressedTime;
	}
	
	public Long getReleasedTime() 
	{
		return releasedTime;
	}
	
	public void setReleasedTime(Long releasedTime) 
	{
		this.releasedTime = releasedTime;
	}
	
	public String getKeyCode() {
		return keyCode;
	}

	public void setKeyCode(String keyCode) {
		this.keyCode = keyCode;
	}

	public String getModifiers() 
	{
		return modifiers;
	}
	
	public void setModifiers(String modifiers) 
	{
		this.modifiers = modifiers;
	}
	
	public String getKeyText() 
	{
		return keyText;
	}
	
	public void setKeyText(String keyText) 
	{
		this.keyText = keyText;
	}
	
	public String getKeyChar() 
	{
		return keyChar;
	}
	
	public void setKeyChar(String keyChar) 
	{
		this.keyChar = keyChar;
	}
	
	public String getKeyLocation() {
		return keyLocation;
	}

	public void setKeyLocation(String keyLocation) {
		this.keyLocation = keyLocation;
	}

	public boolean isReleased() 
	{
		return isReleased;
	}
	
	public void setReleased(boolean isReleased) 
	{
		this.isReleased = isReleased;
	}
	
	public Long getDwellTime()
	{
		if(pressedTime != null && releasedTime != null)
		{
			return (this.releasedTime - this.pressedTime);
		}
		else
		{
			return null;
		}
	}
	
}
