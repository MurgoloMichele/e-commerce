package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

import user.Admin;
import user.Member;
import user.User;
import user.UserManager;



public class SignUpPanel extends CustomPanel{
	
	public static String TAG = "signup";
	
	private JLabel usernameLabel;
	private JLabel passwordLabel;
	private JButton cancel;
	private JTextField username;
	private JPasswordField password;
	private JButton signUp;
	private JCheckBox isAdmin;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SignUpPanel(PanelManager panelManager){
		super(panelManager);
		
		usernameLabel = new JLabel("Insert username:");
		passwordLabel = new JLabel("Insert password:");
		cancel = new JButton("Cancel");
		signUp = new JButton("Sign up");
		username = new JTextField(20);
		password = new JPasswordField(20);
		password.setEchoChar('*');
		isAdmin = new JCheckBox("Admin");
		
		this.add(username);
		this.add(isAdmin);
		this.add(passwordLabel);
		cancel.addActionListener(this);
		signUp.addActionListener(this);

		this.add(signUp);
		this.add(cancel);
		this.add(usernameLabel);
		this.add(password);
		this.add(isAdmin);
		
		JPanel jp = new JPanel();
		GroupLayout layout = new GroupLayout(jp);
		jp.setLayout(layout);
		
		jp.add(usernameLabel);
		jp.add(passwordLabel);
		jp.add(cancel);
		jp.add(signUp);
		jp.add(username);
		jp.add(password);
		jp.add(isAdmin);
	
		GridBagLayout gbLayout = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.CENTER;
		setLayout(gbLayout);
		add(jp, c);
		
		layout.setAutoCreateGaps(true);
	    layout.setAutoCreateContainerGaps(true);
	
	    
	    
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
							.addComponent(cancel)
							.addComponent(signUp)
							.addComponent(isAdmin)
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
										.addComponent(cancel)
										.addComponent(signUp)
										.addComponent(isAdmin)
								)
								
					)
		);
		
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource().equals(signUp)) {
			if (username.getText().isEmpty() || new String(password.getPassword()).isEmpty()){
				JOptionPane.showMessageDialog(this, "Nome utente e/o password vuoti", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else{
				String user = username.getText();
				String psw = new String(password.getPassword());
				if(isAdmin.isSelected()){
					//crea un utente amministratore
					User a = new Admin(user, psw);
					System.out.println("Admin creato" + " " + user + " " + psw);
					
					UserManager.aggiungiUser(a);
					UserManager.salvaUsers();
					panelManager.setCurrentPanel(LoginPanel.TAG);
				}
				else{
					//crea un utente normale
					User m = new Member(user, psw);
					System.out.println("Member creato" + " " + user + " " + psw);
					UserManager.aggiungiUser(m);
					UserManager.salvaUsers();
					panelManager.setCurrentPanel(LoginPanel.TAG);
				}
			}
		}
		
		if (e.getSource().equals(cancel)) {
			panelManager.setCurrentPanel(LoginPanel.TAG);
		}
		
	}
	
	public void onEnter(){
		username.setText("");
		password.setText("");
	}
	
}
	
