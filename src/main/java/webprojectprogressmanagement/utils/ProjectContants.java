package webprojectprogressmanagement.utils;

public class ProjectContants {
	/*
	 * DB Properties
	 */
	public static String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
	public static String DB_URL = "jdbc:mysql://sehajde.myweb.cs.uwindsor.ca:3306/sehajde_DBproject";
	public static String DB_USER = "sehajde_db";
	public static String DB_PASSWORD = "DB@12345";
	/*
	 * Email Properties
	 */
	public static String EMAIL_SUBJECT = "New Assignment";
	public static String MSG = "\n \n You have assigned a new assignment. Open the Application and take an appropriate action : Accept/Reject \n \n Thanks, \n Rajwinder Kaur";
	public static String PASSWORD_EMAIL = "\n \n You are added in Project Managemnet System. Please find the Password to login into the system.\n\n";
	public static String SIGNATURE =" Thanks, \\n Rajwinder Kaur";
	public static String FROM = "ProjectAnalysis2019@gmail.com";
	public static String GMAIL_HOST = "smtp.gmail.com";
	public static String GMAIL_PORT = "587";
	public static String GMAIL_AUTH_ENABLED = "true";
	public static String GMAIL_TTS_ENABLED = "true";
	public static String GMAIL_USER = "projectanalysis2019@gmail.com";
	public static String GMAIL_PASSWORD = "ranarafi@123";
	/*
	 * Employee Table Queries
	 */
	public static String ALL_EMPLOYEES = "SELECT * FROM TeamInfo ";
	public static String INSERT_EMPLOYEE = "INSERT INTO emp VALUES (?,?,?,?)";
}
