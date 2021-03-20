package al3xandria.controlador.interficie;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import al3xandria.vista.centralPanel.CentralPanel;
import al3xandria.vista.footPanel.FootPanel;
import al3xandria.vista.headPanel.HeadPanel;
import al3xandria.vista.principal.PrincipalFrame;

public class ControladorInterficie implements ActionListener{

	private CentralPanel centralPanel;
	private FootPanel footPanel;
	private HeadPanel headPanel;
	private PrincipalFrame principalFrame;
	
	public ControladorInterficie(PrincipalFrame principalFrame, HeadPanel headPanel, CentralPanel centralPanel, FootPanel footPanel) {
		this.principalFrame = principalFrame;
		this.headPanel = headPanel;
		this.centralPanel = centralPanel;
		this.footPanel = footPanel;
	}
	
	public void setCentralPanel(CentralPanel centralPanel) {
		this.centralPanel = centralPanel;
	}
	
	public void setFootPanel(FootPanel footPanel) {
		this.footPanel = footPanel;
	}
	
	public void setHeadPanel(HeadPanel headPanel) {
		this.headPanel = headPanel;
	}
	
	public void setPrincipalFrame(PrincipalFrame principalFrame) {
		this.principalFrame = principalFrame;
	}
	
	
	public void crearPrincipalFrame() {
		principalFrame.add(centralPanel);
		principalFrame.add(footPanel);
		principalFrame.add(headPanel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
