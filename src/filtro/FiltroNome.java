package filtro;

import prodotti.Prodotto;


/**
 * Filtro di ricerca prodotti in base al nome
 * @author Michele Murgolo
 */
public class FiltroNome extends FiltroProdotti{
	
	/**
	 * @var brand
	 * Stringa contenente il nome che si desidera filtrare
	 */
	private String name;
	
	
	/**
	 * @brief Costruttore con parametri
	 * @param name Nome che si desidera filtrare
	 */
	public FiltroNome(String name) {
		this.name = name;
	}

	/**
	 * @brief Implementazione del metodo astratto della superclasse
	 * @return true se il filtro è nullo o se il nome del prodotto contiene il nome cercato
	 */
	@Override
	public boolean match(Prodotto p) {
		return name.isEmpty() || p.getNome().contains(name);
	}

}
