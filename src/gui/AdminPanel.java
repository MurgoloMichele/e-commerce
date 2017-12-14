package gui;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;
import prodotti.ProdottoManager;
import widget.AdminProductView;
import widget.AdminViewModel;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;


public class AdminPanel extends CustomPanel {

	private static final long serialVersionUID = 8476981234655424394L;
	
	/**
	 * @var TAG
	 * Tag univoco utilizzato per identificare questa schermata
	 */
	public static final String TAG = "admin";

	/**
	 * @var addButton
	 * Pulsante per aggiungere un nuovo prodotto
	 */
	private JButton addButton;
	
	/**
	 * @var backButton
	 * Pulsante per tornare alla schermata precedente
	 */
	private JButton backButton;
	
	/**
	 * @var editButton
	 * Pulsante per modificare il prodotto selezionato
	 */
	private JButton editButton;
	
	/**
	 * @var deleteButton
	 * Pulsante per eliminare il prodotto selezionato
	 */
	private JButton deleteButton;
	
	/**
	 * @var productView
	 * Tabella dei prodotti
	 */
	private AdminProductView productView;
	
	public AdminPanel(PanelManager panelManager){
		super(panelManager);
		
		BorderLayout bLayout = new BorderLayout();
		setLayout(bLayout);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		
		addButton = createToolBarButton("/image/add.png", "Aggiungi prodotto");
		backButton = createToolBarButton("/image/back.png", "Esci");
		editButton = createToolBarButton("/image/edit.png", "Modifica prodotto");
		deleteButton = createToolBarButton("/image/delete.png", "Rimuovi prodotto");

		
		toolBar.add(backButton);
		toolBar.add(addButton);
		toolBar.add(editButton);
		toolBar.add(deleteButton);
		
		
		productView = new AdminProductView(new AdminViewModel());

		add(toolBar, BorderLayout.PAGE_START);	
		add(productView, BorderLayout.CENTER);
		
		
	}
	
	/**
	 * @brief All'ingresso della schermata ricarica i prodotti
	 */
	@Override
	public void onEnter() {
		//Carica i prodotti
		ProdottoManager.caricaProdotto();
		productView.refresh();
	}
	
	

	public void actionPerformed(ActionEvent e) {
		//Torna al pannello di login
		if(e.getSource().equals(backButton)){
			panelManager.setCurrentPanel(LoginPanel.TAG);
		}
		//Aggiunge un prodotto
		else if(e.getSource().equals(addButton)){
				panelManager.setCurrentPanel(AddProductPanel.TAG);
				productView.refresh();
			}
		//Modifica il prodotto selezionato
		else if(e.getSource().equals(editButton)){
			int index = productView.getSelectedRow();
			if(index != -1){
				EditProductPanel.setIndex(index);
				System.out.println(index);
				panelManager.setCurrentPanel(EditProductPanel.TAG);
				productView.refresh();
				}
			}
		//Elimina il prodotto selezionato
		else if(e.getSource().equals(deleteButton)){
			int index = productView.getSelectedRow();
			if(index != -1){
				int res = JOptionPane.showConfirmDialog(this, "Vuoi cancellare questo prodotto?", "Cancellare?", JOptionPane.YES_NO_OPTION);
				if(res == JOptionPane.YES_OPTION){
					ProdottoManager.rimuoviProdotto(index);
					ProdottoManager.salvaProdotto();
					productView.refresh();
				}
			}
		}
		
	}

}
