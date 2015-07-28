package com.lin.exception;

/**
 * 连接异常 
 * 
 * @author xuelin
 *
 * 广州房友圈网络技术有限公司
 */
public class HttpConnectException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1176747145120340443L;
	private String message;

	public String getMessage() {
		return message;
	}
	
	public HttpConnectException(String message){
		super(message);
		this.message = message;
	}
	
	public HttpConnectException(){
		super();
	}
}
