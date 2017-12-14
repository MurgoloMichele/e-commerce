package carrello;

import java.util.HashMap;

import prodotti.Prodotto;



/**
 * Classe contentente metodi statici per la gestione del singleton rappresentante il carrello dell'utente  
 * @author Michele Murgolo
 */
public class CarrelloManager {

	/**
	 * @var isDirty
	 * Valore booleano che indica se il carrello è stato modificato dall'ultima visualizzazione
	 */
	private static boolean isDirty = true;
	
	/**
	 * @var basket
	 * Hashmap che associa ad ogni prodotto nel carrello la sua quantità
	 */
	private static HashMap<Prodotto, Integer> basket = new HashMap<Prodotto, Integer>();
	
	/**
	 * @brief Costruttutore private per evitare la creazione di un'istanza
	 */
	private CarrelloManager() {
	
	}
	
	/**
	 * @brief Aggiunge un prodotto al carrello con la relativa quantità
	 * La funzione "sporca" il carrello
	 * @param product Istanza del prodotto da inserire
	 * @param qt Quantità da aggiungere al carrello
	 */
	public static void addProduct(Prodotto p, int qt){
		basket.put(p, qt);
		System.out.println(p.hashCode());
		isDirty = true;
	}
	
	/**
	 * @brief Rimuove un prodotto dal carrello con la relativa quantità
	 * La funzione "sporca" il carrello
	 * @param product Istanza del prodotto da rimuovere
	 */
	public static void removeProduct(Prodotto p){
		basket.remove(p);
		isDirty = true;
	}
	
	/**
	 * @brief Rimuove tutti i prodotti dal carrello con le relative quantità
	 * La funzione "sporca" il carrello
	 */
	public static void clear(){
		basket.clear();
		isDirty = true;
	}
	
	/**
	 * @brief Ritorna il numero di prodotti distinti presenti nel carrello
	 * @return Numero di prodotti nel carrello
	 */
	public static int getCount(){
		return basket.size();
	}
	
	/**
	 * @brief Controlla se il carrello è sporco
	 * @return Valore booleano rappresentante se il carrello è sporco o no
	 **/
	public static boolean isDirty(){
		return isDirty;
	}
	
	/**
	 * @brief Setta lo stato corrente del carrello
	 * @param dirty Stato del carrello
	 */
	public static void setDirty(boolean dirty){
		isDirty = dirty;
	}
	
	/**
	 * @brief Ritorna un prodotto dato l'indice dell'array
	 * @param index Indice del prodotto
	 * @return Istanza di prodotto
	 */
	public static Prodotto getProduct(int index){
		return (Prodotto)basket.keySet().toArray()[index];
	}
	
	/**
	 * @brief Ritorna la quantità di un prodotto individuato dall'indice dell'array
	 * @param index Indice del prodotto
	 * @return Quantità del prodotto
	 */
	public static int getProductQuantity(int index){
		return (Integer)basket.values().toArray()[index];
	}
	
}
