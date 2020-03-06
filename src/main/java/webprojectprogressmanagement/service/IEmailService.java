package webprojectprogressmanagement.service;

public interface IEmailService {
	public void send(String to, String username);

	void sendPasswordEmail(String to, String username, String password);
}
