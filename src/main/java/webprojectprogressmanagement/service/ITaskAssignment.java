package webprojectprogressmanagement.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import webprojectprogressmanagement.models.Team;

public interface ITaskAssignment {

	List<Team> getTeamMemberDetails(int roleId) throws ClassNotFoundException, IllegalAccessException, SQLException, IOException;

	void assignTaskToTeamMember(int projectId, String taskName, String taskDesc, int userId, Integer roleId,
			String memberName, Date deadLine);

	void assignTaskToTeamLead(int projectId, int userId, Integer roleId, String memberName, Date deadLine);

	boolean updateStatus(String decision, int userId);

	boolean updateTaskStatus(String decision, Integer userId);
}
