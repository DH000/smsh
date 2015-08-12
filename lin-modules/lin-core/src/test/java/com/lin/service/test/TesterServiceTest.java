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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/applicationContext.xml", "/applicationContext-dao.xml", "/applicationContext-service.xml"})
public class TesterServiceTest {
	@Resource
	private TesterService testerService;
	
	@Test
	public void saveTest(){
		Tester er = new Tester();
		er.setVersion(1);
		
		for(int i=0; i<100; i++){
			er.setName("hello" + i);
			testerService.save(er);
		}
	}
	
	@Test
	public void saveOrUpdateTest(){
		Tester er = new Tester();
		er.setVersion(1);
		er.setId(1);
		
		for(int i=0; i<100; i++){
			er.setName("helloxx-" + i);
			testerService.saveOrUpdate(er);
		}
	}
	
	@Test
	public void findTest(){
		Tester er = testerService.find(1);
		System.out.println(JSONObject.toJSONString(er));
	}
	
	@Test
	public void loadTest(){
		Tester er = testerService.find(1);
		System.out.println(JSONObject.toJSONString(er));
	}
	
	@Test
	public void findByPropertyTest(){
		System.out.println(JSONObject.toJSONString(testerService.findByProperty("name", "xxx1")));
	}
	
	@Test
	public void findByPropertiesTest(){
		System.out.println(JSONObject.toJSONString(testerService.findByProperties(new String[]{"id", "name"}, new Object[]{2, "xxx1"})));
	}
	
	@Test
	public void findByPropertyForPageTest(){
		System.out.println(JSONObject.toJSONString(testerService.findByPropertyForPage("version", 1, 10, 10)));
	}
	
	@Test
	public void findByPropertiesForPageTest(){
		System.out.println(JSONObject.toJSONString(testerService.findByPropertiesForPage(new String[]{"name", "id"}, new Object[]{"xxx1", 2}, 0, 10)));
	}
	
	@Test
	public void countTest(){
		System.out.println(testerService.count());
	}
	
	@Test
	public void countByPropertyTest(){
		System.out.println(testerService.countByProperty("name", "xxx1"));
	}
	
	@Test
	public void countByPropertiesTest(){
		System.out.println(testerService.countByProperties(new String[]{"name", "version"}, new Object[]{"xxx1", 2}));
	}
	
	@Test
	public void updateTest(){
		Tester er = new Tester();
		er.setVersion(3);
		er.setId(1);
		er.setName("xx");
		testerService.update(er);
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
		
		testerService.update(list);
	}
	
	@Test
	public void findForUpdateTest(){
		testerService.findForUpdate(1);
//		new Thread(new Runnable() {
//			@Override
//			public void run() {
//				testerService.findForUpdate(1);
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
	
	@Test
	public void findPropertiesById(){
		System.out.println(testerService.findProperties(new String[]{"name", "tName"}, 1));
	}
	
	@Test
	public void findProsByPro(){
		System.out.println(testerService.findProperties(new String[]{"name", "tName"}, "name", "000"));
	}
	
	@Test
	public void findProsByProForPage(){
		System.out.println(testerService.findPropertiesForPage(new String[]{"id", "name", "tName"}, "name", "000", 10, 5));
	}
	
	@Test
	public void findProsByPros(){
		System.out.println(testerService.findProperties(new String[]{"name", "tName"}, new String[]{"name", "id"}, new Object[]{"000", 1}));
	}
	
	@Test
	public void findProsByProsForPage(){
		System.out.println(testerService.findPropertiesForPage(new String[]{"id", "name", "tName", "version"}, new String[]{"name", "version"}, new Object[]{"000", 4}, 10, 5));
	}
}













