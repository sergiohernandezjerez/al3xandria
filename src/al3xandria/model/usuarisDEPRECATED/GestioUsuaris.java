package al3xandria.model.usuarisDEPRECATED;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que crea i omple un array d'usuaris
 * @author SergioHernandez
 *
 */
public class GestioUsuaris {
	
	private List<Usuari> llistatUsuaris;
	
	public GestioUsuaris() {
		
		creaUsuaris();
	}
	
	public void creaUsuaris() {
		llistatUsuaris = new ArrayList<Usuari>();
		Usuari usuari1 = new Usuari("pepe@pepe.com", "pepe", "Usuari");
		Usuari usuari2 = new Usuari("maria@maria.com", "maria", "Usuari");
		Usuari usuari3 = new Usuari("josep@josep.com", "josep", "Usuari");
		Usuari usuari4 = new Usuari("arnau@arnau.com", "arnau", "Administrador");
		Usuari usuari5 = new Usuari("anna@anna.com", "anna", "Usuari");
		Usuari usuari6 = new Usuari("sergi@sergi.com", "sergi", "Usuari");
		Usuari usuari7 = new Usuari("pere@pere.com", "pere", "Usuari");
		Usuari usuari8 = new Usuari("marta@marta.com", "marta", "Administrador");
		Usuari usuari9 = new Usuari("pilar@pilar.com", "pilar", "Administrador");
		Usuari usuari10 = new Usuari("julia@julia.com", "julia", "Usuari");
		Usuari usuari11 = new Usuari("pau@pau.com", "pau", "Usuari");
		
		llistatUsuaris.add(usuari1);
		llistatUsuaris.add(usuari2);
		llistatUsuaris.add(usuari3);
		llistatUsuaris.add(usuari4);
		llistatUsuaris.add(usuari5);
		llistatUsuaris.add(usuari6);
		llistatUsuaris.add(usuari7);
		llistatUsuaris.add(usuari8);
		llistatUsuaris.add(usuari9);
		llistatUsuaris.add(usuari10);
		llistatUsuaris.add(usuari11);
	}
	
	
	/**
	 * busca un usuari que coincideixi amb un email i 
	 * una contrasenya i el retorna
	 * @param email  --> email de l'usuari que vol fer login
	 * @param contrasenya  --> contrasenya de l'usuari que vol fer login
	 * @return Usuari si el troba, o null si no el troba
	 * @author SergioHernandez
	 */
	public Usuari buscarUsuari(String email, String contrasenya) {
		Usuari usuariTrobat = null;
		for (Usuari usuari : llistatUsuaris) {
			if(usuari.getEmail().equals(email) && 
					usuari.getContrasenya().equals(contrasenya)) {
				usuariTrobat = new Usuari(
						usuari.getEmail(), usuari.getContrasenya(), 
						usuari.getTipus());
			}
		}
		
		return usuariTrobat;
	}
	
		
}
