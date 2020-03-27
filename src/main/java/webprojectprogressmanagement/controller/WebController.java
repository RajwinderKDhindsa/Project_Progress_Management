package webprojectprogressmanagement.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mysql.cj.util.StringUtils;

import webprojectprogressmanagement.models.Team;
import webprojectprogressmanagement.models.User;
import webprojectprogressmanagement.service.servicesimpl.EmailService;
import webprojectprogressmanagement.service.servicesimpl.ProjectService;
import webprojectprogressmanagement.service.servicesimpl.RoleInfoService;
import webprojectprogressmanagement.service.servicesimpl.SessionManagementService;
import webprojectprogressmanagement.service.servicesimpl.TaskAssignment;
import webprojectprogressmanagement.service.servicesimpl.TeamMemberService;
import webprojectprogressmanagement.service.servicesimpl.UserService;

@Controller
public class WebController {

	private static final Logger log = LogManager.getLogger(WebController.class);

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

	@Autowired
	SessionManagementService sessionService;

	@RequestMapping("/")
	public String login(Map<String, Object> model)
			throws ClassNotFoundException, IllegalAccessException, SQLException, IOException {
		return "Login";
	}

	@PostMapping("/login")
	public String login(@RequestParam("email") String email, @RequestParam("password") String password,
			Map<String, Object> model, HttpServletRequest request)
			throws ParseException, ClassNotFoundException, IllegalAccessException, SQLException, IOException {
		log.debug("User try to login with userName " + email);
		if (!(StringUtils.isNullOrEmpty(email) || StringUtils.isNullOrEmpty(password)
				|| StringUtils.isEmptyOrWhitespaceOnly(email) || StringUtils.isEmptyOrWhitespaceOnly(password))) {
			log.info("User with email Id " + email + " having sessio ID " + request.getSession().getId());
			log.info("Session Creation Time : " + request.getSession().getCreationTime());

			if (userService.checkUser(email, password) == 1) {
				User user = userService.userDetails(email);
				sessionService.createSession(user.getId(), user.getName(), request.getSession().getCreationTime(),
						request.getSession().getId(), "Login");
				request.getSession().setAttribute("user", user);
				model.put("message", "Welcome " + user.getName());
				model.put("userDetails", user);
				model.put("managerDetails", userService.getManagerDetails());
				model.put("leadList", userService.getTeamDetails(2));
				// TODO: NO need of this code check dependencies
				model.put("projectNameList", projectService.getProjectName());
				// TODO: add assignedTo field and update it when project is assigned
				model.put("projectList", projectService.getProjectList());
				return "Manager";
			} else if (userService.checkUser(email, password) == 2) {
				User user = userService.userDetails(email);
				sessionService.createSession(user.getId(), user.getName(), request.getSession().getCreationTime(),
						request.getSession().getId(), "Login");
				request.getSession().setAttribute("user", user);
				model.put("message", "Welcome " + user.getName());
				model.put("userDetails", user);
				model.put("managerDetails", userService.getManagerDetails());
				model.put("memberList", userService.getTeamDetails(3));
				model.put("projectList", teamMember.getProjectList(user.getId()));
				model.put("acceptedProjectList", teamMember.getAcceptedProjectList(user.getId()));
				return "TeamLead";
			} else if (userService.checkUser(email, password) == 3) {
				User user = userService.userDetails(email);
				sessionService.createSession(user.getId(), user.getName(), request.getSession().getCreationTime(),
						request.getSession().getId(), "Login");
				model.put("message", "Welcome " + user.getName());
				model.put("userDetails", user);
				model.put("userId", user.getUserName());
				model.put("taskList", taskAssignment.getTaskList(user.getId()));
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
		}
		model.put("errorExit", true);
		model.put("error", "Enter Valid UserName and Password!!");
		request.getSession().invalidate();
		return "Login";
	}

	@PostMapping("/assignProject")
	public String assignProject(@RequestParam("userId") String userID,
			@RequestParam("TeamLeadDetails") String teamLeadDetails, @RequestParam("projectName") int projectId,
			@RequestParam("deadlineDate") Date deadLine, Map<String, Object> model, HttpServletRequest request)
			throws ParseException, ClassNotFoundException, IllegalAccessException, SQLException, IOException {
		log.debug("Project Assignment Process Begin !!");
		if (request.getSession(true) != null) {
			String[] leadDetails = teamLeadDetails.split(",");
			// TODO: add assignedTo field and update it when project is assigned
			taskAssignment.assignTaskToTeamLead(projectId, Integer.valueOf(leadDetails[0]),
					Integer.valueOf(leadDetails[1]), leadDetails[2], deadLine);
			return displayMsg(userID, "Project is allocated!!", request, model);
		}
		return "redirect:/";
	}

	@PostMapping("/addNewTeamLead")
	public String addNewTeamLead(@RequestParam("userId") String userID,
			@RequestParam("teamLeadName") String teamLeadName, @RequestParam("email") String email,
			Map<String, Object> model, HttpServletRequest request)
			throws ParseException, ClassNotFoundException, IllegalAccessException, SQLException, IOException {
		log.debug("Adding New Team Lead Process Begin !!");
		if (request.getSession(true) != null) {
			userService.addTeamLead(teamLeadName, email);
			return displayMsg(userID, "Team Lead is added!!", request, model);
		}
		return "redirect:/";
	}

	@PostMapping("/addProject")
	public String addProject(@RequestParam("userId") String userID, @RequestParam("projectName") String projectName,
			@RequestParam("projectDesc") String projectDesc, Map<String, Object> model, HttpServletRequest request)
			throws ParseException, ClassNotFoundException, IllegalAccessException, SQLException, IOException {
		log.debug("Adding New Project Begin !!");
		if (request.getSession(true) != null) {
			// TODO: add assignedTo field and update it when project is assigned
			projectService.addProject(projectName, projectDesc);
			return displayMsg(userID, "Project has been added!!", request, model);
		}
		return "redirect:/";
	}

	@PostMapping("/assignTaskToTeamMember")
	public String assignTaskToTeamMember(@RequestParam("userId") String userID,
			@RequestParam("TeamMemberDetails") String teamLeadDetails, @RequestParam("projectName") int projectId,
			@RequestParam("taskName") String taskName, @RequestParam("taskDesc") String taskDesc,
			@RequestParam("deadlineDate") Date deadLine, Map<String, Object> model, HttpServletRequest request)
			throws ParseException, ClassNotFoundException, IllegalAccessException, SQLException, IOException {
		log.debug("Assigned Task to Team Member Begin !!");
		if (request.getSession(true) != null) {
			String[] leadDetails = teamLeadDetails.split(",");
			// TODO: add assignedTo field and update it when project is assigned
			taskAssignment.assignTaskToTeamMember(projectId, taskName, taskDesc, Integer.valueOf(leadDetails[0]),
					Integer.valueOf(leadDetails[1]), leadDetails[2], deadLine);
			return displayMsg(userID, "Task has been assigned!!", request, model);
		}
		return "redirect:/";
	}

	@PostMapping("/addNewTeamMember")
	public String addNewTeamMember(@RequestParam("userId") String userID, @RequestParam("name") String name,
			@RequestParam("email") String email, @RequestParam("roleID") int roleId, Map<String, Object> model,
			HttpServletRequest request)
			throws ParseException, ClassNotFoundException, IllegalAccessException, SQLException, IOException {
		log.debug("Adding New Team Member Process Begin !!");
		if (request.getSession(true) != null) {
			userService.addNewTeamMember(name, email, roleId);
			return displayMsg(userID, "Team Member has been added to the system!!", request, model);
		}
		return "redirect:/";
	}

	@PostMapping("/acceptProject")
	public String acceptProject(@RequestParam("userId") String userID, @RequestParam("taskDecision") String decision,
			@RequestParam("userDetails") String userDetails, Map<String, Object> model, HttpServletRequest request)
			throws ParseException, ClassNotFoundException, IllegalAccessException, SQLException, IOException {
		log.debug("Project Decision Process Begin !!");
		if (request.getSession(true) != null) {
			String[] user = userDetails.split(",");
			if (taskAssignment.updateProjectStatus(decision, Integer.valueOf(user[0]), Integer.valueOf(user[3]))) {
				emailService.sendStatusNotification(user[2], user[1], decision);
			}
			log.debug("Project Decision Process Completed !!");
			return displayMsg(userID, "Project is accepted by you!!", request, model);
		}
		return "redirect:/";
	}

	@PostMapping("/acceptTask")
	public String acceptTask(@RequestParam("userId") String userID, @RequestParam("taskDecision") String decision,
			@RequestParam("userDetails") String userDetails, Map<String, Object> model, HttpServletRequest request)
			throws ParseException, ClassNotFoundException, IllegalAccessException, SQLException, IOException {
		log.debug("Task Decision Process Begin !!");
		if (request.getSession(true) != null) {
			String[] user = userDetails.split(",");
			if (taskAssignment.updateTaskStatus(decision, Integer.valueOf(user[0]))) {
				emailService.sendStatusNotification(user[2], user[1], decision);
			}
			log.debug("Task Decision Process Completed !!");
			return displayMsg(userID, "Task is accepted by you!!", request, model);
		}
		return "redirect:/";
	}

	@PostMapping("/logout")
	public String destroySession(HttpServletRequest request, @RequestParam("userId") int userId) {
		request.getSession().invalidate();
		sessionService.updateStatus("logout", userId);
		return "redirect:/";
	}

	@GetMapping("/error")
	public String errorPage(HttpServletRequest request) {
		request.getSession().invalidate();
		return "redirect:/";
	}

	private String displayMsg(String userID, String response, HttpServletRequest request, Map<String, Object> model)
			throws ClassNotFoundException, IllegalAccessException, SQLException, IOException {
		System.out.println("UserID --- " + userID);
		User user = userService.userDetails(userID);
		if (user != null) {
			if (user.getRoleId() == 1) {
				model.put("message", response);
				model.put("userDetails", user);
				model.put("managerDetails", userService.getManagerDetails());
				model.put("leadList", userService.getTeamDetails(2));
				// TODO: NO need of this code check dependencies
				model.put("projectNameList", projectService.getProjectName());
				// TODO: add assignedTo field and update it when project is assigned
				model.put("projectList", projectService.getProjectList());
				return "Manager";
			} else if (user.getRoleId() == 2) {
				model.put("message", response);
				model.put("userDetails", user);
				model.put("managerDetails", userService.getManagerDetails());
				model.put("memberList", userService.getTeamDetails(3));
				model.put("projectList", teamMember.getProjectList(user.getId()));
				model.put("acceptedProjectList", teamMember.getAcceptedProjectList(user.getId()));
				return "TeamLead";
			} else if (user.getRoleId() == 3) {
				model.put("message", response);
				model.put("userDetails", user);
				model.put("taskList", taskAssignment.getTaskList(user.getId()));
				model.put("message", "Welcome Team Member!");
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
		}
		model.put("errorExit", true);
		model.put("error", "Enter Valid UserName and Password!!");
		request.getSession().invalidate();
		return "Login";
	}

}
