package webprojectprogressmanagement.models;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "projectsinfo")
public class Projects {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "projectID")
	private int projectId;

	@Column(name = "projectName")
	private String projectName;

	@Column(name = "projectDesc")
	private String projectDesc;

	@Column(name = "status")
	private String projectStatus;

	@Column(name = "teamId")
	private int teamId;

	@Column(name = "deadline")
	private Date deadline;

	public Projects() {

	}

	public Projects(int projectId, String projectName, String projectDesc, String projectStatus, int teamId,
			Date deadline) {

		this.projectId = projectId;
		this.projectName = projectName;
		this.projectDesc = projectDesc;
		this.projectStatus = projectStatus;
		this.teamId = teamId;
		this.deadline = deadline;
	}

	public String getProjectStatus() {
		return projectStatus;
	}

	public void setProjectStatus(String projectStatus) {
		this.projectStatus = projectStatus;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public String getProjectDesc() {
		return projectDesc;
	}

	public void setProjectDesc(String projectDesc) {
		this.projectDesc = projectDesc;
	}

	public int getTeamId() {
		return teamId;
	}

	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

}
