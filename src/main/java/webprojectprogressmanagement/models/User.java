package webprojectprogressmanagement.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "userDetails")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "userId")
	private int id;

	@Column(name = "userName")
	private String userName;

	@Column(name = "name")
	private String name;

	@Column(name = "password")
	private String password;

	@Column(name = "roleId")
	private int roleId;

	public User() {

	}

	public User(int id, String userName, String name, String password, int roleId) {
		this.id = id;
		this.userName = userName;
		this.name = name;
		this.password = password;
		this.roleId = roleId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

}
