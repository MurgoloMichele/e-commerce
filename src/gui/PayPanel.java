package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import carrello.CarrelloManager;


/**
 * Classe che implementa la schermata di pagamento
 * @author Michele Murgolo
 */
public class PayPanel extends CustomPanel {
	
	private static final long serialVersionUID = -7017064370430341891L;
	
	/**
	 * @var TAG
	 * Tag univoco utilizzato per identificare questa schermata
	 */
	public static final String TAG = "pay";


	/**
	 * @var indirizzoLabel
	 * Label per identificare l'indirizzo di spedizione
	 */
	private JLabel indirizzoLabel;
	
	/**
	 * @var indirizzo
	 * Casella di testo per l'inserimento dell'indirizzo di spedizione
	 */
	private JTextField indirizzo;

	/**
	 * @var carteLabel
	 * Label per identificare il tipo di carta di credito con cui pagare
	 */
	private JLabel carteLabel;
	
	/**
	 * @var carte
	 * Finestra per selezionare il tipo di carta di credito con cui pagare
	 */
	private JComboBox <String> carte;
	
	/**
	 * @var numeroCartaLabel
	 * Label per identificare il numero della carta
	 */
	private JLabel numeroCartaLabel;
	
	/**
	 * @var numeroCarta
	 * Casella di testo per l'inserimento del numero della carta di credito
	 */
	private JTextField numeroCarta;
	
	/**
	 * @var okButton
	 * Pulsante per la conferma del pagamento
	 */
	private JButton okButton;
	
	/**
	 * @var cancelButton
	 * Pulsante per annullare il pagamento
	 */
	private JButton cancelButton;

	private final String[] carteCredito = {"PayPal", "MasterCard", "American Express"};
	
	public PayPanel(PanelManager panelManager) {
		super(panelManager);
		
		indirizzoLabel = new JLabel("Inserisci indirizzo");
		indirizzo = new JTextField();
		carteLabel = new JLabel ("Seleziona metodo di pagamento");
		carte = new JComboBox<String>(carteCredito);
		carte.addActionListener(this);
		numeroCartaLabel = new JLabel ("Inserisci numero di carta");
		numeroCarta = new JTextField(16);
		okButton = new JButton ("Conferma");
		cancelButton = new JButton ("Annulla");
		
		this.add(indirizzoLabel);
		this.add(indirizzo);
		this.add(carteLabel);
		this.add(numeroCartaLabel);
		this.add(numeroCarta);
		okButton.addActionListener(this);
		cancelButton.addActionListener(this);
		
		JPanel jp = new JPanel();
		GroupLayout layout = new GroupLayout(jp);
		jp.setLayout(layout);
		
		jp.add(indirizzoLabel);
		jp.add(indirizzo);
		jp.add(carteLabel);
		jp.add(carte);
		jp.add(numeroCartaLabel);
		jp.add(numeroCarta);
		jp.add(okButton);
		jp.add(cancelButton);
		
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
								.addComponent(indirizzoLabel)
								.addComponent(indirizzo)
					)
					.addGroup(
							layout.createParallelGroup(GroupLayout.Alignment.CENTER)
								.addComponent(carteLabel)
								.addComponent(carte)
					)
					.addGroup(
							layout.createParallelGroup(GroupLayout.Alignment.CENTER)
								.addComponent(numeroCartaLabel)
								.addComponent(numeroCarta)
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
											.addComponent(indirizzoLabel)
											.addComponent(carteLabel)
											.addComponent(numeroCartaLabel)
											.addComponent(okButton)
											)
									.addGroup(layout.createParallelGroup()
											.addComponent(indirizzo)
											.addComponent(carte)
											.addComponent(numeroCarta)
											.addComponent(cancelButton)
											)
									
								
										)
							)
		);
		
	    
	}

	

	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(okButton)){
			CarrelloManager.clear();
			panelManager.setCurrentPanel(CartPanel.TAG);
			System.out.println("pagamento effettuato");
			JOptionPane.showMessageDialog(this, "Pagamento effettuato!", "Congratulazioni!", JOptionPane.INFORMATION_MESSAGE);
		}
		
		if (e.getSource().equals(cancelButton)){
			panelManager.setCurrentPanel(CartPanel.TAG);
		}
	}

}
