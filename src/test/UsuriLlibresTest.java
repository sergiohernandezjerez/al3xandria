package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import al3xandria.controlador.consultaLlibres.ConsultaLlibresNoRegistratControlador;
import al3xandria.controlador.usuaris.AdministradorUsuarisControlador;
import al3xandria.model.ControlDeDades;
import al3xandria.model.objects.Usuari;
import al3xandria.vista.centralPanel.AdministradorUsuaris;
import al3xandria.vista.centralPanel.ConsultaLlibresNoRegistrat;

class UsuriLlibresTest {

	private ControlDeDades controlDeDades = new ControlDeDades();

	// Comprova que el format del Isbn sigui correcte
	// Ha de ser un número de 10 o 13 xifres
	// en aquest cas té 10 xifres
	@Test
	void testComprovacioFormatIsbn10xifres() {
		assertTrue(controlDeDades.comprovacioFormatIsbn("1234567894"));

	}

	// Comprova que el format del Isbn sigui correcte
	// Ha de ser un número de 10 o 13 xifres
	// en aquest cas té 13 xifres
	@Test
	void testComprovacioFormatIsbn13xifres() {
		assertTrue(controlDeDades.comprovacioFormatIsbn("1234567894561"));

	}

	// Comprova que el format del Isbn sigui correcte
	// Ha de ser un número de 10 o 13 xifres
	// en aquest cas té 9 xifres
	@Test
	void testComprovacioFormatIsbn9xifres() {
		assertFalse(controlDeDades.comprovacioFormatIsbn("123456789"));

	}

	// Comprova que el format del Isbn sigui correcte
	// Ha de ser un número de 10 o 13 xifres
	// en aquest cas té 14 xifres
	@Test
	void testComprovacioFormatIsbn14xifres() {
		assertFalse(controlDeDades.comprovacioFormatIsbn("45678912345678"));

	}

	// Comprova que el format del Isbn sigui correcte
	// Ha de ser un número de 10 o 13 xifres
	// en aquest cas té 11 xifres
	@Test
	void testComprovacioFormatIsbn11xifres() {
		assertFalse(controlDeDades.comprovacioFormatIsbn("12345167894"));

	}

	// Comprova que el format del Isbn sigui correcte
	// Ha de ser un número de 10 o 13 xifres
	// en aquest cas no es numeric
	@Test
	void testComprovacioFormatIsbnNoNumeric() {
		assertFalse(controlDeDades.comprovacioFormatIsbn("nonumeric"));

	}

	// Comprova que les dades introduides per donar d'alta un usuari
	// estiguin omplertes i siguin correctes
	// en aquest cas tot està correcta
	@Test
	void testComprovacioCampsOmplertsAltaUsuariTotCorrecte() {
		assertTrue(controlDeDades.comprovarCampsOmplertsAltaUsuari("Sergi", "Hernandez", "Carrer 3", "email@email.com",
				"Montcada", "08110", "Españya", 2, "935684578", "lkjlkj", "52177001Y", "C54", 2));
	}

	// Comprova que les dades introduides per donar d'alta un usuari
	// estiguin omplertes
	// en aquest falta el camp nom
	@Test
	void testComprovacioCampsOmplertsAltaUsuariFaltaUnCamp() {
		assertFalse(controlDeDades.comprovarCampsOmplertsAltaUsuari("", "Hernandez", "Carrer 3", "email@email.com",
				"Montcada", "08110", "Españya", 2, "935684578", "lkjlkj", "52177001Y", "C54", 2));
	}

	// Comprova que les dades introduides per donar d'alta un usuari
	// estiguin omplertes
	// en aquest cas falta el telefon i l'adreça
	@Test
	void testComprovacioCampsOmplertsAltaUsuariFaltenDosCamps() {
		assertFalse(controlDeDades.comprovarCampsOmplertsAltaUsuari("Sergi", "Hernandez", "", "email@email.com",
				"Montcada", "08110", "Españya", 2, "", "lkjlkj", "52177001Y", "C54", 2));
	}

	// Comprova que les dades introduides per donar d'alta un llibre
	// estiguin omplertes
	// en aquest cas tot esta correcte
	@Test
	void testComprovacioCampsOmplertsAltaLlibreTotCorrecte() {
		assertTrue(controlDeDades.comprovarCampsOmplertsAltaLlibre("Java de 0 a professional", 2, 1, 3, "1234567891",
				"Primera", "21-12-2020", "456"));
	}

	// Comprova que les dades introduides per donar d'alta un llibre
	// estiguin omplertes
	// en aquest cas falta l'isbn i el titol
	@Test
	void testComprovacioCampsOmplertsAltaLlibreFaltenDosCamps() {
		assertFalse(controlDeDades.comprovarCampsOmplertsAltaLlibre("", 2, 1, 3, "", "Primera", "21-12-2020", "456"));
	}
	
	
	

}
