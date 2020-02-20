package webprojectprogressmanagement.service.servicesimpl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import webprojectprogressmanagement.manager.managerImp.ProjectInfoManager;
import webprojectprogressmanagement.manager.managerImp.TeamInfoManager;
import webprojectprogressmanagement.manager.managerImp.UserManager;
import webprojectprogressmanagement.models.Projects;
import webprojectprogressmanagement.models.User;
import webprojectprogressmanagement.service.ITaskAssignment;

@Service
public class TaskAssignment implements ITaskAssignment{

	@Autowired
	TeamInfoManager teamInfoManager;
	
	@Autowired
	UserManager userManager;
	
	@Autowired
	ProjectInfoManager projectInfoManager;
	
	@Override
	public boolean assignNewTask() {
		
		//teamInfoManager.
		
		
		
		return false;
	}

	@Override
	public List<Projects> getProjectList()
			throws ClassNotFoundException, IllegalAccessException, SQLException, IOException {
		return projectInfoManager.getProjectList();
	}

	@Override
	public List<Projects> getProjectName()
			throws ClassNotFoundException, IllegalAccessException, SQLException, IOException {
		return projectInfoManager.getProjectList();
	}

	@Override
	public User getManagerDetails() throws ClassNotFoundException, IllegalAccessException, SQLException, IOException {
	
		return userManager.getManagerOnly();
	}

	@Override
	public List<User> getTeamLeadDetails() throws ClassNotFoundException, IllegalAccessException, SQLException, IOException {
	
		return userManager.getTeamLeadList();
	}

}
