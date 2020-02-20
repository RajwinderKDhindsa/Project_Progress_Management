package webprojectprogressmanagement.manager.managerImp;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import webprojectprogressmanagement.manager.IProjectInfoManager;
import webprojectprogressmanagement.models.Projects;
import webprojectprogressmanagement.utils.HibernateUtil;

@Component
public class ProjectInfoManager implements IProjectInfoManager {

	private static final Logger log = LogManager.getLogger(ProjectInfoManager.class);

	public static ProjectInfoManager projectInfoManager = null;

	private ProjectInfoManager() {

	}

	public static ProjectInfoManager getInstance() {
		synchronized (ProjectInfoManager.class) {
			if (projectInfoManager == null) {
				projectInfoManager = new ProjectInfoManager();
			}

		}
		return projectInfoManager;
	}

	@Override
	public List<Projects> getProjectList()
			throws ClassNotFoundException, IllegalAccessException, SQLException, IOException {
		Transaction transaction = null;
		List<Projects> projectsList = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();

			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Projects> query = builder.createQuery(Projects.class);
			Root<Projects> root = query.from(Projects.class);
			// query.select(root).where(builder.equal(root.get("memberRoleId"), 2));
			query.select(root);
			Query<Projects> q = session.createQuery(query);
			projectsList = q.getResultList();
			transaction.commit();
			return projectsList;
		} catch (Exception e) {
			e.printStackTrace();
			if (transaction != null) {
				transaction.rollback();
			}
		}
		return projectsList;
	}

	@Override
	public List<String> getProjectNameList()
			throws ClassNotFoundException, IllegalAccessException, SQLException, IOException {
		Transaction transaction = null;
		List<String> projectsList = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();

			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<String> query = builder.createQuery(String.class);
			Root<Projects> root = query.from(Projects.class);
			query.select(root.get("projectName"));
			Query<String> q = session.createQuery(query);
			projectsList = q.getResultList();
			transaction.commit();
			return projectsList;
		} catch (Exception e) {
			e.printStackTrace();
			if (transaction != null) {
				transaction.rollback();
			}
		}
		return projectsList;
	}

}
