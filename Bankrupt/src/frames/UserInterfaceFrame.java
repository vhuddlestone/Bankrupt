package frames;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.bankroute.datatools.SQLInteraction;
import com.bankroute.user.Banker;
import com.bankroute.user.Customer;
import com.bankroute.user.User;

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
	

	//int id, String address, String firstName,String lastName, String mail, String password, int role, SQLInteraction sqlInteraction, int councillor
	
	public UserInterfaceFrame(User userConnected) {
		if (userConnected != null) {
			this.firstName = userConnected.getFirstName();
			this.lastName = userConnected.getLastName();
			this.address = userConnected.getAddress();
			this.mail = userConnected.getMail();
			this.password = userConnected.getPassword();
			this.sqlInteraction=userConnected.getSQLInstance();
			fullName = firstName + " " + lastName;
		}
		
		//initComponentsUser();
		switch(userConnected.getRole()) {
		case customerRole:
			initComponentsUser();
			break;
		case adminRole:
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
		
		JButton checkBankAccountButton = new JButton("Consulter un compte banquaire");
		
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
		
		JButton btnSupprimerLeCompte = new JButton("Supprimer le compte client");
		btnSupprimerLeCompte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(roleLabel, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(468, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(65, Short.MAX_VALUE)
					.addComponent(helloLabel, GroupLayout.PREFERRED_SIZE, 448, GroupLayout.PREFERRED_SIZE)
					.addGap(56))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(168, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnSupprimerLeCompte, GroupLayout.PREFERRED_SIZE, 243, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(createBankAccountButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(checkBankAccountButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(externalTransfertButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnVirerDeLargent, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
					.addGap(158))
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
					.addPreferredGap(ComponentPlacement.RELATED, 110, Short.MAX_VALUE)
					.addComponent(roleLabel)
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}

	private void initComponentsBanker() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 595, 458);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		/*Labels init*/
		
		helloLabel.setText("Bonjour, "+fullName);
		helloLabel.setHorizontalAlignment(SwingConstants.CENTER);
		helloLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		
		roleLabel.setText("Banquier");
		
		JButton createBankAccountButton = new JButton("Ouvrir un compte banquaire");
		
		JButton checkBankAccountButton = new JButton("Consulter un compte banquaire");
		
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
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(roleLabel, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(468, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(65, Short.MAX_VALUE)
					.addComponent(helloLabel, GroupLayout.PREFERRED_SIZE, 448, GroupLayout.PREFERRED_SIZE)
					.addGap(56))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(168, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(btnSupprimerLeCompte, GroupLayout.PREFERRED_SIZE, 243, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(createBankAccountButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(checkBankAccountButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(externalTransfertButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnVirerDeLargent, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
					.addGap(158))
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
					.addPreferredGap(ComponentPlacement.RELATED, 110, Short.MAX_VALUE)
					.addComponent(roleLabel)
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	
}
