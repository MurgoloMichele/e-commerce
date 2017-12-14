package carrello;


/**
 * Interfaccia utilizzata per implementare un metodo di callback per la pressione del tasto rimuovi in BasketProductPanel
 * @author Michele Murgolo
 */
public interface RimuoviProdotto {

	/**
	 * @brief Funzione di callback invocata da un'istanza di BasketProductPanel quando il bottone rimuovi è stato premuto
	 * @param basketProductPanel Istanza di BasketProductPanel in cui è stato premuto il bottone rimuovi
	 */
	public void onBasketProductRemoved(RimuoviProdotto basketProductPanel);
}
