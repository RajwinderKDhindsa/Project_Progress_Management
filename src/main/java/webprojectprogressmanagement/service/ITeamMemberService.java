package webprojectprogressmanagement.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import webprojectprogressmanagement.models.Team;
public interface ITeamMemberService {
	List<Team> getTeamLeadManager(Integer teamId) throws IllegalAccessException, ClassNotFoundException, SQLException, IOException;
	List<Team> getTeamMember(int roleId)
			throws IllegalAccessException, ClassNotFoundException, SQLException, IOException; 
}
