package utils;

public class StringUtils {
	
	public static boolean isEmpty(String string){
		return string == null || string.equals("") || string.equalsIgnoreCase("null");
	}

}
