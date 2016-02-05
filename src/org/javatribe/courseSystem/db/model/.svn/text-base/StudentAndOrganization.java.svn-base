/**
 * 
 */
package org.javatribe.courseSystem.db.model;

import com.ab.db.orm.annotation.Column;
import com.ab.db.orm.annotation.Table;

/**
 * @author zhou
 * @version 创建时间：2014年11月29日 上午10:26:26
 * SQLite 安卓本地数据库学生与组织对象关系的实体对象
 */
@Table(name="studentAndOrganization")
public class StudentAndOrganization implements java.io.Serializable{
	@Column(name="stuNo")
	private String stuNo;
	@Column(name="orgId")
	private String orgId;
	@Column(name="orgName")
	private String orgName;
	@Column(name="orgImg")
	private String orgImg;
	@Column(name="orgIntroduction")
	private String orgIntroduction;
	/**
	 * @param stuNo  学生学号
	 * @param orgId  组织编号
	 * @param orgName  组织名字
	 * @param orgImg  组织头像存放路径
	 * @param orgIntroduction  组织简介
	 */
	public StudentAndOrganization(String stuNo, String orgId, String orgName,
			String orgImg, String orgIntroduction) {
		super();
		this.stuNo = stuNo;
		this.orgId = orgId;
		this.orgName = orgName;
		this.orgImg = orgImg;
		this.orgIntroduction = orgIntroduction;
	}
	public String getStuNo() {
		return stuNo;
	}
	public void setStuNo(String stuNo) {
		this.stuNo = stuNo;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getOrgImg() {
		return orgImg;
	}
	public void setOrgImg(String orgImg) {
		this.orgImg = orgImg;
	}
	public String getOrgIntroduction() {
		return orgIntroduction;
	}
	public void setOrgIntroduction(String orgIntroduction) {
		this.orgIntroduction = orgIntroduction;
	}
	
	
}
