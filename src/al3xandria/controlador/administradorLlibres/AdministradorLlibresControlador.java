package al3xandria.controlador.administradorLlibres;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import al3xandria.model.ControlDeDades;
import al3xandria.model.objects.Usuari;
import al3xandria.strings.WarningStrings;
import al3xandria.vista.centralPanel.AdministradorLlibres;
import al3xandria.vista.centralPanel.CentralPanelMessages;

/**
 * Classe que controla les accions que fa l'administrado en el panel
 * AdministradorLlibres
 * 
 * @author SergioHernandez
 *
 */
public class AdministradorLlibresControlador implements MouseListener {

	private AdministradorLlibres administradorLlibres;
	private JTable table;
	private Usuari usuariConnectat;
	private ControlDeDades controlDeDades;

	/**
	 * Constructor
	 * 
	 * @param administradorLlibres jpanel a controlar
	 * @param usuariConnectat      usuari que està connectat
	 */
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

		// mostra les dades d'un llibre si s'ha seleccionat
		if (administradorLlibres.getMostrarLlibreButton() == e.getSource() && getAccio().equals("default")) {
			if (comprovarSiLlibreSeleccionat()) {
				setDadesLlibresPerMostrar();
				desactivarDadesLlibres();
				mostrarDadesLlibres();

			}

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

		// elimina un llibre si s'ha seleccionat
		if (administradorLlibres.getBaixaLlibreButton() == e.getSource() && getAccio().equals("default")) {
			if (comprovarSiLlibreSeleccionat()) {
				setAccio("baixa");
				avisConfirmarBaixaLlibre();
			}

		}

		// edita un llibre si s'ha seleccionat
		if (administradorLlibres.getEditarLlibreButton() == e.getSource() && getAccio().equals("default")) {
			if (comprovarSiLlibreSeleccionat()) {
				setAccio("editar");
				setDadesLlibresPerEditar();
			}

		}

		// Accions del butó confirmar
		if (administradorLlibres.getConfirmarButton() == e.getSource()) {
			if (getAccio().equals("alta")) {
				avisConfirmarAltaLlibre();

			}

			if (getAccio().equals("editar")) {
				avisConfirmarUpdateLlibre();
			}
		}

		// accio del botó cancel.lar
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

	/**
	 * Mostra un avís per modificar un llibre Comproba que totes les dades estiguin
	 * introduides i les mostra totes per informar a l'usuari
	 * 
	 * @author SergioHernandez
	 */
	private void avisConfirmarUpdateLlibre() {
		if (comprovacioDadesAltaLlibre()) {
			String dadesLlibreModificat = dadesLlibreModificat();
			int valor = JOptionPane.showConfirmDialog(administradorLlibres,
					dadesLlibreModificat + "\n\n" + "Vols modificar el llibre?", "Dades del llibre modificat",
					JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null);
			if (valor == JOptionPane.YES_OPTION) {
				mostraDadesLlibreModificat(dadesLlibreModificat, dadesLlibreToString());
			}
		}

	}

	/**
	 * ge l'id del llibre per pasar-lo per paràmetres al servidor
	 * 
	 * @return id del llibre
	 * @author SergioHernandez
	 */
	private String getIdLlibre() {
		return administradorLlibres.getIdLlibreField().getText();

	}

	/**
	 * Dades del llibre ja modificat per mostrar en el missatge d'informació
	 * 
	 * @return dades del llibre amb salt de linea
	 * @author SergioHernandez
	 */
	private String dadesLlibreModificat() {
		String dadesLlibreModificat = "id: " + administradorLlibres.getIdLlibreField().getText() + "\n" + "títol: "
				+ administradorLlibres.getTitolField().getText() + "\n" + "reservat?: "
				+ administradorLlibres.getReservatCheckBox().isSelected() + "\n" + "autor: "
				+ administradorLlibres.getAutorsComboBox().getSelectedItem() + "\n" + "gènere: "
				+ administradorLlibres.getGeneresComboBox().getSelectedItem() + "\n" + "editorial: "
				+ administradorLlibres.getEditorialsComboBox().getSelectedItem() + ",\n" + "puntuació: "
				+ administradorLlibres.getPuntuacioField().getText() + ",\n" + "isbn: "
				+ administradorLlibres.getIsbnField().getText() + ",\n" + "edició: "
				+ administradorLlibres.getEdicioField().getText() + ",\n" + "data publicació: "
				+ administradorLlibres.getDataPublicacioField().getText() + ",\n" + "núm. pàgines: "
				+ administradorLlibres.getNumeroPaginesField().getText() + ",\n" + "núm. reserves: "
				+ administradorLlibres.getNumeroReservesField().getText() + ",\n" + "sinopsis: "
				+ administradorLlibres.getSinopsisTextArea().getText() + "...\n";

		return dadesLlibreModificat;
	}

	/**
	 * Dades del llibre per fer l'alta per mostrar en el missatge d'informació
	 * 
	 * @return dades del llibre amb salt de linea
	 * @author SergioHernandez
	 */
	private String dadesLlibreAlta() {
		String dadesLlibreAlta = "titol: " + administradorLlibres.getTitolField().getText() + "\n" + "reservat?: "
				+ administradorLlibres.getReservatCheckBox().isSelected() + "\n" + "autor: "
				+ administradorLlibres.getAutorsComboBox().getSelectedItem() + "\n" + "gènere: "
				+ administradorLlibres.getGeneresComboBox().getSelectedItem() + "\n" + "editorial: "
				+ administradorLlibres.getEditorialsComboBox().getSelectedItem() + ",\n" + "puntuació: "
				+ administradorLlibres.getPuntuacioField().getText() + ",\n" + "isbn: "
				+ administradorLlibres.getIsbnField().getText() + ",\n" + "edició: "
				+ administradorLlibres.getEdicioField().getText() + ",\n" + "data publicació: "
				+ administradorLlibres.getDataPublicacioField().getText() + ",\n" + "núm. pàgines: "
				+ administradorLlibres.getNumeroPaginesField().getText() + ",\n" + "núm reserves: "
				+ administradorLlibres.getNumeroReservesField().getText() + ",\n" + "sinopsis: "
				+ administradorLlibres.getSinopsisTextArea().getText() + "...\n";

		return dadesLlibreAlta;
	}

	/**
	 * Dades del llibre per modificar per enviar com a paràmetre al servidor
	 * 
	 * @return dades del llibre separats per comes
	 * @author SergioHernandez
	 */
	private String dadesLlibreToString() {
		String dadesLlibreModificat = administradorLlibres.getTitolField().getText() + ","
				+ administradorLlibres.getReservatCheckBox().isSelected() + ","
				+ administradorLlibres.getAutorsComboBox().getSelectedItem() + ","
				+ administradorLlibres.getGeneresComboBox().getSelectedItem() + ","
				+ administradorLlibres.getEditorialsComboBox().getSelectedItem() + ","
				+ administradorLlibres.getPuntuacioField().getText() + ","
				+ administradorLlibres.getIsbnField().getText() + "," + administradorLlibres.getEdicioField().getText()
				+ "," + administradorLlibres.getDataPublicacioField().getText() + ","
				+ administradorLlibres.getNumeroPaginesField().getText() + ","
				+ administradorLlibres.getNumeroReservesField().getText() + ","
				+ administradorLlibres.getSinopsisTextArea().getText();

		return dadesLlibreModificat;
	}

	/**
	 * Mostra un avís amb l'informació del llibre que s'ha modificat amb les dades
	 * del mateix i la consulta que s'envia al servidor
	 * 
	 * @param dadesLlibres        dades del llibre amb salt de linea
	 * @param dadesLlibreToString dades del llibre separat per comes
	 * @author SergioHernandez
	 */
	private void mostraDadesLlibreModificat(String dadesLlibres, String dadesLlibreToString) {
		String idLlibre = getIdLlibre();
		JOptionPane.showMessageDialog(administradorLlibres,
				"Has modificat el llibre amb aquestes dades: \n" + dadesLlibres + "\nDades enviades al servidor: "
						+ usuariConnectat.getIdSessio() + ",modificar_llibre," + idLlibre + "," + dadesLlibreToString,
				"Dades llibre modificat", JOptionPane.INFORMATION_MESSAGE);
		setPanelPerDefecte();
	}

	/**
	 * Mostra un avís amb l'informació del llibre que s'eliminarà
	 * 
	 * @author SergioHernandez
	 */
	private void avisConfirmarBaixaLlibre() {
		setAccio("default");
		String dadesLlibreAEliminar = getDadesRowLLibre(administradorLlibres.getRowActiu());
		int valor = JOptionPane.showConfirmDialog(administradorLlibres,
				dadesLlibreAEliminar + "\n\n" + "Vols eliminar el llibre?", "Dades del llibre a eliminar",
				JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null);
		if (valor == JOptionPane.YES_OPTION) {

			mostraDadesLlibreAEliminar(dadesLlibreAEliminar, getDadesRowToString(administradorLlibres.getRowActiu()));
		}

	}

	/**
	 * Mostra un avis amb les dades del llibra que s'ha eliminat i mostra la
	 * consulta que s'enviarà al servidor
	 * 
	 * @param dadesLlibres        dades del llibre amb salt de linea
	 * @param dadesLlibreToString dades del llibre separat per comes
	 * @author SergioHernandez
	 */
	private void mostraDadesLlibreAEliminar(String dadesLlibre, String dadesLlibreToString) {
		String idLlibre = getIdLlibre();
		JOptionPane.showMessageDialog(administradorLlibres,
				"Has esborrat el llibre amb aquestes dades: \n" + dadesLlibre + "\nDades enviades al servidor: "
						+ usuariConnectat.getIdSessio() + ",eliminar_llibre," + idLlibre + "," + dadesLlibreToString,
				"Dades llibre eliminat", JOptionPane.INFORMATION_MESSAGE);
		setPanelPerDefecte();
	}

	/**
	 * Mostra un avis amb les dades del llibre que es crearà
	 * 
	 * @author SergioHernandez
	 */
	private void avisConfirmarAltaLlibre() {
		if (comprovacioDadesAltaLlibre()) {
			String dadesLLibresAlta = dadesLlibreAlta();
			int valor = JOptionPane.showConfirmDialog(administradorLlibres,
					dadesLLibresAlta + "\n\n" + "Vols afegir el llibre?", "Dades del llibre a afegir",
					JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null);
			if (valor == JOptionPane.YES_OPTION) {
				mostraDadesLlibreAlta(dadesLLibresAlta, dadesLlibreToString());
			}
		}

	}

	/**
	 * Comprova que totes les dades introduïdes per fer l'alta d'un llibre siguin
	 * correctes. Camps buits i format d'isbn
	 * 
	 * @return true si tot està correcte, false si no està correcte
	 * @author SergioHernandez
	 */
	private boolean comprovacioDadesAltaLlibre() {
		controlDeDades = new ControlDeDades();
		boolean dadesCorrectes = false;
		if (controlDeDades.comprovarCampsOmplertsAltaLlibre(administradorLlibres.getTitolField().getText(),
				administradorLlibres.getAutorsComboBox().getSelectedIndex(),
				administradorLlibres.getGeneresComboBox().getSelectedIndex(),
				administradorLlibres.getEditorialsComboBox().getSelectedIndex(),
				administradorLlibres.getIsbnField().getText(), administradorLlibres.getEdicioField().getText(),
				administradorLlibres.getDataPublicacioField().getText(),
				administradorLlibres.getNumeroPaginesField().getText())) {
			if (controlDeDades.comprovacioFormatIsbn(administradorLlibres.getIsbnField().getText())) {
				dadesCorrectes = true;
			} else {
				controlDeDades.errorFormatIsbn();
			}
		}
		return dadesCorrectes;
	}

	/**
	 * Mostra les dades del llibre que s'ha donat d'alta i la consulta que s'enviarà
	 * al servidor
	 * 
	 * @param dadesLlibres        dades del llibre amb salt de linea
	 * @param dadesLlibreToString dades del llibre separat per comes
	 * @author SergioHernandez
	 */
	private void mostraDadesLlibreAlta(String dadesLlibre, String dadesLlibresToString) {
		JOptionPane.showMessageDialog(administradorLlibres,
				"Has afegit el llibre amb aquestes dades: \n" + dadesLlibre + "\nDades enviades al servidor: "
						+ usuariConnectat.getIdSessio() + ",insercio_llibre," + dadesLlibresToString,
				"Dades llibre afegit", JOptionPane.INFORMATION_MESSAGE);
		setPanelPerDefecte();
	}

	/**
	 * Formulari per afegir dades noves a: autors, editorials i gèneres
	 * 
	 * @param nomTaula a quina taula s'afegira la valor introduït
	 * @author SergioHernandez
	 */
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

	/**
	 * Mostra un missatge amb les dades que s'enviaran al servidor per afegir un nou
	 * elementa a les taules: autors, editorials o gèneres
	 * 
	 * @param nomTaula    a quina taula s'afegirà el valor
	 * @param nouRegistre valor a afegir
	 * @author SergioHernandez
	 */
	public void enviarConsultaSegonsElement(String nomTaula, String nouRegistre) {

		switch (nomTaula) {
		case "Autor":
			mostraLaConsultaSegonsElement(
					"Valors enviats al servidor: " + usuariConnectat.getIdSessio() + ",insertat_autor," + nouRegistre);
			break;
		case "Genere":
			mostraLaConsultaSegonsElement(
					"Valors enviats al servidor: " + usuariConnectat.getIdSessio() + ",insertat_genere," + nouRegistre);
			break;
		case "Editorial":
			mostraLaConsultaSegonsElement("Valors enviats al servidor: " + usuariConnectat.getIdSessio()
					+ ",insertat_editorial," + nouRegistre);
			break;
		default:
			break;
		}
	}

	/**
	 * Mostra la consulta que es farà al servidor
	 * 
	 * @param cerca consulta
	 * @author SergioHernandez
	 */
	private void mostraLaConsultaSegonsElement(String cerca) {
		JOptionPane.showMessageDialog(administradorLlibres, cerca, "Dades de la consulta",
				JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * Missatge que informa que l'acció actual es cancel.larà Alta, editar o baixa
	 * 
	 * @author SergioHernandez
	 */
	public void avisCancellacioAccio() {
		int valor = JOptionPane.showConfirmDialog(administradorLlibres,
				"Vols cancel.lar l'acció " + getAccio() + "? Perdrás tota la informació", "Cancel.lar " + getAccio(),
				JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null);
		if (valor == JOptionPane.YES_OPTION) {
			setPanelPerDefecte();
		}

	}

	/**
	 * Mostra la informació del llibre següent
	 * 
	 * @author SergioHernandez
	 */
	private void pasarLlibreSeguent() {
		if (administradorLlibres.getRowActiu() + 1 == table.getRowCount()) {
			administradorLlibres.setRowActiu(table.getRowCount() - 1);
		} else {
			administradorLlibres.seguentRowActiu(1, table.getRowCount());
			getDadesRow(administradorLlibres.getRowActiu());

		}

	}

	/**
	 * Mostra la informació del llibre anterior
	 * 
	 * @author SergioHernandez
	 */
	private void pasarLlibreAnterior() {
		administradorLlibres.anteriorRowActiu(1);
		if (administradorLlibres.getRowActiu() >= 0) {
			getDadesRow(administradorLlibres.getRowActiu());
		} else {
			administradorLlibres.setRowActiu(0);
		}
	}

	/**
	 * Mostra les dades del llibre seleccionat
	 * 
	 * @author SergioHernandez
	 */
	private void mostrarDadesLlibres() {
		int rowAMostrar = administradorLlibres.getLlibresTable().getSelectedRow();

		administradorLlibres.setRowActiu(administradorLlibres.getLlibresTable().getSelectedRow());
		getDadesRow(rowAMostrar);

	}

	/**
	 * Posa el panel de les dades del llibre per defecte
	 * 
	 * @author SergioHernandez
	 */
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

	/**
	 * Configurar el panel per mostra les dades d'un llibre
	 * 
	 * @author SergioHernandez
	 */
	private void setDadesLlibresPerMostrar() {
		administradorLlibres.getPaginadorPanel().setVisible(true);
		administradorLlibres.getConfirmarButton().setVisible(false);
		administradorLlibres.getCancellarButton().setVisible(false);
		administradorLlibres.omplirComboBox();
	}

	/**
	 * Configurar el panel per editar les dades d'un llibre
	 * 
	 * @author SergioHernandez
	 */
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

	/**
	 * Configurar el panel per inserir les dades d'un llibre per fer l'alta
	 * 
	 * @author SergioHernandez
	 */
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

	/**
	 * Esborra els camps amb l'informació que hi hagi
	 * 
	 * @author SergioHernandez
	 */
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

	/**
	 * Activa els camps per que estiguin editables
	 * 
	 * @author SergioHernandez
	 */
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

	/**
	 * Desactiva els camps per que no estiguin editables
	 * 
	 * @author SergioHernandez
	 */
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

	/**
	 * Extreu les dades d'una fila i les introdueix als camps del llibre per
	 * mostrar, o editar
	 * 
	 * @param row fila de la table
	 * @author SergioHernandez
	 */
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

	/**
	 * Extreu les dades d'una fila i crea un cadena amb salts de linea
	 * 
	 * @param row fila de la table
	 * @author SergioHernandez
	 */
	private String getDadesRowLLibre(int row) {
		String dadesRowToString = "id: " + getValorCella(row, 0) + "\n" + "isbn: " + getValorCella(row, 1) + "\n"
				+ "títol: " + getValorCella(row, 2) + "\n" + "gènere: " + getValorCella(row, 3) + "\n" + "autor: "
				+ getValorCella(row, 4) + "\n" + "data: " + getValorCella(row, 5) + "\n" + "edicio: "
				+ getValorCella(row, 6) + "\n" + "editorial: " + getValorCella(row, 7) + "\n" + "sinopsis: "
				+ getValorCella(row, 8) + "\n" + "puntuació: " + getValorCella(row, 9) + "\n" + "núm. pàgines: "
				+ getValorCella(row, 10) + "\n" + "reservat?: " + getValorCella(row, 11) + "\n" + "núm reserves: "
				+ getValorCella(row, 12);
		return dadesRowToString;
	}

	/**
	 * Extreu les dades d'una fila i crea un cadea separada per comes
	 * 
	 * @param row fila de la table
	 * @author SergioHernandez
	 */
	private String getDadesRowToString(int row) {
		String dadesRowToString = getValorCella(row, 0) + "," + getValorCella(row, 1) + "," + getValorCella(row, 2)
				+ "," + getValorCella(row, 3) + "," + getValorCella(row, 4) + "," + getValorCella(row, 5) + ","
				+ getValorCella(row, 6) + "," + getValorCella(row, 7) + "," + getValorCella(row, 8) + ","
				+ getValorCella(row, 9) + "," + getValorCella(row, 10) + "," + getValorCella(row, 11) + ","
				+ getValorCella(row, 12);
		return dadesRowToString;
	}

	/**
	 * Comprova si un llibre està selecciona
	 * 
	 * @return true si hi ha un llibre seleccionat, false si no
	 * @author SergioHernandez
	 */
	private boolean comprovarSiLlibreSeleccionat() {
		int rowAMostrar = administradorLlibres.getLlibresTable().getSelectedRow();
		if (rowAMostrar == -1) {
			errorLlibreNoSeleccionat();
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Converteix la informació d'una cel.la en un String
	 * 
	 * @param row    fila de la table
	 * @param column columna de la table
	 * @return la informació de la cel.la en string
	 * @author SergioHernandez
	 */
	private String getValorCella(int row, int column) {
		return table.getValueAt(row, column).toString();
	}

	/**
	 * Fa la cerca amb els valor introduïts En aquest cas només mostro la consulta
	 * que s'enviarà al servidor
	 * 
	 * @author SergioHernandez
	 */
	private void ferLaCerca() {
		String textDeLaCerca = administradorLlibres.getCercaField().getText();
		String filtre = getFiltre();
		if (textDeLaCerca.length() == 0) {
			errorCampCercaBuit();
		} else {
			// llibresModel = new LlibresModel();
			// llibresModel.consultarTotsElsLlibresPerFiltre(filtre + "," + textDeLaCerca);

			// S'envia l'string de cerca al servidor
			mostraLaCerca("Filtre: " + filtre + "\n" + "Text a cercar: " + textDeLaCerca + "\n"
					+ "Valors enviats al servidor: " + usuariConnectat.getIdSessio() + "," + "consulta_llibre_" + filtre
					+ "," + textDeLaCerca);
		}

	}

	/**
	 * Missatge que mostra la consulta que s'envia al servidor
	 * 
	 * @param cerca consulta
	 * @author SergioHernandez
	 */
	private void mostraLaCerca(String cerca) {
		JOptionPane.showMessageDialog(administradorLlibres, cerca, "Dades de la cerca",
				JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * Esborra les dades introduïdes al camp de la cerca
	 * 
	 * @author SergioHernandez
	 */
	private void esborrarCampCerca() {
		administradorLlibres.getCercaField().setText("");
		administradorLlibres.getCercaField().requestFocus();

	}

	/**
	 * Extreu el filtre que s'ha seleccionat per fer la cerca
	 * 
	 * @return el nom del filtre
	 * @author SergioHernandez
	 */
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

	/**
	 * Mostra un error si el camp de cerca està buit
	 * 
	 * @author SergioHernandez
	 */
	private void errorCampCercaBuit() {
		JOptionPane.showMessageDialog(administradorLlibres,
				WarningStrings.getString("ConsultaLlibresNoRegistrat.missatgeCampCercaBuit"),
				WarningStrings.getString("ConsultaLlibresNoRegistrat.titolMissatgeCampCercaBuit"),
				JOptionPane.ERROR_MESSAGE);

	}

	/**
	 * Mostra un error si no s'ha seleccionat cap llibre
	 * 
	 * @author SergioHernandez
	 */
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
