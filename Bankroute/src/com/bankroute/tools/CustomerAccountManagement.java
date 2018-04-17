package com.bankroute.tools;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com.bankroute.bankaccount.BankAccount;
import com.bankroute.datatools.SQLInteraction;
import com.bankroute.user.Banker;
import com.bankroute.user.Customer;
import com.bankroute.user.User;

public class CustomerAccountManagement implements AccountManagement {

	static final int bankerRole =2;
	static final int customerRole = 1;
	static final int adminRole = 3;
	SQLInteraction sqlInteraction=null;
	
	public CustomerAccountManagement(SQLInteraction sqlInteraction){
		this.sqlInteraction=sqlInteraction;
		
	}
	
	@Override
	public Vector<User> getUser(int role){
		Vector<User> vectUser=null;
		String requete = null;
		
		switch(role) {
		case customerRole:
			requete="SELECT * FROM user WHERE role="+customerRole;
			break;
		case adminRole:
			break;
		case bankerRole:
			requete="SELECT * FROM user WHERE role="+bankerRole;
			break;
		}

		ResultSet rs=sqlInteraction.executeQuery(requete);
		
		try {
			vectUser= new Vector<User>();
			while(rs.next()){
				int userId=rs.getInt("id");
				switch(role) {
				case customerRole:
					int councillor_id=getCouncillorIdFromClientId(userId);
					vectUser.add(new Customer(rs.getInt("id"), rs.getString("address"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("mail"), rs.getString("password"),customerRole, sqlInteraction,councillor_id));
					break;
				case bankerRole:
					Vector<User> customers = getClientsFromBankerId(userId);
					vectUser.add(new Banker(userId, rs.getString("address"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("mail"), rs.getString("password"), bankerRole, sqlInteraction, customers));
				break;
				case adminRole:
					//vectUser.add(new Banker(rs.getInt("id"), rs.getString("address"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("mail"), rs.getString("password")));
					break;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vectUser;
	}

	@Override
	public User createAccount(String address, String firstName, String lastName, String mail, String password,
			int role) {
		
		String requete = "INSERT INTO user (id,address, firstName, lastName, mail, password, role) VALUES ('"+address+"','"
				+firstName+"','"+lastName+"','"+mail+"','"+password+"',"+role+")";
		
		ResultSet rs=sqlInteraction.executeQuery(requete);
		return null;
		
	}

	@Override
	public BankAccount createBankAccount(int customerID, int accountType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BankAccount deleteBankAccount(BankAccount bankAccountToDelete) {
		String requete = new String();
		
		if(bankAccountToDelete.accountType == 1)
			 requete = "DELETE FROM current WHERE account_id = " + bankAccountToDelete.accountNumber;
		else
			 requete = "DELETE FROM saving WHERE account_id = " + bankAccountToDelete.accountNumber;
		
		ResultSet rs=sqlInteraction.executeQuery(requete);
		return null;
	}

	@Override
	public User deleteAccount(User usrToDelete) {
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public Vector<User> getClientsFromBankerId(int id) {
		String requete;
		Vector<User> vectClients = null;
		ResultSet rs= null;
		
		requete="SELECT user.* FROM user, customer WHERE user.id=councillor.user_id AND cuncillor.councillor_id="+id;
		rs= sqlInteraction.executeQuery(requete);
		try {
			vectClients=new Vector<User>();
			while(rs.next()) {
				vectClients.add(new Customer(rs.getInt("id"), rs.getString("address"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("mail"), rs.getString("password"),customerRole, sqlInteraction,id));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return vectClients;
	}

	@Override
	public int getCouncillorIdFromClientId(int id) {
		ResultSet rs=null;
		int councillor_id= -1;
		String requete = "SELECT councillor_id FROM customer WHERE user_id="+id;
		
		rs=sqlInteraction.executeQuery(requete);
		try {
			if(rs.next()) {
				councillor_id= rs.getInt("councillor_id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return councillor_id;
	}

}