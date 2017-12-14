package gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import prodotti.Prodotto;
import prodotti.Prodotto3x2;
import prodotti.ProdottoScontato;
import utils.ImageLoader;


/**
 * Classe che implementa la schermata per l'inserimento di un nuovo prodotto 
 * @author Michele Murgolo
 */
public class AddProductPanel extends CustomPanel implements MouseListener, ActionListener {
	
	private static final long serialVersionUID = 4808606191145163897L;
	
	/**
	 * @var TAG
	 * Tag univoco utilizzato per identificare questa schermata
	 */
	public static final String TAG = "nuovoProdotto";
	
	
	/**
	 * @var nomeProdottoLabel
	 * Label per identificare il nome del prodotto
	 */
	private JLabel nomeProdottoLabel;
	
	/**
	 * @var nomeProdotto
	 * Casella di testo per l'inserimento del nome del prodotto
	 */
	protected JTextField nomeProdotto;
	
	/**
	 * @var marcaProdottoLabel
	 * Label per identificare la marca del prodotto
	 */
	private JLabel marcaProdottoLabel;
	
	/**
	 * @var marcaProdotto
	 * Casella di testo per l'inserimento della marca di un prodotto
	 */
	protected JTextField marcaProdotto;
	
	/**
	 * @var idProdottoLabel
	 * Label per identificare l'ID del prodotto
	 */
	private JLabel idProdottoLabel;
	
	/**
	 * @var idProdotto
	 * Casella di testo per l'inserimento dell'ID del prodotto
	 */
	protected JTextField idProdotto;
	
	/**
	 * @var categoriaProdottoLabel
	 * Label per identificare la categoria del prodotto
	 */
	private JLabel categoriaProdottoLabel;
	
	/**
	 * @var categoriaProdotto
	 * Casella di testo per l'inserimento della categoria di appartenenza del prodotto
	 */
	protected JTextField categoriaProdotto;
	
	/**
	 * @var prezzoLabel
	 * Label per identificare il prezzo del prodotto
	 */
	private JLabel prezzoLabel;
	
	/**
	 * @var prezzo
	 * Casella di testo per l'inserimento del prezzo di un prodotto
	 */
	protected JTextField prezzo;
	
	/**
	 * @var okButton
	 * Pulsante per confermare l'inserimento dei dati del nuovo prodotto
	 */
	protected JButton okButton;
	
	/**
	 * @var cancelButton
	 * Pulsante per cancellare l'inserimento dei dati del nuovo prodotto
	 */
	private JButton cancelButton;
	
	/**
	 * @var image
	 * Immagine che rappresenta il prodotto
	 */
	protected JLabel image;
	
	/**
	 * @var imageLabel
	 * Label per identificare l'immagine del prodotto
	 */
	private JLabel imageLabel;
	
	/**
	 * @var sconto
	 * RadioButton per selezionare che il prodotto prevede uno sconto
	 */
	protected JRadioButton sconto;
	
	/**
	 * @var trexdue
	 * RadioButton per selezionare che il prodotto prevede uno sconto nella modalità 3x2
	 */
	protected JRadioButton trexdue;
	
	/**
	 * @var nil
	 * RadioButton per selezionare che il prodotto non prevede alcun tipo di sconto
	 */
	protected JRadioButton nil;
	
	/**
	 * @var scontoFieldLabel
	 * Label per identificare lo sconto in percentuale del prodotto
	 */
	protected JLabel scontoFieldLabel;
	
	/**
	 * @var scontoField
	 * Casella di testo per inserire lo sconto in percentuale del prodotto
	 */
	protected JTextField scontoField;
	
	/**
	 * @var p
	 * Istanza del prodotto da inserire
	 */
	protected Prodotto p;
	
	
	/**
	 * @var imagePath
	 * Stringa contenente il path dell'immagine del prodotto
	 */
	protected String imagePath;

