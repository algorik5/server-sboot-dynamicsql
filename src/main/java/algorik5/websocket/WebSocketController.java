package algorik5.websocket;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import util.DateUtil;
import util.Log;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.scheduling.annotation.Scheduled;

//@Controller
@RestController
@RequestMapping("/websocket")
public class WebSocketController {

	@Autowired
	private SimpMessagingTemplate stomp;//SimpMessageSendingOperations impl
	
	Gson gson = new Gson();
	long count = 0;
	
	@MessageMapping("/hello") //toserver/hello
    //@SendTo("/server/hello") //convertAndSend사용
	public void hello(@Payload String message) throws Exception {
		count++;
		Log.log("------------------- hello start # "+ message);
		Map map = gson.fromJson(message, Map.class);
		map.put("reply","ok-"+ count);
		Log.log("\t --- hello map # "+ map);
		//if(1==1) throw new Exception("xxx");//에러생겨도 /topic/hello로 리턴됨 
		stomp.convertAndSend("/toclient/hello", gson.toJson(map));
	}

	@Scheduled(fixedRate = 10000)
	public void hellotimer() throws Exception {
		count++;
		//Log.log("------------------- hellotimer start # ");
		Map map = new LinkedHashMap();
		map.put("count",count);
		map.put("time",DateUtil.currentDate());
		map.put("msg","timer");
		Log.log("\t --- hellotimer map # "+ map);
		stomp.convertAndSend("/toclient/timer", gson.toJson(map));
		
		testappdata();
	}
	
	private void testappdata()
	{
		for(int i=0;i<3;i++)
		{
			String app = "app-"+i;
			Map mapapp = new LinkedHashMap();
			mapapp.put("app",app);
			mapapp.put("ver","v-"+i);
			mapapp.put("count",count);
			mapapp.put("time",DateUtil.currentDate());
			
			Map mapgap = new LinkedHashMap();
			mapgap.put("SRT",i);
			mapgap.put("END",i);
			mapgap.put("ERR",i);
			
			Map maptotal = new LinkedHashMap();
			maptotal.put("SRT",i);
			maptotal.put("END",i);
			maptotal.put("ERR",i);
			
			Map map = new LinkedHashMap();
			map.put("GAP",mapgap);
			map.put("TOTAL",maptotal);
			map.put("APP",mapapp);
			
			Log.log("\t --- testappdata map # "+ map);
			stomp.convertAndSend("/toclient/appdata", gson.toJson(map));
			//allmap.put(app, map);
		}
		//Log.log("\t --- testappdata allmap # "+ allmap);
		//stomp.convertAndSend("/toclient/appdata", gson.toJson(allmap));
	}
	
	
	
	
	
	
	
	
	//http://localhost:8080/websocket/hellorest?msg=aaa
	@RequestMapping(value = "/hellorest", method = RequestMethod.GET)
	@ResponseBody
	public String hellorest(String msg) {
		count++;
		Log.log("------------------- hellorest start # "+ msg);
		Map map = new LinkedHashMap();
		map.put("count",count);
		map.put("time",DateUtil.currentDate());
		map.put("msg",msg);
		String json = gson.toJson(map);
		this.stomp.convertAndSend("/toserver/hello", json);//stomp send
	    return json;//rest리턴
	}

	@MessageExceptionHandler
	//@MessageMapping("/errors")
	//@SendTo("/errors")
	//@SendToUser("/errors")
    public String handleException(Throwable exception) {
		Log.log("------------------- handleException start # "+ exception);
		exception.printStackTrace();
		//명시적으로 convertAndSend 해야하는 군...
		this.stomp.convertAndSend("/server/errors", exception.getMessage());
	    return exception.getMessage();
    }

}