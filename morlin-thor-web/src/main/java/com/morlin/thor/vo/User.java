package com.morlin.thor.vo;

import java.io.Serializable;

public class User implements Serializable{

	private static final long serialVersionUID = -542724292556272333L;
	
	private Long userId;
	
	private String name;
	
	private Integer sex;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}
	

}
