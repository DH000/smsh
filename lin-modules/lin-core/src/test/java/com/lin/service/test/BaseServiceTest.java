package com.lin.service.test;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONObject;
import com.lin.entity.test.Tester;
import com.lin.service.IBaseService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/applicationContext.xml", "/applicationContext-dao.xml", "/applicationContext-service.xml"})
public class BaseServiceTest {
	@Resource
	private IBaseService<Tester, Integer> baseService;
	
	@Test
	public void saveTest(){
		Tester er = new Tester();
		er.setVersion(1);
		
		for(int i=0; i<100; i++){
			er.setName("hello" + i);
			System.out.println(baseService.save(er));
		}
	}
	
	@Test
	public void saveOrUpdateTest(){
		Tester er = new Tester();
		er.setVersion(1);
		er.setId(1);
		
		for(int i=0; i<100; i++){
			er.setName("helloxx-" + i);
			baseService.saveOrUpdate(er);
		}
	}
	
	@Test
	public void findTest(){
		Tester er = baseService.find(1);
		System.out.println(JSONObject.toJSONString(er));
	}
	
	@Test
	public void loadTest(){
		Tester er = baseService.find(1);
		System.out.println(JSONObject.toJSONString(er));
	}
}
