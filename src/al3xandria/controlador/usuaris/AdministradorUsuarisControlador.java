package al3xandria.controlador.usuaris;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTable;

import al3xandria.model.ControlDeDades;
import al3xandria.model.llibres.LlibresModel;
import al3xandria.model.objects.Usuari;
import al3xandria.model.usuaris.UsuarisModel;
import al3xandria.strings.WarningStrings;
import al3xandria.vista.centralPanel.AdministradorUsuaris;

public class AdministradorUsuarisControlador implements MouseListener{
	
	private AdministradorUsuaris administradorUsuaris;
	private JTable table;
	private UsuarisModel usuarisModel;
	private Usuari usuariConnectat;
	private ControlDeDades controlDeDades;
	
	 public AdministradorUsuarisControlador(AdministradorUsuaris administradorUsuaris, Usuari usuariConnectat){
		 this.administradorUsuaris = administradorUsuaris;
		 this.table = administradorUsuaris.getUsuarisTable();
		 this.usuariConnectat = usuariConnectat;
	 }

	@Override
	public void mouseClicked(MouseEvent e) {
		if (administradorUsuaris.getCercarButton() == e.getSource()) {
			ferLaCerca();
		}

		if (administradorUsuaris.getMostrarButton() == e.getSource() && getAccio().equals("default")) {
				setDadesUsuarisPerMostrar();
				desactivarDadesUsuaris();
				mostrarDadesUsuaris();
		}

		if (administradorUsuaris.getEsborrarLabel() == e.getSource()) {
			esborrarCampCerca();
		}

		if (administradorUsuaris.getLupaLabel() == e.getSource()) {
			ferLaCerca();
		}

		if (administradorUsuaris.getSeguentIconLabel() == e.getSource()) {
			pasarUsuariSeguent();
		}

		if (administradorUsuaris.getAnteriorIconLabel() == e.getSource()) {
			pasarUsuariAnterior();
		}

		if (administradorUsuaris.getAltaUsuariButton() == e.getSource() && getAccio().equals("default")) {
				setAccio("alta");
				setDadesUsuarisPerAlta();
		}

		if (administradorUsuaris.getBaixaUsuariButton() == e.getSource() && getAccio().equals("default")) {
			if(comprovarSiUsuariSeleccionat()) {
				setAccio("baixa");
				avisConfirmarBaixaUsuari();
			}
			
		}

		if (administradorUsuaris.getEditarUsuariButton() == e.getSource() && getAccio().equals("default")) {
			if (comprovarSiUsuariSeleccionat()) {
				setAccio("editar");
				setDadesUsuarisPerEditar();
			}

		}
		

		if (administradorUsuaris.getConfirmarButton() == e.getSource()) {
			if (getAccio().equals("alta")) {
				avisConfirmarAltaUsuari();
				
			}
			
			if (getAccio().equals("editar")) {
				avisConfirmarUpdateUsuari();
			}
		}

		if (administradorUsuaris.getCancellarButton() == e.getSource()) {
			avisCancellacioAccio();
		}

	
		
	}

	
	private void avisConfirmarUpdateUsuari() {
		if(comprovacioDadesAltaUsuari()) {
			String dadesUsuariModificat = dadesUsuariModificat();
			int valor = JOptionPane.showConfirmDialog(administradorUsuaris,dadesUsuariModificat + "\n\n" + "Vols modificar l'usuari?",
					"Dades l'usuari modificat",
					JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null);
			if (valor == JOptionPane.YES_OPTION) {
				mostraDadesUsuariModificat(dadesUsuariModificat,dadesUsuariModificatToString());
			}
		}
		
		
	}

	private void mostraDadesUsuariModificat(String dadesUsuariModificat, Object dadesUsuariModificatToString) {
		String idUsuari = getIdUsuari();
		JOptionPane.showMessageDialog(administradorUsuaris,"Has modificat un usuari amb aquestes dades: \n" + dadesUsuariModificat
				+ "\nDades enviades al servidor: " + usuariConnectat.getIdSessio() + ",modificar_usuari," + idUsuari + "," + dadesUsuariModificatToString, 
				"Dades usuari modificat", JOptionPane.INFORMATION_MESSAGE);
		setPanelPerDefecte();
		
	}

