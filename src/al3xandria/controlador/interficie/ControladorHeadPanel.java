package al3xandria.controlador.interficie;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import al3xandria.model.ComunicacioServer;
import al3xandria.model.ControlDeDades;
import al3xandria.strings.ExternalizeStrings;
import al3xandria.vista.footPanel.FootPanel;
import al3xandria.vista.headPanel.HeadPanel;

public class ControladorHeadPanel implements ActionListener, MouseListener {

	private HeadPanel headPanel;
	private ControlDeDades controlDeDades;
	private ComunicacioServer comunicacioServer;
	private ControladorFootPanel controladorFootPanel;
	private ControladorCentralPanel controladorCentralPanel;

	private String emailIntroduitPerLusuari;
	private String contrasenyaIntroduidaPerLusuari;
	private String estatDelBotoLogin;
	private ArrayList<Object> arrayListObjects;
	private Map<String, ArrayList<Object>> dadesRebudesServidorMap;
	private String tipusUsuari;

	public ControladorHeadPanel(HeadPanel headPanel, FootPanel footPanel) {
		this.headPanel = headPanel;
		comunicacioServer = new ComunicacioServer();
		controladorFootPanel = new ControladorFootPanel(footPanel);
	}
	
	public ControladorHeadPanel(HeadPanel headPanel) {
		this.headPanel = headPanel;
		comunicacioServer = new ComunicacioServer();
	}
	


	@Override
	public void actionPerformed(ActionEvent e) {

		if (headPanel.ferLoginButton == e.getSource()) {
			controlDeDades = new ControlDeDades();

			emailIntroduitPerLusuari = headPanel.getEmailintroduitPerLusuari().getText();
			contrasenyaIntroduidaPerLusuari = headPanel.getContrasenyaIntroduidaPerLusuari().getText();
			estatDelBotoLogin = headPanel.getFerLoginButton().getText();

			if (estatDelBotoLogin.equals("Login")) {
				if (controlDeDades.comprovacioEmail(emailIntroduitPerLusuari)) {
					enviarDadesAlServidorPerFerLogin();
				} else {
					errorEnElFormatDelEmailIntroduit();
				}

			} else if (estatDelBotoLogin.equals("Logout")) {

				enviarDadesAlServidorPerFerLogout();
			}
		}

	}

	public void enviarDadesAlServidorPerFerLogin() {
		comunicacioServer.repDadesPerEnviarLogin(emailIntroduitPerLusuari, contrasenyaIntroduidaPerLusuari);
		dadesRebudesServidorMap = comunicacioServer.getDadesRebudesDelServidor();
		if (dadesRebudesServidorMap.containsKey("0")) {
			permisPerFerLogin();
		} else {
			errorDadesPerFerLogin();
		}
	}

	public void enviarDadesAlServidorPerFerLogout() {
		comunicacioServer.repDadesPerEnviarLogout("idSessio", emailIntroduitPerLusuari);
		dadesRebudesServidorMap = comunicacioServer.getDadesRebudesDelServidor();
		if (dadesRebudesServidorMap.containsKey("0")) {
			avisDeLogout();
		} else {
			errorPeticioDeLogout();
		}
	}

	public void permisPerFerLogin() {
		ControladorFootPanel.setUsuariIconOn();
		desactivaElPanelPerFerLogin();
		arrayListObjects = dadesRebudesServidorMap.get("0");
		if (arrayListObjects.get(0).equals("true")) {
			tipusUsuari = "Administrador";
		} else {
			tipusUsuari = "Usuari";
		}

		usuariAFetLogin();
	}

	public void usuariAFetLogin() {

		String emailDeLusuari = headPanel.getEmailintroduitPerLusuari().getText();

		headPanel.getFerLoginButton().setText("Logout");
		headPanel.getFerLoginButton().setToolTipText(ExternalizeStrings.getString("HeadPanel.logoutButtonTolTip"));
		controladorFootPanel.omplirDadesUsuariConectat(tipusUsuari, emailDeLusuari, (String) arrayListObjects.get(2));
		switch (tipusUsuari) {
		case "Usuari":
			controladorCentralPanel.setUsuariPanel();
			break;

		case "Administrador":
			controladorCentralPanel.setAdministradorPanel();
			break;

		default:
			break;
		}

	}
	
	public void usuariAFetLogout() {
		setPanelLoginDefault();
		
		switch (tipusUsuari) {
		case "Usuari":
			controladorCentralPanel.removeUsuariPanelToDefault();
			break;

		case "Administrador":
			controladorCentralPanel.removeAdministradorPanelToDefautl();
			break;

		default:
			break;
		}
	}
	
	public void setPanelLoginDefault() {
		headPanel.getFerLoginButton().setText("Login");
		headPanel.getFerLoginButton().setToolTipText(ExternalizeStrings.getString("HeadPanel.loginButtonToltip"));
		headPanel.getEmailintroduitPerLusuari().setText("");
		controladorFootPanel.setInformacioUsuariDefault();
	}


	public void avisDeLogout() {
		JOptionPane optionPaneAvisDeLogout = new JOptionPane(
				ExternalizeStrings.getString("BotoLoginLogout.optionPaneAvisDeLogout"), JOptionPane.QUESTION_MESSAGE,
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
		controladorFootPanel.setUsuariIconOff();
		usuariAFetLogout();

	}

	public void errorEnElFormatDelEmailIntroduit() {
		JOptionPane.showMessageDialog(headPanel,
				ExternalizeStrings.getString("BotoLoginLogout.missatgeErrorEnElFormatDelEmailIntroduit"),
				ExternalizeStrings.getString("BotoLoginLogout.titolMissatgeErrorEnElFormatDelEmailIntroduit"),
				JOptionPane.ERROR_MESSAGE);
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

		headPanel.getEmailintroduitPerLusuari()
				.setToolTipText(ExternalizeStrings.getString("HeadPanel.emailIntroduitPerLusuariToltip"));
		headPanel.getContrasenyaIntroduidaPerLusuari()
				.setToolTipText(ExternalizeStrings.getString("HeadPanel.contrasenyaIntroduidaPerLusuariToltip"));
		headPanel.getNouUsuariButton().setToolTipText(ExternalizeStrings.getString("HeadPanel.nouUsuariButtonToltip"));
		headPanel.getCancelLoginLabel().setToolTipText(ExternalizeStrings.getString("HeadPanel.cancelLabelToltip"));
		headPanel.getHasOblidatLaContrasenyaLabel()
				.setToolTipText(ExternalizeStrings.getString("HeadPanel.hasOblidatContrasenyaToltip"));
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (headPanel.esborrarDadesLoginLabel == e.getSource()) {
			headPanel.getEmailintroduitPerLusuari().setText("");
			headPanel.getContrasenyaIntroduidaPerLusuari().setText("");
			headPanel.getEmailintroduitPerLusuari().requestFocus();
		}

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
