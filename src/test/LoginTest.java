package test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import al3xandria.model.ControlDeDades;
import al3xandria.model.ComunicacioClientServidor;

class LoginTest {
	
	ComunicacioClientServidor enviarLogin;
	ControlDeDades controlDeDades;

	@Test
	void testEnviamentLoginCorrecteUsuari() {
		enviarLogin = new ComunicacioClientServidor("login,pere@pere.com,pere");
		String[] retorn = enviarLogin.getDadesDelServidor();
		assertEquals("0", retorn[0]);
		assertEquals("Usuari", retorn[2]);
	}
	
	@Test
	void testEnviamentLoginCorrecteAdministrador() {
		enviarLogin = new ComunicacioClientServidor("login,pilar@pilar.com,pilar");
		String[] retorn = enviarLogin.getDadesDelServidor();
		assertEquals("0", retorn[0]);
		assertEquals("Administrador", retorn[2]);
	}
	
	@Test
	void testEnviamentLogoutCorrecteUsuari() {
		enviarLogin = new ComunicacioClientServidor("logoutOK,pere@pere.com");
		String[] retorn = enviarLogin.getDadesDelServidor();
		assertEquals("0", retorn[0]);
		assertEquals("Usuari", retorn[2]);
	}

			
	@Test
	void testFormatEmailCorrecte() {
		controlDeDades = new ControlDeDades();
		assertTrue(controlDeDades.comprovacioEmail("ioc@gmail.com"));
	}
	
	@Test
	void testFormatEmailIncorrecteSenseArroba() {
		controlDeDades = new ControlDeDades();
		assertFalse(controlDeDades.comprovacioEmail("iocgmail.com"));
	}
	
	@Test
	void testFormatEmailIncorrecteSensePunt() {
		controlDeDades = new ControlDeDades();
		assertFalse(controlDeDades.comprovacioEmail("ioc@gmailcom"));
	}
	
	@Test
	void testFormatEmailIncorrecteSenseArrobaNiPunt() {
		controlDeDades = new ControlDeDades();
		assertFalse(controlDeDades.comprovacioEmail("iocgmailcom"));
	}
	
	
	
	
	
	

}
