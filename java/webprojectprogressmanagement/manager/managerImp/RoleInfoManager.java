package webprojectprogressmanagement.manager.managerImp;

import java.util.List;

import javax.persistence.criteria.CriteriaQuery;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import webprojectprogressmanagement.manager.IRoleInfoManager;
import webprojectprogressmanagement.models.Role;
import webprojectprogressmanagement.utils.HibernateUtil;

@Component
public class RoleInfoManager implements IRoleInfoManager {

	private static final Logger log = LogManager.getLogger(RoleInfoManager.class);

	public static RoleInfoManager roleInfoManager = null;

	private RoleInfoManager() {

	}

	public static RoleInfoManager getInstance() {
		synchronized (RoleInfoManager.class) {
			if (roleInfoManager == null) {
				roleInfoManager = new RoleInfoManager();
			}
		}
		log.debug("roleInfoManager Initialised !!");
		return roleInfoManager;
	}
	
	public List < Role > getRoles() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		org.hibernate.Transaction tr = session.beginTransaction();

		System.out.println(session);
		CriteriaQuery cq = session.getCriteriaBuilder().createQuery(Role.class);
		System.out.println(cq);
		cq.from(Role.class);
		List<Role> roleList = session.createQuery(cq).getResultList();

		for (Role role : roleList) {
			System.out.println("ID: " + role.getRoleId());
			System.out.println("Name: " + role.getRoleName());
		}  
		

		tr.commit();
		System.out.println("Data printed");
		return roleList;
			
		/*
		 * try (Session session = HibernateUtil.getSessionFactory().openSession()) {
		 * Transaction tx = session.beginTransaction();
		 * System.out.println("Properties --- "+session.getProperties()); return
		 * session.createQuery("from Role", Role.class).list(); }
		 */
    }


}
