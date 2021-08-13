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
				<th nowrap>Checked Out</th>
				<th nowrap>Due Date</th>
				<th nowrap>Return Date</th>
				<%-- Removed header for the action, want to combine it with the return date column --%>
				
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
					
					<td nowrap>
						<c:out value="${ book.checkedout }" />
					</td>
					
					<td nowrap>
						<c:out value="${ book.due_date }" />
					</td>
					
					<td nowrap>
						<%-- display return date if returned --%>
						<c:if test="${ book.returned != null }">
							<c:out value="${ book.returned }" />
						</c:if>
						
						<%-- display button to return if not returned yet --%>
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