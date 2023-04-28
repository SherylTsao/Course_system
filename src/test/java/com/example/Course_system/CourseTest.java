package com.example.Course_system;

import java.time.LocalTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.Course_system.service.ifs.CourseService;
import com.example.Course_system.vo.CourseRequest;
import com.example.Course_system.vo.CourseResponse;

@SpringBootTest(classes = CourseSystemApplication.class)
public class CourseTest {

	@Autowired
	private CourseService courseService;

	@Test
	public void createCourseTest() {
		CourseRequest request = new CourseRequest();
		request.setCourSeq(1);
		request.setCourName("PE");
		request.setCourCredit(2);
		request.setStartTime(LocalTime.of(8, 00));
		request.setEndTime(LocalTime.of(10, 00));
		System.out.println(request);
	}

	@Test
	public void updateCourseTest() {
		CourseRequest request = new CourseRequest();
		request.setCourSeq(1);
		request.setCourName("PE");
		request.setCourCredit(2);
		request.setStartTime(LocalTime.of(8, 00));
		request.setEndTime(LocalTime.of(10, 00));

		CourseResponse res = courseService.updateCourse(request);
		System.out.println(res.getMessage());
	}

	@Test
	public void deleteCourseTest() {
		CourseRequest request = new CourseRequest();
		request.setCourSeq(1);
		System.out.println(request);
	}

	@Test
	public void readCourseTest() {
		CourseRequest request = new CourseRequest();
		request.setCourSeq(1);
		System.out.println(request);
	}

}
