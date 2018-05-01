package frames;

import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import static javax.swing.JOptionPane.WARNING_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;

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

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

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
	
	private int customerId = -1;
	private BankAccount currentBankAccount;

	private JPanel contentPane;
	private JTextField customerAccountInput;
	private JTextField receiverAccountInput;
	private JTextField amountInput;
	private User currentUser;
	private double amount = 0;
	private int numberAccountReceiver = -1;
	private boolean internal = true;
	
	/**
	 * Create the frame to edit a user
	 * @wbp.parser.constructor
	 */
	public MakeOperationFrame(User currentUser, SQLInteraction sqlInteraction) {
		setResizable(false);
		if (currentUser != null) {
			this.sqlInteraction=sqlInteraction;
			this.customerId = currentUser.getId();
			System.out.println(this.customerId);
			this.currentUser = currentUser;
			this.currentBankAccount = currentUser.operationManagement.findBankAccount(customerId, 1,0,sqlInteraction);
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
			customerAccountInput.setText(Integer.toString(currentBankAccount.getAccountNumber()));
		}

		customerAccountInput.setColumns(10);

		receiverAccountInput = new JTextField();
		receiverAccountInput.setColumns(10);

		amountInput = new JTextField();
		amountInput.setColumns(10);
		
		ButtonGroup group = new ButtonGroup();
		
		JRadioButton rdbtnInterne = new JRadioButton("Interne \u00E0 la banque");
		rdbtnInterne.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				internal = true;
			}
		});
		
		JRadioButton rdbtnExterne = new JRadioButton("Externe \u00E0 la banque");
		rdbtnExterne.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				internal = false;
			}
		});

		group.add(rdbtnInterne);
		group.add(rdbtnExterne);
		rdbtnInterne.setSelected(true);

		JButton ValideButton = new JButton("Transf\u00E9rer");
		ValideButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean transfertResult = transfertMoney();
				if(!transfertResult)
					showMessageDialog(null, "Transfert impossible", "Warning", WARNING_MESSAGE);
				else
					showMessageDialog(null, "Transfert effectu\u00E9", "Information", INFORMATION_MESSAGE);
				
			}
		});
		
		JButton CancelButton = new JButton("Quitter");
		CancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		JLabel lblSolde = new JLabel("Solde: " + currentBankAccount.getBalance() + " �");
		lblSolde.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(31)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(customerAccountLabel, GroupLayout.PREFERRED_SIZE, 178, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
											.addComponent(customerAccountInput, GroupLayout.PREFERRED_SIZE, 196, GroupLayout.PREFERRED_SIZE))
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(receiverAccountLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addGap(18)
											.addComponent(receiverAccountInput, GroupLayout.PREFERRED_SIZE, 196, GroupLayout.PREFERRED_SIZE))
										.addComponent(lblSolde, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(amountLabel, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(ValideButton)
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(CancelButton))
										.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
											.addGroup(gl_contentPane.createSequentialGroup()
												.addComponent(rdbtnInterne)
												.addGap(18)
												.addComponent(rdbtnExterne, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED))
											.addComponent(amountInput, GroupLayout.PREFERRED_SIZE, 196, GroupLayout.PREFERRED_SIZE)))))
							.addGap(318))
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
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(22)
							.addComponent(customerAccountInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(customerAccountLabel, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)))
					.addGap(4)
					.addComponent(lblSolde)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(receiverAccountLabel, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(receiverAccountInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(rdbtnInterne)
						.addComponent(rdbtnExterne))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(amountInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(amountLabel, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(ValideButton)
						.addComponent(CancelButton))
					.addGap(16))
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
	
	private boolean transfertMoney() {
		boolean transfertResult = false;
		
		if(!internal) {
			amount = Double.parseDouble(amountInput.getText());
			numberAccountReceiver = Integer.parseInt(receiverAccountInput.getText());
			transfertResult = currentUser.operationManagement.makeExternalOperation(currentUser, amount, currentBankAccount.getAccountNumber(), numberAccountReceiver,sqlInteraction);
			
		} else {
			
			amount = Double.parseDouble(amountInput.getText());
			numberAccountReceiver = Integer.parseInt(receiverAccountInput.getText());
			try {
				
				String requete = "SELECT number FROM account where number=" + numberAccountReceiver;
				ResultSet rs = sqlInteraction.executeQuery(requete);
				rs.next();
				if(rs.getInt(1) == numberAccountReceiver) {
					transfertResult = currentUser.operationManagement.makeInternalOperation(currentUser, amount, currentBankAccount.getAccountNumber(), numberAccountReceiver,sqlInteraction);
				} else {
					transfertResult = false;
				}
				
			} catch (SQLException e1) {
				e1.printStackTrace();
				return false;
			}
		}
		return transfertResult;
	}
}