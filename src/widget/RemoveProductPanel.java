package widget;

import gui.CartPanel;

import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;


import carrello.CarrelloManager;

import prodotti.Prodotto;

public class RemoveProductPanel extends BuyProductPanel {

	public RemoveProductPanel(Prodotto product) {
		super(product);
		
		addCartButton.setIcon(new ImageIcon(BuyProductPanel.class.getResource("/image/delete.png")));	
		qtSpinner.setVisible(false);
		qtLabel.setVisible(false);

	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 2113339894393902609L;
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(addCartButton)){
			CarrelloManager.removeProduct(product);
			System.out.println(product.getNome() + " è stato rimosso dal carrello");
			CartPanel.reloadTotal();
		}
	}

}
