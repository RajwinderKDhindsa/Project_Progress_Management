package webprojectprogressmanagement.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import webprojectprogressmanagement.models.Projects;

public interface IProjectService {

	List<Projects> getProjectList() throws ClassNotFoundException, IllegalAccessException, SQLException, IOException;

	List<Projects> getProjectName() throws ClassNotFoundException, IllegalAccessException, SQLException, IOException;

	void addProject(String projectName, String projectDesc);
	
}
