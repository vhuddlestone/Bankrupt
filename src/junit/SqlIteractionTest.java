package junit;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.ResultSet;

import org.junit.jupiter.api.Test;

import com.bankroute.datatools.SQLInteraction;

class SqlIteractionTest {

	SQLInteraction sqlInteractionTest = null;

	@Test
	void testSQLInteraction() {

		String hostName = "localhost";
		String dbName = "bankrupt";
		String user = "root";
		String password = "";
		String port = "";

		sqlInteractionTest = new SQLInteraction(hostName, user, password, port, dbName);

		assertEquals("connected", sqlInteractionTest.getState());
	}

	@Test
	void testExecuteQuery() {
		String hostName = "localhost";
		String dbName = "bankrupt";
		String user = "root";
		String password = "";
		String port = "";

		sqlInteractionTest = new SQLInteraction(hostName, user, password, port, dbName);

		assertEquals("connected", sqlInteractionTest.getState());
		String sqlQuery = "SELECT * FROM USER";
		ResultSet rsTest = null;
		rsTest = sqlInteractionTest.executeQuery(sqlQuery);
		assertNotNull(rsTest);
	}

}
