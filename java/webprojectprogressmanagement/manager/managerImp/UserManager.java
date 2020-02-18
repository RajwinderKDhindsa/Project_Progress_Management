package webprojectprogressmanagement.manager.managerImp;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import webprojectprogressmanagement.manager.ISubTaskManager;
import webprojectprogressmanagement.models.User;
import webprojectprogressmanagement.utils.HibernateUtil;

@Component
public class UserManager implements ISubTaskManager {

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
	
	public List < User > getUsers() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
        	Transaction tx = session.beginTransaction();
        	System.out.println("Properties --- "+session.getProperties());
            return session.createQuery("from User", User.class).list();
        }
    }


}
