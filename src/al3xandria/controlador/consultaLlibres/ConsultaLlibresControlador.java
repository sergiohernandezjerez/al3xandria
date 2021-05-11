package al3xandria.controlador.consultaLlibres;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import al3xandria.model.ComunicacioClientServidor;
import al3xandria.model.objects.Usuari;
import al3xandria.strings.WarningStrings;
import al3xandria.vista.centralPanel.ConsultaLlibres;

/**
 * Clase per controlar la consulta dels llibres per usuaris que han fet login
 * 
 * @param consultaLlibres
 */
public class ConsultaLlibresControlador implements MouseListener {

	private ConsultaLlibres consultaLlibres;
	private JTable table;
	private Usuari usuariConnectat;
	private ComunicacioClientServidor comunicacioClientServidor = new ComunicacioClientServidor();
	private int idLlibre;

	public ConsultaLlibresControlador(ConsultaLlibres consultaLlibres,
			Usuari usuariConnectat) {
		this.consultaLlibres = consultaLlibres;
		this.table = consultaLlibres.getLlibresTable();
		this.usuariConnectat = consultaLlibres.getUsuariConnectat();
	}

	@Override
	public void mouseClicked(MouseEvent e) {

		if (consultaLlibres.getCercarButton() == e.getSource()) {
			ferLaCerca();
		}

		if (consultaLlibres.getRefrescarLabel() == e.getSource()) {
			consultaLlibres.llistarLlibres();
		}

		// mostra les dades d'un llibre si s'ha seleccionat
		if (consultaLlibres.getMostrarLlibreButton() == e.getSource()) {
			consultaLlibres.getPaginadorPanel().setVisible(true);
			mostrarDadesLlibres();
		}

		if (consultaLlibres.getEsborrarLabel() == e.getSource()) {
			esborrarCampCerca();
		}

		if (consultaLlibres.getLupaLabel() == e.getSource()) {
			ferLaCerca();
		}

		if (consultaLlibres.getSeguentIconLabel() == e.getSource()) {
			pasarLlibreSeguent();
		}

		if (consultaLlibres.getAnteriorIconLabel() == e.getSource()) {
			pasarLlibreAnterior();
		}

		if (consultaLlibres.getPrestecsLlibreButton() == e.getSource()) {
			mostrarPrestecs();
		}

		if (consultaLlibres.getReservarButton() == e.getSource()) {
			if (comprovarSiLlibreSeleccionat()) {
				reservarLlibre();
			}

		}

		if (consultaLlibres.getLlogarLlibreButton() == e.getSource()) {
			if (comprovarSiLlibreSeleccionat()) {
				llogarLlibre();
			}
		}

		if (consultaLlibres.getCancellarButton() == e.getSource()) {

		}

	}

	/**
	 * Métode que permet llogar un llibre
	 * 
	 * @author SergioHernandez
	 */
	private void llogarLlibre() {
		int rowAMostrar = consultaLlibres.getLlibresTable().getSelectedRow();
		consultaLlibres.setRowActiu(
				consultaLlibres.getLlibresTable().getSelectedRow());
		String dadesLlibreALlogar = getDadesRowLLibre(
				consultaLlibres.getRowActiu());
		int valor = JOptionPane.showConfirmDialog(consultaLlibres,
				dadesLlibreALlogar + "\n\n" + "Vols llogar el llibre?",
				"Dades del llibre a reservar", JOptionPane.YES_NO_OPTION,
				JOptionPane.WARNING_MESSAGE, null);
		if (valor == JOptionPane.YES_OPTION) {

			if (enviarDadesLlibreALlogarAlServidor()) {
				mostraDadesLlibreAReservar(dadesLlibreALlogar,
						getDadesRowToString(consultaLlibres.getRowActiu()));
				refrescarElsLlibres();
			} else {
				mostraErrorLlogarLlibre();
			}
		}
	}