	private Object dadesUsuariModificatToString() {
		String dadesUsuariModificat =
				administradorUsuaris.getNomField().getText() + "," +
				administradorUsuaris.getCognomsField().getText() + "," +
				administradorUsuaris.getDniNieField().getText() + "," +
				administradorUsuaris.getEmailField().getText() + "," +
				administradorUsuaris.getContrasenyaToString() + "," +
				administradorUsuaris.getCarnetField().getText() + "," +
				administradorUsuaris.getAdrecaField().getText() + "," +
				administradorUsuaris.getCodiPostalField().getText() + "," +
				administradorUsuaris.getPoblacioField().getText() + "," +
				administradorUsuaris.getProvinciaComboBox().getSelectedItem() + "," +
				administradorUsuaris.getPaisField().getText() + "," +
				administradorUsuaris.getTelefonField().getText() + "," +
				administradorUsuaris.getTipusUsuariComboBox().getSelectedItem() + "," +
				administradorUsuaris.getPuntuacioField().getText() + "," +
				administradorUsuaris.getActiuCheckBox().isSelected();
return dadesUsuariModificat;
	}

	private String dadesUsuariModificat() {
		String dadesUsuariModificat =
				"id: " + administradorUsuaris.getIdUsuariField().getText() + "\n " +
						"nom: " + administradorUsuaris.getNomField().getText() + "\n " +
						"cognoms: " + administradorUsuaris.getCognomsField().getText() + "\n " +
						"dni/nie: " + administradorUsuaris.getDniNieField().getText() + "\n " +
						"email: " + administradorUsuaris.getEmailField().getText() + "\n " +
						"contrasenya: " + administradorUsuaris.getContrasenyaToString() + "\n " +
						"carnet: " + administradorUsuaris.getCarnetField().getText() + "\n " +
						"adreça: " + administradorUsuaris.getAdrecaField().getText() + "\n " +
						"cp: " + administradorUsuaris.getCodiPostalField().getText() + "\n " +
						"població: " + administradorUsuaris.getPoblacioField().getText() + "\n " +
						"provincia: " + administradorUsuaris.getProvinciaComboBox().getSelectedItem() + "\n" +
						"pais: " + administradorUsuaris.getPaisField().getText() + "\n " +
						"telèfon: " + administradorUsuaris.getTelefonField().getText() + "\n " +
						"tipus usuari: " + administradorUsuaris.getTipusUsuariComboBox().getSelectedItem() + "\n" +
						"puntuació: " + administradorUsuaris.getPuntuacioField().getText() + "\n " +
						"actiu?: " + administradorUsuaris.getActiuCheckBox().isSelected() + "\n";
		return dadesUsuariModificat;
	}

	private void avisConfirmarAltaUsuari() {
		
		if(comprovacioDadesAltaUsuari()) {
			String dadesUsuariAlta = dadesUsuariAlta();
			int valor = JOptionPane.showConfirmDialog(administradorUsuaris,dadesUsuariAlta + "\n\n" + "Vols afegir l'usuari?",
					"Dades de l'usuari a afegir",
					JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null);
			if (valor == JOptionPane.YES_OPTION) {
				mostraDadesUsuariAlta(dadesUsuariAlta, dadesUsuariAltaToString());
			}
		}
		
	}

