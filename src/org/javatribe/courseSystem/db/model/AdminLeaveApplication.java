/**
 * 
 */
package org.javatribe.courseSystem.db.model;

import java.util.Date;

import com.ab.db.orm.annotation.Column;
import com.ab.db.orm.annotation.Table;

/**
 * @author zhou
 * @version 创建时间：2014年11月29日 上午10:44:46
 * 管理员管理请假条实体对象
 */
@Table(name="adminLeaveApplication")
public class AdminLeaveApplication implements java.io.Serializable{
	@Column(name="orgId")
	private int orgId;
	@Column(name="leaveId")
	private int leaveId;
	@Column(name="sessionName")
	private String sessionName;
	@Column(name="senderName")
	private String senderName;
	@Column(name="sendTime")
	private java.util.Date sendTime;
	@Column(name="leaveEvent")
	private String leaveEvent;
	@Column(name="leaveReason")
	private String leaveReason;
	/**
	 * @param orgId  学生作为管理员的某组织编号
	 * @param sessionName  某请假同学的部门名称
	 * @param senderName  某请假同学的名字
	 * @param sendTime  某请假同学提交请假条的时间
	 * @param leaveEvent 请假事件
	 * @param leaveReason  请假原因
	 * @param leaveId   请假条编号
	 */
	public AdminLeaveApplication(int orgId, int leaveId,String sessionName,
			String senderName, Date sendTime, String leaveEvent,
			String leaveReason) {
		super();
		this.orgId = orgId;
		this.leaveId=leaveId;
		this.sessionName = sessionName;
		this.senderName = senderName;
		this.sendTime = sendTime;
		this.leaveEvent = leaveEvent;
		this.leaveReason = leaveReason;
	}
	public int getLeaveId() {
		return leaveId;
	}
	public void setLeaveId(int leaveId) {
		this.leaveId = leaveId;
	}
	public int getOrgId() {
		return orgId;
	}
	public void setOrgId(int orgId) {
		this.orgId = orgId;
	}
	public String getSessionName() {
		return sessionName;
	}
	public void setSessionName(String sessionName) {
		this.sessionName = sessionName;
	}
	public String getSenderName() {
		return senderName;
	}
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
	public java.util.Date getSendTime() {
		return sendTime;
	}
	public void setSendTime(java.util.Date sendTime) {
		this.sendTime = sendTime;
	}
	public String getLeaveEvent() {
		return leaveEvent;
	}
	public void setLeaveEvent(String leaveEvent) {
		this.leaveEvent = leaveEvent;
	}
	public String getLeaveReason() {
		return leaveReason;
	}
	public void setLeaveReason(String leaveReason) {
		this.leaveReason = leaveReason;
	}
	
}