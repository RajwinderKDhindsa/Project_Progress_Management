<!DOCTYPE html>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html lang="en">
<head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
        <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
        <script>
            $(function () {
                $("#datepicker").datepicker();
            });
        </script>

    </head>
<body>
	<div align="center">
		<%-- <div id="addEmployee">
			<form method="post" action="saveDetails">
				// saveDetails url mapping in EmployeeController Enter Employee Name
				: <input type="text" name="employeeName" /> Enter Employee Email
				Address: <input type="email" name="employeeEmail"> <input
					type="submit" value="Submit">
			</form>
		</div> --%>
		<h1>Manager</h1>
		<h2>Hello ${message}${managerDetails.name}</h2>
		<form method="post" action="assignProject">
			<table border="0">
				<tr>
					<td colspan="2" align="center"><h2>Assign the Project to
							Team Lead</h2></td>
				</tr>
				<tr>
					<td>Project:</td>
					<td><select id="projectName" name="projectName">
							<option value="">Select Project</option>
							<c:forEach items="${projectList}" var="project">
								<option value="${project.projectId}">${project.projectName}</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td>Lead List:</td>
					<td><select id="lead" name="TeamLeadDetails">
							<option value="">Select Team Lead</option>
							<c:forEach items="${leadList}" var="lead">
								<option value="${lead.roleId},${lead.name}">${lead.name}</option>
							</c:forEach>
					</select></td>
					<td>
				</tr>
				<tr>
					<td>DeadLine</td>
					<td><input type="text" name="selDate" id="datepicker"></td>
				</tr>
							
				<tr>
					<td colspan="2" align="center"><input type="submit"
						value="Assign" /></td>
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
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>