	private boolean comprovacioDadesAltaUsuari() {
		controlDeDades = new ControlDeDades();
		boolean dadesCorrectes = false;
		if (controlDeDades.comprovarCampsOmplertsAltaUsuari(administradorUsuaris.getNomField().getText(),
				administradorUsuaris.getCognomsField().getText(), administradorUsuaris.getAdrecaField().getText(),
				administradorUsuaris.getEmailField().getText(), administradorUsuaris.getPoblacioField().getText(),
				administradorUsuaris.getCodiPostalField().getText(), administradorUsuaris.getPaisField().getText(),
				administradorUsuaris.getProvinciaComboBox().getSelectedIndex(),
				administradorUsuaris.getTelefonField().getText(), administradorUsuaris.getContrasenyaToString(),
				administradorUsuaris.getDniNieField().getText(), administradorUsuaris.getCarnetField().getText(),
				administradorUsuaris.getTipusUsuariComboBox().getSelectedIndex())) {
			if (controlDeDades.comprovacioEmail(administradorUsuaris.getEmailField().getText())) {

				if (controlDeDades.comprovacioFormatTelefon(administradorUsuaris.getTelefonField().getText())) {

					dadesCorrectes = true;
				}else {
					controlDeDades.errorFormatTelefon();
				}
			}else {
				controlDeDades.errorEnElFormatDelEmailIntroduit();
			}
		}

		return dadesCorrectes;
	}

	private void mostraDadesUsuariAlta(String dadesUsuariAlta, Object dadesUsuariAltaToString) {
		String idUsuari = getIdUsuari();
		JOptionPane.showMessageDialog(administradorUsuaris,"Has afegit un usuari amb aquestes dades: \n" + dadesUsuariAlta
				+ "\nDades enviades al servidor: " + usuariConnectat.getIdSessio() + ",insercio_usuari," + dadesUsuariAltaToString, 
				"Dades usuari afegit", JOptionPane.INFORMATION_MESSAGE);
		setPanelPerDefecte();
		
	}

	private Object dadesUsuariAltaToString() {
		String dadesUsuariAlta =
						administradorUsuaris.getNomField().getText() + "," +
						administradorUsuaris.getCognomsField().getText() + "," +
						administradorUsuaris.getDniNieField().getText() + "," +
						administradorUsuaris.getEmailField().getText() + "," +
						administradorUsuaris.getContrasenyaToString() + "," +
						administradorUsuaris.getCarnetField().getText() + "," +
						administradorUsuaris.getAdrecaField().getText() + "," +
						administradorUsuaris.getCodiPostalField().getText() + "," +
						administradorUsuaris.getPoblacioField().getText() + "," +
						administradorUsuaris.getProvinciaComboBox().getSelectedItem() + "," +
						administradorUsuaris.getPaisField().getText() + "," +
						administradorUsuaris.getTelefonField().getText() + "," +
						administradorUsuaris.getTipusUsuariComboBox().getSelectedItem() + "," +
						administradorUsuaris.getPuntuacioField().getText() + "," +
						administradorUsuaris.getActiuCheckBox().isSelected();
		return dadesUsuariAlta;
	}

	private String dadesUsuariAlta() {
		String dadesUsuariAlta =
						"nom: " + administradorUsuaris.getNomField().getText() + "\n " +
						"cognoms: " + administradorUsuaris.getCognomsField().getText() + "\n " +
						"dni/nie: " + administradorUsuaris.getDniNieField().getText() + "\n " +
						"email: " + administradorUsuaris.getEmailField().getText() + "\n " +
						"contrasenya: " + administradorUsuaris.getContrasenyaToString() + "\n " +
						"carnet: " + administradorUsuaris.getCarnetField().getText() + "\n " +
						"adreça: " + administradorUsuaris.getAdrecaField().getText() + "\n " +
						"cp: " + administradorUsuaris.getCodiPostalField().getText() + "\n " +
						"població: " + administradorUsuaris.getPoblacioField().getText() + "\n " +
						"provincia: " + administradorUsuaris.getProvinciaComboBox().getSelectedItem() + "\n" +
						"pais: " + administradorUsuaris.getPaisField().getText() + "\n " +
						"telèfon: " + administradorUsuaris.getTelefonField().getText() + "\n " +
						"tipus usuari: " + administradorUsuaris.getTipusUsuariComboBox().getSelectedItem() + "\n" +
						"puntuació: " + administradorUsuaris.getPuntuacioField().getText() + "\n " +
						"actiu?: " + administradorUsuaris.getActiuCheckBox().isSelected() + "\n";
		return dadesUsuariAlta;
	}

