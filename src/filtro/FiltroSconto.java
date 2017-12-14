package filtro;

import prodotti.Prodotto;

/**
 * Filtro di ricerca prodotti in base allo sconto
 * @author Michele Murgolo
 */
public class FiltroSconto extends FiltroProdotti{
	
	/**
	 * @brief Implementazione del metodo astratto della superclasse
	 * @return true se il prodotto presenta uno sconto
	 */
	@Override
	public boolean match(Prodotto p) {
		return p.getSconto() > 0;
	}

	
	
}

