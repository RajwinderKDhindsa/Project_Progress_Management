package webprojectprogressmanagement.manager;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import webprojectprogressmanagement.models.User;

public interface IUserManager {
	
	public User getManagerOnly();
	public List < User > getUsers();

}