	private void avisConfirmarBaixaUsuari() {
		setAccio("default");
		String dadesUsuariAEliminar = getDadesRowLUsuari(administradorUsuaris.getRowActiu());
		int valor = JOptionPane.showConfirmDialog(administradorUsuaris,dadesUsuariAEliminar + "\n\n" + "Vols eliminar l'usuari?",
				"Dades del usuari a eliminar",
				JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null);
		if (valor == JOptionPane.YES_OPTION) {
			
			mostraDadesUsuariAEliminar(dadesUsuariAEliminar, getDadesRowToString(administradorUsuaris.getRowActiu()));
		}
	}

	private void mostraDadesUsuariAEliminar(String dadesUsuariAEliminar, String dadesRowToString) {
		String idUsuari = getIdUsuari();
		JOptionPane.showMessageDialog(administradorUsuaris,"Has esborrat un usuari amb aquestes dades: \n" + dadesUsuariAEliminar
				+ "\nDades enviades al servidor: " + usuariConnectat.getIdSessio() + ",eliminar_usuari," + idUsuari + "," + dadesRowToString,  
				"Dades usuari eliminat", JOptionPane.INFORMATION_MESSAGE);
		setPanelPerDefecte();
		
	}

	private String getIdUsuari() {
		return administradorUsuaris.getIdUsuariField().getText();
	}

	private String getDadesRowToString(int row) {
		String dadesRowToString =
				getValorCella(row, 0) + "," +
						getValorCella(row, 1) + "," +
						getValorCella(row, 2) + "," +
						getValorCella(row, 3) + "," +
						getValorCella(row, 4) + "," +
						getValorCella(row, 5) + "," +
						getValorCella(row, 6) + "," +
						getValorCella(row, 7) + "," +
						getValorCella(row, 8) + "," +
						getValorCella(row, 9) + "," +
						getValorCella(row, 10) + "," +
						getValorCella(row, 11) + "," +
						getValorCella(row, 12) + "," +
						getValorCella(row, 13) + "," +
						getValorCella(row, 14) + "," +
						getValorCella(row, 15);
		return dadesRowToString;
	}

	private String getDadesRowLUsuari(int row) {
		String dadesRowToString =
				"id: " + getValorCella(row, 0) + "\n" +
						"nom: " + getValorCella(row, 1) + "\n" +
						"cognoms: " + getValorCella(row, 2) + "\n" +
						"dni/nie: " + getValorCella(row, 3) + "\n" +
						"email: " + getValorCella(row, 4) + "\n" +
						"contrasenya: " + getValorCella(row, 5) + "\n" +
						"adreça: " + getValorCella(row, 6) + "\n" +
						"cp: " + getValorCella(row, 7) + "\n" +
						"població: " + getValorCella(row, 8) + "\n" +
						"provincia: " + getValorCella(row, 9) + "\n" +
						"pais: " + getValorCella(row, 10) + "\n" +
						"telèfon: " + getValorCella(row, 11) + "\n" +
						"carnet: " + getValorCella(row, 12) + "\n" +
						"tipus: " + getValorCella(row, 13) + "\n" +
						"puntuació: " + getValorCella(row, 14) + "\n" +
						"actiu?: " + getValorCella(row, 15);
		return dadesRowToString;
	}

	private void setDadesUsuarisPerEditar() {
		administradorUsuaris.omplirCombobox();
		administradorUsuaris.getAltaUsuariButton().setEnabled(false);
		administradorUsuaris.getMostrarButton().setEnabled(false);
		administradorUsuaris.getBaixaUsuariButton().setEnabled(false);
		administradorUsuaris.getConfirmarButton().setVisible(true);
		administradorUsuaris.getCancellarButton().setVisible(true);
		administradorUsuaris.getPaginadorPanel().setVisible(false);
		administradorUsuaris.getActiuCheckBox().setEnabled(true);
		mostrarDadesUsuaris();
		activarDadesUsuaris();
		
	}

