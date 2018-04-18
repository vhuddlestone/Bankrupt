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

/**
 * Generic fram to add/edit a user
 * 
 * @author vhuddlestone
 */
public class UserDetailsFrame extends JFrame {
	
	private SQLInteraction sqlInteraction;
	
	private String firstName = "";
	private String lastName = "";
	private String address = "";
	private String mail = "";
	private String password = "";

	private JPanel contentPane;
	private JTextField firstNameInput;
	private JTextField lastNameInput;
	private JTextField addressInput;
	private JTextField mailInput;
	private JTextField passwordInput;
	
	

	/**
	 * Create the frame for a new user, withour pre-setted informations
	 */
	public UserDetailsFrame(SQLInteraction sqlInteraction) {
		initComponents();
		this.sqlInteraction=sqlInteraction;
	}

	/**
	 * Create the frame for edit a user
	 * @wbp.parser.constructor
	 */
	public UserDetailsFrame(User userToEdit) {
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
		setBounds(100, 100, 394, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		JLabel frameLabel = new JLabel("Cr\u00E9ation d'un compte client");
		frameLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));

		JLabel firstNameLabel = new JLabel("Pr\u00E9nom:");
		firstNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));

		JLabel lastNameLabel = new JLabel("Nom:");
		lastNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));

		JLabel addressLabel = new JLabel("Addresse:");
		addressLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));

		JLabel mailLabel = new JLabel("Mail:");
		mailLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));

		JLabel passwordLabel = new JLabel("Mot de passe:");
		passwordLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));

		firstNameInput = new JTextField();
		firstNameInput.setColumns(10);
		firstNameInput.setText(this.firstName);

		lastNameInput = new JTextField();
		lastNameInput.setColumns(10);
		lastNameInput.setText(this.lastName);

		addressInput = new JTextField();
		addressInput.setColumns(10);
		addressInput.setText(this.address);

		mailInput = new JTextField();
		mailInput.setColumns(10);
		mailInput.setText(this.mail);

		passwordInput = new JTextField();
		passwordInput.setColumns(10);
		passwordInput.setText(this.password);

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

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup().addGap(31)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(passwordLabel, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
								.addComponent(mailLabel, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
								.addComponent(addressLabel, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
								.addComponent(lastNameLabel, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
								.addComponent(firstNameLabel, GroupLayout.PREFERRED_SIZE, 80,
										GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(firstNameInput, GroupLayout.PREFERRED_SIZE, 196,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(lastNameInput, GroupLayout.PREFERRED_SIZE, 196,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(addressInput, GroupLayout.PREFERRED_SIZE, 196, GroupLayout.PREFERRED_SIZE)
								.addComponent(mailInput, GroupLayout.PREFERRED_SIZE, 196, GroupLayout.PREFERRED_SIZE)
								.addComponent(passwordInput, GroupLayout.PREFERRED_SIZE, 196,
										GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createSequentialGroup().addComponent(ValideButton)
										.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(CancelButton)))
						.addGap(200))
				.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup().addGap(43).addComponent(frameLabel)
						.addContainerGap(179, Short.MAX_VALUE)));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup().addContainerGap().addComponent(frameLabel).addGap(43)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(firstNameLabel, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(firstNameInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lastNameLabel, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(lastNameInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(addressLabel, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(addressInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(mailLabel, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(mailInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(passwordLabel, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addComponent(passwordInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED, 37, Short.MAX_VALUE).addGroup(gl_contentPane
						.createParallelGroup(Alignment.BASELINE).addComponent(ValideButton).addComponent(CancelButton))
				.addGap(39)));
		contentPane.setLayout(gl_contentPane);
	}

}