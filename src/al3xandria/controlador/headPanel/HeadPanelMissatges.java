package al3xandria.controlador.headPanel;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import al3xandria.model.communicacioServer.PreparaDadesPerEnviar;
import al3xandria.strings.ExternalizeStrings;
import al3xandria.vista.headPanel.HeadPanel;

public class HeadPanelMissatges {

	private PreparaDadesPerEnviar preparaDadesPerEnviar;
	private HeadPanel headPanel;
	
	
	public void avisDeLogout() {
		JOptionPane optionPaneAvisDeLogout = new JOptionPane(ExternalizeStrings.getString("BotoLoginLogout.optionPaneAvisDeLogout"), 
				JOptionPane.QUESTION_MESSAGE, 
				JOptionPane.YES_NO_OPTION);
		JDialog dialogAvisDeLogout = optionPaneAvisDeLogout.createDialog(headPanel, 
				ExternalizeStrings.getString("BotoLoginLogout.dialogAvisDeLogout")); 
		dialogAvisDeLogout.setVisible(true);
		Object respostaUsuariPerFerLogout = optionPaneAvisDeLogout.getValue().toString();
		if (respostaUsuariPerFerLogout.equals("0")) {
			preparaDadesPerEnviar.permisPerFerLogout();
		}
	}
	
	public void errorDadesPerFerLogin() {
		JOptionPane.showMessageDialog(headPanel, 
				ExternalizeStrings.getString("BotoLoginLogout.missatgeErrorDadesPerFerLogin"), 
				ExternalizeStrings.getString("BotoLoginLogout.titolMissatgeErrorDadesPerFerLogin"), 
				JOptionPane.ERROR_MESSAGE);
		headPanel.getEmailintroduitPerLusuari().requestFocus();;
	}
	
	public void errorCampEmailBuit() {
		JOptionPane.showMessageDialog(headPanel, 
				ExternalizeStrings.getString("BotoLoginLogout.missatgeErrorCampEmailBuit"), 
				ExternalizeStrings.getString("BotoLoginLogout.titolMissatgeErrorCampBuit"), 
				JOptionPane.ERROR_MESSAGE); 
	}
	
	public void errorCampContrasenyaBuit() {
		JOptionPane.showMessageDialog(headPanel, 
				ExternalizeStrings.getString("BotoLoginLogout.missatgeErrorCampContrasenyaBuit"), 
				ExternalizeStrings.getString("BotoLoginLogout.titolMissatgeErrorCampBuit"), 
				JOptionPane.ERROR_MESSAGE); 
	}

	public void errorPeticioDeLogout() {
		JOptionPane.showMessageDialog(headPanel, 
				ExternalizeStrings.getString("BotoLoginLogout.missatgeErrorPeticioDeLogout"), 
				ExternalizeStrings.getString("BotoLoginLogout.titolMissatgeErrorPeticioDeLogout"), 
				JOptionPane.ERROR_MESSAGE); 
	}
	
	public void errorUsuariJaHaFetLogin() {
		JOptionPane.showMessageDialog(headPanel, 
				ExternalizeStrings.getString("BotoLoginLogout.missatgeErrorUsuariJaHaFetLogin"), 
				ExternalizeStrings.getString("BotoLoginLogout.titolMissatgeErrorUsuariJaHaFetLogin"), 
				JOptionPane.ERROR_MESSAGE); 
	}
	
	public void errorEnElFormatDelEmailIntroduit() {
		JOptionPane.showMessageDialog(headPanel, 
				ExternalizeStrings.getString("BotoLoginLogout.missatgeErrorEnElFormatDelEmailIntroduit"), 
				ExternalizeStrings.getString("BotoLoginLogout.titolMissatgeErrorEnElFormatDelEmailIntroduit"), 
				JOptionPane.ERROR_MESSAGE); 
	}
}
