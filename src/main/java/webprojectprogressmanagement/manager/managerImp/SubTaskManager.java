package webprojectprogressmanagement.manager.managerImp;

import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import webprojectprogressmanagement.manager.ISubTaskManager;
import webprojectprogressmanagement.models.SubTasks;
import webprojectprogressmanagement.models.Team;
import webprojectprogressmanagement.utils.HibernateUtil;

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

	@Override
	public void addNewTask(int projectId, int userId, Integer roleId, String taskName, String taskDesc, Date deadLine) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			SubTasks subTask = new SubTasks();
			subTask.setUserId(userId);
			subTask.setDeadline(deadLine);
			subTask.setRoleId(roleId);
			subTask.setTaskName(taskName);
			subTask.setTaskDesc(taskDesc);			
			session.save(subTask);
		} catch (Exception e) {
			if (session.getTransaction().isActive()) {
				session.getTransaction().rollback();
			}
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	@Override
	public boolean updateTaskStatus(String decision, Integer userId) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			// create update
			CriteriaUpdate<Team> update = builder.createCriteriaUpdate(Team.class);
			Root<Team> root = update.from(Team.class);
			// set update and where clause
			update.set("status", decision);
			update.where(builder.equal(root.get("userId"), userId));
			Transaction transaction = session.beginTransaction();
			session.createQuery(update).executeUpdate();
			transaction.commit();
			return true;
		} catch (Exception e) {
			if (session.getTransaction().isActive()) {
				session.getTransaction().rollback();
			}
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return false;
	}

}
