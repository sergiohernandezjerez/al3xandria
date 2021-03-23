package al3xandria.controlador.login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import al3xandria.model.ControlDeDades;
import al3xandria.model.ComunicacioClientServidor;
import al3xandria.strings.ExternalizeStrings;
import al3xandria.vista.centralPanel.CentralPanel;
import al3xandria.vista.footPanel.FootPanel;
import al3xandria.vista.headPanel.HeadPanel;

/**
 * Classe que controla tot el que succeeix quan es prem el boto de login/logout
 * 
 * @author SergioHernandez
 *
 */
public class BotoLoginLogout implements ActionListener {

	private String tipusUsuari;
	ControlDeDades controlDeDades;
	private HeadPanel headPanel;
	private FootPanel footPanel;
	private CentralPanel centralPanel;
	private ComunicacioClientServidor enviarInformacioAlServidor;
	private String[] dadesRebudesDelServidor;
	private String idSessio;
	private String emailUsuariIntroduit;
	private String estatDelBotoLogin;
	private String contrasenyaUsuariIntroduida;

	/**
	 * Constructor
	 * 
	 * @param headPanel    --> Panel HeadPanel
	 * @param footPanel    --> Panel FootPanel
	 * @param centralPanel --> Panel CentralPanel
	 */
	public BotoLoginLogout(HeadPanel headPanel, FootPanel footPanel, CentralPanel centralPanel) {
		this.headPanel = headPanel;
		this.footPanel = footPanel;
		this.centralPanel = centralPanel;

		controlDeDades = new ControlDeDades();
	}

	/**
	 * Escolta quan es fa clic al botó Login i comprova si els camps estan omplerts
	 * i si el format de l'email és correcte. Si la funció del botó es Login envia
	 * les dades per fer login i si és Logout, envia les dades per fer logout. Tambè
	 * mostra els errors de camps buit i format d'email incorrecte
	 * 
	 * @author SergioHernandez
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		emailUsuariIntroduit = headPanel.getEmailintroduitPerLusuari().getText();
		estatDelBotoLogin = headPanel.getFerLoginButton().getText();
		contrasenyaUsuariIntroduida = headPanel.getContrasenyaIntroduidaPerLusuariToString();

		if (comprovarCampsOmplerts(emailUsuariIntroduit, contrasenyaUsuariIntroduida)) {
			if (estatDelBotoLogin.equals("Login")) {
				if (controlDeDades.comprovacioEmail(emailUsuariIntroduit)) {

					enviarDadesPerFerLogin();
				} else {
					errorEnElFormatDelEmailIntroduit();
				}

			} else if (estatDelBotoLogin.equals("Logout")) {
				enviarDadesPerFerLogout();
			}
		}
	}

	/**
	 * Comprova si els camps de text JTextField no estan buits
	 * 
	 * @param email       -> camp de text que pertany al email
	 * @param contrasenya -> camp de text que pertany a la contrasenya
	 * @return true si els dos camps estan omplerts | false si no ho estan
	 * @author SergioHernandez
	 */
	private boolean comprovarCampsOmplerts(String email, String contrasenya) {
		boolean totOmplert = false;
		if (email.length() == 0) {
			errorCampEmailBuit();
		} else if (contrasenya.length() == 0) {
			errorCampContrasenyaBuit();
		} else {
			totOmplert = true;
		}

		return totOmplert;
	}

	/**
	 * Envia tres valors separats per comes (login, email, contrasenya) i rep la
	 * resposta del servidor:<br>
	 * usuari trobat: 0,idSessio, tipus d'usuari --> dona permís per fer login<br>
	 * usuari no trobat: 440 --> mostra missatge error<br>
	 * usuari ja està logeat: 550 --> mostra missatge error
	 * 
	 * @author SergioHernandez
	 */
	public void enviarDadesPerFerLogin() {
		enviarInformacioAlServidor = new ComunicacioClientServidor(
				"login" + "," + emailUsuariIntroduit + "," + contrasenyaUsuariIntroduida);
		dadesRebudesDelServidor = enviarInformacioAlServidor.getDadesDelServidor();
		if (dadesRebudesDelServidor != null) {
			if (dadesRebudesDelServidor[0].equals("0")) {
				idSessio = dadesRebudesDelServidor[1];
				permisPerFerLogin();
			} else if (dadesRebudesDelServidor[0].equals("550")) {
				errorUsuariJaHaFetLogin();
			} else if (dadesRebudesDelServidor[0].equals("440")) {
				errorDadesPerFerLogin();
			}
		}

	}

