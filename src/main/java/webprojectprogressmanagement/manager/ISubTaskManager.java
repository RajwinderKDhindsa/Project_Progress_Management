package webprojectprogressmanagement.manager;

import java.util.Date;

public interface ISubTaskManager {

	void addNewTask(int projectId, int userId, Integer roleId, String taskName, String taskDesc, Date deadLine);

	boolean updateTaskStatus(String decision, Integer userId);
			
}
