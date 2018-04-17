package frames;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.bankroute.datatools.SQLInteraction;
import com.bankroute.user.User;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JList;
import javax.swing.JRadioButton;

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
		if(this.firstName!="") {
			ValideButton = new JButton("Editer");
		}

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

		/* Gestion affichage des éléments */
		
		JLabel lblSlectionnerUnType = new JLabel("S\u00E9lectionner un type de compte");
		lblSlectionnerUnType.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JList list = new JList();
		
		JRadioButton rdbtnCompteCourant = new JRadioButton("Compte courant");
		
		JRadioButton rdbtnComptepargnePel = new JRadioButton("Compte \u00E9pargne: PEL");
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("Compte \u00E9pargne: Livret A");
		
		JRadioButton rdbtnAssuranceVie = new JRadioButton("Assurance vie");

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(rdbtnCompteCourant)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(list, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE))
								.addComponent(rdbtnNewRadioButton))
							.addGap(26)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(rdbtnAssuranceVie, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
								.addComponent(rdbtnComptepargnePel))
							.addContainerGap(72, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblSlectionnerUnType)
							.addContainerGap(200, Short.MAX_VALUE))))
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addComponent(frameLabel)
					.addContainerGap(48, Short.MAX_VALUE))
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(ValideButton)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(CancelButton)
					.addContainerGap(260, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(frameLabel)
					.addGap(45)
					.addComponent(lblSlectionnerUnType)
					.addGap(36)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(list, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(rdbtnCompteCourant)
							.addComponent(rdbtnComptepargnePel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(rdbtnNewRadioButton)
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