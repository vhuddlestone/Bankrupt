package com.bankroute.datatools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public final class SQLInteraction {
	String hostName;
	String dbName;
	String user;
	String password;
	String port;
	Connection conn;

	SQLInteraction(String hostname, String user, String password, String port, String dbName) {
		this.hostName = hostname;
		this.user = user;
		this.password = password;
		this.port = port;
		this.dbName = dbName;

		try {
			conn = DriverManager.getConnection(
					"jdbc:mysql://" + hostname + "/" + dbName + "?user=" + user + "&password=" + password);
			System.out.println("Database connection OP");

		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("VendorError: " + e.getErrorCode());
		}
	}

	public ResultSet executeQuery(String sqlQuery) {
		ResultSet rs=null;
		try {
			Statement stm=conn.createStatement();
			rs=stm.executeQuery(sqlQuery);
		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("VendorError: " + e.getErrorCode());
		}
		return rs;
	}
}
