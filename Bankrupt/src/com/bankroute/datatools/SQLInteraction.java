package com.bankroute.datatools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import com.bankroute.user.Banker;
import com.bankroute.user.Customer;
import com.bankroute.user.User;

import java.sql.ResultSet;

public final class SQLInteraction {

	static final int customerRole = 1;
	static final int bankerRole = 2;

	String hostName;
	String dbName;
	String user;
	String password;
	String port;
	Connection conn = null;
	String state;

	public SQLInteraction(String hostname, String user, String password, String port, String dbName) {
		this.hostName = hostname;
		this.user = user;
		this.password = password;
		this.port = port;
		this.dbName = dbName;
		this.state = "unconnected";

		try {
			conn = DriverManager.getConnection(
					"jdbc:mysql://" + hostname + "/" + dbName + "?user=" + user + "&password=" + password);
			System.out.println("Database connection OP");
			this.state = "connected";

		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("VendorError: " + e.getErrorCode());
		}
	}

	public ResultSet executeQuery(String sqlQuery) {
		ResultSet rs = null;
		try {
			Statement stm = conn.createStatement();
			System.out.println(sqlQuery);
			rs = stm.executeQuery(sqlQuery);
		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("VendorError: " + e.getErrorCode());
		}
		return rs;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	public User connectUser(String userName, String password) {
		int role = -1;
		int userId = -1;
		ResultSet rs = null;
		User user = null;

		password = MD5Encryption.encrypteString(password);
		String requete = "Select * FROM user WHERE mail='" + userName + "' AND password='" + password + "'";

		rs = executeQuery(requete);

		try {
			if (rs.next()) {
				role = rs.getInt("role");
				userId = rs.getInt("id");
				switch (role) {
				case customerRole:
					int councillor_id = getCouncillorIdFromClientId(userId);
					user = new Customer(userId, rs.getString("address"), rs.getString("firstName"),
							rs.getString("lastName"), rs.getString("mail"), rs.getString("password"), customerRole, councillor_id);
					break;
				case bankerRole:
					requete = "SELECT * FROM user WHERE user.id=" + userId;
					Vector<User> clients = getClientsFromBankerId(userId);
					user = new Banker(userId, rs.getString("address"), rs.getString("firstName"),
							rs.getString("lastName"), rs.getString("mail"), rs.getString("password"), bankerRole,
							clients);
					break;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}

	public Vector<User> getClientsFromBankerId(int id) {
		String requete;
		Vector<User> vectClients = null;
		ResultSet rs = null;

		requete = "SELECT user.* FROM user, customer WHERE user.id=customer.user_id AND customer.councillor_id="
				+ id;
		rs = executeQuery(requete);
		try {
			vectClients = new Vector<User>();
			while (rs.next()) {
				vectClients.add(new Customer(rs.getInt("id"), rs.getString("address"), rs.getString("firstName"),
						rs.getString("lastName"), rs.getString("mail"), rs.getString("password"), customerRole,
						id));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return vectClients;
	}

	public int getCouncillorIdFromClientId(int id) {
		ResultSet rs = null;
		int councillor_id = -1;
		String requete = "SELECT councillor_id FROM customer WHERE user_id=" + id;

		rs = executeQuery(requete);
		try {
			if (rs.next()) {
				councillor_id = rs.getInt("councillor_id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return councillor_id;
	}
	
	/**
	 * Execute INSERT and UPDATE querys
	 * @param requete
	 * @return 1 for updated, -1 for failures
	 */
	public int executeUpdate(String requete) {
		int retour=-1;
		try {
			System.out.println(requete);
			retour= conn.createStatement().executeUpdate(requete);
		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("VendorError: " + e.getErrorCode());
		}
		return retour;
	}

}
