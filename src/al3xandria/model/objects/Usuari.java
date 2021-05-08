package al3xandria.model.objects;

import java.io.Serializable;

public class Usuari implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id_usuari;
	private String nom_usuari;
	private String cognoms_usuari;
	private String dni_nie;
	private String email;
	private String contrasenya;
	private String adreca;
	private String codi_postal;
	private String poblacio;
	private String provincia;
	private String pais;
	private String telefon;
	private String carnet;
	private String tipus_usuari;
	private int puntuacio_usuari;
	private boolean actiu;
	
	private String idSessio;
	
	public Usuari() {
		this.idSessio = "0000000000000";
	}
	
	
	
	
	
	
	
public Usuari(int id_usuari, String nom_usuari, String cognoms_usuari,
			String dni_nie, String email, String contrasenya, String adreca,
			String codi_postal, String poblacio, String provincia, String pais,
			String telefon, String carnet, String tipus_usuari,
			int puntuacio_usuari, boolean actiu, String idSessio) {
		this.id_usuari = id_usuari;
		this.nom_usuari = nom_usuari;
		this.cognoms_usuari = cognoms_usuari;
		this.dni_nie = dni_nie;
		this.email = email;
		this.contrasenya = contrasenya;
		this.adreca = adreca;
		this.codi_postal = codi_postal;
		this.poblacio = poblacio;
		this.provincia = provincia;
		this.pais = pais;
		this.telefon = telefon;
		this.carnet = carnet;
		this.tipus_usuari = tipus_usuari;
		this.puntuacio_usuari = puntuacio_usuari;
		this.actiu = actiu;
		this.idSessio = idSessio;
	}


@Override
public String toString() {
	return "Usuari [id_usuari=" + id_usuari + ", nom_usuari=" + nom_usuari
			+ ", cognoms_usuari=" + cognoms_usuari + ", dni_nie=" + dni_nie
			+ ", email=" + email + ", contrasenya=" + contrasenya + ", adreca="
			+ adreca + ", codi_postal=" + codi_postal + ", poblacio=" + poblacio
			+ ", provincia=" + provincia + ", pais=" + pais + ", telefon="
			+ telefon + ", carnet=" + carnet + ", tipus_usuari=" + tipus_usuari
			+ ", puntuacio_usuari=" + puntuacio_usuari + ", actiu=" + actiu
			+ ", idSessio=" + idSessio + "]";
}




