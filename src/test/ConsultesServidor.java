package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import al3xandria.controlador.administradorLlibres.AdministradorLlibresControlador;
import al3xandria.controlador.usuaris.AdministradorUsuarisControlador;
import al3xandria.model.llibres.LlibresModel;
import al3xandria.model.usuaris.UsuarisModel;

class ConsultesServidor {
	
	private AdministradorLlibresControlador administradorLlibresControlador;
	private AdministradorUsuarisControlador administradorUsuarisControlador;
	private LlibresModel llibresModel;
	private UsuarisModel usuarisModel;
	

	@Test
	void testComprovacioFormatIsbn13xifres() {
		assertTrue(administradorLlibresControlador.enviarDadesAltaLlibreAlServidor());
	}

}
