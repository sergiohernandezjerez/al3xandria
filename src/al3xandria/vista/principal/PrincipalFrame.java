package al3xandria.vista.principal;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import al3xandria.controlador.login.Controller;
import al3xandria.vista.centralPanel.CentralPanel;
import al3xandria.vista.footPanel.FootPanel;
import al3xandria.vista.headPanel.HeadPanel;

public class PrincipalFrame extends JFrame{
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private FootPanel footPanel;
	private JPanel contentPane;
	private CentralPanel centralPanel;
	
	public PrincipalFrame() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Al3xandria");
		setBounds(300, 200, 1000, 800);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		iniciarComponents();
				
	}


	private void iniciarComponents() {
		centralPanel = new CentralPanel();
		footPanel = new FootPanel();
		new HeadPanel(footPanel, centralPanel);
					
	}
	
	public void setController(Controller controller) {
		
	}


	





	
}
