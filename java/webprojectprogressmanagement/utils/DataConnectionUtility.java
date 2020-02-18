package webprojectprogressmanagement.utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataConnectionUtility {

	private static DataConnectionUtility connectionUtiliy = null;
	private Connection connection = null;

	private DataConnectionUtility() {
	}

	public static DataConnectionUtility getInstance()
			throws IOException, IllegalAccessException, SQLException, ClassNotFoundException {
		System.out.println("getInstance");
		// Synchronized against connectionUtility instance

		synchronized (DataConnectionUtility.class) {
			// Check whether the
			/*
			 * connectionUtility is null or not
			 */if (connectionUtiliy == null) {
				System.out.println("connection is null !");

				connectionUtiliy = new DataConnectionUtility();
				System.out.println("Create new connection!!");
				// Load driver class
				Class.forName(ProjectContants.DB_DRIVER);

				// Create connection
				connectionUtiliy.setConnection(DriverManager.getConnection(ProjectContants.DB_URL,
						ProjectContants.DB_USER, ProjectContants.DB_PASSWORD));
				System.out.println("Connection created!!");
			}
			// System.out.println(connectionUtiliy.getInstance());
			return connectionUtiliy;
		}
	}

	public Connection getConnection() throws ClassNotFoundException, SQLException, IOException, IllegalAccessException {
		if (connection.isClosed()) {
			System.out.println("connection was closed!!");
			getConnection();
			System.out.println("Connection created!!");
		}
		return connection;
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

}
