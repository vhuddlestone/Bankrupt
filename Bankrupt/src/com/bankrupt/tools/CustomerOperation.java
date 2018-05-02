package com.bankrupt.tools;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import java.io.*;

import com.bankrupt.bankaccount.BankAccount;
import com.bankrupt.bankaccount.CurrentAccount;
import com.bankrupt.bankaccount.Operation;
import com.bankrupt.bankaccount.SavingAccount;
import com.bankrupt.datatools.SQLInteraction;
import com.bankrupt.user.Banker;
import com.bankrupt.user.Customer;
import com.bankrupt.user.User;

public class CustomerOperation implements OperationManagement {
	
	Vector<BankAccount> vectBankAccount=null;
	private static final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	Date date = new Date();
	
	public CustomerOperation(){
		
	}
	
	public BankAccount findBankAccount(int accountNumber, SQLInteraction sqlInteraction)
	{
		Vector<BankAccount> bankAccountList = getBankAccount(sqlInteraction);
		BankAccount bankAccountFound = null;
		
		for(BankAccount b : bankAccountList)
		{
			if(b.getAccountNumber() == accountNumber)
				bankAccountFound = b;
		}
		
		return bankAccountFound;
	}


	public BankAccount findBankAccount(int customerID, int accountType, int savingType ,SQLInteraction sqlInteraction)
	{
		Vector<BankAccount> bankAccountList = getBankAccount(sqlInteraction,accountType);
		System.out.println("Debug findBankAccount - ID:  " + customerID + " Account Type: " + accountType);
		BankAccount bankAccountFound = null;
		
		if(accountType == 1)
		{
			for(BankAccount b : bankAccountList)
			{
				if(b.getCustomerID() == customerID)
					bankAccountFound = b;
			}
		} else {
			switch(savingType) {
			case 1:
				for(BankAccount b : bankAccountList)
				{
					if(b.getCustomerID() == customerID && b.getSavingType().equals("PEL"))
						bankAccountFound = b;
				}
				break;
			case 2:
				for(BankAccount b : bankAccountList)
				{
					if(b.getCustomerID() == customerID && b.getSavingType().equals("LA"))
						bankAccountFound = b;
				}
				break;
			case 3:
				for(BankAccount b : bankAccountList)
				{
					if(b.getCustomerID() == customerID && b.getSavingType().equals("AV"))
						bankAccountFound = b;
				}
				break;
			}
			
		}
		
		return bankAccountFound;
	}
	
		
	public boolean makeExternalOperation(User currentUser, double amount, int numberAccountSender, int numberAccountReceiver,SQLInteraction sqlInteraction) {
		try{
			if(amount<0)
				return false;
			
			BankAccount bankAccount = findBankAccount(currentUser.getId(),1,0,sqlInteraction);	
			bankAccount.balance -= amount;
			
			if(bankAccount.balance<0)
				return false;
			
			
			String requete = "INSERT INTO operation (sender, receiver, amount, date) VALUES ("+numberAccountSender+","
					+numberAccountReceiver+","+Math.abs(amount)+",'"+dateFormat.format(date)+"')";
			
			int rs=sqlInteraction.executeUpdate(requete);
			if(rs == 0)
				return false;
			
			requete = "UPDATE account SET balance=" + bankAccount.getBalance() + " WHERE number=" + numberAccountSender;
			rs=sqlInteraction.executeUpdate(requete);
			if(rs == 0)
				return false;
			else
				return true;
			}
		catch(Exception e) {
			System.out.println(e.toString());
			return false;
		}
	}	
	
