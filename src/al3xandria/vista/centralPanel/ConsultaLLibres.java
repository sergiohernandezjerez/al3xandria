package al3xandria.vista.centralPanel;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextArea;


/**
 * clase per crear el panel de consulta de llibres
 * @author SergioHernandez
 *
 */
public class ConsultaLLibres extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ConsultaLLibres() {
		setBackground(Color.WHITE);
		setLayout(new BorderLayout(0, 0));
		JLabel lblNewLabel = new JLabel("Consulta llibres");
		

		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 33));
		add(lblNewLabel, BorderLayout.NORTH);
		
		JTextArea txtrEmailContrasenyaTipus = new JTextArea();
		txtrEmailContrasenyaTipus.setEditable(false);
		txtrEmailContrasenyaTipus.setText("Dades dels usuaris que es poden utilitzar per fer proves\r\n\r\nemail            contrasenya   tipus\r\n\r\npepe@pepe.com     pepe         usuari\r\nmaria@maria.com   maria        usuari\r\njosep@josep.com   josep        usuari\r\narnau@arnau.com   arnau        administrador\r\nanna@anna.com     anna         usuari\r\nsergi@sergi.com   sergi        usuari\r\npere@pere.com     pere         usuari\r\nmarta@marta.com   marta        administrador\r\npilar@pilar.com   pilar        administrador\r\njulia@julia.com   julia        usuari\r\npau@pau.com       pau          usuari");
		add(txtrEmailContrasenyaTipus, BorderLayout.WEST);

	}

}
