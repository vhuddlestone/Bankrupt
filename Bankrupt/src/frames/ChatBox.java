package frames;

import java.awt.EventQueue;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JTextPane;

import com.bankrupt.datatools.ClientSocket;
import com.bankrupt.user.User;

import static javax.swing.JOptionPane.WARNING_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ChatBox {

	private JFrame frame;
	private JTextField textInput;
	private ClientSocket clientSocket = null;
	private User currentUser;
	private JTextPane chatPanel;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JTextField ipInputField;
	private JTextField portInputField;
	private JButton connectButton;

	/**
	 * Create the application.
	 */
	public ChatBox(User user) {
		initialize();
		this.currentUser = user;

		Timer timerUpdateChatPanel = new Timer();
		timerUpdateChatPanel.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				if (clientSocket != null) {
					System.out.println("Je verifie si un autre chatteur parle");
					String text = clientSocket.getUpdate();
					if (chatPanel.getText() != text) {
						chatPanel.setText(text);
					}
				}
			}
		}, new Date(), 2000);
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 543, 386);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		chatPanel = new JTextPane();
		chatPanel.setEnabled(false);
		chatPanel.setEditable(false);
		chatPanel.setBounds(12, 71, 501, 216);
		frame.getContentPane().add(chatPanel);

		textInput = new JTextField();
		textInput.setEnabled(false);
		textInput.setBounds(12, 300, 501, 26);
		frame.getContentPane().add(textInput);
		textInput.setColumns(10);

		lblNewLabel = new JLabel("Addresse IP");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(12, 13, 122, 16);
		frame.getContentPane().add(lblNewLabel);

		lblNewLabel_1 = new JLabel("Port");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(12, 42, 56, 16);
		frame.getContentPane().add(lblNewLabel_1);

		ipInputField = new JTextField();
		ipInputField.setBounds(109, 10, 116, 22);
		frame.getContentPane().add(ipInputField);
		ipInputField.setColumns(10);

		portInputField = new JTextField();
		portInputField.setBounds(109, 40, 116, 22);
		frame.getContentPane().add(portInputField);
		portInputField.setColumns(10);

		connectButton = new JButton("Connexion");
		connectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connectButtonClicked();
			}
		});
		connectButton.setBounds(251, 24, 97, 25);
		frame.getContentPane().add(connectButton);
		textInput.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				sendMessage(textInput.getText());
			}
		});
	}

	private void connectButtonClicked() {

		if (ipInputField.getText().isEmpty() && portInputField.getText().isEmpty()) {
			showMessageDialog(null, "Username and password empty", "Warning", WARNING_MESSAGE);
		} else if (ipInputField.getText().isEmpty()) {
			showMessageDialog(null, "Ip vide", "Warning", WARNING_MESSAGE);
		} else if (portInputField.getText().isEmpty()) {
			showMessageDialog(null, "Port vide", "Title", WARNING_MESSAGE);
		} else {
			if (clientSocket != null) {
				clientSocket = new ClientSocket(currentUser, ipInputField.getText(),
						Integer.parseInt(portInputField.getText()));
				if (clientSocket.getConnexion() != null) {
					chatPanel.setEnabled(true);
					textInput.setEnabled(true);
				}
			}

			clientSocket = new ClientSocket(currentUser, ipInputField.getText(),
					Integer.parseInt(portInputField.getText()));
			if (clientSocket.getConnexion() != null) {
				chatPanel.setEnabled(true);
				textInput.setEnabled(true);
			}
		}
	}


	private void sendMessage(String message) {
		chatPanel.setText(clientSocket.sengMessage(message));
		textInput.setText("");
	}
}
