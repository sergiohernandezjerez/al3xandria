package test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import al3xandria.model.ComunicacioClientServidor;

class ConsultesServidor {

	private ComunicacioClientServidor comunicacioClientServidor = new ComunicacioClientServidor();

	// inserta un autor nou a la base de dades
	@Test
	void testComprovacioInsertarAutorNou() {
		comunicacioClientServidor.iniciarComunicacio(
				"0000000000000,insercio_autor,Nou Autor Test");
		String resposta = comunicacioClientServidor.getData();
		assertEquals("1", resposta);
	}

	// inserta un autor que ja existeix a la base de dades
	@Test
	void testComprovacioInsertarAutorExistent() {
		comunicacioClientServidor.iniciarComunicacio(
				"0000000000000,insercio_autor,Sergio Hernández");
		String resposta = comunicacioClientServidor.getData();
		assertEquals("0", resposta);
	}

	// inserta un genere nou a la base de dades
	@Test
	void testComprovacioInsertarGenereNou() {
		comunicacioClientServidor.iniciarComunicacio(
				"0000000000000,insercio_genere,Nou Genere Test");
		String resposta = comunicacioClientServidor.getData();
		assertEquals("1", resposta);
	}

	// inserta un genere que ja existeix a la base de dades
	@Test
	void testComprovacioInsertarGenereExistent() {
		comunicacioClientServidor
				.iniciarComunicacio("0000000000000,insercio_genere,Java");
		String resposta = comunicacioClientServidor.getData();
		assertEquals("0", resposta);
	}

	// inserta una editorial nova a la base de dades
	@Test
	void testComprovacioInsertaEditorialNova() {
		comunicacioClientServidor.iniciarComunicacio(
				"0000000000000,insercio_editorial,Nova Editorial Test");
		String resposta = comunicacioClientServidor.getData();
		assertEquals("1", resposta);
	}

	// inserta una editorial que ja existeix a la base de dades
	@Test
	void testComprovacioInsertarEditorialExistent() {
		comunicacioClientServidor
				.iniciarComunicacio("0000000000000,insercio_editorial,Rama");
		String resposta = comunicacioClientServidor.getData();
		assertEquals("0", resposta);
	}

	// Consulta un llibre per una editorial
	// En aquest cas l'editorial existeix
	@Test
	void testComprovacioConsultaLlibrePerEditorialExisteix() {
		comunicacioClientServidor.iniciarComunicacio(
				"0000000000000,consulta_llibre_editorial,Rama");
		String resposta = comunicacioClientServidor.getData();
		assertTrue(resposta.length() > 0);
	}

	// Consulta un llibre per una editorial
	// En aquest cas l'editorial no existeix
	@Test
	void testComprovacioConsultaLlibrePerEditorialNoExisteix() {
		comunicacioClientServidor.iniciarComunicacio(
				"0000000000000,consulta_llibre_editorial,Editorial que no existeix");
		String resposta = comunicacioClientServidor.getData();
		assertTrue(resposta.equals("null"));
	}

	// Consulta un llibre per un autor
	// En aquest cas l'autor existeix
	@Test
	void testComprovacioConsultaLlibrePerAutorExisteix() {
		comunicacioClientServidor.iniciarComunicacio(
				"0000000000000,consulta_llibre_autor,Richard Deal");
		String resposta = comunicacioClientServidor.getData();
		assertTrue(resposta.length() > 0);
	}

	// Consulta un llibre per una editorial
	// En aquest cas l'autor no existeix
	@Test
	void testComprovacioConsultaLlibrePerAutorNoExisteix() {
		comunicacioClientServidor.iniciarComunicacio(
				"0000000000000,consulta_llibre_autor,Autor que no existeix");
		String resposta = comunicacioClientServidor.getData();
		assertTrue(resposta.equals("null"));
	}

	// Consulta un llibre per un genere
	// En aquest cas el genere existeix
	@Test
	void testComprovacioConsultaLlibrePerGenereExisteix() {
		comunicacioClientServidor
				.iniciarComunicacio("0000000000000,consulta_llibre_genere,SQL");
		String resposta = comunicacioClientServidor.getData();
		assertTrue(resposta.length() > 0);
	}

