package al3xandria.controlador.food;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import al3xandria.controlador.head.BotoLoginLogout;
import al3xandria.model.objects.Usuari;
import al3xandria.vista.footPanel.FootPanel;
import al3xandria.vista.headPanel.FormulariAltaUsuari;

public class FootPanelControlador implements MouseListener{
	
	private FootPanel footPanel;
	private FormulariAltaUsuari formulariAltaUsuari = new FormulariAltaUsuari();
	private Usuari usuariConnectat;
	
	public FootPanelControlador(FootPanel footPanel) {
		this.footPanel = footPanel;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(footPanel.getModificarDadesLabel() == e.getSource()) {
			formulariAltaUsuari.setTipusAccio("modificacio");
			setUsuariConnectat(footPanel.getUsuariConectat());
			activarFormulariModificacio();

		}
		
	}

	private void activarFormulariModificacio() {
		formulariAltaUsuari.setVisible(true);
		omplirDadesFormulari();
		activarEdicioFormulari();
		
	}

	private void activarEdicioFormulari() {
		formulariAltaUsuari.getTitolLabel().setText("Modificar dades usuari");
		formulariAltaUsuari.getNomField().setEditable(false);
		formulariAltaUsuari.getCognomsField().setEditable(false);
		formulariAltaUsuari.getEmailField().setEditable(true);
		formulariAltaUsuari.getTelefonField().setEditable(true);
		formulariAltaUsuari.getAdrecaField().setEditable(true);
		formulariAltaUsuari.getPoblacioField().setEditable(true);
		formulariAltaUsuari.getCodiPostalField().setEditable(true);
		formulariAltaUsuari.getPaisField().setEditable(true);
		formulariAltaUsuari.getDniNieField().setEditable(false);
		formulariAltaUsuari.getDniNieComboBox().setEnabled(false);
		formulariAltaUsuari.getTipusUsuariComboBox().setEnabled(false);
		formulariAltaUsuari.getContrasenyaField().setEditable(true);
		formulariAltaUsuari.getRepetirContrasenyaField().setEditable(true);

		
	}

	private void omplirDadesFormulari() {
		formulariAltaUsuari.getNomField().setText(usuariConnectat.getNomUsuari());
		formulariAltaUsuari.getCognomsField().setText(usuariConnectat.getCognomsUsuari());
		formulariAltaUsuari.getEmailField().setText(usuariConnectat.getEmail());
		formulariAltaUsuari.getTelefonField().setText(usuariConnectat.getTelefon());
		formulariAltaUsuari.getAdrecaField().setText(usuariConnectat.getAdreca());
		formulariAltaUsuari.getPoblacioField().setText(usuariConnectat.getPoblacio());
		formulariAltaUsuari.getCodiPostalField().setText(usuariConnectat.getCodiPostal());
		formulariAltaUsuari.getPaisField().setText(usuariConnectat.getPais());
		formulariAltaUsuari.getProvinciaComboBox().setSelectedItem(usuariConnectat.getProvincia());
		formulariAltaUsuari.getDniNieField().setText(usuariConnectat.getDniNie());
		formulariAltaUsuari.getTipusUsuariComboBox().setSelectedItem(usuariConnectat.getTipusUsuari());
		formulariAltaUsuari.getContrasenyaLabel().setText("Nova contrasenya");
		formulariAltaUsuari.getContrasenyaField().setText(usuariConnectat.getContrasenya());
		formulariAltaUsuari.getRepetirContrasenyaField().setText(usuariConnectat.getContrasenya());
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void setUsuariConnectat(Usuari usuariConnectat) {
		this.usuariConnectat = usuariConnectat;
	}

}
