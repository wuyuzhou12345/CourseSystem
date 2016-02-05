package org.javatribe.courseSystem.vo;

import java.util.List;

/**
 * @author zhou
 *list�еĲ���ʵ�����
 */
public class SessionInfo {
	private String id;
	private String name;
	private List<PersonInfo> personInfos;
	private String json;

	public SessionInfo(){
		
	}
	public SessionInfo(String id,String name,List<PersonInfo> personInfos){
		this.personInfos=personInfos;
		this.id=id;
		this.name=name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<PersonInfo> getPersonInfos() {
		return personInfos;
	}
	public void setPersonInfos(List<PersonInfo> personInfos) {
		this.personInfos = personInfos;
	}
	public String getJson() {
		return json;
	}
	public void setJson(String json) {
		this.json = json;
	}
	
}
