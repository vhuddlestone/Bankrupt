package frames;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.bankrupt.datatools.SQLInteraction;
import com.bankrupt.user.Banker;
import com.bankrupt.user.Customer;
import com.bankrupt.user.User;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;



public class UserInterfaceFrame extends JFrame {

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

	//int id, String address, String firstName,String lastName, String mail, String password, int role, SQLInteraction sqlInteraction, int councillor
	
	public UserInterfaceFrame(User userConnected, SQLInteraction sqlInteraction) {
		setResizable(false);
		if (userConnected != null) {
			this.firstName = userConnected.getFirstName();
			this.lastName = userConnected.getLastName();
			this.address = userConnected.getAddress();
			this.mail = userConnected.getMail();
			this.password = userConnected.getPassword();
			this.sqlInteraction=sqlInteraction;
			this.currentUser = userConnected;
			fullName = firstName + " " + lastName;
		}
		
		initComponentsUser();
		
		switch(userConnected.getRole()) {
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
		
		helloLabel.setText("Bonjour, " + fullName);
		helloLabel.repaint();
		helloLabel.setHorizontalAlignment(SwingConstants.CENTER);
		helloLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		
		roleLabel.setText("Client");
		
		JButton createBankAccountButton = new JButton("Ouvrir un compte banquaire");
		createBankAccountButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CreateBankAccountFrame frame = new CreateBankAccountFrame(currentUser, sqlInteraction);
				frame.setVisible(true);
			}
		});
		
		JButton checkBankAccountButton = new JButton("Consulter un compte banquaire");
		
		JButton externalTransfertButton = new JButton("Effectuer un transfert vers un compte client");
		externalTransfertButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MakeOperationFrame frame = new MakeOperationFrame(currentUser,sqlInteraction);
				frame.setVisible(true);
			}
		});
		
		JButton btnVirerDeLargent = new JButton("Virer de l'argent sur un compte interne");
		btnVirerDeLargent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JButton btnSupprimerLeCompte = new JButton("Supprimer le compte client");
		btnSupprimerLeCompte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JButton editCurrentAccount = new JButton("Modifier votre compte client");
		editCurrentAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				UserDetailsFrame frame = new UserDetailsFrame(sqlInteraction,currentUser, 0);
				frame.setVisible(true);
			}
		});
		
		JButton disconnectButton = new JButton("D\u00E9connexion");
		disconnectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				LoginFrame frame = new LoginFrame(sqlInteraction);
				frame.setVisible(true);
			}
		});
		
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
							.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
								.addComponent(roleLabel, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, 327, Short.MAX_VALUE)
								.addComponent(disconnectButton, GroupLayout.PREFERRED_SIZE, 141, GroupLayout.PREFERRED_SIZE)
								.addContainerGap())
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
									.addComponent(createBankAccountButton, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(checkBankAccountButton, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(externalTransfertButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(btnVirerDeLargent, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(btnSupprimerLeCompte, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(editCurrentAccount, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addGap(158)))
						.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
							.addComponent(helloLabel, GroupLayout.PREFERRED_SIZE, 448, GroupLayout.PREFERRED_SIZE)
							.addGap(55))))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(helloLabel, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addGap(94)
					.addComponent(createBankAccountButton)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(checkBankAccountButton)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(externalTransfertButton)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnVirerDeLargent)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnSupprimerLeCompte)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(editCurrentAccount)
					.addPreferredGap(ComponentPlacement.RELATED, 82, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(roleLabel)
						.addComponent(disconnectButton))
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
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
		createBankAccountButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CreateBankAccountFrame frame = new CreateBankAccountFrame(currentUser, sqlInteraction);
				frame.setVisible(true);
			}
		});
		
		JButton checkBankAccountButton = new JButton("Consulter un compte banquaire");
		checkBankAccountButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		JButton externalTransfertButton = new JButton("Effectuer un transfert vers un compte client");
		externalTransfertButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		JButton btnVirerDeLargent = new JButton("Virer de l'argent sur un compte interne");
		btnVirerDeLargent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JButton btnSupprimerLeCompte = new JButton("Supprimer le compte d'un client");
		btnSupprimerLeCompte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JButton showCustomerListButton = new JButton("Consulter la liste de vos clients");
		showCustomerListButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserViewFrame frame = new UserViewFrame(currentUser, sqlInteraction);
				frame.setVisible(true);
			}
		});
		
		JButton createCustomerButton = new JButton("Crï¿½er un compte client");
		createCustomerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				UserDetailsFrame frame = new UserDetailsFrame(sqlInteraction);
				frame.setVisible(true);
			}
		});
		
		JButton disconnectButton = new JButton("D\u00E9connexion");
		disconnectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				LoginFrame frame = new LoginFrame(sqlInteraction);
				frame.setVisible(true);
			}
		});
		
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(roleLabel, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 363, Short.MAX_VALUE)
					.addComponent(disconnectButton)
					.addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(168)
					.addComponent(btnSupprimerLeCompte, GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
					.addGap(158))
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addGap(168)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(createCustomerButton, GroupLayout.PREFERRED_SIZE, 243, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(showCustomerListButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(158))))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(168)
					.addComponent(btnVirerDeLargent, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(158))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(168)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(createBankAccountButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
						.addComponent(checkBankAccountButton, GroupLayout.DEFAULT_SIZE, 243, Short.MAX_VALUE)
						.addComponent(externalTransfertButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(158))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(65, Short.MAX_VALUE)
					.addComponent(helloLabel, GroupLayout.PREFERRED_SIZE, 448, GroupLayout.PREFERRED_SIZE)
					.addGap(56))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(helloLabel, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addGap(94)
					.addComponent(createBankAccountButton)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(checkBankAccountButton)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(externalTransfertButton)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnVirerDeLargent)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnSupprimerLeCompte)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(showCustomerListButton)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(createCustomerButton)
					.addPreferredGap(ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(roleLabel)
						.addComponent(disconnectButton))
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}
}