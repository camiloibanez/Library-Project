<%@ include file="header.jsp" %>

<div class="container">

	<h1>Book History</h1>
	<br>
	<br>
	<table class="table table-striped">
	
		<thead>
			<tr>
				<th>isbn</th>
				<th>Title</th>
				<th>Description</th>
				<th>Checked Out</th>
				<th>Due Date</th>
				<th>Return Date</th>
				<th>Actions</th>
				
			</tr>
		</thead>
		
		<tbody>
		
			<c:forEach var="book" items="${ userHistory }">
				
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
						<c:out value="${ book.checkedout }" />
					</td>
					
					<td>
						<c:out value="${ book.due_date }" />
					</td>
					
					<td>
						<c:out value="${ book.returned }" />
					</td>
					
					<td>
						<c:if test="${ book.returned == null }">
							<a href="return?isbn=<c:out value='${ book.isbn }' />&checkout_id=<c:out value='${ book.checkout_id }' />">
								<button class="btn btn-primary">Return</button>
							</a>
						</c:if>
						
					</td>
					
				</tr>
			
			</c:forEach>
		
		</tbody>
	
	</table>

</div>


<%@ include file="footer.jsp" %>