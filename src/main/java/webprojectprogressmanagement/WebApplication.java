package webprojectprogressmanagement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebApplication {

	private static final Logger log = LogManager.getLogger(WebApplication.class);

	public static void main(String[] args) {
		if (log.isDebugEnabled()) {
			log.debug("Start: Project Progress Management Web Spring Boot Application");
		}
		SpringApplication.run(WebApplication.class, args);
	}
}
