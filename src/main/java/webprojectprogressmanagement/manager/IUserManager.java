package webprojectprogressmanagement.manager;

import java.util.List;

import webprojectprogressmanagement.models.User;

public interface IUserManager {
	
	public User getManagerOnly();
	public List < User > getUsers();
	void addNewTeamLead(String teamLeadName, String email);
	User getUserDetails(String email);
	void addNewTeamMember(String name, String email, int roleId);
	List<User> getTeamDetails(int roleID);

}
