package webprojectprogressmanagement.manager.managerImp;

import java.security.SecureRandom;
import java.util.List;
import java.util.Random;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import webprojectprogressmanagement.manager.IUserManager;
import webprojectprogressmanagement.models.User;
import webprojectprogressmanagement.service.servicesimpl.EmailService;
import webprojectprogressmanagement.utils.HibernateUtil;

@Repository
public class UserManager implements IUserManager {

	@Autowired
	EmailService emailService;

	private static final Logger log = LogManager.getLogger(UserManager.class);
	private static final Random RANDOM = new SecureRandom();
	private static final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

	public static UserManager userManager = null;

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
		Session session = null;
		User teamManager = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<User> query = builder.createQuery(User.class);
			Root<User> root = query.from(User.class);
			query.select(root).where(builder.equal(root.get("roleId"), 1));
			Query<User> q = session.createQuery(query);
			teamManager = q.getSingleResult();
			System.out.println(teamManager.getUserName());
			return teamManager;
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
		return teamManager;
	}

	@Override
	public void addNewTeamLead(String teamLeadName, String email) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			User teamLead = new User();
			teamLead.setName(teamLeadName);
			teamLead.setUserName(email);
			teamLead.setRoleId(2);
			String password = generatePassword(20);
			teamLead.setPassword(password);
			session.save(teamLead);
			log.debug("Adding New Team Lead Process Completed !!");
			// send email to person with password
			emailService.sendPasswordEmail(email, teamLeadName, password);
			log.debug("Email Notification Sent !!");
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

	private static String generatePassword(int length) {
		StringBuilder returnValue = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			returnValue.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
		}
		return new String(returnValue);
	}

	@Override
	public User getUserDetails(String email) {
		Session session = null;
		User user = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<User> query = builder.createQuery(User.class);
			Root<User> root = query.from(User.class);
			query.select(root).where(builder.equal(root.get("userName"), email));
			Query<User> q = session.createQuery(query);
			user = q.getSingleResult();
			return user;
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
		return user;
	}

	@Override
	public void addNewTeamMember(String name, String email, int roleId) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			User teamLead = new User();
			teamLead.setName(name);
			teamLead.setUserName(email);
			teamLead.setRoleId(roleId);
			String password = generatePassword(20);
			// send email to person about with password
			teamLead.setPassword(password);
			session.save(teamLead);
			log.debug("Adding New Team Member Process Completed !!");
			emailService.sendPasswordEmail(email, name, password);
			log.debug("Password sent in email to Team Member !!");
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
	public List<User> getTeamDetails(int roleID) {
		Session session = null;
		List<User> teamLeadList = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<User> query = builder.createQuery(User.class);
			Root<User> root = query.from(User.class);
			query.select(root).where(builder.equal(root.get("roleId"), roleID));
			Query<User> q = session.createQuery(query);
			teamLeadList = q.getResultList();
			return (List<User>) teamLeadList;
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
		return teamLeadList;
	}

}
