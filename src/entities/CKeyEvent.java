package entities;

import java.util.ArrayList;

/**
 * @author Mirec
 * 
 * Keyevent represents 1 word 
 */
public class CKeyEvent extends CEvent {
	private ArrayList<CKeyAction> keys = new ArrayList<>();
	private int misstakesCount = 0;
	private int modifiersCount = 0;
	
	public void addActionsToList (CKeyAction keyAction)
	{
		keys.add(keyAction);
	}
	
	public ArrayList<CKeyAction> getAllKeys ()
	{
		return keys;
	}
	
	public CKeyAction getFirstKey ()
	{
		return keys.get(0);
	}
	
	public CKeyAction getLastKey ()
	{
		return keys.get(keys.size()-1);
	}
	
	public CKeyAction getKey (int i)
	{
		return keys.get(i);
	}
	
	public int keysSize()
	{
		return keys.size();
	}
	
	public boolean keysListEmpty()
	{
		return keys.isEmpty();
	}
	
	public int getMisstakesCount()
	{
		return misstakesCount;
	}
	
	public void incMisstakes()
	{
		misstakesCount++;
	}
	
	public int getModifiersCount()
	{
		return modifiersCount;
	}
	
	public void setModifiersCount(int modifiersCount)
	{
		if(this.modifiersCount < modifiersCount)
		{
			this.modifiersCount = modifiersCount;
		}
	}
	
	public boolean isComplete()
	{
		boolean isComplete = true;
		
		for(CKeyAction ka : keys)
		{
			if(!ka.isReleased())
			{
				isComplete = false;
			}
		}
		
		return isComplete;
	}
}
