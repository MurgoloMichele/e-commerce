package widget; 

import java.awt.Dimension;
import java.awt.Image;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.table.AbstractTableModel;

import prodotti.Prodotto;
import prodotti.Prodotto3x2;
import prodotti.ProdottoManager;
import utils.ImageLoader;

/**
 * Classe che implementa il modello della tabella di gestione dei prodotti inserita nell' {@link AdminPanel.java}
 * @author Michele Murgolo
 */
public class AdminViewModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 318141372309160439L;

	/**
	 * @var IMG_COL
	 * Indice della colonna contenente le immagini dei prodotti
	 */
	public static final int IMG_COL = 0;
	
	/**
	 * @var CODE_COL
	 * Indice della colonna contenente il codice dei prodotti
	 */
	public static final int CODE_COL = 1;
	
	/**
	 * @var NAME_COL
	 * Indice della colonna contenente il nome dei prodotti
	 */
	public static final int NAME_COL = 2;
	
	/**
	 * @var BRAND_COL
	 * Indice della colonna contenente la marca dei prodotti
	 */
	public static final int BRAND_COL = 3;
	
	/**
	 * @var CATEGORY_COL
	 * Indice della colonna contenente la categoria dei prodotti
	 */
	public static final int CATEGORY_COL = 4;
	
	/**
	 * @var PRICE_COL
	 * Indice della colonna contenente il prezzo dei prodotti
	 */
	public static final int PRICE_COL = 5;
	
	/**
	 * @var OFFER_COL
	 * Indice della colonna contenente le offerte dei prodotti 
	 */
	public static final int OFFER_COL = 6;
	
	/**
	 * @var productsImgCache
	 * HashMap che associa ad ogni path l'immagine relativa.
	 */
	private HashMap<String, ImageIcon> productsImgCache;

	/**
	 * @brief Costruttore
	 */
	public AdminViewModel() {
		super();
		productsImgCache = new HashMap<String, ImageIcon>();
	}
	
	/**
	 * @brief Implementazione del metodo di {@link AbstractTableModel}
	 * @return Numero di righe della tabella
	 */
	public int getRowCount() {
		return ProdottoManager.getNumeroProdotti();
	}

	/**
	 * @brief Implementazione del metodo di {@link AbstractTableModel}
	 * @return Numero di colonne della tabella
	 */
	public int getColumnCount() {
		return 7;
	}
	
	/**
	 * @brief Implementazione del metodo di {@link AbstractTableModel}
	 * @param columnIndex Indice della colonna
	 * @return Tipologia di classe contenuta in una specifica colonna
	 */
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		if(columnIndex == IMG_COL){
			return ImageIcon.class;
		}
		return String.class;
	}

	/**
	 * @brief Implementazione del metodo di {@link AbstractTableModel}
	 * @param rowIndex Indice della riga
	 * @param columnIndex Indice della colonna
	 * @return Ritorna l'oggetto contenuto in una cella della tabella
	 */
	public Object getValueAt(int rowIndex, int columnIndex) {
		Prodotto prodotto = ProdottoManager.getProdotto(rowIndex);
		
		if(prodotto != null){
			switch (columnIndex) {
				case IMG_COL:
					//If the icon is already loaded
					if(productsImgCache.containsKey(prodotto.getImmagine())){
						//Return the cached img
						return productsImgCache.get(prodotto.getImmagine());
					}
					
					//Else try to load the image
					Image img = ImageLoader.loadImage(prodotto.getImmagine(), new Dimension(100, 100));
					//If the img exists
					if(img != null){
						//Add the img to the cache
						ImageIcon icon = new ImageIcon(img);
						productsImgCache.put(prodotto.getImmagine(), icon);
						return icon;
					}
					return null;
					
				case CODE_COL:
					return prodotto.getIdProdotto();
					
				case NAME_COL:
					return prodotto.getNome();
					
				case BRAND_COL:
					return prodotto.getMarca();
					
				case CATEGORY_COL:
					return prodotto.getCategoria();
					
				case PRICE_COL:
					return "€" + String.format("%.2f", prodotto.getPrezzo());
					
				case OFFER_COL:
					if(prodotto instanceof Prodotto3x2){
						return "3x2";
					}
					else if(prodotto.getSconto() > 0){
						return Integer.toString(prodotto.getSconto()) + "%";
					}
					return "";
			}
		}
		
		return "";
	}
	
	/**
	 * @brief Implementazione del metodo di {@link AbstractTableModel}
	 * @param columnIndex Indice della colonna
	 * @return Ritorna il nome della colonna 
	 */
	@Override
	public String getColumnName(int column) {
		switch (column) {
			case IMG_COL:
				return "Immagine";
		
			case CODE_COL:
				return "Codice";
				
			case NAME_COL:
				return "Nome";
				
			case BRAND_COL:
				return "Marca";
				
			case CATEGORY_COL:
				return "Categoria";
				
			case PRICE_COL:
				return "Prezzo";
				
			case OFFER_COL:
				return "Offerta";
		}	
		
		return "";
	}
	
	/**
	 * @brief Implementazione del metodo di {@link AbstractTableModel}
	 * @return Disabilita la modifica delle celle
	 */
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

}
