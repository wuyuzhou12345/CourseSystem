package org.javatribe.courseSystem.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Student {
	private String stuNo;
	private String schoolName;
	private int dailyCourseCount;
	private boolean longTelePublic;
	private boolean shortTelePublic;
	
private String dept;
private String major;
private String longTele;
private String name;
private String password;
private String gender;
private String shortTele;
private String username;
private List<StuOrgSession> stuOrgSessions=new ArrayList<StuOrgSession>();
	public Student()
	{
		
	}
	public Student(String stuNo)
	{
		this.stuNo = stuNo;
	}
	public Student(String stuNo,String name)
	{
		this.stuNo = stuNo;
		this.name=name;
	}
	public Student(String stuNo, String schoolName, boolean longTelePublic,
			boolean shortTelePublic, String longTele, String name,
			String password, String gender, String shortTele, String username) {
		super();
		this.stuNo = stuNo;
		this.schoolName = schoolName;
		this.longTelePublic = longTelePublic;
		this.shortTelePublic = shortTelePublic;
		this.longTele = longTele;
		this.name = name;
		this.password = password;
		this.gender = gender;
		this.shortTele = shortTele;
		this.username = username;
	}
	
	public Student(String stuNo, String schoolName, int dailyCourseCount,
			boolean longTelePublic, boolean shortTelePublic,
			String longTele, String name, String password, String gender,
			String shortTele, String username, Set<Course> courses,
			Set<Message> messageReceive, Set<Message> messageSend,
			Set<LeaveApplication> leaveApplications) {
		super();
		this.stuNo = stuNo;
		this.schoolName = schoolName;
		this.dailyCourseCount = dailyCourseCount;
		this.longTelePublic = longTelePublic;
		this.shortTelePublic = shortTelePublic;
		this.longTele = longTele;
		this.name = name;
		this.password = password;
		this.gender = gender;
		this.shortTele = shortTele;
		this.username = username;
	}
	public String getSchoolName() {
		return schoolName;
	}
	
	public String getStuNo() {
		return stuNo;
	}
	public int getDailyCourseCount() {
		return dailyCourseCount;
	}
	public String getLongTele() {
		return longTele;
	}
	public String getName() {
		return name;
	}
	public String getPassword() {
		return password;
	}
	public String getGender() {
		return gender;
	}
	public String getShortTele() {
		return shortTele;
	}
	public String getUsername() {
		return username;
	}
	public boolean isLongTelePublic() {
		return longTelePublic;
	}
	public boolean isShortTelePublic() {
		return shortTelePublic;
	}
	public String getDept() {
	return dept;
	
}
	public String getMajor() {
		return major;
	}
	public List<StuOrgSession> getStuOrgSessions() {
		return stuOrgSessions;
	}
	public void setStuOrgSessions(List<StuOrgSession> stuOrgSessions) {
		this.stuOrgSessions = stuOrgSessions;
	}
	public void setMajor(String major) {
		this.major = major;
	}

	public void setDailyCourseCount(int dailyCourseCount) {
		this.dailyCourseCount = dailyCourseCount;
	}

	public void setLongTele(String longTele) {
		this.longTele = longTele;
	}

	public void setName(String name) {
		this.name = name;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	
	public void setShortTele(String shortTele) {
		this.shortTele = shortTele;
	}
	public void setStuNo(String stuNo) {
		this.stuNo = stuNo;
	}
	
	public void setUsername(String username) {
		this.username = username;
		
	}
	public void setLongTelePublic(boolean longTelePublic) {
		this.longTelePublic = longTelePublic;
	}
	
	public void setShortTelePublic(boolean shortTelePublic) {
		this.shortTelePublic = shortTelePublic;
	}

public void setDept(String dept) {
	this.dept = dept;
}

}
