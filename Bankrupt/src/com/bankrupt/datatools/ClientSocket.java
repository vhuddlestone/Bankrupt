package com.bankrupt.datatools;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;

import com.bankrupt.tools.Values;
import com.bankrupt.user.User;

/**
 * Client class for socket communication
 */
public class ClientSocket {

	private Socket connexion = null;
	private PrintWriter writer = null;
	private BufferedInputStream reader = null;
	private User currentUser;

	/**
	 * Commandes du protocol de communication, les trammes envoyées sont de la forme
	 * COMMANDE[SEPARATOR][MESSAGE]
	 */
	private String[] listCommands = { "NAME", "MESSAGE", "CLOSE", "UPDATE" };
	private static int count = 0;
	private String name = "Client-";

	/**
	 * @return the connexion
	 */
	public Socket getConnexion() {
		return connexion;
	}

	/**
	 * @param connexion
	 *            the connexion to set
	 */
	public void setConnexion(Socket connexion) {
		this.connexion = connexion;
	}

	public ClientSocket(User user, String host, int port) {
		name += ++count;
		currentUser = user;
		try {
			connexion = new Socket(host, port);
			initSocket();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Initialise la connection avec le serveur en envoyant la commande NAME et le
	 * nom de famille de l'utilisateur
	 */
	private void initSocket() {
		sendSocket(listCommands[0], currentUser.getLastName());
	}

	/**
	 * Envoie un message pour le chat
	 * @param message
	 * @return le fil de discution
	 */
	public String sengMessage(String message) {
		return sendSocket(listCommands[1], message);
	}
	
	/**
	 * Demande au serveur le fil de discution
	 * @return
	 */
	public String getUpdate() {
		return sendSocket(listCommands[3], "");
	}

	/**
	 * Envoie la socket contenant la commande et le message
	 * 
	 * @param commande
	 * @param msg
	 * @return réponse du serveur
	 */
	public String sendSocket(String commande, String msg) {
		String response = "";
		try {
			writer = new PrintWriter(connexion.getOutputStream(), true);
			reader = new BufferedInputStream(connexion.getInputStream());

			// On envoie la concaténation de la commande, du séparateur et du message
			writer.write(commande + Values.defautlSeparator + msg);
			writer.flush();

			System.out.println("Commande " + commande + " envoyée au serveur");

			// On attend la réponse
			response = read();
			System.out.println("\t * " + name + " : Réponse reçue " + response);

		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return response;
	}

	/**
	 * Envoie la commande pour informer le serveur de l'arret de communication
	 */
	private void close() {
		writer.write(listCommands[2]);
		writer.flush();
		writer.close();
	}

	/**
	 * Fonction qui converti la réponse en string
	 * 
	 * @return
	 * @throws IOException
	 */
	private String read() throws IOException {
		String response = "";
		int stream;
		byte[] b = new byte[4096];
		stream = reader.read(b);
		response = new String(b, 0, stream);
		return response;
	}
}