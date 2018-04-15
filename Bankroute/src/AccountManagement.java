import java.util.Vector;

public interface AccountManagement {
	User createAccount(int type);
	User deleteAccount(int id, Vector<User> liste);
	BankAccount createBankAccount(int type);
	BankAccount deleteBankAccount(int id, int type);
}
