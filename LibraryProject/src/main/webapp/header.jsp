<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sign In</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We" crossorigin="anonymous">

</head>
<body>

	<div class="container">
		
		<nav class="navbar navbar-expand-lg navbar-light bg-light">
		  <div class="container-fluid">
		  	<c:if test="${ !isLoggedIn }">
		    	<a class="navbar-brand" href="<%= request.getContextPath() %>/">Dashboard</a>
		    </c:if>
		    <c:if test="${ isLoggedIn }">
		    	<a class="navbar-brand" href="<%= request.getContextPath() %>/dashboard">Dashboard</a>
		    </c:if>
		    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
		      <span class="navbar-toggler-icon"></span>
		    </button>
		    <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
		      <div class="navbar-nav">
		        
		        <c:if test="${ isLoggedIn }">
		        
			        <c:if test="${ isLibrarian }">
				        <a class="nav-link" href="<%= request.getContextPath() %>/accounts">Accounts</a>
				        <a class="nav-link" href="<%= request.getContextPath() %>/booklist">Library Books</a>
				        <a class="nav-link" href="<%= request.getContextPath() %>/newbook">Add Book</a>
				        <a class="nav-link" href="<%= request.getContextPath() %>/createLibrarian">Add Librarian</a>
				        <a class="nav-link" href="<%= request.getContextPath() %>/accountForm">Profile</a>
			        	<a class="nav-link" href="<%= request.getContextPath() %>/logout">Logout</a>
			        </c:if>
			        
			        <c:if test="${ !isLibrarian }">
				        <a class="nav-link" href="<%= request.getContextPath() %>/history">Book History</a>
				        <a class="nav-link" href="<%= request.getContextPath() %>/booklist">Library Books</a>
				        <a class="nav-link" href="<%= request.getContextPath() %>/accountForm">Profile</a>
			        	<a class="nav-link" href="<%= request.getContextPath() %>/logout">Logout</a>
			        </c:if>
			        
		        </c:if>
		        
		        <c:if test="${ !isLoggedIn }">
		        	<a class="nav-link" href="<%= request.getContextPath() %>/accountForm">Create Account</a>
		        </c:if>
		        
		        
		      </div>
		    </div>
		  </div>
		</nav>
		