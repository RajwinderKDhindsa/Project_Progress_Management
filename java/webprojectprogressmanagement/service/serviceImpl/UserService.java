package webprojectprogressmanagement.service.serviceImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import webprojectprogressmanagement.manager.managerImp.UserManager;
import webprojectprogressmanagement.models.User;
import webprojectprogressmanagement.service.IUserService;

@Service
@Transactional
public class UserService implements IUserService{
	private static final Logger log = LogManager.getLogger(UserService.class);

	@Autowired
	UserManager UserManager;
	
	@Override
	public User findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findAllUsers() {
		
		
		return UserManager.getUsers();
	}
}
