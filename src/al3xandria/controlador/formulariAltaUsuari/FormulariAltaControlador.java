package al3xandria.controlador.formulariAltaUsuari;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JOptionPane;

import al3xandria.model.ControlDeDades;
import al3xandria.strings.WarningStrings;
import al3xandria.vista.headPanel.FormulariAltaUsuari;
import al3xandria.vista.headPanel.HeadPanelMessages;

/**
 * Clase controladora del formulari d'alta d'usuari
 * 
 * @author SergioHernandez
 *
 */
public class FormulariAltaControlador implements ActionListener, MouseListener, 
ItemListener, WindowListener {

	private FormulariAltaUsuari formulariAltaUsuari;
	private ControlDeDades controlDeDades;

	/**
	 * Constructor
	 * 
	 * @param formulari --> formulari que controlarà
	 * @author SergioHernandez
	 */
	public FormulariAltaControlador(FormulariAltaUsuari formulari) {
		this.formulariAltaUsuari = formulari;
		controlDeDades = new ControlDeDades();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// comportament botó esborrar dades formulari
		if (formulariAltaUsuari.getEsborrarButton() == e.getSource()) {
			esborrarCampsAltaUsuari();
		}

		// comportament botó enviar dades per fer l'alta d'usuari. Si tot correcte envia
		// la consulta al servidor
		if (formulariAltaUsuari.getEnviarButton() == e.getSource()) {
			if (controlDeDades.comprovarCampsOmplertsFormulariAltaUsuari(
					formulariAltaUsuari.getNomField().getText(),
					formulariAltaUsuari.getCognomsField().getText(), 
					formulariAltaUsuari.getAdrecaField().getText(),
					formulariAltaUsuari.getEmailField().getText(), 
					formulariAltaUsuari.getPoblacioField().getText(),
					formulariAltaUsuari.getCodiPostalField().getText(), 
					formulariAltaUsuari.getPaisField().getText(),
					formulariAltaUsuari.getProvinciaComboBox().getSelectedIndex(), 
					formulariAltaUsuari.getTelefonField().getText(),
					formulariAltaUsuari.getDniNieField().getText(),
					formulariAltaUsuari.getDniNieComboBox().getSelectedIndex(),
					formulariAltaUsuari.getTipusUsuariComboBox().getSelectedIndex(),
					formulariAltaUsuari.getContrasenyaFieldToString())) {

				if (comprovarDadesFormulari()) {
					confirmacioEnviamentDadesAltaUsuari();
				}

			}
		}

		// comportament botó cancel.lar alta usuari
		if (formulariAltaUsuari.getCancellarButton() == e.getSource()) {
			avisTancamentFormulari();
		}

	}

	/**
	 * Comprova que totes les dades del formulari tinguin el format correcte
	 * 
	 * @return --> true si són correctes | false si són incorrectes
	 * @author SergioHernandez
	 */
	private boolean comprovarDadesFormulari() {
		boolean formatDadesCorrecta = false;
		if (comprovacioEmail()) {
			if (comprovacioTelefon()) {
				if(comprovacioContrasenya()) {
					if(comprovacioDniNie()) {
						formatDadesCorrecta = true;
					}
				}
			} else {
				controlDeDades.errorFormatTelefon();
			}

		} else {
			controlDeDades.errorEnElFormatDelEmailIntroduit();
		}

		return formatDadesCorrecta;
	}
	
	/*
	 * comprova el format del email
	 */
	public boolean comprovacioEmail() {
		return controlDeDades.comprovacioEmail(formulariAltaUsuari.getEmailField().getText());	
	}
	
	/*
	 * Comprova el format del telèfon
	 */
	public boolean comprovacioTelefon() {
		return controlDeDades.comprovacioFormatTelefon(formulariAltaUsuari.getTelefonField().getText());
	}
	
	/*
	 * Comprova el format del identificador DNI o NIE
	 */
	public boolean comprovacioDniNie() {
		boolean formatDniNie = false;
		if (formulariAltaUsuari.getDniNieComboBox().getSelectedIndex() == 1) {
			if (!controlDeDades.comprovacioValidezaDNI(formulariAltaUsuari.getDniNieField().getText())) {
				controlDeDades.errorEnElFormatDelDocumentIntroduit("DNI");
			} else {
				formatDniNie = true;
			}
		} else if (formulariAltaUsuari.getDniNieComboBox().getSelectedIndex() == 2) {
			if (!controlDeDades.comprovacioValidezaNIE(formulariAltaUsuari.getDniNieField().getText())) {
				controlDeDades.errorEnElFormatDelDocumentIntroduit("NIE");
			} else {
				formatDniNie = true;
			}
		}
		return formatDniNie;
	}
	
	/*
	 * Comprova la contrasenya
	 */
	public boolean comprovacioContrasenya() {
		return controlDeDades.comprovarContrasenyaFormulariAltaUsuari(formulariAltaUsuari.getContrasenyaFieldToString(), 
				formulariAltaUsuari.getRepetirContrasenyaFieldToString());
	}

	/**
	 * Esborra tots els camps del formulari i deixa la selecció del combobox al
	 * index 0
	 * 
	 * @author SergioHernandez
	 */
	private void esborrarCampsAltaUsuari() {
		formulariAltaUsuari.getNomField().setText("");
		formulariAltaUsuari.getCognomsField().setText("");
		formulariAltaUsuari.getDniNieField().setText("");
		formulariAltaUsuari.getEmailField().setText("");
		formulariAltaUsuari.getAdrecaField().setText("");
		formulariAltaUsuari.getCodiPostalField().setText("");
		formulariAltaUsuari.getPoblacioField().setText("");
		formulariAltaUsuari.getProvinciaComboBox().setSelectedIndex(0);
		formulariAltaUsuari.getPaisField().setText("");
		formulariAltaUsuari.getTelefonField().setText("");
		formulariAltaUsuari.getContrasenyaField().setText("");
		formulariAltaUsuari.getRepetirContrasenyaField().setText("");
		formulariAltaUsuari.getTipusUsuariComboBox().setSelectedIndex(0);
		formulariAltaUsuari.getDniNieComboBox().setSelectedIndex(0);
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

	/**
	 * Missatge que mostra un avís per tancar el formulari
	 * 
	 * @author SergioHernandez
	 */
	public void avisTancamentFormulari() {
		int valor = JOptionPane.showConfirmDialog(formulariAltaUsuari,
				WarningStrings.getString("ForumulariAltausuari.missatgeAvisTancamentFormulari"),
				WarningStrings.getString("ForumulariAltausuari.titolMissatgeAvisTancamentFormulari"),
				JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
		if (valor == JOptionPane.YES_OPTION) {
			formulariAltaUsuari.setVisible(false);
			formulariAltaUsuari.dispose();
		}

	}
	
	
	/**
	 * Missatge que mostra les dades introduïdes per l'usuari al formulari 
	 * i demana si són correctes abans d'enviar-les al servidor
	 */
	public void confirmacioEnviamentDadesAltaUsuari() {
		int valor = JOptionPane.showConfirmDialog(formulariAltaUsuari,dadesUsuariFormulari(),
				WarningStrings.getString("FormulariAltaUsuari.titolConfirmarEnviarDadesAltaUsuari"),
				JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
		if (valor == JOptionPane.YES_OPTION) {
			avisDadesAltaUsuariEnviades();
			formulariAltaUsuari.setVisible(false);
			formulariAltaUsuari.dispose();
		}
	}
	
	/**
	 * Missatge per informar que les dades d'alta d'usuari han estat enviades
	 * 
	 * @author SergioHernandez
	 */
	public void avisDadesAltaUsuariEnviades() {
		JOptionPane.showMessageDialog(formulariAltaUsuari,
				WarningStrings.getString("FormulariAltaUsuari.avisDadesAltaUsuariEnviades"),
				WarningStrings.getString("FormulariAltaUsuari.titolAvisDadesAltaUsuariEnviades"),
				JOptionPane.INFORMATION_MESSAGE);
	}

	
	/**
	 * Mostra les dades introduïdes per l'usuari al formulari
	 * abans d'enviar-les al servidor
	 * @return String --> dades introduïdes per l'usuari al formulari
	 * @author SergioHernandez
	 */
	private String dadesUsuariFormulari() {
		String dades = 
			"Nom: " + formulariAltaUsuari.getNomField().getText() + "\n" +
			"Cognoms: " + formulariAltaUsuari.getCognomsField().getText() + "\n" +
			"Adreça: " + formulariAltaUsuari.getAdrecaField().getText() + "\n" +
			"Email: " + formulariAltaUsuari.getEmailField().getText() + "\n" +
			"Població: " + formulariAltaUsuari.getPoblacioField().getText() + "\n" +
			"Codi Postal: " + formulariAltaUsuari.getCodiPostalField().getText() + "\n" +
			"Provincia: " + String.valueOf(formulariAltaUsuari.getProvinciaComboBox().getSelectedItem()) + "\n" +
			"Pais: " + formulariAltaUsuari.getPaisField().getText() + "\n" +
			"Mòbil: " + formulariAltaUsuari.getTelefonField().getText() + "\n" +
			"Identificador " + String.valueOf(formulariAltaUsuari.getDniNieComboBox().getSelectedItem()) + 
			": " + formulariAltaUsuari.getDniNieField().getText() + "\n" +
			"Tipus Usuari: " + String.valueOf(formulariAltaUsuari.getTipusUsuariComboBox().getSelectedItem()) + "\n\n" +
			"Són correctes les dades introduïdes?"
				;
		return dades;
	}

	/**
	 * Mètode que cambia el toltip de el JTextField de l'entrada del document segons
	 * el tipus de document que s'introduïrà
	 * 
	 * @author SergioHernandez + consulta Internet
	 */
	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			Object item = e.getItem();
			if (item.equals("DNI")) {
				formulariAltaUsuari.getDniNieField()
						.setToolTipText(HeadPanelMessages.getString("FormulariAltaUsuari.dniNieFieldToltipDni"));
			} else if (item.equals("NIE")) {
				formulariAltaUsuari.getDniNieField()
						.setToolTipText(HeadPanelMessages.getString("FormulariAltaUsuari.dniNieFieldToltipNie"));
			} else {
				formulariAltaUsuari.getDniNieField()
						.setToolTipText(HeadPanelMessages.getString("FormulariAltaUsuari.dniNieFieldToltipDefault")); //$NON-NLS-1$
			}
		}

	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Mostra un avís quan es vol tancar el formulari
	 * @author SergioHernandez
	 */
	@Override
	public void windowClosing(WindowEvent e) {
		avisTancamentFormulari();
		
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

}
