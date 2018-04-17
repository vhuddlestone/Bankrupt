package com.bankroute.tools;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com.bankroute.bankaccount.BankAccount;
import com.bankroute.datatools.MD5Encryption;
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
	
	/**
	 * Function to delete an user, first checking if the user to delete have bank accounts
	 * @param id
	 * @return errorMessage empy if user deleted, otherwise error message to display to user.
	 */
	public String deleteUser(int id) {
		String errorMessage="";
		ResultSet rs= null;
		String requete;
		
		// first checking if accounts exists for this user
		requete= "SELECT count(account.number) as NB FROM account, user WHERE user.id=account.customer_id";
		rs= sqlInteraction.executeQuery(requete);
		
		/*try {
			if(rs.next()) {
				errorMessage +="USer already have "+ rs.getInt("NB") +"opened accounts";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}*/
		
		if(errorMessage.isEmpty()) {
			rs=null;
			requete= "DELETE FROM user WHERE id="+id;
			int retour = sqlInteraction.executeUpdate(requete);
			if(retour != -1) {
				requete="DELETE FROM customer where councillor_id="+id;
				retour=sqlInteraction.executeUpdate(requete);
				if (retour==-1) {
					errorMessage="\n Dele customer cover failed";
				}
			}else {
				errorMessage+="\n Delete user failed";
			}
		}
		return errorMessage;
	}
	
	/**
	 * Function to add a customer or a user in database
	 * @param firstName
	 * @param lastName
	 * @param mail
	 * @param address
	 * @param password
	 * @param role
	 * @param councillorId (-1 to create user by user, otherwise to create user by banker)
	 */
	public void addUser(String firstName, String lastName, String mail, String address, String password, int role, int councillorId) {
		String requete = "";
		
		password= MD5Encryption.encrypteString(password);
		
		requete = "INSERT INTO user(firstname, lastname, mail, address, password, role) VALUES ('"+firstName+"','"+lastName+"','"+mail+"','"+address+"','"+password+"',"+role+")";
		try {
			int id = sqlInteraction.executeUpdate(requete);
			int userId;
			System.out.println(id);
			if(councillorId!=-1) {
				requete = "SELECT MAX(id) FROM user";
				ResultSet rs=sqlInteraction.executeQuery(requete);
				rs.next();
				userId = rs.getInt(1);
				System.out.println("userId:="+userId);
				requete = "INSERT INTO customer(councillor_id, user_id) VALUES ("+councillorId+","+userId+")";
				id = sqlInteraction.executeUpdate(requete);
				System.out.println(id);
			}
		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("VendorError: " + e.getErrorCode());
		}
		// TODO voir pour vérifier comment s'est passé un insert dans la base données. voir si le rs contient la linge
	}
	
	public int editUser(int userId, String firstName, String lastName, String mail, String address, String password, int role) {
		int retour;
		password= MD5Encryption.encrypteString(password);
		String requete = "Update user SET firstname='"+firstName+"', lastname='"+lastName+"', mail='"+mail+"', address='"+address+"', password='"+password+"', role="+role+" WHERE id="+userId;
		retour = sqlInteraction.executeUpdate(requete);
		return retour;
	}

}
