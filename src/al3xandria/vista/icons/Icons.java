package al3xandria.vista.icons;

import java.awt.Image;
import java.io.File;

import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 * clase que crea les icones que utilitza l'aplicació
 * 
 * @author SergioHernandez
 *
 */
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
	private Icon mostrarContrasenyaIcon;
	private Icon lupaIcon;

	public Icons() {
		consultaLlbresIcon = new ImageIcon(
				new ImageIcon("." + File.separator + "resources" + File.separator + "consultaLlibresIcon.png")
						.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));

		consultaComentarisIcon = new ImageIcon(
				new ImageIcon("." + File.separator + "resources" + File.separator + "consultaComentarisIcon.png")
						.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));

		consultaPrestecsIcon = new ImageIcon(
				new ImageIcon("." + File.separator + "resources" + File.separator + "consultaPrestecsIcon.png")
						.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));

		administrarUsuarisIcon = new ImageIcon(
				new ImageIcon("." + File.separator + "resources" + File.separator + "administrarUsuarisIcon.png")
						.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));

		administrarPrestecsIcon = new ImageIcon(
				new ImageIcon("." + File.separator + "resources" + File.separator + "administrarPrestecsIcon.png")
						.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));

		administrarLibresIcon = new ImageIcon(
				new ImageIcon("." + File.separator + "resources" + File.separator + "administrarLlibresIcon.png")
						.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));

		administrarComentarisIcon = new ImageIcon(
				new ImageIcon("." + File.separator + "resources" + File.separator + "administrarComentarisIcon.png")
						.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));

		mostrarContrasenyaIcon = new ImageIcon(
				new ImageIcon("." + File.separator + "resources" + File.separator + "mostrarContrasenyaIcon.png")
						.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));

		usuariNoConectatIcon = new ImageIcon(
				new ImageIcon("." + File.separator + "resources" + File.separator + "usuariNoConectatIcon.png")
						.getImage().getScaledInstance(32, 32, Image.SCALE_AREA_AVERAGING));

		usuariConectatIcon = new ImageIcon(
				new ImageIcon("." + File.separator + "resources" + File.separator + "usuariConectatIcon.png")
				.getImage().getScaledInstance(32, 32, Image.SCALE_AREA_AVERAGING));

		logoAlexandria = new ImageIcon("." + File.separator + "resources" + File.separator + "logo.png");
		
		lupaIcon = new ImageIcon(
				new ImageIcon("." + File.separator + "resources" + File.separator + "lupaIcon.png")
						.getImage());
	}

	/*-------------------------- Getters and Setters Methods --------------------------*/
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

	public Icon getMostrarContrasenyaIcon() {
		return mostrarContrasenyaIcon;
	}
	
	public Icon getLupaIcon() {
		return lupaIcon;
	}

}
