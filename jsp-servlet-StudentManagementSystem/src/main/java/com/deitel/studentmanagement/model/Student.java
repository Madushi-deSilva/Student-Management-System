package com.deitel.studentmanagement.model;

public class Student {
	
	private int SID;
	private String Student_Name;
	private String DOB;
	private String Gender;
	private String Address;
	private int Contact_No;
	
	
	public Student(int sID, String student_Name, String dOB, String gender, String address, int contact_No) {
		super();
		SID = sID;
		Student_Name = student_Name;
		DOB = dOB;
		Gender = gender;
		Address = address;
		Contact_No = contact_No;
	}
	
	
	public Student(String student_Name, String dOB, String gender, String address, int contact_No) {
		super();
		Student_Name = student_Name;
		DOB = dOB;
		Gender = gender;
		Address = address;
		Contact_No = contact_No;
	}


	public int getSID() {
		return SID;
	}
	public void setSID(int sID) {
		SID = sID;
	}
	public String getStudent_Name() {
		return Student_Name;
	}
	public void setStudent_Name(String student_Name) {
		Student_Name = student_Name;
	}
	public String getDOB() {
		return DOB;
	}
	public void setDOB(String dOB) {
		DOB = dOB;
	}
	public String getGender() {
		return Gender;
	}
	public void setGender(String gender) {
		Gender = gender;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public int getContact_No() {
		return Contact_No;
	}
	public void setContact_No(int contact_No) {
		Contact_No = contact_No;
	}
	
	

}
