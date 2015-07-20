package com.lin.service.test;

import java.util.ArrayList;
import java.util.List;

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
	@Resource
	private ITesterService testerService;
	
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
	
	@Test
	public void findByPropertyTest(){
		System.out.println(JSONObject.toJSONString(baseService.findByProperty("name", "xxx1")));
	}
	
	@Test
	public void findByPropertiesTest(){
		System.out.println(JSONObject.toJSONString(baseService.findByProperties(new String[]{"id", "name"}, new Object[]{2, "xxx1"})));
	}
	
	@Test
	public void findByPropertyForPageTest(){
		System.out.println(JSONObject.toJSONString(baseService.findByPropertyForPage("version", 1, 10, 10)));
	}
	
	@Test
	public void findByPropertiesForPageTest(){
		System.out.println(JSONObject.toJSONString(baseService.findByPropertiesForPage(new String[]{"name", "id"}, new Object[]{"xxx1", 2}, 0, 10)));
	}
	
	@Test
	public void countTest(){
		System.out.println(baseService.count());
	}
	
	@Test
	public void countByPropertyTest(){
		System.out.println(baseService.countByProperty("name", "xxx1"));
	}
	
	@Test
	public void countByPropertiesTest(){
		System.out.println(baseService.countByProperties(new String[]{"name", "version"}, new Object[]{"xxx1", 2}));
	}
	
	@Test
	public void updateTest(){
		Tester er = new Tester();
		er.setVersion(3);
		er.setId(1);
		er.setName("xx");
		baseService.update(er);
	}
	
	@Test
	public void updateAllTest(){
		List<Tester> list = new ArrayList<Tester>();
		for(int i=0; i<100; i++){
			Tester er = new Tester();
			er.setVersion(4);
			er.setId(i + 1);
			er.setName("xx" + i);
			list.add(er);
		}
		
		baseService.update(list);
	}
	
	@Test
	public void findForUpdateTest(){
		baseService.findForUpdate(1);
//		new Thread(new Runnable() {
//			@Override
//			public void run() {
//				baseService.findForUpdate(1);
//			}
//		}).start();
//		new Thread(new Runnable() {
//			@Override
//			public void run() {
//				try {
//					Thread.sleep(1000);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//				updateTest();
//			}
//		}).start();
//		
//		try {
//			Thread.sleep(10000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
	}
	
	@Test
	public void forUpdateTest(){
		testerService.forUpdateTest(1);
	}
}













