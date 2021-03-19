package al3xandria.main;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.UIManager;

import al3xandria.controlador.login.Controller;
import al3xandria.model.Usuari;
import al3xandria.model.conexioDB.ConsultasBD;
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
		Usuari usuari = new Usuari();
			
		
		Controller controller = new Controller();
		CentralPanel centralPanel = new CentralPanel();
		FootPanel footPanel = new FootPanel();
		HeadPanel headPanel = new HeadPanel(footPanel, centralPanel);
		
		PrincipalFrame framePrincipal = new PrincipalFrame();

		
		
		usuari.setController(controller);
		centralPanel.setController(controller);
		framePrincipal.setController(controller);
		footPanel.setController(controller);
		headPanel.setController(controller);
		
		controller.setFramePrincipal(framePrincipal);
		controller.setCentralPanel(centralPanel);
		controller.setFootPanel(footPanel);
		controller.setHeadPanel(headPanel);
		controller.setUsuariPanel(usuari);
		
		controller.addHeadPanel();
		controller.addCentralPanel();
		controller.addFootPanel();
		
		
		framePrincipal.setVisible(true);
	}
	
	

}
