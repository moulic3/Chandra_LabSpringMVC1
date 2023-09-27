package com.gl.lib.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int rno;
	private double per;
	private String sname;
	public Student() {};
	public Student(int rno, double per, String sname) {
		this.rno = rno;
		this.per = per;
		this.sname = sname;
	}
	@Override
	public String toString() {
		return "Student [rno=" + rno + ", per=" + per + ", sname=" + sname + "]";
	}
	
	
	
}
