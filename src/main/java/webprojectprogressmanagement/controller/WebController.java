package webprojectprogressmanagement.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import webprojectprogressmanagement.models.Projects;
import webprojectprogressmanagement.service.servicesimpl.ProjectService;
import webprojectprogressmanagement.service.servicesimpl.RoleInfoService;
import webprojectprogressmanagement.service.servicesimpl.TaskAssignment;
import webprojectprogressmanagement.service.servicesimpl.TeamMemberService;
import webprojectprogressmanagement.service.servicesimpl.UserService;
import webprojectprogressmanagement.models.Team;

@Controller
public class WebController {
	@Autowired
	ProjectService projectService;

	@Autowired
	UserService userService;

	@Autowired
	RoleInfoService roleinfoService;

	@Autowired
	TaskAssignment taskAssignment;
	
	@Autowired
	TeamMemberService teamMember;

	@RequestMapping("/home")
	public String home(Map<String, Object> model)
			throws ClassNotFoundException, IllegalAccessException, SQLException, IOException {
		Projects projectInfoForm = new Projects();
		model.put("projectInfoForm", projectInfoForm);
		model.put("message", "Welcome Manager!!");
		model.put("managerDetails", userService.getManagerDetails());
		model.put("leadList", userService.getTeamLeadDetails());
		model.put("projectNameList", projectService.getProjectName());
		model.put("projectList", projectService.getProjectList());
		return "Manager";
	}

	@PostMapping("/assignProject") // it only support port method
	public String assignProject(@RequestParam("TeamLeadDetails") String teamLeadDetails,
			@RequestParam("projectName") int projectId, @RequestParam("deadlineDate") Date deadLine,
			Map<String, Object> model)
			throws ParseException, ClassNotFoundException, IllegalAccessException, SQLException, IOException {

		String[] leadDetails = teamLeadDetails.split(",");
		taskAssignment.assignTask(projectId, Integer.valueOf(leadDetails[0]), leadDetails[1]);
		model.put("message",
				"Welcome Manager assign project!!            --- " + leadDetails[0] + " LeadName: " + leadDetails[1]);
		model.put("managerDetails", userService.getManagerDetails());
		model.put("leadList", userService.getTeamLeadDetails());
		model.put("projectList", projectService.getProjectList());

		return "next";
	}

	@PostMapping("/addNewTeamLead")
	public String addNewTeamLead(@RequestParam("teamLeadName") String teamLeadName,
			@RequestParam("email") String email,
			Map<String, Object> model)
			throws ParseException, ClassNotFoundException, IllegalAccessException, SQLException, IOException {

		userService.addTeamLead(teamLeadName,email);
		// taskAssignment.assignTask(projectId, Integer.valueOf(leadDetails[0]),
		// leadDetails[1]);
		model.put("message", "Welcome Manager assign project!!            --- LeadName: " + teamLeadName);
		model.put("managerDetails", userService.getManagerDetails());
		model.put("leadList", userService.getTeamLeadDetails());
		model.put("projectList", projectService.getProjectList());

		return "next";
	}

	@PostMapping("/addProject")
	public String addProject(@RequestParam("projectName") String projectName,
			@RequestParam("projectDesc") String projectDesc, Map<String, Object> model)
			throws ParseException, ClassNotFoundException, IllegalAccessException, SQLException, IOException {

		projectService.addProject(projectName, projectDesc);
		model.put("message",
				"Welcome Manager assign project!!            --- " + projectName + " projectDesc: " + projectDesc);
		model.put("managerDetails", userService.getManagerDetails());
		model.put("leadList", userService.getTeamLeadDetails());
		model.put("projectList", projectService.getProjectList());

		return "next"; 
	}
	
	@RequestMapping(value = "/")
	public String teamMember(Map<String, Object> model)
			throws ClassNotFoundException, IllegalAccessException, SQLException, IOException {
		model.put("message", "Welcome Team Member!");
		// List<Role> roles = roleinfoService.findAllRoles();
		List<Team> team = teamMember.getTeamMember();
		model.put("teamMembers", team);
		List<Team> leadManager = teamMember.getTeamLeadManager(team.get(0).getProjectId());
		for (Team leads : leadManager) {
		    if(leads.getMemberRoleId()==2) {
		    	model.put("leader",leads.getMemberName());
		    }else if(leads.getMemberRoleId()==1) {
		    	model.put("manager",leads.getMemberName());
		    }
		}

		return "TeamMember";
	}

}