	/**
	 * Envia dos valors separats per comes (logout, idSessio) i rep la resposta del
	 * servidor<br>
	 * logout correcte: 0 --> mostra avís de logout<br>
	 * el logout no s'ha pogut fer: 440 --> mostra missatge error<br>
	 * 
	 * @author SergioHernandez
	 */
	public void enviarDadesPerFerLogout() {
		enviarInformacioAlServidor = new ComunicacioClientServidor("logout," + idSessio);
		dadesRebudesDelServidor = enviarInformacioAlServidor.getDadesDelServidor();
		if (dadesRebudesDelServidor[0].equals("0")) {
			avisDeLogout();
		} else if (dadesRebudesDelServidor[0].equals("440")) {
			errorPeticioDeLogout();
		}
	}

	/**
	 * Si té permis per fer login, assigna el tipus d'usuari i fa login
	 * 
	 * @author SergioHernandez
	 */
	public void permisPerFerLogin() {
		footPanel.setUsuariIconOn();
		desactivaElPanelPerFerLogin();
		if (dadesRebudesDelServidor[2].equals("Administrador")) {
			tipusUsuari = "Administrador";

		} else {
			tipusUsuari = "Usuari";
		}

		usuariAFetLogin();
	}

	/**
	 * Modifica el HeadPanel i el FootPanel per mostrar l'informació necesaria i
	 * segons el tipus d'usuari mostra el panel central
	 * 
	 * @author SergioHernandez
	 */
	public void usuariAFetLogin() {
		headPanel.getFerLoginButton().setText("Logout");
		headPanel.getFerLoginButton().setToolTipText(ExternalizeStrings.getString("HeadPanel.logoutButtonTolTip"));
		footPanel.getEstasConectatComLabel()
				.setText(ExternalizeStrings.getString("FootPanel.estasConectatComLabelConectat"));
		footPanel.getTipuUsuariLabel().setText(tipusUsuari);
		footPanel.getEmailUsuariLabel().setText(emailUsuariIntroduit);
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
		headPanel.setTipusUsuari(tipusUsuari);
	}

