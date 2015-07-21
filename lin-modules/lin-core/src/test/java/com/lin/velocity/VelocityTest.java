package com.lin.velocity;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.velocity.app.VelocityEngine;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.lin.utils.DateUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/applicationContext.xml", "/applicationContext-dao.xml", "/applicationContext-service.xml"})
public class VelocityTest {
	@Resource
	private VelocityEngine velocityEngine;
	
	@Test
	public void velocityTest1(){
		Map<String, Object> map = new HashMap<>();
		map.put("name", "test1.vm");
		map.put("time", new Date());
		map.put("dateFormat", new DateUtils());
		String res = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "test1.vm", "utf-8", map);
		
		System.out.println(res);
	}
	
	@Test
	public void velocityTest2(){
		Map<String, Object> map = new HashMap<>();
		map.put("name", "test2.vm");
		map.put("time", new Date());
		map.put("dateFormat", new DateUtils());
//		String res = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "/test/test2.vm", "utf-8", map);
		String res = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "test2.vm", "utf-8", map);
		
		System.out.println(res);
	}
	
}
