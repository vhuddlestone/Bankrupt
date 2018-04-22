package main;
import java.util.Vector;

import com.bankroute.datatools.*;
import com.bankroute.tools.*;
import com.bankroute.user.Customer;
import com.bankroute.user.User;

import frames.LoginFrame;
import frames.UserInterfaceFrame;
import frames.UserViewFrame;

public class Main {

	public static void main(String[] args) {
		String hostName = "sql7.freesqldatabase.com";
		String dbName = "sql7233751";
		String user = "sql7233751";
		String password = "adizMbM48f";
		String port = "3306";
		System.out.println(MD5Encryption.encrypteString("test"));
		SQLInteraction sqlInteraction= new SQLInteraction(hostName, user, password, port, dbName);	
		System.out.println("pwd:");
		System.out.println("pwd:"+MD5Encryption.encrypteString("test"));
		//LoginFrame loginFrame= new LoginFrame(sqlInteraction);
		UserInterfaceFrame userInterfaceFrame=new UserInterfaceFrame(sqlInteraction.connectUser("banquier@mail.fr", "test"), sqlInteraction);
		userInterfaceFrame.setVisible(true);
	}

}
