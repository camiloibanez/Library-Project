<%@ include file="header.jsp" %>

<div class="Container">
	<h1 class="display-4">Login Page</h1>
	<p class="lead">Welcome! Please login below.</p>
	
	<form action="LoginServlet" method="post">
		
			<div class="form-group">
				<label for="username" class="form-label">Username</label> 
				<input type="text" id="username" name = "username" class="form-control" required>
			</div>
			
			<div class="form-group">
				<label for="pw" class="form-label">Password</label> 
				<input type="password" id="pw" name = "pw" class="form-control" required>
			</div>
			<br>
			
			<div class="form-check">
			  <input class="form-check-input" type="radio" name="isLibrarian" id="patron" value="false" required>
			  <label class="form-check-label" for="patron">
			    Patron
			  </label>
			</div>
			<div class="form-check">
			  <input class="form-check-input" type="radio" name="isLibrarian" id="librarian" value="true">
			  <label class="form-check-label" for="librarian">
			    Librarian
			  </label>
			</div>
			<br>
			
			<input type="submit" value="Login" class="btn btn-primary"> 
		</form>
</div>


<%@ include file="footer.jsp" %>
		
