package al3xandria.vista.principal;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

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
	private ComunicacioClientServidor enviarLoginServer;

	/**
	 * Contructor
	 * 
	 * @author SergioHernandez
	 */
	public PrincipalFrame() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setTitle("Al3xandria");
		setBounds(300, 200, 1000, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		tancarAplicacio();
		iniciarComponents();

	}

	/**
	 * Inicia els components que forman el frame principal
	 * 
	 * @author SergioHernandez
	 */
	private void iniciarComponents() {
		
		centralPanel = new CentralPanel();
		footPanel = new FootPanel();
		headPanel = new HeadPanel(footPanel, centralPanel);
		add(centralPanel);
		add(footPanel);
		add(headPanel);
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
			addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					if (headPanel.getTipusUsuari() != null) {
						if (headPanel.getTipusUsuari().equals("Administrador")) {
							avisTancamentAplicacio();
						} else {
							avisImposibleTancarAplicaci�();
						}
					} else {
						avisImposibleTancarAplicaci�();
					}
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Missatge que mostra un av�s quan un usuari vol tancar l'aplicaci�
	 * 
	 * @author SergioHernandez
	 */
	public void avisImposibleTancarAplicaci�() {
		JOptionPane.showMessageDialog(headPanel,
				ExternalizeStrings.getString("PrincipalFrame.missatgeNoEsPotTancarAplicacio"),
				ExternalizeStrings.getString("PrincipalFrame.titolMissatgeNoEsPotTancarAplicacio"),
				JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * Missatge que mostra un av�s per tancar l'aplicaci� i fer logout i demana
	 * confirmaci� Quan es confirma, s'envia un missatge per fer logout de l'usuari
	 * i tanca l'aplicaci�
	 * 
	 * @author SergioHernandez
	 */
	public void avisTancamentAplicacio() {
		int valor = JOptionPane.showConfirmDialog(this,
				ExternalizeStrings.getString("PrincipalFrame.missatgeAvisTancamentAplicacio"),
				ExternalizeStrings.getString("PrincipalFrame.titolMissatgeAvisTancamentAplicacio"),
				JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
		if (valor == JOptionPane.YES_OPTION) {
			JOptionPane.showMessageDialog(this,
					ExternalizeStrings.getString("PrincipalFrame.missatgeAcomiadamentAplicacio"),
					ExternalizeStrings.getString("PrincipalFrame.titolMissatgeAcomiadamentAplicacio"),
					JOptionPane.INFORMATION_MESSAGE);
			enviarLoginServer = new ComunicacioClientServidor("logoutOK," + headPanel.getEmailintroduitPerLusuari().getText());
			System.exit(0);
		}

	}


}
