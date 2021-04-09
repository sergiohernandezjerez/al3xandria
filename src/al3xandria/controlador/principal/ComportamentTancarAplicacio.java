package al3xandria.controlador.principal;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JOptionPane;

import al3xandria.model.ComunicacioClientServidor;
import al3xandria.strings.WarningStrings;
import al3xandria.vista.headPanel.HeadPanel;

public class ComportamentTancarAplicacio implements WindowListener{
	
	private HeadPanel headPanel;
	private ComunicacioClientServidor comunicacioClientServidor;
	
	public ComportamentTancarAplicacio(HeadPanel headPanel) {
		this.headPanel = headPanel;
		comunicacioClientServidor = new ComunicacioClientServidor();
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		if (headPanel.getTipusUsuari() != null) {
			if (headPanel.getTipusUsuari().equals("Administrador")) {
				avisTancamentAplicacio();
			} else {
				avisImposibleTancarAplicacio();
			}
		} else {
			avisImposibleTancarAplicacio();
		}
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	/**
	 * Missatge que mostra un avís quan un usuari vol tancar l'aplicació
	 * 
	 * @author SergioHernandez
	 */
	public void avisImposibleTancarAplicacio() {
		JOptionPane.showMessageDialog(headPanel,
				WarningStrings.getString("PrincipalFrame.missatgeNoEsPotTancarAplicacio"),
				WarningStrings.getString("PrincipalFrame.titolMissatgeNoEsPotTancarAplicacio"),
				JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * Missatge que mostra un avís per tancar l'aplicació i fer logout i demana
	 * confirmació Quan es confirma, s'envia un missatge per fer logout de l'usuari
	 * i tanca l'aplicació
	 * 
	 * @author SergioHernandez
	 */
	public void avisTancamentAplicacio() {
		int valor = JOptionPane.showConfirmDialog(headPanel,
				WarningStrings.getString("PrincipalFrame.missatgeAvisTancamentAplicacio"),
				WarningStrings.getString("PrincipalFrame.titolMissatgeAvisTancamentAplicacio"),
				JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
		if (valor == JOptionPane.YES_OPTION) {
			JOptionPane.showMessageDialog(headPanel,
					WarningStrings.getString("PrincipalFrame.missatgeAcomiadamentAplicacio"),
					WarningStrings.getString("PrincipalFrame.titolMissatgeAcomiadamentAplicacio"),
					JOptionPane.INFORMATION_MESSAGE);
			comunicacioClientServidor.iniciarComunicacio("logoutOK," + headPanel.getEmailintroduitPerLusuari().getText());
			System.exit(0);
		}

	}

}
