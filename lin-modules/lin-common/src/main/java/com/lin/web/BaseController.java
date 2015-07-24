package com.lin.web;

import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Maps;

/**
 * 
 * @ClassName: BaseController
 * @Description: action基类
 * @author xuelin
 * @date Jul 24, 2015 10:25:40 AM
 *
 */
public abstract class BaseController {
//	private static final ThreadLocal<HttpServletRequest> requestLocal = new NamedThreadLocal<>("Request object");
//	private static final ThreadLocal<HttpServletResponse> responseLocal = new NamedThreadLocal<>("Response object");
//	
//	/**
//	 * 请求时设置request和response
//	 * 
//	 * @param request
//	 * @param response
//	 */
//	@ModelAttribute
//	public void setRequestAndResponse(HttpServletRequest request, HttpServletResponse response){
//		requestLocal.set(request);
//		responseLocal.set(response);
//	}
//	
//	public static HttpServletRequest getRequest() {
//		return requestLocal.get();
//	}
//
//	public static HttpServletResponse getResponse() {
//		return responseLocal.get();
//	}

	/**
	 * 不返回空字符参数、不存在参数。多个同名参数除外，并且返回字符串数组
	 * 
	 * @param request
	 * @param paramKeys
	 * @return
	 */
	public static Map<String, Object> requestToMap(HttpServletRequest request, String[] paramKeys) {
		int len = paramKeys.length;
		if (null == paramKeys || 0 == len) {
			return Maps.newHashMap();
		}

		Map<String, Object> map = Maps.newHashMap();
		for (int i = 0; i < len; i++) {
			String key = paramKeys[i];
			String[] vals = request.getParameterValues(key);

			if (null != vals && vals.length > 1) {
				map.put(key, vals);
			} else if (null != vals && 1 == vals.length && !StringUtils.isEmpty(vals[0])) {
				map.put(key, vals[0]);
			}
		}

		return map;
	}

	/**
	 * 请求上下文携带参数
	 * 
	 * @param key
	 * @param value
	 */
	public static void setContextValue(HttpServletRequest request, String key, String value) {
		request.setAttribute(key, value);
	}

	/**
	 * 把所有参数设置到请求上下文
	 * 
	 */
	@SuppressWarnings("rawtypes")
	public static void setContextValue(HttpServletRequest request) {
		for (Enumeration names = request.getParameterNames(); names.hasMoreElements();) {
			String name = names.nextElement().toString();
			setContextValue(request, name, request.getParameter(name));
		}
	}

	public static HttpSession getSession(HttpServletRequest request) {
		return request.getSession(true);
	}

	public static void setSessionValue(HttpServletRequest request, String name, Object value) {
		getSession(request).setAttribute(name, value);
	}

	@SuppressWarnings("unchecked")
	public static <T> T getSessionValue(HttpServletRequest request, String name) {
		return (T) getSession(request).getAttribute(name);
	}

	/**
	 * 获取cookie
	 * 
	 * @param name
	 * @return
	 */
	public static Cookie getCookie(HttpServletRequest request, String name) {
		if (StringUtils.isEmpty(name)) {
			return null;
		}

		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			if (name.equals(cookie.getName())) {
				return cookie;
			}
		}

		return null;
	}

	/**
	 * 获取cookie值
	 * 
	 * @param name
	 * @return
	 */
	public static String getCookieValue(HttpServletRequest request, String name) {
		Cookie cookie = getCookie(request, name);
		if (null != cookie) {
			return cookie.getValue();
		}

		return null;
	}

	/**
	 * 获取请求ip地址
	 * 
	 * @return
	 */
	public static String getRequestIp(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		if("unknown".equals(ip)){
			return null;
		}
		return ip;
	}
	
	/**
	 * 不带参数完成url
	 * 
	 * @return
	 */
	public static String getRequestUrl(HttpServletRequest request){
		return request.getRequestURL().toString();
	}
	
	/**
	 * 设置cookie
	 * 
	 * @param name
	 * @param value
	 * @param domain
	 * @param uri
	 * @param days
	 * @param secure
	 */
	public static void setCookie(HttpServletResponse response, String name, String value, String domain, String uri, int days, boolean secure){
		if(days <= 0){
			return;
		}
		
		Cookie cookie = new Cookie(name, value);
		if(!StringUtils.isEmpty(domain)){
			cookie.setDomain(domain);
		}
		if(!StringUtils.isEmpty(uri)){
			cookie.setPath(uri);
		}
		if(secure){
			cookie.setSecure(secure);
		}
		cookie.setMaxAge(3600 * 24 * days);
		
		response.addCookie(cookie);
	}
	
	/**
	 * 设置cookie
	 * 
	 * @param name
	 * @param value
	 * @param days
	 */
	public static void setCookie(HttpServletRequest request, HttpServletResponse response, String name, String value, int days){
		setCookie(response, name, value, request.getServerName(), request.getRequestURI(), days, false);
	}
}
