<%@ include file="header.jsp" %>

<div class="container">

	<h1>Library Book List</h1>
	<br>
	<br>
	<table class="table table-striped">
		
			<thead>
				<tr>
					<th>isbn</th>
					<th>Title</th>
					<th>Description</th>
					<th>Rented</th>
					<c:if test="${ isLibrarian }">
						<th>Date Added</th>
					</c:if>
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
							<c:out value="${ book.descr }" />
						</td>
						
						<td>
							<c:out value="${ book.rented }" />
						</td>
						
						<c:if test="${ isLibrarian }">
							<td>
								<c:out value="${ book.added_to_library }" />
							</td>
						
							<td>
								
								<a href="editbook?isbn=<c:out value='${ book.isbn }' />">
									<button class="btn btn-primary">Edit</button>
								</a>
															
							</td>
						</c:if>
						
						<c:if test="${ !isLibrarian }">
		
							<c:if test="${ !book.rented }">
								<td>
				
									<a href="checkout?isbn=<c:out value='${ book.isbn }' />">
										<button class="btn btn-primary">Checkout</button>
									</a>
					
								</td>
							</c:if>
							
							<c:if test="${ book.rented }">
								<td>
				
									<a href="checkout?isbn=<c:out value='${ book.isbn }' />">
										<button class="btn btn-primary" disabled>Checkout</button>
									</a>
					
								</td>
							</c:if>
										
						</c:if>
						
					</tr>
				
				</c:forEach>
			
			</tbody>
		
		</table>
		
</div>


<%@ include file="footer.jsp" %>