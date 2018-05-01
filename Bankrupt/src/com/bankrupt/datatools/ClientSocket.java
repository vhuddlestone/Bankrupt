package com.bankrupt.datatools;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;

import com.bankrupt.tools.Values;
import com.bankrupt.user.User;

public class ClientSocket {

   private Socket connexion = null;
   private PrintWriter writer = null;
   private BufferedInputStream reader = null;
   private User currentUser;
   
   //Notre liste de commandes. Le serveur nous répondra différemment selon la commande utilisée.
   private String[] listCommands = {"NAME", "MESSAGE", "CLOSE", "UPDATE"};
   private static int count = 0;
   private String name = "Client-";   
   
   /**
 * @return the connexion
 */
public Socket getConnexion() {
	return connexion;
}

/**
 * @param connexion the connexion to set
 */
public void setConnexion(Socket connexion) {
	this.connexion = connexion;
}

public ClientSocket(User user,String host, int port){
      name += ++count;
      currentUser=user;
      try {
         connexion = new Socket(host, port);
         initSocket();
      } catch (UnknownHostException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
   
   private void initSocket() {
	   sendSocket(listCommands[0], currentUser.getLastName());
   }
   
   public String sengMessage(String message) {
	   return sendSocket(listCommands[1], message);
   }
   
   public String getUpdate() {
	   return sendSocket(listCommands[3], "");
   }
   
   public String sendSocket(String commande, String msg){
	   String response ="";
	   try {
           writer = new PrintWriter(connexion.getOutputStream(), true);
           reader = new BufferedInputStream(connexion.getInputStream());
           //On envoie la commande au serveur
          
           writer.write(commande +Values.defautlSeparator+ msg );
           //TOUJOURS UTILISER flush() POUR ENVOYER RÉELLEMENT DES INFOS AU SERVEUR
           writer.flush();  
           
           System.out.println("Commande " + commande + " envoyée au serveur");
           
           //On attend la réponse
           response = read();
           System.out.println("\t * " + name + " : Réponse reçue " + response);
           
        } catch (IOException e1) {
           e1.printStackTrace();
        }
	   return response;
   }
   
   private void close() {
	      writer.write("CLOSE");
	      writer.flush();
	      writer.close();
   }
   
   //Méthode pour lire les réponses du serveur
   private String read() throws IOException{      
      String response = "";
      int stream;
      byte[] b = new byte[4096];
      stream = reader.read(b);
      response = new String(b, 0, stream);      
      return response;
   }
}