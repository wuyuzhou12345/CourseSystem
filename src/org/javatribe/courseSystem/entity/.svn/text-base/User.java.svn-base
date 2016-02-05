package org.javatribe.courseSystem.entity;


import java.util.HashMap;
import java.util.Map;

public class User {
	private String id;
	private String name;
	private String phone;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	private String sex;
	private String dept;
	public User() {
	}
	public User(String id,String name) {
		this.id=id;
		this.name=name;
	}
	public Map<String,Object> toMap(){
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("id",id);
		map.put("name", name);
		return map;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public static Map<String, String> newUser(String id,String name){
		Map<String, String> map = new HashMap<String, String>();
		map.put("id",id);
		map.put("name", name);
		return map;
	}
}
