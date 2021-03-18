package al3xandria.controlador.footPanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import al3xandria.controlador.login.Controller;
import al3xandria.vista.footPanel.FootPanel;
import al3xandria.vista.headPanel.HeadPanel;

public class EstatUsuariIconLogout implements MouseListener {
	

	private HeadPanel headPanel;
	private FootPanel footPanel;
	private Controller controller;
	
	

	
	public EstatUsuariIconLogout() {
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		System.out.println(headPanel.getEmailintroduitPerLusuari().getText());
		headPanel.getEmailLoginLabel().setText("jjjjjjjjjjjjjj");

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
	
	public void setController(Controller controller) {
		this.controller = controller;
	}

}
