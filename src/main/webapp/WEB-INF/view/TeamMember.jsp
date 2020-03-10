<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
<body>
	<div align="center">

		<table border="0">
			<tr>
				<td colspan="2" align="center"><h2>Team member details</h2></td>
			</tr>
			<tr>
		</table>


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

		<table>
			<tr bgcolor="blue">
				<td colspan="2"><font color="white"> Task List </font></td>
			</tr>
			<tr bgcolor="blue">
				<td colspan="2"><font color="white"> Task ID </font></td>
				<td colspan="2"><font color="white"> Task Name </font></td>
				<td colspan="2"><font color="white"> Task Description </font></td>
				<td colspan="2"><font color="white"> Task Status </font></td>
				<td colspan="2"><font color="white"> Task Deadline </font></td>
			</tr>
			<c:forEach var="task" items="${taskList}">
				<tr bgcolor="blue">
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
