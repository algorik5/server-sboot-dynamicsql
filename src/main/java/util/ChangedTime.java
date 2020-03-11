package util;
import com.google.common.base.Stopwatch;

import util.Log;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ChangedTime {

	private static SimpleDateFormat formatSS = new SimpleDateFormat("ss");
    private static String ssOld = "99";
    
    public static boolean changedTime(long millis)// throws Exception
    {
		try 
		{
			Thread.sleep(millis);
			String ss = formatSS.format(new Date());
			if(ss.endsWith(ssOld)) return false;
			ssOld = ss;
		} catch (InterruptedException e) { e.printStackTrace(); }
		return true;
    }
    
    public static void main(String[] args) throws Exception
    {
    	while(true)
    	{
    		Log.log("=== # "+ ChangedTime.changedTime(100));
    	}
    }
}

