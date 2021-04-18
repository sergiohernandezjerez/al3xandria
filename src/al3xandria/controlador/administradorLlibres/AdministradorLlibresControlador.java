package al3xandria.controlador.administradorLlibres;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import al3xandria.model.ControlDeDades;
import al3xandria.model.llibres.LlibresModel;
import al3xandria.model.objects.Usuari;
import al3xandria.strings.WarningStrings;
import al3xandria.vista.centralPanel.AdministradorLlibres;
import al3xandria.vista.centralPanel.CentralPanelMessages;

public class AdministradorLlibresControlador implements MouseListener {

	private AdministradorLlibres administradorLlibres;
	private JTable table;
	private LlibresModel llibresModel;
	private Usuari usuariConnectat;
	private ControlDeDades controlDeDades;

	public AdministradorLlibresControlador(AdministradorLlibres administradorLlibres, Usuari usuariConnectat) {
		this.administradorLlibres = administradorLlibres;
		this.table = administradorLlibres.getLlibresTable();
		this.usuariConnectat = usuariConnectat;
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {

		if (administradorLlibres.getCercarButton() == e.getSource()) {
			ferLaCerca();
		}

		if (administradorLlibres.getMostrarLlibreButton() == e.getSource() && getAccio().equals("default")) {
				setDadesLlibresPerMostrar();
				desactivarDadesLlibres();
				mostrarDadesLlibres();
			
			
		}

		if (administradorLlibres.getEsborrarLabel() == e.getSource()) {
			esborrarCampCerca();
		}

		if (administradorLlibres.getLupaLabel() == e.getSource()) {
			ferLaCerca();
		}

		if (administradorLlibres.getSeguentIconLabel() == e.getSource()) {
			pasarLlibreSeguent();
		}

		if (administradorLlibres.getAnteriorIconLabel() == e.getSource()) {
			pasarLlibreAnterior();
		}

		if (administradorLlibres.getAltaLlibreButton() == e.getSource() && getAccio().equals("default")) {
				setAccio("alta");
				setDadesLlibresPerAlta();
		}

		if (administradorLlibres.getBaixaLlibreButton() == e.getSource() && getAccio().equals("default")) {
			if(comprovarSiLlibreSeleccionat()) {
				setAccio("baixa");
				avisConfirmarBaixaLlibre();
			}
			
		}

		if (administradorLlibres.getEditarLlibreButton() == e.getSource() && getAccio().equals("default")) {
			if (comprovarSiLlibreSeleccionat()) {
				setAccio("editar");
				setDadesLlibresPerEditar();
			}

		}

		if (administradorLlibres.getConfirmarButton() == e.getSource()) {
			if (getAccio().equals("alta")) {
				avisConfirmarAltaLlibre();
				
			}
			
			if (getAccio().equals("editar")) {
				avisConfirmarUpdateLlibre();
			}
		}

		if (administradorLlibres.getCancellarButton() == e.getSource()) {
			avisCancellacioAccio();
		}

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



	private void avisConfirmarUpdateLlibre() {
		if(comprovacioDadesAltaLlibre()) {
			String dadesLlibreModificat = dadesLlibreModificat();
			int valor = JOptionPane.showConfirmDialog(administradorLlibres,dadesLlibreModificat + "\n\n" + "Vols modificar el llibre?",
					"Dades del llibre modificat",
					JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null);
			if (valor == JOptionPane.YES_OPTION) {
				mostraDadesLlibreModificat(dadesLlibreModificat,dadesLlibreModificatToString());
			}
		}
		
		
	}
	
	private String getIdLlibre() {
		return administradorLlibres.getIdLlibreField().getText();
		
	}

	private String dadesLlibreModificat() {
		String dadesLlibreModificat = 
				"id: " + administradorLlibres.getIdLlibreField().getText() + "\n" +
						"títol: " + administradorLlibres.getTitolField().getText() + "\n" +
						"reservat?: " + administradorLlibres.getReservatCheckBox().isSelected() + "\n" +
						"autor: " + administradorLlibres.getAutorsComboBox().getSelectedItem() + "\n" +
						"gènere: " + administradorLlibres.getGeneresComboBox().getSelectedItem() + "\n" +
						"editorial: " + administradorLlibres.getEditorialsComboBox().getSelectedItem() + ",\n" +
						"puntuació: " + administradorLlibres.getPuntuacioField().getText() + ",\n" +
						"isbn: " + administradorLlibres.getIsbnField().getText() + ",\n" +
						"edició: " + administradorLlibres.getEdicioField().getText() + ",\n" +
						"data publicació: " + administradorLlibres.getDataPublicacioField().getText() + ",\n" +
						"núm. pàgines: " + administradorLlibres.getNumeroPaginesField().getText() + ",\n" +
						"núm. reserves: " + administradorLlibres.getNumeroReservesField().getText() + ",\n" +
						"sinopsis: " + administradorLlibres.getSinopsisTextArea().getText() + "...\n";
			
		return dadesLlibreModificat;
	}
	
	private String dadesLlibreAlta() {
		String dadesLlibreAlta = 
				"titol: " + administradorLlibres.getTitolField().getText() + "\n" +
						"reservat?: " + administradorLlibres.getReservatCheckBox().isSelected() + "\n" +
						"autor: " + administradorLlibres.getAutorsComboBox().getSelectedItem() + "\n" +
						"gènere: " + administradorLlibres.getGeneresComboBox().getSelectedItem() + "\n" +
						"editorial: " + administradorLlibres.getEditorialsComboBox().getSelectedItem() + ",\n" +
						"puntuació: " + administradorLlibres.getPuntuacioField().getText() + ",\n" +
						"isbn: " + administradorLlibres.getIsbnField().getText() + ",\n" +
						"edició: " + administradorLlibres.getEdicioField().getText() + ",\n" +
						"data publicació: " + administradorLlibres.getDataPublicacioField().getText() + ",\n" +
						"núm. pàgines: " + administradorLlibres.getNumeroPaginesField().getText() + ",\n" +
						"núm reserves: " + administradorLlibres.getNumeroReservesField().getText() + ",\n" +
						"sinopsis: " + administradorLlibres.getSinopsisTextArea().getText() + "...\n";
			
		return dadesLlibreAlta;
	}
	
	private String dadesLlibreModificatToString() {
		String dadesLlibreModificat = 
				administradorLlibres.getTitolField().getText() + "," +
				administradorLlibres.getReservatCheckBox().isSelected() + "," +
				administradorLlibres.getAutorsComboBox().getSelectedItem() + "," +
				administradorLlibres.getGeneresComboBox().getSelectedItem() + "," +
				administradorLlibres.getEditorialsComboBox().getSelectedItem() + "," +
				administradorLlibres.getPuntuacioField().getText() + "," +
				administradorLlibres.getIsbnField().getText() + "," +
				administradorLlibres.getEdicioField().getText() + "," +
				administradorLlibres.getDataPublicacioField().getText() + "," +
				administradorLlibres.getNumeroPaginesField().getText() + "," +
				administradorLlibres.getNumeroReservesField().getText() + "," +
				administradorLlibres.getSinopsisTextArea().getText();
			
		return dadesLlibreModificat;
	}
	
	private String dadesLlibreAltaToString() {
		String dadesLlibreAlta = 
				administradorLlibres.getTitolField().getText() + "," +
				administradorLlibres.getReservatCheckBox().isSelected() + "," +
				administradorLlibres.getAutorsComboBox().getSelectedItem() + "," +
				administradorLlibres.getGeneresComboBox().getSelectedItem() + "," +
				administradorLlibres.getEditorialsComboBox().getSelectedItem() + "," +
				administradorLlibres.getPuntuacioField().getText() + "," +
				administradorLlibres.getIsbnField().getText() + "," +
				administradorLlibres.getEdicioField().getText() + "," +
				administradorLlibres.getDataPublicacioField().getText() + "," +
				administradorLlibres.getNumeroPaginesField().getText() + "," +
				administradorLlibres.getNumeroReservesField().getText() + "," +
				administradorLlibres.getSinopsisTextArea().getText();
			
		return dadesLlibreAlta;
	}

	private void mostraDadesLlibreModificat(String dadesLlibres, String dadesLlibreToString) {
		String idLlibre = getIdLlibre();
		JOptionPane.showMessageDialog(administradorLlibres,"Has modificat el llibre amb aquestes dades: \n" + dadesLlibres
				+ "\nDades enviades al servidor: " + usuariConnectat.getIdSessio() + ",modificar_llibre," + idLlibre + "," + dadesLlibreToString, 
				"Dades llibre modificat", JOptionPane.INFORMATION_MESSAGE);
		setPanelPerDefecte();
	}

	private void avisConfirmarBaixaLlibre() {
		setAccio("default");
		String dadesLlibreAEliminar = getDadesRowLLibre(administradorLlibres.getRowActiu());
		int valor = JOptionPane.showConfirmDialog(administradorLlibres,dadesLlibreAEliminar + "\n\n" + "Vols eliminar el llibre?",
				"Dades del llibre a eliminar",
				JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null);
		if (valor == JOptionPane.YES_OPTION) {
			
			mostraDadesLlibreAEliminar(dadesLlibreAEliminar, getDadesRowToString(administradorLlibres.getRowActiu()));
		}
		
	}
	
	private void mostraDadesLlibreAEliminar(String dadesLlibre, String dadesLlibreToString) {
		String idLlibre = getIdLlibre();
		JOptionPane.showMessageDialog(administradorLlibres,"Has esborrat el llibre amb aquestes dades: \n" + dadesLlibre
				+ "\nDades enviades al servidor: " + usuariConnectat.getIdSessio() + ",eliminar_llibre," + idLlibre + "," + dadesLlibreToString,  
				"Dades llibre eliminat", JOptionPane.INFORMATION_MESSAGE);
		setPanelPerDefecte();
	}

	private void avisConfirmarAltaLlibre() {
		
		if(comprovacioDadesAltaLlibre()) {
			String dadesLLibresAlta = dadesLlibreAlta();
			int valor = JOptionPane.showConfirmDialog(administradorLlibres,dadesLLibresAlta + "\n\n" + "Vols afegir el llibre?",
					"Dades del llibre a afegir",
					JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null);
			if (valor == JOptionPane.YES_OPTION) {
				mostraDadesLlibreAlta(dadesLLibresAlta, dadesLlibreAltaToString());
			}
		}
		
		
	}
	
	private boolean comprovacioDadesAltaLlibre() {
		controlDeDades = new ControlDeDades();
		boolean dadesCorrectes = false;
		if(controlDeDades.comprovarCampsOmplertsAltaLlibre(
				administradorLlibres.getTitolField().getText(), 
				administradorLlibres.getAutorsComboBox().getSelectedIndex(), 
				administradorLlibres.getGeneresComboBox().getSelectedIndex(), 
				administradorLlibres.getEditorialsComboBox().getSelectedIndex(), 
				administradorLlibres.getIsbnField().getText(), 
				administradorLlibres.getEdicioField().getText(), 
				administradorLlibres.getDataPublicacioField().getText(), 
				administradorLlibres.getNumeroPaginesField().getText())) {
			if(controlDeDades.comprovacioFormatIsbn(administradorLlibres.getIsbnField().getText())) {
				dadesCorrectes = true;
			}else {
				controlDeDades.errorFormatIsbn();
			}
		}
		return dadesCorrectes;
	}

	private void mostraDadesLlibreAlta(String dadesLlibre, String dadesLlibresToString) {
		String idLlibre = getIdLlibre();
		JOptionPane.showMessageDialog(administradorLlibres,"Has afegit el llibre amb aquestes dades: \n" + dadesLlibre
				+ "\nDades enviades al servidor: " + usuariConnectat.getIdSessio() + ",insercio_llibre," + dadesLlibresToString, 
				"Dades llibre afegit", JOptionPane.INFORMATION_MESSAGE);
		setPanelPerDefecte();
	}

	private void formulariAfegirNouElement(String nomTaula) {
		String nouRegistre;
		nouRegistre = JOptionPane.showInputDialog(null, "Nom de " + nomTaula + " que vols donar d'alta",
				"Nou " + nomTaula, JOptionPane.WARNING_MESSAGE);
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
		// TODO enviar consulta segons element
		switch (nomTaula) {
		case "Autor":
			mostraLaConsultaSegonsElement("Valors enviats al servidor: " + usuariConnectat.getIdSessio() + ",insertat_autor," + nouRegistre);
			break;
		case "Genere":
			mostraLaConsultaSegonsElement("Valors enviats al servidor: " + usuariConnectat.getIdSessio() + ",insertat_genere," + nouRegistre);
			break;
		case "Editorial":
			mostraLaConsultaSegonsElement("Valors enviats al servidor: " + usuariConnectat.getIdSessio() + ",insertat_editorial," + nouRegistre);
			break;
		default:
			break;
		}
	}
	
	private void mostraLaConsultaSegonsElement(String cerca) {
		JOptionPane.showMessageDialog(administradorLlibres,cerca, "Dades de la consulta", JOptionPane.INFORMATION_MESSAGE);
	}

	public void avisCancellacioAccio() {
		int valor = JOptionPane.showConfirmDialog(administradorLlibres,
				"Vols cancel.lar l'acció "+getAccio()+"? Perdrás tota la informació", 
				"Cancel.lar "+ getAccio(), JOptionPane.YES_NO_OPTION,
				JOptionPane.WARNING_MESSAGE, null);
		if (valor == JOptionPane.YES_OPTION) {
			setPanelPerDefecte();
		}

	}
	
	
	private void pasarLlibreSeguent() {
		if (administradorLlibres.getRowActiu() + 1 == table.getRowCount()) {
			administradorLlibres.setRowActiu(table.getRowCount() - 1);
		} else {
			administradorLlibres.seguentRowActiu(1, table.getRowCount());
			getDadesRow(administradorLlibres.getRowActiu());

		}

	}

	private void pasarLlibreAnterior() {
		administradorLlibres.anteriorRowActiu(1);
		if (administradorLlibres.getRowActiu() >= 0) {
			getDadesRow(administradorLlibres.getRowActiu());
		} else {
			administradorLlibres.setRowActiu(0);
		}
	}

	private void mostrarDadesLlibres() {
		int rowAMostrar = administradorLlibres.getLlibresTable().getSelectedRow();
		if (rowAMostrar > -1) {
			administradorLlibres.setRowActiu(administradorLlibres.getLlibresTable().getSelectedRow());
			getDadesRow(rowAMostrar);
		} else {
			errorLlibreNoSeleccionat();
		}

	}

	private void setPanelPerDefecte() {
		setAccio("default");
		esborrarDadesLlibres();
		desactivarDadesLlibres();
		administradorLlibres.getAltaLlibreButton().setEnabled(true);
		administradorLlibres.getMostrarLlibreButton().setEnabled(true);
		administradorLlibres.getBaixaLlibreButton().setEnabled(true);
		administradorLlibres.getEditarLlibreButton().setEnabled(true);
		administradorLlibres.getConfirmarButton().setVisible(false);
		administradorLlibres.getCancellarButton().setVisible(false);
		administradorLlibres.getPaginadorPanel().setVisible(false);
		administradorLlibres.getReservatCheckBox().setEnabled(false);
	}
	
	private void setDadesLlibresPerMostrar() {
		administradorLlibres.getPaginadorPanel().setVisible(true);
		administradorLlibres.getConfirmarButton().setVisible(false);
		administradorLlibres.getCancellarButton().setVisible(false);
		administradorLlibres.omplirComboBox();
	}

	private void setDadesLlibresPerEditar() {
		administradorLlibres.omplirComboBox();
		administradorLlibres.getAltaLlibreButton().setEnabled(false);
		administradorLlibres.getMostrarLlibreButton().setEnabled(false);
		administradorLlibres.getBaixaLlibreButton().setEnabled(false);
		administradorLlibres.getConfirmarButton().setVisible(true);
		administradorLlibres.getCancellarButton().setVisible(true);
		administradorLlibres.getAfegirAutorLabel().setEnabled(true);
		administradorLlibres.getAfegirEditorialLabel().setEnabled(true);
		administradorLlibres.getAfegirGenereLabel().setEnabled(true);
		administradorLlibres.getPaginadorPanel().setVisible(false);
		administradorLlibres.getReservatCheckBox().setEnabled(true);
		mostrarDadesLlibres();
		activarDadesLlibres();
	}

	private void setDadesLlibresPerAlta() {
		administradorLlibres.omplirComboBox();
		administradorLlibres.getEditarLlibreButton().setEnabled(false);
		administradorLlibres.getMostrarLlibreButton().setEnabled(false);
		administradorLlibres.getBaixaLlibreButton().setEnabled(false);
		administradorLlibres.getConfirmarButton().setVisible(true);
		administradorLlibres.getCancellarButton().setVisible(true);
		administradorLlibres.getAfegirAutorLabel().setEnabled(true);
		administradorLlibres.getAfegirEditorialLabel().setEnabled(true);
		administradorLlibres.getAfegirGenereLabel().setEnabled(true);
		administradorLlibres.getPaginadorPanel().setVisible(false);
		esborrarDadesLlibres();
		activarDadesLlibres();
	}

	private void esborrarDadesLlibres() {
		administradorLlibres.getIdLlibreField().setText("");
		administradorLlibres.getIsbnField().setText("");
		administradorLlibres.getTitolField().setText("");
		administradorLlibres.getGeneresComboBox().setSelectedIndex(0);
		administradorLlibres.getAutorsComboBox().setSelectedIndex(0);
		administradorLlibres.getEditorialsComboBox().setSelectedIndex(0);
		administradorLlibres.getDataPublicacioField().setText("");
		administradorLlibres.getEdicioField().setText("");
		administradorLlibres.getSinopsisTextArea().setText("");
		administradorLlibres.getPuntuacioField().setText("");
		administradorLlibres.getSeguentIconLabel().setText("");
		administradorLlibres.getAnteriorIconLabel().setText("");
		administradorLlibres.getNumeroPaginesField().setText("");
	}

	private void activarDadesLlibres() {
		administradorLlibres.getIsbnField().setEditable(true);
		administradorLlibres.getTitolField().setEditable(true);
		administradorLlibres.getDataPublicacioField().setEditable(true);
		administradorLlibres.getEdicioField().setEditable(true);
		administradorLlibres.getSinopsisTextArea().setEditable(true);
		administradorLlibres.getNumeroPaginesField().setEditable(true);
		administradorLlibres.getAutorsComboBox().setEnabled(true);
		administradorLlibres.getAutorsComboBox().setEnabled(true);
		administradorLlibres.getEditorialsComboBox().setEnabled(true);
		administradorLlibres.getGeneresComboBox().setEnabled(true);
		administradorLlibres.getPuntuacioField().setEditable(true);
	}

	private void desactivarDadesLlibres() {
		administradorLlibres.getIsbnField().setEditable(false);
		administradorLlibres.getTitolField().setEditable(false);
		administradorLlibres.getDataPublicacioField().setEditable(false);
		administradorLlibres.getEdicioField().setEditable(false);
		administradorLlibres.getSinopsisTextArea().setEditable(false);
		administradorLlibres.getNumeroPaginesField().setEditable(false);
		administradorLlibres.getAutorsComboBox().setEnabled(false);
		administradorLlibres.getEditorialsComboBox().setEnabled(false);
		administradorLlibres.getGeneresComboBox().setEnabled(false);
		administradorLlibres.getPuntuacioField().setEditable(false);

	}

	private void getDadesRow(int row) {
		administradorLlibres.getRowActualField().setText(String.valueOf(administradorLlibres.getRowActiu() + 1));
		administradorLlibres.getRowTotalsField().setText(String.valueOf(table.getRowCount()));
		administradorLlibres.getLlibresTable().setRowSelectionInterval(row, row);
		administradorLlibres.getIdLlibreField().setText(getValorCella(row, 0));
		administradorLlibres.getIsbnField().setText(getValorCella(row, 1));
		administradorLlibres.getTitolField().setText(getValorCella(row, 2));
		administradorLlibres.getDataPublicacioField().setText(getValorCella(row, 5));
		administradorLlibres.getEdicioField().setText(getValorCella(row, 6));
		administradorLlibres.getAutorsComboBox().setSelectedItem(getValorCella(row, 4));
		administradorLlibres.getEditorialsComboBox().setSelectedItem(getValorCella(row, 7));
		administradorLlibres.getGeneresComboBox().setSelectedItem(getValorCella(row, 3));
		administradorLlibres.getSinopsisTextArea().setText(getValorCella(row, 8));
		administradorLlibres.getPuntuacioField().setText(getValorCella(row, 9));
		administradorLlibres.getNumeroPaginesField().setText(getValorCella(row, 10));

		if (getValorCella(row, 11).equals("true")) {
			administradorLlibres.getReservatCheckBox().setSelected(true);
		} else {
			administradorLlibres.getReservatCheckBox().setSelected(false);
		}
		administradorLlibres.getNumeroReservesField().setText(getValorCella(row, 12));

	}
	
	private String getDadesRowLLibre(int row) {
		String dadesRowToString = 
				"id: " + getValorCella(row, 0) + "\n" +
						"isbn: " + getValorCella(row, 1) + "\n" +
						"títol: " + getValorCella(row, 2) + "\n" +
						"gènere: " + getValorCella(row, 3) + "\n" +
						"autor: " + 	getValorCella(row, 4) + "\n" +
						"data: " + getValorCella(row, 5) + "\n" +
						"edicio: " + getValorCella(row, 6) + "\n" +
						"editorial: " + getValorCella(row, 7) + "\n" +
						"sinopsis: " + getValorCella(row, 8) + "\n" +
						"puntuació: " + getValorCella(row, 9) + "\n" +
						"núm. pàgines: " + getValorCella(row, 10) + "\n" +
						"reservat?: " + getValorCella(row, 11) + "\n" +
						"núm reserves: " + getValorCella(row, 12);
		return dadesRowToString;
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
						getValorCella(row, 12);
		return dadesRowToString;
	}

	private boolean comprovarSiLlibreSeleccionat() {
		int rowAMostrar = administradorLlibres.getLlibresTable().getSelectedRow();
		if (rowAMostrar == -1) {
			errorLlibreNoSeleccionat();
			return false;
		} else {
			return true;
		}
	}

	private String getValorCella(int row, int column) {
		return table.getValueAt(row, column).toString();
	}

	private void ferLaCerca() {
		String textDeLaCerca = administradorLlibres.getCercaField().getText();
		String filtre = getFiltre();
		if (textDeLaCerca.length() == 0) {
			errorCampCercaBuit();
		} else {
			//llibresModel = new LlibresModel();
			//llibresModel.consultarTotsElsLlibresPerFiltre(filtre + "," + textDeLaCerca);
			
			//S'envia l'string de cerca al servidor
			mostraLaCerca("Filtre: " + filtre +"\n" + "Text a cercar: " + textDeLaCerca + "\n" 
			+ "Valors enviats al servidor: " + usuariConnectat.getIdSessio() + "," + "consulta_llibre_" + filtre + "," + textDeLaCerca);
		}

	}
	
	private void mostraLaCerca(String cerca) {
		JOptionPane.showMessageDialog(administradorLlibres,cerca, "Dades de la cerca", JOptionPane.INFORMATION_MESSAGE);
	}

	private void esborrarCampCerca() {
		administradorLlibres.getCercaField().setText("");
		administradorLlibres.getCercaField().requestFocus();

	}

	private String getFiltre() {
		String filtre = null;
		ButtonGroup grup = administradorLlibres.getFiltreButtonGroup();
		if (grup.getSelection().equals(administradorLlibres.getTitolRadioButton().getModel())) {
			filtre = "titol";
		}
		if (grup.getSelection().equals(administradorLlibres.getAutorRadioButton().getModel())) {
			filtre = "autor";
		}
		if (grup.getSelection().equals(administradorLlibres.getGenereRadioButton().getModel())) {
			filtre = "genere";
		}
		if (grup.getSelection().equals(administradorLlibres.getEditorialRadioButton().getModel())) {
			filtre = "editorial";
		}
		return filtre;
	}

	private void errorCampCercaBuit() {
		JOptionPane.showMessageDialog(administradorLlibres,
				WarningStrings.getString("ConsultaLlibresNoRegistrat.missatgeCampCercaBuit"),
				WarningStrings.getString("ConsultaLlibresNoRegistrat.titolMissatgeCampCercaBuit"),
				JOptionPane.ERROR_MESSAGE);

	}

	private void errorLlibreNoSeleccionat() {
		JOptionPane.showMessageDialog(administradorLlibres, "No has seleccionat cap llibre",
				WarningStrings.getString("Error selecció llibre"), JOptionPane.ERROR_MESSAGE);
	}

	@Override
	public void mouseEntered(MouseEvent e) {

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

	/*-------------------------- Getters and Setters Methods --------------------------*/

	public String getAccio() {
		return administradorLlibres.getAccio();
		
	}

	public void setAccio(String accio) {
		administradorLlibres.setAccio(accio);
	}
}
