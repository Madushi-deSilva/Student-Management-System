<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>User Management Application</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
	
	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.13.0/css/all.css">
</head>
<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: #6600CC">
			<div>
				<a href="https://www.javaguides.net" class="navbar-brand"> Student
					Management System </a>
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list"
					class="nav-link">Students</a></li>
			</ul>
		</nav>
	</header>
	<br>

	<div class="row">
		<!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

		<div class="container">
			<h3 class="text-center">List of Students</h3>
			<hr>
			<div class="container text-left">

				<a href="<%=request.getContextPath()%>/new" class="btn btn-success">Add
					New Student</a>
			</div>
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>ID</th>
						<th>Name</th>
						<th>Date of Birth</th>
						<th>Gender</th>
						<th>Address</th>
						<th>Contact No</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
					<!--   for (Todo todo: todos) {  -->
					<c:forEach var="student" items="${listStudent}">

						<tr>
							<td><c:out value="${student.SID}" /></td>
							<td><c:out value="${student.student_Name}" /></td>
							<td><c:out value="${student.DOB}" /></td>
							<td><c:out value="${student.gender}" /></td>
							<td><c:out value="${student.address}" /></td>
							<td><c:out value="${student.contact_No}" /></td>
							<td><a class="btn-primary btn-sm" href="edit?SID=<c:out value='${student.SID}' />"><i class="fas fa-edit mr-2"></i>Edit</a>
								&nbsp;&nbsp;&nbsp;&nbsp; 
								<a class="btn-danger btn-sm" href="delete?SID=<c:out value='${student.SID}' />"><i class="fas fa-trash-alt mr-2"></i>Delete</a></td>
						</tr>
					</c:forEach>
					<!-- } -->
				</tbody>

			</table>
		</div>
	</div>
</body>
</html>
