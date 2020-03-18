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

import webprojectprogressmanagement.models.Team;
import webprojectprogressmanagement.models.User;
import webprojectprogressmanagement.service.servicesimpl.EmailService;
import webprojectprogressmanagement.service.servicesimpl.ProjectService;
import webprojectprogressmanagement.service.servicesimpl.RoleInfoService;
import webprojectprogressmanagement.service.servicesimpl.TaskAssignment;
import webprojectprogressmanagement.service.servicesimpl.TeamMemberService;
import webprojectprogressmanagement.service.servicesimpl.UserService;

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

	@Autowired
	EmailService emailService;

	@RequestMapping("/")
	public String login(Map<String, Object> model)
			throws ClassNotFoundException, IllegalAccessException, SQLException, IOException {
		return "Login";
	}

	@PostMapping("/login")
	public String login(@RequestParam("email") String email, @RequestParam("password") String password,
			Map<String, Object> model)

			throws ParseException, ClassNotFoundException, IllegalAccessException, SQLException, IOException {

		if (userService.checkUser(email, password) == 1) {
			User user = userService.userDetails(email);
			model.put("message", "Welcome " + user.getName() + "!!");
			model.put("userDetails", user);
			model.put("managerDetails", userService.getManagerDetails());
			model.put("leadList", userService.getTeamDetails(2));
			//TODO: NO need of this code check dependencies 
			model.put("projectNameList", projectService.getProjectName());
			//TODO: add assignedTo field and update it when project is assigned 
			model.put("projectList", projectService.getProjectList());
			return "Manager";
		} else if (userService.checkUser(email, password) == 2) {
			User user = userService.userDetails(email);
			model.put("message", "Welcome " + user.getName() + " Team Manager!!");
			model.put("userDetails", user);
			model.put("managerDetails", userService.getManagerDetails());
			model.put("memberList", userService.getTeamDetails(3));
			model.put("projectList", teamMember.getProjectList(user.getId()));
			model.put("acceptedProjectList", teamMember.getAcceptedProjectList(user.getId()));
			return "TeamLead";
		} else if (userService.checkUser(email, password) == 3) {
			User user = userService.userDetails(email);
			model.put("userDetails", user);
			model.put("taskList", taskAssignment.getTaskList(user.getId()));
			model.put("message", "Welcome Team Member!");
			// List<Role> roles = roleinfoService.findAllRoles();
			List<Team> team = teamMember.getTeamMember(3);
			model.put("teamMembers", team);
			List<Team> leadManager = teamMember.getTeamLeadManager(team.get(0).getProjectId());
			for (Team leads : leadManager) {
				if (leads.getMemberRoleId() == 2) {
					model.put("leader", leads.getMemberName());
				}
				if (leads.getMemberRoleId() == 1) {
					model.put("manager", leads.getMemberName());
				}
			}
			return "TeamMember";
		}
		model.put("errorExit", true);
		model.put("error", "Enter Valid UserName and Password!!");
		return "Login";
	}

	@PostMapping("/assignProject")
	public String assignProject(@RequestParam("TeamLeadDetails") String teamLeadDetails,
			@RequestParam("projectName") int projectId, @RequestParam("deadlineDate") Date deadLine,
			Map<String, Object> model)
			throws ParseException, ClassNotFoundException, IllegalAccessException, SQLException, IOException {

		String[] leadDetails = teamLeadDetails.split(",");
		//TODO: add assignedTo field and update it when project is assigned 
		taskAssignment.assignTaskToTeamLead(projectId, Integer.valueOf(leadDetails[0]), Integer.valueOf(leadDetails[1]),
				leadDetails[2], deadLine);
		model.put("message",
				"Welcome Manager assign project!!            --- " + leadDetails[0] + " LeadName: " + leadDetails[1]);
		model.put("managerDetails", userService.getManagerDetails());
		model.put("leadList", userService.getTeamDetails(2));
		model.put("projectList", projectService.getProjectList());
		return "next";
	}

	@PostMapping("/addNewTeamLead")
	public String addNewTeamLead(@RequestParam("teamLeadName") String teamLeadName, @RequestParam("email") String email,
			Map<String, Object> model)
			throws ParseException, ClassNotFoundException, IllegalAccessException, SQLException, IOException {

		userService.addTeamLead(teamLeadName, email);
		model.put("message", "Welcome Manager assign project!!            --- LeadName: " + teamLeadName);
		model.put("managerDetails", userService.getManagerDetails());
		model.put("leadList", userService.getTeamDetails(2));
		model.put("projectList", projectService.getProjectList());
		return "next";
	}

	@PostMapping("/addProject")
	public String addProject(@RequestParam("projectName") String projectName,
			@RequestParam("projectDesc") String projectDesc, Map<String, Object> model)
			throws ParseException, ClassNotFoundException, IllegalAccessException, SQLException, IOException {
		//TODO: add assignedTo field and update it when project is assigned 
		projectService.addProject(projectName, projectDesc);
		model.put("message",
				"Welcome Manager assign project!!            --- " + projectName + " projectDesc: " + projectDesc);
		model.put("managerDetails", userService.getManagerDetails());
		model.put("leadList", userService.getTeamDetails(2));
		model.put("projectList", projectService.getProjectList());
		return "next";
	}

	@PostMapping("/assignTaskToTeamMember") // it only support port method
	public String assignTaskToTeamMember(@RequestParam("TeamLeadDetails") String teamLeadDetails,
			@RequestParam("projectName") int projectId, @RequestParam("taskName") String taskName,
			@RequestParam("taskDesc") String taskDesc, @RequestParam("deadlineDate") Date deadLine,
			Map<String, Object> model)
			throws ParseException, ClassNotFoundException, IllegalAccessException, SQLException, IOException {

		String[] leadDetails = teamLeadDetails.split(",");
		//TODO: add assignedTo field and update it when project is assigned 
		taskAssignment.assignTaskToTeamMember(projectId, taskName, taskDesc, Integer.valueOf(leadDetails[0]),
				Integer.valueOf(leadDetails[1]), leadDetails[2], deadLine);
		model.put("message",
				"Welcome Manager assign project!!            --- " + leadDetails[0] + " LeadName: " + leadDetails[1]);
		model.put("managerDetails", userService.getManagerDetails());
		model.put("leadList", userService.getTeamDetails(2));
		model.put("projectList", projectService.getProjectList());
		return "next";
	}

	@PostMapping("/addNewTeamMember")
	public String addNewTeamMember(@RequestParam("name") String name, @RequestParam("email") String email,
			@RequestParam("roleID") int roleId, Map<String, Object> model)
			throws ParseException, ClassNotFoundException, IllegalAccessException, SQLException, IOException {

		userService.addNewTeamMember(name, email, roleId);
		model.put("message", "Welcome Manager assign project!!            --- LeadName: " + name);
		model.put("managerDetails", userService.getManagerDetails());
		model.put("leadList", userService.getTeamDetails(2));
		model.put("projectList", projectService.getProjectList());
		return "next";
	}

	@PostMapping("/acceptProject")
	public String acceptProject(@RequestParam("taskDecision") String decision,
			@RequestParam("userDetails") String userDetails, Map<String, Object> model)
			throws ParseException, ClassNotFoundException, IllegalAccessException, SQLException, IOException {
		String[] user = userDetails.split(",");
		if (taskAssignment.updateProjectStatus(decision, Integer.valueOf(user[0]), Integer.valueOf(user[4]))) {
			emailService.sendStatusNotification(user[2], user[1], decision);
		}
		model.put("message", "Welcome Manager assign project!!            --- " + decision);
		model.put("managerDetails", userService.getManagerDetails());
		model.put("leadList", userService.getTeamDetails(2));
		model.put("projectList", projectService.getProjectList());
		return "next";
	}

	@PostMapping("/acceptTask")
	public String acceptTask(@RequestParam("taskDecision") String decision,
			@RequestParam("userDetails") String userDetails, Map<String, Object> model)
			throws ParseException, ClassNotFoundException, IllegalAccessException, SQLException, IOException {
		String[] user = userDetails.split(",");

		if (taskAssignment.updateTaskStatus(decision, Integer.valueOf(user[0]))) {
			emailService.sendStatusNotification(user[2], user[1], decision);
		}
		model.put("message", "Welcome Manager assign project!!            --- " + decision);
		model.put("managerDetails", userService.getManagerDetails());
		model.put("leadList", userService.getTeamDetails(2));
		model.put("projectList", projectService.getProjectList());
		return "next";
	}

}
