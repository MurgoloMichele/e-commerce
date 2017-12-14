package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

import user.Admin;
import user.Member;
import user.User;
import user.UserManager;


/**
 * Classe che implementa la schermata di login
 * @author Michele Murgolo
 */
public class LoginPanel extends CustomPanel {

	/**
	 * @var TAG
	 * Tag univoco utilizzato per identificare questa schermata
	 */
	public static final String TAG = "login";
	
	/**
	 * @var usernameLabel
	 * Label per identificare l'username
	 */
	private JLabel usernameLabel;
	
	/**
	 * @var passwordLabel
	 * Label per identificare la password
	 */
	private JLabel passwordLabel;
	
	/**
	 * @var SignIn
	 * Pulsante per effettuare il login
	 */
	private JButton signIn;
	
	/**
	 * @var username
	 * Casella di testo per l'inserimento dell'username
	 */
	private JTextField username;
	
	/**
	 * @var password
	 * Casella di testo per l'inserimento della password
	 */
	private JPasswordField password;
	
	/**
	 * @var signUp
	 * Pulsante per registrare un nuovo utente
	 */
	private JButton signUp;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LoginPanel(PanelManager panelManager){
		super(panelManager);
		
		usernameLabel = new JLabel("Insert username:");
		passwordLabel = new JLabel("Insert password:");
		signIn = new JButton("Sign in");
		signUp = new JButton("Sign up");
		username = new JTextField(20);
		password = new JPasswordField(20);
		password.setEchoChar('*');
		
		this.add(username);
		this.add(passwordLabel);
		signIn.addActionListener(this);
		signUp.addActionListener(this);
		this.add(signIn);
		this.add(signUp);
		this.add(usernameLabel);
		this.add(password);
		
		JPanel jp = new JPanel();
		GroupLayout layout = new GroupLayout(jp);
		jp.setLayout(layout);
		
		jp.add(usernameLabel);
		jp.add(passwordLabel);
		jp.add(signIn);
		jp.add(username);
		jp.add(password);
	
		GridBagLayout gbLayout = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.CENTER;
		setLayout(gbLayout);
		add(jp, c);
		
		layout.setAutoCreateGaps(true);
	    layout.setAutoCreateContainerGaps(true);
	    layout.linkSize(signUp, signIn);
	    
	    
	    layout.setVerticalGroup(
				layout.createSequentialGroup()
					.addGroup(
							layout.createParallelGroup(GroupLayout.Alignment.CENTER)
								.addComponent(usernameLabel)
								.addComponent(username)
					)
					.addGroup(
							layout.createParallelGroup(GroupLayout.Alignment.CENTER)
								.addComponent(passwordLabel)
								.addComponent(password)
					)
					.addGroup(
							layout.createParallelGroup(GroupLayout.Alignment.CENTER)
							.addComponent(signIn)
							.addComponent(signUp)
					)
		);

		layout.setHorizontalGroup(
				layout.createSequentialGroup()
					.addGroup(
							layout.createParallelGroup(GroupLayout.Alignment.CENTER)
								.addGroup(layout.createSequentialGroup()
									.addGroup(layout.createParallelGroup()
											.addComponent(usernameLabel)
											.addComponent(passwordLabel)
									)
									.addGroup(layout.createParallelGroup()
											.addComponent(username)
											.addComponent(password)
									)
								)
								.addGroup(layout.createSequentialGroup()
										.addComponent(signIn)
										.addComponent(signUp)
								)
								
					)
		);
		
	}

	

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource().equals(signIn)) {
			UserManager.caricaUsers();
			User newMember = new Member(username.getText(), new String(password.getPassword()));
			User newAdmin = new Admin(username.getText(), new String(password.getPassword()));
			
            boolean loginMember = UserManager.getUser(newMember);
            boolean loginAdmin = UserManager.getUser(newAdmin);
            
            if (loginMember) {
				System.out.println("Login effettuato con successo da Member");
				JOptionPane.showMessageDialog(this, "Login effettuato correttamente da Member", "Welcome", JOptionPane.INFORMATION_MESSAGE);
				panelManager.setCurrentPanel(MemberPanel.TAG);
			}
            
            if (loginAdmin) {
				System.out.println("Login effettuato con successo da Admin");
				JOptionPane.showMessageDialog(this, "Login effettuato correttamente da Admin", "Welcome", JOptionPane.INFORMATION_MESSAGE);
				panelManager.setCurrentPanel(AdminPanel.TAG);
			}
            
            if (!loginAdmin && !loginMember) {
				System.out.println("Non esiste questo utente");
				JOptionPane.showMessageDialog(this, "Nome utente e/o password non corretti", "Error", JOptionPane.ERROR_MESSAGE);
			}
			
		}
		
		if(e.getSource().equals(signUp)){
			panelManager.setCurrentPanel(SignUpPanel.TAG);
		}
		
	}
	
	public void onEnter(){
		username.setText("");
		password.setText("");
	}




	
	
}
