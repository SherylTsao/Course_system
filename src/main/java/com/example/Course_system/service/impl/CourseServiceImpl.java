package com.example.Course_system.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.Course_system.contants.RtnCode;
import com.example.Course_system.entity.Course;
import com.example.Course_system.entity.StudentsData;
import com.example.Course_system.respository.CourseDao;
import com.example.Course_system.respository.StudentsDataDao;
import com.example.Course_system.service.ifs.CourseService;
import com.example.Course_system.vo.CourseRequest;
import com.example.Course_system.vo.CourseResponse;

@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	private CourseDao courseDao;

	@Autowired
	private StudentsDataDao studentsDataDao;

	// @Autowired
	// private String patternWeekDay = "^(?=.*[Mon|Tue|Wed|Thu|Fri|Sat|Sun])$";

	/*
	 * 建立課程表 可能會有不同課程代碼但名稱相同 防呆:null或空(課程名稱,上課星期,學分數,上課下課時間)
	 */
	@Override
	public CourseResponse createCourse(CourseRequest request) {
		// 抽出去方法
		// 防呆:null或空
		if (!StringUtils.hasText(request.getCourName()) || !StringUtils.hasText(request.getCourDay())
				|| request.getCourCredit() == 0 || request.getStartTime() == null || request.getEndTime() == null) {
			return new CourseResponse(RtnCode.CANNOT_EMPTY.getMessage());
		}

		// 防呆:上課時間不可以晚於下課時間,反之亦然
		if (request.getStartTime().getHour() > request.getEndTime().getHour()) {
			return new CourseResponse(RtnCode.TIME_ERROR.getMessage());
		}
		// 防呆:學分數的設定不可以超過3
		if (request.getCourCredit() < 0 && request.getCourCredit() >= 4) {
			return new CourseResponse(RtnCode.DATA_ERROR.getMessage());
		}
		// 防呆:上課星期不可以超過周一~周六

		List<String> day = Arrays.asList("一", "二", "三", "四", "五", "六");
		if (!day.contains(request.getCourDay())) {
			return new CourseResponse(RtnCode.TIME_ERROR.getMessage());
		}

		// 存入資料庫
		Course courseIn = new Course(request.getCourName(), request.getCourDay(), request.getStartTime(),
				request.getEndTime(), request.getCourCredit());
		courseDao.save(courseIn);
		return new CourseResponse(RtnCode.SUCCESSFUL.getMessage());
	}

	/*
	 * 更新修改(編輯)舊的(無法新增資料庫未建立的)課程表的某些資料:更改課程名稱or上課星期or上下課時間or學分數 要先找到該筆資料
	 * 從資料庫取出要更改的資料,再存入資料庫
	 */
	@Override
	public CourseResponse updateCourse(CourseRequest request) {

		// 防呆:資料庫已有課程資料,才可以編輯
		Course course = courseDao.findAllByCourSeq(request.getCourSeq());
		if (course == null) {
			return new CourseResponse(RtnCode.NOT_FOUND.getMessage());
		} 
		// List放入修改過後的資料(加上List是因為可以依據使用者需求一次輸入單筆或多筆)
		List<Course> updateList = new ArrayList<>();

		// 傳入的課程名稱如果不是空的(使用者要輸入要修改的資料)
		if (StringUtils.hasText(request.getCourName())) {
			// 重新設置名稱
			course.setCourName(request.getCourName());
			// 將修改後的名稱放入List
			updateList.add(course);
		}
		// 傳入的上課星期如果不是空的(使用者要輸入要修改的資料)
		if (StringUtils.hasText(request.getCourDay())) {
			// 重新設置上課星期
			course.setCourDay(request.getCourDay());
			// 將修改後的上課星期放入List
			updateList.add(course);
		}
		// 如果傳入的(要求的)學分介於1~3之間(使用者要輸入要修改的資料)
		if (request.getCourCredit() > 0 && request.getCourCredit() <= 3) {
			// 重新設置學分
			course.setCourCredit(request.getCourCredit());
			// 將修改後的學分放入List
			updateList.add(course);

		}
		/*
		 * 如果傳入的(要求的)上下課時間不為空(使用者要輸入要修改的資料), 注意!!要先判斷上下課時間是否不為空,才能再判斷上課時間是否有小於下課時間
		 * 因為request.get取出的資料不能為null,否則會報錯
		 */
		if (request.getStartTime() != null && request.getEndTime() != null
				&& request.getStartTime().isBefore(request.getEndTime())) {
			// 重新設置上下課時間
			course.setStartTime(request.getStartTime());
			course.setEndTime(request.getEndTime());
			// 將修改後的上下課時間放入List
			updateList.add(course);
		}
		// 一次存入單筆或多筆修改後的資料
		courseDao.saveAll(updateList);
		return new CourseResponse(RtnCode.SUCCESSFUL.getMessage());
	}

	@Override
	public CourseResponse deleteCourse(CourseRequest request) {
		// 要先檢查是否有學生選修
		List<StudentsData> courSeq = studentsDataDao.findAllByCourSeq(request.getCourSeq());
		if (!courSeq.isEmpty()) {
			return new CourseResponse(RtnCode.STUDENT_SELECT.getMessage());
		}
		// 防呆:資料庫已有課程資料,才可以刪除
		Optional<Course> op = courseDao.findById(request.getCourSeq());
		// 找不到資料
		if (op.isEmpty()) {
			return new CourseResponse(RtnCode.NOT_FOUND.getMessage());
		}
		// 從資料庫取出要刪除的資料
		Course allData = op.get();
		// 刪除資料庫內容
		courseDao.delete(allData);
		return new CourseResponse(RtnCode.SUCCESSFUL.getMessage());
	}

	@Override
	public CourseResponse readCourse(CourseRequest request) {
		// 若外部有傳入課程名稱(非null或空)
		if (StringUtils.hasText(request.getCourName())) {
			// 用課程名稱,查詢所有資料(查出單或多筆)
			List<Course> all = courseDao.findAllByCourName(request.getCourName());

			// 防呆:找不到資料
			if (all.isEmpty()) {

				return new CourseResponse(RtnCode.NOT_FOUND.getMessage());
			}
			if (!all.isEmpty()) {

				return new CourseResponse(all, RtnCode.SUCCESSFUL.getMessage());
			}
		}
		if (request.getCourSeq() != 0) {// 依課程代碼(查出一筆)
			Optional<Course> op = courseDao.findById(request.getCourSeq());

			if (op.isEmpty()) {
				return new CourseResponse(RtnCode.NOT_FOUND.getMessage());
			}
			if (!op.isEmpty()) {
				return new CourseResponse(op, RtnCode.SUCCESSFUL.getMessage());
			}
		}
		// 若沒有輸入名稱或流水號查詢,回傳錯誤資訊
		return new CourseResponse(RtnCode.NOT_FOUND.getMessage());

	}

}
