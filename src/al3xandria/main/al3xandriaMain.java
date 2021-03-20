package al3xandria.main;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.UIManager;

import al3xandria.controlador.interficie.ControladorCentralPanel;
import al3xandria.controlador.interficie.ControladorFootPanel;
import al3xandria.controlador.interficie.ControladorHeadPanel;
import al3xandria.controlador.interficie.ControladorInterficie;
import al3xandria.vista.centralPanel.CentralPanel;
import al3xandria.vista.footPanel.FootPanel;
import al3xandria.vista.headPanel.HeadPanel;
import al3xandria.vista.principal.PrincipalFrame;

public class al3xandriaMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		

		try{
			 
			  JFrame.setDefaultLookAndFeelDecorated(true);
			  JDialog.setDefaultLookAndFeelDecorated(true);
			  UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
			  //UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
			  //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			  //UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
		
			}
			catch (Exception e)
			 {
			  e.printStackTrace();
			 }
	
	
		
		HeadPanel headPanel = new HeadPanel();
		FootPanel footPanel = new FootPanel();
		CentralPanel centralPanel = new CentralPanel();
		PrincipalFrame framePrincipal = new PrincipalFrame();
		ControladorInterficie controladorInterficie = new ControladorInterficie(framePrincipal, headPanel, centralPanel, footPanel);
		ControladorHeadPanel controladorHeadPanel = new ControladorHeadPanel(headPanel, footPanel);
		ControladorFootPanel controladorFootPanel = new ControladorFootPanel(footPanel);
		
		ControladorCentralPanel controladorCentralPanel = new ControladorCentralPanel(centralPanel);
		

		controladorInterficie.crearPrincipalFrame();
		framePrincipal.setVisible(true);
	}
	
	

}
