package com;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ChatServer {

	// swing components
	private JFrame frame;
	private JTextField clientNumberField;
	private JTextField portField;
	private JLabel lblNombreClients;
	private JPanel statePanel;
	private JTextPane textPanel;

	private ServerSocket serveurSocket;

	private boolean isRunning = true;
	private String host = "127.0.0.1";
	private int nbConnectedClients = 0;

	/**
	 * Create the application.
	 */
	public ChatServer() {
		initialize();
		Timer timerState = initTimerState();
		frame.setVisible(true);
	}

	private Timer initTimerState() {
		Timer timerState = new Timer();
		timerState.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				System.out.println("Je verifie l'état du serveur ");
				if (serveurSocket != null) {
					if (serveurSocket.isBound()) {
						if (serveurSocket.isClosed()) {
							statePanel.setBackground(Color.RED);
						} else {
							statePanel.setBackground(Color.GREEN);
							lblNombreClients.setText(String.valueOf(nbConnectedClients));
						}
					} else {
						statePanel.setBackground(Color.RED);
					}
				} else {
					statePanel.setBackground(Color.RED);
				}
			}
		}, new Date(), 1000);
		return timerState;
	}

	private int startServer() {
		int port = -1;
		try {
			textPanel.setText("");
			serveurSocket = new ServerSocket(0);
			port = serveurSocket.getLocalPort();
			System.out.println(serveurSocket.getInetAddress());
			System.out.println(serveurSocket.getLocalSocketAddress());
			isRunning = true;
			open();
		} catch (IOException e) {
			isRunning = false;
			e.printStackTrace();
		}
		return port;
	}

	public void open() {
		Thread t = new Thread(new Runnable() {
			public void run() {
				while (isRunning == true) {

					try {
						// On attend une connexion d'un client
						System.out.println("On attend une connexion d'un client");
						Socket client = serveurSocket.accept();

						// Une fois reçue, on la traite dans un thread séparé
						System.out.println("Connexion cliente reçue.");
						nbConnectedClients++;
						Thread t = new Thread(new ClientProcessor(client, textPanel));
						t.start();

					} catch (IOException e) {

					}
				}
			}
		});

		t.start();
	}

	private void stopServer() {
		if (serveurSocket != null) {
			try {
				this.isRunning = false;
				serveurSocket.close();
				nbConnectedClients=0;
				portField.setText("0");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * Initialize the contents of the frame.
	 * Auto generated ugly code
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		statePanel = new JPanel();
		statePanel.setBackground(Color.RED);
		statePanel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));

		JLabel lblNewLabel = new JLabel("Etat :");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));

		JLabel lblNombreClients = new JLabel("Nombre clients :");
		lblNombreClients.setFont(new Font("Tahoma", Font.PLAIN, 16));

		clientNumberField = new JTextField();
		clientNumberField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		clientNumberField.setText(String.valueOf(nbConnectedClients));
		clientNumberField.setEditable(false);
		clientNumberField.setColumns(10);

		JButton startButton = new JButton("D\u00E9marrer");
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int port = startServer();
				portField.setText(String.valueOf(port));
			}
		});

		JButton StopButton = new JButton("Arreter");
		StopButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stopServer();
			}
		});

		textPanel = new JTextPane();
		textPanel.setFont(new Font("Tahoma", Font.PLAIN, 14));

		JLabel lblPortDcoute = new JLabel("Port d'\u00E9coute :");
		lblPortDcoute.setFont(new Font("Tahoma", Font.PLAIN, 16));

		portField = new JTextField();
		portField.setText("0");
		portField.setFont(new Font("Tahoma", Font.PLAIN, 15));
		portField.setEditable(false);
		portField.setColumns(10);
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addContainerGap()
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
						.createSequentialGroup()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(StopButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addComponent(startButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE))
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup().addGap(97).addComponent(lblNewLabel))
								.addGroup(groupLayout.createSequentialGroup().addGap(18)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(lblPortDcoute).addComponent(lblNombreClients))))
						.addGap(18)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(portField, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
										.addComponent(clientNumberField, 0, 0, Short.MAX_VALUE)
										.addComponent(statePanel, GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE))))
						.addComponent(textPanel, GroupLayout.PREFERRED_SIZE, 410, GroupLayout.PREFERRED_SIZE))
				.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.TRAILING).addGroup(groupLayout
				.createSequentialGroup().addGap(23)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
						.createSequentialGroup().addGap(3)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING).addComponent(lblPortDcoute)
								.addComponent(portField, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING).addComponent(lblNewLabel)
								.addComponent(statePanel, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(lblNombreClients)
								.addComponent(clientNumberField, GroupLayout.PREFERRED_SIZE, 25,
										GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup().addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(startButton, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
								.addComponent(StopButton, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)))
				.addGap(16).addComponent(textPanel, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE)
				.addContainerGap()));
		frame.getContentPane().setLayout(groupLayout);
	}
}
