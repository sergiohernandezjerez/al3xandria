package al3xandria.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UsuarisConectats {
	
	private ArrayList<String> idsDeConexioEstudiants;
	private ArrayList<String> idsDeConexioProfessors;
	private ArrayList<String> idsDeConexioAdministradors;
	

	private Map<String, ArrayList<String>> usuarisConectats = new HashMap<String, ArrayList<String>>();
	
	public UsuarisConectats() {
		usuarisConectats.put("Estudiant", idsDeConexioEstudiants);
		usuarisConectats.put("Professor", idsDeConexioProfessors);
		usuarisConectats.put("Administrador", idsDeConexioAdministradors);
		
	}
	
	public void addUsuariConectat(Usuari usuari) {
		usuarisConectats.put(usuari.getTipus(), addUsuariSegonsTipus(usuari));
	}
	
	public ArrayList<String> addUsuariSegonsTipus(Usuari usuari) {
		
		switch (usuari.getTipus()) {
		case "Estudiant":
			idsDeConexioEstudiants.add(usuari.getIdConexio());
			return idsDeConexioEstudiants;
		case "Professor":
			idsDeConexioProfessors.add(usuari.getIdConexio());
			return idsDeConexioProfessors;
		case "Administrador":
			idsDeConexioAdministradors.add(usuari.getIdConexio());
			return idsDeConexioAdministradors;
			

		default:
			return null;
			
		}
	}

	public ArrayList<String> getIdsDeConexioEstudiants() {
		return idsDeConexioEstudiants;
	}

	public void setIdsDeConexioEstudiants(ArrayList<String> idsDeConexioEstudiants) {
		this.idsDeConexioEstudiants = idsDeConexioEstudiants;
	}

	public ArrayList<String> getIdsDeConexioProfessors() {
		return idsDeConexioProfessors;
	}

	public void setIdsDeConexioProfessors(ArrayList<String> idsDeConexioProfessors) {
		this.idsDeConexioProfessors = idsDeConexioProfessors;
	}

	public ArrayList<String> getIdsDeConexioAdministradors() {
		return idsDeConexioAdministradors;
	}

	public void setIdsDeConexioAdministradors(ArrayList<String> idsDeConexioAdministradors) {
		this.idsDeConexioAdministradors = idsDeConexioAdministradors;
	}

	public Map<String, ArrayList<String>> getUsuarisConectats() {
		return usuarisConectats;
	}

	public void setUsuarisConectats(Map<String, ArrayList<String>> usuarisConectats) {
		this.usuarisConectats = usuarisConectats;
	}
	
	
	
	
	
}
