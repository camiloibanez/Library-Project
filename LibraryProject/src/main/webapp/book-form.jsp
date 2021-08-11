
<%@ include file="header.jsp" %>
<%@ page import="java.util.Date" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="container">

	<% String formType = "update"; %>


	<c:if test="${ book != null }">
	
		<h1>Update Book</h1>
		
	</c:if>
	
	<c:if test = "${ book == null }">
		
		<h1>Add New Book</h1>
		
		<% formType = "add"; %>
		
	</c:if>
	
	<form action="<%= formType %>" method="get" >
	
		<c:if test="${ book != null }">
		
			<input type="hidden" name="isbn" value="<c:out value='${ book.isbn }' />">
			<input type="hidden" name="rented" value="<c:out value='${ book.rented }' />">
			<input type="hidden" name="date_added" value="<c:out value='${ book.date_added }' />">
		
		</c:if>
		
		<c:if test="${ book == null }">
		
			<input type="hidden" name="rented" value="false">
			
			<c:set var="today" value="<%=new Date()%>"/> 
			<input type="hidden" name="date_added" value="<fmt:formatDate type="date" value="${today}" pattern="yyyy-mm-dd"/>">
		
		  <div class="form-group">
		    
		    <label for="isbn" class="form-label">ISBN number</label>
		    <input type="number" class="form-control" id="isbn" name="isbn" required>
		    
		  </div>

		</c:if>
		
	  <div class="form-group">
	    
	    <label for="title" class="form-label">Title</label>
	    <input type="text" class="form-control" id="title" name="title" 
	    	value="<c:out value='${ book.title }' />" required>
	    
	  </div>
	  
	  <div class="form-group">
	  
	    <label for="description" class="form-label">Description</label>
	    <textarea class="form-control" id="description" name="description" rows="3" required>
	    	<c:out value="${ book.description }" />
	    </textarea>
	    
	  </div>
	  
	  <button type="submit" 
	  		  class="btn btn-primary"
	  		  style="margin:10px">Submit</button>
	  
	</form>

</div>


<%@ include file="footer.jsp" %>