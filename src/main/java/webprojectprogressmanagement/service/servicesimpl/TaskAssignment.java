package webprojectprogressmanagement.service.servicesimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import webprojectprogressmanagement.manager.managerImp.TeamInfoManager;
import webprojectprogressmanagement.service.ITaskAssignment;

@Service

public class TaskAssignment implements ITaskAssignment {

	@Autowired
	TeamInfoManager teamInfoManager;

	@Override
	public void assignTaskToTeamLead(int projectId, int leadDetails, String memberName) {
		teamInfoManager.assignTaskToTeamLead(projectId, leadDetails, memberName);

	}

	@Override
	public void assignTask(int projectId, int leadDetails, String memberName) {
		// TODO Auto-generated method stub

	}

}
