package al3xandria.controlador.consultaLlibres;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

import com.google.gson.Gson;

import al3xandria.model.ComunicacioClientServidor;
import al3xandria.model.llibres.LlibresModel;
import al3xandria.model.objects.Llibres;
import al3xandria.model.objects.Usuari;
import al3xandria.strings.WarningStrings;
import al3xandria.vista.centralPanel.ConsultaLlibresNoRegistrat;

public class ConsultaLlibresNoRegistratControlador implements MouseListener {

	private ConsultaLlibresNoRegistrat consultaLlibresNoRegistrat;
	private JTable table;
	private Usuari usuariConnectat;
	private ComunicacioClientServidor comunicacioClientServidor;
	private LlibresModel llibresModel;

	/**
	 * Clase per controlar la consulta dels llibres per usuaris que no han fet login
	 * 
	 * @param consultaLlibresNoRegistrat
	 */
	public ConsultaLlibresNoRegistratControlador(ConsultaLlibresNoRegistrat consultaLlibresNoRegistrat,
			Usuari usuariConnectat) {
		this.consultaLlibresNoRegistrat = consultaLlibresNoRegistrat;
		this.table = consultaLlibresNoRegistrat.getLlibresTable();
		this.usuariConnectat = usuariConnectat;

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		if(consultaLlibresNoRegistrat.getRefrescarLabel() == e.getSource()) {
			consultaLlibresNoRegistrat.llistarLlibres();
		}

		if (consultaLlibresNoRegistrat.getCercarButton() == e.getSource()) {
			ferLaCerca();
		}

		// mostra les dades d'un llibre si s'ha seleccionat
		if (consultaLlibresNoRegistrat.getMostrarLlibreButton() == e.getSource()) {
			consultaLlibresNoRegistrat.getPaginadorPanel().setVisible(true);
			mostrarDadesLlibres();
		}

		if (consultaLlibresNoRegistrat.getEsborrarLabel() == e.getSource()) {
			esborrarCampCerca();
		}

		if (consultaLlibresNoRegistrat.getLupaLabel() == e.getSource()) {
			ferLaCerca();
		}

		if (consultaLlibresNoRegistrat.getSeguentIconLabel() == e.getSource()) {
			pasarLlibreSeguent();
		}

		if (consultaLlibresNoRegistrat.getAnteriorIconLabel() == e.getSource()) {
			pasarLlibreAnterior();
		}

	}

	/**
	 * Mètode que mostra el llibre següent de la table
	 * 
	 * @author SergioHernandez
	 */
	private void pasarLlibreSeguent() {
		if (consultaLlibresNoRegistrat.getRowActiu() + 1 == table.getRowCount()) {
			consultaLlibresNoRegistrat.setRowActiu(table.getRowCount() - 1);
		} else {
			consultaLlibresNoRegistrat.seguentRowActiu(1, table.getRowCount());
			getDadesRow(consultaLlibresNoRegistrat.getRowActiu());

		}

	}

	/**
	 * Mètode que mostra el llibre anterior de la table
	 * 
	 * @author SergioHernandez
	 */
	private void pasarLlibreAnterior() {
		consultaLlibresNoRegistrat.anteriorRowActiu(1);
		if (consultaLlibresNoRegistrat.getRowActiu() >= 0) {
			getDadesRow(consultaLlibresNoRegistrat.getRowActiu());
		} else {
			consultaLlibresNoRegistrat.setRowActiu(0);
		}
	}

