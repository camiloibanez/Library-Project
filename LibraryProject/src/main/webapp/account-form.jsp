
<%@ include file="header.jsp" %>

<div class="container">

	<% String formType = "updateuser"; %>
	
	<c:if test="${ user != null }">
		<h1>Update Account</h1>
	</c:if>

	<c:if test="${ user == null }">
		<h1>Create Account</h1>
		
		<% formType = "adduser"; %>
	</c:if>
	
	<form action="<%= formType %>" method="get">
		
		<%-- if user object exists, we're editing a current user --%>
		<c:if test="${ user != null }">
			
			<c:if test="${ isLibrarian }">
				<input type="hidden" name="id" value="<c:out value='${ user.librarian_id }' />">
			</c:if>
			<c:if test="${ !isLibrarian }">
				<input type="hidden" name="id" value="<c:out value='${ user.patron_id }' />">
			</c:if>
			
		</c:if>
		
		<div class="form-group">
			<label for="username" class="form-label">Username</label> 
			<input type="text" id="username" name = "username" class="form-control" value="<c:out value='${ user.username }' />" required>
		</div>
			
		<div class="form-group">
			<label for="pw" class="form-label">Password</label> 
			<input type="password" id="pw" name = "pw" class="form-control" value="<c:out value='${ user.password }' />" required>
		</div>
		
		<%-- Only show inputs for first and last name if user is a patron --%>
		<c:if test="${ !isLibrarian }">
			<div class="form-group">
				<label for="firstName" class="form-label">First Name</label> 
				<input type="text" id="firstName" name = "firstName" class="form-control" value="<c:out value='${ user.first_name }' />">
			</div>
				
			<div class="form-group">
				<label for="lastName" class="form-label">Last Name</label> 
				<input type="text" id="lastName" name = "lastName" class="form-control" value="<c:out value='${ user.last_name }' />">
			</div>
		</c:if>
			  
	  	<button type="submit" 
	  		  class="btn btn-primary"
	  		  style="margin:10px 0px">Submit</button>
	
	</form>

</div>


<%@ include file="footer.jsp" %>