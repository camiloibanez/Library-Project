
<%@ include file="header.jsp" %>

<div class="container">

	<c:if test="${ isLibrarian }">
	
		<% String formType = "updatebook"; %>
	
		<c:if test="${ book != null }">
		
			<h1>Update Book</h1>
			
		</c:if>
		
		<c:if test = "${ book == null }">
			
			<h1>Add New Book</h1>
			
			<% formType = "addbook"; %>
			
		</c:if>
		
		<form action="<%= formType %>" method="get" >
		
			<c:if test="${ book != null }">
			
				<input type="hidden" name="isbn" value="<c:out value='${ book.isbn }' />">
				<input type="hidden" name="rented" value="<c:out value='${ book.rented }' />">
				<input type="hidden" name="date_added" value="<c:out value='${ book.added_to_library }' />">
			
			</c:if>
			
			<c:if test="${ book == null }">
			
			  <div class="form-group">
			    
			    <label for="isbn" class="form-label">ISBN number</label>
			    <input type="text" pattern="^[1-9][0-9]{9}$" title="ten digit isbn number" class="form-control" id="isbn" name="isbn" required>
			    
			  </div>
	
			</c:if>
			
		  <div class="form-group">
		    
		    <label for="title" class="form-label">Title</label>
		    <input type="text" class="form-control" id="title" name="title" 
		    	value="<c:out value='${ book.title }' />" required>
		    
		  </div>
		  
		  <div class="form-group">
		  
		    <label for="description" class="form-label">Description</label>
		    <textarea class="form-control" id="description" name="description" rows="3" required><c:out value="${ book.descr }" /></textarea>
		    
		  </div>
		  
		  <button type="submit" 
		  		  class="btn btn-primary"
		  		  style="margin:10px 0px">Submit</button>
		  
		</form>
	
	</c:if>
	
	<c:if test="${ !isLibrarian }">
		<h1>Sorry! You don't have access to this page.</h1>
	</c:if>

</div>


<%@ include file="footer.jsp" %>