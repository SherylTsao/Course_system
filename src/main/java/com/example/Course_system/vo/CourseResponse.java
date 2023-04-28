package com.example.Course_system.vo;

import java.util.List;
import java.util.Optional;

import com.example.Course_system.entity.Course;

public class CourseResponse {
	private List<Course> course;

	private Optional<Course> op;

	private String message;
	
	

	public CourseResponse() {
		super();

	}

	public CourseResponse(String message) {
		super();
		this.message = message;
	}

	public CourseResponse(List<Course> course) {
		super();
		this.course = course;
	}

	public CourseResponse(Optional<Course> op) {
		super();
		this.op = op;
	}

	public CourseResponse(List<Course> course, String message) {
		super();
		this.course = course;
		this.message = message;
	}

	public CourseResponse(Optional<Course> op, String message) {
		super();
		this.op = op;
		this.message = message;
	}

	public List<Course> getCourse() {
		return course;
	}

	public void setCourse(List<Course> course) {
		this.course = course;
	}

	public Optional<Course> getOp() {
		return op;
	}

	public void setOp(Optional<Course> op) {
		this.op = op;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	

}
