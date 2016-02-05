/**
 * 
 */
package org.javatribe.courseSystem.db.model;

import com.ab.db.orm.annotation.Column;
import com.ab.db.orm.annotation.Table;

/**
 * @author zhou
 * @version 创建时间：2014年11月29日 上午10:40:38
 * 某学生作为某些组织管理员的实体对象
 */
@Table(name="adminAndOrganization")
public class AdminAndOrganization implements java.io.Serializable{
	@Column(name="stuNo")
	String stuNo;
	@Column(name="orgId")
	String orgId;
	@Column(name="orgName")
	String orgName;
	@Column(name="orgImg")
	String orgImg;
	@Column(name="orgIntroduction")
	String orgIntroduction;
	/**
	 * @param stuNo 学生学号
	 * @param orgId  学生作为管理员的组织编号
	 * @param orgName 学生作为管理员的组织名称
	 * @param orgImg  组织头像保存路径
	 * @param orgIntroduction  组织简介
	 */
	public AdminAndOrganization(String stuNo, String orgId, String orgName,
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
