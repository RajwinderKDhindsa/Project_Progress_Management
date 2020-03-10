package webprojectprogressmanagement.manager;

import java.util.Date;
import java.util.List;

import webprojectprogressmanagement.models.SubTasks;

public interface ISubTaskManager {

	void addNewTask(int projectId, int userId, Integer roleId, String taskName, String taskDesc, Date deadLine);

	boolean updateTaskStatus(String decision, Integer userId);

	List<SubTasks> getTaskDetails(int userId);
			
}