	/**
	 * Mètode que mostra les dades d'un llibre si está seleccionat
	 * 
	 * @author SergioHernandez
	 */
	public void mostrarDadesLlibres() {
		int rowAMostrar = consultaLlibresNoRegistrat.getLlibresTable().getSelectedRow();
		if (rowAMostrar > -1) {
			consultaLlibresNoRegistrat.setRowActiu(consultaLlibresNoRegistrat.getLlibresTable().getSelectedRow());
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
		consultaLlibresNoRegistrat.getRowActualField()
				.setText(String.valueOf(consultaLlibresNoRegistrat.getRowActiu() + 1));
		consultaLlibresNoRegistrat.getRowTotalsField().setText(String.valueOf(table.getRowCount()));
		consultaLlibresNoRegistrat.getLlibresTable().setRowSelectionInterval(row, row);
		consultaLlibresNoRegistrat.getIdLlibreField().setText(getValorCella(row, 0));
		consultaLlibresNoRegistrat.getIsbnField().setText(getValorCella(row, 1));
		consultaLlibresNoRegistrat.getTitolField().setText(getValorCella(row, 2));
		consultaLlibresNoRegistrat.getGenereField().setText(getValorCella(row, 3));
		consultaLlibresNoRegistrat.getAutorsField().setText(getValorCella(row, 4));
		consultaLlibresNoRegistrat.getDataPublicacioField().setText(getValorCella(row, 5));
		consultaLlibresNoRegistrat.getEdicioField().setText(getValorCella(row, 6));
		consultaLlibresNoRegistrat.getEditorialField().setText(getValorCella(row, 7));
		consultaLlibresNoRegistrat.getSinopsisTextArea().setText(getValorCella(row, 8));
		consultaLlibresNoRegistrat.getPuntuacioField().setText(getValorCella(row, 9));
		consultaLlibresNoRegistrat.getNumeroPaginesField().setText(getValorCella(row, 10));

		if (getValorCella(row, 11).equals("true")) {
			consultaLlibresNoRegistrat.getReservatCheckBox().setSelected(true);
		} else {
			consultaLlibresNoRegistrat.getReservatCheckBox().setSelected(false);
		}
		consultaLlibresNoRegistrat.getNumeroReservesField().setText(getValorCella(row, 12));

	}

	/**
	 * Mètode que convertiex el valor d'una cel.la a String
	 * 
	 * @param row    fila de la table
	 * @param column columna de la table
	 * @return el valor de la cel.la en format String
	 * @author SergioHernandez
	 */
	private String getValorCella(int row, int column) {
		return table.getValueAt(row, column).toString();
	}

	/**
	 * Mètode que envia les dades per fer la cerca
	 * 
	 * @author SergioHernandez
	 */
	public void ferLaCerca() {
		String textDeLaCerca = consultaLlibresNoRegistrat.getCercaField().getText();
		String filtre = getFiltre();
		if (textDeLaCerca.length() == 0) {
			errorCampCercaBuit();
		} else {
			

			// S'envia l'string de cerca al servidor
			//mostraLaCerca("Filtre: " + filtre + "\n" + "Text a cercar: " + textDeLaCerca + "\n"
					//+ "Valors enviats al servidor: " + usuariConnectat.getIdSessio() + "," + "consulta_llibre_" + filtre
					//+ "," + textDeLaCerca);
			
			//Per simular la consulta al servidor


			consultaLlibresNoRegistrat.llistarLlibresConsulta(filtre, textDeLaCerca);
			
//			if(dadesDelServidor[0].equals("0")) {
//				mostraLaCerca("llibre trobat");
//			}else {
//				mostraLaCerca("llibre no trobat");
//			}
			
		}

	}

	/**
	 * Missatge que mostra la consulta que s'envia al servidor
	 * 
	 * @param cerca consulta
	 * @author SergioHernandez
	 */
	public void mostraLaCerca(String cerca) {
		JOptionPane.showMessageDialog(consultaLlibresNoRegistrat, cerca, "Dades de la cerca",
				JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * Mètode que esborra el camp cerca
	 * 
	 * @author SergioHernandez
	 */
	public void esborrarCampCerca() {
		consultaLlibresNoRegistrat.getCercaField().setText("");
		consultaLlibresNoRegistrat.getCercaField().requestFocus();

	}

	/**
	 * Mètode que retorna el filtre per el qual es vol fer la cerca
	 * 
	 * @return el filtre seleccionat en format String
	 * @author SergioHernandez
	 */
	public String getFiltre() {
		String filtre = null;
		ButtonGroup grup = consultaLlibresNoRegistrat.getFiltreButtonGroup();
		if (grup.getSelection().equals(consultaLlibresNoRegistrat.getTitolRadioButton().getModel())) {
			filtre = "titol";
		}
		if (grup.getSelection().equals(consultaLlibresNoRegistrat.getAutorRadioButton().getModel())) {
			filtre = "autor";
		}
		if (grup.getSelection().equals(consultaLlibresNoRegistrat.getGenereRadioButton().getModel())) {
			filtre = "genere";
		}
		if (grup.getSelection().equals(consultaLlibresNoRegistrat.getEditorialRadioButton().getModel())) {
			filtre = "editorial";
		}
		return filtre;
	}

	/**
	 * Missatge d'error per avisar que el camp de cerca és buit
	 * 
	 * @author SergioHernandez
	 */
	private void errorCampCercaBuit() {
		JOptionPane.showMessageDialog(consultaLlibresNoRegistrat,
				WarningStrings.getString("ConsultaLlibresNoRegistrat.missatgeCampCercaBuit"),
				WarningStrings.getString("ConsultaLlibresNoRegistrat.titolMissatgeCampCercaBuit"),
				JOptionPane.ERROR_MESSAGE);

	}

	/**
	 * Missatge d'error per avisar es vol fer una acció que requereix la selecció
	 * d'un llibre a la table
	 * 
	 * @author SergioHernandez
	 */
	private void errorLlibreNoSeleccionat() {
		JOptionPane.showMessageDialog(consultaLlibresNoRegistrat, "No s'ha seleccionat cap llibre",
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
