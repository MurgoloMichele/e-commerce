package user;

import java.io.Serializable;

/**
 * Classe che rappresenta un utente generico.
 * La classe è Serializable per il salvataggio su file.
 * @author Michele Murgolo
 */
public abstract class User implements Serializable{

	
	private static final long serialVersionUID = 4188443976735128885L;
	
	/**
	 * @var nome
	 * Nome utente
	 */
	private String name;
	
	/**
	 * @var password
	 * Password utente
	 */
	private String password;
	
	/**
	 * @var isAdmin
	 * True se l'utente è amministratore
	 */
	private Boolean isAdmin;
	
	
	public User(String name, String password, boolean isAdmin) {
		this.name = name;
		this.isAdmin = isAdmin;
		this.password = password;
	}
	
	public String getName(){
		return name;
	}
	

	public String getPassword(){
		return password;
	}
	
	public boolean isAdmin(){
		return isAdmin;
	}
	
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	
	public String toString(){
		return name + " " + password + " " + isAdmin;
	}
	
	public boolean equals(Object o)
	 {
	       if(o == null || !getClass().equals(o.getClass()))  return false;
	       if(o == this)  return true;

	       User u = (User) o;
	       if (this.name.compareTo(u.name) == 0 && this.password.compareTo(u.password) == 0 && this.isAdmin.compareTo(u.isAdmin) == 0) {
			return true;
	       }
	   return false;
	}
}
