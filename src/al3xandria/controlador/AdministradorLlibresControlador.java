package al3xandria.controlador;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;

import al3xandria.strings.ExternalizeStrings;
import al3xandria.vista.centralPanel.AdministradorLlibres;
import al3xandria.vista.centralPanel.CentralPanelMessages;

public class AdministradorLlibresControlador implements MouseListener {

	private AdministradorLlibres administradorLlibres;

	public AdministradorLlibresControlador(AdministradorLlibres administradorLlibres) {
		this.administradorLlibres = administradorLlibres;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (administradorLlibres.getAfegirAutorLabel() == e.getSource()) {
			formulariAfegirNouElement("Autor");
		}
		if (administradorLlibres.getAfegirGenereLabel() == e.getSource()) {
			formulariAfegirNouElement("Genere");
		}
		if (administradorLlibres.getAfegirEditorialLabel() == e.getSource()) {
			formulariAfegirNouElement("Editorial");
		}

	}

	private void formulariAfegirNouElement(String nomTaula) {
		String nouRegistre;
		nouRegistre = JOptionPane.showInputDialog(null, "Nom de " + nomTaula + " que vols donar d'alta", "Nou " + nomTaula,
				JOptionPane.WARNING_MESSAGE);
		if (nouRegistre != null) {
			int valor = JOptionPane.showConfirmDialog(administradorLlibres,
					CentralPanelMessages.getString("AdministradorLlibres.missatgeEnviarNouElement") + nomTaula + ": "
							+ nouRegistre,
					CentralPanelMessages.getString("AdministradorLlibres.titolEnviarNouElement"),
					JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null);
			if (valor == JOptionPane.YES_OPTION) {
				 enviarConsultaSegonsElement(nomTaula, nouRegistre);
			}
		}
	}
	
	// TODO crear metode enviar consulta segons element
	public void enviarConsultaSegonsElement(String nomTaula, String nouRegistre) {
		//TODO enviar consulta segons element
		switch (nomTaula) {
		case "Autor":
			System.out.println();
			break;
		case "Genere":
			System.out.println("Enviar Gènere: " + nouElement);
			break;
		case "Editorial":
			System.out.println("Enviar Editorial: " + nouElement);
			break;
		default:
			break;
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
