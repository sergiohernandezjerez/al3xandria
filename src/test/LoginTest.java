package test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import al3xandria.model.ControlDeDades;
import al3xandria.controlador.login.BotoLoginLogout;
import al3xandria.mockserver.MockSocketsServerMain;
import al3xandria.model.ComunicacioClientServidor;

class LoginTest {
	
	ComunicacioClientServidor comunicacioClientServidor = new ComunicacioClientServidor();
	ControlDeDades controlDeDades;
	String[] retorn;
	
	

	//prova a fer login com un administrador
	@Test
	void testEnviamentLoginCorrecteAdministrador() {
		comunicacioClientServidor.iniciarComunicacio("login,pilar@pilar.com,pilar");
		retorn = comunicacioClientServidor.getDadesDelServidor();
		assertEquals("0", retorn[0]);
		assertEquals("Administrador", retorn[2]);
		comunicacioClientServidor.iniciarComunicacio("logoutOK,pilar@pilar.com");
	}
	
	//prova a fer login com a usuari
	@Test
	void testEnviamentLoginCorrecteUsuari() {
		comunicacioClientServidor.iniciarComunicacio("login,pepe@pepe.com,pepe");
		retorn = comunicacioClientServidor.getDadesDelServidor();
		assertEquals("0", retorn[0]);
		assertEquals("Usuari", retorn[2]);
		comunicacioClientServidor.iniciarComunicacio("logoutOK,pepe@pepe.com");
	}
	
	//prova a fer logout amb resultat exitos
	@Test
	void testEnviamentLogoutCorrecte() {
		comunicacioClientServidor.iniciarComunicacio("login,pere@pere.com,pere");
		retorn = comunicacioClientServidor.getDadesDelServidor();
		comunicacioClientServidor.iniciarComunicacio("logout," + retorn[1]);
		retorn = comunicacioClientServidor.getDadesDelServidor();
		assertEquals("0", retorn[0]);
		comunicacioClientServidor.iniciarComunicacio("logoutOK,pere@pere.com");
	}
	
	//prova a fer logout amb resultat no exitos
	@Test
	void testEnviamentLogoutIncorrecte() {
		comunicacioClientServidor.iniciarComunicacio("logout,pere@pere.com");
		retorn = comunicacioClientServidor.getDadesDelServidor();
		assertEquals("440", retorn[0]);
		comunicacioClientServidor.iniciarComunicacio("logoutOK,pere@pere.com");
	}
	
	//prova a fer login amb una contrasenya incorrecte
	@Test
	void testEnviamentLoginContrasenyaIncorrecte() {
		comunicacioClientServidor.iniciarComunicacio("login,pere@pere.com,tres");
		retorn = comunicacioClientServidor.getDadesDelServidor();
		assertEquals("440", retorn[0]);
	}
	
	//prova a fer login amb un email incorrecte
		@Test
		void testEnviamentLoginEmailIncorrecte() {
			comunicacioClientServidor.iniciarComunicacio("login,pedre@pere.com,pere");
			retorn = comunicacioClientServidor.getDadesDelServidor();
			assertEquals("440", retorn[0]);
		}
	
		//prova a fer login sense haver tancat la sessió abans
	@Test
	void testTornaAFerConexioSenseFerLogout() {
		comunicacioClientServidor.iniciarComunicacio("login,pere@pere.com,pere");
		retorn = comunicacioClientServidor.getDadesDelServidor();
		comunicacioClientServidor.iniciarComunicacio("login,pere@pere.com,pere");
		retorn = comunicacioClientServidor.getDadesDelServidor();
		assertEquals("550", retorn[0]);
		comunicacioClientServidor.iniciarComunicacio("logoutOK,pere@pere.com");
	}
	
		
	//comprova si el camp email està buit
	@Test
	void testCampEmailBuit() {
		controlDeDades = new ControlDeDades();
		assertFalse(controlDeDades.comprovarCampsOmplerts("", "contraseenya"));
	}
	
	//comprova si el camp contrasenya està buit
	@Test
	void testCampContrasenyaBuit() {
		controlDeDades = new ControlDeDades();
		assertFalse(controlDeDades.comprovarCampsOmplerts("email@email.com", ""));
	}
	
	//comprova que el format de l'email introduït sigui correcte
	@Test
	void testFormatEmailCorrecte() {
		controlDeDades = new ControlDeDades();
		assertTrue(controlDeDades.comprovacioEmail("ioc@gmail.com"));
	}
	
	//comprova que el format de l'email introduït sigui correcte.
	//en aquest cas falta @
	@Test
	void testFormatEmailIncorrecteSenseArroba() {
		controlDeDades = new ControlDeDades();
		assertFalse(controlDeDades.comprovacioEmail("iocgmail.com"));
	}
	
	//comprova que el format de l'email introduït sigui correcte
	//en aquest cas falta el punt
	@Test
	void testFormatEmailIncorrecteSensePunt() {
		controlDeDades = new ControlDeDades();
		assertFalse(controlDeDades.comprovacioEmail("ioc@gmailcom"));
	}
	
	//comprova que el format de l'email introduït sigui correcte
	//en aquest cas falta @ i el punt
	@Test
	void testFormatEmailIncorrecteSenseArrobaNiPunt() {
		controlDeDades = new ControlDeDades();
		assertFalse(controlDeDades.comprovacioEmail("iocgmailcom"));
	}
	
	
	
	
	
	

}
