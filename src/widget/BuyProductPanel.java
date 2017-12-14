package widget;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.BevelBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import carrello.CarrelloManager;
import prodotti.Prodotto;
import prodotti.Prodotto3x2;
import utils.ImageLoader;


/**
 * Classe che implementa un widget per la presentazione di un prodotto per essere acquistato.
 * E' anche implementato il drag'n drop del componente per l'aggiunta al carrello
 * @author Michele Murgolo
 */
public class BuyProductPanel extends JPanel implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3847905024137278487L;

	/**
	 * @var product
	 * Riferimento al prodotto da mostrare
	 */
	protected Prodotto product;
	
	/**
	 * @var qtSpinner
	 * Spinner contenente il numero di prodotti da acquistare
	 */
	protected JSpinner qtSpinner;
	
	/**
	 * @var addCartButton
	 * Bottone per l'aggiunta al carrello
	 */
	protected JButton addCartButton;

    
    protected JLabel qtLabel;
    

    /**
     * @brief Costruttore
     * @param product Rifermimento al prodotto da mostrare
     */
	public BuyProductPanel(Prodotto product) {
		this.product = product;
		
		JLabel nameLabel = new JLabel();
		JLabel brandLabel = new JLabel();
		JLabel categoryLabel = new JLabel();
		JLabel priceLabel = new JLabel();
		JLabel imgLabel = new JLabel();
		JLabel offerLabel = new JLabel();
		qtLabel = new JLabel("Quantità:");
		
		nameLabel.setText(product.getNome());
		nameLabel.setFont(new Font(nameLabel.getFont().getFontName(), Font.BOLD, 36));
		
		brandLabel.setText("Prodotto da " + product.getMarca());

		categoryLabel.setText("in " + product.getCategoria());
		
		priceLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		offerLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		if(product.getSconto() > 0){
			offerLabel.setText(
					String.format(
						"<html>"
						+ "<font size='4'><strike>€ %.2f</strike><font color='red'>&nbsp;&nbsp;-%d%%</font></font>"
						+ "</html>", 
						product.getPrezzo(), product.getSconto()
					)
			);
			priceLabel.setText(String.format("<html>€ %.2f<html>", product.getPrezzoFinale()));
		}
		else if(product instanceof Prodotto3x2){
			offerLabel.setText(
					String.format(
						"<html>"
						+ "<font size='4' color='red'>Offerta 3x2</font>"
						+ "</html>"
					)
			);
			priceLabel.setText(String.format("<html>€ %.2f<html>", product.getPrezzo()));
		}
		else{
			priceLabel.setText(String.format("<html>€ %.2f<html>", product.getPrezzo()));
		}		
		priceLabel.setFont(new Font(nameLabel.getFont().getFontName(), Font.PLAIN, 28));

		imgLabel.setHorizontalAlignment(SwingConstants.CENTER);
		imgLabel.setVerticalAlignment(SwingConstants.CENTER);
		imgLabel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		imgLabel.setPreferredSize(new Dimension(192, 192));
		imgLabel.setMinimumSize(imgLabel.getPreferredSize());
		imgLabel.setMaximumSize(imgLabel.getPreferredSize());
		
		Image img = ImageLoader.loadImage(product.getImmagine(), imgLabel.getPreferredSize());
		if(img != null){
			imgLabel.setIcon(new ImageIcon(img));
		}
		else{
			imgLabel.setText("<html><p align='center'>Nessuna immagine<br>disponibile</p></html>");
		}
		
		qtSpinner = new JSpinner(new SpinnerNumberModel(1, 1, Short.MAX_VALUE, 1));
		
		addCartButton = new JButton(new ImageIcon(BuyProductPanel.class.getResource("/image/cart.png")));
		addCartButton.addActionListener(this);

		
		GroupLayout gLayout = new GroupLayout(this);
		setLayout(gLayout);
		
		gLayout.setAutoCreateContainerGaps(true);
		gLayout.setAutoCreateGaps(true);
		
		gLayout.setVerticalGroup(
				gLayout.createSequentialGroup()
					.addGap(16)
					.addGroup(
						gLayout.createParallelGroup()
							.addComponent(imgLabel)
							.addGroup(
									gLayout.createSequentialGroup()
										.addComponent(nameLabel)
										.addComponent(categoryLabel)
										.addGap(8)
										.addGroup(
												gLayout.createParallelGroup()
													.addComponent(brandLabel)
													.addComponent(offerLabel)
										)
										.addComponent(priceLabel)
										.addGap(16)
										.addGroup(
												gLayout.createParallelGroup(Alignment.CENTER)
													.addComponent(qtLabel)
													.addComponent(qtSpinner, 32, 32, 32)
													.addComponent(addCartButton)
										)			
							)
					)
					.addGap(16)
		);
		
		gLayout.setHorizontalGroup(
				gLayout.createSequentialGroup()
					.addGap(16)
					.addComponent(imgLabel)	
					.addGap(8)
					.addGroup(
							gLayout.createParallelGroup()
								.addComponent(nameLabel)
								.addComponent(categoryLabel)
								.addComponent(brandLabel)
					)
					.addGroup(
							gLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(
										gLayout.createParallelGroup()
											.addComponent(offerLabel)
											.addComponent(priceLabel)
								)
								.addGroup(
										gLayout.createSequentialGroup()
											.addComponent(qtLabel)
											.addComponent(qtSpinner, 48, 48, 48)
											.addComponent(addCartButton)
								)
					)
					.addGap(16)
		);
	}
	
	/**
	 * @brief Ritorna un riferimento al prodotto visualizzato
	 * @return Riferimento al prodotto
	 */
	public Prodotto getProduct(){
		return product;
	}

	public void actionPerformed(ActionEvent e) {
		//Add product to basket
		if(e.getSource().equals(addCartButton)){
			CarrelloManager.addProduct(product, (Integer) qtSpinner.getValue());
		}
	}

	
}
