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
						<c:if test="${ book.rented }">
							<a href="return?isbn=<c:out value='${ book.isbn }' />">
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