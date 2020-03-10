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
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import webprojectprogressmanagement.manager.IProjectInfoManager;
import webprojectprogressmanagement.models.Projects;
import webprojectprogressmanagement.utils.HibernateUtil;

@Repository
public class ProjectInfoManager implements IProjectInfoManager {

	private static final Logger log = LogManager.getLogger(ProjectInfoManager.class);

	public static ProjectInfoManager projectInfoManager = null;

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
		Session session = null;
		List<Projects> projectsList = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Projects> query = builder.createQuery(Projects.class);
			Root<Projects> root = query.from(Projects.class);
			query.select(root);
			Query<Projects> q = session.createQuery(query);
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
	
	@Override
	public List<Projects> getAcceptedProjectList()
			throws ClassNotFoundException, IllegalAccessException, SQLException, IOException {
		Session session = null;
		List<Projects> projectsList = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Projects> query = builder.createQuery(Projects.class);
			Root<Projects> root = query.from(Projects.class);
			query.select(root).where(builder.equal(root.get("projectStatus"), "Accepted"));
			Query<Projects> q = session.createQuery(query);
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
	
	@Override
	public List<Projects> getProjectList(int roleId)
			throws ClassNotFoundException, IllegalAccessException, SQLException, IOException {
		Session session = null;
		List<Projects> projectsList = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Projects> query = builder.createQuery(Projects.class);
			Root<Projects> root = query.from(Projects.class);
			query.select(root).where(builder.equal(root.get("roleId"), roleId));
			Query<Projects> q = session.createQuery(query);
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

	
	@Override
	public List<String> getProjectNameList()
			throws ClassNotFoundException, IllegalAccessException, SQLException, IOException {
		Session session = null;
		List<String> projectsList = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<String> query = builder.createQuery(String.class);
			Root<Projects> root = query.from(Projects.class);
			query.select(root.get("projectName"));
			Query<String> q = session.createQuery(query);
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

	@Override
	public void addProject(String projectName, String projectDesc) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Projects newProject = new Projects();
			newProject.setProjectDesc(projectDesc);
			newProject.setProjectName(projectName);
			newProject.setProjectStatus("Pending");
			session.save(newProject);
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

}
