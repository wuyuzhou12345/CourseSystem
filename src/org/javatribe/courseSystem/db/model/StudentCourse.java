/**
 * 
 */
package org.javatribe.courseSystem.db.model;

import java.io.Serializable;

import com.ab.db.orm.annotation.Column;
import com.ab.db.orm.annotation.Table;

/**
 * @author zhou
 * @version 创建时间：2014年11月29日 上午10:20:25
 * SQLite 安卓本地数据库学生用户无课表实体对象
 */
@Table(name="studentCourse")
public class StudentCourse implements Serializable{
	@Column(name="stuNo")
	private String stuNo;
	@Column(name="courseId")
	private int courseId;
	@Column(name="courseNo")
	private int courseNo;
	@Column(name="weekType")
	private int weekType;
	@Column(name="weekday")
	private int weekday;
	/**
	 * @param stuNo  学生学号
	 * @param courseId  自增长编号
	 * @param courseNo 课程号
	 * @param weekType  单周、双周、不限
	 * @param weekday 星期几
	 */
	public StudentCourse(String stuNo, int courseId, int courseNo,
			int weekType, int weekday) {
		super();
		this.stuNo = stuNo;
		this.courseId = courseId;
		this.courseNo = courseNo;
		this.weekType = weekType;
		this.weekday = weekday;
	}
	public String getStuNo() {
		return stuNo;
	}
	public void setStuNo(String stuNo) {
		this.stuNo = stuNo;
	}
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public int getCourseNo() {
		return courseNo;
	}
	public void setCourseNo(int courseNo) {
		this.courseNo = courseNo;
	}
	public int getWeekType() {
		return weekType;
	}
	public void setWeekType(int weekType) {
		this.weekType = weekType;
	}
	public int getWeekday() {
		return weekday;
	}
	public void setWeekday(int weekday) {
		this.weekday = weekday;
	}
	
	
}
