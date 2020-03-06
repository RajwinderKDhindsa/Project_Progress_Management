package webprojectprogressmanagement.service;

public interface ITaskAssignment {

	void assignTask(int projectId, int leadDetails, String memberName);

	void assignTaskToTeamLead(int projectId, int leadDetails, String memberName);
}
