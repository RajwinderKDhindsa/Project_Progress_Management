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

import webprojectprogressmanagement.manager.IUserManager;
import webprojectprogressmanagement.models.User;
import webprojectprogressmanagement.utils.HibernateUtil;

@Component
public class UserManager implements IUserManager {

	private static final Logger log = LogManager.getLogger(UserManager.class);

	public static UserManager userManager = null;

	private UserManager() {

	}

	public static UserManager getInstance() {
		synchronized (UserManager.class) {
			if (userManager == null) {
				userManager = new UserManager();
			}
		}
		log.debug("UerManager Initialised !!");
		return userManager;
	}

	public List<User> getUsers() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("from User", User.class).list();
		}
	}

	public User getManagerOnly() {
		Transaction transaction = null;
		User teamManager = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<User> query = builder.createQuery(User.class);
			Root<User> root = query.from(User.class);
			query.select(root).where(builder.equal(root.get("roleId"), 1));
			Query<User> q = session.createQuery(query);
			teamManager = q.getSingleResult();
			System.out.println(teamManager.getUserName());
			transaction.commit();
			return teamManager;
		} catch (Exception e) {
			e.printStackTrace();
			if (transaction != null) {
				transaction.rollback();
			}
		}
		return teamManager;
	}
	public List<User> getTeamLeadList()
			throws ClassNotFoundException, IllegalAccessException, SQLException, IOException {
		Transaction transaction = null;
		List<User> teamLeadList = null;
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			transaction = session.beginTransaction();

			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<User> query = builder.createQuery(User.class);
			Root<User> root = query.from(User.class);
			query.select(root).where(builder.equal(root.get("roleId"), 2));
			Query<User> q = session.createQuery(query);
			teamLeadList = q.getResultList();
			System.out.println("Team Lead list Object : "+teamLeadList);
			transaction.commit();
			return (List<User>) teamLeadList;
		} catch (Exception e) {
			e.printStackTrace();
			if (transaction != null) {
				transaction.rollback();
			}
		}
		return teamLeadList;
	}

}
