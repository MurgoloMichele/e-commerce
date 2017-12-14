package prodotti;

/**
 * Classe che rappresenta un singolo prodotto scontato
 * @author Michele Murgolo
 */
public class ProdottoScontato extends Prodotto{

	private static final long serialVersionUID = 1L;

	/**
	 * @var sconto
	 * Percentuale intera di sconto
	 */
	private int sconto;
	
	/**
	 * @brief Setta lo sconto. Reimplementazione del metodo della classe padre Prodotto
	 */
	@Override
	public void setSconto(int sconto){
		this.sconto = sconto;
	}
	
	/**
	 * @brief Ritorna lo sconto. Reimplementazione del metodo della classe padre Prodotto
	 * @return Sconto prodotto
	 */
	@Override
	public int getSconto(){
		return sconto;
	}
}
