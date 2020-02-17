package webprojectprogressmanagement.service;

import java.util.List;

import webprojectprogressmanagement.models.Role;

public interface IRoleInfoService {
	Role findById(int id);
	List<Role> findAllRoles(); 
}
