package com.example.Course_system.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.example.Course_system.contants.RtnCode;
import com.example.Course_system.entity.Course;

import com.example.Course_system.service.ifs.StudentsDataService;
import com.example.Course_system.vo.StudentDataResponse;

@Service
public class StudentsDataServiceImpl implements StudentsDataService {

	@Override
	public StudentDataResponse addStudentData(List<Course> course) {

		for (Course item : course) {// foreach檢查每筆資料
			if (CollectionUtils.isEmpty(course)) { // 長度不能為0

				return new StudentDataResponse(RtnCode.CANNOT_EMPTY.getMessage());
			}
			// 新增姓名不可以為null,若因為一次新增多筆,第二筆不得空白,
			if (!StringUtils.hasText(item.getCourName())) {

				return new StudentDataResponse(RtnCode.CANNOT_EMPTY.getMessage());
				

			}
			// 新增學號不可以為null
			if (item.getCourNumber() <= 0) {
				 return new StudentDataResponse(RtnCode.CANNOT_EMPTY.getMessage());
			}
			// StudentsDataDao.saveAll(course);

			return new StudentDataResponse();
		}
		return null;

	}
}
