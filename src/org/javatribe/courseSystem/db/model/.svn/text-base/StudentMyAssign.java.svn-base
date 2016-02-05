/**
 * 
 */
package org.javatribe.courseSystem.db.model;

import java.util.Date;

import com.ab.db.orm.annotation.Column;
import com.ab.db.orm.annotation.Table;

/**
 * @author zhou
 * @version 创建时间：2014年11月29日 上午10:01:58
 * SQLite 安卓本地数据库学生用户的我的任务实体对象
 */
@Table(name = "studentMyAssign")
public class StudentMyAssign implements java.io.Serializable{
	@Column(name="orgId")
	private int orgId;
	@Column(name="messageId")
	private int messageId;
	@Column(name="senderName")
	private String senderName;
	@Column(name="sendTime")
	private java.util.Date sendTime;
	@Column(name="messageTitle")
	private String messageTitle;
	@Column(name="messageContent")
	private String messageContent;
	/**
	 * @param orgId 组织编号
	 * @param messageId 我的任务（消息）编号
	 * @param senderName 发送人的名字
	 * @param sendTime 发送任务的时间
	 * @param messageTitle 任务标题
	 * @param messageContent 任务内容
	 */
	public StudentMyAssign(int orgId, int messageId, String senderName,
			Date sendTime, String messageTitle, String messageContent) {
		super();
		this.orgId = orgId;
		this.messageId = messageId;
		this.senderName = senderName;
		this.sendTime = sendTime;
		this.messageTitle = messageTitle;
		this.messageContent = messageContent;
	}
	public int getOrgId() {
		return orgId;
	}
	public void setOrgId(int orgId) {
		this.orgId = orgId;
	}
	public int getMessageId() {
		return messageId;
	}
	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}
	public String getSenderName() {
		return senderName;
	}
	public void setSender_name(String senderName) {
		this.senderName = senderName;
	}
	public java.util.Date getSendTime() {
		return sendTime;
	}
	public void setSendTime(java.util.Date sendTime) {
		this.sendTime = sendTime;
	}
	public String getMessageTitle() {
		return messageTitle;
	}
	public void setMessageTitle(String messageTitle) {
		this.messageTitle = messageTitle;
	}
	public String getMessageContent() {
		return messageContent;
	}
	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}
	
}