	/**
	 * Torna el panel de login al seu estat per defecte i modifica el panel central
	 * a l'estat per defecte
	 * 
	 * @author SergioHernandez
	 */
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
		headPanel.setTipusUsuari(null);
	}

	/**
	 * Missatge que mostra un avís per fer logout i demana confirmació
	 * 
	 * @author SergioHernandez
	 */
	public void avisDeLogout() {

		int valor = JOptionPane.showConfirmDialog(headPanel,
				ExternalizeStrings.getString("BotoLoginLogout.optionPaneAvisDeLogout"),
				ExternalizeStrings.getString("BotoLoginLogout.dialogAvisDeLogout"), JOptionPane.YES_NO_OPTION,
				JOptionPane.WARNING_MESSAGE, null);
		if (valor == JOptionPane.YES_OPTION) {
			enviarInformacioAlServidor = new ComunicacioClientServidor("logoutOK," + emailUsuariIntroduit);
			permisPerFerLogout();
		}

	}

	/**
	 * Si té permís per fer logout el fa
	 * 
	 * @author SergioHernandez
	 */
	public void permisPerFerLogout() {
		activaElPanelPerFerLogin();
		footPanel.setUsuariIconOff();
		usuariAFetLogout();

	}

	/**
	 * Missatge que mostra un avís quan les dades introduïdes no pertanyen a un
	 * usuari
	 * 
	 * @author SergioHernandez
	 */
	public void errorDadesPerFerLogin() {
		JOptionPane.showMessageDialog(headPanel,
				ExternalizeStrings.getString("BotoLoginLogout.missatgeErrorDadesPerFerLogin"),
				ExternalizeStrings.getString("BotoLoginLogout.titolMissatgeErrorDadesPerFerLogin"),
				JOptionPane.ERROR_MESSAGE);
		headPanel.getEmailintroduitPerLusuari().requestFocus();
	}

	/**
	 * Missatge que mostra un avís quan el camp email està buit
	 * 
	 * @author SergioHernandez
	 */
	public void errorCampEmailBuit() {
		JOptionPane.showMessageDialog(headPanel,
				ExternalizeStrings.getString("BotoLoginLogout.missatgeErrorCampEmailBuit"),
				ExternalizeStrings.getString("BotoLoginLogout.titolMissatgeErrorCampBuit"), JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * Missatge que mostra un avís quan el camp contrasenya està buit
	 * 
	 * @author SergioHernandez
	 */
	public void errorCampContrasenyaBuit() {
		JOptionPane.showMessageDialog(headPanel,
				ExternalizeStrings.getString("BotoLoginLogout.missatgeErrorCampContrasenyaBuit"),
				ExternalizeStrings.getString("BotoLoginLogout.titolMissatgeErrorCampBuit"), JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * Missatge que mostra un avís quan hi ha hagut un error al fer logout
	 * 
	 * @author SergioHernandez
	 */
	public void errorPeticioDeLogout() {
		JOptionPane.showMessageDialog(headPanel,
				ExternalizeStrings.getString("BotoLoginLogout.missatgeErrorPeticioDeLogout"),
				ExternalizeStrings.getString("BotoLoginLogout.titolMissatgeErrorPeticioDeLogout"),
				JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * Missatge que mostra un avís quan l'usuari intenta fer login i ja té una
	 * sessió oberta
	 * 
	 * @author SergioHernandez
	 */
	public void errorUsuariJaHaFetLogin() {
		JOptionPane.showMessageDialog(headPanel,
				ExternalizeStrings.getString("BotoLoginLogout.missatgeErrorUsuariJaHaFetLogin"),
				ExternalizeStrings.getString("BotoLoginLogout.titolMissatgeErrorUsuariJaHaFetLogin"),
				JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * Missatge que mostra un avís quan el format de l'email no és correcte
	 * 
	 * @author SergioHernandez
	 */
	public void errorEnElFormatDelEmailIntroduit() {
		JOptionPane.showMessageDialog(headPanel,
				ExternalizeStrings.getString("BotoLoginLogout.missatgeErrorEnElFormatDelEmailIntroduit"),
				ExternalizeStrings.getString("BotoLoginLogout.titolMissatgeErrorEnElFormatDelEmailIntroduit"),
				JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * Quan es fa login desconecte el panel de login
	 * 
	 * @author SergioHernandez
	 */
	public void desactivaElPanelPerFerLogin() {
		headPanel.getEmailintroduitPerLusuari().setEnabled(false);
		headPanel.getContrasenyaIntroduidaPerLusuari().setEnabled(false);
		headPanel.getNouUsuariButton().setEnabled(false);
		headPanel.getEsborrarDadesLoginLabel().setEnabled(false);
		headPanel.getHasOblidatLaContrasenyaLabel().setEnabled(false);
		headPanel.getMostrarContrasenya().setEnabled(false);

		headPanel.getEmailintroduitPerLusuari().setToolTipText(null);
		headPanel.getContrasenyaIntroduidaPerLusuari().setToolTipText(null);
		headPanel.getNouUsuariButton().setToolTipText(null);
		headPanel.getEsborrarDadesLoginLabel().setToolTipText(null);
		headPanel.getHasOblidatLaContrasenyaLabel().setToolTipText(null);
		headPanel.getMostrarContrasenya().setToolTipText(null);

	}

	/**
	 * Quan es fa logout es conecte el panel de login
	 * 
	 * @author SergioHernandez
	 */
	public void activaElPanelPerFerLogin() {
		headPanel.getEmailintroduitPerLusuari().setEnabled(true);
		headPanel.getNouUsuariButton().setEnabled(true);
		headPanel.getEsborrarDadesLoginLabel().setEnabled(true);
		headPanel.getContrasenyaIntroduidaPerLusuari().setEnabled(true);
		headPanel.getHasOblidatLaContrasenyaLabel().setEnabled(true);
		headPanel.getMostrarContrasenya().setEnabled(true);

		headPanel.getEmailintroduitPerLusuari()
				.setToolTipText(ExternalizeStrings.getString("HeadPanel.emailIntroduitPerLusuariToltip"));
		headPanel.getContrasenyaIntroduidaPerLusuari()
				.setToolTipText(ExternalizeStrings.getString("HeadPanel.contrasenyaIntroduidaPerLusuariToltip"));
		headPanel.getNouUsuariButton().setToolTipText(ExternalizeStrings.getString("HeadPanel.nouUsuariButtonToltip"));
		headPanel.getEsborrarDadesLoginLabel()
				.setToolTipText(ExternalizeStrings.getString("HeadPanel.cancelLabelToltip"));
		headPanel.getHasOblidatLaContrasenyaLabel()
				.setToolTipText(ExternalizeStrings.getString("HeadPanel.hasOblidatContrasenyaToltip"));
		headPanel.getMostrarContrasenya()
				.setToolTipText(ExternalizeStrings.getString("HeadPanel.mostrarContrasenyaToltip"));
	}

	/**
	 * Posa el panel de login en el seu estat per defecte
	 * 
	 * @author SergioHernandez
	 */
	public void setPanelLoginDefault() {
		headPanel.getFerLoginButton().setText("Login");
		headPanel.getFerLoginButton().setToolTipText(ExternalizeStrings.getString("HeadPanel.loginButtonToltip"));
		headPanel.getEmailintroduitPerLusuari().setText("");
		headPanel.getContrasenyaIntroduidaPerLusuari().setText("");
		footPanel.getEstasConectatComLabel()
				.setText(ExternalizeStrings.getString("FootPanel.estasConectatComLabelNoConectat"));
		footPanel.getTipuUsuariLabel().setText(ExternalizeStrings.getString("FootPanel.tipusUsuariAnominLabel"));
	}

	/*-------------------------- Getters and Setters Methods --------------------------*/
	public void setTipusUsuari(String tipusUsuari) {
		this.tipusUsuari = tipusUsuari;
	}

	public String getTipusUsuari() {
		return tipusUsuari;
	}
}
