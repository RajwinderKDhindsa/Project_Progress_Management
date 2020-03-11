<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
<body>
	<div align="center">
<h1 align="center"><font color="black">Team member details</font></h1>
		<table border="1" cellpadding="30%" align="center" bordercolor="blue">
		
			<tr>
				<td colspan="2"><font color="black"> Lead </font></td>
				<td colspan="2"><font color="black"> Manager </font></td>

			</tr>

			<tr>
				<td colspan="2"><c:out value="${leader}" /></td>
				<td colspan="2"><c:out value="${manager}" /></td>
			</tr>

		</table>

<h1 align="center"><font color="black"> Task List </font></h1>
		<table border="1" cellpadding="30%" align="center" bordercolor="blue">
			<tr>
				<th colspan="2"><font color="black"> Task ID </font></th>
				<th colspan="2"><font color="black"> Task Name </font></th>
				<th colspan="2"><font color="black"> Task Description </font></th>
				<th colspan="2"><font color="black"> Task Status </font></th>
				<th colspan="2"><font color="black"> Task Deadline </font></th>
			</tr>
			<c:forEach var="task" items="${taskList}">
				<tr>
					<td colspan="2"><c:out value="${task.taskId}" /></td>
					<td colspan="2"><c:out value="${task.taskName}" /></td>
					<td colspan="2"><c:out value="${task.taskDesc}" /></td>
					<td colspan="2"><c:out value="${task.status}" /></td>
					<td colspan="2"><c:out value="${task.deadline}" /></td>
					<c:if test="${task.status eq 'Pending'}">
						<td colspan="2">
							<form method="post" action="acceptTask">
								Yes: <input type="radio" name="taskDecision" value="Accepted">
								NO: <input type="radio" name="taskDecision" value="Rejected">
								<input type="hidden" name="userDetails"
									value="${userDetails.id},${userDetails.name},${userDetails.userName}">
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
