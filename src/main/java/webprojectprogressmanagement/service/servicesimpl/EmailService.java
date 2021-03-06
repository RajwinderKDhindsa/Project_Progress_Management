package webprojectprogressmanagement.service.servicesimpl;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import webprojectprogressmanagement.service.IEmailService;
import webprojectprogressmanagement.utils.ProjectContants;

@Service
public class EmailService implements IEmailService{
	private static final Logger log = LogManager.getLogger(EmailService.class);

	@Override
	public void send(String to, String username) {

		Properties prop = new Properties();
		prop.put("mail.smtp.host", ProjectContants.GMAIL_HOST);
		prop.put("mail.smtp.port", ProjectContants.GMAIL_PORT);
		prop.put("mail.smtp.auth", ProjectContants.GMAIL_AUTH_ENABLED);
		prop.put("mail.smtp.starttls.enable", ProjectContants.GMAIL_TTS_ENABLED);
	
		Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
			protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
				return new javax.mail.PasswordAuthentication(ProjectContants.GMAIL_USER,
						ProjectContants.GMAIL_PASSWORD);
			}
		});

		try {
			if(username.isEmpty())
				username = "Team Member";

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(ProjectContants.FROM));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			message.setSubject(ProjectContants.EMAIL_SUBJECT);
			message.setText("Dear "+username+" ," + ProjectContants.MSG);

			Transport.send(message);

			log.debug("Email sent to User : " + to + " ");

		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}
	@Override
	public void sendPasswordEmail(String to, String username,String password) {

		Properties prop = new Properties();
		prop.put("mail.smtp.host", ProjectContants.GMAIL_HOST);
		prop.put("mail.smtp.port", ProjectContants.GMAIL_PORT);
		prop.put("mail.smtp.auth", ProjectContants.GMAIL_AUTH_ENABLED);
		prop.put("mail.smtp.starttls.enable", ProjectContants.GMAIL_TTS_ENABLED);
	
		Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
			protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
				return new javax.mail.PasswordAuthentication(ProjectContants.GMAIL_USER,
						ProjectContants.GMAIL_PASSWORD);
			}
		});

		try {
			if(username.isEmpty())
				username = "Team Member";

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(ProjectContants.FROM));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			message.setSubject(ProjectContants.EMAIL_SUBJECT_PASSWORD);
			message.setText("Dear "+username+" ," + ProjectContants.PASSWORD_EMAIL+password+"\n\n"+ProjectContants.SIGNATURE);

			Transport.send(message);

			log.debug("Email sent to User : " + to + " ");

		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}
	
	@Override
	public void sendStatusNotification(String email, String username, String decision) {
		Properties prop = new Properties();
		prop.put("mail.smtp.host", ProjectContants.GMAIL_HOST);
		prop.put("mail.smtp.port", ProjectContants.GMAIL_PORT);
		prop.put("mail.smtp.auth", ProjectContants.GMAIL_AUTH_ENABLED);
		prop.put("mail.smtp.starttls.enable", ProjectContants.GMAIL_TTS_ENABLED);
	
		Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
			protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
				return new javax.mail.PasswordAuthentication(ProjectContants.GMAIL_USER,
						ProjectContants.GMAIL_PASSWORD);
			}
		});

		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(ProjectContants.FROM));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
			message.setSubject(ProjectContants.EMAIL_SUBJECT_STATUS);
			message.setText("Dear "+username.trim()+" ," + ProjectContants.STATUS_NOTIFICATION+"\n\n"+ProjectContants.STATUS+decision+"!"+ProjectContants.SIGNATURE);

			Transport.send(message);

			log.debug("Email sent to User : " + email + " ");

		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}
}
