package webprojectprogressmanagement.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import webprojectprogressmanagement.models.Role;
import webprojectprogressmanagement.service.serviceImpl.*;

@Controller
public class WebController {
	@Autowired
	UserService UserService;
	
	@Autowired
	RoleInfoService roleinfoService;

	@RequestMapping("/")
	public String home(Map<String, Object> model) {
		model.put("message", "rajwinder Reader !!");
		/*List<Role> roles = roleinfoService.findAllRoles();
		model.put("roles", roles);*/
		return "Manager";
	}
	
	@RequestMapping("/next")
	public String next(Map<String, Object> model) {
		model.put("message", "You are in new page !!");
		return "next";
	}

}