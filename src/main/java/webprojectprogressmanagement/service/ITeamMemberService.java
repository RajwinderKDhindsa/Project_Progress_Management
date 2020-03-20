package webprojectprogressmanagement.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import webprojectprogressmanagement.models.Projects;
import webprojectprogressmanagement.models.Team;
public interface ITeamMemberService {
	List<Team> getTeamMember(int roleId)
			throws IllegalAccessException, ClassNotFoundException, SQLException, IOException;
	List<Projects> getProjectList(int id) throws ClassNotFoundException, IllegalAccessException, SQLException, IOException;
	List<Team> getTeamLeadManager(int Id)
			throws IllegalAccessException, ClassNotFoundException, SQLException, IOException;
	Object getAcceptedProjectList(int userId)
			throws ClassNotFoundException, IllegalAccessException, SQLException, IOException;
	
}
