package junit;
import java.util.Vector;

import com.bankroute.datatools.*;
import com.bankroute.tools.*;
import com.bankroute.user.User;

import frames.LoginFrame;

public class Main {

	public static void main(String[] args) {
		String hostName = "localhost";
		String dbName = "bankrupt";
		String user = "root";
		String password = "";
		String port = "";

		SQLInteraction sqlInteraction= new SQLInteraction(hostName, user, password, port, dbName);	
		System.out.println("pwd:");
		System.out.println("pwd:"+MD5Encryption.encrypteString("test"));
		LoginFrame loginFrame= new LoginFrame(sqlInteraction);
		loginFrame.setVisible(true);;
	}

}
