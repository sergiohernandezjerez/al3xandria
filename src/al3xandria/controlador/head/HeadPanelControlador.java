package al3xandria.controlador.head;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;

import al3xandria.strings.WarningStrings;
import al3xandria.vista.headPanel.FormulariAltaUsuari;
import al3xandria.vista.headPanel.HeadPanel;

/**
 * Clase per controlar les accions del HeadPanel
 * 
 * @author SergioHernandez
 *
 */
public class HeadPanelControlador implements ActionListener, MouseListener {

	private HeadPanel headPanel;
	private FormulariAltaUsuari formulariAltaUsuari;

	/**
	 * Constructor
	 * @param headPanel  --> el JPanel que controlarà
	 */
	public HeadPanelControlador(HeadPanel headPanel) {
		this.headPanel = headPanel;
	}

	
	@Override
	public void mouseClicked(MouseEvent e) {
		// es prem el boto d'esborrar les dades introduïdes
		if (headPanel.getEsborrarDadesLoginLabel() == e.getSource()) {
			headPanel.getEmailintroduitPerLusuari().setText("");
			headPanel.getContrasenyaIntroduidaPerLusuari().setText("");
			headPanel.getEmailintroduitPerLusuari().requestFocus();
		}
		
		if(headPanel.getNouUsuariButton() == e.getSource()) {
			formulariAltaUsuari = new FormulariAltaUsuari();
			formulariAltaUsuari.setVisible(true);
		}
		
		if(headPanel.getHasOblidatLaContrasenyaLabel() == e.getSource()) {
			mostrarInformacioRecuperacioContrasenya();
		}

	}

	/**
	 * Missatge per informar com recuperar la contrasenya
	 * 
	 * @author SergioHernandez
	 */
	public void mostrarInformacioRecuperacioContrasenya() {
		JOptionPane.showMessageDialog(formulariAltaUsuari,
				WarningStrings.getString("HeadPanel.missatgeRecuperarContrasenya"),
				WarningStrings.getString("HeadPanel.titolMissatgeRecuperarContrasenya"),
				JOptionPane.INFORMATION_MESSAGE);
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
		//es prem el boto de mostrar la contrasenya i la mostra
		if (headPanel.getMostrarContrasenya() == e.getSource()) {
			headPanel.getContrasenyaIntroduidaPerLusuari().setEchoChar((char) 0);
		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		//es deixa de premer el boto de mostrar la contrasenya i l'oculta
		if (headPanel.getMostrarContrasenya() == e.getSource()) {
			headPanel.getContrasenyaIntroduidaPerLusuari().setEchoChar('*');
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

}
