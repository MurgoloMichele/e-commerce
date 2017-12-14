package filtro;

import prodotti.Prodotto;



/**
 * Filtro di ricerca prodotti in base ad una fascia di prezzi
 * @author Michele Murgolo
 */
public class FiltroPrezzo extends FiltroProdotti{

	/**
	 * @var minPrice
	 * Prezzo minimo
	 */
	private int minPrice;
	
	/**
	 * @var maxPrice
	 * Prezzo massimo
	 */
	private int maxPrice;
	
	/**
	 * @brief Costruttore con parametri
	 * @param minPrice Prezzo minimo
	 * @param maxPrice Prezzo massimo
	 */
	public FiltroPrezzo(int minPrice, int maxPrice) {
		this.minPrice = minPrice;
		this.maxPrice = maxPrice;
	}

	/**
	 * @brief Implementazione del metodo astratto della superclasse
	 * @return true se il prezzo del prodotto rientra nella fascia di prezzi del filtro
	 */
	public boolean match(Prodotto p) {
		return p.getPrezzoFinale() >= minPrice && p.getPrezzoFinale() <= maxPrice;
	}

	
	
}
