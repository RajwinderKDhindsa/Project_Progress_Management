package webprojectprogressmanagement.models;

public class Team {
	
	private int teamId;
	private int projectId;
	private int memberId;
	private int memberRoleId;
	private String memberName;

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
