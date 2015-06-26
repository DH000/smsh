package com.lin.action.test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lin.web.MediaTypes;

/**
 * action测试类
 * 
 * @author xuelin
 *
 * 广州房友圈网络技术有限公司
 */
@Controller
@Scope("prototype")
public class TestController {

	@RequestMapping(value = "/test/hello/{name}/name.asp", produces = {MediaTypes.JSON_UTF_8})
	public Object hello(@PathVariable String name, HttpServletRequest request, HttpServletResponse response){
		System.out.println(name);
		
		return new ResponseEntity<String>("{\"st\":\"ok\"}", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/test/hello/index.asp")
	public String hello(HttpServletRequest request, HttpServletResponse response){
		System.out.println("hello");
		return "index";
	}
	
}
