package al3xandria.vista.centralPanel;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;

public class AdministradorLlibres extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public AdministradorLlibres() {
		setLayout(new BorderLayout());
		
		JLabel lblNewLabel = new JLabel("Administrador llibres");
		
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 33));
		add(lblNewLabel, BorderLayout.CENTER);

	}

}
