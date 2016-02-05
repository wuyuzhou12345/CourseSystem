package org.javatribe.courseSystem.vo;

public class PersonItem {
	private String id;
	private String name;
	private String tele_short;
	private int image_power;
	private String tele_long;
	private String dept;
	public String getTele_long() {
		return tele_long;
	}
	public void setTele_long(String tele_long) {
		this.tele_long = tele_long;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public PersonItem(){
		
	}
	public int getImage_power() {
		return image_power;
	}
	public void setImage_power(int image_power) {
		this.image_power = image_power;
	}
	public PersonItem(String id,String name,String tele_short){
		this.id=id;
		this.name=name;
		this.tele_short=tele_short;
	}
	public PersonItem(String id,String name){
		this.id=id;
		this.name=name;
	}
	public PersonItem(String id,String name,int image_power){
		this.id=id;
		this.name=name;
		this.image_power=image_power;
	}
	public PersonItem(String id,String name,int image_power,String tele_short,String tele_long,String dept){
		this.id=id;
		this.name=name;
		this.image_power=image_power;
		this.tele_short=tele_short;
		this.tele_long=tele_long;
		this.dept=dept;
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
	public void setTele_short(String tele_short){
		this.tele_short=tele_short;
	}
	public String getTele_short(){
		return tele_short;
	}
	
}
