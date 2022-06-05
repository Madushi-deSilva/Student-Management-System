package com.deitel.studentmanagement.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.deitel.studentmanagement.model.Student;




public class StudentDAO {
	
	private String jdbcURL = "jdbc:mysql://localhost:3306/studentmanagementsystem?useSSL=false";
	private String jdbcUsername = "root";
	private String jdbcPassword = "cricket49";
	
	private static final String INSERT_STUDENTS_SQL = "INSERT INTO student" + "  (Student_Name, DOB, Gender, Address, Contact_No) VALUES "
			+ " (?, ?, ?, ?, ?);";

	private static final String SELECT_STUDENT_BY_ID = "select SID,Student_Name, DOB, Gender, Address, Contact_No from student where SID =?";
	private static final String SELECT_ALL_STUDENTS = "select * from student";
	private static final String DELETE_STUDENTS_SQL = "delete from student where SID = ?;";
	private static final String UPDATE_STUDENTS_SQL = "update student set Student_Name = ?,DOB= ?, Gender =?, Address = ?,Contact_No= ? where SID = ?;";

	protected Connection getConnection(){
        Connection connection = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
            
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
	
	//create student
    public void createStudent(Student student) throws SQLException{
    	try(Connection connection = getConnection();
    			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STUDENTS_SQL)){
    		preparedStatement.setString(1, student.getStudent_Name());
            preparedStatement.setString(2, student.getDOB());
            preparedStatement.setString(3, student.getGender());
            preparedStatement.setString(4, student.getAddress());
            preparedStatement.setInt(5, student.getContact_No());
            preparedStatement.executeUpdate();
    	}catch (Exception e){
            e.printStackTrace();
        }           
    }
    
  //update student
    public boolean updateStudent(Student student) throws SQLException{
        boolean rowUpdated;
        try(Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_STUDENTS_SQL)){
        	preparedStatement.setString(1, student.getStudent_Name());
            preparedStatement.setString(2, student.getDOB());
            preparedStatement.setString(3, student.getGender());
            preparedStatement.setString(4, student.getAddress());
            preparedStatement.setInt(5, student.getContact_No());
            preparedStatement.setInt(6, student.getSID());
            
            rowUpdated = preparedStatement.executeUpdate()>0;
        }
        return rowUpdated;
    }

  //select student by id
    public Student selectStudent(int sid){
        Student student = null;
        //Step 1: Establishing a connection
        try(Connection connection = getConnection();
                //Step 2: Create a statement using connection object
                PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STUDENT_BY_ID);){
            preparedStatement.setInt(1, sid);
            System.out.println(preparedStatement);
            //Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();
            
            //Step 4: Process the ResultSet object
            while(rs.next()){
                String student_name = rs.getString("Student_Name");
                String dob = rs.getString("DOB");
                String gender = rs.getString("Gender");
                String address = rs.getString("Address");
                int contact_no = rs.getInt("Contact_No");
                student = new Student(sid, student_name, dob, gender, address, contact_no);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    } 
    
  //select students
    public List<Student> selectAllStudents(){
        List<Student> students = new ArrayList<>();
        //Step 1: Establishing a connection
        try(Connection connection = getConnection();
                //Step 2: Create a statement using connection object
                PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_STUDENTS);){
            System.out.println(preparedStatement);
            //Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();
            
            //Step 4: Process the ResultSet object
            while(rs.next()){
                int sid = rs.getInt("SID");
                String student_name = rs.getString("Student_Name");
                String dob = rs.getString("DOB");
                String gender = rs.getString("Gender");
                String address = rs.getString("Address");
                int contact_no = rs.getInt("Contact_No");
                students.add(new Student(sid, student_name, dob, gender, address, contact_no));
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    } 
    
  //delete students
    public boolean deleteStudent(int sid) throws SQLException{
        boolean rowDeleted;
        try(Connection connection = getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(DELETE_STUDENTS_SQL);){
            preparedStatement.setInt(1, sid);
            rowDeleted = preparedStatement.executeUpdate()>0;
        }
        return rowDeleted;
    }
}
