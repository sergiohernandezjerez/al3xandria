package al3xandria.vista.centralPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import al3xandria.controlador.consultaLlibres.ConsultaLlibresControlador;
import al3xandria.model.objects.Usuari;
import al3xandria.vista.icons.Icons;

import javax.swing.JTabbedPane;

/**
 * Clase que crea el panel central de l'aplicaci�
 * 
 * @author SergioHernandez
 *
 */
public class CentralPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Usuari usuariConnectat;
	private Icons icons;
	
	private ConsultaLlibres consultaLLibres;
	private AdministradorLlibres administradorLlibres;
	private AdministradorUsuaris administradorUsuaris;
	private ConsultaLlibresNoRegistrat consultaLlibresNoRegistrat;
	private JTabbedPane centralTabPanel;

	public CentralPanel(Usuari usuariConnectat) {
		this.usuariConnectat = usuariConnectat;
		setForeground(Color.BLACK);
		setBackground(Color.WHITE);
		setBorder(new LineBorder(Color.decode("#00838f")));
		setLayout(new BorderLayout(0, 0));

		iniciarComponents();

	}

	private void iniciarComponents() {
		
		icons = new Icons();
		//consultaPrestecs = new ConsultaPrestecs();
		//consultaLLibres = new ConsultaLlibres(usuariConnectat);
		consultaLlibresNoRegistrat = new ConsultaLlibresNoRegistrat(usuariConnectat);
		//consultaComentaris = new ConsultaComentaris();
		//administradorComentaris = new AdministradorComentaris();
		//administradorLlibres = new AdministradorLlibres(usuariConnectat);
		//administradorPrestecs = new AdministradorPrestecs();
	    //administradorUsuaris = new AdministradorUsuaris(usuariConnectat);
		centralTabPanel = new JTabbedPane();
		
		centralTabPanel.addTab("Consulta LLibres ", icons.getConsultaLlbresIcon(), 
				consultaLlibresNoRegistrat, "Consulta els llibres de la biblioteca com a usuari no registrat");
		add(centralTabPanel);

	}

	
	/**
	 * crear el panel central especific per l'usuari
	 * @author SergioHernandez
	 */
	public void setUsuariPanel(Usuari usuariConnectat) {
		consultaLLibres = new ConsultaLlibres(usuariConnectat);
		centralTabPanel.remove(consultaLlibresNoRegistrat);
		centralTabPanel.addTab("Consulta LLibres ", icons.getConsultaLlbresIcon(), 
				consultaLLibres, "Consulta els llibres de la biblioteca");
		//centralTabPanel.addTab("Prestecs ", icons.getconsultaPrestecsIcon(),
				//consultaPrestecs, "Consulta els teus prestecs");
		//centralTabPanel.addTab("Comentaris ", icons.getConsultaComentarisIcon(), 
				//consultaComentaris,	"Consultar els teus comentaris");
	}

	
	/**
	 * crear el panel central especific per l'administrador
	 * @author SergioHernandez
	 */
	public void setAdministradorPanel(Usuari usuariConnectat) {
		administradorLlibres = new AdministradorLlibres(usuariConnectat);
		administradorUsuaris = new AdministradorUsuaris(usuariConnectat);
		centralTabPanel.remove(consultaLlibresNoRegistrat);
		centralTabPanel.addTab("Admin Llibres ", icons.getAdministrarLibresIcon(),
				administradorLlibres, "Administra els llibres de la biblioteca");
		//centralTabPanel.addTab("Admin Prestecs ", icons.getAdministrarPrestecsIcon(), 
				//administradorPrestecs, "Administra els prestecs de la biblioteca");
		centralTabPanel.addTab("Admin Usuaris ", icons.getAdministrarUsuarisIcon(), 
				administradorUsuaris, "Administra els usuaris de la biblioteca");
		//centralTabPanel.addTab("Admin Comentaris ", icons.getAdministrarComentarisIcon(), 
				//administradorComentaris, "Administra els comentaris del usuaris");
	}

	/**
	 * elimina el panel de l'adiminstrador i crea el panel per defecte
	 * @author SergioHernandez
	 */
	public void removeAdministradorPanelToDefautl() {
		centralTabPanel.remove(administradorLlibres);
		//centralTabPanel.remove(administradorPrestecs);
		centralTabPanel.remove(administradorUsuaris);
		//centralTabPanel.remove(administradorComentaris);
		centralTabPanel.addTab("Consulta Llibres ", icons.getConsultaLlbresIcon(), 
				consultaLlibresNoRegistrat, "Consulta els llibres de la biblioteca com usuari no registrat");
	}

	
	/**
	 * elimina el panel de l'ususuari i crea el panel per defecte
	 * @author SergioHernandez
	 */
	public void removeUsuariPanelToDefault() {
		
		centralTabPanel.remove(consultaLLibres);
		
		centralTabPanel.addTab("Consulta LLibres ", icons.getConsultaLlbresIcon(), 
				consultaLlibresNoRegistrat, "Consulta els llibres de la biblioteca com a usuari no registrat");
		//centralTabPanel.remove(consultaComentaris);
		//centralTabPanel.remove(consultaPrestecs);
	}
	
	public void setUsuariConectat(Usuari usuariConnectat) {
		this.usuariConnectat = usuariConnectat;
	}
	
	public Usuari getUsuariConnectat() {
		return usuariConnectat;
	}
	

}
