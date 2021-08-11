
<%@ include file="header.jsp" %>

<div class="container">

	<% String formType = "update"; %>


	<c:if test="${ account != null }">
	
		<h1>Update Account</h1>
		
	</c:if>
	
	<c:if test = "${ account == null }">
		
		<h1>Add New Account</h1>
		
		<% formType = "add"; %>
		
	</c:if>
	
	<form action="<%= formType %>" method="get" >
	
		<c:if test="${ account != null }">

			<input type="hidden" name="id" value="<c:out value='${ account.id }'/>">
			
		</c:if>
		
		<c:if test="${ account == null }">
		
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