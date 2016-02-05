package org.javatribe.courseSystem.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Message {

	private String messageContent;
	private int messageId;
	private String messageTitle;
	private Organization organization;
	private List<Student> receivers=new ArrayList<Student>();
	private Student sender;
	private java.util.Date sendTime;
	private String json;
	public Message() {
		
	}
	public Message(int messageId) {
		this.messageId=messageId;
	}
	public Message(String messageContent, int messageId, String messageTitle,
			Date sendTime) {
		super();
		this.messageContent = messageContent;
		this.messageId = messageId;
		this.messageTitle = messageTitle;
		this.sendTime = sendTime;
	}

	public Message(String messageContent, String messageTitle,
			Organization organization, List<Student> receivers, Student sender,
			Date sendTime) {
		this.messageContent = messageContent;
		this.messageTitle = messageTitle;
		this.organization = organization;
		this.receivers = receivers;
		this.sender = sender;
		this.sendTime = sendTime;
	}
	public String getMessageContent() {
		return messageContent;
	}
	public int getMessageId() {
		return messageId;
	}
	public String getMessageTitle() {
		return messageTitle;
	}
	public Organization getOrganization() {
		return organization;
	}
	public List<Student> getReceivers() {
		return receivers;
	}
	public Student getSender() {
		return sender;
	}
	public java.util.Date getSendTime() {
		return sendTime;
	}
	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}
	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}
	public void setMessageTitle(String messageTitle) {
		this.messageTitle = messageTitle;
	}
	
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
	public void setReceivers(List<Student> receivers) {
		this.receivers = receivers;
	}

	public void setSender(Student sender) {
		this.sender = sender;
	}
	public void setSendTime(java.util.Date sendTime) {
		this.sendTime = sendTime;
	}
	public String getJson() {
		return json;
	}
	public void setJson(String json) {
		this.json = json;
	}

	
}

