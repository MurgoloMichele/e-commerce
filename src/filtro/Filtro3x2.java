package filtro;

import prodotti.Prodotto;
import prodotti.Prodotto3x2;



/**
 * Filtro di ricerca prodotti in base all'offerta 3x2
 * @author Michele Murgolo
 */
public class Filtro3x2 extends FiltroProdotti{

	/**
	 * @brief Implementazione del metodo astratto della superclasse
	 * @return true se il prodotto è un'istanza di Prodotto3x2
	 */
	@Override
	public boolean match(Prodotto p) {
		return p instanceof Prodotto3x2;
	}
	
	
}
