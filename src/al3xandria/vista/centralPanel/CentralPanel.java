package al3xandria.vista.centralPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import al3xandria.vista.icons.Icons;

import javax.swing.JTabbedPane;

/**
 * Clase que crea el panel central de l'aplicació
 * 
 * @author SergioHernandez
 *
 */
public class CentralPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ConsultaLlibres consultaLLibres;
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
		setLayout(new BorderLayout(0, 0));

		iniciarComponents();

	}

	private void iniciarComponents() {
		icons = new Icons();
		consultaPrestecs = new ConsultaPrestecs();
		consultaLLibres = new ConsultaLlibres();
		consultaComentaris = new ConsultaComentaris();
		administradorComentaris = new AdministradorComentaris();
		administradorLlibres = new AdministradorLlibres();
		administradorPrestecs = new AdministradorPrestecs();
		administradorUsuaris = new AdministradorUsuaris();
		centralTabPanel = new JTabbedPane();
		
		centralTabPanel.addTab("Consulta LLibres ", icons.getConsultaLlbresIcon(), 
				consultaLLibres, "Consulta els llibres de la biblioteca");
		add(centralTabPanel);

	}

	
	/**
	 * crear el panel central especific per l'usuari
	 * @author SergioHernandez
	 */
	public void setUsuariPanel() {
		centralTabPanel.addTab("Prestecs ", icons.getconsultaPrestecsIcon(),
				consultaPrestecs, "Consulta els teus prestecs");
		centralTabPanel.addTab("Comentaris ", icons.getConsultaComentarisIcon(), 
				consultaComentaris,	"Consultar els teus comentaris");
	}

	
	/**
	 * crear el panel central especific per l'administrador
	 * @author SergioHernandez
	 */
	public void setAdministradorPanel() {
		centralTabPanel.remove(consultaLLibres);
		centralTabPanel.addTab("Admin Llibres ", icons.getAdministrarLibresIcon(),
				administradorLlibres, "Administra els llibres de la biblioteca");
		centralTabPanel.addTab("Admin Prestecs ", icons.getAdministrarPrestecsIcon(), 
				administradorPrestecs, "Administra els prestecs de la biblioteca");
		centralTabPanel.addTab("Admin Usuaris ", icons.getAdministrarUsuarisIcon(), 
				administradorUsuaris, "Administra els usuaris de la biblioteca");
		centralTabPanel.addTab("Admin Comentaris ", icons.getAdministrarComentarisIcon(), 
				administradorComentaris, "Administra els comentaris del usuaris");
	}

	/**
	 * elimina el panel de l'adiminstrador i crea el panel per defecte
	 * @author SergioHernandez
	 */
	public void removeAdministradorPanelToDefautl() {
		centralTabPanel.remove(administradorLlibres);
		centralTabPanel.remove(administradorPrestecs);
		centralTabPanel.remove(administradorUsuaris);
		centralTabPanel.remove(administradorComentaris);
		centralTabPanel.addTab("Consulta Llibres ", icons.getConsultaLlbresIcon(), 
				consultaLLibres, "Consulta els llibres de la biblioteca");
	}

	
	/**
	 * elimina el panel de l'ususuari i crea el panel per defecte
	 * @author SergioHernandez
	 */
	public void removeUsuariPanelToDefault() {
		centralTabPanel.remove(consultaComentaris);
		centralTabPanel.remove(consultaPrestecs);
	}

}
