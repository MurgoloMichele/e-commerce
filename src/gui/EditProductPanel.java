package gui;

import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;



import prodotti.Prodotto;
import prodotti.ProdottoManager;
import utils.ImageLoader;



public class EditProductPanel extends AddProductPanel{

	private static final long serialVersionUID = 8356218955497689935L;
	
	/**
	 * @var index
	 * Indice del prodotto da modificare nella tabella
	 */
	private static int index;
	
	/**
	 * @var TAG
	 * Tag univoco utilizzato per identificare questa schermata
	 */
	public static final String TAG = "editProduct";
	
	public EditProductPanel(PanelManager panelManager) {
		super(panelManager);
		
		//Modifica il nome del pulsante OK in Modifica
		okButton.setText("Modifica");
		
		//Nasconde gli sconti
		sconto.setVisible(false);
		trexdue.setVisible(false);
		nil.setVisible(false);
		scontoFieldLabel.setVisible(false);
		scontoField.setVisible(false);
	}
	
	
	public void onEnter(){
		Prodotto p = ProdottoManager.getProdotto(index);
		nomeProdotto.setText(p.getNome());
		marcaProdotto.setText(p.getMarca());
		categoriaProdotto.setText(p.getCategoria());
		idProdotto.setText(p.getIdProdotto());
		imagePath = p.getImmagine();
		if(p.getImmagine() != null){
			image.setIcon(new ImageIcon(ImageLoader.loadImage(p.getImmagine(), image.getMinimumSize())));
			image.setText("");
		}
		prezzo.setText(Float.toString(p.getPrezzo()));
	}
	
	public static int getIndex(){
		return index;
	}


	public static void setIndex(int i){
		index = i;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Modifica")) {
			System.out.println(nomeProdotto.getText() + " " + marcaProdotto.getText() + " " + idProdotto.getText() + " " + categoriaProdotto.getText() + prezzo.getText());
			Prodotto p = new Prodotto(nomeProdotto.getText(), marcaProdotto.getText(), idProdotto.getText(), categoriaProdotto.getText(), imagePath, Float.parseFloat(prezzo.getText()));
			prodotti.ProdottoManager.modificaProdotto(p, index);
			panelManager.setCurrentPanel(AdminPanel.TAG);
		}
	}
	
}