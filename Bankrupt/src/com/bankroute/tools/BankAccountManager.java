package com.bankroute.tools;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com.bankroute.bankaccount.BankAccount;
import com.bankroute.bankaccount.CurrentAccount;
import com.bankroute.bankaccount.SavingAccount;
import com.bankroute.datatools.SQLInteraction;
import com.bankroute.user.User;

public class BankAccountManager {
	private  SQLInteraction sqlInteraction;
	
	public BankAccountManager(SQLInteraction sqlInteraction){
		this.sqlInteraction=sqlInteraction;
	}
	
	public Vector<BankAccount> getUserAccounts(User user){
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
	
}
