package webprojectprogressmanagement.service.servicesimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import webprojectprogressmanagement.manager.managerImp.RoleInfoManager;
import webprojectprogressmanagement.models.Role;
import webprojectprogressmanagement.service.IRoleInfoService;

@Service
@Transactional
public class RoleInfoService implements IRoleInfoService{
	private static final Logger log = LogManager.getLogger(RoleInfoService.class);

	@Autowired
	RoleInfoManager roleInfoManager;
	
	public Role findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Role> findAllRoles() {
		
		return roleInfoManager.getRoles();
	}
	
}
