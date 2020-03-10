package webprojectprogressmanagement.service.servicesimpl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import webprojectprogressmanagement.manager.managerImp.ProjectInfoManager;
import webprojectprogressmanagement.models.Projects;
import webprojectprogressmanagement.service.IProjectService;

@Service

public class ProjectService implements IProjectService{


	
	@Autowired
	ProjectInfoManager projectInfoManager;
	
	
	@Override
	public List<Projects> getProjectList()
			throws ClassNotFoundException, IllegalAccessException, SQLException, IOException {
		return projectInfoManager.getProjectList();
	}

	@Override
	public List<Projects> getAcceptedProjectList()
			throws ClassNotFoundException, IllegalAccessException, SQLException, IOException {
		return projectInfoManager.getAcceptedProjectList();
	}
	@Override
	public List<Projects> getProjectList(int roleId)
			throws ClassNotFoundException, IllegalAccessException, SQLException, IOException {
		return projectInfoManager.getProjectList(roleId);
	}

	@Override
	public List<Projects> getProjectName()
			throws ClassNotFoundException, IllegalAccessException, SQLException, IOException {
		return projectInfoManager.getProjectList();
	}


	@Override
	public void addProject( String projectName, String projectDesc) {
		projectInfoManager.addProject(projectName, projectDesc);
	}
	

	
}
