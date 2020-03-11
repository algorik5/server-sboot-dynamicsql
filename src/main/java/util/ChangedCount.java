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

public class ChangedCount {

    private static long count = 0;
    private static long countOld = 0;
    private static long gap = 0;
    
    //public static void addCount() { count++; }
    
    //public static long getCount() { return count; }
    public static long getGap() { return gap; }
    public static String toString2() { return "#gap="+ String.format("%-10d", gap) + "#count="+ String.format("%-10d", count); }
    
    public static boolean changedCount(long tempCount)
    {
		count = tempCount;
		
    	if(count < 1) return false;
		if(count == countOld) return false;
		
		gap = count - countOld;
		countOld = count;
		return true;
    }
    
    
    public static void main(String[] args) throws Exception
    {
    	long temp = 1;
    	long i = 0;
    	while(true)
    	{
    		i++;
    		if(i % 10 == 1) temp++;
    		Log.log("=== "+ "#i="+i +"#temp="+ temp +"#changed"+ ChangedCount.changedCount(temp));
    	}
    }
}

