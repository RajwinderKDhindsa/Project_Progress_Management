package webprojectprogressmanagement.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import webprojectprogressmanagement.models.SubTasks;
import webprojectprogressmanagement.models.Team;

public interface ITaskAssignment {

	void assignTaskToTeamMember(int projectId, String taskName, String taskDesc, int userId, Integer roleId,
			String memberName, Date deadLine);

	void assignTaskToTeamLead(int projectId, int userId, Integer roleId, String memberName, Date deadLine);

	boolean updateStatus(String decision, int userId);

	boolean updateTaskStatus(String decision, Integer userId);

	List<Team> getAllTeamMemberDetails(int userId)
			throws ClassNotFoundException, IllegalAccessException, SQLException, IOException;

	List<SubTasks> getTaskList(int userId)
			throws ClassNotFoundException, IllegalAccessException, SQLException, IOException;

	
	boolean updateProjectStatus(String decision, int userId, int projectId);
}
