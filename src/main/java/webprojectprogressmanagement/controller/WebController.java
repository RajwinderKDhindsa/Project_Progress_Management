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
import org.springframework.boot.autoconfigure.web.ServerProperties.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
import javax.servlet.http.HttpSession;

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
		log.info("User try to login with userName " + email);
		if (userService.checkUser(email, password) == 1) {
			User user = userService.userDetails(email);
			request.getSession().setAttribute("user", user);
			model.put("message", "Welcome" + user.getName() + " Team Manager!!");
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
			request.getSession().setAttribute("user", user);
			model.put("message", "Welcome" + user.getName() + " Team Manager!!");
			model.put("userDetails", user);
			model.put("managerDetails", userService.getManagerDetails());
			model.put("memberList", userService.getTeamDetails(3));
			model.put("projectList", teamMember.getProjectList(user.getId()));
			model.put("acceptedProjectList", teamMember.getAcceptedProjectList(user.getId()));
			return "TeamLead";
		} else if (userService.checkUser(email, password) == 3) {
			User user = userService.userDetails(email);
			request.getSession().setAttribute("user", user);
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
		model.put("errorExit", true);
		model.put("error", "Enter Valid UserName and Password!!");
		request.getSession().invalidate();
		return "Login";
	}

	@GetMapping("/logged")
	public String logged(Map<String, Object> model, HttpServletRequest request)

			throws ParseException, ClassNotFoundException, IllegalAccessException, SQLException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		if (user != null) {
			if (user.getId() == 1) {
				model.put("message", request.getSession().getAttribute("response"));
				model.put("userDetails", user);
				model.put("managerDetails", userService.getManagerDetails());
				model.put("leadList", userService.getTeamDetails(2));
				// TODO: NO need of this code check dependencies
				model.put("projectNameList", projectService.getProjectName());
				// TODO: add assignedTo field and update it when project is assigned
				model.put("projectList", projectService.getProjectList());
				return "Manager";
			} else if (user.getId() == 2) {
				model.put("message", request.getSession().getAttribute("response"));
				model.put("userDetails", user);
				model.put("managerDetails", userService.getManagerDetails());
				model.put("memberList", userService.getTeamDetails(3));
				model.put("projectList", teamMember.getProjectList(user.getId()));
				model.put("acceptedProjectList", teamMember.getAcceptedProjectList(user.getId()));
				return "TeamLead";
			} else if (user.getId() == 3) {
				model.put("message", request.getSession().getAttribute("response"));
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

	@PostMapping("/assignProject")
	public String assignProject(@RequestParam("TeamLeadDetails") String teamLeadDetails,
			@RequestParam("projectName") int projectId, @RequestParam("deadlineDate") Date deadLine,
			Map<String, Object> model, HttpServletRequest request)
			throws ParseException, ClassNotFoundException, IllegalAccessException, SQLException, IOException {
		if (request.getSession(true) != null) {
			String[] leadDetails = teamLeadDetails.split(",");
			// TODO: add assignedTo field and update it when project is assigned
			taskAssignment.assignTaskToTeamLead(projectId, Integer.valueOf(leadDetails[0]),
					Integer.valueOf(leadDetails[1]), leadDetails[2], deadLine);
			request.getSession().setAttribute("response", "Team Lead is added!!");
			return "redirect:/logged";
		}
		return "redirect:/";
	}

	@PostMapping("/addNewTeamLead")
	public String addNewTeamLead(@RequestParam("teamLeadName") String teamLeadName, @RequestParam("email") String email,
			Map<String, Object> model, HttpServletRequest request)
			throws ParseException, ClassNotFoundException, IllegalAccessException, SQLException, IOException {
		if (request.getSession(true) != null) {
			userService.addTeamLead(teamLeadName, email);
			request.getSession().setAttribute("response", "Team Lead is added!!");
			return "redirect:/logged";
		}
		return "redirect:/";
	}

	@PostMapping("/addProject")
	public String addProject(@RequestParam("projectName") String projectName,
			@RequestParam("projectDesc") String projectDesc, Map<String, Object> model, HttpServletRequest request)
			throws ParseException, ClassNotFoundException, IllegalAccessException, SQLException, IOException {
		if (request.getSession(true) != null) {
			// TODO: add assignedTo field and update it when project is assigned
			projectService.addProject(projectName, projectDesc);
			request.getSession().setAttribute("response", "Project is added!!");
			return "redirect:/logged";
		}
		return "redirect:/";
	}

	/*
	 * assignTaskToTeamMember(int projectId, String taskName, String taskDesc, int userId, Integer roleId,
			String memberName, Date deadLine)
			${member.id},${member.roleId},${member.name}
	 */
	@PostMapping("/assignTaskToTeamMember") // it only support port method
	public String assignTaskToTeamMember(@RequestParam("TeamMemberDetails") String teamLeadDetails,
			@RequestParam("projectName") int projectId, @RequestParam("taskName") String taskName,
			@RequestParam("taskDesc") String taskDesc, @RequestParam("deadlineDate") Date deadLine,
			Map<String, Object> model, HttpServletRequest request)
			throws ParseException, ClassNotFoundException, IllegalAccessException, SQLException, IOException {
		if (request.getSession(true) != null) {
			System.out.println("teamLeadDetails ---- "+teamLeadDetails);
			String[] leadDetails = teamLeadDetails.split(",");
			// TODO: add assignedTo field and update it when project is assigned
			taskAssignment.assignTaskToTeamMember(projectId, taskName, taskDesc, Integer.valueOf(leadDetails[0]),
					Integer.valueOf(leadDetails[1]), leadDetails[2], deadLine);
			request.getSession().setAttribute("response", "Task has been assigned!!");
			return "redirect:/logged";
		}
		return "redirect:/";
	}

	@PostMapping("/addNewTeamMember")
	public String addNewTeamMember(@RequestParam("name") String name, @RequestParam("email") String email,
			@RequestParam("roleID") int roleId, Map<String, Object> model, HttpServletRequest request)
			throws ParseException, ClassNotFoundException, IllegalAccessException, SQLException, IOException {
		if (request.getSession(true) != null) {
			userService.addNewTeamMember(name, email, roleId);
			request.getSession().setAttribute("response", "Team Member has been added to the system!!");
			return "redirect:/logged";
		}
		return "redirect:/";
	}

	@PostMapping("/acceptProject")
	public String acceptProject(@RequestParam("taskDecision") String decision,
			@RequestParam("userDetails") String userDetails, Map<String, Object> model, HttpServletRequest request)
			throws ParseException, ClassNotFoundException, IllegalAccessException, SQLException, IOException {
		if (request.getSession(true) != null) {
			String[] user = userDetails.split(",");
			if (taskAssignment.updateProjectStatus(decision, Integer.valueOf(user[0]), Integer.valueOf(user[3]))) {
				emailService.sendStatusNotification(user[2], user[1], decision);
			}
			request.getSession().setAttribute("response", "Project is accepted by you!!");
			return "redirect:/logged";
		}
		return "redirect:/";
	}

	@PostMapping("/acceptTask")
	public String acceptTask(@RequestParam("taskDecision") String decision,
			@RequestParam("userDetails") String userDetails, Map<String, Object> model, HttpServletRequest request)
			throws ParseException, ClassNotFoundException, IllegalAccessException, SQLException, IOException {
		if (request.getSession(true) != null) {
			String[] user = userDetails.split(",");
			if (taskAssignment.updateTaskStatus(decision, Integer.valueOf(user[0]))) {
				emailService.sendStatusNotification(user[2], user[1], decision);
			}
			request.getSession().setAttribute("response", "Task is accepted by you!!");
			return "redirect:/logged";
		}
		return "redirect:/";
	}

	@PostMapping("/logout")
	public String destroySession(HttpServletRequest request) {
		request.getSession().invalidate();
		return "redirect:/";
	}

	@GetMapping("/error")
	public String errorPage(HttpServletRequest request) {
		request.getSession().invalidate();
		return "redirect:/";
	}

}
