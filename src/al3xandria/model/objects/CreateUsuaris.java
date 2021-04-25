package al3xandria.model.objects;

import java.util.ArrayList;


/**
 * Classe que crea les dades dels usuaris
 * @author SergioHernandez
 *
 */
public class CreateUsuaris {
private ArrayList<Usuari> llistatUsuaris;
private ArrayList<String> llistatProvincies = new ArrayList<String>();
private ArrayList<String> llistatTipusUsuaris = new ArrayList<String>();
private Usuari usuariConnectat;
	
	public CreateUsuaris() {
		usuariConnectat = new Usuari();
		creaUsuaris();
	}
	
	public void creaUsuaris() {
		llistatUsuaris = new ArrayList<Usuari>();
		Usuari usuari1 = new Usuari(1, "Pepe", "Garcia", "52177001", "pepe@pepe.com", "pepe", "Carrer 4", "08521", "Reus", "Tarragona", "Espanya", "123456789", "C123", "Estudiant", 0, true);
		Usuari usuari2 = new Usuari(2, "Maria", "Jimenex", "52177001", "maria@maria.com", "maria", "Carrer 5", "08451", "Montcada", "Barcelona", "Espanya", "456789123", "C124", "Administrador", 0, true);
		Usuari usuari3 = new Usuari(3, "Josep", "Perez", "52177001", "josep@josep.com", "josep", "Carrer 8", "09564", "Mollet", "Girona", "Espanya", "789456123", "C125", "Professor", 0, true);
		Usuari usuari4 = new Usuari(4, "Arnau", "Jerez", "52177001", "arnau@arnau.com", "arnau", "Carrer 12", "08080", "Vic", "Lleida", "Espanya", "456123789", "C126", "Administrador", 0, true);
		Usuari usuari5 = new Usuari(5, "Anna", "Hernandez", "52177001", "anna@anna.com", "anna", "Carrer 54", "06895", "Camprodon", "Barcelona", "Espanya", "456456123", "C127", "Estudiant", 0, true);
		Usuari usuari6 = new Usuari(6, "Sergi", "Guardiola", "52177001", "sergi@sergi.com", "sergi", "Carrer 45", "08456", "La Llagosta", "Tarragona", "Espanya", "123123456", "C128", "Professor", 0, true);
		Usuari usuari7 = new Usuari(7, "Pere", "Llopis", "52177001", "pere@pere.com", "pere", "Carrer 100", "08452", "Ripollet", "Girona", "Espanya", "456789789", "C129", "Professor", 0, true);
		Usuari usuari8 = new Usuari(8, "Marta", "Penades", "52177001", "marta@marta.com", "marta", "Carrer 32", "08741", "Cerdanyola", "Lleida", "Espanya", "123456123", "C130", "Administrador", 0, true);
		Usuari usuari9 = new Usuari(9, "Pilar", "Lorenzo", "52177001", "pilar@pilar.com", "pilar", "Carrer 1", "08963", "Badalona", "Tarragona", "Espanya", "123789456", "C131", "Administrador", 0, true);
		Usuari usuari10 = new Usuari(10, "Julia", "Leon", "52177001", "julia@julia.com", "julia", "Carrer 88", "08521", "La Garriga", "Barcelona", "Espanya", "456789123", "C132", "Professor", 0, true);
		Usuari usuari11 = new Usuari(11, "Pau", "Suarez", "52177001", "pau@pau.com", "pau", "Carrer 443", "08880", "Vielha", "Tarragona", "Espanya", "123456789", "C133", "Estudiant", 0, true);

		
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
		
		llistatProvincies.add("Barcelona");
		llistatProvincies.add("Tarragona");
		llistatProvincies.add("LLeida");
		llistatProvincies.add("Girona");
		
		llistatTipusUsuaris.add("Estudiant");
		llistatTipusUsuaris.add("Professor");
		llistatTipusUsuaris.add("Administrador");
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
						usuari.getIdUsuari(),
						usuari.getNomUsuari(), 
						usuari.getCognomsUsuari(),
						usuari.getDniNie(),
						usuari.getEmail(),
						usuari.getContrasenya(),
						usuari.getAdreca(),
						usuari.getCodiPostal(),
						usuari.getPoblacio(),
						usuari.getProvincia(),
						usuari.getPais(),
						usuari.getTelefon(),
						usuari.getCarnet(),
						usuari.getTipusUsuari(),
						usuari.getPuntuacioUsuari(),
						usuari.getActiu()
						);
			}
		}
		
		return usuariTrobat;
	}
	
	public ArrayList<Usuari> getLlistatUsuaris() {
		return llistatUsuaris;
	}
	
	public ArrayList<String> getLlistatProvincies() {
		return llistatProvincies;
	}
	
	public ArrayList<String> getLlistatTipusUsuaris() {
		return llistatTipusUsuaris;
	}
	
	public Usuari getUsuariConnectat() {
		return usuariConnectat;
	}
	
	public void setUsuariConnectat(Usuari usuariConnectat) {
		this.usuariConnectat = usuariConnectat;
	}
}
