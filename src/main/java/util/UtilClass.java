package util;

public class UtilClass {
	public static void checkInstanceof()
	{
		Object object = "aaa";
		
		System.out.println("==== instanceof # "+ (object instanceof String));
		System.out.println("==== instanceof # "+ (object.getClass().isInstance(String.class)));
	}
}
