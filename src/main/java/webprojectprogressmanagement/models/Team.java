package webprojectprogressmanagement.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "teaminfo")
public class Team {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "teamId")
	private int teamId;
	
	
	@Column(name = "projectID")
	private int projectId;
	
	@Column(name = "memberId")
	private int memberId;
	
	@Column(name = "memberRoleId")
	private int memberRoleId;
	
	
	@Column(name = "memberName")
	private String memberName;
	
	public Team() {
		
	}
	

	public Team(int teamId, int projectId, int memberId, int memberRoleId, String memberName) {
		this.teamId = teamId;
		this.projectId = projectId;
		this.memberId = memberId;
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

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
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
