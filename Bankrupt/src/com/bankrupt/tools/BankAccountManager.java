package com.bankrupt.tools;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com.bankrupt.bankaccount.BankAccount;
import com.bankrupt.bankaccount.CurrentAccount;
import com.bankrupt.bankaccount.SavingAccount;
import com.bankrupt.datatools.SQLInteraction;
import com.bankrupt.user.Banker;
import com.bankrupt.user.Customer;
import com.bankrupt.user.User;

public class BankAccountManager implements AccountManagement {
	
	public BankAccountManager(){
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
	public void addUser(String firstName, String lastName, String mail, String address, String password, int role,
			int councillorId, SQLInteraction sqlInteraction) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAccount(User currentUser, SQLInteraction sqlInteraction) {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean addBankAccount(User customer, int account_type, int saving_type, SQLInteraction sqlInteraction)
			throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public BankAccount deleteBankAccount(BankAccount bankAccountToDelete, SQLInteraction sqlInteraction) {
		// TODO Auto-generated method stub
		return null;
	}

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

	@Override
	public Vector<User> getClientsFromBankerId(int id, SQLInteraction sqlInteraction) {
		// TODO Auto-generated method stub
		return null;
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

	@Override
	public User findUser(int userId, int role, SQLInteraction sqlInteraction) {
		User userFound = null;
		Vector<User> vectUser = getUser(role,sqlInteraction);
		for(User u : vectUser) {
			if(u.getId() == userId)
				userFound = u;
		}
		return userFound;
	}
}