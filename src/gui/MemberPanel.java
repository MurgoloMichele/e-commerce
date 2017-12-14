package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Vector;

import javax.swing.*;

import filtro.FiltroCategoria;
import filtro.FiltroNome;
import filtro.FiltroProdotti;

import prodotti.Prodotto;
import prodotti.ProdottoManager;
import widget.BuyProductPanel;

/**
 * Classe che implementa la schermata per i clienti
 * @author Michele Murgolo
 */
public class MemberPanel extends CustomPanel{

	private static final long serialVersionUID = 1L;
	
	public static final String TAG = "member";
	
	/**
	 * @var backButton
	 * Bottone per tornare alla schermata precedente
	 */
	private JButton backButton;
	
	/**
	 * @var nameField
	 * Casella di testo per ricerca rapida tramite nome
	 */
	private JTextField nameField;
	
	/**
	 * @var categoryCombo
	 * Combobox per filtrare le categorie
	 */
	private JComboBox<String> categoryCombo;
	
	/**
	 * @var searchButton
	 * Bottone per la ricerca rapida
	 */
	private JButton searchButton;
	
	/**
	 * @var filterButton
	 * Bottone per aprire la dialog di ricerca avanzata
	 */
	private JButton filterButton;
	
	/**
	 * @var removeFilterButton
	 * Bottone per rimuovere tutti i filtri di ricerca applicati
	 */
	private JButton removeFilterButton;
	
	/**
	 * @var basketButton
	 * Bottone per andare alla schermata del carrello
	 */
	private JButton basketButton;
	
	/**
	 * @var productsPanel
	 * Pannello contenente tutti i prodotti
	 */
	private static JPanel productsPanel;
	
	public MemberPanel(PanelManager panelManager){
		super(panelManager);
		
		BorderLayout bLayout = new BorderLayout();
		setLayout(bLayout);

		JLabel searchLabel = new JLabel("Cerca: ");
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		
		backButton = createToolBarButton("/image/back.png", "Esci");
		nameField = new JTextField(20);
		nameField.setMaximumSize(new Dimension(4096*2, 24));
		categoryCombo = new JComboBox<String>();
		categoryCombo.setMaximumSize(new Dimension(4096, 24));

		searchButton = createToolBarButton("/image/search.png", "Cerca");
		filterButton = createToolBarButton("/image/filter.png", "Applica filtri di ricerca");
		removeFilterButton = createToolBarButton("/image/removefilter.png", "Rimuovi tutti i filtri di ricerca");

		basketButton = createToolBarButton("/image/gotocart.png", "Carrello");
		toolBar.add(backButton);
		toolBar.addSeparator();
		toolBar.add(searchLabel);
		toolBar.add(nameField);
		toolBar.add(categoryCombo);
		toolBar.add(searchButton);
		toolBar.add(filterButton);
		toolBar.add(removeFilterButton);
		toolBar.add(Box.createHorizontalGlue()); // After this every component will be added to the right
		toolBar.add(basketButton);
		
		productsPanel = new JPanel();
		productsPanel.setLayout(new GridBagLayout());		

		add(toolBar, BorderLayout.PAGE_START);		
		add(new JScrollPane(productsPanel), BorderLayout.CENTER);
		

	}
	
	
	public void onEnter(){
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.weightx = 1f;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(4, 8, 4, 8);
		
		//Rimuove tutti i prodotti
		productsPanel.removeAll();
		
		//Carica tutti i prodotti
		for(int i = 0; i < ProdottoManager.getNumeroProdotti(); i++){
			c.gridy = i;
			productsPanel.add(new BuyProductPanel(ProdottoManager.getProdotto(i)), c);
		}
		
		//Carica le categorie
		Vector<String> categories = ProdottoManager.getListaCategoriaProdotto();
		categories.add(0, FiltroCategoria.ALL_CATEGORIES);
		categoryCombo.setModel(new DefaultComboBoxModel<String>(categories));
		
	}
	
	


	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(basketButton)){
			panelManager.setCurrentPanel(CartPanel.TAG);
		}
		
		if(e.getSource().equals(searchButton)){
			Vector<FiltroProdotti> filters = new Vector<FiltroProdotti>();
			filters.add(new FiltroNome(nameField.getText()));
			filters.add(new FiltroCategoria((String)categoryCombo.getSelectedItem()));
			applyFilters(filters);
		}
		
		if(e.getSource().equals(backButton)){
			panelManager.setCurrentPanel(LoginPanel.TAG);
		}

		if(e.getSource().equals(filterButton)){
			panelManager.setCurrentPanel(FilterPanel.TAG);
			
			
		}
		
		if(e.getSource().equals(removeFilterButton)){
			removeAllFilters();
		}
		
	}
	
	/**
	 * @brief Rimuove tutti i filtri applicati
	 */
	private void removeAllFilters(){
		nameField.setText("");
		categoryCombo.setSelectedIndex(0);
		
		showAllProducts();
	}
	
	/**
	 * @brief Mostra tutti i prodotti
	 */
	private static void showAllProducts() {
		for(Component c : productsPanel.getComponents()){
			c.setVisible(true);
		}
	}
	
	/**
	 * @brief Applica i filtri selezionati
	 */
	protected static void applyFilters(Vector<FiltroProdotti> filters) {
		Prodotto p;
		
		showAllProducts();
		
		for(FiltroProdotti filter : filters){
			for(Component c : productsPanel.getComponents()){
				if(c.isVisible()){
					p = ((BuyProductPanel)c).getProduct();
					c.setVisible(filter.match(p));
				}
			}
		}
	}
	
}
