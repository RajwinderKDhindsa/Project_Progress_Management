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

		<h1>Manager</h1>
		<h2>Hello ${message}</h2>
		<form method="post" action="assignProject">
			<table border="0">
				<tr>
					<td colspan="2" align="center"><h2>Assign the Project to
							Team Lead</h2></td>
				</tr>
				<tr>
					<td>Project:</td>
					<td><select id="projectName" name="projectName" class="txtBox">
							<option value="">Select Project</option>
							<c:forEach items="${projectList}" var="project" >
								<option  value="${project.projectId}">${project.projectName}</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td>Lead List:</td>
					<td><select id="lead" name="TeamLeadDetails" class="txtBox">
							<option value="">Select Team Lead</option>
							<c:forEach items="${leadList}" var="lead">
								<option value="${lead.id},${lead.roleId},${lead.name}">${lead.name}</option>
							</c:forEach>
					</select></td>
					<td>
				</tr>
				<tr>
					<td>DeadLine</td>
					<td><input type="text" name="deadlineDate" id="datepicker" class="txtBox"></td>
				</tr>

				<tr>
					<td colspan="2" align="center"><input type="submit"
						value="Assign" class="btnAssign"/></td>
				</tr>
			</table>
		</form>
		<h2 align="center"><font color="black">Add New Project</font></h2>
		<form method="post" action="addProject">
			<table border="1" cellpadding="30%" align="center" bordercolor="blue">
				<tr>
					<td>Project Name:</td>
					<td><input type="text" name="projectName"></td>
				</tr>
				<tr>
					<td>Project Description</td>
					<td><input type="textarea" name="projectDesc"></td>
				</tr>

				<tr>
					<td colspan="2" align="center"><input type="submit" class="txtBox"
						value="Add New Project" /></td>
				</tr>
			</table>
		</form>
		<h2 align="center"><font color="black">Add New Team Lead</font></h2>
		<form method="post" action="addNewTeamLead">
			<table border="1" cellpadding="30%" align="center" bordercolor="blue">
				<tr>
					<td>Team Lead Name:</td>
					<td><input type="text" name="teamLeadName"></td>
				</tr>
				<tr>
					<td>Email Address:</td>
					<td><input type="email" name="email"></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input type="submit"
						value="Add New Team Lead" class="txtBox"/></td>
				</tr>
			</table>
		</form>
		<h2 align="center"><font color="black"> Project List </font></h2>
		<table border="1" cellpadding="30%" align="center" bordercolor="blue">
			<tr>
				<td colspan="2"><font color="black"> Project ID </font></td>
				<td colspan="2"><font color="black"> Project Name </font></td>
				<td colspan="2"><font color="black"> Project Description
				</font></td>
				<td colspan="2"><font color="black"> Project Status </font></td>
			</tr>
			<c:forEach var="project" items="${projectList}">
				<tr>
					<td colspan="2"><c:out value="${project.projectId}" /></td>
					<td colspan="2"><c:out value="${project.projectName}" /></td>
					<td colspan="2"><c:out value="${project.projectDesc}" /></td>
					<td colspan="2"><c:out value="${project.projectStatus}" /></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>
<style type="text/css">
.txtBox{
width: 80%;
    height: 28px;
    border-color:blue;
    margin-top:2px;
    margin-bottom:2px;
}
.btnAssign{
width: 35%;
    height: 28px;
    border-color:blue;
    margin-top:2px;
    margin-bottom:2px;
}
</style>
