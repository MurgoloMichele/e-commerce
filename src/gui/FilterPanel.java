package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import filtro.Filtro3x2;
import filtro.FiltroCategoria;
import filtro.FiltroMarca;
import filtro.FiltroNome;
import filtro.FiltroPrezzo;
import filtro.FiltroProdotti;
import filtro.FiltroSconto;

import prodotti.ProdottoManager;
/**
 * Dialog per la selezione dei filtri da applicare alla visualizzazione dei prodotti
 * @author Michele Murgolo
 */
public class FilterPanel extends CustomPanel implements ActionListener, ItemListener, ChangeListener{

	/**
	 * @var TAG
	 * Tag univoco utilizzato per identificare questa schermata
	 */
	public static final String TAG = "filtro";
	
	private static final long serialVersionUID = 6965027352662405919L;

	/**
	 * @var filters
	 * Vettore contenente tutti i filtri da applicare 
	 */
	public static Vector<FiltroProdotti> filters = null;

	/**
	 * @var nameField
	 * Casella di testo contenente una stringa da ricercare all'interno del nome dei prodotti
	 */
	private JTextField nameField;
	
	/**
	 * @var categoryCombo
	 * Combobox contenente le categorie dei prodotti
	 */
	private JComboBox<String> categoryCombo;
	
	/**
	 * @var brandsCombo
	 * Combobox contenente le marche dei prodotti
	 */
	private JComboBox<String> brandsCombo;
	
	/**
	 * @var priceCheck
	 * Checkbox per l'abilitazione del ricerca per prezzo
	 */
	private JCheckBox priceCheck;
	
	/**
	 * @var minPriceSpinner
	 * Spinner contenente il prezzo minimo da cercare
	 */
	private JSpinner minPriceSpinner;
	
	/**
	 * @var maxPriceSpinner
	 * Spinner contenente il prezzo massimo da cercare
	 */
	private JSpinner maxPriceSpinner;
	
	/**
	 * @var normalCheck
	 * Radio per filtrare i prodotti senza offerte
	 */
	private JRadioButton normalCheck;
	
	/**
	 * @var discountCheck
	 * Radio per filtrare i prodotti con sconto
	 */
	private JRadioButton discountCheck;
	
	/**
	 * @var threextwoCheck
	 * Radio per filtrare i prodotti con offerta 3x2
	 */
	private JRadioButton threextwoCheck;
	
	/**
	 * @var applyButton
	 * Bottone di conferma dei filtri
	 */
	private JButton applyButton;
	
