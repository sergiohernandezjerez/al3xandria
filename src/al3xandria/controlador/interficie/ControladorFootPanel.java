package al3xandria.controlador.interficie;

import al3xandria.strings.ExternalizeStrings;
import al3xandria.vista.footPanel.FootPanel;
import al3xandria.vista.icons.Icons;

public class ControladorFootPanel {

	
	private static FootPanel footPanel;
	private static Icons icons;
	
	public ControladorFootPanel(FootPanel footPanel) {
		this.footPanel = footPanel;
	}
	
	public static void setUsuariIconOn() {
		footPanel.getEstatUsuariIcon().setIcon(icons.getUsuariConectatIcon());
	}
	
	public void setUsuariIconOff() {
		footPanel.getEstatUsuariIcon().setIcon(icons.getUsuariNoConectatIcon());
	}
	
	public void omplirDadesUsuariConectat(String tipusUsuari, String emailUsuari, String idSessio) {
		footPanel.getEstasConectatComLabel().setText(ExternalizeStrings.getString("FootPanel.estasConectatComLabelConectat")); 
		footPanel.getTipuUsuariLabel().setText(tipusUsuari);
		footPanel.getEmailUsuariLabel().setText(emailUsuari);
		footPanel.getIdSessioUsuariLabel().setText(idSessio);
	}
	
	public void setInformacioUsuariDefault() {
		footPanel.getEstasConectatComLabel()
		.setText(ExternalizeStrings.getString("FootPanel.estasConectatComLabelNoConectat"));
		footPanel.getTipuUsuariLabel().setText(ExternalizeStrings.getString("FootPanel.tipusUsuariAnominLabel"));footPanel.getEmailUsuariLabel().setText("");
		footPanel.getIdSessioUsuariLabel().setText("");
	}
}
