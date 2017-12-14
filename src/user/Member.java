package user;

/**
 * Classe figlia che rappresenta un membro che eredita da User.
 * @author Michele Murgolo
 */
public class Member extends User {

	private static final long serialVersionUID = 1L;

	public Member(String name, String password) {
		super(name, password, false);
	}


}
