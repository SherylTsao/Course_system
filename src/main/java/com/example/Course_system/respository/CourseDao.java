package com.example.Course_system.respository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Course_system.entity.Course;

@Repository
public interface CourseDao extends JpaRepository<Course, Integer> {
    //回傳多筆
	public List<Course> findAllByCourName(String name);
    //回傳單筆
	public Course findAllByCourSeq(int courSeq);

}
