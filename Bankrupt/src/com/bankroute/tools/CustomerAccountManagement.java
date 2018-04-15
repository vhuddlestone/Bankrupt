package com.bankroute.tools;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com.bankroute.bankaccount.BankAccount;
import com.bankroute.datatools.SQLInteraction;
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
		String requete = "SELECT * FROM USER";
		ResultSet rs=sqlInteraction.executeQuery(requete);
		
		try {
			vectUser= new Vector<User>();
			while(rs.next()){
				switch(role) {
				case customerRole:
					vectUser.add(new Customer(rs.getInt("id"), rs.getString("address"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("mail"), rs.getString("password")));
					break;
				case bankerRole:
					vectUser.add(new Banker(rs.getInt("id"), rs.getString("address"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("mail"), rs.getString("password")));
				break;
				case adminRole:
					vectUser.add(new Banker(rs.getInt("id"), rs.getString("address"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("mail"), rs.getString("password")));
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BankAccount createBankAccount(int customerID, int accountType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BankAccount deleteBankAccount(BankAccount bankAccountToDelete) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User deleteAccount(User usrToDelete) {
		// TODO Auto-generated method stub
		return null;
	}




}