	private boolean comprovarSiUsuariSeleccionat() {
		int rowAMostrar = administradorUsuaris.getUsuarisTable().getSelectedRow();
		if (rowAMostrar == -1) {
			errorUsuariNoSeleccionat();
			return false;
		} else {
			return true;
		}
	}

	private void setDadesUsuarisPerAlta() {
		administradorUsuaris.omplirCombobox();
		administradorUsuaris.getEditarUsuariButton().setEnabled(false);
		administradorUsuaris.getMostrarButton().setEnabled(false);
		administradorUsuaris.getBaixaUsuariButton().setEnabled(false);
		administradorUsuaris.getConfirmarButton().setVisible(true);
		administradorUsuaris.getCancellarButton().setVisible(true);
		administradorUsuaris.getPaginadorPanel().setVisible(false);
		esborrarDadesUsuaris();
		activarDadesUsuaris();
		
	}

	private void activarDadesUsuaris() {
		administradorUsuaris.getIdUsuariField().setEditable(false);
		administradorUsuaris.getNomField().setEditable(true);
		administradorUsuaris.getCognomsField().setEditable(true);
		administradorUsuaris.getDniNieField().setEditable(true);
		administradorUsuaris.getEmailField().setEditable(true);
		administradorUsuaris.getContrasenyaField().setEditable(true);
		administradorUsuaris.getCarnetField().setEditable(true);
		administradorUsuaris.getAdrecaField().setEditable(true);
		administradorUsuaris.getCodiPostalField().setEditable(true);
		administradorUsuaris.getPoblacioField().setEditable(true);
		administradorUsuaris.getProvinciaComboBox().setEnabled(true);
		administradorUsuaris.getPaisField().setEditable(true);
		administradorUsuaris.getTelefonField().setEditable(true);
		administradorUsuaris.getTipusUsuariComboBox().setEnabled(true);
		administradorUsuaris.getPuntuacioField().setEditable(true);
		administradorUsuaris.getActiuCheckBox().setEnabled(true);
		
	}

	private void esborrarDadesUsuaris() {
		administradorUsuaris.getIdUsuariField().setText("");
		administradorUsuaris.getNomField().setText("");
		administradorUsuaris.getCognomsField().setText("");
		administradorUsuaris.getDniNieField().setText("");
		administradorUsuaris.getEmailField().setText("");
		administradorUsuaris.getContrasenyaField().setText("");
		administradorUsuaris.getCarnetField().setText("");
		administradorUsuaris.getAdrecaField().setText("");
		administradorUsuaris.getCodiPostalField().setText("");
		administradorUsuaris.getPoblacioField().setText("");
		administradorUsuaris.getProvinciaComboBox().setSelectedIndex(0);
		administradorUsuaris.getPaisField().setText("");
		administradorUsuaris.getTelefonField().setText("");
		administradorUsuaris.getTipusUsuariComboBox().setSelectedIndex(0);
		administradorUsuaris.getPuntuacioField().setText("");
		administradorUsuaris.getActiuCheckBox().setSelected(false);
		
	}

	private void pasarUsuariSeguent() {
		if (administradorUsuaris.getRowActiu() + 1 == table.getRowCount()) {
			administradorUsuaris.setRowActiu(table.getRowCount() - 1);
		} else {
			administradorUsuaris.seguentRowActiu(1, table.getRowCount());
			getDadesRow(administradorUsuaris.getRowActiu());

		}

	}
	
	public void avisCancellacioAccio() {
		int valor = JOptionPane.showConfirmDialog(administradorUsuaris,
				"Vols cancel.lar l'acció "+getAccio()+"? Perdrás tota la informació", 
				"Cancel.lar "+ getAccio(), JOptionPane.YES_NO_OPTION,
				JOptionPane.WARNING_MESSAGE, null);
		if (valor == JOptionPane.YES_OPTION) {
			setPanelPerDefecte();
		}

	}

