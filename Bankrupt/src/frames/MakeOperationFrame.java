package frames;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.bankrupt.bankaccount.BankAccount;
import com.bankrupt.datatools.*;
import com.bankrupt.tools.*;
import com.bankrupt.user.User;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Generic frame to add/edit a user
 * 
 * @author vhuddlestone
 */
public class MakeOperationFrame extends JFrame {
	
	static final int customerRole = 1;
	static final int bankerRole = 2;
	static final int adminRole = 3;
	
	private SQLInteraction sqlInteraction;
	
	private String firstName = "";
	private String lastName = "";
	private String address = "";
	private String mail = "";
	private String password = "";
	private BankAccount currentBankAccount;

	private JPanel contentPane;
	private JTextField customerAccountInput;
	private JTextField receiverAccountInput;
	private JTextField amountInput;
	private User currentUser;
	
	/**
	 * Create the frame to edit a user
	 * @wbp.parser.constructor
	 */
	public MakeOperationFrame(User currentUser, SQLInteraction sqlInteraction) {
		setResizable(false);
		if (currentUser != null) {
			this.firstName = currentUser.getFirstName();
			this.lastName = currentUser.getLastName();
			this.address = currentUser.getAddress();
			this.mail = currentUser.getMail();
			this.password = currentUser.getPassword();
			this.sqlInteraction=sqlInteraction;
			this.currentUser = currentUser;
			currentBankAccount = null;
		}
		
		initComponentsUser();
		
		switch(currentUser.getRole()) {
		case customerRole:
			initComponentsUser();
			break;
		case bankerRole:
			initComponentsBanker();
			break;
		}
	}
	
	public MakeOperationFrame(User currentUser, BankAccount currentBankAccount, SQLInteraction sqlInteraction) {
		if (currentUser != null) {
			this.firstName = currentUser.getFirstName();
			this.lastName = currentUser.getLastName();
			this.address = currentUser.getAddress();
			this.mail = currentUser.getMail();
			this.password = currentUser.getPassword();
			this.sqlInteraction=sqlInteraction;
			this.currentUser = currentUser;
			this.currentBankAccount = currentBankAccount;
		}
		
		initComponentsUser();
		
		switch(currentUser.getRole()) {
		case customerRole:
			initComponentsUser();
			break;
		case bankerRole:
			initComponentsBanker();
			break;
		}
	}

	
	
	private void initComponentsUser() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 499, 320);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel frameLabel = new JLabel("Transf\u00E9rer de l'argent");
		frameLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));

		JLabel customerAccountLabel = new JLabel("Num\u00E9ro du compte courant:");
		customerAccountLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));

		JLabel receiverAccountLabel = new JLabel("Num\u00E9ro de compte destinataire:");
		receiverAccountLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));

		JLabel amountLabel = new JLabel("Montant:");
		amountLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));

		customerAccountInput = new JTextField();
		customerAccountInput.setEditable(false);
		if(currentBankAccount!=null) {
			customerAccountInput.setText(currentBankAccount.toString());
		}

		customerAccountInput.setColumns(10);

		receiverAccountInput = new JTextField();
		receiverAccountInput.setColumns(10);

		amountInput = new JTextField();
		amountInput.setColumns(10);

		JButton ValideButton = new JButton("Transf\u00E9rer");

		JButton CancelButton = new JButton("Quitter");
		CancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(31)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(amountLabel, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 116, Short.MAX_VALUE)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(ValideButton)
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(CancelButton))
										.addComponent(amountInput, GroupLayout.PREFERRED_SIZE, 196, GroupLayout.PREFERRED_SIZE)))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(customerAccountLabel, GroupLayout.PREFERRED_SIZE, 178, GroupLayout.PREFERRED_SIZE)
											.addGap(18)
											.addComponent(customerAccountInput, GroupLayout.PREFERRED_SIZE, 196, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(receiverAccountLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addGap(18)
											.addComponent(receiverAccountInput, GroupLayout.PREFERRED_SIZE, 196, GroupLayout.PREFERRED_SIZE)))
									.addPreferredGap(ComponentPlacement.RELATED)))
							.addGap(196))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(133)
							.addComponent(frameLabel)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(frameLabel)
					.addGap(22)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(customerAccountLabel, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(customerAccountInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(receiverAccountLabel, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(receiverAccountInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(19)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(amountLabel, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(amountInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(CancelButton)
						.addComponent(ValideButton))
					.addGap(44))
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	private void initComponentsBanker() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 499, 320);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel frameLabel = new JLabel("Transf\u00E9rer de l'argent");
		frameLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));

		JLabel customerAccountLabel = new JLabel("Num\u00E9ro du compte courant:");
		customerAccountLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));

		JLabel receiverAccountLabel = new JLabel("Num\u00E9ro de compte destinataire:");
		receiverAccountLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));

		JLabel amountLabel = new JLabel("Montant:");
		amountLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));

		customerAccountInput = new JTextField();
		customerAccountInput.setColumns(10);

		receiverAccountInput = new JTextField();
		receiverAccountInput.setColumns(10);

		amountInput = new JTextField();
		amountInput.setColumns(10);
		
		JButton ValideButton = new JButton("Transf\u00E9rer");

		JButton CancelButton = new JButton("Quitter");
		CancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(31)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(amountLabel, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 116, Short.MAX_VALUE)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(ValideButton)
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(CancelButton))
										.addComponent(amountInput, GroupLayout.PREFERRED_SIZE, 196, GroupLayout.PREFERRED_SIZE)))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(customerAccountLabel, GroupLayout.PREFERRED_SIZE, 178, GroupLayout.PREFERRED_SIZE)
											.addGap(18)
											.addComponent(customerAccountInput, GroupLayout.PREFERRED_SIZE, 196, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(receiverAccountLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addGap(18)
											.addComponent(receiverAccountInput, GroupLayout.PREFERRED_SIZE, 196, GroupLayout.PREFERRED_SIZE)))
									.addPreferredGap(ComponentPlacement.RELATED)))
							.addGap(196))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(133)
							.addComponent(frameLabel)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(frameLabel)
					.addGap(22)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(customerAccountLabel, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(customerAccountInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(receiverAccountLabel, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(receiverAccountInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(19)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(amountLabel, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(amountInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(CancelButton)
						.addComponent(ValideButton))
					.addGap(44))
		);
		contentPane.setLayout(gl_contentPane);
	}

}