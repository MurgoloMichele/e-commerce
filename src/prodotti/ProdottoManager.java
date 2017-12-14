package prodotti;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collections;
import java.util.Vector;

/**
 * Classe contentente metodi statici per la gestione dell'insieme dei prodotti.
 * @author Michele Murgolo
 */
public class ProdottoManager extends Prodotto{
	
	private ProdottoManager() {

	}

	
	private static final long serialVersionUID = -662617905576366111L;
	
	/**
	 * @var prodotti
	 * Vettore che contiene le istanze di tutti i prodotti
	 */	
	private static Vector<Prodotto> prodotti = new Vector<Prodotto>();

	

	public static void aggiungiProdotto(Prodotto nuovoProdotto){
		prodotti.addElement(nuovoProdotto);
		System.out.println(prodotti.size());
		salvaProdotto();
	}
	
	public static boolean modificaProdotto(Prodotto p, int index){
		prodotti.elementAt(index).setNome(p.getNome());
		prodotti.elementAt(index).setCategoria(p.getCategoria());
		prodotti.elementAt(index).setMarca(p.getMarca());
		prodotti.elementAt(index).setIdProdotto(p.getIdProdotto());
		prodotti.elementAt(index).setPrezzo(p.getPrezzo());
		prodotti.elementAt(index).setImmagine(p.getImmagine());
		
		salvaProdotto();
		
		return false;
	}
	
	public static boolean rimuoviProdotto(int index){
				prodotti.removeElementAt(index);
				return true;
	}
	
	public static boolean salvaProdotto(){
		File fileProdotti = new File("src/prodotti/prodotti");
		
		try {
			FileOutputStream fos = new FileOutputStream(fileProdotti);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(prodotti);
			oos.flush();
			oos.close();
			
			System.out.println("file prodotti salvato");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	@SuppressWarnings("unchecked")
	public static boolean caricaProdotto(){
		//Get the products configuration file path
		File productFile = new File("src/prodotti/prodotti");

		//Check if the file exists
		if(productFile.exists()){
			System.out.println("Products file found at " + productFile.getAbsolutePath());
			try {
				//Load the serialized users hash map
				FileInputStream fis = new FileInputStream(productFile);
				ObjectInputStream ois = new ObjectInputStream(fis);
				prodotti = (Vector<Prodotto>)ois.readObject();
				
				ois.close();
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else{
			System.out.println("file prodotto non trovato");
		}
		return false;
	}
	
	public static int getNumeroProdotti(){
		return prodotti == null ? 0 : prodotti.size();
	}

	public static Prodotto getProdotto(int index){
		if(index < prodotti.size()){
			return prodotti.get(index);
		}
		return null;
	}
	
	public static int getIndiceProdotto(Prodotto p){
		return prodotti.indexOf(p);
	}
	
	public static Vector<String> getListaCategoriaProdotto(){
		Vector<String> categories = new Vector<String>();
		
		for(int i = 0; i < prodotti.size(); i++){
			if(!categories.contains(prodotti.get(i).getCategoria())){
				categories.add(prodotti.get(i).getCategoria());
			}
		}
		
		Collections.sort(categories);
				
		return categories;
	}
	
	public static Vector<String> getListaMarcaProdotto(){
		Vector<String> brands = new Vector<String>();
		
		for(int i = 0; i < prodotti.size(); i++){
			if(!brands.contains(prodotti.get(i).getMarca())){
				brands.add(prodotti.get(i).getMarca());
			}
		}
		
		Collections.sort(brands);
				
		return brands;
	}
	
	
}