	/**
	 * @var cancelButton
	 * Bottone di annullamento
	 */
	private JButton cancelButton;

	
	public FilterPanel(PanelManager panelManager) {
		super(panelManager);
		
		JPanel jp = new JPanel();
		GroupLayout gLayout = new GroupLayout(jp);
		jp.setLayout(gLayout);
		
		JLabel nameLabel = new JLabel("Nome:");
		JLabel categoryLabel = new JLabel("Categoria:");
		JLabel brandLabel = new JLabel("Marca:");
		JLabel priceLabel = new JLabel("Prezzo:");
		JLabel sepLabel = new JLabel("-");
		JLabel offerLabel = new JLabel("Offerte:");

		nameField = new JTextField(20);
		
		Vector<String> categories = ProdottoManager.getListaCategoriaProdotto();
		categories.add(0, FiltroCategoria.ALL_CATEGORIES);
		categoryCombo = new JComboBox<String>(new DefaultComboBoxModel<String>(categories));
		
		Vector<String> brands = ProdottoManager.getListaMarcaProdotto();
		brands.add(0, FiltroMarca.ALL_BRANDS);
		brandsCombo = new JComboBox<String>(new DefaultComboBoxModel<String>(brands));
		
		priceCheck = new JCheckBox();
		priceCheck.addItemListener(this);
		minPriceSpinner = new JSpinner(new SpinnerNumberModel(0, 0, Short.MAX_VALUE, 1));
		minPriceSpinner.setEnabled(false);
		minPriceSpinner.addChangeListener(this);
		maxPriceSpinner = new JSpinner(new SpinnerNumberModel(1, 1, Short.MAX_VALUE, 1));
		maxPriceSpinner.setEnabled(false);
		maxPriceSpinner.addChangeListener(this);
		
		normalCheck = new JRadioButton("Nessuna", true);
		discountCheck = new JRadioButton("Scontato");
		threextwoCheck = new JRadioButton("3x2");
		ButtonGroup bt = new ButtonGroup();
		bt.add(normalCheck);
		bt.add(discountCheck);
		bt.add(threextwoCheck);
	
		applyButton = new JButton("Applica");
		applyButton.addActionListener(this);
		
		cancelButton = new JButton("Annulla");
		cancelButton.addActionListener(this);
		
		
		
		gLayout.setAutoCreateContainerGaps(true);
		gLayout.setAutoCreateGaps(true);
		
		gLayout.setVerticalGroup(
				gLayout.createSequentialGroup()
					.addGroup(
							gLayout.createParallelGroup()
								.addComponent(nameLabel)
								.addComponent(nameField)
					)
					.addGroup(
							gLayout.createParallelGroup()
								.addComponent(categoryLabel)
								.addComponent(categoryCombo)
					)
					.addGroup(
							gLayout.createParallelGroup()
								.addComponent(brandLabel)
								.addComponent(brandsCombo)
					)
					.addGroup(
							gLayout.createParallelGroup()
								.addComponent(priceLabel)
								.addComponent(priceCheck)
								.addComponent(minPriceSpinner)
								.addComponent(sepLabel)
								.addComponent(maxPriceSpinner)
					)
					.addGroup(
							gLayout.createParallelGroup()
								.addComponent(offerLabel)
								.addComponent(normalCheck)
								.addComponent(discountCheck)
								.addComponent(threextwoCheck)
					)
					.addGroup(
							gLayout.createParallelGroup()
								.addComponent(applyButton)
								.addComponent(cancelButton)
					)
		);
		
		gLayout.setHorizontalGroup(
				gLayout.createParallelGroup(Alignment.CENTER)
					.addGroup(
						gLayout.createSequentialGroup()
							.addGroup(
									gLayout.createParallelGroup()
									.addComponent(nameLabel)
									.addComponent(categoryLabel)
									.addComponent(brandLabel)
									.addComponent(priceLabel)
									.addComponent(offerLabel)
							)
							.addGroup(
									gLayout.createParallelGroup()
									.addComponent(nameField)
									.addComponent(categoryCombo)
									.addComponent(brandsCombo)
									.addGroup(
											gLayout.createSequentialGroup()
												.addComponent(priceCheck)
												.addComponent(minPriceSpinner, 64, 64, 64)
												.addComponent(sepLabel)
												.addComponent(maxPriceSpinner, 64, 64, 64)
												
									)
									.addGroup(
											gLayout.createSequentialGroup()
												.addComponent(normalCheck)
												.addComponent(discountCheck)
												.addComponent(threextwoCheck)	
									)
						)
					)
					.addGroup(
							gLayout.createSequentialGroup()
								.addComponent(applyButton)
								.addComponent(cancelButton)
					)
				
		);
	
		jp.add(nameLabel);
		jp.add(categoryLabel);
		jp.add(brandLabel);
		jp.add(priceLabel);
		jp.add(sepLabel);
		jp.add(offerLabel);
		
		jp.add(nameField);
		jp.add(categoryCombo);
		jp.add(brandsCombo);
		jp.add(priceCheck);
		jp.add(minPriceSpinner);
		jp.add(maxPriceSpinner);
		jp.add(normalCheck);
		jp.add(discountCheck);
		jp.add(threextwoCheck);
		
		jp.add(applyButton);
		jp.add(cancelButton);
		
		
		add(jp);
		
	

	}

	
	
	/**
	 *@brief Costruttore della dialog
	 */
	
		
	/**
	 * @brief Ritorna un vettore contenente i filtri da applicare
	 * @return Vettore dei filtri da applicare
	 */
	public static Vector<FiltroProdotti> getFilters(){
		return filters;
	}

	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(cancelButton)){
			panelManager.setCurrentPanel(MemberPanel.TAG);
		}
		else if(e.getSource().equals(applyButton)){
			//Aggiunge i filtri da applicare
			filters = new Vector<FiltroProdotti>();
			filters.add(new FiltroNome(nameField.getText()));
			filters.add(new FiltroMarca((String)brandsCombo.getSelectedItem()));
			filters.add(new FiltroCategoria((String)categoryCombo.getSelectedItem()));
			
			if(priceCheck.isSelected()){
				filters.add(new FiltroPrezzo((Integer)minPriceSpinner.getValue(), (Integer)maxPriceSpinner.getValue()));
			}
			else if(discountCheck.isSelected()){
				filters.add(new FiltroSconto());
			}
			else if(threextwoCheck.isSelected()){
				filters.add(new Filtro3x2());
			}		
			
			panelManager.setCurrentPanel(MemberPanel.TAG);
			MemberPanel.applyFilters(filters);
		}
	}
	


	public void stateChanged(ChangeEvent e) {
		//Mantiene il prezzo minimo sempre minore del prezzo massimo
		if(e.getSource().equals(minPriceSpinner)){
			if((Integer)minPriceSpinner.getValue() > (Integer)maxPriceSpinner.getValue()){
				maxPriceSpinner.setValue((Integer)minPriceSpinner.getValue() + 1);
			}
		}
		else if(e.getSource().equals(maxPriceSpinner)){
			if((Integer)minPriceSpinner.getValue() > (Integer)maxPriceSpinner.getValue()){
				minPriceSpinner.setValue((Integer)maxPriceSpinner.getValue() - 1);
			}
		}
	}

	public void itemStateChanged(ItemEvent e) {
		if(e.getSource().equals(priceCheck)){
			minPriceSpinner.setEnabled(priceCheck.isSelected());
			maxPriceSpinner.setEnabled(priceCheck.isSelected());
		}
	}
	
}
