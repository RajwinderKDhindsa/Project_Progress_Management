package webprojectprogressmanagement.service;

public interface IEmailService {
	public void send(String to, String username);

	void sendPasswordEmail(String to, String username, String password);

	void sendStatusNotification(String string, String string2, String decision);
}
