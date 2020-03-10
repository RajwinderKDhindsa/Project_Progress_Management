package webprojectprogressmanagement.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "AssignTask")
public class Team {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "teamId")
	private int teamId;
	
	@Column(name = "projectID")
	private int projectID;
	
	@Column(name = "status",nullable = false, columnDefinition =  "varchar(25) default 'Pending'")
	private String status;
	
	@Column(name = "memberRoleId")
	private int memberRoleId;
	
	@Column(name = "userId")
	private int userId;
		
		
	@Column(name = "memberName")
	private String memberName;
	
	@Column(name = "deadline")
	private Date deadline;
	
	public Team() {
		
	}
	

	public Team(int teamId, int projectId, String status, int memberRoleId,int userId, String memberName, Date deadline) {
		this.teamId = teamId;
		this.projectID = projectId;
		this.status = status;
		this.userId = userId;
		this.deadline = deadline;
		this.memberRoleId = memberRoleId;
		this.memberName = memberName;
	}

	
	public Date getDeadline() {
		return deadline;
	}


	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public int getTeamId() {
		return teamId;
	}

	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}

	public int getProjectId() {
		return projectID;
	}

	public void setProjectId(int projectId) {
		this.projectID = projectId;
	}

	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public int getMemberRoleId() {
		return memberRoleId;
	}

	public void setMemberRoleId(int memberRoleId) {
		this.memberRoleId = memberRoleId;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

}
