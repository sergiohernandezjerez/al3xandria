package al3xandria.vista.centralPanel;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;


/**
 * clase per crear el panel de consulta de comentaris
 * @author SergioHernandez
 *
 */
public class ConsultaComentaris extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ConsultaComentaris() {
		setLayout(new BorderLayout());
		
		JLabel lblNewLabel = new JLabel("Consulta Comentaris");
		
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 33));
		add(lblNewLabel, BorderLayout.CENTER);


	}

}
