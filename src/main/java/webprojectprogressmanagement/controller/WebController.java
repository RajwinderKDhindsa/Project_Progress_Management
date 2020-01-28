package webprojectprogressmanagement.controller;

import java.sql.Connection;
import java.sql.DriverManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WebController {

	private static final Logger log = LogManager.getLogger(WebController.class);
	/*
	 * @Autowired EmailTrigger emailTrigger;
	 */
	@Value("${spring.application.name}")
	String appName;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String webSearchEngineStartUp(Model model) {
		if (log.isDebugEnabled())
			log.debug("Executing web controller...");
	
		model.addAttribute("appName", appName);
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "user", "password");
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return "Main";
	}


}
