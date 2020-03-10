<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
<body>
	<div align="center">
		<form:form action="/assignProject" method="post"
			commandName="projectInfoForm">
			<table border="0">
				<tr>
					<td colspan="2" align="center"><h2>Team member details</h2></td>
				</tr>
				<tr>
			</table>
		</form:form>
		
			<table border="0">
			<tr bgcolor="blue">
				<td colspan="2"><font color="white"> Lead </font></td>
				<td colspan="2"><font color="white"> Manager </font></td>
				
			</tr>
			
				<tr bgcolor="blue">
					<td colspan="2"><c:out value="${leader}" /></td>
					<td colspan="2"><c:out value="${manager}" /></td>
				</tr>
			
		</table>
		
		<table border="0">
			<tr bgcolor="blue">
				<td colspan="2"><font color="white"> Team Member List </font></td>
			</tr>
			<tr bgcolor="grey">
				<td colspan="2"><font color="white"> Team ID </font></td>
				<td colspan="2"><font color="white"> Team Project ID </font></td>
				<td colspan="2"><font color="white"> Team Member ID
				</font></td>
				<td colspan="2"><font color="white"> Team Member Name </font></td>
			</tr>
			<c:forEach var="project" items="${teamMembers}">
				<tr bgcolor="grey">
					<td colspan="2"><c:out value="${project.teamId}" /></td>
					<td colspan="2"><c:out value="${project.projectId}" /></td>
					<td colspan="2"><c:out value="${project.memberRoleId}" /></td>
					<td colspan="2"><c:out value="${project.memberName}" /></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>

</html>
