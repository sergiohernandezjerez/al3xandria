package al3xandria.controlador.login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import al3xandria.vista.headPanel.HeadPanel;

public class HeadPanelControlador implements ActionListener, MouseListener{

	private HeadPanel headPanel;
	
	public HeadPanelControlador(HeadPanel headPanel) {
		this.headPanel = headPanel;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if(headPanel.getEsborrarDadesLoginLabel() == e.getSource()) {
			headPanel.getEmailintroduitPerLusuari().setText("");
			headPanel.getContrasenyaIntroduidaPerLusuari().setText("");
			headPanel.getEmailintroduitPerLusuari().requestFocus();
		}
		
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
if(headPanel.getMostrarContrasenya() == e.getSource()) {
	headPanel.getContrasenyaIntroduidaPerLusuari().setEchoChar((char)0);
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
if(headPanel.getMostrarContrasenya() == e.getSource()) {
	headPanel.getContrasenyaIntroduidaPerLusuari().setEchoChar('*');
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
