package al3xandria.vista.centralPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.LineBorder;
import al3xandria.controlador.centralPanel.CentralPanelControlador;
import al3xandria.vista.icons.Icons;

public class CentralPanel extends JPanel{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
private CentralPanelControlador centralPanelControlador;

private CentralPanel centralPanel;
private Icons icons;
private ConsultaPrestecs consultaPrestecs;
private ConsultaLLibres consultaLLibres;
private ConsultaComentaris consultaComentaris;
private AdministradorComentaris administradorComentaris;
private AdministradorLlibres administradorLlibres;
private AdministradorPrestecs administradorPrestecs;
private AdministradorUsuaris administradorUsuaris;
private JTabbedPane centralTabPanel;
	
	public CentralPanel() {
		setForeground(Color.BLACK);
		setBackground(Color.WHITE);
		setBorder(new LineBorder(Color.decode("#00838f")));
		setBounds(10, 113, 963, 562);
		setLayout(new BorderLayout(0, 0));
		
		iniciarComponents();
			
	}
	
	public void iniciarComponents() {
		icons = new Icons();
		consultaPrestecs = new ConsultaPrestecs();
		consultaLLibres = new ConsultaLLibres();
		consultaComentaris = new ConsultaComentaris();
		administradorComentaris = new AdministradorComentaris();
		administradorLlibres = new AdministradorLlibres();
		administradorPrestecs = new AdministradorPrestecs();
		administradorUsuaris = new AdministradorUsuaris();
		centralTabPanel = new JTabbedPane();
		
		centralTabPanel.addTab("Consulta LLibres ", icons.getConsultaLlbresIcon(), consultaLLibres, "Consulta els llibres de la biblioteca");
		add(centralTabPanel);
		
	}

	
public void setCentralPanelControlador(CentralPanelControlador centralPanelControlador) {
	this.centralPanelControlador = centralPanelControlador;
}
	



}
