package com.lin.action.test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lin.entity.test.Tester;
import com.lin.web.BaseController;
import com.lin.web.MediaTypes;

/**
 * action测试类
 * 
 * @author xuelin
 *
 * 广州房友圈网络技术有限公司
 */
@Controller
public class TestController extends BaseController {

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
	
	@RequestMapping(value = "/test/rr.asp")
	public String requestAndResponseTest(HttpServletRequest request, HttpServletResponse response){
		System.out.println("RequestIp: " + getRequestIp(request));
		System.out.println("RequestUrl: " + getRequestUrl(request));
		System.out.println(requestToMap(request, new String[]{"a", "b", "c"}));
		System.out.println("t_1:" + getCookieValue(request, "t_1"));
		System.out.println("t_2:" + getCookieValue(request, "t_2"));
		setContextValue(request);
		setContextValue(request, "test", "hello");
		setSessionValue(request, "test", new Tester());
		setCookie(response, "t_1", "xxx", "127.0.0.1", "/", 1, false);
		setCookie(request, response, "t_2", "ccc", 1);
		return "test";
	}
	
	@RequestMapping(value = "/test/rr2.asp")
	public String requestAndResponseTest2(HttpServletRequest request, HttpServletResponse response){
		System.out.println("RequestIp: " + getRequestIp(request));
		System.out.println("RequestUrl: " + getRequestUrl(request));
		Tester t = getSessionValue(request, "test");
		System.out.println(t);
		System.out.println("t_1:" + getCookieValue(request, "t_1"));
		System.out.println("t_2:" + getCookieValue(request, "t_2"));
		return "test";
	}
}
