package webprojectprogressmanagement.manager.managerImp;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import webprojectprogressmanagement.manager.IRoleInfoManager;
import webprojectprogressmanagement.models.Role;
import webprojectprogressmanagement.utils.HibernateUtil;

@Repository
public class RoleInfoManager implements IRoleInfoManager {

	private static final Logger log = LogManager.getLogger(RoleInfoManager.class);

	public static RoleInfoManager roleInfoManager = null;

	public static RoleInfoManager getInstance() {
		synchronized (RoleInfoManager.class) {
			if (roleInfoManager == null) {
				roleInfoManager = new RoleInfoManager();
			}
		}
		log.debug("roleInfoManager Initialised !!");
		return roleInfoManager;
	}

	public List<Role> getRoles() {
		Session session = null;
		List<Role> roleList = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Role> query = builder.createQuery(Role.class);
			Root<Role> root = query.from(Role.class);
			query.select(root);
			Query<Role> q = session.createQuery(query);
			roleList = q.getResultList();
			System.out.println("******************************Role List : " + roleList);
			return (List<Role>) roleList;
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
		return roleList;
	}

}
