package webprojectprogressmanagement.manager;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import webprojectprogressmanagement.models.Projects;
import webprojectprogressmanagement.models.Team;

public interface ITeamInfoManager {
	public boolean deleteTeamMember(Team team);
	public boolean updateTeamMember(Team team, int teamId);
	public Team getManager() throws ClassNotFoundException, IllegalAccessException, SQLException, IOException ;
	List<Team> getAllTeamMembers(int roleID)
			throws ClassNotFoundException, IllegalAccessException, SQLException, IOException;

	List<Team> getTeamLeadList() throws ClassNotFoundException, IllegalAccessException, SQLException, IOException;
	void assignTaskToTeamLead(int projectId, int userId, Integer roleId, String memberName, Date deadLine);
	boolean updateStatus(String decision, int userId);

	public List<Team> getTeamLeadManager(Integer id);
	
	List<Team> getTeamLeadDetails(int userId)
			throws ClassNotFoundException, IllegalAccessException, SQLException, IOException;
	List<Team> getAcceptedProjectList(int userId)
			throws ClassNotFoundException, IllegalAccessException, SQLException, IOException;
	List<Projects> getProjectList(int userId)
			throws ClassNotFoundException, IllegalAccessException, SQLException, IOException;

	
}
