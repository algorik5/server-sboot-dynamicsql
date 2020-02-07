package algorik5.dynamicsql;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
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
	 Environment env;
	 
	 @Autowired
	 private DynamicService dbService;

	 
	 @GetMapping("/dynamicSelect")
	 @CrossOrigin(origins = "http://localhost:4200")
	 public List<Map<String,Object>> dynamicSelect(@RequestParam(required=false,defaultValue="") String sql,@RequestParam(required=false,defaultValue="5") int rownum) throws Exception
	 {
		 if(StringUtils.length(sql)<1)
		 {
			 String driver = env.getProperty("spring.datasource.driver-class-name");
			 if(StringUtils.contains(driver, ".h2.")) sql = "SELECT * FROM INFORMATION_SCHEMA.TABLES";
			 else if(StringUtils.contains(driver, "OracleDriver")) sql = "select * from tab";
			 else throw new Exception("--- sql required ---");
			 logger.info("##### DEFAULT SQL - "+ "#driver="+ driver+ "#sql="+ sql);
		 }
		 
		 logger.info("======================= dynamicSelect START !! "+ "#rownum="+ rownum+ "#sql="+ sql);
		 
		 Map<String,Object> bindingMap =  new LinkedHashMap<String, Object>();
		 
		 List<Map<String,Object>> result = dbService.dynamicSelect(sql,bindingMap,rownum);
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
