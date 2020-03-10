package webprojectprogressmanagement.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import webprojectprogressmanagement.models.User;

public interface IUserService {
	User findById(int id);
	List<User> findAllUsers();
	User getManagerDetails() throws ClassNotFoundException, IllegalAccessException, SQLException, IOException;
	void addTeamLead(String teamLeadName, String emailAddress);
	int checkUser(String email, String password);
	User userDetails(String email);
	void addNewTeamMember(String name, String email, int roleId);
	List<User> getTeamDetails(int roleID); 
}
