package al3xandria.vista.principal;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import al3xandria.controlador.headPanel.HeadPanelControlador;
import al3xandria.vista.centralPanel.CentralPanel;
import al3xandria.vista.footPanel.FootPanel;
import al3xandria.vista.headPanel.HeadPanel;
import al3xandria.vista.icons.Icons;

public class PrincipalFrame extends JFrame{
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private HeadPanelControlador headPanelControlador;
	
	private FootPanel footPanel;
	private JPanel contentPane;
	private CentralPanel centralPanel;
	private Icons icons;


	private HeadPanel headPanel;
	
	public PrincipalFrame(HeadPanel headPanel, CentralPanel centralPanel, FootPanel footPanel) {
		this.headPanel = headPanel;
		this.centralPanel = centralPanel;
		this.footPanel = footPanel;
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

		icons = new Icons();
		add(centralPanel);
		add(footPanel);
		add(headPanel);
					
	}
	
	
	


	





	
}
