package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import widget.RemoveProductPanel;
import carrello.CarrelloManager;




/**
 * Classe che implementa la schermata di gestione dei prodotti nel carrello
 * @author Michele Murgolo
 */
public class CartPanel extends CustomPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8514692706131340645L;

	/**
	 * @var TAG
	 * Tag univoco utilizzato per identificare questa schermata
	 */
	public static final String TAG = "cart";

	/**
	 * @var backButton
	 * Bottone per tornare alla schermata precedente
	 */
	private JButton backButton;
	
	/**
	 * @var clearBasket
	 * Bottone per rimuovere tutti i prodotti nel carrello
	 */
	private JButton clearBasket;
	
	/**
	 * @var buyButton
	 * Bottone per passare alla schermata di acquisto
	 */
	private JButton buyButton;
	
	/**
	 * @var totLabel
	 * Label contenente il totale dei prodtti nel carrello
	 */
	private static JLabel totLabel;
	
	/**
	 * @var productsPanel
	 * Pannello contenente tutti i prodotti
	 */
	public static JPanel productsPanel;
		
	/**
	 * Costruttore
	 * @param panelManager Finestra
	 */
	public CartPanel(PanelManager panelManager) {
		super(panelManager);
	
		BorderLayout bLayout = new BorderLayout();
		setLayout(bLayout);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);

		backButton = createToolBarButton("/image/back.png", "Indietro");
		clearBasket = createToolBarButton("/image/clearcart.png", "Svuota carrello");
		buyButton = createToolBarButton("/image/pay.png", "Conferma acquisto");
		
		totLabel = new JLabel("0.00");
		
		toolBar.add(backButton);
		toolBar.addSeparator();
		toolBar.add(clearBasket);
		toolBar.add(Box.createHorizontalGlue());
		toolBar.add(new JLabel("Totale: € "));
		toolBar.add(totLabel);
		toolBar.addSeparator();
		toolBar.add(buyButton);
	
		
		productsPanel = new JPanel();
	
		add(toolBar, BorderLayout.PAGE_START);
		add(new JScrollPane(productsPanel), BorderLayout.CENTER);
	}
	
	/**
	 * @brief Carica tutti prodotti presenti nel carrello
	 */
	@Override
	public void onEnter() {
		//Se il carrello è sporco
		if(CarrelloManager.isDirty()){
			//Elimina tutti i prodotti
			productsPanel.removeAll();
			//Carica tutti i nuovi prodotti
			for(int i = 0; i < CarrelloManager.getCount(); i++){
				productsPanel.add(new RemoveProductPanel(CarrelloManager.getProduct(i)));
			}
			//Pulisce il carrello
			CarrelloManager.setDirty(false);
		}
		
		reloadTotal();
	}
	
	/**
	 * @brief Calcolo del costo totale dei prodotti
	 */
	public static void reloadTotal() {
		float total = 0;
		for(int i = 0; i < CarrelloManager.getCount(); i++){
			total += CarrelloManager.getProduct(i).getTotale(CarrelloManager.getProductQuantity(i));
		}
		totLabel.setText(String.format("%.2f", total));
	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(backButton)){
			panelManager.setCurrentPanel(MemberPanel.TAG);
		}
		
		if(e.getSource().equals(clearBasket)){
			CarrelloManager.clear();
			reloadTotal();
			productsPanel.removeAll();
			productsPanel.revalidate();
			productsPanel.repaint();
		}
		
		else if(e.getSource().equals(buyButton)){
			if(CarrelloManager.getCount() > 0){
				panelManager.setCurrentPanel(PayPanel.TAG);
			}
			else{
				JOptionPane.showMessageDialog(this, "Impossibile procedere all'acquisto: il carrello è vuoto", "Errore", JOptionPane.ERROR_MESSAGE);
			}
		}
		else{
			reloadTotal();
		}
	}
	
}