	/**
	 * Envia la consulta al servidor per llogar un llibre
	 * 
	 * @return true si tot ha anat bé, false si no
	 * @author SergioHernandez
	 */
	private boolean enviarDadesLlibreALlogarAlServidor() {
		comunicacioClientServidor = new ComunicacioClientServidor();
		comunicacioClientServidor.iniciarComunicacio(
				usuariConnectat.getIdSessio() + ",llogar_llibre,"
						+ usuariConnectat.getId_usuari() + "," + idLlibre);
		String rebutString = comunicacioClientServidor.getData();
		if (rebutString.equals("1")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Métode que permet fer la reserva d'un llibre
	 * 
	 * @author SergioHernandez
	 */
	private void reservarLlibre() {
		int rowAMostrar = consultaLlibres.getLlibresTable().getSelectedRow();
		consultaLlibres.setRowActiu(
				consultaLlibres.getLlibresTable().getSelectedRow());
		String dadesLlibreAReservar = getDadesRowLLibre(
				consultaLlibres.getRowActiu());
		int valor = JOptionPane.showConfirmDialog(consultaLlibres,
				dadesLlibreAReservar + "\n\n" + "Vols reservar el llibre?",
				"Dades del llibre a reservar", JOptionPane.YES_NO_OPTION,
				JOptionPane.WARNING_MESSAGE, null);
		if (valor == JOptionPane.YES_OPTION) {

			if (enviarDadesLlibreAReservarAlServidor()) {
				mostraDadesLlibreAReservar(dadesLlibreAReservar,
						getDadesRowToString(consultaLlibres.getRowActiu()));
				refrescarElsLlibres();
			} else {
				mostraErrorReservarLlibre();
			}
		}
	}

	/**
	 * Envia la consulta al servidor per reservar un llibre
	 * 
	 * @return true si tot ha anat bé, false si no
	 * @author SergioHernandez
	 */
	private boolean enviarDadesLlibreAReservarAlServidor() {
		comunicacioClientServidor = new ComunicacioClientServidor();
		comunicacioClientServidor.iniciarComunicacio(
				usuariConnectat.getIdSessio() + ",fer_reserva,"
						+ usuariConnectat.getId_usuari() + "," + idLlibre);
		String rebutString = comunicacioClientServidor.getData();
		if (rebutString.equals("1")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Mostra un error al reservar un llibre
	 * 
	 * @author SergioHernandez
	 */
	private void mostraErrorReservarLlibre() {
		JOptionPane.showMessageDialog(consultaLlibres,
				"No s'ha pogut reservar el llibre", "Error reserva",
				JOptionPane.ERROR_MESSAGE);

	}

	/**
	 * Mostra un error al llogar un llibre
	 * 
	 * @author SergioHernandez
	 */
	private void mostraErrorLlogarLlibre() {
		JOptionPane.showMessageDialog(consultaLlibres,
				"No s'ha pogut llogar el llibre", "Error llogar",
				JOptionPane.ERROR_MESSAGE);

	}

	/**
	 * Torna a omplir el jtable amb tots els llibres
	 * 
	 * @author SergioHernandez
	 */
	private void refrescarElsLlibres() {
		consultaLlibres.llistarLlibres();

	}

	/**
	 * Mostra les dades del llibre que es vol reservar
	 * 
	 * @param dadesLlibreAReservar
	 * @param dadesRowToString
	 * @author SergioHernandez
	 */
	private void mostraDadesLlibreAReservar(String dadesLlibreAReservar,
			String dadesRowToString) {
		JOptionPane.showMessageDialog(consultaLlibres,
				"Has reservat el llibre amb aquestes dades: \n"
						+ dadesLlibreAReservar
						+ "\nDades enviades al servidor: "
						+ usuariConnectat.getIdSessio() + ",insercio_llibre,"
						+ dadesRowToString,
				"Dades llibre afegit", JOptionPane.INFORMATION_MESSAGE);

	}

	/**
	 * Extreu les dades d'una fila i crea un cadea separada per comes
	 * 
	 * @param row fila de la table
	 * @author SergioHernandez
	 */
	private String getDadesRowToString(int row) {
		String dadesRowToString = getValorCella(row, 0) + ","
				+ getValorCella(row, 1) + "," + getValorCella(row, 2) + ","
				+ getValorCella(row, 3) + "," + getValorCella(row, 4) + ","
				+ getValorCella(row, 5) + "," + getValorCella(row, 6) + ","
				+ getValorCella(row, 7) + "," + getValorCella(row, 8) + ","
				+ getValorCella(row, 9) + "," + getValorCella(row, 10) + ","
				+ getValorCella(row, 11) + "," + getValorCella(row, 12);
		return dadesRowToString;
	}

	private void mostrarPrestecs() {
		consultaLlibres.llistarPrestecs();

	}

	/**
	 * Mètode que mostra el llibre següent de la table
	 * 
	 * @author SergioHernandez
	 */
	private void pasarLlibreSeguent() {
		if (consultaLlibres.getRowActiu() + 1 == table.getRowCount()) {
			consultaLlibres.setRowActiu(table.getRowCount() - 1);
		} else {
			consultaLlibres.seguentRowActiu(1, table.getRowCount());
			getDadesRow(consultaLlibres.getRowActiu());

		}

	}

	/**
	 * Comprova si un llibre està selecciona
	 * 
	 * @return true si hi ha un llibre seleccionat, false si no
	 * @author SergioHernandez
	 */
	private boolean comprovarSiLlibreSeleccionat() {
		int rowAMostrar = consultaLlibres.getLlibresTable().getSelectedRow();
		if (rowAMostrar == -1) {
			errorLlibreNoSeleccionat();
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Mètode que mostra el llibre anterior de la table
	 * 
	 * @author SergioHernandez
	 */
	private void pasarLlibreAnterior() {
		consultaLlibres.anteriorRowActiu(1);
		if (consultaLlibres.getRowActiu() >= 0) {
			getDadesRow(consultaLlibres.getRowActiu());
		} else {
			consultaLlibres.setRowActiu(0);
		}
	}

	/**
	 * Mètode que mostra les dades d'un llibre si está seleccionat
	 * 
	 * @author SergioHernandez
	 */
	private void mostrarDadesLlibres() {
		int rowAMostrar = consultaLlibres.getLlibresTable().getSelectedRow();
		if (rowAMostrar > -1) {
			consultaLlibres.setRowActiu(
					consultaLlibres.getLlibresTable().getSelectedRow());
			getDadesRow(rowAMostrar);
		} else {
			errorLlibreNoSeleccionat();
		}

	}

	/**
	 * Mètode que obté les dades d'una fila de la table i les mostra en el panel
	 * d'informació del llibre
	 * 
	 * @param row fila seleccionada
	 * @author SergioHernandez
	 */
	private void getDadesRow(int row) {
		consultaLlibres.getRowActualField()
				.setText(String.valueOf(consultaLlibres.getRowActiu() + 1));
		consultaLlibres.getRowTotalsField()
				.setText(String.valueOf(table.getRowCount()));
		consultaLlibres.getLlibresTable().setRowSelectionInterval(row, row);
		consultaLlibres.getIdLlibreField().setText(getValorCella(row, 0));
		consultaLlibres.getIsbnField().setText(getValorCella(row, 1));
		consultaLlibres.getTitolField().setText(getValorCella(row, 2));
		consultaLlibres.getGenereField().setText(getValorCella(row, 3));
		consultaLlibres.getAutorsField().setText(getValorCella(row, 4));
		consultaLlibres.getDataPublicacioField().setText(getValorCella(row, 5));
		consultaLlibres.getEdicioField().setText(getValorCella(row, 6));
		consultaLlibres.getEditorialField().setText(getValorCella(row, 7));
		consultaLlibres.getSinopsisTextArea().setText(getValorCella(row, 8));
		consultaLlibres.getPuntuacioField().setText(getValorCella(row, 9));
		consultaLlibres.getNumeroPaginesField().setText(getValorCella(row, 10));

		if (getValorCella(row, 11).equals("true")) {
			consultaLlibres.getReservatCheckBox().setSelected(true);
		} else {
			consultaLlibres.getReservatCheckBox().setSelected(false);
		}
		consultaLlibres.getNumeroReservesField()
				.setText(getValorCella(row, 12));

	}

	/**
	 * Extreu les dades d'una fila i crea un cadena amb salts de linea
	 * 
	 * @param row fila de la table
	 * @author SergioHernandez
	 */
	private String getDadesRowLLibre(int row) {
		String dadesRowToString = "id: " + getValorCella(row, 0) + "\n"
				+ "isbn: " + getValorCella(row, 1) + "\n" + "títol: "
				+ getValorCella(row, 2) + "\n" + "gènere: "
				+ getValorCella(row, 3) + "\n" + "autor: "
				+ getValorCella(row, 4) + "\n" + "data: "
				+ getValorCella(row, 5) + "\n" + "edicio: "
				+ getValorCella(row, 6) + "\n" + "editorial: "
				+ getValorCella(row, 7) + "\n" + "sinopsis: "
				+ getValorCella(row, 8) + "\n" + "puntuació: "
				+ getValorCella(row, 9) + "\n" + "núm. pàgines: "
				+ getValorCella(row, 10) + "\n" + "reservat?: "
				+ getValorCella(row, 11) + "\n" + "núm reserves: "
				+ getValorCella(row, 12);
		setIdLlibre(Integer.parseInt(getValorCella(row, 0)));
		return dadesRowToString;
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
	 * Fa la cerca amb els valor introduïts En aquest cas només mostro la
	 * consulta que s'enviarà al servidor
	 * 
	 * @author SergioHernandez
	 */
	private void ferLaCerca() {
		String textDeLaCerca = consultaLlibres.getCercaField().getText();
		String filtre = getFiltre();
		if (textDeLaCerca.length() == 0) {
			errorCampCercaBuit();
		} else {
			comunicacioClientServidor.iniciarComunicacio(
					usuariConnectat.getIdSessio() + "," + "consulta_llibre_"
							+ filtre + "," + textDeLaCerca);

			consultaLlibres.llistarLlibresConsulta(filtre, textDeLaCerca);
		}

	}

	/**
	 * Missatge que mostra la consulta que s'envia al servidor
	 * 
	 * @param cerca consulta
	 * @author SergioHernandez
	 */
	private void mostraLaCerca(String cerca) {
		JOptionPane.showMessageDialog(consultaLlibres, cerca,
				"Dades de la cerca", JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * Esborra les dades introduïdes al camp de la cerca
	 * 
	 * @author SergioHernandez
	 */
	private void esborrarCampCerca() {
		consultaLlibres.getCercaField().setText("");
		consultaLlibres.getCercaField().requestFocus();

	}

	/**
	 * Extreu el filtre que s'ha seleccionat per fer la cerca
	 * 
	 * @return el nom del filtre
	 * @author SergioHernandez
	 */
	private String getFiltre() {
		String filtre = null;
		ButtonGroup grup = consultaLlibres.getFiltreButtonGroup();
		if (grup.getSelection()
				.equals(consultaLlibres.getTitolRadioButton().getModel())) {
			filtre = "titol";
		}
		if (grup.getSelection()
				.equals(consultaLlibres.getAutorRadioButton().getModel())) {
			filtre = "autor";
		}
		if (grup.getSelection()
				.equals(consultaLlibres.getGenereRadioButton().getModel())) {
			filtre = "genere";
		}
		if (grup.getSelection()
				.equals(consultaLlibres.getEditorialRadioButton().getModel())) {
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
		JOptionPane.showMessageDialog(consultaLlibres,
				WarningStrings.getString(
						"ConsultaLlibresNoRegistrat.missatgeCampCercaBuit"),
				WarningStrings.getString(
						"ConsultaLlibresNoRegistrat.titolMissatgeCampCercaBuit"),
				JOptionPane.ERROR_MESSAGE);

	}

	/**
	 * Mostra un error si no s'ha seleccionat cap llibre
	 * 
	 * @author SergioHernandez
	 */
	private void errorLlibreNoSeleccionat() {
		JOptionPane.showMessageDialog(consultaLlibres,
				"No s'ha seleccionat cap llibre",
				WarningStrings.getString("Error selecció llibre"),
				JOptionPane.ERROR_MESSAGE);
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

	private void setIdLlibre(int id_llibre) {
		this.idLlibre = id_llibre;

	}

}
