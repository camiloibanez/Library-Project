<%@ include file="header.jsp" %>

<div class="container">

	<h1>Library Book List</h1>
	<br>
	<br>
	<table class="table table-striped">
	
		<c:if test="${ isLibrarian }">
		
			<thead>
				<tr>
					<th>isbn</th>
					<th>Title</th>
					<th>Description</th>
					<th>Rented</th>
					<th>Date Added</th>
					<th>Actions</th>
				</tr>
			</thead>
			
			<tbody>
			
				<c:forEach var="book" items="${ allBooks }">
					
					<tr>
						<td>
							<c:out value="${ book.isbn }" />
						</td>
						
						<td>
							<c:out value="${ book.title }" />
						</td>
						
						<td>
							<c:out value="${ book.description }" />
						</td>
						
						<td>
							<c:out value="${ book.rented }" />
						</td>
						
						<td>
							<c:out value="${ book.date_added }" />
						</td>
						
						<td>
							
							<a href="edit?id=<c:out value='${ book.isbn }' />">
								<button class="btn btn-primary">Edit</button>
							</a>
							
							<a href="delete?id=<c:out value='${ book.isbn }' />">
								<button class="btn btn-danger">Delete</button>
							</a>
							
						</td>
						
					</tr>
				
				</c:forEach>
			
			</tbody>
		
		</c:if>
		
		<c:if test="${ !isLibrarian }">
		
			<thead>
				<tr>
					<th>isbn</th>
					<th>Title</th>
					<th>Description</th>
					<th>Rented</th>
					<th>Actions</th>
				</tr>
			</thead>
			
			<tbody>
			
				<c:forEach var="book" items="${ allBooks }">
					
					<tr>
						<td>
							<c:out value="${ book.isbn }" />
						</td>
						
						<td>
							<c:out value="${ book.title }" />
						</td>
						
						<td>
							<c:out value="${ book.description }" />
						</td>
						
						<td>
							<c:out value="${ book.rented }" />
						</td>					
						
						<c:if test="${ !book.rented }">
							<td>
			
								<a href="checkout?id=<c:out value='${ book.isbn }' />">
									<button class="btn btn-primary">Checkout</button>
								</a>
				
							</td>
						</c:if>
						
						<c:if test="${ book.rented }">
							<td>
			
								<a href="checkout?id=<c:out value='${ book.isbn }' />">
									<button class="btn btn-primary" disabled>Checkout</button>
								</a>
				
							</td>
						</c:if>
						
					</tr>
				
				</c:forEach>
			
			</tbody>
			
		</c:if>
					
	</table>
	
	

</div>


<%@ include file="footer.jsp" %>