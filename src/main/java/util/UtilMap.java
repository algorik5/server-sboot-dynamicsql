package util;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import com.google.common.collect.ImmutableMap;

public class UtilMap {
	
	public static Map initMap()
	{
	  //Map<String, String> map = Map.of("key1","value1", "key2", "value2");//java9
	  Map<String, String> map  = ImmutableMap.of("type", "test1", "type2", "test2");//guava
	  return map;
	}
	
	public static Map<String,Object> createInlineMap()
	{
		Map<String,Object> map =  Collections.unmodifiableMap(new LinkedHashMap<String, String>() {{  
			put("one", "1");
			put("two", "2");
		}});
		
//		Map<Integer, String> mymap = new LinkedHashMap<Integer, String>() {{
//			put(1, "one");
//			put(2, "two");
//		}};
		
		//guava >>> Map<String, Integer> left = ImmutableMap.of("a", 1, "b", 2, "c", 3);
		
		//java 9 >>> Map<Integer,String> map = Map.of(1, "A", 2, "B", 3, "C");
		return map;
	}
	
}
