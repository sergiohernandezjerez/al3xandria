package test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import al3xandria.model.ControlDeDades;
import al3xandria.model.ComunicacioServer;

class LoginTest {
	
	ComunicacioServer enviarLogin;
	ControlDeDades controlDeDades;

	@Test
	void testEnviamentLoginCorrecteUser() {
		enviarLogin = new ComunicacioServer("login@user.com");
		String[] retorn = enviarLogin.getDadesDelServidor();
		assertEquals("0", retorn[0]);
		assertEquals("false", retorn[2]);
	}
	
	@Test
	void testEnviamentLoginCorrecteAdministrador() {
		enviarLogin = new ComunicacioServer("login@admin.com");
		String[] retorn = enviarLogin.getDadesDelServidor();
		assertEquals("0", retorn[0]);
		assertEquals("true", retorn[2]);
	}

	@Test
	void testEnviamentLoginIncorrecte() {
		enviarLogin = new ComunicacioServer("dflksjdf");
		String[] retorn = enviarLogin.getDadesDelServidor();
		assertEquals("400", retorn[0]);
	}
	
	@Test
	void testEnviamentLoginEnBlanc() {
		enviarLogin = new ComunicacioServer("");
		String[] retorn = enviarLogin.getDadesDelServidor();
		assertEquals("400", retorn[0]);
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
