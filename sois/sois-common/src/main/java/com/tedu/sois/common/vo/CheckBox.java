package com.tedu.sois.common.vo;

import java.io.Serializable;


public class CheckBox  implements Serializable{
	private static final long serialVersionUID = 5065823170856122977L;

	private Integer id;
	private String name;


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

	@Override
	public String toString() {
		return "CheckBox{" +
				"id=" + id +
				", name='" + name + '\'' +
				'}';
	}
}
