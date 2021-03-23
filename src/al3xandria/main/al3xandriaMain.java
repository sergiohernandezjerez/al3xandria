package al3xandria.main;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.UIManager;

import al3xandria.vista.principal.PrincipalFrame;

/**
 * Clase per inicialitzar el client
 * 
 * @author SergioHernandez
 *
 */
public class al3xandriaMain {

	public static void main(String[] args) {

		try {

			// Utilizo els Looks and Feel per canviar l'aparença de l'aplicació
			JFrame.setDefaultLookAndFeelDecorated(true);
			JDialog.setDefaultLookAndFeelDecorated(true);
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
			// UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
			// UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			// UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");

		} catch (Exception e) {
			e.printStackTrace();
		}

	
		PrincipalFrame principalFrame = new PrincipalFrame();
		principalFrame.setVisible(true);
	}

}
