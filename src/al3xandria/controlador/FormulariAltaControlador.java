package al3xandria.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;

import al3xandria.model.ControlDeDades;
import al3xandria.strings.ExternalizeStrings;
import al3xandria.vista.headPanel.FormulariAltaUsuari;

/**
 * Clase controladora del formulari d'alta d'usuari
 * 
 * @author SergioHernandez
 *
 */
public class FormulariAltaControlador implements ActionListener, MouseListener, ItemListener {

	private FormulariAltaUsuari formulariAltaUsuari;
	private ControlDeDades controlDeDades;

	/**
	 * Constructor
	 * 
	 * @param formulari --> formulari que controlar�
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
		// comportament bot� esborrar dades formulari
		if (formulariAltaUsuari.getEsborrarButton() == e.getSource()) {
			esborrarCampsAltaUsuari();
		}

		// comportament bot� enviar dades per fer l'alta d'usuari. Si tot correcte envia
		// la consulta al servidor
		if (formulariAltaUsuari.getEnviarButton() == e.getSource()) {
			if (controlDeDades.comprovarCampsOmplertsFormulariAltaUsuari(formulariAltaUsuari.getNomField().getText(),
					formulariAltaUsuari.getCognomsField().getText(), formulariAltaUsuari.getAdrecaField().getText(),
					formulariAltaUsuari.getEmailField().getText(), formulariAltaUsuari.getPoblacioField().getText(),
					formulariAltaUsuari.getCodiPostalField().getText(), formulariAltaUsuari.getPaisField().getText(),
					formulariAltaUsuari.getProvinciaField().getText(), formulariAltaUsuari.getTelefonField().getText(),
					formulariAltaUsuari.getDniNieField().getText(),
					formulariAltaUsuari.getDniNieComboBox().getSelectedIndex(),
					formulariAltaUsuari.getTipusUsuariComboBox().getSelectedIndex())) {

				if (comprovarDadesFormulari()) {
					confirmacioEnviamentDadesAltaUsuari();
				}

			}
		}

		// comportament bot� cancel.lar alta usuari
		if (formulariAltaUsuari.getCancellarButton() == e.getSource()) {
			avisTancamentFormulari();
		}

	}

	/**
	 * Comprova que totes les dades del formulari tinguin el format correcte
	 * 
	 * @return --> true si s�n correctes | false si s�n incorrectes
	 * @author SergioHernandez
	 */
	private boolean comprovarDadesFormulari() {
		boolean formatDadesCorrecta = false;
		if (controlDeDades.comprovacioEmail(formulariAltaUsuari.getEmailField().getText())) {
			if (controlDeDades.comprovacioFormatTelefon(formulariAltaUsuari.getTelefonField().getText())) {
				if (formulariAltaUsuari.getDniNieComboBox().getSelectedIndex() == 1) {
					if (!controlDeDades.comprovacioValidezaDNI(formulariAltaUsuari.getDniNieField().getText())) {
						controlDeDades.errorEnElFormatDelDocumentIntroduit("DNI");
					} else {
						formatDadesCorrecta = true;
					}
				} else if (formulariAltaUsuari.getDniNieComboBox().getSelectedIndex() == 2) {
					if (!controlDeDades.comprovacioValidezaNIE(formulariAltaUsuari.getDniNieField().getText())) {
						controlDeDades.errorEnElFormatDelDocumentIntroduit("NIE");
					} else {
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

	/**
	 * Esborra tots els camps del formulari i deixa la selecci� del combobox al
	 * indez 0
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
		formulariAltaUsuari.getProvinciaField().setText("");
		formulariAltaUsuari.getPaisField().setText("");
		formulariAltaUsuari.getTelefonField().setText("");
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
	 * Missatge que mostra un av�s per tancar el formulari
	 * 
	 * @author SergioHernandez
	 */
	public void avisTancamentFormulari() {
		int valor = JOptionPane.showConfirmDialog(formulariAltaUsuari,
				ExternalizeStrings.getString("ForumulariAltausuari.missatgeAvisTancamentFormulari"),
				ExternalizeStrings.getString("ForumulariAltausuari.titolMissatgeAvisTancamentFormulari"),
				JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
		if (valor == JOptionPane.YES_OPTION) {
			formulariAltaUsuari.setVisible(false);
			formulariAltaUsuari.dispose();
		}

	}
	
	
	/**
	 * Missatge que mostra les dades introdu�des per l'usuari al formulari 
	 * i demana si s�n correctes abans d'enviar-les al servidor
	 */
	public void confirmacioEnviamentDadesAltaUsuari() {
		int valor = JOptionPane.showConfirmDialog(formulariAltaUsuari,dadesUsuariFormulari(),
				ExternalizeStrings.getString("FormulariAltaUsuari.titolConfirmarEnviarDadesAltaUsuari"),
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
				ExternalizeStrings.getString("FormulariAltaUsuari.avisDadesAltaUsuariEnviades"),
				ExternalizeStrings.getString("FormulariAltaUsuari.titolAvisDadesAltaUsuariEnviades"),
				JOptionPane.INFORMATION_MESSAGE);
	}

	
	/**
	 * Mostra les dades introdu�des per l'usuari al formulari
	 * abans d'enviar-les al servidor
	 * @return String --> dades introdu�des per l'usuari al formulari
	 * @author SergioHernandez
	 */
	private String dadesUsuariFormulari() {
		String dades = 
			"Nom: " + formulariAltaUsuari.getNomField().getText() + "\n" +
			"Cognoms: " + formulariAltaUsuari.getCognomsField().getText() + "\n" +
			"Adre�a: " + formulariAltaUsuari.getAdrecaField().getText() + "\n" +
			"Email: " + formulariAltaUsuari.getEmailField().getText() + "\n" +
			"Poblaci�: " + formulariAltaUsuari.getPoblacioField().getText() + "\n" +
			"Codi Postal: " + formulariAltaUsuari.getCodiPostalField().getText() + "\n" +
			"Provincia: " + formulariAltaUsuari.getProvinciaField().getText() + "\n" +
			"Pais: " + formulariAltaUsuari.getPaisField().getText() + "\n" +
			"M�bil: " + formulariAltaUsuari.getTelefonField().getText() + "\n" +
			"Identificador " + String.valueOf(formulariAltaUsuari.getDniNieComboBox().getSelectedItem()) + 
			": " + formulariAltaUsuari.getDniNieField().getText() + "\n" +
			"Tipus Usuari: " + String.valueOf(formulariAltaUsuari.getTipusUsuariComboBox().getSelectedItem()) + "\n\n" +
			"S�n correctes les dades introdu�des?"
				;
		return dades;
	}

	/**
	 * M�tode que cambia el toltip de el JTextField de l'entrada del document segons
	 * el tipus de document que s'introdu�r�
	 * 
	 * @author SergioHernandez + consulta Internet
	 */
	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			Object item = e.getItem();
			if (item.equals("DNI")) {
				formulariAltaUsuari.getDniNieField()
						.setToolTipText(ExternalizeStrings.getString("FormulariAltaUsuari.dniNieFieldToltipDni"));
			} else if (item.equals("NIE")) {
				formulariAltaUsuari.getDniNieField()
						.setToolTipText(ExternalizeStrings.getString("FormulariAltaUsuari.dniNieFieldToltipNie"));
			} else {
				formulariAltaUsuari.getDniNieField()
						.setToolTipText(ExternalizeStrings.getString("FormulariAltaUsuari.dniNieFieldToltipDefault")); //$NON-NLS-1$
			}
		}

	}

}