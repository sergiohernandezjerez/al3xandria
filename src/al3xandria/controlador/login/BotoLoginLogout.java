package al3xandria.controlador.login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.ToolTipManager;
import javax.swing.plaf.metal.MetalToolTipUI;

import al3xandria.model.ControlDeDades;
import al3xandria.model.EnviarLoginServer;
import al3xandria.strings.ExternalizeStrings;
import al3xandria.vista.centralPanel.CentralPanel;
import al3xandria.vista.footPanel.FootPanel;
import al3xandria.vista.headPanel.HeadPanel;

public class BotoLoginLogout implements ActionListener {

	private String tipusUsuari;
	ControlDeDades controlDeDades;
	private HeadPanel headPanel;
	private FootPanel footPanel;
	private CentralPanel centralPanel;
	private EnviarLoginServer enviarLoginServer;
	private String[] dadesRebudesDelServidor;

	
	public BotoLoginLogout(HeadPanel headPanel, FootPanel footPanel, CentralPanel centralPanel) {
		this.headPanel = headPanel;
		this.footPanel = footPanel;
		this.centralPanel = centralPanel;
	
		controlDeDades = new ControlDeDades();
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		String emailUsuariIntroduit = headPanel.getEmailintroduitPerLusuari().getText();
		
		String estatDelBotoLogin = headPanel.getFerLoginButton().getText();

		if (estatDelBotoLogin.equals("Login")) { 
			if(controlDeDades.comprovacioEmail(emailUsuariIntroduit)) {
				enviarDadesPerFerLogin();
			}else {
				errorEnElFormatDelEmailIntroduit();
			}

		} else if (estatDelBotoLogin.equals("Logout")) { 
			enviarDadesPerFerLogout();
		}
	}
	
	public void enviarDadesPerFerLogin() {
		String emailUsuariIntroduit = headPanel.getEmailintroduitPerLusuari().getText();
		String contrasenyaUsuariIntroduida = headPanel.getContrasenyaIntroduidaPerLusuari().getText();
		enviarLoginServer = new EnviarLoginServer(emailUsuariIntroduit );
		dadesRebudesDelServidor = enviarLoginServer.getDadesDelServidor();

		if (dadesRebudesDelServidor[0].equals("0")) { 
			permisPerFerLogin();
		} else {
			errorDadesPerFerLogin();
		}
	}
	
	public void enviarDadesPerFerLogout() {
		enviarLoginServer = new EnviarLoginServer("logout"); 
		dadesRebudesDelServidor = enviarLoginServer.getDadesDelServidor();
		if (dadesRebudesDelServidor[0].equals("0")) { 
			avisDeLogout();
		} else {
			errorPeticioDeLogout();
		}
	}

	public void permisPerFerLogin() {
		footPanel.setUsuariIconOn();
		desactivaElPanelPerFerLogin();
		if (dadesRebudesDelServidor[2].equals("true")) { 
			tipusUsuari = "Administrador"; 
		} else {
			tipusUsuari = "Usuari"; 
		}
		
		usuariAFetLogin();
	}

	public void avisDeLogout() {
		JOptionPane optionPaneAvisDeLogout = new JOptionPane(ExternalizeStrings.getString("BotoLoginLogout.optionPaneAvisDeLogout"), 
				JOptionPane.QUESTION_MESSAGE, 
				JOptionPane.YES_NO_OPTION);
		JDialog dialogAvisDeLogout = optionPaneAvisDeLogout.createDialog(headPanel, 
				ExternalizeStrings.getString("BotoLoginLogout.dialogAvisDeLogout")); 
		dialogAvisDeLogout.setVisible(true);
		Object respostaUsuariPerFerLogout = optionPaneAvisDeLogout.getValue().toString();
		if (respostaUsuariPerFerLogout.equals("0")) {
			permisPerFerLogout();
		}
	}

	public void permisPerFerLogout() {
		activaElPanelPerFerLogin();
		footPanel.setUsuariIconOff();
		usuariAFetLogout();

	}

	public void errorDadesPerFerLogin() {
		JOptionPane.showMessageDialog(headPanel, 
				ExternalizeStrings.getString("BotoLoginLogout.missatgeErrorDadesPerFerLogin"), 
				ExternalizeStrings.getString("BotoLoginLogout.titolMissatgeErrorDadesPerFerLogin"), 
				JOptionPane.ERROR_MESSAGE);
		headPanel.getEmailintroduitPerLusuari().setText(""); 
		headPanel.getContrasenyaLoginLabel().setText(""); 
	}

	public void errorPeticioDeLogout() {
		JOptionPane.showMessageDialog(headPanel, 
				ExternalizeStrings.getString("BotoLoginLogout.missatgeErrorPeticioDeLogout"), 
				ExternalizeStrings.getString("BotoLoginLogout.titolMissatgeErrorPeticioDeLogout"), 
				JOptionPane.ERROR_MESSAGE); 
	}
	