	// Consulta un llibre per una genere
	// En aquest cas el genere no existeix
	@Test
	void testComprovacioConsultaLlibrePerGenereNoExisteix() {
		comunicacioClientServidor.iniciarComunicacio(
				"0000000000000,consulta_llibre_genere,Genere que no existeix");
		String resposta = comunicacioClientServidor.getData();
		assertTrue(resposta.equals("null"));
	}

	// Consulta un llibre per un titol
	// En aquest cas el titol existeix
	@Test
	void testComprovacioConsultaLlibrePerTitolExisteix() {
		comunicacioClientServidor
				.iniciarComunicacio("0000000000000,consulta_llibre_titol,Java");
		String resposta = comunicacioClientServidor.getData();
		assertTrue(resposta.length() > 0);
	}

	// Consulta un llibre per un titol
	// En aquest cas el titol no existeix
	@Test
	void testComprovacioConsultaLlibrePerTitolNoExisteix() {
		comunicacioClientServidor.iniciarComunicacio(
				"0000000000000,consulta_llibre_titol,Titol que no existeix");
		String resposta = comunicacioClientServidor.getData();
		assertTrue(resposta.equals("null"));
	}

	// Consulta els prestecs d'un usuari
	// En aquest cas si en té de prestecs
	@Test
	void testComprovacioPrestecsUsuariQueSiEnTe() {
		comunicacioClientServidor.iniciarComunicacio(
				"cS1620585775837,consulta_prestecs_usuari,3");
		String resposta = comunicacioClientServidor.getData();
		assertTrue(resposta.length() > 0);
	}

	// Consulta els prestecs d'un usuari
	// En aquest cas no en té de prestecs
	@Test
	void testComprovacioPrestecsUsuariQueNoEnTe() {
		comunicacioClientServidor.iniciarComunicacio(
				"cS1620585775837,consulta_prestecs_usuari,12");
		String resposta = comunicacioClientServidor.getData();
		assertTrue(resposta.equals("null"));
	}

	// Esborra un usuari de la base de dades
	// En aquest cas l'usuari existeix
	@Test
	void testComprovacioEsborrarUsuariExistent() {
		comunicacioClientServidor
				.iniciarComunicacio("cS1620585775837,borrar_usuari,5032");
		String resposta = comunicacioClientServidor.getData();
		assertTrue(resposta.equals("1"));
	}
	// Afegeix un llibre de la base de dades
		@Test
		void testComprovacioAfegeixLlibre() {
			comunicacioClientServidor.iniciarComunicacio(
					"cS1620585775837,insercio_llibre,Llibre nou,false,Máximo Núñez Alarcón,C++,Rama,999,1111111111,Edicio,2020-01-01,111,,sinopsis");
			String resposta = comunicacioClientServidor.getData();
			assertTrue(resposta.equals("1"));
		}

	// Esborra un usuari de la base de dades
	// En aquest cas l'usuari no existeix
	@Test
	void testComprovacioEsborrarUsuariNoExistent() {
		comunicacioClientServidor
				.iniciarComunicacio("cS1620585775837,borrar_usuari,6015");
		String resposta = comunicacioClientServidor.getData();
		assertTrue(resposta.equals("0"));
	}

	// Afegeix un usuari de la base de dades
	@Test
	void testComprovacioAfegeixUsuari() {
		comunicacioClientServidor.iniciarComunicacio(
				"cS1620585775837,insercio_usuari,Usuari nou,Castillo,489657482W,azucena@azucena.com,54401d296cf9205c850efc88869109c0054506f8,Carril 56,08800,Cardedeu,Lleida,Espanya,789455211,usuari,99,true");
		String resposta = comunicacioClientServidor.getData();
		assertTrue(resposta.equals("1"));
	}

	

	// Esborra un llibre de la base de dades
	// En aquest cas el llibre existeix
	@Test
	void testComprovacioEsborrarllibreExistent() {
		comunicacioClientServidor
				.iniciarComunicacio("cS1620585775837,eliminar_llibre,26");
		String resposta = comunicacioClientServidor.getData();
		assertTrue(resposta.equals("1"));
	}

	// Esborra un llibre de la base de dades
	// En aquest cas lel llibre no existeix
	@Test
	void testComprovacioEsborrarLlibreNoExistent() {
		comunicacioClientServidor
				.iniciarComunicacio("cS1620585775837,eliminar_llibre,6015");
		String resposta = comunicacioClientServidor.getData();
		assertTrue(resposta.equals("0"));
	}

}
