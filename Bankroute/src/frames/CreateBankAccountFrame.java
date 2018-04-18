package frames;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.bankroute.datatools.SQLInteraction;
import com.bankroute.user.Customer;
import com.bankroute.user.User;

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

	private JPanel contentPane;

	/**
	 * Create the frame for edit a user
	 * @wbp.parser.constructor
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			SQLInteraction sqlInteraction = new SQLInteraction("localhost","root","","3306","mysql");
			public void run() {
				try {
					LoginFrame frame = new LoginFrame(sqlInteraction);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public CreateBankAccountFrame(User userToEdit) {
		if (userToEdit != null) {
			this.firstName = userToEdit.getFirstName();
			this.lastName = userToEdit.getLastName();
			this.address = userToEdit.getAddress();
			this.mail = userToEdit.getMail();
			this.password = userToEdit.getPassword();
			this.sqlInteraction=userToEdit.getSQLInstance();
		}
		initComponents();
	}

	private void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 387, 305);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel frameLabel = new JLabel("Ouverture d'un compte banquaire");
		frameLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));

		JButton ValideButton = new JButton("Cr\u00E9er");

		ValideButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				/*if() {
					//updateUser(); TODO  
				}else {
					// createUser(); TODO AJOUTER VERIFICATIONS DES CHAMPS ET APPEL DE addUSer
				}*/
			}
		});

		JButton CancelButton = new JButton("Quitter");
		CancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		

		/* Gestion affichage des éléments */
		
		JLabel lblSlectionnerUnType = new JLabel("S\u00E9lectionner un type de compte");
		lblSlectionnerUnType.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JRadioButton rdbtnCompteCourant = new JRadioButton("Compte courant");
		
		JRadioButton rdbtnComptepargnePel = new JRadioButton("Compte \u00E9pargne: PEL");
		
		JRadioButton rdbtnLivretA = new JRadioButton("Compte \u00E9pargne: Livret A");
		
		JRadioButton rdbtnAssuranceVie = new JRadioButton("Assurance vie");
		
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(rdbtnCompteCourant);
		buttonGroup.add(rdbtnComptepargnePel);
		buttonGroup.add(rdbtnLivretA);
		buttonGroup.add(rdbtnAssuranceVie);
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(rdbtnCompteCourant)
										.addComponent(rdbtnLivretA))
									.addGap(26)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(rdbtnAssuranceVie, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
										.addComponent(rdbtnComptepargnePel)))
								.addComponent(lblSlectionnerUnType)))
						.addComponent(frameLabel)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(ValideButton)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(CancelButton)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(frameLabel)
					.addGap(45)
					.addComponent(lblSlectionnerUnType)
					.addGap(36)
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