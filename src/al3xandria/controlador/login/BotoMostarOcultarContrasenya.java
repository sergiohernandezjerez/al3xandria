package al3xandria.controlador.login;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import al3xandria.vista.headPanel.HeadPanel;

public class BotoMostarOcultarContrasenya implements MouseListener {

	private HeadPanel headPanel;
	
	public BotoMostarOcultarContrasenya(HeadPanel view) {
		this.headPanel =  view;
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
	
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
		headPanel.getContrasenyaIntroduidaPerLusuari().setEchoChar((char)0);

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		headPanel.getContrasenyaIntroduidaPerLusuari().setEchoChar('*');

	}

}