	public boolean makeInternalOperation(User currentUser, double amount, int numAccountSender, int senderSavingType, int senderAccountType, int numAccountReceiver, int receiverAccountType, int receiverSavingType, SQLInteraction sqlInteraction) {
		try{
			if(amount<0)
				return false;
		
			BankAccount bankAccountSender = findBankAccount(currentUser.getId(),senderAccountType,senderSavingType,sqlInteraction);	
			System.out.println("Balance sender avant: " + bankAccountSender.getBalance());
			bankAccountSender.setBalance(bankAccountSender.getBalance()+(-amount));
			System.out.println("Balance sender apres: " + bankAccountSender.getBalance());
			
			if(bankAccountSender.balance<0)
				return false;
			
			BankAccount bankAccountReceiver = findBankAccount(currentUser.getId(),receiverAccountType,receiverSavingType,sqlInteraction);	
			System.out.println("Balance receiver avant: " + bankAccountReceiver.getBalance());
			bankAccountReceiver.setBalance(bankAccountReceiver.getBalance()+amount);
			System.out.println("Balance receiver apres: " + bankAccountReceiver.getBalance());
			
			String requete = "INSERT INTO operation (sender, receiver, amount, date) VALUES ("+numAccountSender+","
					+numAccountReceiver+","+Math.abs(amount)+",'"+dateFormat.format(date)+"')";
			
			int rs=sqlInteraction.executeUpdate(requete);
			if(rs == 0)
				return false;
			
			requete = "UPDATE account SET balance=" + bankAccountSender.getBalance() + " WHERE number=" + numAccountSender;
			rs=sqlInteraction.executeUpdate(requete);
			if(rs == 0)
				return false;
			
			requete = "UPDATE account SET balance=" + bankAccountReceiver.getBalance() + " WHERE number=" + numAccountReceiver;
			rs=sqlInteraction.executeUpdate(requete);
			if(rs == 0)
				return false;
			else
				return true;
			}
		catch(Exception e) {
			System.out.println(e.toString());
			return false;
		}
		
	}
	
	/*public boolean makeInternalOperation(User currentUser, double amount, int numberAccountSender, int numberAccountReceiver, SQLInteraction sqlInteraction) {
		try{
			if(amount<0)
				return false;
		
			BankAccount bankAccountSender = findBankAccount(currentUser.getId(),1,0,sqlInteraction);	
			bankAccountSender.balance += -amount;
			
			if(bankAccountSender.balance<0)
				return false;
			
			BankAccount bankAccountReceiver = findBankAccount(currentUser.getId(),1,0,sqlInteraction);	
			bankAccountReceiver.balance += amount;
			
			String requete = "INSERT INTO operation (sender, receiver, amount, date) VALUES ("+numberAccountSender+","
					+numberAccountReceiver+","+Math.abs(amount)+",'"+dateFormat.format(date)+"')";
			
			int rs=sqlInteraction.executeUpdate(requete);
			if(rs == 0)
				return false;
			
			requete = "UPDATE account SET balance=" + bankAccountSender.getBalance() + " WHERE number=" + numberAccountSender;
			rs=sqlInteraction.executeUpdate(requete);
			if(rs == 0)
				return false;
			
			requete = "UPDATE account SET balance=" + bankAccountReceiver.getBalance() + " WHERE number=" + numberAccountReceiver;
			rs=sqlInteraction.executeUpdate(requete);
			if(rs == 0)
				return false;
			else
				return true;
			}
		catch(Exception e) {
			System.out.println(e.toString());
			return false;
		}
	}*/
	
