package webprojectprogressmanagement.manager;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import webprojectprogressmanagement.models.Projects;

public interface IProjectInfoManager {
	public List<Projects> getProjectList()
			throws ClassNotFoundException, IllegalAccessException, SQLException, IOException;

	List<String> getProjectNameList()
			throws ClassNotFoundException, IllegalAccessException, SQLException, IOException;

	void addProject(String projectName, String projectDesc);
}
