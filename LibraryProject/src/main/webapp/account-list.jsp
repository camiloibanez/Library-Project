<%@ include file="header.jsp" %>

<div class="container">

	<c:if test="${ isLibrarian }">
		<h1>Account List</h1>
		<br>
		<br>
		<table class="table table-striped">
		
			<thead>
				<tr>
					<th>id</th>
					<th>first name</th>
					<th>last name</th>
					<th>username</th>
					<th>password</th>
					<th>frozen</th>
					<th>Actions</th>
				</tr>
			</thead>
			
			<tbody>
			
				<c:forEach var="user" items="${ allPatrons }">
					
					<tr>
						<td>
							<c:out value="${ user.patron_id }" />
						</td>
						
						<td>
							<c:out value="${ user.first_name }" />
						</td>
						
						<td>
							<c:out value="${ user.last_name }" />
						</td>
						
						<td>
							<c:out value="${ user.username }" />
						</td>
	
						<td>
							<c:out value="${ user.password }" />
						</td>
						
						<td>
							<c:out value="${ user.account_frozen }" />
						</td>
	
						<td>
							<c:if test="${ user.account_frozen }">
								<a href="unfreeze?id=<c:out value='${ user.patron_id }' />">
									<button class="btn btn-primary">Unfreeze</button>
								</a>
							</c:if>
	
							<c:if test="${ !user.account_frozen }">
								<a href="freeze?id=<c:out value='${ user.patron_id }' />">
									<button class="btn btn-primary">Freeze</button>
								</a>
							</c:if>
							
						</td>
						
					</tr>
				
				</c:forEach>
			
			</tbody>
		
		</table>
	</c:if>
	
	<c:if test="${ !isLibrarian }">
		<h1>Sorry! You don't have access to this page.</h1>
	</c:if>

</div>


<%@ include file="footer.jsp" %>