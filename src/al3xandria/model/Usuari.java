package al3xandria.model;

import java.util.Observable;

import al3xandria.controlador.login.Controller;

public class Usuari extends Observable{
	
	private String nomUsuari;
	private String dni;
	private String email;
	private String direcci�;
	private String codiPostal;
	private String poblaci�;
	private String provincia;
	//el n�mero de carnet acaba amb una lletra: E, d'estudiant, P de profesor
	//es genera a partir del dni
	private String numeroCarnet;
	private String telefon;
	private String tipus;
	private String idConexio;
	
	public Usuari() {
		tipus = null;
	}
	
	public Usuari(String tipus) {
		this.tipus = tipus;
	}

	

	
	public Usuari(String nomUsuari, String dni, String email, String direcci�, String codiPostal, String poblaci�,
			String provincia, String numeroCarnet, String telefon, String tipus, String idConexio) {
		this.nomUsuari = nomUsuari;
		this.dni = dni;
		this.email = email;
		this.direcci� = direcci�;
		this.codiPostal = codiPostal;
		this.poblaci� = poblaci�;
		this.provincia = provincia;
		this.numeroCarnet = numeroCarnet;
		this.telefon = telefon;
		this.tipus = tipus;
		this.idConexio = idConexio;
	}

	
	
	
	public String getNomUsuari() {
		return nomUsuari;
	}

	public void setNomUsuari(String nomUsuari) {
		this.nomUsuari = nomUsuari;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDirecci�() {
		return direcci�;
	}

	public void setDirecci�(String direcci�) {
		this.direcci� = direcci�;
	}

	public String getCodiPostal() {
		return codiPostal;
	}

	public void setCodiPostal(String codiPostal) {
		this.codiPostal = codiPostal;
	}

	public String getPoblaci�() {
		return poblaci�;
	}

	public void setPoblaci�(String poblaci�) {
		this.poblaci� = poblaci�;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getNumeroCarnet() {
		return numeroCarnet;
	}

	public void setNumeroCarnet(String numeroCarnet) {
		this.numeroCarnet = numeroCarnet;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public String getTipus() {
		return tipus;
	}

	public void setTipus(String tipus) {
		this.tipus = tipus;
	}

	public String getIdConexio() {
		return idConexio;
	}

	public void setIdConexio(String idConexio) {
		this.idConexio = idConexio;
	}

	public void setController(Controller controller) {
		
	}


	
	
}
