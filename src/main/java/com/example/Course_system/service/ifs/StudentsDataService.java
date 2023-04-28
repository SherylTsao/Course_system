package com.example.Course_system.service.ifs;

import java.util.List;

import com.example.Course_system.entity.Course;
import com.example.Course_system.vo.StudentDataResponse;

public interface StudentsDataService {

	public StudentDataResponse addStudentData(List<Course>course);
}
