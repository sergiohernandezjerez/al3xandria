package al3xandria.vista.centralPanel;

import java.awt.BorderLayout;
import java.awt.SystemColor;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import al3xandria.vista.icons.Icons;

import javax.swing.JTabbedPane;

public class CentralPanel extends JPanel{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ConsultaLLibres consultaLLibres;
	
	private AdministradorLlibres administradorLlibres;
	private AdministradorPrestecs administradorPrestecs;
	private AdministradorUsuaris administradorUsuaris;
	private AdministradorComentaris administradorComentaris;
	
	private ConsultaComentaris consultaComentaris;
	private ConsultaPrestecs consultaPrestecs;
	
	private JTabbedPane tabPanel;
	
	private Icons icons;
	

	public CentralPanel() {
		setBorder(new LineBorder(SystemColor.activeCaption));
		setBounds(10, 113, 963, 562);
		setLayout(new BorderLayout(0, 0));
		
		iniciarComponents();
			
	}

	private void iniciarComponents() {
		icons = new Icons();
		consultaPrestecs = new ConsultaPrestecs();
		consultaLLibres = new ConsultaLLibres();
		consultaComentaris = new ConsultaComentaris();
		administradorComentaris = new AdministradorComentaris();
		administradorLlibres = new AdministradorLlibres();
		administradorPrestecs = new AdministradorPrestecs();
		administradorUsuaris = new AdministradorUsuaris();
		tabPanel = new JTabbedPane();
	
		tabPanel.addTab("Consulta LLibres ", icons.getConsultaLlbresIcon(), consultaLLibres, "Consulta els llibres de la biblioteca");
		add(tabPanel);
		
	}

	

public void setUsuariPanel() {
	tabPanel.addTab("Prestecs ", icons.getconsultaPrestecsIcon(), consultaPrestecs, "Consulta els teus prestecs");
	tabPanel.addTab("Comentaris ", icons.getConsultaComentarisIcon(), consultaComentaris, "Consultar els teus comentaris");
}

public void setAdministradorPanel() {
	tabPanel.remove(consultaLLibres);
	tabPanel.addTab("Admin Llibres ", icons.getAdministrarLibresIcon(),administradorLlibres, "Administra els llibres de la biblioteca");
	tabPanel.addTab("Admin Prestecs ", icons.getAdministrarPrestecsIcon(),administradorPrestecs, "Administra els prestecs de la biblioteca");
	tabPanel.addTab("Admin Usuaris ", icons.getAdministrarUsuarisIcon(),administradorUsuaris, "Administra els usuaris de la biblioteca");
	tabPanel.addTab("Admin Comentaris ", icons.getAdministrarComentarisIcon(),administradorComentaris, "Administra els comentaris del usuaris");
}
	
public void removeAdministradorPanelToDefautl() {
	tabPanel.remove(administradorLlibres);
	tabPanel.remove(administradorPrestecs);
	tabPanel.remove(administradorUsuaris);
	tabPanel.remove(administradorComentaris);
	tabPanel.addTab("Consulta Llibres ", icons.getConsultaLlbresIcon(),consultaLLibres, "Consulta els llibres de la biblioteca");
}

public void removeUsuariPanelToDefault() {
	tabPanel.remove(consultaComentaris);
	tabPanel.remove(consultaPrestecs);
}

}
