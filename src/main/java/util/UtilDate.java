package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class UtilDate {
	private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
    public static String currentDate()
    {
    	return format.format(new Date());
    }
    
    public static String longToString(long time)
    {
    	return format.format(new Date(time));
    }
    public static String dateToString(Date date)
    {
    	return format.format(date);
    }
    public static Date stringToDate(String str)
    {
    	try {
			return format.parse(str);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return null;
    }
    
	private static DateTimeFormatter format2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static long gapDate_secs(String from,String to)
    {
    	LocalDateTime fromTime = LocalDateTime.parse(from, format2);
    	LocalDateTime toTime = LocalDateTime.parse(to, format2);
     
        Duration duration = Duration.between(fromTime, toTime);
        //return Math.abs(duration.getSeconds());
        return duration.getSeconds();
    }
	public static void main(String[] args) throws Exception
	{
//		//String now = "2001-01-01";
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");// HH:mm");
//        //LocalDateTime formatDateTime = LocalDateTime.parse(now, formatter);
//        
////		System.out.println("=== # "+ LocalDateTime.parse("2001-01-01", formatter));
////		System.out.println("=== # "+ LocalDateTime.parse("2001-01-02", formatter));
////		System.out.println("=== # "+ LocalDateTime.parse("2001-01-03", formatter));
//		
//		System.out.println("=== # "+ format.parse("2001-01-01").getTime());
//		System.out.println("=== # "+ format.parse("2001-01-02").getTime());
//		System.out.println("=== # "+ format.parse("2001-01-03").getTime());
//		

		
		String from = "2001-10-10 10:10:10";
		String to = "2001-10-10 10:10:20";
		DateTimeFormatter format2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    	LocalDateTime fromTime = LocalDateTime.parse(from, format2);
    	LocalDateTime toTime = LocalDateTime.parse(to, format2);
     
        Duration duration = Duration.between(fromTime, toTime);
        //return Math.abs(duration.getSeconds());
		System.out.println("=== getSeconds # "+ duration.getSeconds());

	}
}
