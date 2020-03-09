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
<%-- <body>
	<div>
		<div>
			<h1>Manager</h1>
			<h2>Hello ${message}${managerDetails.name}</h2>
			<form action="/createNewProject" method="post">
				Project Name :<br> <input type="text" name="newProject"> <br>
				<br> <input type="submit" value="Create New Project">
			</form>


			<form action="/form" method="post">

				<select NAME="Lead" SIZE="5" width="20">
					<c:forEach var="project" items="${projectList}">
						<option><font color="#00CC00"> <c:out
									value="${project.projectName}" />
							</font>
						</option>
					</c:forEach>
				</select> <select NAME="Project" SIZE="5" width="20">
					<c:forEach var="lead" items="${leadList}">
						<option><font color="#00CC00"> <c:out
									value="${lead.name}" />
							</font>
						</option>
					</c:forEach>
				</select> <input type="submit" value="Submit">
			</form>
			<table border="0">
				<tr bgcolor="blue">
					<td colspan="2"><font color="white"> Project List </font></td>
				</tr>
				<tr bgcolor="blue">
					<td colspan="2"><font color="white"> Project ID </font></td>
					<td colspan="2"><font color="white"> Project Name </font></td>
					<td colspan="2"><font color="white"> Project
							Description </font></td>
					<td colspan="2"><font color="white"> Project Status </font></td>
				</tr>
				<c:forEach var="project" items="${projectList}">
					<tr bgcolor="blue">
						<td colspan="2"><c:out value="${project.projectId}" /></td>
						<td colspan="2"><c:out value="${project.projectName}" /></td>
						<td colspan="2"><c:out value="${project.projectDesc}" /></td>
						<td colspan="2"><c:out value="${project.projectStatus}" /></td>
					</tr>
				</c:forEach>
			</table>
			Click on this <strong><a href="next">link</a></strong> to visit
			another page.
		</div>
	</div>
</body> --%>
</html>
