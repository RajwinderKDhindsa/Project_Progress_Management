package webprojectprogressmanagement.service.servicesimpl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import webprojectprogressmanagement.manager.managerImp.UserManager;
import webprojectprogressmanagement.models.User;
import webprojectprogressmanagement.service.IUserService;

@Service

public class UserService implements IUserService {
	private static final Logger log = LogManager.getLogger(UserService.class);

	@Autowired
	UserManager userManager;

	@Override
	public User findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findAllUsers() {

		return userManager.getUsers();
	}

	@Override
	public User getManagerDetails() throws ClassNotFoundException, IllegalAccessException, SQLException, IOException {

		return userManager.getManagerOnly();
	}

	@Override
	public List<User> getTeamLeadDetails()
			throws ClassNotFoundException, IllegalAccessException, SQLException, IOException {

		return userManager.getTeamLeadList();
	}

	@Override
	public void addTeamLead(String teamLeadName, String emailAddress) {
		userManager.addNewTeamLead(teamLeadName, emailAddress);
		
	}

}
