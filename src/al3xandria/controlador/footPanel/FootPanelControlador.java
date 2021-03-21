package al3xandria.controlador.footPanel;

import al3xandria.strings.ExternalizeStrings;
import al3xandria.vista.footPanel.FootPanel;
import al3xandria.vista.icons.Icons;

public class FootPanelControlador {

	
	private FootPanel footPanel;
	private Icons icons;
	
	public FootPanelControlador(FootPanel footPanel) {
	this.footPanel = footPanel;
		icons = new Icons();
	}
	
	
	public void canviaInformacioUsuari(String tipusUsuari, String email, String idSessio) {
		footPanel.getEstasConectatComLabel().setText(ExternalizeStrings.getString("FootPanel.estasConectatComLabelConectat")); 
		footPanel.getTipuUsuariLabel().setText(tipusUsuari);
		footPanel.getEmailUsuariLabel().setText(email);
		footPanel.getIdSessioUsuariLabel().setText(idSessio);
		
	}
	
	public void esborraInformacioUsuari() {
		footPanel.getEmailUsuariLabel().setText(""); 
		footPanel.getIdSessioUsuariLabel().setText(""); 
	}
	
	public void panelInformacioPerDefecte() {
		footPanel.getEstasConectatComLabel().setText(ExternalizeStrings.getString("FootPanel.estasConectatComLabelNoConectat")); 
		footPanel.getTipuUsuariLabel().setText(ExternalizeStrings.getString("FootPanel.tipusUsuariAnominLabel"));
	}
	
	public void setUsuariIconOn() {
		footPanel.getEstatUsuariIcon().setIcon(icons.getUsuariConectatIcon());
	}
	
	public void setUsuariIconOff() {
		footPanel.getEstatUsuariIcon().setIcon(icons.getUsuariNoConectatIcon());
	}

