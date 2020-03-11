package util;
import com.google.common.base.Stopwatch;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CountMap {
	
	static { startThread(); }
	
	private static long total = 0;
    private static Map<String,Integer> map = new TreeMap<String,Integer>();
    public static String toString2() { return "#total="+ ChangedCount.toString2() +"#"+ map; }
    public static void addCount(String key,int value)
    {
    	total++;
    	if(map.containsKey(key) == false) map.put(key, 0);
		map.put(key, map.get(key) + value);
    }
    public static void setCount(String key,int value)
    {
    	total++;
		map.put(key,value);
    }

    
    private static Map<String,String> mapStr = new TreeMap<String,String>();
    public static void setString(String key,String value)
    {
    	mapStr.put(key,value);
    }
    
    
    
    private static boolean started = false;
    private synchronized static void startThread()
	{
		if(started == true) return;
		started = true;

	    new Thread(() -> 
	    {
	    	Stopwatch stopwatch = Stopwatch.createStarted();
    		while(true)
    		{
    			try
    			{
    				if(ChangedTime.changedTime(10000)==false) continue;//100(즉각 변경 체크)
    				
    				//ChangedCount.changedCount(total);
    				if(ChangedCount.changedCount(total)==false) continue;
    				
    				
    		        Log.log("\t ***** COUNT " +"#elapse="+ String.format("%-5d", stopwatch.elapsed(TimeUnit.SECONDS))+ ChangedCount.toString2() +"#map="+ map +"#mapStr="+ mapStr);

    			}catch(Exception e){ e.printStackTrace(); }
	    	}
	    }).start();

	}
    
    public static void main(String[] args) throws Exception
    {
    	while(true)
    	{
    		Thread.sleep(1000);
    		CountMap.addCount("A",1);
    	}
    }
}

