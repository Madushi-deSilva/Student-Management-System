package com.deitel.studentmanagement.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.deitel.studentmanagement.dao.StudentDAO;
import com.deitel.studentmanagement.model.Student;


/**
 * Servlet implementation class StudentServlet
 */
@WebServlet("/")
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StudentDAO studentDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentServlet() {
        this.studentDAO = new StudentDAO();
    }

    /**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getServletPath();
		
		try {
			switch (action) {
			case "/new":
				showNewForm(request, response);
				break;
			case "/insert":
				insertStudent(request, response);
				break;
			case "/delete":
				deleteStudent(request, response);
				break;
			case "/edit":
				showEditForm(request, response);
				break;
			case "/update":
				updateStudent(request, response);
				break;
			default:
				listStudent(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}
    
    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        RequestDispatcher dispatcher = request.getRequestDispatcher("student-form.jsp");
        dispatcher.forward(request, response);
    }
    
    private void insertStudent(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException{
    	String student_name = request.getParameter("Student_Name");
        String dob = request.getParameter("DOB");
        String gender = request.getParameter("Gender");
        String address = request.getParameter("Address");
        int contact_no = Integer.parseInt(request.getParameter("Contact_No"));
        Student newStudent = new Student(student_name, dob, gender, address, contact_no);
        studentDAO.createStudent(newStudent);
        response.sendRedirect("list");
    }
    
    private void deleteStudent(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException{
        int sid = Integer.parseInt(request.getParameter("SID"));
        studentDAO.deleteStudent(sid);
        response.sendRedirect("list");
    }
    
    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
        int sid = Integer.parseInt(request.getParameter("SID"));
        Student existingStudent = studentDAO.selectStudent(sid);
        RequestDispatcher dispatcher = request.getRequestDispatcher("student-form.jsp");
        request.setAttribute("student", existingStudent);
        dispatcher.forward(request, response);
    }
    
    private void updateStudent(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException{
        int sid = Integer.parseInt(request.getParameter("SID"));
        String student_name = request.getParameter("Student_Name");
        String dob = request.getParameter("DOB");
        String gender = request.getParameter("Gender");
        String address = request.getParameter("Address");
        int contact_no = Integer.parseInt(request.getParameter("Contact_No"));
        
        Student newStudent = new Student(sid,student_name, dob, gender, address, contact_no);
        studentDAO.updateStudent(newStudent);
        response.sendRedirect("list");
    } 
     
    private void listStudent(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Student> listStudent = studentDAO.selectAllStudents();
		request.setAttribute("listStudent", listStudent);
		RequestDispatcher dispatcher = request.getRequestDispatcher("student-list.jsp");
		dispatcher.forward(request, response);
	}

	
}