	public Vector<BankAccount> getBankAccount(SQLInteraction sqlInteraction){
		Vector<BankAccount> vectBankAccount=null;
		vectBankAccount= new Vector<BankAccount>();
		
		
		try {
				String requeteCurrent = "SELECT A.number, A.balance, A.customer_id, A.type, C.credit_card_number, C.authorized_overdraft FROM account A LEFT JOIN current C ON A.number = C.account_number WHERE A.type=1";
				ResultSet rsCurrent = sqlInteraction.executeQuery(requeteCurrent);
					
				if(rsCurrent == null)
					return null;
					
				while(rsCurrent.next()) {
					int accountNumber = rsCurrent.getInt(1);
					double balance = rsCurrent.getDouble(2);
					int customerID = rsCurrent.getInt(3);
					int accountType = rsCurrent.getInt(4);
					double creditCardNumber = rsCurrent.getDouble(5);;
					int authorizedOverdraft = rsCurrent.getInt(6);
					vectBankAccount.add(new CurrentAccount(balance,accountNumber,customerID,accountType,creditCardNumber,authorizedOverdraft));
				}
					
				String requeteSaving = "SELECT A.number, A.balance, A.customer_id, A.type, S.interest_rate, S.type FROM account A LEFT JOIN saving S ON A.number = S.account_number WHERE A.type=2";
				ResultSet rsSaving = sqlInteraction.executeQuery(requeteSaving);
					
				if(rsSaving == null)
					return null;
					
				while(rsSaving.next()) {
					int accountNumber = rsSaving.getInt(1);
					double balance = rsSaving.getDouble(2);
					int customerID = rsSaving.getInt(3);
					int accountType = rsSaving.getInt(4);
					float interestRate = rsSaving.getFloat(5);
					String savingType = rsSaving.getString(6);
					vectBankAccount.add(new SavingAccount(balance,accountNumber,customerID,accountType,savingType,interestRate));
				}
					
		}
		
		 catch (Exception e) {
			e.printStackTrace();
		}
		
		return vectBankAccount;
	}
	
	public Vector<BankAccount> getBankAccount(SQLInteraction sqlInteraction, int type){
		Vector<BankAccount> vectBankAccount=null;
		vectBankAccount= new Vector<BankAccount>();
		
		
		try {
			switch(type) {
				case 1:
					String requeteCurrent = "SELECT A.number, A.balance, A.customer_id, A.type, C.credit_card_number, C.authorized_overdraft FROM account A LEFT JOIN current C ON A.number = C.account_number WHERE A.type=1";
					ResultSet rsCurrent = sqlInteraction.executeQuery(requeteCurrent);
					
					if(rsCurrent == null)
						return null;
					
					while(rsCurrent.next()) {
						int accountNumber = rsCurrent.getInt(1);
						double balance = rsCurrent.getDouble(2);
						int customerID = rsCurrent.getInt(3);
						int accountType = rsCurrent.getInt(4);
						double creditCardNumber = rsCurrent.getDouble(5);;
						int authorizedOverdraft = rsCurrent.getInt(6);
						vectBankAccount.add(new CurrentAccount(balance,accountNumber,customerID,accountType,creditCardNumber,authorizedOverdraft));
					}
					
					break;
					
				case 2:
					String requeteSaving = "SELECT A.number, A.balance, A.customer_id, A.type, S.interest_rate, S.type FROM account A LEFT JOIN saving S ON A.number = S.account_number WHERE A.type=2";
					ResultSet rsSaving = sqlInteraction.executeQuery(requeteSaving);
					
					if(rsSaving == null)
						return null;
					
					while(rsSaving.next()) {
						int accountNumber = rsSaving.getInt(1);
						double balance = rsSaving.getDouble(2);
						int customerID = rsSaving.getInt(3);
						int accountType = rsSaving.getInt(4);
						float interestRate = rsSaving.getFloat(5);
						String savingType = rsSaving.getString(6);
						vectBankAccount.add(new SavingAccount(balance,accountNumber,customerID,accountType,savingType,interestRate));
					}
				
					break;
					
			}
		}
		
		 catch (Exception e) {
			e.printStackTrace();
		}
		
		return vectBankAccount;
	}
	
	public Vector<Operation> getOperationsOfAccount(int accountId,SQLInteraction sqlInteraction){
		Vector<Operation> operationsVect=new Vector<Operation>();
		
		String requete="SELECT sender, receiver, amount, date FROM operation WHERE receiver="+accountId+" OR sender="+accountId; 
		System.out.println(requete);
		
		ResultSet result=sqlInteraction.executeQuery(requete);
		
		try {
			while(result.next()) {
				operationsVect.add(new Operation(result.getInt("sender"), result.getInt("receiver"),result.getDouble("amount"),result.getDate("date")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return operationsVect;
	}
	
}	