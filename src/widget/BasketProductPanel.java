package widget;


import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

import carrello.RimuoviProdotto;

import prodotti.Prodotto;
import prodotti.Prodotto3x2;
import utils.ImageLoader;

/**
 * Classe che implementa un widget per la presentazione di un prodotto inserito all'interno del carrello della spesa
 * @author Michele Murgolo
 */
public class BasketProductPanel extends JPanel implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3576236507660643005L;

	/**
	 * @var removeButton
	 * Bottone per la rimozione del prodotto dal carrello
	 */
	private JButton removeButton;
	
	/**
	 * @var product
	 * Riferimento al prodotto da visualizzare
	 */
	private Prodotto product;
	
	/**
	 * @var removeListener
	 * Riferimento al listener da invocare alla pressione del bottone di rimozione
	 */
	private RimuoviProdotto removeListener;
	
	/**
	 * @brief Costruttore
	 * @param product Prodotto da visualizzare
	 * @param qt Quantità associata al prodotto 
	 * @param removeListener Listener per intercettare la pressione del tasto di rimozione
	 */
	public BasketProductPanel(Prodotto product, int qt, RimuoviProdotto removeListener) {
		this.product = product;
		this.removeListener = removeListener;
		
		JLabel imgLabel = new JLabel();
		imgLabel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		imgLabel.setPreferredSize(new Dimension(150, 150));
		imgLabel.setMinimumSize(imgLabel.getPreferredSize());
		imgLabel.setMaximumSize(imgLabel.getPreferredSize());
		imgLabel.setHorizontalAlignment(SwingConstants.CENTER);
		imgLabel.setVerticalAlignment(SwingConstants.CENTER);
		imgLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		Image img = ImageLoader.loadImage(product.getImmagine(), imgLabel.getPreferredSize());
		if(img != null){
			imgLabel.setIcon(new ImageIcon(img));
		}
		else{
			imgLabel.setText("<html><p align='center'>Nessuna immagine<br>disponibile</p></html>");
		}
		
		JLabel nameLabel = new JLabel(product.getNome());
		nameLabel.setFont(new Font(nameLabel.getFont().getFontName(), Font.BOLD, 28));
		nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		JLabel priceLabel = new JLabel();
		priceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

		
		if(product.getSconto() > 0){
			priceLabel.setText(
					String.format(
						"<html>"
						+ "Prezzo: <font size='2'><strike>€ %.2f</strike>"
						+ "<font color='red'>&nbsp;&nbsp;-%d%%&nbsp;&nbsp;&nbsp;</font>"
						+ "</font>"
						+ "€ %.2f"
						+ "</html>", 
						product.getPrezzo(), product.getSconto(), product.getPrezzoFinale()
					)
			);
		}
		else if(product instanceof Prodotto3x2){
			priceLabel.setText(
					String.format(
						"<html>"
						+ "Prezzo: <font color='red'>Offerta 3x2 &nbsp;&nbsp;</font>"
						+ "€ %.2f"
						+ "</html>",
						product.getPrezzo()
					)
			);
		}
		else{
			priceLabel.setText(String.format("<html>Prezzo: € %.2f<html>", product.getPrezzo()));
		}		
		
		JLabel qtLabel = new JLabel("<html>Quantità: " + qt + "</html>");
		qtLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

		
		JLabel totLabel = new JLabel(String.format("<html>Totale: € %.2f </html>", product.getTotale(qt)));
		totLabel.setFont(new Font(totLabel.getFont().getFontName(), Font.PLAIN, 20));
		totLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

		removeButton = new JButton("Rimuovi dal carrello");
		removeButton.addActionListener(this);
		removeButton.setAlignmentX(Component.CENTER_ALIGNMENT);

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		add(imgLabel);
		add(nameLabel);
		add(priceLabel);
		add(qtLabel);
		add(totLabel);
		add(removeButton);
				
	}
	
	/**
	 * @brief Ritorna il riferimento al prodotto visualizzato
	 * @return Riferimento al prodotto
	 */
	public Prodotto getProduct() {
		return product;
	}

	public void actionPerformed(ActionEvent e) {
		//Call the remove listener function
		if(e.getSource().equals(removeButton)){
			if(removeListener != null){
				removeListener.onBasketProductRemoved((RimuoviProdotto) this);
			}
		}
	}

}
