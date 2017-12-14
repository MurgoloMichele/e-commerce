package filtro;

import prodotti.Prodotto;

/**
 * Classe base per la creazione di filtri ricerca dei prodotti
 * @author Michele Murgolo
 */
public abstract class FiltroProdotti {

	/**
	 * @brief Funzione astratta da implementare nelle specializzazioni.
	 * La funzione ritorna true se rispetta le condizioni del filtro di ricerca altrimenti false.
	 * @param product Prodotto da controllare
	 * @return true se il prodotto rispetta le condizione del filtro
	 */
	public abstract boolean match(Prodotto p);
	
}
