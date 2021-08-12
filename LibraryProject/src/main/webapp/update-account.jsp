
<%@ include file="header.jsp" %>

<div class="container">

	<h1>Update Account</h1>	
	
	<c:if test="${ !isLibrarian }">
		<form action="updateAccount" method="post" >
		
			<input type="hidden" name="id" value="<c:out value='${ account.id }'/>">
					
			<div class="form-group">
				<label for="firstName" class="form-label">First Name</label> 
				<input type="text" id="firstName" name = "firstName" class="form-control" value="<c:out value='${ account.firstName }' />" required>
			</div>
			
			<div class="form-group">
				<label for="lastName" class="form-label">Last Name</label> 
				<input type="text" id="lastName" name = "lastName" class="form-control" value="<c:out value='${ account.lastName }' />" required>
			</div>
				
			<div class="form-group">
				<label for="username" class="form-label">Username</label> 
				<input type="text" id="username" name = "username" class="form-control" value="<c:out value='${ account.username }' />" required>
			</div>
				
			<div class="form-group">
				<label for="pw" class="form-label">Password</label> 
				<input type="password" id="pw" name = "pw" class="form-control" value="<c:out value='${ account.password }' />" required>
			</div>
				  
		  	<button type="submit" 
		  		  class="btn btn-primary"
		  		  style="margin:10px 0px">Submit</button>
		  
		</form>
		
	</c:if>

	<c:if test="${ isLibrarian }">
		<form action="updateAccount" method="post" >
		
			<input type="hidden" name="id" value="<c:out value='${ account.id }'/>">
									
			<div class="form-group">
				<label for="username" class="form-label">Username</label> 
				<input type="text" id="username" name = "username" class="form-control" value="<c:out value='${ account.username }' />" required>
			</div>
				
			<div class="form-group">
				<label for="pw" class="form-label">Password</label> 
				<input type="password" id="pw" name = "pw" class="form-control" value="<c:out value='${ account.password }' />" required>
			</div>
				  
		  	<button type="submit" 
		  		  class="btn btn-primary"
		  		  style="margin:10px 0px">Submit</button>
		  
		</form>
		
	</c:if>

</div>


<%@ include file="footer.jsp" %>