package webprojectprogressmanagement.manager.managerImp;

import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import webprojectprogressmanagement.manager.ISubTaskManager;
import webprojectprogressmanagement.models.SubTasks;
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
			log.debug("Assigned Task to Team Member Completed !!");
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
			CriteriaUpdate<SubTasks> update = builder.createCriteriaUpdate(SubTasks.class);
			Root<SubTasks> root = update.from(SubTasks.class);
			// set update and where clause
			update.set("status", decision);
			update.where(builder.equal(root.get("userId"), userId));
			Transaction transaction = session.beginTransaction();
			session.createQuery(update).executeUpdate();
			transaction.commit();
			log.debug("Task status updated to "+decision+" !!");
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

	@Override
	public List<SubTasks> getTaskDetails(int userId) {
		Session session = null;
		List<SubTasks> projectsList = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<SubTasks> query = builder.createQuery(SubTasks.class);
			Root<SubTasks> root = query.from(SubTasks.class);
			query.select(root).where(builder.equal(root.get("userId"), userId));;
			Query<SubTasks> q = session.createQuery(query);
			projectsList = q.getResultList();
			return projectsList;
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
		return projectsList;
	}
	

}
