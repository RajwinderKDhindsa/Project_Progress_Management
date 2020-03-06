package webprojectprogressmanagement.manager.managerImp;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import webprojectprogressmanagement.manager.ITeamInfoManager;
import webprojectprogressmanagement.models.Team;
import webprojectprogressmanagement.utils.HibernateUtil;

@Repository
public class TeamInfoManager implements ITeamInfoManager {

	private static final Logger log = LogManager.getLogger(TeamInfoManager.class);

	public static TeamInfoManager teamInfoManager = null;

	public static TeamInfoManager getInstance() {
		synchronized (TeamInfoManager.class) {
			if (teamInfoManager == null) {
				teamInfoManager = new TeamInfoManager();
			}

		}
		return teamInfoManager;
	}

	public boolean deleteTeamMember(Team team) {
		return false;
	}

	public boolean updateTeamMember(Team team, int teamId) {
		return false;
	}

	@Override
	public List<Team> getAllTeamMembers()
			throws ClassNotFoundException, IllegalAccessException, SQLException, IOException {
		Session session = null;
		List<Team> teamList = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Team> query = builder.createQuery(Team.class);
			Root<Team> root = query.from(Team.class);
			query.select(root).where(builder.equal(root.get("memberRoleId"), 3));
			Query<Team> q = session.createQuery(query);
			teamList = q.getResultList();
			System.out.println("Team Lead list Object : " + teamList);
			return (List<Team>) teamList;
		} catch (Exception e) {
			if (session.getTransaction().isActive()) {
				session.getTransaction().rollback();
			}
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return teamList;
	}

	@Override
	public Team getManager() throws ClassNotFoundException, IllegalAccessException, SQLException, IOException {
		Session session = null;
		Team teamManager = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Team> query = builder.createQuery(Team.class);
			Root<Team> root = query.from(Team.class);
			query.select(root).where(builder.equal(root.get("memberRoleId"), 1));
			Query<Team> q = session.createQuery(query);
			teamManager = q.getSingleResult();
			System.out.println(teamManager.getMemberName());
			return teamManager;
		} catch (Exception e) {
			if (session.getTransaction().isActive()) {
				session.getTransaction().rollback();
			}
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return teamManager;
	}

	@Override
	public List<Team> getTeamLeadList()
			throws ClassNotFoundException, IllegalAccessException, SQLException, IOException {
		Session session = null;
		List<Team> teamLeadList = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Team> query = builder.createQuery(Team.class);
			Root<Team> root = query.from(Team.class);
			query.select(root).where(builder.equal(root.get("memberRoleId"), 2));
			Query<Team> q = session.createQuery(query);
			teamLeadList = q.getResultList();
			return (List<Team>) teamLeadList;
		} catch (Exception e) {
			if (session.getTransaction().isActive()) {
				session.getTransaction().rollback();
			}
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return teamLeadList;
	}

	@Override
	public void assignTaskToTeamLead(int projectId, int leadDetails, String memberName) {
		Session session = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			Team teamLeadAssignment = new Team();
			teamLeadAssignment.setProjectId(projectId);
			teamLeadAssignment.setMemberRoleId(leadDetails);
			teamLeadAssignment.setMemberName(memberName);
			teamLeadAssignment.setStatus("Pending");
			session.save(teamLeadAssignment);
		} catch (Exception e) {
			if (session.getTransaction().isActive()) {
				session.getTransaction().rollback();
			}
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}

	}
}
