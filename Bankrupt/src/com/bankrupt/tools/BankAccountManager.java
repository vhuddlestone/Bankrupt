package com.bankrupt.tools;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com.bankrupt.bankaccount.BankAccount;
import com.bankrupt.bankaccount.CurrentAccount;
import com.bankrupt.bankaccount.SavingAccount;
import com.bankrupt.datatools.SQLInteraction;
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
	public User deleteAccount(User usrToDelete, SQLInteraction sqlInteraction) {
		// TODO Auto-generated method stub
		return null;
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

	@Override
	public Vector<User> getUser(int role, SQLInteraction sqlInteraction) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector<User> getClientsFromBankerId(int id, SQLInteraction sqlInteraction) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getCouncillorIdFromClientId(int id, SQLInteraction sqlInteraction) {
		// TODO Auto-generated method stub
		return 0;
	}
}
