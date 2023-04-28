package com.example.Course_system.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity // @之前要先在gradle建立依賴網址
@Table(name = "students_data")
public class StudentsData {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 流水編號遞增
	@Column(name = "stu_seq")
	private int stuSeq;// 打建構方法可省略,因為系統自動遞增,所以自己輸入的無效

	@Column(name = "stu_number")
	private int stuNumber;

	@Column(name = "stu_name")
	private String stuName;

	@Column(name = "cour_seq")
	private int courSeq;

	@Column(name = "total_credit")
	private int totalCredit;

	public StudentsData() {
		super();
	}

	public int getStuSeq() {
		return stuSeq;
	}

	public void setStuSeq(int stuSeq) {
		this.stuSeq = stuSeq;
	}

	public int getStuNumber() {
		return stuNumber;
	}

	public void setStuNumber(int stuNumber) {
		this.stuNumber = stuNumber;
	}

	public String getStuName() {
		return stuName;
	}

	public void setStuName(String stuName) {
		this.stuName = stuName;
	}

	public int getCourSeq() {
		return courSeq;
	}

	public void setCourSeq(int courSeq) {
		this.courSeq = courSeq;
	}

	public int getTotalCredit() {
		return totalCredit;
	}

	public void setTotalCredit(int totalCredit) {
		this.totalCredit = totalCredit;
	}

}
