package algorik5.dynamicsql;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Query;
import javax.persistence.Tuple;
import javax.persistence.TupleElement;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.Data;

@Service
public class DynamicService {
	
	private static Logger logger = LoggerFactory.getLogger(DynamicService.class);

	@Autowired
	EntityManager em;
	
	public List<Map<String,Object>> dynamicSelect(String sql,Map<String,Object> bindingMap)
	{
		 logger.info("\t===== dynamicSelect START!! "+ "#sql="+ sql+ "#bindingMap="+ bindingMap);
		List<Map<String,Object>> result = new ArrayList<>();
		
//		List<Tuple> list = em.createNativeQuery("select tname as name,tabtype as detail from tab where tname like :prefix", Tuple.class)
//				.setParameter( "prefix", prefix)
//				.getResultList();
		Query query = em.createNativeQuery(sql, Tuple.class);
		Iterator<String> it = bindingMap.keySet().iterator();
		while(it.hasNext()) 
		{
			String key = it.next();
			Object value = bindingMap.get(key);
			query.setParameter(key, value);
		}
		List<Tuple> list = query.getResultList();
		
		logger.info("\t===== dynamicSelect createNativeQuery !! "+ "#list="+ list.size());
		for(int i=0;i<list.size();i++)
		{
			Tuple tuple = list.get(i);
			//System.out.println("=== "+ "#i="+ i +"#tuple="+  ToStringBuilder.reflectionToString(tuple, ToStringStyle.JSON_STYLE));
			Map<String,Object> map = new LinkedHashMap<>();
			result.add(map);
			Iterator<TupleElement<?>> it2 = tuple.getElements().iterator();
			while(it2.hasNext())
			{
				String key = it2.next().getAlias();
				Object value = tuple.get(key);
				if(value instanceof String) map.put(key, ((String)value));
				else map.put(key, value);
				//System.out.println("=== "+ "#i="+ i +"#key="+  key +"#value="+ value);
			}
		}
		return result;
	}

	@Transactional
	public int dynamicUpdate(String sql,Map<String,Object> bindingMap)
	{
		logger.info("\t===== dynamicUpdate START!! "+ "#sql="+ sql+ "#bindingMap="+ bindingMap);
		
		Query query = em.createNativeQuery(sql);
		Iterator<String> it = bindingMap.keySet().iterator();
		while(it.hasNext()) 
		{
			String key = it.next();
			Object value = bindingMap.get(key);
			query.setParameter(key, value);
		}
		int result = query.executeUpdate();
		
		logger.info("\t===== dynamicUpdate END !! "+ "#result="+ result);

		return result;
	}	
	
	

}
