package entities;

import java.util.ArrayList;

public class CPressedList extends ArrayList<Integer> {
	
	private static CPressedList pressedList;
	
	public static CPressedList getInstance()
	{
		if(pressedList == null)
		{			
			pressedList = new CPressedList();
		}
		
		return pressedList;
	}
	
}
