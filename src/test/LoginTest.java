package test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import al3xandria.model.EnviarLoginServer;

class LoginTest {
	
	EnviarLoginServer enviarLogin;

	@Test
	void testEnviamentLoginCorrecteUser() {
		enviarLogin = new EnviarLoginServer("login@user.com");
		String[] retorn = enviarLogin.getDadesDelServidor();
		System.out.println();
		assertEquals("0", retorn[0]);
		assertEquals("false", retorn[2]);
	}
	
	@Test
	void testEnviamentLoginCorrecteAdministrador() {
		enviarLogin = new EnviarLoginServer("login@admin.com");
		String[] retorn = enviarLogin.getDadesDelServidor();
		assertEquals("0", retorn[0]);
		assertEquals("true", retorn[2]);
	}

	@Test
	void testEnviamentLoginIncorrecte() {
		enviarLogin = new EnviarLoginServer("dflksjdf");
		String[] retorn = enviarLogin.getDadesDelServidor();
		assertEquals("400", retorn[0]);
	}
	
	@Test
	void testEnviamentLoginEnBlanc() {
		enviarLogin = new EnviarLoginServer("");
		String[] retorn = enviarLogin.getDadesDelServidor();
		assertEquals("400", retorn[0]);
	}

}
