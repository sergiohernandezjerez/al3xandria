package al3xandria.controlador.login;

import al3xandria.model.Usuari;
import al3xandria.vista.centralPanel.CentralPanel;
import al3xandria.vista.footPanel.FootPanel;
import al3xandria.vista.headPanel.HeadPanel;
import al3xandria.vista.principal.PrincipalFrame;



public class Controller {

	private CentralPanel centralPanel;
	private HeadPanel headPanel;
	private FootPanel footPanel;
	private PrincipalFrame framePrincipal;
	public void setCentralPanel(CentralPanel centralPanel) {
		this.centralPanel = centralPanel;
	}


	public void setHeadPanel(HeadPanel headPanel) {
		this.headPanel = headPanel;
		
	}

	public void setFootPanel(FootPanel footPanel) {
		this.footPanel = footPanel;
		
	}

	public void setFramePrincipal(PrincipalFrame framePrincipal) {
		this.framePrincipal = framePrincipal;
		
	}


	public void addHeadPanel() {
		framePrincipal.add(headPanel);
	}
	
	public void addCentralPanel() {
			framePrincipal.add(centralPanel);
	}
	
	public void addFootPanel() {
		framePrincipal.add(footPanel);
	}

	public void setUsuariPanel(Usuari usuari) {
		
	}
	
	public FootPanel getFootPanel() {
		return footPanel;
	}
	

	public HeadPanel getHeadPanel() {
		return headPanel;
	}
	
	public CentralPanel getCentralPanel() {
		return centralPanel;
	}
	
	
}