	public AddProductPanel(PanelManager panelManager) {
		super(panelManager);
		
		nomeProdottoLabel = new JLabel("Nome: ");
		marcaProdottoLabel = new JLabel("Marca: ");
		idProdottoLabel = new JLabel("Id: ");
		categoriaProdottoLabel = new JLabel("Categoria: ");
		prezzoLabel = new JLabel("prezzo: ");
		imageLabel = new JLabel("immagine: ");
		scontoFieldLabel = new JLabel("Sconto: ");
		
		nomeProdotto = new JTextField(20);
		marcaProdotto = new JTextField(20);
		idProdotto = new JTextField(20);
		categoriaProdotto = new JTextField(20);
		prezzo = new JTextField(20);
		scontoField = new JTextField(20);
		
		okButton = new JButton("Inserisci");
		cancelButton = new JButton("Annulla");
		
		sconto = new JRadioButton("sconto");
		trexdue = new JRadioButton("3x2");
		nil = new JRadioButton("prezzo intero");
		
		ButtonGroup group = new ButtonGroup();
		group.add(sconto);
		group.add(trexdue);
		group.add(nil);
		
		sconto.addActionListener(this);
		trexdue.addActionListener(this);
		nil.addActionListener(this);
		
		image = new JLabel("Click per selezionare un'immagine");
		image.addMouseListener(this);
		image.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		image.setMinimumSize(new Dimension(200, 150));
		image.setHorizontalAlignment(SwingConstants.CENTER);
		image.setVerticalAlignment(SwingConstants.CENTER);
		image.addMouseListener(this);

		
		
		this.add(nomeProdottoLabel);
		this.add(nomeProdotto);
		this.add(marcaProdottoLabel);
		this.add(marcaProdotto);
		this.add(categoriaProdottoLabel);
		this.add(categoriaProdotto);
		this.add(idProdottoLabel);
		this.add(idProdotto);
		this.add(prezzoLabel);
		this.add(prezzo);
		this.add(imageLabel);
		this.add(image);
		this.add(sconto);
		this.add(trexdue);
		this.add(nil);
		this.add(scontoFieldLabel);
		this.add(scontoField);
		
		okButton.addActionListener(this);
		cancelButton.addActionListener(this);
		
		sconto.addActionListener(this);
		nil.addActionListener(this);
		trexdue.addActionListener(this);
		
		JPanel jp = new JPanel();
		GroupLayout layout = new GroupLayout(jp);
		jp.setLayout(layout);
		
		jp.add(nomeProdottoLabel);
		jp.add(nomeProdotto);
		jp.add(marcaProdottoLabel);
		jp.add(marcaProdotto);
		jp.add(categoriaProdottoLabel);
		jp.add(categoriaProdotto);
		jp.add(idProdottoLabel);
		jp.add(idProdotto);
		jp.add(prezzoLabel);
		jp.add(prezzo);	
		jp.add(image);
		jp.add(imageLabel);
		jp.add(sconto);
		jp.add(trexdue);
		jp.add(nil);
		jp.add(scontoFieldLabel);
		jp.add(scontoField);
		
		
		GridBagLayout gbLayout = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		c.anchor = GridBagConstraints.CENTER;
		setLayout(gbLayout);
		add(jp, c);
		
		layout.setAutoCreateGaps(true);
	    layout.setAutoCreateContainerGaps(true);
	    layout.linkSize(okButton, cancelButton);
	    
	    
	    layout.setVerticalGroup(
				layout.createSequentialGroup()
					.addGroup(
							layout.createParallelGroup(GroupLayout.Alignment.CENTER)
								.addComponent(nomeProdottoLabel)
								.addComponent(nomeProdotto)
					)
					.addGroup(
							layout.createParallelGroup(GroupLayout.Alignment.CENTER)
								.addComponent(marcaProdottoLabel)
								.addComponent(marcaProdotto)
					)
					.addGroup(
							layout.createParallelGroup(GroupLayout.Alignment.CENTER)
							.addComponent(categoriaProdottoLabel)
							.addComponent(categoriaProdotto)
					)
					.addGroup(
							layout.createParallelGroup(GroupLayout.Alignment.CENTER)
							.addComponent(idProdottoLabel)
							.addComponent(idProdotto)
					)
					.addGroup(
							layout.createParallelGroup(GroupLayout.Alignment.CENTER)
							.addComponent(prezzoLabel)
							.addComponent(prezzo)
					)
					.addGroup(
							layout.createParallelGroup(GroupLayout.Alignment.CENTER)
							.addComponent(imageLabel)
							.addComponent(image)
					)
					.addGroup(
							layout.createParallelGroup(GroupLayout.Alignment.CENTER)
							.addComponent(sconto)
							.addComponent(trexdue)
					)
					.addGroup(
							layout.createParallelGroup(GroupLayout.Alignment.CENTER)
							.addComponent(scontoFieldLabel)
							.addComponent(scontoField)
					)
					.addGroup(
							layout.createParallelGroup(GroupLayout.Alignment.CENTER)
							.addComponent(nil)
					)
					.addGroup(
							layout.createParallelGroup(GroupLayout.Alignment.CENTER)
							.addComponent(okButton)
							.addComponent(cancelButton)
					)
				
		);

		layout.setHorizontalGroup(
				layout.createSequentialGroup()
					.addGroup(
							layout.createParallelGroup(GroupLayout.Alignment.CENTER)
								.addGroup(layout.createSequentialGroup()
									.addGroup(layout.createParallelGroup()
											.addComponent(nomeProdottoLabel)
											.addComponent(marcaProdottoLabel)
											.addComponent(categoriaProdottoLabel)
											.addComponent(idProdottoLabel)
											.addComponent(prezzoLabel)
											.addComponent(imageLabel)
											.addComponent(sconto)
											.addComponent(scontoFieldLabel)
											.addComponent(nil)
											.addComponent(okButton)
									)
									.addGroup(layout.createParallelGroup()
											.addComponent(nomeProdotto)
											.addComponent(marcaProdotto)
											.addComponent(categoriaProdotto)
											.addComponent(idProdotto)
											.addComponent(prezzo)
											.addComponent(image)
											.addComponent(scontoField)
											.addComponent(trexdue)
											.addComponent(cancelButton)
									)
								)
					)
		);
		
	}

	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(okButton)) {
			System.out.println("ok");
			System.out.println(nomeProdotto.getText() + " " + marcaProdotto.getText() + " " + idProdotto.getText() + " " + categoriaProdotto.getText() + prezzo.getText());
			if (nil.isSelected()) {
				p = new Prodotto();
			}
			else if (sconto.isSelected()) {
				p = new ProdottoScontato();
			}
			else if (trexdue.isSelected()) {
				p = new Prodotto3x2();
			}
			p.setIdProdotto(idProdotto.getText());
			p.setNome(nomeProdotto.getText());
			p.setCategoria(categoriaProdotto.getText());
			p.setMarca(marcaProdotto.getText());
			p.setPrezzo(Float.parseFloat(prezzo.getText()));
			if (sconto.isSelected()) {
				p.setSconto(Integer.parseInt(scontoField.getText()));
			}
			p.setImmagine(imagePath);
			prodotti.ProdottoManager.aggiungiProdotto(p);
			panelManager.setCurrentPanel(AdminPanel.TAG);
		}
		
		if (e.getSource().equals(cancelButton)) {
			panelManager.setCurrentPanel(AdminPanel.TAG);
		}
		
		if (e.getSource().equals(nil)) {
			scontoField.setEditable(false);
		}
		
		if (e.getSource().equals(sconto)) {
			scontoField.setEditable(true);
		}
		
		if (e.getSource().equals(trexdue)) {
			scontoField.setEditable(false);
		}
		
		
		
	}

	
	public void onEnter(){
		nomeProdotto.setText("");
		marcaProdotto.setText("");
		categoriaProdotto.setText("");
		idProdotto.setText("");
		prezzo.setText("");
		imageLabel.setText("");
	}

	
	public void mouseClicked(MouseEvent e) {
		//Carica l'immagine se si clicca sulla label
		JFileChooser fChooser = new JFileChooser();
		FileFilter imageFilter = new FileNameExtensionFilter("Image files", ImageIO.getReaderFileSuffixes());
		fChooser.setFileFilter(imageFilter);
		
		if(fChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION){
			imagePath = fChooser.getSelectedFile().getAbsolutePath();
			try {
				image.setIcon(new ImageIcon(ImageLoader.loadImage(imagePath, image.getSize())));
				image.setText("");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}


	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