	private void setPanelPerDefecte() {
		setAccio("default");
		esborrarDadesUsuaris();
		desactivarDadesUsuaris();
		administradorUsuaris.getAltaUsuariButton().setEnabled(true);
		administradorUsuaris.getMostrarButton().setEnabled(true);
		administradorUsuaris.getBaixaUsuariButton().setEnabled(true);
		administradorUsuaris.getEditarUsuariButton().setEnabled(true);
		administradorUsuaris.getConfirmarButton().setVisible(false);
		administradorUsuaris.getCancellarButton().setVisible(false);
		administradorUsuaris.getPaginadorPanel().setVisible(false);
		administradorUsuaris.getActiuCheckBox().setEnabled(false);
		
	}

	private void pasarUsuariAnterior() {
		administradorUsuaris.anteriorRowActiu(1);
		if (administradorUsuaris.getRowActiu() >= 0) {
			getDadesRow(administradorUsuaris.getRowActiu());
		} else {
			administradorUsuaris.setRowActiu(0);
		}
	}
	private void esborrarCampCerca() {
		administradorUsuaris.getCercaField().setText("");
		administradorUsuaris.getCercaField().requestFocus();

	}

	private void mostrarDadesUsuaris() {
		int rowAMostrar = administradorUsuaris.getUsuarisTable().getSelectedRow();
		if (rowAMostrar > -1) {
			administradorUsuaris.setRowActiu(administradorUsuaris.getUsuarisTable().getSelectedRow());
			getDadesRow(rowAMostrar);
		} else {
			errorUsuariNoSeleccionat();
		}
		
	}

	private void getDadesRow(int row) {
		administradorUsuaris.getRowActualField().setText(String.valueOf(administradorUsuaris.getRowActiu() + 1));
		administradorUsuaris.getRowTotalsField().setText(String.valueOf(table.getRowCount()));
		administradorUsuaris.getUsuarisTable().setRowSelectionInterval(row, row);
		administradorUsuaris.getIdUsuariField().setText(getValorCella(row, 0));
		administradorUsuaris.getNomField().setText(getValorCella(row, 1));
		administradorUsuaris.getCognomsField().setText(getValorCella(row, 2));
		administradorUsuaris.getDniNieField().setText(getValorCella(row, 3));
		administradorUsuaris.getEmailField().setText(getValorCella(row, 4));
		administradorUsuaris.getContrasenyaField().setText(getValorCella(row, 5));

		administradorUsuaris.getAdrecaField().setText(getValorCella(row, 6));

		administradorUsuaris.getCodiPostalField().setText(getValorCella(row, 7));
		administradorUsuaris.getPoblacioField().setText(getValorCella(row, 8));
		administradorUsuaris.getProvinciaComboBox().setSelectedItem(getValorCella(row, 9));
		administradorUsuaris.getPaisField().setText(getValorCella(row, 10));
		administradorUsuaris.getTelefonField().setText(getValorCella(row, 11));
		administradorUsuaris.getCarnetField().setText(getValorCella(row, 12));
		administradorUsuaris.getTipusUsuariComboBox().setSelectedItem(getValorCella(row, 13));
		administradorUsuaris.getPuntuacioField().setText(getValorCella(row, 14));
		if (getValorCella(row, 15).equals("true")) {
			administradorUsuaris.getActiuCheckBox().setSelected(true);
		} else {
			administradorUsuaris.getActiuCheckBox().setSelected(false);
		}
		
	}
	
	private String getValorCella(int row, int column) {
		return table.getValueAt(row, column).toString();
	}

	private void errorUsuariNoSeleccionat() {
		JOptionPane.showMessageDialog(administradorUsuaris, "No has seleccionat cap usuari",
				WarningStrings.getString("Error selecció usuari"), JOptionPane.ERROR_MESSAGE);
		
	}

