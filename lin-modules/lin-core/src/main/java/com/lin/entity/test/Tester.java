package com.lin.entity.test;

import java.io.Serializable;

/**
 * 测试实体
 * 
 * @author xuelin
 *
 *         广州房友圈网络技术有限公司
 */
public class Tester implements Serializable {

	/**
	 * uid
	 */
	private static final long serialVersionUID = -6693309003258334015L;

	private Integer id;
	private String name;
	private Integer version;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

}
