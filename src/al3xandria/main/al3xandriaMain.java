package al3xandria.main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.sql.SQLException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.UIManager;

import al3xandria.model.ComunicacioClientServidor;
import al3xandria.server.ControladorServidor;
import al3xandria.server.ModelServidor;
import al3xandria.vista.principal.PrincipalFrame;

/**
 * Clase per inicialitzar el client
 * Primer s'ha de inicialitzar el servidor: /Al3xandria/src/al3xandria/mockserver/MockSocketsServerMain.java
 * 
 * @author SergioHernandez
 *
 */
public class al3xandriaMain {

	public static void main(String[] args) throws InvalidKeyException, UnrecoverableKeyException, KeyManagementException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, KeyStoreException, FileNotFoundException, CertificateException, SQLException, IOException {

		try {

			// Utilizo els Looks and Feel per canviar l'aparença de l'aplicació
			//JFrame.setDefaultLookAndFeelDecorated(true);
			//JDialog.setDefaultLookAndFeelDecorated(true);
			//UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
			//UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
			//UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			//UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");

		} catch (Exception e) {
			e.printStackTrace();
		}
		  
		mostraCartellAplicacioIniciada();
		PrincipalFrame principalFrame = new PrincipalFrame();
		principalFrame.setVisible(true);
		
	}
	
	//creat per fer el video
	public static void mostraCartellAplicacioIniciada() {
		System.out.println("--------------------------------------------");
		System.out.println("--------------AL3XANDRIA--------------------");
		System.out.println("----------Aplicació Iniciada----------------");
		System.out.println("--------------------------------------------");
	}

}
