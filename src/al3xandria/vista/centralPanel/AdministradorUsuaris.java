package al3xandria.vista.centralPanel;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;


/**
 * clase per crear el panel d'adminsitració d'usuaris
 * @author SergioHernandez
 *
 */
public class AdministradorUsuaris extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AdministradorUsuaris() {
		setLayout(new BorderLayout());
		
		JLabel lblNewLabel = new JLabel("Administracio Usuaris");
		
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 33));
		add(lblNewLabel, BorderLayout.CENTER);


	}

}
