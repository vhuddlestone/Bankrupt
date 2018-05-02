package com.bankrupt.tools;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com.bankrupt.bankaccount.BankAccount;
import com.bankrupt.bankaccount.CurrentAccount;
import com.bankrupt.bankaccount.SavingAccount;
import com.bankrupt.datatools.MD5Encryption;
import com.bankrupt.datatools.SQLInteraction;
import com.bankrupt.user.Banker;
import com.bankrupt.user.Customer;
import com.bankrupt.user.User;

public class CustomerAccountManagement implements AccountManagement {

	static final int bankerRole =2;
	static final int customerRole = 1;
	static final int adminRole = 3;
	
	public CustomerAccountManagement(){
		
	}
	
	public User findUser(int userId, int role, SQLInteraction sqlInteraction) {
		User userFound = null;
		Vector<User> vectUser = getUser(role,sqlInteraction);
		for(User u : vectUser) {
			if(u.getId() == userId)
			{
				System.out.println("findUser client: trouve");
				userFound = u;
			}
		}
		return userFound;
	}
	
	@Override
	public Vector<User> getUser(int role, SQLInteraction sqlInteraction){
		Vector<User> vectUser=null;
		String requete = null;
		
		switch(role) {
		case Values.customerRole:	
			requete="SELECT * FROM user WHERE role="+Values.customerRole;
			break;
		case Values.bankerRole:
			requete="SELECT * FROM user WHERE role="+Values.bankerRole;
			break;
		}

		ResultSet rs=sqlInteraction.executeQuery(requete);
		
		try {
			vectUser= new Vector<User>();
			while(rs.next()){
				int userId=rs.getInt("id");
				switch(role) {
				case Values.customerRole:
					int councillor_id=getCouncillorIdFromClientId(userId, sqlInteraction);
					vectUser.add(new Customer(rs.getInt("id"), rs.getString("address"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("mail"), rs.getString("password"),Values.customerRole, councillor_id));
					break;
				case Values.bankerRole:
					System.out.println("getUser: cas banquier");
					Vector<User> customers = getClientsFromBankerId(userId, sqlInteraction);
					vectUser.add(new Banker(userId, rs.getString("address"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("mail"), rs.getString("password"), Values.bankerRole, customers));
				break;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vectUser;
	}

	
	private int genBankAccountID(SQLInteraction sqlInteraction)
	{
		String requete = "SELECT MAX(number) FROM account";
		try {
			ResultSet rs=sqlInteraction.executeQuery(requete);
			rs.next();
			int accountNumber = rs.getInt(1);
			return accountNumber+1;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}
	
	private int genUserID(SQLInteraction sqlInteraction)
	{
		String requete = "SELECT MAX(id) FROM user";
		try {
			ResultSet rs=sqlInteraction.executeQuery(requete);
			rs.next();
			int userId = rs.getInt(1);
			return userId+1;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
		
	}
	
	private boolean checkAccountIfExist(SQLInteraction sqlInteraction,User user,int account_type,int saving_type) throws SQLException
	{
		
		String requete = "";
		ResultSet resultSet = null;
		int userId = user.getId();
		
		if(account_type == 1)
			requete = "SELECT * FROM account WHERE customer_id="+userId+" AND type="+account_type;
		
		else {
			switch(saving_type) {
			case 1:
				requete = "SELECT A.number, S.account_number FROM account A LEFT JOIN saving S ON A.number = S.account_number WHERE A.customer_id="+userId+" and S.type=\"PEL\"";
				break;
			case 2:
				requete = "SELECT A.number, S.account_number FROM account A LEFT JOIN saving S ON A.number = S.account_number WHERE A.customer_id="+userId+" and S.type=\"LA\"";
				break;
			case 3:
				requete = "SELECT A.number, S.account_number FROM account A LEFT JOIN saving S ON A.number = S.account_number WHERE A.customer_id="+userId+" and S.type=\"AV\"";
				break;
			}
		}
		
		resultSet = sqlInteraction.executeQuery(requete);
		
		resultSet.last();
		
		System.out.println("Requete du check account if exist: " + requete + "\nrow value: " + resultSet.getRow());
		
		if(resultSet.getRow() == 0)
			return false;
		else
			return true;
	}
	
	public boolean addBankAccount(User customer, int account_type, int saving_type, SQLInteraction sqlInteraction) throws SQLException {
		
		int accountNumber = genBankAccountID(sqlInteraction);
		int customerId = customer.getId();
		String requete = "";
		
		if(customer.getRole() == Values.bankerRole)
			return false;
		
		if(checkAccountIfExist(sqlInteraction,customer,account_type,saving_type))
			return false;
		
		if(accountNumber == -1)
			return false;
		
		requete = "INSERT INTO account(number, balance, customer_id, type) VALUES ("+accountNumber+","+0+","+customerId+","+account_type+")";
		int rs = sqlInteraction.executeUpdate(requete);
		if(rs == 0)
			return false;
		
		if(account_type == Values.customerRole)
		{
			requete = "INSERT INTO current(account_number, credit_card_number, authorized_overdraft) VALUES ("+accountNumber+","+0+","+0+")";
		} else {
			switch(saving_type) {
			case 1:
				requete = "INSERT INTO saving(account_number, interest_rate, type) VALUES ("+accountNumber+","+1+",\"PEL\")";
				break;
			case 2:
				requete = "INSERT INTO saving(account_number, interest_rate, type) VALUES ("+accountNumber+","+1+",\"LA\")";
				break;
			case 3:
				requete = "INSERT INTO saving(account_number, interest_rate, type) VALUES ("+accountNumber+","+1+",\"AV\")";
				break;
			}
		}
		
		rs = sqlInteraction.executeUpdate(requete);
		
		if(rs == 0)
			return false;
		else
			return true;
	}

		public void deleteAccount(User currentUser, SQLInteraction sqlInteraction) {
		
		BankAccount currentBankAccount = currentUser.operationManagement.findBankAccount(currentUser.getId(),1,"0",sqlInteraction);
		BankAccount savingBankAccount1 = currentUser.operationManagement.findBankAccount(currentUser.getId(),2,"PEL",sqlInteraction);
		BankAccount savingBankAccount2 = currentUser.operationManagement.findBankAccount(currentUser.getId(),2,"AV",sqlInteraction);
		BankAccount savingBankAccount3 = currentUser.operationManagement.findBankAccount(currentUser.getId(),2,"LA",sqlInteraction);
		
		Vector<BankAccount> bankAccountList = new Vector<BankAccount>();
		bankAccountList.add(savingBankAccount1);
		bankAccountList.add(savingBankAccount2);
		bankAccountList.add(savingBankAccount3);
		
		if(currentBankAccount != null) {
			String requete = "DELETE from current where account_number=" + currentBankAccount.getAccountNumber();
			int id = sqlInteraction.executeUpdate(requete);
			requete = "DELETE from account where number=" + currentBankAccount.getAccountNumber();
			id = sqlInteraction.executeUpdate(requete);	
		}
		
		for(BankAccount b : bankAccountList) {
			if(b != null) {
				String requete = "DELETE from saving where account_number=" + b.getAccountNumber();
				int id = sqlInteraction.executeUpdate(requete);
				requete = "DELETE from account where number=" + b.getAccountNumber();
				id = sqlInteraction.executeUpdate(requete);	
			}
		}
		
		String requete = "DELETE from user where id=" + currentUser.getId();
		int id = sqlInteraction.executeUpdate(requete);
	}

	@Override
	public Vector<User> getClientsFromBankerId(int id, SQLInteraction sqlInteraction) {
		String requete;
		Vector<User> vectClients = null;
		ResultSet rs= null;
		
		requete="SELECT user.* FROM user, customer WHERE user.id=councillor.user_id AND cuncillor.councillor_id="+id;
		rs= sqlInteraction.executeQuery(requete);
		try {
			vectClients=new Vector<User>();
			while(rs.next()) {
				vectClients.add(new Customer(rs.getInt("id"), rs.getString("address"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("mail"), rs.getString("password"),customerRole,id));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return vectClients;
	}

	@Override
	public int getCouncillorIdFromClientId(int id, SQLInteraction sqlInteraction) {
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
	public String deleteUser(int id, SQLInteraction sqlInteraction) {
		String errorMessage="";
		ResultSet rs= null;
		String requete;
		
		// first checking if accounts exists for this user
		requete= "SELECT count(acc	ount.number) as NB FROM account, user WHERE user.id=account.customer_id";
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
	public void addUser(String firstName, String lastName, String mail, String address, String password, int role, int councillorId,SQLInteraction sqlInteraction) {
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
		// TODO voir pour vÃ©rifier comment s'est passÃ© un insert dans la base donnÃ©es. voir si le rs contient la linge
	}
	
	public int editUser(int userId, String firstName, String lastName, String mail, String address, String password, int role, SQLInteraction sqlInteraction) {
		int retour;
		String requete = "Update user SET firstname='"+firstName+"', lastname='"+lastName+"', mail='"+mail+"', address='"+address+"', password='"+password+"', role="+role+" WHERE id="+userId;
		retour = sqlInteraction.executeUpdate(requete);
		return retour;
	}

	@Override
	public Vector<BankAccount> getUserAccounts(User user, SQLInteraction sqlInteraction){
		Vector<BankAccount> vectAccounts = null;
		
		int userId=user.getId();
		String requete = "SELECT account.number, saving.interest_rate, saving.type as savingType, account.balance, " + 
				"account.type, current.authorized_overdraft, current.credit_card_number FROM account " + 
				"LEFT JOIN current ON account.number=current.account_number " + 
				"LEFT JOIN saving ON saving.account_number=account.number " + 
				"WHERE account.customer_id="+userId;
		
		ResultSet rs=sqlInteraction.executeQuery(requete);
		
		if(rs != null) {
			try {
				vectAccounts=new Vector<BankAccount>();
				while(rs.next()) {
					int type=rs.getInt("type");
					int number= rs.getInt("number");
					double balance=rs.getDouble("balance");
					switch(type) {
					case Values.currentAccountType:
						double creditCardNumber= rs.getDouble("credit_card_number");
						int authorizedOverdraft= rs.getInt("authorized_overdraft");
						vectAccounts.add(new CurrentAccount(balance, number, userId, type, creditCardNumber, authorizedOverdraft));
						break;
					case Values.savingAccountType:
						String savingType= rs.getString("savingType");
						float interestRate=rs.getFloat("interest_rate");
						vectAccounts.add(new SavingAccount(balance, number, userId, type, savingType, interestRate));
						break;
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return vectAccounts;
	}

	@Override
	public BankAccount deleteBankAccount(BankAccount bankAccountToDelete, SQLInteraction sqlInteraction) {
		// TODO Auto-generated method stub
		return null;
	}
}