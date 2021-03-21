package al3xandria.model.usuaris;

import java.util.ArrayList;
import java.util.List;

public class GestioUsuaris {

	
	
	private List<Usuari> llistatUsuaris;
	
	public GestioUsuaris() {
		
		creaUsuaris();
	}
	
	public void creaUsuaris() {
		llistatUsuaris = new ArrayList<Usuari>();
		Usuari usuari1 = new Usuari("pepe@pepe.com", "pepe", "usuari");
		Usuari usuari2 = new Usuari("maria@maria.com", "maria", "usuari");
		Usuari usuari3 = new Usuari("josep@josep.com", "josep", "usuari");
		Usuari usuari4 = new Usuari("arnau@arnau.com", "arnau", "administrador");
		Usuari usuari5 = new Usuari("anna@anna.com", "anna", "usuari");
		Usuari usuari6 = new Usuari("sergi@sergi.com", "sergi", "usuari");
		Usuari usuari7 = new Usuari("pere@pere.com", "pere", "usuari");
		Usuari usuari8 = new Usuari("marta@marta.com", "marta", "administrador");
		Usuari usuari9 = new Usuari("pilar@pilar.com", "pilar", "administrador");
		Usuari usuari10 = new Usuari("julia@julia.com", "julia", "usuari");
		Usuari usuari11 = new Usuari("pau@pau.com", "pau", "usuari");
		
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
	
	
	public Usuari buscarUsuari(String email, String contrasenya) {
		Usuari usuariTrobat = null;
		for (Usuari usuari : llistatUsuaris) {
			if(usuari.getEmail().equals(email) && usuari.getContrasenya().equals(contrasenya)) {
				usuariTrobat = new Usuari(usuari.getEmail(), usuari.getContrasenya(), usuari.getContrasenya());
			}
		}
		
		return usuariTrobat;
	}
	
		
}
