package webprojectprogressmanagement.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import webprojectprogressmanagement.models.Team;

public interface ITeamMemberService {
	List<Team> getTeamMember() throws IllegalAccessException, ClassNotFoundException, SQLException, IOException; 
}
