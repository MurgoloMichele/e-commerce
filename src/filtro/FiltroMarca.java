package filtro;

import prodotti.Prodotto;



/**
 * Filtro di ricerca prodotti in base alla marca
 * @author Michele Murgolo
 */
public class FiltroMarca extends FiltroProdotti{

	/**
	 * @var ALL_BRANDS
	 * Stringa jolly per indicare tutte le possibili marche
	 */
	public static final String ALL_BRANDS = "Tutte";
	
	/**
	 * @var brand
	 * Stringa contenente la marca che si desidera filtrare
	 */
	private String brand;
	
	
	/**
	 * @brief Costruttore con parametri
	 * @param brand Marca che si desidera filtrare
	 */
	public FiltroMarca(String brand) {
		this.brand = brand;
	}

	/**
	 * @brief Implementazione del metodo astratto della superclasse
	 * @return true se la marca del prodotto è uguale a quella filtrata o se il filtro è impostato su tutte le marche
	 */
	@Override
	public boolean match(Prodotto p) {
		return brand.equals(ALL_BRANDS) || p.getMarca().equals(brand);
	}
	
	
}