package al3xandria.controlador.interficie;

import javax.swing.JTabbedPane;

import al3xandria.vista.centralPanel.AdministradorComentaris;
import al3xandria.vista.centralPanel.AdministradorLlibres;
import al3xandria.vista.centralPanel.AdministradorPrestecs;
import al3xandria.vista.centralPanel.AdministradorUsuaris;
import al3xandria.vista.centralPanel.CentralPanel;
import al3xandria.vista.centralPanel.ConsultaComentaris;
import al3xandria.vista.centralPanel.ConsultaLLibres;
import al3xandria.vista.centralPanel.ConsultaPrestecs;
import al3xandria.vista.icons.Icons;

public class ControladorCentralPanel {
	
	private CentralPanel centralPanel;
	private Icons icons;
	private JTabbedPane tabPanel;
	
	private AdministradorLlibres administradorLlibres;
	private AdministradorPrestecs administradorPrestecs;
	private AdministradorUsuaris administradorUsuaris;
	private AdministradorComentaris administradorComentaris;
	private ConsultaLLibres consultaLLibres;
	private ConsultaComentaris consultaComentaris;
	private ConsultaPrestecs consultaPrestecs;
	
	public ControladorCentralPanel(CentralPanel centralPanel) {
		this.centralPanel = centralPanel;
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
