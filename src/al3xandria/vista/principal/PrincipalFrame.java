package al3xandria.vista.principal;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import al3xandria.controlador.principal.ComportamentTancarAplicacio;
import al3xandria.model.ComunicacioClientServidor;
import al3xandria.strings.ExternalizeStrings;
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
	private ComunicacioClientServidor comunicacioClientServidor;

	/**
	 * Contructor
	 * 
	 * @author SergioHernandez
	 */
	public PrincipalFrame() {
		setMinimumSize(new Dimension(750, 800));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000, 800);
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
		comunicacioClientServidor = new ComunicacioClientServidor();
		contentPane.add(headPanel, BorderLayout.NORTH);
		contentPane.add(centralPanel, BorderLayout.CENTER);
		contentPane.add(footPanel, BorderLayout.SOUTH);

	}

	/**
	 * Evita que cap usuari pugui tancar l'aplicaci�. Nom�s un usuari administrador
	 * podr� tancar l'aplicaci�
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
