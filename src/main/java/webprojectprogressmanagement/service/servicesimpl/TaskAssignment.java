package webprojectprogressmanagement.service.servicesimpl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import webprojectprogressmanagement.manager.managerImp.ProjectInfoManager;
import webprojectprogressmanagement.manager.managerImp.SubTaskManager;
import webprojectprogressmanagement.manager.managerImp.TeamInfoManager;
import webprojectprogressmanagement.models.SubTasks;
import webprojectprogressmanagement.models.Team;
import webprojectprogressmanagement.service.ITaskAssignment;

@Service
public class TaskAssignment implements ITaskAssignment {

	@Autowired
	TeamInfoManager teamInfoManager;

	@Autowired
	SubTaskManager subTaskManager;

	@Autowired
	ProjectInfoManager projectInfoManager;

	@Override
	public void assignTaskToTeamLead(int projectId, int userId, Integer roleId, String memberName, Date deadLine) {
		teamInfoManager.assignTaskToTeamLead(projectId, userId, roleId, memberName, deadLine);

	}

	@Override
	public List<Team> getAllTeamMemberDetails(int roleId)
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
	public boolean updateProjectStatus(String decision, int userId, int projectId) {
		updateStatus(decision, userId);
		return projectInfoManager.updateProjectStatus(decision, projectId);
	}

	@Override
	public boolean updateTaskStatus(String decision, Integer userId) {
		updateStatus(decision, userId);
		return subTaskManager.updateTaskStatus(decision, userId);
	}

	@Override
	public List<SubTasks> getTaskList(int userId)
			throws ClassNotFoundException, IllegalAccessException, SQLException, IOException {
		return subTaskManager.getTaskDetails(userId);
	}

}
