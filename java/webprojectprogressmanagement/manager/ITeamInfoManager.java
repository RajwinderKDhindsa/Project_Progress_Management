package webprojectprogressmanagement.manager;

import java.io.IOException;
import java.sql.SQLException;

import webprojectprogressmanagement.models.Team;

public interface ITeamInfoManager {
	public Team addNewTeamMember(Team team)throws SQLException, IllegalAccessException, IOException, ClassNotFoundException;
	public void allTeamMembers() throws SQLException, IllegalAccessException, IOException, ClassNotFoundException;
	public boolean deleteTeamMember(Team team);
	public boolean updateTeamMember(Team team, int teamId);
		
}
