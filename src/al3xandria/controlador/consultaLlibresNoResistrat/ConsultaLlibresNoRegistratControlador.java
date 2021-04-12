package al3xandria.controlador.consultaLlibresNoResistrat;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import al3xandria.strings.WarningStrings;
import al3xandria.vista.centralPanel.ConsultaLlibresNoRegistrat;

public class ConsultaLlibresNoRegistratControlador implements MouseListener{
	
	private ConsultaLlibresNoRegistrat consultaLlibresNoRegistrat;
	private JTable table;
	
	
	
	public ConsultaLlibresNoRegistratControlador(ConsultaLlibresNoRegistrat consultaLlibresNoRegistrat) {
		this.consultaLlibresNoRegistrat = consultaLlibresNoRegistrat;
		this.table = consultaLlibresNoRegistrat.getLlibresTable();
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		if(consultaLlibresNoRegistrat.getCercarButton() == e.getSource()) {
			ferLaCerca();
		}
		
		if(consultaLlibresNoRegistrat.getMostrarLlibreButton() == e.getSource()) {
			consultaLlibresNoRegistrat.getPaginadorPanel().setVisible(true);
			mostrarDadesLlibres();
		}
		
		if(consultaLlibresNoRegistrat.getEsborrarLabel() == e.getSource()) {
			esborrarCampCerca();
		}
		
		if(consultaLlibresNoRegistrat.getLupaLabel() == e.getSource()) {
			ferLaCerca();
		}
		
		if(consultaLlibresNoRegistrat.getSeguentIconLabel() == e.getSource()) {
			pasarLlibreSeguent();
		}
		
		if(consultaLlibresNoRegistrat.getAnteriorIconLabel() == e.getSource()) {
			pasarLlibreAnterior();
		}
		
	}

	private void pasarLlibreSeguent() {
		if(consultaLlibresNoRegistrat.getRowActiu() + 1 == table.getRowCount()) {
			System.out.println(consultaLlibresNoRegistrat.getRowActiu());
			consultaLlibresNoRegistrat.setRowActiu(table.getRowCount()-1);
		}else {
			consultaLlibresNoRegistrat.seguentRowActiu(1, table.getRowCount());
			getDadesRow(consultaLlibresNoRegistrat.getRowActiu());
			
		}
		
	}
	
	private void pasarLlibreAnterior() {
		consultaLlibresNoRegistrat.anteriorRowActiu(1, table.getRowCount());
		if(consultaLlibresNoRegistrat.getRowActiu() >= 0) {
			getDadesRow(consultaLlibresNoRegistrat.getRowActiu());
		}else {
			consultaLlibresNoRegistrat.setRowActiu(0);
		}
	}

	private void mostrarDadesLlibres() {
		int rowAMostrar = consultaLlibresNoRegistrat.getLlibresTable().getSelectedRow();
		consultaLlibresNoRegistrat.setRowActiu(consultaLlibresNoRegistrat.getLlibresTable().getSelectedRow());
		getDadesRow(rowAMostrar);

	}
	
	private void getDadesRow(int row) {
		System.out.println("row actiu" + consultaLlibresNoRegistrat.getRowActiu());
		consultaLlibresNoRegistrat.getRowActualField().setText(String.valueOf(consultaLlibresNoRegistrat.getRowActiu() + 1));
		consultaLlibresNoRegistrat.getRowTotalsField().setText(String.valueOf(table.getRowCount()));
		consultaLlibresNoRegistrat.getLlibresTable().setRowSelectionInterval(1, consultaLlibresNoRegistrat.getRowActiu());
		consultaLlibresNoRegistrat.getIdLlibreField().setText(getValorCella( row, 0));
		consultaLlibresNoRegistrat.getIsbnField().setText(getValorCella( row, 1));
		consultaLlibresNoRegistrat.getTitolField().setText(getValorCella( row, 2));
		consultaLlibresNoRegistrat.getGenereField().setText(getValorCella( row, 3));
		consultaLlibresNoRegistrat.getAutorsField().setText(getValorCella( row, 4));
		consultaLlibresNoRegistrat.getDataPublicacioField().setText(getValorCella( row, 5));
		consultaLlibresNoRegistrat.getEdicioField().setText(getValorCella( row, 6));
		consultaLlibresNoRegistrat.getEditorialField().setText(getValorCella( row, 7));
		consultaLlibresNoRegistrat.getSinopsisTextArea().setText(getValorCella( row, 8));
		consultaLlibresNoRegistrat.getPuntuacioField().setText(getValorCella( row, 9));
		consultaLlibresNoRegistrat.getNumeroPaginesField().setText(getValorCella( row, 10));
		if(getValorCella(row, 11).equals("true")) {
			consultaLlibresNoRegistrat.getReservatCheckBox().setSelected(true);
		}else {
			consultaLlibresNoRegistrat.getReservatCheckBox().setSelected(false);
		}
		consultaLlibresNoRegistrat.getNumeroReservesField().setText(getValorCella( row, 12));
		
	}

	private String getValorCella(int row, int column) {
		return table.getValueAt(row, column).toString();
	}

	private void ferLaCerca() {
		String textDeLaCerca = consultaLlibresNoRegistrat.getCercaField().getText();
		String filtre = getFiltre();
		if(textDeLaCerca.length() == 0) {
			errorCampCercaBuit();
		}else {
			System.out.println("cercar: " + textDeLaCerca + "--" + filtre);
		}
		
	}

	private void esborrarCampCerca() {
		consultaLlibresNoRegistrat.getCercaField().setText("");
		consultaLlibresNoRegistrat.getCercaField().requestFocus();
		
	}

	private String getFiltre() {
		String filtre = null;
		ButtonGroup grup = consultaLlibresNoRegistrat.getFiltreButtonGroup();
		if(grup.getSelection().equals(consultaLlibresNoRegistrat.getTitolRadioButton().getModel())) {
			filtre = "titol";
		}
		if(grup.getSelection().equals(consultaLlibresNoRegistrat.getAutorRadioButton().getModel())) {
			filtre = "autor";
		}
		if(grup.getSelection().equals(consultaLlibresNoRegistrat.getGenereRadioButton().getModel())) {
			filtre = "genere";
		}
		if(grup.getSelection().equals(consultaLlibresNoRegistrat.getEditorialRadioButton().getModel())) {
			filtre = "editorial";
		}
		return filtre;
	}

	private void errorCampCercaBuit() {
		JOptionPane.showMessageDialog(consultaLlibresNoRegistrat,
				WarningStrings.getString("ConsultaLlibresNoRegistrat.missatgeCampCercaBuit"),
				WarningStrings.getString("ConsultaLlibresNoRegistrat.titolMissatgeCampCercaBuit"),
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
	
	/*-------------------------- Getters and Setters Methods --------------------------*/
	

}
