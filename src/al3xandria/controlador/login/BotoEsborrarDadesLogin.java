package al3xandria.controlador.login;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import al3xandria.vista.headPanel.HeadPanel;


public class BotoEsborrarDadesLogin implements MouseListener{
	
	private HeadPanel headPanel;
	
	public BotoEsborrarDadesLogin(HeadPanel view) {
		this.headPanel =  view;
	}

	
	@Override
	public void mouseClicked(MouseEvent e) {
		headPanel.getEmailintroduitPerLusuari().setText("");
		headPanel.getContrasenyaIntroduidaPerLusuari().setText("");
		headPanel.getEmailintroduitPerLusuari().requestFocus();
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}
	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	
	
	

}
