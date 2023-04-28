package com.example.Course_system.vo;

import java.util.List;

import com.example.Course_system.entity.Course;



public class StudentDataResponse {

	List<Course>course;
	
	String message;
	

	public StudentDataResponse() {
		super();
		
	}

	public StudentDataResponse(String message) {
		super();
		this.message = message;
	}


	public List<Course> getCourse() {
		return course;
	}

	public void setCourse(List<Course> course) {
		this.course = course;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
