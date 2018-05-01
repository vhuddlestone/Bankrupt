package frames;

import static javax.swing.JOptionPane.WARNING_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.bankrupt.bankaccount.BankAccount;
import com.bankrupt.datatools.SQLInteraction;
import com.bankrupt.user.Banker;
import com.bankrupt.user.Customer;
import com.bankrupt.user.User;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class UserInterfaceFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JLabel helloLabel = new JLabel();
	JLabel roleLabel = new JLabel();

	static final int customerRole = 1;
	static final int bankerRole = 2;
	static final int adminRole = 3;

	private String firstName = "";
	private String lastName = "";
	private String address = "";
	private String mail = "";
	private String password = "";
	private SQLInteraction sqlInteraction;
	private String fullName;
	private User currentUser;

	// int id, String address, String firstName,String lastName, String mail, String
	// password, int role, SQLInteraction sqlInteraction, int councillor

	public UserInterfaceFrame(User userConnected, SQLInteraction sqlInteraction) {
		setResizable(false);
		if (userConnected != null) {
			this.firstName = userConnected.getFirstName();
			this.lastName = userConnected.getLastName();
			this.address = userConnected.getAddress();
			this.mail = userConnected.getMail();
			this.password = userConnected.getPassword();
			this.sqlInteraction = sqlInteraction;
			this.currentUser = userConnected;
			fullName = firstName + " " + lastName;
		}

		initComponentsUser();

		switch (userConnected.getRole()) {
		case customerRole:
			initComponentsUser();
			break;
		case bankerRole:
			initComponentsBanker();
			break;
		}

	}
	
	private void initComponentsUser() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 595, 458);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		/*Labels init*/
		helloLabel.setBounds(81, 18, 448, 30);
		
		helloLabel.setText("Bonjour, " + fullName);
		helloLabel.repaint();
		helloLabel.setHorizontalAlignment(SwingConstants.CENTER);
		helloLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		roleLabel.setBounds(17, 384, 91, 16);
		
		roleLabel.setText("Client");
		
		JButton createBankAccountButton = new JButton("Ouvrir un compte banquaire");
		createBankAccountButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		createBankAccountButton.setBounds(145, 100, 342, 39);
		createBankAccountButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CreateBankAccountFrame frame = new CreateBankAccountFrame(currentUser, sqlInteraction);
				frame.setVisible(true);
			}
		});
		
		JButton checkBankAccountButton = new JButton("Consulter un compte banquaire");
		checkBankAccountButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		checkBankAccountButton.setBounds(145, 140, 342, 39);
		checkBankAccountButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CustomerAccountsFrame frame = new CustomerAccountsFrame(currentUser,sqlInteraction);
				frame.setVisible(true);
			}
		});
		
		JButton externalTransfertButton = new JButton("Effectuer un transfert vers un compte client");
		externalTransfertButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		externalTransfertButton.setBounds(145, 180, 342, 39);
		externalTransfertButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(currentUser.operationManagement.findBankAccount(currentUser.getId(), 1, 0, sqlInteraction) == null)
				{
					showMessageDialog(null, "Vous ne poss\u00E9dez pas de compte courant", "Warning", WARNING_MESSAGE);
					return;
				}
				MakeOperationFrame frame = new MakeOperationFrame(currentUser,sqlInteraction);
				frame.setVisible(true);
			}
		});
		
		JButton btnVirerDeLargent = new JButton("Virer de l'argent sur un compte interne");
		btnVirerDeLargent.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnVirerDeLargent.setBounds(145, 221, 342, 39);
		btnVirerDeLargent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JButton btnSupprimerLeCompte = new JButton("Supprimer le compte client");
		btnSupprimerLeCompte.setBounds(145, 263, 342, 39);
		btnSupprimerLeCompte.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSupprimerLeCompte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int delete = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment supprimer votre compte ?","Warning",JOptionPane.YES_NO_OPTION);
				if(delete == JOptionPane.YES_OPTION) {
					currentUser.accountManagement.deleteAccount(currentUser,sqlInteraction);
					dispose();
					showMessageDialog(null, "Bye bye !", "Warning", WARNING_MESSAGE);
					LoginFrame frame = new LoginFrame(sqlInteraction);
					frame.setVisible(true);
				}
			}	
		});
		
		JButton editCurrentAccount = new JButton("Modifier votre compte client");
		editCurrentAccount.setBounds(145, 303, 342, 39);
		editCurrentAccount.setFont(new Font("Tahoma", Font.PLAIN, 15));
		editCurrentAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				UserDetailsFrame frame = new UserDetailsFrame(sqlInteraction,currentUser, 0);
				frame.setVisible(true);
			}
		});
		
		JButton disconnectButton = new JButton("D\u00E9connexion");
		disconnectButton.setBounds(463, 375, 114, 35);
		disconnectButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		disconnectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				LoginFrame frame = new LoginFrame(sqlInteraction);
				frame.setVisible(true);
			}
		});
		contentPane.setLayout(null);
		contentPane.add(roleLabel);
		contentPane.add(disconnectButton);
		contentPane.add(createBankAccountButton);
		contentPane.add(checkBankAccountButton);
		contentPane.add(externalTransfertButton);
		contentPane.add(btnVirerDeLargent);
		contentPane.add(btnSupprimerLeCompte);
		contentPane.add(editCurrentAccount);
		contentPane.add(helloLabel);
		
		JButton btnChatter = new JButton("Chat");
		btnChatter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ChatBox chatBox= new ChatBox(currentUser);
			}
		});
		btnChatter.setBounds(144, 355, 91, 60);
		contentPane.add(btnChatter);
	}

	private void initComponentsBanker() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 675, 458);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		/*Labels init*/
		
		helloLabel.setText("Bonjour, "+fullName);
		helloLabel.setHorizontalAlignment(SwingConstants.CENTER);
		helloLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		
		roleLabel.setText("Banquier");
		
		JButton createBankAccountButton = new JButton("Ouvrir un compte banquaire");
		createBankAccountButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		createBankAccountButton.setBounds(145, 100, 342, 39);
		createBankAccountButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CreateBankAccountFrame frame = new CreateBankAccountFrame(currentUser, sqlInteraction);
				frame.setVisible(true);
			}
		});
		
		JButton checkBankAccountButton = new JButton("Consulter un compte banquaire");
		checkBankAccountButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		checkBankAccountButton.setBounds(145, 140, 342, 39);
		checkBankAccountButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		JButton externalTransfertButton = new JButton("Effectuer un transfert vers un compte client");
		externalTransfertButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		externalTransfertButton.setBounds(145, 180, 342, 39);
		externalTransfertButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		JButton btnVirerDeLargent = new JButton("Virer de l'argent sur un compte interne");
		btnVirerDeLargent.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnVirerDeLargent.setBounds(145, 220, 342, 39);
		btnVirerDeLargent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JButton btnSupprimerLeCompte = new JButton("Supprimer le compte d'un client");
		btnSupprimerLeCompte.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnSupprimerLeCompte.setBounds(145, 260, 342, 39);
		
		JButton showCustomerListButton = new JButton("Consulter la liste de vos clients");
		showCustomerListButton.setBounds(145, 260, 342, 39);
		showCustomerListButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		showCustomerListButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserViewFrame frame = new UserViewFrame(currentUser, sqlInteraction);
				frame.setVisible(true);
			}
		});
		
		JButton createCustomerButton = new JButton("Créer un compte client");
		createCustomerButton.setBounds(145, 300, 342, 39);
		createCustomerButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		createCustomerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UserDetailsFrame frame = new UserDetailsFrame(sqlInteraction);
				frame.setVisible(true);
			}
		});
		
		JButton disconnectButton = new JButton("D\u00E9connexion");
		disconnectButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		disconnectButton.setBounds(463, 375,150,39);
		disconnectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				LoginFrame frame = new LoginFrame(sqlInteraction);
				frame.setVisible(true);
			}
		});
		
		contentPane.setLayout(null);
		contentPane.add(roleLabel);
		contentPane.add(disconnectButton);
		contentPane.add(createBankAccountButton);
		contentPane.add(checkBankAccountButton);
		contentPane.add(externalTransfertButton);
		contentPane.add(btnVirerDeLargent);
		contentPane.add(btnSupprimerLeCompte);
		contentPane.add(createCustomerButton);
		contentPane.add(helloLabel);
		
		JButton btnChatter = new JButton("Chat");
		btnChatter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ChatBox chatBox= new ChatBox(currentUser);
			}
		});
		btnChatter.setBounds(144, 355, 91, 60);
		contentPane.add(btnChatter);
	}
}