package al3xandria.main;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.UIManager;

import al3xandria.model.Usuari;
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
		
			

		CentralPanel centralPanel = new CentralPanel();
		FootPanel footPanel = new FootPanel();
		HeadPanel headPanel = new HeadPanel(footPanel, centralPanel);
		
		PrincipalFrame framePrincipal = new PrincipalFrame();

		
		
		framePrincipal.setVisible(true);
	}

}
