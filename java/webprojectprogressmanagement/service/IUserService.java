package webprojectprogressmanagement.service;

import java.util.List;

import webprojectprogressmanagement.models.User;

public interface IUserService {
	User findById(int id);
	List<User> findAllUsers(); 
}
