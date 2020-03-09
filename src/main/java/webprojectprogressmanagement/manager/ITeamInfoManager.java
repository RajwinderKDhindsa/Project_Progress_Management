package webprojectprogressmanagement.manager;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import webprojectprogressmanagement.models.Team;

public interface ITeamInfoManager {
	public boolean deleteTeamMember(Team team);
	public boolean updateTeamMember(Team team, int teamId);
	public List<Team> getAllTeamMembers() throws ClassNotFoundException, IllegalAccessException, SQLException, IOException ;
	public Team getManager() throws ClassNotFoundException, IllegalAccessException, SQLException, IOException ;
	public List<Team> getTeamLeadList()
			throws ClassNotFoundException, IllegalAccessException, SQLException, IOException;
	void assignTaskToTeamLead(int projectId, int leadDetails, String memberName);
	public List<Team> getTeamLeadManager(Integer id);
	
	
}
