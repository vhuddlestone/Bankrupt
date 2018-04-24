package com.bankroute.tools;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import java.io.*;

import com.bankroute.bankaccount.BankAccount;
import com.bankroute.bankaccount.CurrentAccount;
import com.bankroute.bankaccount.Operation;
import com.bankroute.datatools.SQLInteraction;
import com.bankroute.user.Banker;
import com.bankroute.user.Customer;
import com.bankroute.user.User;

public class CustomerOperation implements OperationManagement {
	
	Vector<BankAccount> vectBankAccount=null;
	SQLInteraction sqlInteraction;
	private static final DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	Date date = new Date();
	
	public CustomerOperation(SQLInteraction sqlInteraction){
		this.sqlInteraction=sqlInteraction;
	}

	//TODO AJOUTER LES NOUVEAUX ARGUMENTS DU CONSTRUCTEUR POUR UTILISER
	/*BankAccount findBankAccount(int customerID, int accountType)
	{
		Vector<BankAccount> bankAccountList = getBankAccount(sqlInteraction,accountType);
		for(BankAccount b : bankAccountList)
		{
			if(b.customerID == customerID && b.accountType == accountType)
				return b;
		}
		return null;
	}*/
	
		
	/*ublic boolean makeOperation(double amount, BankAccount bankAccountSender, int numberAccountReceiver) {
		try{
			vectBankAccount = getBankAccount(sqlInteraction, bankAccountSender.accountType);	
			BankAccount bk = findBankAccount(bankAccountSender.accountNumber, bankAccountSender.accountType);
			bk.balance += amount;
			
			String requete = "INSERT INTO OPERATION (SENDER, RECEIVER, AMOUNT, DATE) VALUES ("+bankAccountSender.accountNumber+","
					+numberAccountReceiver+","+amount+","+dateFormat.format(date)+")";
			ResultSet rs=sqlInteraction.executeQuery(requete);
			
			requete = "UPDATE BANKACCOUNT SET BALANCE = "+bk.balance+" WHERE ACCOUNTNUMBER = "+bk.accountNumber+"AND ACCOUNTYPE="+bk.accountType;
			rs=sqlInteraction.executeQuery(requete);
			return true;
		}
		catch(Exception e) {
			return false;
		}
	}	*/
	
	public Vector<Operation> getOperationsOfAccount(int accountId){
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

	@Override
	public boolean makeOperation(double amount, BankAccount bankAccountSender, int numberAccountReceiver) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}