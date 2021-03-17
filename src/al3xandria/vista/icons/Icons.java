package al3xandria.vista.icons;

import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Icons {

	private Icon consultaLlbresIcon;
	private Icon logoAlexandria;
	private Icon consultaComentarisIcon;
	private Icon administrarUsuarisIcon;
	private Icon administrarPrestecsIcon;
	private Icon administrarLibresIcon;
	private Icon administrarComentarisIcon;
	private Icon consultaPrestecsIcon;
	private Icon usuariNoConectatIcon;
	private Icon usuariConectatIcon;

	public Icons() {
		consultaLlbresIcon = new ImageIcon(new ImageIcon("F:\\workSpaces\\eclipse\\Al3xandria\\resources\\consultaLlibresIcon.png").
				getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		
		consultaComentarisIcon = new ImageIcon(new ImageIcon("F:\\workSpaces\\eclipse\\Al3xandria\\resources\\consultaComentarisIcon.png").
				getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		
		consultaPrestecsIcon = new ImageIcon(new ImageIcon("F:\\workSpaces\\eclipse\\Al3xandria\\resources\\consultaPrestecsIcon.png").
				getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		
		administrarUsuarisIcon = new ImageIcon(new ImageIcon("F:\\workSpaces\\eclipse\\Al3xandria\\resources\\administrarUsuarisIcon.png").
				getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		
		administrarPrestecsIcon = new ImageIcon(new ImageIcon("F:\\workSpaces\\eclipse\\Al3xandria\\resources\\administrarPrestecsIcon.png").
				getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		
		administrarLibresIcon = new ImageIcon(new ImageIcon("F:\\workSpaces\\eclipse\\Al3xandria\\resources\\administrarLlibresIcon.png").
				getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		
		administrarComentarisIcon = new ImageIcon(new ImageIcon("F:\\workSpaces\\eclipse\\Al3xandria\\resources\\administrarComentarisIcon.png").
				getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
		
		usuariNoConectatIcon = new ImageIcon(new ImageIcon("F:\\workSpaces\\eclipse\\Al3xandria\\resources\\usuariNoConectatIcon.png").
				getImage().getScaledInstance(32, 32, Image.SCALE_AREA_AVERAGING));
		
		usuariConectatIcon = new ImageIcon(new ImageIcon("F:\\workSpaces\\eclipse\\Al3xandria\\resources\\usuariConectatIcon.png").
				getImage().getScaledInstance(32, 32, Image.SCALE_AREA_AVERAGING));
				
		logoAlexandria = new ImageIcon("F:\\workSpaces\\eclipse\\Al3xandria\\resources\\logo.png");
	}
	
	
	public Icon getConsultaLlbresIcon() {
		return consultaLlbresIcon;
	}

	public Icon getLogoAlexandria() {
		return logoAlexandria;
	}


	public Icon getConsultaComentarisIcon() {
		return consultaComentarisIcon;
	}


	public Icon getAdministrarUsuarisIcon() {
		return administrarUsuarisIcon;
	}


	public Icon getAdministrarPrestecsIcon() {
		return administrarPrestecsIcon;
	}


	public Icon getAdministrarLibresIcon() {
		return administrarLibresIcon;
	}


	public Icon getAdministrarComentarisIcon() {
		return administrarComentarisIcon;
	}


	public Icon getconsultaPrestecsIcon() {
		return consultaPrestecsIcon;
	}
	
	public Icon getConsultaPrestecsIcon() {
		return consultaPrestecsIcon;
	}
	
	public Icon getUsuariConectatIcon() {
		return usuariConectatIcon;
	}
	
	public Icon getUsuariNoConectatIcon() {
		return usuariNoConectatIcon;
	}
	
}
