package com.lin.freemarker;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lin.utils.FreeMarkerUtils;

import freemarker.template.Configuration;
import freemarker.template.TemplateException;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/applicationContext.xml", "/applicationContext-dao.xml", "/applicationContext-service.xml"})
public class FreeMarkerTest {
	@Resource
	private Configuration freeMarkerConfiguration;
	
	@Test
	public void markerTest1(){
		try {
			Map<String, Object> model = new HashMap<>();
			model.put("name", "tem1.ftl-哈哈");
			model.put("time", new Date());
			
			String res = FreeMarkerUtils.processTemplateIntoString(freeMarkerConfiguration, "tem1.ftl", model);
			System.out.println(res);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void markerTest2(){
		try {
			markerTest1();
			
			Map<String, Object> model = new HashMap<>();
			model.put("name", "tem2.ftl");
			model.put("time", new Date());
			
			Writer writer = new StringWriter();
			FreeMarkerUtils.processTemplate(freeMarkerConfiguration, writer, "tem2.ftl", model);
			System.out.println(writer.toString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
	}
	
}
