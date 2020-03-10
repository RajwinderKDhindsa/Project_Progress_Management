<!DOCTYPE html>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet"
	href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
	$(function() {
		$("#datepicker").datepicker();
	});
</script>

</head>
<body>
	<div align="center">
		<h1>Team Lead</h1>
		<h2>Hello ${message}</h2>
		<form method="post" action="assignTaskToTeamMember">
			<table border="0">
				<tr>
					<td colspan="2" align="center"><h2>Assign the Task to
							Team Member</h2></td>
				</tr>
				<tr>
					<td>Project:</td>
					<td><select id="projectName" name="projectName">
							<option value="">Select Project</option>
							<c:forEach items="${acceptedProjectList}" var="project">
								<option value="${project.projectId}">${project.projectName}</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td>Team Member List:</td>
					<td><select id="lead" name="TeamLeadDetails">
							<option value="">Select Team Lead</option>
							<c:forEach items="${memberList}" var="member">
								<option value="${member.id},${member.roleId},${member.name}">${member.name}</option>
							</c:forEach>
					</select></td>
					<td>
				</tr>
				<tr>
					<td>Task Name:</td>
					<td><input type="text" name="taskName"></td>
				</tr>
				<tr>
					<td>Task Description:</td>
					<td><input type="text" name="taskDesc"></td>
				</tr>
				<tr>
					<td>DeadLine</td>
					<td><input type="text" name="deadlineDate" id="datepicker"></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="hidden"
						name="status" value="Pending"> <input type="submit"
						value="Assign" /></td>
				</tr>
			</table>
		</form>
		<form method="post" action="addNewTeamMember">
			<table border="0">
				<tr>
					<td colspan="2" align="center"><h2>Add New Team Member</h2></td>
				</tr>
				<tr>
					<td>Team Member Name:</td>
					<td><input type="text" name="name"></td>
				</tr>
				<tr>
					<td>Email Address:</td>
					<td><input type="email" name="email"></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="hidden"
						name="roleID" value="3"> <input type="submit"
						value="Add New Team Lead" /></td>
				</tr>
			</table>
		</form>
		<table>
			<tr bgcolor="blue">
				<td colspan="2"><font color="white"> Project List </font></td>
			</tr>
			<tr bgcolor="blue">
				<td colspan="2"><font color="white"> Project ID </font></td>
				<td colspan="2"><font color="white"> Project Name </font></td>
				<td colspan="2"><font color="white"> Project Description
				</font></td>
				<td colspan="2"><font color="white"> Project Status </font></td>
			</tr>
			<c:forEach var="project" items="${projectList}">
				<tr bgcolor="blue">
					<td colspan="2"><c:out value="${project.projectId}" /></td>
					<td colspan="2"><c:out value="${project.projectName}" /></td>
					<td colspan="2"><c:out value="${project.projectDesc}" /></td>
					<td colspan="2"><c:out value="${project.projectStatus}" /></td>
					<c:if test="${task.status eq 'Pending'}">
						<td colspan="2">
							<form method="post" action="acceptProject">
								Yes: <input type="radio" name="taskDecision" value="Accepted">
								NO: <input type="radio" name="taskDecision" value="Rejected">
								<input type="hidden" name="userDetails"
									value="${userDetails.id},${userDetails.name},${userDetails.userName},${project.projectId}">
								<input type="submit" value="Accept/Reject" />
							</form>
						</td>
					</c:if>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>