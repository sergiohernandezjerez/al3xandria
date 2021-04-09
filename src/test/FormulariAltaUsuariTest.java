package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import al3xandria.model.ControlDeDades;

class FormulariAltaUsuariTest {

	private ControlDeDades controlDeDades;
	
	
	//El test de la contrasenya correcta del formulari
	//utilitza el mateix m�tode que la comprovaci� de
	//contrasenya del TEA2 a l'hora de fer login
	

	// comprova si el dni te un format correcte
	@Test
	void testDniCorrecte() {
		controlDeDades = new ControlDeDades();
		assertTrue(controlDeDades.comprovacioValidezaDNI("52177001Y"));
	}

	// comprova si el dni te un format correcte
	// en aquest cas falta la lletra final
	@Test
	void testDniIncorrecteFaltaLletra() {
		controlDeDades = new ControlDeDades();
		assertFalse(controlDeDades.comprovacioValidezaDNI("52177001"));
	}

	// comprova si el dni te un format correcte
	// en aquest cas falta un n�mero
	@Test
	void testDniIncorrecteFaltaUnNumero() {
		controlDeDades = new ControlDeDades();
		assertFalse(controlDeDades.comprovacioValidezaDNI("5217700Y"));
	}

	// comprova si el dni te un format correcte
	// en aquest cas el dni �s inventat
	@Test
	void testDniIncorrecteInventat() {
		controlDeDades = new ControlDeDades();
		assertFalse(controlDeDades.comprovacioValidezaDNI("54175001N"));
	}

	// comprova si el Nie te un format correcte
	@Test
	void testNieCorrecte() {
		controlDeDades = new ControlDeDades();
		assertTrue(controlDeDades.comprovacioValidezaNIE("Y8237411K"));
	}

	// comprova si el Nie te un format correcte
	// en aquest cas falta la lletra final
	@Test
	void testNieIncorrecteFaltaLletraFinal() {
		controlDeDades = new ControlDeDades();
		assertFalse(controlDeDades.comprovacioValidezaNIE("Y8237411"));
	}

	// comprova si el Nie te un format correcte
	// en aquest cas falta la lletra inicial
	@Test
	void testNieIncorrecteFaltaLletraInicial() {
		controlDeDades = new ControlDeDades();
		assertFalse(controlDeDades.comprovacioValidezaNIE("8237411K"));
	}

	// comprova si el Nie te un format correcte
	// es aquest cas falta un n�mero
	@Test
	void testNieIncorrecteFaltaUnNumero() {
		controlDeDades = new ControlDeDades();
		assertFalse(controlDeDades.comprovacioValidezaNIE("Y827411K"));
	}

	// comprova si el Nie te un format correcte
	// en aquest cas el nie �s inventat
	@Test
	void testNieIncorrecteInventat() {
		controlDeDades = new ControlDeDades();
		assertFalse(controlDeDades.comprovacioValidezaNIE("C847611N"));
	}

	// comprova si el tel�fon es correcte
	@Test
	void testTelefonCorrecte() {
		controlDeDades = new ControlDeDades();
		assertTrue(controlDeDades.comprovacioFormatTelefon("456789123"));
	}

	// comprova si el tel�fon es correcte
	// en aquest cas falta un n�mero
	@Test
	void testTelefonIncorrecteFaltaNumero() {
		controlDeDades = new ControlDeDades();
		assertFalse(controlDeDades.comprovacioFormatTelefon("46789123"));
	}

	// comprova si el tel�fon es correcte
	// en aquest cas sobra un n�mero
	@Test
	void testTelefonIncorrecteSobraNumero() {
		controlDeDades = new ControlDeDades();
		assertFalse(controlDeDades.comprovacioFormatTelefon("4563789123"));
	}

	// comprova si el tel�fon es correcte
	// en aquest substituim un n�mero per una lletra
	@Test
	void testTelefonIncorrecteUnaLletra() {
		controlDeDades = new ControlDeDades();
		assertFalse(controlDeDades.comprovacioFormatTelefon("456I23789"));
	}

	// comprova si el tel�fon es correcte
	// en aquest substituim s�n car�cters
	@Test
	void testTelefonIncorrecteSonCaracters() {
		controlDeDades = new ControlDeDades();
		assertFalse(controlDeDades.comprovacioFormatTelefon("hyubjnght"));
	}

	// comprova si les contrasenyes s�n correctes
	@Test
	void testContrasenyaCorrecta() {
		controlDeDades = new ControlDeDades();
		assertTrue(controlDeDades.comprovarContrasenyaFormulariAltaUsuari("rt64hjfdkj", "rt64hjfdkj"));
	}

	// comprova si les contrasenyes s�n correctes
	// en aquest cas les contrasenyes s�n diferents
	@Test
	void testContrasenyaIncorrectaSonDiferents() {
		controlDeDades = new ControlDeDades();
		assertFalse(controlDeDades.comprovarContrasenyaFormulariAltaUsuari("rt64hjfdkj", "rt64jfdkj"));
	}

	// comprova si les contrasenyes s�n correctes
	// en aquest cas s�n iguals per la longitud �s inferior a 8 digits
	@Test
	void testContrasenyaIncorrectaMidaInferior() {
		controlDeDades = new ControlDeDades();
		assertFalse(controlDeDades.comprovarContrasenyaFormulariAltaUsuari("rt4567", "rt4567"));
	}
	
	//comprova si els camps estan buits i no s'ha seleccionat
	// res als combobox
	@Test
	void testComprovaci�CampsBuits() {
		controlDeDades = new ControlDeDades();
		assertFalse(controlDeDades.comprovarCampsOmplertsFormulariAltaUsuari(
				"",
				"", 
				"", 
				"", 
				"", 
				"", 
				"", 
				0, 
				"", 
				"", 
				0,
				0,
				""));
	}
	
	//comprova si els camps estan omplerts i s'han seleccionat
		//els combobox
		@Test
		void testComprovaci�CampsOmplerts() {
			controlDeDades = new ControlDeDades();
			assertTrue(controlDeDades.comprovarCampsOmplertsFormulariAltaUsuari(
					"Sergio",
					"Hern�ndez", 
					"Sant Lluis, 43 4-1", 
					"sergio@sergio.com", 
					"Sant Pere", 
					"08110", 
					"Espanya", 
					1, 
					"456789123", 
					"52177001Y", 
					1,
					1,
					"rt4567898"));
		}

}
