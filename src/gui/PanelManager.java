package gui;



import java.awt.CardLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;


/**
 * Classe che implementa la finestra principale del programma
 *
 */
public class PanelManager extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * @var container
	 * Panello principale in cui verranno mostrate le varie schermate del programma
	 */
	private static JPanel container;
	
	public PanelManager() {

		//Create the container and add all the panel
		container = new JPanel(new CardLayout());
		container.add(new LoginPanel(this), LoginPanel.TAG);
		container.add(new SignUpPanel(this), SignUpPanel.TAG);
		container.add(new MemberPanel(this), MemberPanel.TAG);
		container.add(new AdminPanel(this), AdminPanel.TAG);
		container.add(new AddProductPanel(this), AddProductPanel.TAG);
		container.add(new EditProductPanel(this), EditProductPanel.TAG);
		container.add(new CartPanel(this), CartPanel.TAG);
		container.add(new FilterPanel(this), FilterPanel.TAG);
		container.add(new PayPanel(this), PayPanel.TAG);




		
		add(container);
		
		//Set windows settings
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(800, 600);
		setMinimumSize(new Dimension(400, 300));
		setTitle("E-Commerce");
		setLocationRelativeTo(null);
		setVisible(true);
		pack();
	}
	
	/**
	 * Cambia la schermata corrente con un'altra
	 * @param panelName Tag associato alla schermata da mostrare
	 */
	public void setCurrentPanel(String panelName){
		CardLayout cl = (CardLayout)(container.getLayout());
        cl.show(container, panelName);   
	}

	
	

}

