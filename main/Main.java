package main;
import java.util.Vector;

import com.bankrupt.bankaccount.BankAccount;
import com.bankrupt.datatools.*;
import com.bankrupt.tools.*;
import com.bankrupt.user.Customer;
import com.bankrupt.user.User;

import frames.LoginFrame;
import frames.UserViewFrame;
import com.bankrupt.tools.*;

public class Main {

	public static void main(String[] args) {
		final CustomerOperation customerOperations = new CustomerOperation();
		String hostName = "localhost";
		String dbName = "Bankrupt";
		String user = "root";
		String password = "";
		String port = "3306";
		SQLInteraction sqlInteraction= new SQLInteraction(hostName, user, password, port, dbName);	
		LoginFrame loginFrame= new LoginFrame(sqlInteraction);
		loginFrame.setVisible(true);
		
	}

}