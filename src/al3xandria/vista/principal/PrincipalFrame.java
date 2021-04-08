package al3xandria.vista.principal;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import al3xandria.controlador.principal.ComportamentTancarAplicacio;
import al3xandria.vista.centralPanel.CentralPanel;
import al3xandria.vista.footPanel.FootPanel;
import al3xandria.vista.headPanel.HeadPanel;

/**
 * clase que crea el JFrame principal
 * 
 * @author SergioHernandez
 *
 */
public class PrincipalFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private FootPanel footPanel;
	private CentralPanel centralPanel;
	private HeadPanel headPanel;
	private JPanel contentPane;
	/**
	 * Contructor
	 * 
	 * @author SergioHernandez
	 */
	public PrincipalFrame() {
		setMinimumSize(new Dimension(834, 850));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 850);
		footPanel = new FootPanel();
		centralPanel = new CentralPanel();
		headPanel = new HeadPanel(footPanel, centralPanel);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		tancarAplicacio();
		iniciarComponents();

	}

	/**
	 * Inicia els components que forman el frame principal
	 * 
	 * @author SergioHernandez
	 */
	private void iniciarComponents() {
		contentPane.add(headPanel, BorderLayout.NORTH);
		contentPane.add(centralPanel, BorderLayout.CENTER);
		contentPane.add(footPanel, BorderLayout.SOUTH);

	}

	/**
	 * Evita que cap usuari pugui tancar l'aplicació. Només un usuari administrador
	 * podrà tancar l'aplicació
	 * 
	 * @author SergioHernandez
	 */
	public void tancarAplicacio() {
		try {
			this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			addWindowListener(new ComportamentTancarAplicacio(headPanel));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
