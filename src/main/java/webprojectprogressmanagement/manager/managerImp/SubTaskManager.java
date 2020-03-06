package webprojectprogressmanagement.manager.managerImp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import webprojectprogressmanagement.manager.ISubTaskManager;

@Repository
public class SubTaskManager implements ISubTaskManager {

	private static final Logger log = LogManager.getLogger(SubTaskManager.class);

	public static SubTaskManager subTaskManager = null;

	

	public static SubTaskManager getInstance() {
		synchronized (SubTaskManager.class) {
			if (subTaskManager == null) {
				subTaskManager = new SubTaskManager();
			}
		}
		log.debug("SubTaskManager Initialised !!");
		return subTaskManager;
	}


}
