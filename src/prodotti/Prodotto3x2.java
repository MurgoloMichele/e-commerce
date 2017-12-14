package prodotti;

/**
 * Classe che rappresenta un singolo prodotto con offerta 3x2
 * @author Michele Murgolo
 */
public class Prodotto3x2 extends Prodotto{

	private static final long serialVersionUID = 9019674693899738287L;

	@Override
	/**
	 * @brief Ritorna il totale soggetto all'offerta 3x2.
	 * Reimplementazione del metodo della classe Product
	 * @return Totale
	 */
	public float getTotale(int n) {
		float tot = getPrezzoFinale() * n;
		return tot - getPrezzoFinale() * (n / 3);
	}
		
}
