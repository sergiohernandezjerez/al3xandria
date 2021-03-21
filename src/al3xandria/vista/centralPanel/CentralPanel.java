package al3xandria.vista.centralPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.SystemColor;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicTabbedPaneUI;

import al3xandria.vista.icons.Icons;

import javax.swing.JTabbedPane;
import java.awt.Dimension;
import javax.swing.DebugGraphics;
import java.awt.Cursor;
import java.awt.ComponentOrientation;

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
	
	private JTabbedPane centralTabPanel;
	
	private Icons icons;
	

	public CentralPanel() {
		setForeground(Color.BLACK);
		setBackground(Color.WHITE);
		setBorder(new LineBorder(Color.decode("#00838f")));
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
		centralTabPanel = new JTabbedPane();
		
		centralTabPanel.addTab("Consulta LLibres ", icons.getConsultaLlbresIcon(), consultaLLibres, "Consulta els llibres de la biblioteca");
		add(centralTabPanel);
		
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
