package webprojectprogressmanagement.service.servicesimpl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	public void addTeamLead(String teamLeadName, String emailAddress) {
		userManager.addNewTeamLead(teamLeadName, emailAddress);

	}

	@Override
	public int checkUser(String email, String password) {

		if (userManager.getUserDetails(email) != null
				&& password.equals(userManager.getUserDetails(email).getPassword())) {
			System.out.println("LOGIN TO SYSTEM");

			return userManager.getUserDetails(email).getRoleId();
		}
		return 0;

	}

	@Override
	public User userDetails(String email) {

		return userManager.getUserDetails(email);

	}

	
	@Override
	public void addNewTeamMember(String name, String email, int roleId) {
		userManager.addNewTeamLead(name, email,roleId);
		
	}

	@Override
	public List<User> getTeamDetails(int roleID) {
		// TODO Auto-generated method stub
		return userManager.getTeamDetails(roleID);
	}

}
