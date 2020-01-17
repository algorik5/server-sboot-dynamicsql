package algorik5;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.google.gson.Gson;

import util.DateUtil;
import util.Log;

@Controller
public class HelloController {

	//Gson gson = new Gson();
	long count = 0;
	
	//http://localhost:18080/swagger-ui.html
	//http://localhost:8080/hello?msg=aaa
	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	@ResponseBody
	public Map hello(@RequestParam(required=true,defaultValue="default") String msg) {
		count++;
		Log.log("------------------- hello start # "+ msg);
		Map map = new LinkedHashMap();
		map.put("count",count);
		map.put("time",DateUtil.currentDate());
		map.put("msg",msg);
		//String json = gson.toJson(map);
	    //return json;
	    return map;
	}

}