package org.javatribe.courseSystem.model;

import java.util.HashSet;
import java.util.Set;


public class SecretProtectQuestion {
	private int questionId;
	private String questionContent;
	private Set<Student> students=new HashSet<Student>();

	public SecretProtectQuestion()
	{
		
	}
	
	public SecretProtectQuestion(String questionContent) {
		
		this.questionContent = questionContent;
	}

	public int getQuestionId() {
		return questionId;
	}
	
	public String getQuestionContent() {
		return questionContent;
	}

	public Set<Student> getStudents() {
		return students;
	}
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	public void setStudents(Set<Student> students) {
		this.students = students;
	}

	public void setQuestionContent(String questionContent) {
		this.questionContent = questionContent;
	}
	
}
