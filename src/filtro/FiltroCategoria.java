package filtro;

import prodotti.Prodotto;


/**
 * Filtro di ricerca prodotti in base alla categoria
 * @author Michele Murgolo
 */
public class FiltroCategoria extends FiltroProdotti{

	/**
	 * @var ALL_CATEGORIES
	 * Stringa jolly per indicare tutte le possibili categorie
	 */
	public static final String ALL_CATEGORIES = "Tutte";
	
	/**
	 * @var brand
	 * Stringa contenente la categoria che si desidera filtrare
	 */
	private String category;
	
	
	/**
	 * @brief Costruttore con parametri
	 * @param category Categoria che si desidera filtrare
	 */
	public FiltroCategoria(String categoria) {
		this.category = categoria;
	}

	/**
	 * @brief Implementazione del metodo astratto della superclasse
	 * @return true se la categoria del prodotto è uguale a quella filtrata o se il filtro è impostato su tutte le marche
	 */
	@Override
	public boolean match(Prodotto p) {
		return category.equals(ALL_CATEGORIES) || p.getCategoria().equals(category);
	}

	
	
}
