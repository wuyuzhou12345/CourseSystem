package org.javatribe.courseSystem.db.model;

import com.ab.db.orm.annotation.Column;
import com.ab.db.orm.annotation.Id;
import com.ab.db.orm.annotation.Table;
/**
 * @author zhou
 *2014年11月29日
 *SQLite 安卓本地数据库学生用户实体对象
 */
@Table(name = "student")
public class Student implements java.io.Serializable {
	@Column(name="name")
	private String name;
	@Column(name = "stuNo")
	private String stuNo;
	@Column(name = "schoolName")
	private String schoolName;
	@Column(name = "username")
	private String username;
	@Column(name = "password")
	private String password;
	@Column(name = "dept")
	private String dept;
	@Column(name = "major")
	private String major;
	@Column(name = "gender")
	private String gender;
	@Column(name = "shortTele")
	private String shortTele;
	@Column(name = "longTele")
	private String longTele;
	@Column(name = "shortTelePublic")
	private boolean shortTelePublic;
	@Column(name = "longTelePublic")
	private boolean longTelePublic;
	@Column(name = "dailyCourseCount")
	private int dailyCourseCount;
	public Student(){
		
	}
	/**
	 * @param name  学生真实姓名
	 * @param stuNo  学生学号
	 * @param schoolName  学校名称
	 * @param username  学生用户名
	 * @param password   密码
	 * @param dept  系别
	 * @param major  专业
	 * @param gender  性别
	 * @param shortTele  短号
	 * @param longTele   长号
	 * @param shortTelePublic  短号是否公开
	 * @param longTelePublic  长号是否公开
	 * @param dailyCourseCount  一天的课时数
	 */
	public Student(String name, String stuNo, String schoolName,
			String username, String password, String dept, String major,
			String gender, String shortTele, String longTele,
			boolean shortTelePublic, boolean longTelePublic,
			int dailyCourseCount) {
		super();
		this.name = name;
		this.stuNo = stuNo;
		this.schoolName = schoolName;
		this.username = username;
		this.password = password;
		this.dept = dept;
		this.major = major;
		this.gender = gender;
		this.shortTele = shortTele;
		this.longTele = longTele;
		this.shortTelePublic = shortTelePublic;
		this.longTelePublic = longTelePublic;
		this.dailyCourseCount = dailyCourseCount;
	}
	public Student(String name, String stuNo) {
		super();
		this.name = name;
		this.stuNo = stuNo;
	}
	public Student(String stuNo) {
		super();
		this.stuNo = stuNo;
	}
	public Student(String stuNo,String schoolName,boolean longTelePublic,boolean shortTelePublic,String longTele,String name,String password,String gender,String shortTele,String username,String dept,String major){
		super();
		this.name = name;
		this.stuNo = stuNo;
		this.schoolName = schoolName;
		this.username = username;
		this.password = password;
		this.dept = dept;
		this.major = major;
		this.gender = gender;
		this.shortTele = shortTele;
		this.longTele = longTele;
		this.shortTelePublic = shortTelePublic;
		this.longTelePublic = longTelePublic;
		
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStuNo() {
		return stuNo;
	}
	public void setStuNo(String stuNo) {
		this.stuNo = stuNo;
	}
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getShortTele() {
		return shortTele;
	}
	public void setShortTele(String shortTele) {
		this.shortTele = shortTele;
	}
	public String getLongTele() {
		return longTele;
	}
	public void setLongTele(String longTele) {
		this.longTele = longTele;
	}
	public boolean isShortTelePublic() {
		return shortTelePublic;
	}
	public void setShortTelePublic(boolean shortTelePublic) {
		this.shortTelePublic = shortTelePublic;
	}

	public boolean isLongTelePublic() {
		return longTelePublic;
	}
	public void setLongTelePublic(boolean longTelePublic) {
		this.longTelePublic = longTelePublic;
	}
	public int getDailyCourseCount() {
		return dailyCourseCount;
	}
	public void setDailyCourseCount(int dailyCourseCount) {
		this.dailyCourseCount = dailyCourseCount;
	}
	
}
