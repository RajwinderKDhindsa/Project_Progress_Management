package webprojectprogressmanagement.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import webprojectprogressmanagement.models.User;

public interface IUserService {
	User findById(int id);
	List<User> findAllUsers();
	User getManagerDetails() throws ClassNotFoundException, IllegalAccessException, SQLException, IOException;
	List<User> getTeamLeadDetails() throws ClassNotFoundException, IllegalAccessException, SQLException, IOException;
	void addTeamLead(String teamLeadName, String emailAddress); 
}
