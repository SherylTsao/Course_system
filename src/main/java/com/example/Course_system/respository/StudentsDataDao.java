package com.example.Course_system.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Course_system.entity.StudentsData;




@Repository
public interface StudentsDataDao extends JpaRepository<StudentsData, Integer>{
    //List回傳多筆
	public List<StudentsData> findAllByCourSeq(int courSeq);
}