	private void desactivarDadesUsuaris() {
		administradorUsuaris.getIdUsuariField().setEditable(false);
		administradorUsuaris.getNomField().setEditable(false);
		administradorUsuaris.getCognomsField().setEditable(false);
		administradorUsuaris.getDniNieField().setEditable(false);
		administradorUsuaris.getEmailField().setEditable(false);
		administradorUsuaris.getContrasenyaField().setEditable(false);
		administradorUsuaris.getCarnetField().setEditable(false);
		administradorUsuaris.getAdrecaField().setEditable(false);
		administradorUsuaris.getCodiPostalField().setEditable(false);
		administradorUsuaris.getPoblacioField().setEditable(false);
		administradorUsuaris.getProvinciaComboBox().setEnabled(false);
		administradorUsuaris.getPaisField().setEditable(false);
		administradorUsuaris.getTelefonField().setEditable(false);
		administradorUsuaris.getTipusUsuariComboBox().setEnabled(false);
		administradorUsuaris.getPuntuacioField().setEditable(false);
		administradorUsuaris.getActiuCheckBox().setEnabled(false);
		
	}

	private void setDadesUsuarisPerMostrar() {
		administradorUsuaris.getPaginadorPanel().setVisible(true);
		administradorUsuaris.getConfirmarButton().setVisible(false);
		administradorUsuaris.getCancellarButton().setVisible(false);
		administradorUsuaris.omplirCombobox();
	}

	
	private void ferLaCerca() {
		String textDeLaCerca = administradorUsuaris.getCercaField().getText();
		String filtre = getFiltre();
		String tipusUsuariString = administradorUsuaris.getFiltreComboBox().getSelectedItem().toString().toLowerCase();
		if(administradorUsuaris.getFiltreComboBox().getSelectedIndex() == 0) {
			tipusUsuariString = "tots";
		}
		
		if (textDeLaCerca.length() == 0) {
			errorCampCercaBuit();
		} else {
			//usuarisModel = new UsuarisModel();
			//usuarisModel.consultarTotsElsUsuarisPerFiltre(filtre + "," + tipusUsuariString + "," + textDeLaCerca );
			
			//S'envia l'string de cerca al servidor
			mostraLaCerca("Filtre: " + filtre + "\n" + "Tipus usuari: " + tipusUsuariString + "\n" + 
			"Text de la cerca: " + textDeLaCerca + "\n" + 
			"Valors enviats al servidor: " + usuariConnectat.getIdSessio() + ",consulta_usuari_" + filtre + "," + 
			tipusUsuariString + "," + textDeLaCerca);
		}

	}
	
	private void mostraLaCerca(String cerca) {
		JOptionPane.showMessageDialog(administradorUsuaris,cerca, "Dades de la cerca", JOptionPane.INFORMATION_MESSAGE);
	}

	private void errorCampCercaBuit() {
		JOptionPane.showMessageDialog(administradorUsuaris,
				WarningStrings.getString("ConsultaLlibresNoRegistrat.missatgeCampCercaBuit"),
				WarningStrings.getString("ConsultaLlibresNoRegistrat.titolMissatgeCampCercaBuit"),
				JOptionPane.ERROR_MESSAGE);

	}

	private String getFiltre() {
		String filtre = null;
		ButtonGroup grup = administradorUsuaris.getFiltreButtonGroup();
		if (grup.getSelection().equals(administradorUsuaris.getNomRadioButton().getModel())) {
			filtre = "nom";
		}
		if (grup.getSelection().equals(administradorUsuaris.getCarnetRadioButton().getModel())) {
			filtre = "carnet";
		}
		if (grup.getSelection().equals(administradorUsuaris.getInactiusRadioButton().getModel())) {
			filtre = "inactiu";
		}
		
		return filtre;
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
		if(administradorUsuaris.getMostrarContrasenyaLabel() == e.getSource()) {
			administradorUsuaris.getContrasenyaField().setEchoChar((char) 0);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		if(administradorUsuaris.getMostrarContrasenyaLabel() == e.getSource()) {
			administradorUsuaris.getContrasenyaField().setEchoChar('*');
		}
	}
	
	public String getAccio() {
		return administradorUsuaris.getAccio();
		
	}

	public void setAccio(String accio) {
		administradorUsuaris.setAccio(accio);
	}
	


}
