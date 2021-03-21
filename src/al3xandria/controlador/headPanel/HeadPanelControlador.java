package al3xandria.controlador.headPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;

import al3xandria.model.ControlDeDades;
import al3xandria.model.communicacioServer.PreparaDadesPerEnviar;
import al3xandria.strings.ExternalizeStrings;
import al3xandria.vista.footPanel.FootPanel;
import al3xandria.vista.headPanel.HeadPanel;

public class HeadPanelControlador implements ActionListener, MouseListener{

	private String emailUsuariIntroduit;
	private String estatDelBotoLogin;
	private String contrasenyaUsuariIntroduit;
	
	private HeadPanel headPanel;
	private ControlDeDades controlDeDades;
	private HeadPanelMissatges headPanelMissatges;
	private PreparaDadesPerEnviar preparaDadesPerEnviar;
	
	
	public HeadPanelControlador(HeadPanel headPanel) {
		this.headPanel = headPanel;
		controlDeDades = new ControlDeDades();
		headPanelMissatges = new HeadPanelMissatges();
		preparaDadesPerEnviar = new PreparaDadesPerEnviar();
		
	}
	
		
	@Override
	public void actionPerformed(ActionEvent e) {
		emailUsuariIntroduit = headPanel.getEmailintroduitPerLusuari().getText();
		estatDelBotoLogin = headPanel.getFerLoginButton().getText();
		contrasenyaUsuariIntroduit = headPanel.getContrasenyaIntroduidaPerLusuariToString();
        if(controlDeDades.comprovarCampsOmplerts(emailUsuariIntroduit, contrasenyaUsuariIntroduit)) {
    		if (estatDelBotoLogin.equals("Login")) { 
    			if(controlDeDades.comprovacioEmail(emailUsuariIntroduit)) {
    				preparaDadesPerEnviar.preparaDadesPerFerLogin(emailUsuariIntroduit, contrasenyaUsuariIntroduit);
    			}else {
    				headPanelMissatges.errorEnElFormatDelEmailIntroduit();
    			}

    		} else if (estatDelBotoLogin.equals("Logout")) { 
    			preparaDadesPerEnviar.prepararDadesPerFerLogout();
    		}
            }
	}
	
	public void canviaBotoLoginPerLogout() {
		headPanel.getFerLoginButton().setText("Logout");
		headPanel.getFerLoginButton().setToolTipText(ExternalizeStrings.getString("HeadPanel.logoutButtonTolTip"));
	}


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

	public void activaElPanelPerFerLogin() {
		headPanel.getEmailintroduitPerLusuari().setEnabled(true);
		headPanel.getNouUsuariButton().setEnabled(true);
		headPanel.getEsborrarDadesLoginLabel().setEnabled(true);
		headPanel.getContrasenyaIntroduidaPerLusuari().setEnabled(true);
		headPanel.getHasOblidatLaContrasenyaLabel().setEnabled(true);
		headPanel.getMostrarContrasenya().setEnabled(true);
		
		headPanel.getEmailintroduitPerLusuari().setToolTipText(ExternalizeStrings.getString("HeadPanel.emailIntroduitPerLusuariToltip"));
		headPanel.getContrasenyaIntroduidaPerLusuari().setToolTipText(ExternalizeStrings.getString("HeadPanel.contrasenyaIntroduidaPerLusuariToltip"));
		headPanel.getNouUsuariButton().setToolTipText(ExternalizeStrings.getString("HeadPanel.nouUsuariButtonToltip"));
		headPanel.getEsborrarDadesLoginLabel().setToolTipText(ExternalizeStrings.getString("HeadPanel.cancelLabelToltip"));
		headPanel.getHasOblidatLaContrasenyaLabel().setToolTipText(ExternalizeStrings.getString("HeadPanel.hasOblidatContrasenyaToltip"));
		headPanel.getMostrarContrasenya().setToolTipText(ExternalizeStrings.getString("HeadPanel.mostrarContrasenyaToltip"));
	}
	
	public void panelLoginPerDefecte() {
		headPanel.getFerLoginButton().setText("Login"); 
		headPanel.getFerLoginButton().setToolTipText(ExternalizeStrings.getString("HeadPanel.loginButtonToltip"));
		headPanel.getEmailintroduitPerLusuari().setText(""); 
		headPanel.getContrasenyaIntroduidaPerLusuari().setText("");
	}
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if(headPanel.getEsborrarDadesLoginLabel() == e.getSource()) {
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
if(headPanel.getMostrarContrasenya() == e.getSource()) {
	headPanel.getContrasenyaIntroduidaPerLusuari().setEchoChar((char)0);
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
if(headPanel.getMostrarContrasenya() == e.getSource()) {
	headPanel.getContrasenyaIntroduidaPerLusuari().setEchoChar('*');
		}
		
	}

	public HeadPanel getHeadPanel() {
		return headPanel;
	}
	
	
}
