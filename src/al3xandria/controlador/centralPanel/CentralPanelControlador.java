package al3xandria.controlador.centralPanel;

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

public class CentralPanelControlador {
	
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
	
	public CentralPanelControlador(CentralPanel centralPanel) {
		this.centralPanel = centralPanel;
	}

	
	
	
	public void setUsuariPanel() {
		centralTabPanel.addTab("Prestecs ", icons.getconsultaPrestecsIcon(), consultaPrestecs, "Consulta els teus prestecs");
		centralTabPanel.addTab("Comentaris ", icons.getConsultaComentarisIcon(), consultaComentaris, "Consultar els teus comentaris");
	}

	public void setAdministradorPanel() {
		centralTabPanel.remove(consultaLLibres);
		centralTabPanel.addTab("Admin Llibres ", icons.getAdministrarLibresIcon(),administradorLlibres, "Administra els llibres de la biblioteca");
		centralTabPanel.addTab("Admin Prestecs ", icons.getAdministrarPrestecsIcon(),administradorPrestecs, "Administra els prestecs de la biblioteca");
		centralTabPanel.addTab("Admin Usuaris ", icons.getAdministrarUsuarisIcon(),administradorUsuaris, "Administra els usuaris de la biblioteca");
		centralTabPanel.addTab("Admin Comentaris ", icons.getAdministrarComentarisIcon(),administradorComentaris, "Administra els comentaris del usuaris");
	}
		
	public void removeAdministradorPanelToDefautl() {
		centralTabPanel.remove(administradorLlibres);
		centralTabPanel.remove(administradorPrestecs);
		centralTabPanel.remove(administradorUsuaris);
		centralTabPanel.remove(administradorComentaris);
		centralTabPanel.addTab("Consulta Llibres ", icons.getConsultaLlbresIcon(),consultaLLibres, "Consulta els llibres de la biblioteca");
	}

	public void removeUsuariPanelToDefault() {
		centralTabPanel.remove(consultaComentaris);
		centralTabPanel.remove(consultaPrestecs);
	}
	

}
