package al3xandria.model.communicacioServer;

import al3xandria.controlador.centralPanel.CentralPanelControlador;
import al3xandria.controlador.footPanel.FootPanelControlador;
import al3xandria.controlador.headPanel.HeadPanelControlador;
import al3xandria.controlador.headPanel.HeadPanelMissatges;
import al3xandria.vista.centralPanel.CentralPanel;
import al3xandria.vista.footPanel.FootPanel;
import al3xandria.vista.headPanel.HeadPanel;

public class PreparaDadesPerEnviar {

	private HeadPanelMissatges headPanelMissatges;
	private HeadPanelControlador headPanelControlador;
	private FootPanel footPanel;
	private HeadPanel headPanel;
	private CentralPanel centralPanel;
	private EnviaRepDadesServidor enviaRepDadesServidor;
	private String[] dadesRebudesDelServidor;
	private String idSessio;
	private String tipusUsuari;
	private FootPanelControlador footPanelControlador;
	private CentralPanelControlador centralPanelControlador;
	
	public PreparaDadesPerEnviar() {
		
	}
	
	public void preparaDadesPerFerLogin(String email, String contrasenya) {
		enviaRepDadesServidor = new EnviaRepDadesServidor("login" + "," + email + "," + contrasenya );
		dadesRebudesDelServidor = enviaRepDadesServidor.getDadesDelServidor();

		if (dadesRebudesDelServidor[0].equals("0")) { 
			
			idSessio = dadesRebudesDelServidor[1];
			permisPerFerLogin();
		} else if(dadesRebudesDelServidor[0].equals("550")){
			headPanelMissatges.errorUsuariJaHaFetLogin();
		}else {
			headPanelMissatges.errorDadesPerFerLogin();
		}
	}
	
	public void prepararDadesPerFerLogout() {
		enviaRepDadesServidor = new EnviaRepDadesServidor("logout," + idSessio); 
		dadesRebudesDelServidor = enviaRepDadesServidor.getDadesDelServidor();
		if (dadesRebudesDelServidor[0].equals("0")) { 
			headPanelMissatges.avisDeLogout();
		}else {
			headPanelMissatges.errorPeticioDeLogout();
		}
	}
	
	
	public void permisPerFerLogin() {
		footPanelControlador.setUsuariIconOn();
		headPanelControlador.desactivaElPanelPerFerLogin();
		if (dadesRebudesDelServidor[2].equals("administrador")) { 
			tipusUsuari = "Administrador"; 
		} else {
			tipusUsuari = "Usuari"; 
		}
		
		usuariAFetLogin(dadesRebudesDelServidor[3]);
	}
	
	
	public void permisPerFerLogout() {
		headPanelControlador.activaElPanelPerFerLogin();
		footPanel.setUsuariIconOff();
		usuariAFetLogout();

	}
	
public void usuariAFetLogin(String email) {
				
		headPanelControlador.canviaBotoLoginPerLogout();
		footPanelControlador.canviaInformacioUsuari(tipusUsuari, email,dadesRebudesDelServidor[1]);
		switch (tipusUsuari) {
		case "Usuari":
			centralPanelControlador.setUsuariPanel();
			break;
			
		case "Administrador": 
			centralPanelControlador.setAdministradorPanel();
			break;

		default:
			break;
		}
		
	}

public void usuariAFetLogout() {
	setPanelLoginDefault();
	footPanelControlador.esborraInformacioUsuari();
	
	switch (tipusUsuari) {
	case "Usuari": 
		centralPanelControlador.removeUsuariPanelToDefault();
		break;
		
	case "Administrador": 
		centralPanelControlador.removeAdministradorPanelToDefautl();
		break;

	default:
		break;
	}
}

public void setPanelLoginDefault() {
	headPanelControlador.panelLoginPerDefecte();
	footPanelControlador.panelInformacioPerDefecte();
}


/*-------------------------- Getters and Setters Methods --------------------------*/
public void setTipusUsuari(String tipusUsuari) {
	this.tipusUsuari = tipusUsuari;
}

public String getTipusUsuari() {
	return tipusUsuari;
}
	
}
