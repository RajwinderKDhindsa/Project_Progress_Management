<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
<body>
	<div>
		<div>
			<h1>Manager</h1>
			<h2>Hello ${message}</h2>
			<c:forEach var="lang" items="${roles}">
				<font color="#00CC00"><c:out value="${lang.roleId}" /></font>
				<font color="#00CC00"><c:out value="${lang.roleName}" /></font>
			</c:forEach>
			

			Click on this <strong><a href="next">link</a></strong> to visit
			another page.
		</div>
	</div>
</body>
</html>
