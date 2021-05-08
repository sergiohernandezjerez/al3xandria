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

	public ConsultaLlibresControlador(ConsultaLlibres consultaLlibres, Usuari usuariConnectat) {
		this.consultaLlibres = consultaLlibres;
		this.table = consultaLlibres.getLlibresTable();
		this.usuariConnectat = consultaLlibres.getUsuariConnectat();
	}

	@Override
	public void mouseClicked(MouseEvent e) {

		if (consultaLlibres.getCercarButton() == e.getSource()) {
			ferLaCerca();
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
		
		if(consultaLlibres.getPrestecsLlibreButton() == e.getSource()) {
			mostrarPrestecs();
		}
		
		if(consultaLlibres.getReservarButton() == e.getSource()) {
			missatgeLlibreReservat();
		}
		
		if(consultaLlibres.getLlogarLlibreButton() == e.getSource()) {
			missatgeLlibreLlogat();
		}
		
		if(consultaLlibres.getCancellarButton() == e.getSource()) {
			
		}

	}

	private void missatgeLlibreLlogat() {
		int rowAMostrar = consultaLlibres.getLlibresTable().getSelectedRow();
		if (rowAMostrar > -1) {
			JOptionPane.showMessageDialog(consultaLlibres, "Falta implementar el lloger de llibres",
					WarningStrings.getString("Llogar llibre"), JOptionPane.ERROR_MESSAGE);
		} else {
			errorLlibreNoSeleccionat();
		}
		
	}

	private void missatgeLlibreReservat() {
		int rowAMostrar = consultaLlibres.getLlibresTable().getSelectedRow();
		if (rowAMostrar > -1) {
			JOptionPane.showMessageDialog(consultaLlibres, "Falta implementar la reserva de llibres",
					WarningStrings.getString("Reservar llibre"), JOptionPane.ERROR_MESSAGE);
		} else {
			errorLlibreNoSeleccionat();
		}
		
	}

	private void mostrarPrestecs() {
		//Es mostrarà quan estigui implementat el prèstec de llibres
		//consultaLlibres.getCancellarButton().setVisible(true);
		//consultaLlibres.getCancellarButton().setEnabled(true);
		JOptionPane.showMessageDialog(consultaLlibres, "Falta implementar la consulta dels prèstecs\nde l'usuari",
				WarningStrings.getString("Consultar prèstecs"), JOptionPane.ERROR_MESSAGE);
		
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
			consultaLlibres.setRowActiu(consultaLlibres.getLlibresTable().getSelectedRow());
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
		consultaLlibres.getRowActualField().setText(String.valueOf(consultaLlibres.getRowActiu() + 1));
		consultaLlibres.getRowTotalsField().setText(String.valueOf(table.getRowCount()));
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
		consultaLlibres.getNumeroReservesField().setText(getValorCella(row, 12));

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
		String textDeLaCerca = consultaLlibres.getCercaField().getText();
		String filtre = getFiltre();
		if (textDeLaCerca.length() == 0) {
			errorCampCercaBuit();
		} else {
			// llibresModel = new LlibresModel();
			// llibresModel.consultarTotsElsLlibresPerFiltre(filtre + "," + textDeLaCerca);

			comunicacioClientServidor.iniciarComunicacio(usuariConnectat.getIdSessio() + "," + "consulta_llibre_" + filtre
					+ "," + textDeLaCerca);
	//dadesRebudesDelServidor = comunicacioClientServidor.getDadesDelServidor();
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
		JOptionPane.showMessageDialog(consultaLlibres, cerca, "Dades de la cerca", JOptionPane.INFORMATION_MESSAGE);
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
		if (grup.getSelection().equals(consultaLlibres.getTitolRadioButton().getModel())) {
			filtre = "titol";
		}
		if (grup.getSelection().equals(consultaLlibres.getAutorRadioButton().getModel())) {
			filtre = "autor";
		}
		if (grup.getSelection().equals(consultaLlibres.getGenereRadioButton().getModel())) {
			filtre = "genere";
		}
		if (grup.getSelection().equals(consultaLlibres.getEditorialRadioButton().getModel())) {
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
		JOptionPane.showMessageDialog(consultaLlibres, "No s'ha seleccionat cap llibre",
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

}
