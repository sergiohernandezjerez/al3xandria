package al3xandria.controlador.login;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import al3xandria.vista.headPanel.HeadPanel;


public class BotoCancelLogin implements MouseListener{
	
	private HeadPanel headPanel;
	
	public BotoCancelLogin(HeadPanel view) {
		this.headPanel =  view;
	}

	
	@Override
	public void mouseClicked(MouseEvent e) {
		headPanel.getEmailintroduitPerLusuari().setText("");
		headPanel.getContrasenyaLoginLabel().setText("");
		
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
