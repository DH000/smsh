package com.lin.service.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONObject;
import com.lin.entity.test.Tester;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/applicationContext.xml", "/applicationContext-dao.xml", "/applicationContext-service.xml"})
public class TesterServiceTest {
	@Resource
	private ITesterService testerService;
	
	@Test
	public void findTest(){
		Tester er = testerService.find(1);
		System.out.println(JSONObject.toJSONString(er));
	}
	
	@Test
	public void loadTest(){
		Tester er = testerService.load(1);
		System.out.println(JSONObject.toJSONString(er));
	}
	
	@Test
	public void saveTest(){
		Tester er = new Tester();
		er.setName("xxx1");
		er.setVersion(2);
		testerService.save(er);
	}
}
