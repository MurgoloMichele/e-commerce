package prodotti;

import java.io.Serializable;

/**
 * Classe che rappresenta un singolo prodotto senza sconti.
 * La classe è Serializable per il salvataggio su file.
 * @author Michele Murgolo
 */
public class Prodotto implements Serializable {



	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @var nome
	 * Nome del prodotto
	 */
	protected String nome;
	
	/**
	 * @var marca
	 * Marca del prodotto
	 */
	protected String marca;
	
	/**
	 * @var idProdotto
	 * Id del prodotto
	 */
	protected String idProdotto;
	
	/**
	 * @var categoria
	 * Categoria del prodotto
	 */
	protected String categoria;
	
	/**
	 * @var prezzo
	 * Prezzo del prodotto
	 */
	protected float prezzo;
	
	/**
	 * @var immagine
	 * Path dell'immagine del prodotto
	 */
	protected String immagine;
	

	public Prodotto(String nome, String marca, String idProdotto, String categoria,
			String immagine, float prezzo) {

	}

	public Prodotto() {

	}

	/**
	 * @brief Setta lo sconto del prodotto.
	 * Il metodo è presente per compatibilità nel caso di up-casting ma non è implementato
	 */
	public void setSconto(int sconto){
	
	}
	
	/**
	 * @brief Ritorna il nome del prodotto
	 * @return Nome prodotto
	 */
	public String getNome() {
		return nome;
	}
	
	/**
	 * @brief Setta il nome del prodotto
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	/**
	 * @brief Ritorna la marca del prodotto
	 * @return Marca prodotto
	 */
	public String getMarca() {
		return marca;
	}
	
	/**
	 * @brief Setta la marca del prodotto
	 */
	public void setMarca(String marca) {
		this.marca = marca;
	}
	
	/**
	 * @brief Ritorna l'id del prodotto
	 * @return ID prodotto
	 */
	public String getIdProdotto() {
		return idProdotto;
	}
	
	/**
	 * @brief Setta l'ID del prodotto
	 */
	public void setIdProdotto(String idProdotto) {
		this.idProdotto = idProdotto;
	}
	
	/**
	 * @brief Ritorna la categoria del prodotto
	 * @return Categoria prodotto
	 */
	public String getCategoria() {
		return categoria;
	}
	
	/**
	 * @brief Setta la categoria del prodotto
	 */
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	/**
	 * @brief Ritorna il prezzo del prodotto
	 * @return Prezzo prodotto
	 */
	public float getPrezzo() {
		return prezzo;
	}
	
	/**
	 * @brief Setta il prezzo del prodotto
	 */
	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}
	

	public String getImmagine() {
		return immagine;
	}

	/**
	 * @brief Ritorna il prezzo scontato del prodotto
	 * @return Prezzo scontato
	 */
	public float getPrezzoFinale(){
		return prezzo - prezzo * getSconto() / 100;
	}

	/**
	 * @brief Ritorna il totale dell'acquisto di n prodotti
	 * @return Totale acquisto
	 */
	public float getTotale(int n){
		return getPrezzoFinale() * n;
	}

	/**
	 * @brief Setta l'immagine del prodotto
	 * @return Immagine prodotto
	 */
	public void setImmagine(String immagine) {
		this.immagine = immagine;
	}
	
	/**
	 * @brief Ritorna lo sconto del prodotto
	 * Il metodo è presente per compatibilità nel caso di up-casting ma ritorna sempre 0
	 * @return Sconto del prodotto
	 */
	public int getSconto(){
		return 0;
	}

	public String toString(){
		return nome + " " + marca + " " + idProdotto + " " + categoria + " " + prezzo;
	}
	
}
