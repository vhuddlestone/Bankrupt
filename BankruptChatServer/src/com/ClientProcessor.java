package com;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import javax.swing.JTextPane;

public class ClientProcessor implements Runnable {

	private String userName = "";
	private Socket socket;
	private PrintWriter writer = null;
	private BufferedInputStream inputStream = null;
	private JTextPane logPane;
	
	public static final String defautlSeparator = "@";

	public ClientProcessor(Socket socket, JTextPane logPane) {
		this.socket = socket;
		this.logPane = logPane;
	}

	public void run() {
		System.err.println("Traitement d'une connexion cliente");
		
		boolean closeConnexion = false;
		while (!socket.isClosed()) {

			try {
				writer = new PrintWriter(socket.getOutputStream());
				inputStream = new BufferedInputStream(socket.getInputStream());

				// On écoute le client
				String socketMessage = read();
				
				// socket recue
				System.out.println(socketMessage);
				
				// traitement de la requète du client selon le protocol de communication
				String toSend = "";
				String spl[] = socketMessage.split(defautlSeparator);
				String command = spl[0];
				String message = "";

				switch (command.toUpperCase()) {
				case "NAME": 
					message = spl[1];
					this.userName = message;
					toSend = "Welcome " + message;
					break;
				case "MESSAGE":
					message = spl[1];
					logPane.setText(logPane.getText() + "\n" + userName + " - " + message);
					toSend = logPane.getText();
					break;
				case "CLOSE":
					closeConnexion=true;
					toSend="Connexion fermée";
					break;
				case "UPDATE":
					toSend = logPane.getText();
					break;
				default:
					toSend = "Wrong command";
					break;
				}
				writer.write(toSend);
				writer.flush();

				if (closeConnexion) {
					System.err.println("COMMANDE CLOSE DETECTEE ! ");
					writer = null;
					inputStream = null;
					socket.close();
					break;
				}
			} catch (SocketException e) {
				System.err.println("LA CONNEXION A ETE INTERROMPUE ! ");
				break;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// Methode qui récupere la réponse 
	private String read() throws IOException {
		String response = "";
		int stream;
		byte[] b = new byte[4096];
		stream = inputStream.read(b);
		response = new String(b, 0, stream);
		return response;
	}

}