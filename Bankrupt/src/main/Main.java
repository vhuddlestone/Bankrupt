package main;
import java.util.Vector;

import com.bankroute.datatools.*;
import com.bankroute.tools.*;
import com.bankroute.user.Customer;
import com.bankroute.user.User;

import frames.LoginFrame;
import frames.UserViewFrame;

public class Main {

	public static void main(String[] args) {
		String hostName = "localhost";
		String dbName = "bankrupt";
		String user = "root";
		String password = "";
		String port = "";
		System.out.println(MD5Encryption.encrypteString("test"));
		SQLInteraction sqlInteraction= new SQLInteraction(hostName, user, password, port, dbName);	
		System.out.println("pwd:");
		System.out.println("pwd:"+MD5Encryption.encrypteString("test"));
		LoginFrame loginFrame= new LoginFrame(sqlInteraction);
		loginFrame.setVisible(true);
	}

}
