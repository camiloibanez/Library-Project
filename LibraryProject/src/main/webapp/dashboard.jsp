<%@ include file="header.jsp" %>

<div class="container">

	<c:if test='${ isLibrarian }'>
	<h3> You are a librarian</h3>
	</c:if>
	
	<c:if test="${ !isLibrarian }">
	<h3>You are a patron</h3>
	</c:if>
</div>

<%@ include file="footer.jsp" %>