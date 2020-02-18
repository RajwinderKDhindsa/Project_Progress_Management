package webprojectprogressmanagement.manager.managerImp;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import webprojectprogressmanagement.manager.ITeamInfoManager;
import webprojectprogressmanagement.models.Team;
import webprojectprogressmanagement.models.User;
import webprojectprogressmanagement.utils.DataConnectionUtility;
import webprojectprogressmanagement.utils.ProjectContants;

@Component
public class TeamInfoManager implements ITeamInfoManager {

	private static final Logger log = LogManager.getLogger(TeamInfoManager.class);

	public static TeamInfoManager teamInfoManager = null;

	private TeamInfoManager() {

	}

	public static TeamInfoManager getInstance() {
		synchronized (TeamInfoManager.class) {
			if (teamInfoManager == null) {
				teamInfoManager = new TeamInfoManager();
			}

		}
		return teamInfoManager;
	}

	public Team addNewTeamMember(Team team)
			throws SQLException, IllegalAccessException, IOException, ClassNotFoundException {
		// Get connection instance
		Connection connection = DataConnectionUtility.getInstance().getConnection();
		// Create Prepared Statement
		PreparedStatement query = connection.prepareStatement(ProjectContants.INSERT_EMPLOYEE);
		// Set variables
		query.setInt(1, team.getTeamId());
		query.setString(2, team.getMemberName());
		query.setInt(3, team.getProjectId());

		try {
			// Execute
			query.execute();
			log.debug("Query is executed!!");
			// Return employee instance
			return team;
		} catch (Exception e) {
			// Close statement
			query.close();
			// Close connection
			connection.close();
			// Throw another exception for notifying the Servlet
			throw new SQLException(e);
		}
	}

	public boolean deleteTeamMember(Team team) {
		return false;
	}

	public boolean updateTeamMember(Team team, int teamId) {
		return false;
	}

	public List<User> getAllManager() throws ClassNotFoundException, IllegalAccessException, SQLException, IOException {
		System.out.println("Start getAllEmployees.");
		List<User> emps = new ArrayList<User>();
		Connection con = DataConnectionUtility.getInstance().getConnection();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(ProjectContants.ALL_EMPLOYEES);
		while (rs.next())
			emps.add((User) rs);
		
		return emps;
	}
	
	public void allTeamMembers() throws SQLException, IllegalAccessException, IOException, ClassNotFoundException {
		System.out.println("all employees");
		Connection con = DataConnectionUtility.getInstance().getConnection();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(ProjectContants.ALL_EMPLOYEES);
		while (rs.next())
			System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3));
		con.close();
		// return employee;
	}

}
