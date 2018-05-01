package frames;

import static javax.swing.JOptionPane.WARNING_MESSAGE;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.bankrupt.datatools.SQLInteraction;
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
import java.sql.SQLException;

import javax.swing.JList;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Generic fram to add/edit a user
 * 
 * @author vhuddlestone
 */



public class CreateBankAccountFrame extends JFrame {
	
	private SQLInteraction sqlInteraction;
	
	private String firstName = "";
	private String lastName = "";
	private String address = "";
	private String mail = "";
	private String password = "";
	private User currentUser;
	private int account_type = 0;
	private int saving_type = 0;

	private JPanel contentPane;
	
	
	/**
	 * Create the frame for a new user, withour pre-setted informations
	 */
	public CreateBankAccountFrame(SQLInteraction sqlInteraction) {
		initComponents();
		this.sqlInteraction=sqlInteraction;
	}

	/**
	 * Create the frame for edit a user
	 * @wbp.parser.constructor
	 */
	public CreateBankAccountFrame(User currentUser, SQLInteraction sqlInteraction) {
		setResizable(false);
		if (currentUser != null) {
			this.firstName = currentUser.getFirstName();
			this.lastName = currentUser.getLastName();
			this.address = currentUser.getAddress();
			this.mail = currentUser.getMail();
			this.password = currentUser.getPassword();
			this.sqlInteraction=sqlInteraction;
			this.currentUser = currentUser;
		}
		initComponents();
	}
	

	private void initComponents() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 387, 305);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		ButtonGroup group = new ButtonGroup();
		JLabel frameLabel = new JLabel();
		JButton ValideButton = new JButton();
		JButton CancelButton = new JButton();
		JLabel lblSlectionnerUnType = new JLabel();
		JRadioButton rdbtnCompteCourant = new JRadioButton();
		JRadioButton rdbtnComptepargnePel = new JRadioButton();
		JRadioButton rdbtnLivretA = new JRadioButton();
		JRadioButton rdbtnAssuranceVie = new JRadioButton();
		
		group.add(rdbtnCompteCourant);
		group.add(rdbtnComptepargnePel);
		group.add(rdbtnLivretA);
		group.add(rdbtnAssuranceVie);
		
		rdbtnCompteCourant.setSelected(true);
		account_type = 1;
		saving_type = 0;
		
		frameLabel.setText("Ouverture d'un compte banquaire");
		ValideButton.setText("Cr\u00E9er");
		CancelButton.setText("Quitter");
		lblSlectionnerUnType.setText("S\u00E9lectionner un type de compte");
		rdbtnCompteCourant.setText("Compte courant");
		rdbtnComptepargnePel.setText("Compte \u00E9pargne: PEL");
		rdbtnLivretA.setText("Compte \u00E9pargne: Livret A");
		rdbtnAssuranceVie.setText("Assurance vie");
		
		frameLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblSlectionnerUnType.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		
		ValideButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				boolean result;
				try {
					System.out.println("account_type: " + account_type + "saving type: " + saving_type);
					result = currentUser.accountManagement.addBankAccount(currentUser, account_type, saving_type, sqlInteraction);
					if(!result)
						showMessageDialog(null, "Vous poss\u00E9dez d\u00E9j\u00e0 un compte", "Warning", WARNING_MESSAGE);
					else
						showMessageDialog(null, "Compte cr\u00E9\u00E9", "Information", INFORMATION_MESSAGE);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					showMessageDialog(null, "Un probl\u00E8me est survenu lors de la connexion à  la BDD. Compte non cr\u00E9\u00E9", "Warning", WARNING_MESSAGE);
				}
			}
		});

		
		CancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});

		
		
		rdbtnCompteCourant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				account_type = 1;
				saving_type = 0;
			}
		});
		
		
		rdbtnComptepargnePel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				account_type = 2;
				saving_type = 1;
			}
		});
		
		
		rdbtnLivretA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				account_type = 2;
				saving_type = 2;
			}
		});
		
		rdbtnAssuranceVie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				account_type = 2;
				saving_type = 3;
			}
		});
		
		JLabel accountNumberLabel = new JLabel();
		accountNumberLabel.setText("Numéro de compte");
		accountNumberLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(rdbtnCompteCourant)
								.addComponent(rdbtnLivretA))
							.addGap(26)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(rdbtnAssuranceVie, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
								.addComponent(rdbtnComptepargnePel)))
						.addComponent(frameLabel)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(ValideButton)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(CancelButton))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblSlectionnerUnType))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(accountNumberLabel)))
					.addContainerGap(13, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(frameLabel)
					.addGap(18)
					.addComponent(accountNumberLabel, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
					.addGap(28)
					.addComponent(lblSlectionnerUnType)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(rdbtnCompteCourant)
						.addComponent(rdbtnComptepargnePel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(rdbtnLivretA)
						.addComponent(rdbtnAssuranceVie))
					.addGap(35)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(ValideButton)
						.addComponent(CancelButton))
					.addGap(102))
		);
		contentPane.setLayout(gl_contentPane);
	}
}