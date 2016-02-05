/**
 * 
 */
package org.javatribe.courseSystem.db.model;

import java.util.Date;

import com.ab.db.orm.annotation.Column;
import com.ab.db.orm.annotation.Table;

/**
 * @author zhou
 * @version 
 * SQLite 安卓本地数据库学生用户请假条实体对象
 */
@Table(name = "studentLeaveApplication")
public class StudentLeaveApplication implements java.io.Serializable{
	@Column(name="orgId")
	private int orgId;
	@Column(name="leaveId")
	private int leaveId;
	@Column(name="leaveReason")
	private String leaveReason;
	@Column(name="leaveEvent")
	private String leaveEvent;
	@Column(name="sendTime")
	private java.util.Date sendTime;
	/**
	 * @param orgId  组织编号
	 * @param leaveId 请假条编号
	 * @param leaveReason  请假原因
	 * @param leaveEvent  请假事件
	 * @param sendTime  请假条发送时间
	 */
	public StudentLeaveApplication(int orgId, int leaveId, String leaveReason,
			String leaveEvent, Date sendTime) {
		super();
		this.orgId = orgId;
		this.leaveId = leaveId;
		this.leaveReason = leaveReason;
		this.leaveEvent = leaveEvent;
		this.sendTime = sendTime;
	}
	public int getOrgId() {
		return orgId;
	}
	public void setOrgId(int orgId) {
		this.orgId = orgId;
	}
	public int getLeaveId() {
		return leaveId;
	}
	public void setLeaveId(int leaveId) {
		this.leaveId = leaveId;
	}
	public String getLeaveReason() {
		return leaveReason;
	}
	public void setLeaveReason(String leaveReason) {
		this.leaveReason = leaveReason;
	}
	public String getLeaveEvent() {
		return leaveEvent;
	}
	public void setLeaveEvent(String leaveEvent) {
		this.leaveEvent = leaveEvent;
	}
	public java.util.Date getSendTime() {
		return sendTime;
	}
	public void setSendTime(java.util.Date sendTime) {
		this.sendTime = sendTime;
	}
	
	
}
