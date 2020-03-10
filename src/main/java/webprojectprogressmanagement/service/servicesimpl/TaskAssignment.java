package webprojectprogressmanagement.service.servicesimpl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import webprojectprogressmanagement.manager.managerImp.SubTaskManager;
import webprojectprogressmanagement.manager.managerImp.TeamInfoManager;
import webprojectprogressmanagement.models.Team;
import webprojectprogressmanagement.service.ITaskAssignment;

@Service
public class TaskAssignment implements ITaskAssignment {

	@Autowired
	TeamInfoManager teamInfoManager;

	@Autowired
	SubTaskManager subTaskManager;

	@Override
	public void assignTaskToTeamLead(int projectId, int userId, Integer roleId, String memberName, Date deadLine) {
		teamInfoManager.assignTaskToTeamLead(projectId, userId, roleId, memberName, deadLine);

	}

	@Override
	public List<Team> getTeamMemberDetails(int roleId)
			throws ClassNotFoundException, IllegalAccessException, SQLException, IOException {
		return teamInfoManager.getAllTeamMembers(roleId);
	}

	@Override
	public void assignTaskToTeamMember(int projectId, String taskName, String taskDesc, int userId, Integer roleId,
			String memberName, Date deadLine) {
		assignTaskToTeamLead(projectId, userId, roleId, memberName, deadLine);
		subTaskManager.addNewTask(projectId, userId, roleId, taskName, taskDesc, deadLine);
	}

	@Override
	public boolean updateStatus(String decision, int userId) {
		return teamInfoManager.updateStatus(decision, userId);
	}

	@Override
	public boolean updateTaskStatus(String decision, Integer userId) {
		updateStatus(decision, userId);
		return subTaskManager.updateTaskStatus(decision, userId);
	}

}
