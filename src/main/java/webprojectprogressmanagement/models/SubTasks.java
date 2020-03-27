package webprojectprogressmanagement.models;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "projectSubtasks")
public class SubTasks {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "taskId")
	private int taskId;

	@Column(name = "memberId")
	private int userId;

	@Column(name = "memberRoleId")
	private int roleId;

	@Column(name = "taskName")
	private String taskName;

	@Column(name = "taskDescription")
	private String taskDesc;

	@Column(name = "deadline")
	private Date deadline;

	@Column(name = "status")
	private String status;
	
	public SubTasks() {
		
	}

	public SubTasks(int taskId, int userId, int roleId, String taskName, String taskDesc, Date deadline,
			String status) {
		this.taskId = taskId;
		this.userId = userId;
		this.roleId = roleId;
		this.taskName = taskName;
		this.taskDesc = taskDesc;
		this.deadline = deadline;
		this.status = status;
	}

	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getTaskDesc() {
		return taskDesc;
	}

	public void setTaskDesc(String taskDesc) {
		this.taskDesc = taskDesc;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
