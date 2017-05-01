package utils;

public class CParserUtils {
		
	public static long getTimeBetween(long time1, long time2)
	{
		return Math.abs(time2 - time1);
	}
	
	public static int getMax(int num1, int num2)
	{
		return num1 >= num2? num1 : num2;
	}
	
	public static double getMax(double num1, double num2)
	{
		return num1 >= num2? num1 : num2;
	}
	
	public static long getMax(long num1, long num2)
	{
		return num1 >= num2? num1 : num2;
	}
	
	public static int getMin(int num1, int num2)
	{
		return num1 <= num2? num1 : num2;
	}
	
	public static double getMin(double num1, double num2)
	{
		return num1 <= num2? num1 : num2;
	}
	
	public static long getMin(long num1, long num2)
	{
		return num1 <= num2? num1 : num2;
	}
}
