package org.javatribe.courseSystem.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Organization {

	private int orgId;
	private String orgImg;
	private String orgIntroduction;
	private int orgLevel;
	private String orgName;
	private List<Session> sessions=new ArrayList<Session>();
	private Set<LeaveApplication> leaveApplications=new HashSet<LeaveApplication>();
	private Set<Notice> notices=new HashSet<Notice>();
	private Student starter;
public Organization()
{
	
}
public Organization(int orgId)
{
	this.orgId=orgId;
}
	public Organization(String orgIntroduction, int orgLevel, String orgName,
			Student starter) {
		
		this.orgIntroduction = orgIntroduction;
		this.orgLevel = orgLevel;
		this.orgName = orgName;
		this.starter = starter;
	}
	public Organization(String orgImg, String orgIntroduction, int orgLevel,
			String orgName, Student starter) {
		
		this.orgImg = orgImg;
		this.orgIntroduction = orgIntroduction;
		this.orgLevel = orgLevel;
		this.orgName = orgName;
		this.starter = starter;
	}

	public Organization(int orgLevel, String orgName, Student starter) {
		super();
		this.orgLevel = orgLevel;
		this.orgName = orgName;
		this.starter = starter;
	}
	public Set<LeaveApplication> getLeaveApplications() {
		return leaveApplications;
	}
	public Set<Notice> getNotices() {
		return notices;
	}
	public int getOrgId() {
		return orgId;
	}
	public String getOrgImg() {
		return orgImg;
	}
	public String getOrgIntroduction() {
		return orgIntroduction;
	}

	public int getOrgLevel() {
		return orgLevel;
	}
	public String getOrgName() {
		return orgName;
	}
	public List<Session> getSessions() {
		return sessions;
	}
	public Student getStarter() {
		return starter;
	}
	
	public void setStarter(Student starter) {
		this.starter = starter;
	}
	public void setLeaveApplications(Set<LeaveApplication> leaveApplications) {
		this.leaveApplications = leaveApplications;
	}

	public void setNotices(Set<Notice> notices) {
		this.notices = notices;
	}
	public void setOrgId(int orgId) {
		this.orgId = orgId;
	}
	public void setOrgImg(String orgImg) {
		this.orgImg = orgImg;
	}
	public void setOrgIntroduction(String orgIntroduction) {
		this.orgIntroduction = orgIntroduction;
	}
	public void setOrgLevel(int orgLevel) {
		this.orgLevel = orgLevel;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public void setSessions(List<Session> sessions) {
		this.sessions = sessions;
	}
	

	
}
