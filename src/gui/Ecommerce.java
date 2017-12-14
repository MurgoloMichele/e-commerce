package gui;

import prodotti.ProdottoManager;
import user.UserManager;
import gui.LoginPanel;

public class Ecommerce {

	public static void main(String[] args) {
		
		ProdottoManager.caricaProdotto();
		UserManager.caricaUsers();
		PanelManager main = new PanelManager();
		main.setCurrentPanel(LoginPanel.TAG);
	
	}
	
}