/*-------------------------- Getters and Setters Methods --------------------------*/
	
	
	







	public int getId_usuari() {
		return id_usuari;
	}


	public void setId_usuari(int id_usuari) {
		this.id_usuari = id_usuari;
	}


	public String getNom_usuari() {
		return nom_usuari;
	}


	public void setNom_usuari(String nom_usuari) {
		this.nom_usuari = nom_usuari;
	}


	public String getCognoms_usuari() {
		return cognoms_usuari;
	}


	public void setCognoms_usuari(String cognoms_usuari) {
		this.cognoms_usuari = cognoms_usuari;
	}


	public String getDni_nie() {
		return dni_nie;
	}


	public void setDni_nie(String dni_nie) {
		this.dni_nie = dni_nie;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getContrasenya() {
		return contrasenya;
	}


	public void setContrasenya(String contrasenya) {
		this.contrasenya = contrasenya;
	}


	public String getAdreca() {
		return adreca;
	}


	public void setAdreca(String adreca) {
		this.adreca = adreca;
	}


	public String getCodi_postal() {
		return codi_postal;
	}


	public void setCodi_postal(String codi_postal) {
		this.codi_postal = codi_postal;
	}


	public String getPoblacio() {
		return poblacio;
	}


	public void setPoblacio(String poblacio) {
		this.poblacio = poblacio;
	}


	public String getProvincia() {
		return provincia;
	}


	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}


	public String getPais() {
		return pais;
	}


	public void setPais(String pais) {
		this.pais = pais;
	}


	public String getTelefon() {
		return telefon;
	}


	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}


	public String getCarnet() {
		return carnet;
	}


	public void setCarnet(String carnet) {
		this.carnet = carnet;
	}


	public String getTipus_usuari() {
		return tipus_usuari;
	}


	public void setTipus_usuari(String tipus_usuari) {
		this.tipus_usuari = tipus_usuari;
	}


	public int getPuntuacio_usuari() {
		return puntuacio_usuari;
	}


	public void setPuntuacio_usuari(int puntuacio_usuari) {
		this.puntuacio_usuari = puntuacio_usuari;
	}


	public boolean isActiu() {
		return actiu;
	}


	public void setActiu(boolean actiu) {
		this.actiu = actiu;
	}


	public String getIdSessio() {
		return idSessio;
	}


	public void setIdSessio(String idSessio) {
		this.idSessio = idSessio;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	


	
	/*-------------------------- equals and hashcode Methods --------------------------*/
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (actiu ? 1231 : 1237);
		result = prime * result + ((adreca == null) ? 0 : adreca.hashCode());
		result = prime * result + ((carnet == null) ? 0 : carnet.hashCode());
		result = prime * result
				+ ((codi_postal == null) ? 0 : codi_postal.hashCode());
		result = prime * result
				+ ((cognoms_usuari == null) ? 0 : cognoms_usuari.hashCode());
		result = prime * result
				+ ((contrasenya == null) ? 0 : contrasenya.hashCode());
		result = prime * result + ((dni_nie == null) ? 0 : dni_nie.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result
				+ ((idSessio == null) ? 0 : idSessio.hashCode());
		result = prime * result + id_usuari;
		result = prime * result
				+ ((nom_usuari == null) ? 0 : nom_usuari.hashCode());
		result = prime * result + ((pais == null) ? 0 : pais.hashCode());
		result = prime * result
				+ ((poblacio == null) ? 0 : poblacio.hashCode());
		result = prime * result
				+ ((provincia == null) ? 0 : provincia.hashCode());
		result = prime * result + puntuacio_usuari;
		result = prime * result + ((telefon == null) ? 0 : telefon.hashCode());
		result = prime * result
				+ ((tipus_usuari == null) ? 0 : tipus_usuari.hashCode());
		return result;
	}


	

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuari other = (Usuari) obj;
		if (actiu != other.actiu)
			return false;
		if (adreca == null) {
			if (other.adreca != null)
				return false;
		} else if (!adreca.equals(other.adreca))
			return false;
		if (carnet == null) {
			if (other.carnet != null)
				return false;
		} else if (!carnet.equals(other.carnet))
			return false;
		if (codi_postal == null) {
			if (other.codi_postal != null)
				return false;
		} else if (!codi_postal.equals(other.codi_postal))
			return false;
		if (cognoms_usuari == null) {
			if (other.cognoms_usuari != null)
				return false;
		} else if (!cognoms_usuari.equals(other.cognoms_usuari))
			return false;
		if (contrasenya == null) {
			if (other.contrasenya != null)
				return false;
		} else if (!contrasenya.equals(other.contrasenya))
			return false;
		if (dni_nie == null) {
			if (other.dni_nie != null)
				return false;
		} else if (!dni_nie.equals(other.dni_nie))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (idSessio == null) {
			if (other.idSessio != null)
				return false;
		} else if (!idSessio.equals(other.idSessio))
			return false;
		if (id_usuari != other.id_usuari)
			return false;
		if (nom_usuari == null) {
			if (other.nom_usuari != null)
				return false;
		} else if (!nom_usuari.equals(other.nom_usuari))
			return false;
		if (pais == null) {
			if (other.pais != null)
				return false;
		} else if (!pais.equals(other.pais))
			return false;
		if (poblacio == null) {
			if (other.poblacio != null)
				return false;
		} else if (!poblacio.equals(other.poblacio))
			return false;
		if (provincia == null) {
			if (other.provincia != null)
				return false;
		} else if (!provincia.equals(other.provincia))
			return false;
		if (puntuacio_usuari != other.puntuacio_usuari)
			return false;
		if (telefon == null) {
			if (other.telefon != null)
				return false;
		} else if (!telefon.equals(other.telefon))
			return false;
		if (tipus_usuari == null) {
			if (other.tipus_usuari != null)
				return false;
		} else if (!tipus_usuari.equals(other.tipus_usuari))
			return false;
		return true;
	}

	
	

	
	
	
	
	
}
