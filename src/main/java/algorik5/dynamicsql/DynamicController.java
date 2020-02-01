package algorik5.dynamicsql;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dynamicsql")
public class DynamicController {
	 private static Logger logger = LoggerFactory.getLogger(DynamicController.class);

	 @Autowired
	 private DynamicService dbService;

	 
	 @GetMapping("/dynamicSelect")
	 @CrossOrigin(origins = "http://localhost:4200")//show tables
	 public List<Map<String,Object>> dynamicSelect(@RequestParam(required=false,defaultValue="select * from tab") String sql,@RequestParam(required=false,defaultValue="5") int rownum)
	 {
		 logger.info("======================= dynamicSelect START !! "+ "#rownum="+ rownum+ "#sql="+ sql);
		 
		 Map<String,Object> bindingMap =  new LinkedHashMap<String, Object>();
		 
		 List<Map<String,Object>> result = dbService.dynamicSelect(sql,bindingMap);
		 logger.info("======================= dynamicSelect START !! "+ "#rownum="+ rownum+ "#result="+ result.size());
		 return result;
	 }
	 	 
	 @GetMapping("/dynamicUpdate")
	 @CrossOrigin(origins = "http://localhost:4200")
	 public int dynamicUpdate(@RequestParam(required=false,defaultValue="update test1 set time=sysdate where rownum=1") String sql)
	 {
		 logger.info("======================= dynamicUpdate START !! "+ "#sql="+ sql);
		 
		 Map<String,Object> bindingMap =  new LinkedHashMap<String, Object>();
		 
		 int result = dbService.dynamicUpdate(sql,bindingMap);
		 logger.info("======================= dynamicUpdate START !! "+ "#result="+ result);
		 return result;
	 }
}
