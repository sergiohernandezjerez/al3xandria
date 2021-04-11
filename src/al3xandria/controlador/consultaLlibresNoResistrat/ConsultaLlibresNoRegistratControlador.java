package al3xandria.controlador.consultaLlibresNoResistrat;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;

import al3xandria.strings.WarningStrings;
import al3xandria.vista.centralPanel.ConsultaLlibresNoRegistrat;

public class ConsultaLlibresNoRegistratControlador implements MouseListener{
	
	private ConsultaLlibresNoRegistrat consultaLlibresNoRegistrat;
	
	public ConsultaLlibresNoRegistratControlador(ConsultaLlibresNoRegistrat consultaLlibresNoRegistrat) {
		this.consultaLlibresNoRegistrat = consultaLlibresNoRegistrat;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		if(consultaLlibresNoRegistrat.getCercarButton() == e.getSource()) {
			ferLaCerca();
		}
		
		if(consultaLlibresNoRegistrat.getMostrarLlibreButton() == e.getSource()) {
			System.out.println("mostra");
		}
		
		if(consultaLlibresNoRegistrat.getEsborrarLabel() == e.getSource()) {
			esborrarCampCerca();
		}
		
		if(consultaLlibresNoRegistrat.getLupaLabel() == e.getSource()) {
			ferLaCerca();
		}
		
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
