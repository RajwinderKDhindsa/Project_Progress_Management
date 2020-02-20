package webprojectprogressmanagement.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import webprojectprogressmanagement.models.Projects;
import webprojectprogressmanagement.service.servicesimpl.RoleInfoService;
import webprojectprogressmanagement.service.servicesimpl.TaskAssignment;
import webprojectprogressmanagement.service.servicesimpl.UserService;

@Controller
public class WebController {
	@Autowired
	UserService UserService;

	@Autowired
	RoleInfoService roleinfoService;

	@Autowired
	TaskAssignment taskAssignment;

	@RequestMapping("/")
	public String home(Map<String, Object> model)
			throws ClassNotFoundException, IllegalAccessException, SQLException, IOException {
		Projects projectInfoForm = new Projects();
		model.put("projectInfoForm", projectInfoForm);
		model.put("message", "Welcome Manager!!");
		// List<Role> roles = roleinfoService.findAllRoles();
		model.put("managerDetails", taskAssignment.getManagerDetails());
		model.put("leadList", taskAssignment.getTeamLeadDetails());
		model.put("projectNameList", taskAssignment.getProjectName());
		model.put("projectList", taskAssignment.getProjectList());
		return "Manager";
	}

	@RequestMapping(value = "/assignProject", method = RequestMethod.POST)
	public String processRegistration(@ModelAttribute("projectInfoForm") Projects projectInfo,
			Map<String, Object> model)
			throws ClassNotFoundException, IllegalAccessException, SQLException, IOException {

		// implement your own registration logic here...

		// for testing purpose:
		System.out.println("Status: " + projectInfo.getProjectStatus());
		System.out.println("Desc: " + projectInfo.getProjectDesc());
		model.put("message", "Welcome Manager assign project!!");
		// List<Role> roles = roleinfoService.findAllRoles();
		model.put("managerDetails", taskAssignment.getManagerDetails());
		model.put("leadList", taskAssignment.getTeamLeadDetails());
		model.put("projectList", taskAssignment.getProjectList());

		return "Manager";
	}

}