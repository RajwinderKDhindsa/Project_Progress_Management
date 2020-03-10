package webprojectprogressmanagement.service.servicesimpl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import webprojectprogressmanagement.manager.managerImp.TeamInfoManager;
import webprojectprogressmanagement.models.Team;
import webprojectprogressmanagement.service.ITeamMemberService;

@Service
public class TeamMemberService implements ITeamMemberService {

	@Autowired
	TeamInfoManager teamInfoManager;
	
	@Override
	public List<Team> getTeamMember(int roleId) throws IllegalAccessException, ClassNotFoundException, SQLException, IOException {
		List<Team> team = teamInfoManager.getAllTeamMembers(roleId);
		return team;
	}

	@Override
	public List<Team> getTeamLeadManager(Integer Id)
			throws IllegalAccessException, ClassNotFoundException, SQLException, IOException {
		List<Team> user = teamInfoManager.getTeamLeadManager(Id);
		return user;
	}
	

}
