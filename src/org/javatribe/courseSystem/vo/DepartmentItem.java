package org.javatribe.courseSystem.vo;

import java.util.List;

public class DepartmentItem {
	private String id;
	private String name;
	private List<PersonItem> personItems;
	public DepartmentItem(){
		
	}
	public DepartmentItem(String id,String name,List<PersonItem> personItems){
		this.personItems=personItems;
		this.id=id;
		this.name=name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<PersonItem> getPersonItems() {
		return personItems;
	}
	public void setPersonItems(List<PersonItem> personItems) {
		this.personItems = personItems;
	}
	
}
