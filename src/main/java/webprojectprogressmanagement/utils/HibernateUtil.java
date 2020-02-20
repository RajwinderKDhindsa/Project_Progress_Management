package webprojectprogressmanagement.utils;

import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import webprojectprogressmanagement.models.Issues;
import webprojectprogressmanagement.models.Login;
import webprojectprogressmanagement.models.Projects;
import webprojectprogressmanagement.models.Role;
import webprojectprogressmanagement.models.SubTasks;
import webprojectprogressmanagement.models.Team;
import webprojectprogressmanagement.models.User;

public class HibernateUtil {

	private static SessionFactory sessionFactory;

	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			try {
				Configuration configuration = new Configuration();
				// Hibernate settings equivalent to hibernate.cfg.xml's properties
				Properties settings = new Properties();
				settings.put(Environment.DRIVER, ProjectContants.DB_DRIVER);
				settings.put(Environment.URL, ProjectContants.DB_URL);
				settings.put(Environment.USER, ProjectContants.DB_USER);
				settings.put(Environment.PASS, ProjectContants.DB_PASSWORD);
				settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
				settings.put(Environment.SHOW_SQL, "true");
				settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
				settings.put(Environment.HBM2DDL_AUTO, "validate");
				configuration.setProperties(settings);
				configuration.addAnnotatedClass(User.class);
				configuration.addAnnotatedClass(Team.class);
				configuration.addAnnotatedClass(Role.class);
				configuration.addAnnotatedClass(Projects.class);
				configuration.addAnnotatedClass(SubTasks.class);
				configuration.addAnnotatedClass(Login.class);
				configuration.addAnnotatedClass(Issues.class);

				ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
						.applySettings(configuration.getProperties()).build();
				sessionFactory = configuration.buildSessionFactory(serviceRegistry);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return sessionFactory;
	}
}
