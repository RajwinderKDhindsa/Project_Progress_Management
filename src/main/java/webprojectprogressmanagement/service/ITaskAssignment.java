package webprojectprogressmanagement.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import webprojectprogressmanagement.models.Projects;
import webprojectprogressmanagement.models.User;

public interface ITaskAssignment {
	public boolean assignNewTask();

	public User getManagerDetails() throws ClassNotFoundException, IllegalAccessException, SQLException, IOException;

	public List<User> getTeamLeadDetails()
			throws ClassNotFoundException, IllegalAccessException, SQLException, IOException;
	public List<Projects> getProjectList()
			throws ClassNotFoundException, IllegalAccessException, SQLException, IOException;

	public List<Projects> getProjectName() throws ClassNotFoundException, IllegalAccessException, SQLException, IOException;
}
