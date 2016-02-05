/**
 * 
 */
package org.javatribe.courseSystem.db.model;

import java.util.Date;

import com.ab.db.orm.annotation.Column;
import com.ab.db.orm.annotation.Table;

/**
 * @author zhou
 * @version 创建时间：2014年11月29日 上午10:13:55
 * SQLite 安卓本地数据库学生用户与管理员公告栏实体对象
 */
@Table(name = "notice")
public class Notice implements java.io.Serializable{
	@Column(name="orgId")
	private int orgId;
	@Column(name="noticeId")
	private int noticeId;
	@Column(name="senderName")
	private String senderName;
	@Column(name="sendTime")
	private java.util.Date sendTime;
	@Column(name="noticeTitle")
	private String noticeTitle;
	@Column(name="noticeContent")
	private String noticeContent;
	/**
	 * @param orgId  组织编号
	 * @param noticeId  公告栏编号
	 * @param senderName  发布公告者名字
	 * @param sendTime  发布公告的时间
	 * @param noticeTitle  公告标题
	 * @param noticeContent  公告内容
	 */
	public Notice(int orgId, int noticeId, String senderName,
			Date sendTime, String noticeTitle, String noticeContent) {
		super();
		this.orgId = orgId;
		this.noticeId = noticeId;
		this.senderName = senderName;
		this.sendTime = sendTime;
		this.noticeTitle = noticeTitle;
		this.noticeContent = noticeContent;
	}
	public int getOrgId() {
		return orgId;
	}
	public void setOrgId(int orgId) {
		this.orgId = orgId;
	}
	public int getNoticeId() {
		return noticeId;
	}
	public void setNoticeId(int noticeId) {
		this.noticeId = noticeId;
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
	public String getNoticeTitle() {
		return noticeTitle;
	}
	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}
	public String getNoticeContent() {
		return noticeContent;
	}
	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}
    
	
}
