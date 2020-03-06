package webprojectprogressmanagement.models;

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
	private int projectId;
	
	@Column(name = "status",nullable = false, columnDefinition =  "varchar(25) default 'Peding'")
	private String status;
	
	@Column(name = "memberRoleId")
	private int memberRoleId;
	
	
	@Column(name = "memberName")
	private String memberName;
	
	public Team() {
		
	}
	

	public Team(int teamId, int projectId, String status, int memberRoleId, String memberName) {
		this.teamId = teamId;
		this.projectId = projectId;
		this.status = status;
		this.memberRoleId = memberRoleId;
		this.memberName = memberName;
	}

	public int getTeamId() {
		return teamId;
	}

	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
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
