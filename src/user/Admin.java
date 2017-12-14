package user;

/**
 * Classe figlia che rappresenta un amministratore che eredita da User.
 * @author Michele Murgolo
 */
public class Admin extends User{

	private static final long serialVersionUID = 1L;

	public Admin(String name, String password) {
		super(name, password, true);
	}


}
