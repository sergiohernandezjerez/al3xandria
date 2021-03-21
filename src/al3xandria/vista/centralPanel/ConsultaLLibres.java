package al3xandria.vista.centralPanel;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;

public class ConsultaLLibres extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public ConsultaLLibres() {
		setBackground(Color.WHITE);
		setLayout(new BorderLayout(0, 0));
		JLabel lblNewLabel = new JLabel("Consulta llibres");
		

		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 33));
		add(lblNewLabel, BorderLayout.CENTER);

	}

}