	public void errorEnElFormatDelEmailIntroduit() {
		JOptionPane.showMessageDialog(headPanel, 
				ExternalizeStrings.getString("BotoLoginLogout.missatgeErrorEnElFormatDelEmailIntroduit"), 
				ExternalizeStrings.getString("BotoLoginLogout.titolMissatgeErrorEnElFormatDelEmailIntroduit"), 
				JOptionPane.ERROR_MESSAGE); 
	}


	public void desactivaElPanelPerFerLogin() {
		headPanel.getEmailintroduitPerLusuari().setEnabled(false);
		headPanel.getContrasenyaIntroduidaPerLusuari().setEnabled(false);
		headPanel.getNouUsuariButton().setEnabled(false);
		headPanel.getCancelLoginLabel().setEnabled(false);
		headPanel.getHasOblidatLaContrasenyaLabel().setEnabled(false);
		
		headPanel.getEmailintroduitPerLusuari().setToolTipText(null);
		headPanel.getContrasenyaIntroduidaPerLusuari().setToolTipText(null);
		headPanel.getNouUsuariButton().setToolTipText(null);
		headPanel.getCancelLoginLabel().setToolTipText(null);
		headPanel.getHasOblidatLaContrasenyaLabel().setToolTipText(null);
		
	}

	public void activaElPanelPerFerLogin() {
		headPanel.getEmailintroduitPerLusuari().setEnabled(true);
		headPanel.getNouUsuariButton().setEnabled(true);
		headPanel.getCancelLoginLabel().setEnabled(true);
		headPanel.getContrasenyaIntroduidaPerLusuari().setEnabled(true);
		headPanel.getHasOblidatLaContrasenyaLabel().setEnabled(true);
		
		headPanel.getEmailintroduitPerLusuari().setToolTipText(ExternalizeStrings.getString("HeadPanel.emailIntroduitPerLusuariToltip"));
		headPanel.getContrasenyaIntroduidaPerLusuari().setToolTipText(ExternalizeStrings.getString("HeadPanel.contrasenyaIntroduidaPerLusuariToltip"));
		headPanel.getNouUsuariButton().setToolTipText(ExternalizeStrings.getString("HeadPanel.nouUsuariButtonToltip"));
		headPanel.getCancelLoginLabel().setToolTipText(ExternalizeStrings.getString("HeadPanel.cancelLabelToltip"));
		headPanel.getHasOblidatLaContrasenyaLabel().setToolTipText(ExternalizeStrings.getString("HeadPanel.hasOblidatContrasenyaToltip"));
	}

	public void usuariAFetLogin() {
		
		String emailDeLusuari = headPanel.getEmailintroduitPerLusuari().getText();
		
		headPanel.getFerLoginButton().setText("Logout");
		headPanel.getFerLoginButton().setToolTipText(ExternalizeStrings.getString("HeadPanel.logoutButtonTolTip"));
		footPanel.getEstasConectatComLabel().setText(ExternalizeStrings.getString("FootPanel.estasConectatComLabelConectat")); 
		footPanel.getTipuUsuariLabel().setText(tipusUsuari);
		footPanel.getEmailUsuariLabel().setText(emailDeLusuari);
		footPanel.getIdSessioUsuariLabel().setText(dadesRebudesDelServidor[1]);
		
		switch (tipusUsuari) {
		case "Usuari":
			centralPanel.setUsuariPanel();
			break;
			
		case "Administrador": 
			centralPanel.setAdministradorPanel();
			break;

		default:
			break;
		}
		
	}


	public void usuariAFetLogout() {
		setPanelLoginDefault();
		footPanel.getEmailUsuariLabel().setText(""); 
		footPanel.getIdSessioUsuariLabel().setText(""); 
		
		switch (tipusUsuari) {
		case "Usuari": 
			centralPanel.removeUsuariPanelToDefault();
			break;
			
		case "Administrador": 
			centralPanel.removeAdministradorPanelToDefautl();
			break;

		default:
			break;
		}
	}

	public void setPanelLoginDefault() {
		headPanel.getFerLoginButton().setText("Login"); 
		headPanel.getFerLoginButton().setToolTipText(ExternalizeStrings.getString("HeadPanel.loginButtonToltip"));
		headPanel.getEmailintroduitPerLusuari().setText(""); 
		footPanel.getEstasConectatComLabel().setText(ExternalizeStrings.getString("FootPanel.estasConectatComLabelNoConectat")); 
		footPanel.getTipuUsuariLabel().setText(ExternalizeStrings.getString("FootPanel.tipusUsuariAnominLabel"));
	}

	public void setTipusUsuari(String tipusUsuari) {
		this.tipusUsuari = tipusUsuari;
	}
	
	public String getTipusUsuari() {
		return tipusUsuari;
	}
}
