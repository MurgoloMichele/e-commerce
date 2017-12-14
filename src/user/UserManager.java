package user;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;


/**
 * Classe contentente metodi statici per la gestione del singleton rappresentante l'insieme degli utenti 
 * @author Michele Murgolo
 */
public class UserManager {
	
	/**
	 * @var users
	 * Hashmap che associa una stringa univoca ad ogni utente
	 */
	private static Vector<User> users = new Vector<User>(); 
	
	
	/**
	 * @brief Costruttutore private per evitare la creazione di un'istanza
	 */
	private UserManager(){
		
	}
	
	/**
	 * @brief Carica il file degli utenti
	 * @return true se il caricamento è andato a buon fine
	 */
	@SuppressWarnings("unchecked")
	public static boolean caricaUsers(){
		File usersFile = new File("src/user/user");
	
		if(usersFile.exists()){
			System.out.println("Users file found at " + usersFile.getAbsolutePath());
			try {				
				FileInputStream fis = new FileInputStream(usersFile);
				ObjectInputStream ois = new ObjectInputStream(fis);
				users = (Vector<User>)ois.readObject();				
				ois.close();
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				users = new Vector<User>();			
			}
		}
		else{
			System.out.println("Users file not found");

			users = new Vector<User>();			
		}
		return false;
	}
	
	/**
	 * @brief Salva il file degli utenti
	 * @return true se il salvataggio è andato a buon fine
	 */
	public static boolean salvaUsers(){
		
		File usersFile = new File("src/user/user");
		
		try {
			FileOutputStream fos = new FileOutputStream(usersFile);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(users);
			oos.flush();
			oos.close();
			
			System.out.println("Users file saved");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	/**
	 * @brief Aggiunge un nuovo utente
	 */
	public static void aggiungiUser(User newUser){
		users.addElement(newUser);
	}
	
	
	/**
	 * @brief Ritorna il numero degli utenti
	 * @return Numero utenti
	 */
	public static int getUserCount(){
		return users == null ? 0 : users.size();
	}
	
	/**
	 * @brief Controlla se un utente esiste
	 * @param name Nome utente
	 * @param password Password utente
	 * @return true se l'utente esiste
	 */
	public static boolean userExists(String name){
		
		if(users != null){
			return users.contains("name");
		}
		return false;
	}
	
	/**
	 * @brief Ritorna se esiste l'istanza di un utente
	 * @param name Nome dell'utente da cercare
	 * @param password Password dell'utente da cercare
	 * @return Istanza dell'utente se esiste altrimenti null
	 */
	public static boolean getUser(User u){
		boolean isGood = false;
		
		if(users != null){
			isGood = users.contains(u);
			
		}
		
		return isGood;
	}


}
