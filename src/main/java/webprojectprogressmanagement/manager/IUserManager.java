package webprojectprogressmanagement.manager;

import java.util.List;

import webprojectprogressmanagement.models.User;

public interface IUserManager {
	
	public User getManagerOnly();
	public List < User > getUsers();
	void addNewTeamLead(String teamLeadName, String email);

}
