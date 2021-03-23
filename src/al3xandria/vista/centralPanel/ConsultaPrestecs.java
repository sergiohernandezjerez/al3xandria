package al3xandria.vista.centralPanel;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;


/**
 * clase per crear el panel de consulta de prèstecs
 * @author SergioHernandez
 *
 */
public class ConsultaPrestecs extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ConsultaPrestecs() {
		setLayout(new BorderLayout());
		
		JLabel lblNewLabel = new JLabel("Consulta Prestecs");
		
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 33));
		add(lblNewLabel, BorderLayout.CENTER);

	}

}
