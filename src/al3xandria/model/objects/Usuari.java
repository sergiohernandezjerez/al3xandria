package al3xandria.model.objects;

public class Usuari {

	private int idUsuari;
	private String nomUsuari;
	private String cognomsUsuari;
	private String dniNie;
	private String email;
	private String contrasenya;
	private String adreca;
	private String codiPostal;
	private String poblacio;
	private String provincia;
	private String pais;
	private String telefon;
	private String tipusUsuari;
	private int puntuacioUsuari;
	
	private String idSessio;
	
	public Usuari() {
		this.idSessio = "0000000000000";
	}
	
	
	public Usuari(String nomUsuari, String cognomsUsuari, String dniNie, String email, String contrasenya,
			String adreca, String codiPostal, String poblacio, String provincia, String pais, String telefon,
			String tipusUsuari, int puntuacioUsuari) {
		this.nomUsuari = nomUsuari;
		this.cognomsUsuari = cognomsUsuari;
		this.dniNie = dniNie;
		this.email = email;
		this.contrasenya = contrasenya;
		this.adreca = adreca;
		this.codiPostal = codiPostal;
		this.poblacio = poblacio;
		this.provincia = provincia;
		this.pais = pais;
		this.telefon = telefon;
		this.tipusUsuari = tipusUsuari;
		this.puntuacioUsuari = puntuacioUsuari;
	}

	@Override
	public String toString() {
		return "Usuari [idUsuari=" + idUsuari + ", nomUsuari=" + nomUsuari + ", cognomsUsuari=" + cognomsUsuari + ", dniNie=" + dniNie + ", email="
				+ email + ", contrasenya=" + contrasenya + ", adreca=" + adreca + ", codiPostal=" + codiPostal
				+ ", poblacio=" + poblacio + ", provincia=" + provincia + ", pais=" + pais + ", telefon=" + telefon
				+ ", tipusUsuari=" + tipusUsuari + ", puntuacioUsuari=" + puntuacioUsuari + "]";
	}

	
	
/*-------------------------- Getters and Setters Methods --------------------------*/
	
	public String getIdSessio() {
		return idSessio;
	}
	
	public void setIdSessio(String idSessio) {
		this.idSessio = idSessio;
	}
	

	public int getIdUsuari() {
		return idUsuari;
	}
	
	public void setIdUsuari(int idUsuari) {
		this.idUsuari = idUsuari;
	}
	
	public String getNomUsuari() {
		return nomUsuari;
	}

	public void setNomUsuari(String nomUsuari) {
		this.nomUsuari = nomUsuari;
	}

	public String getCognomsUsuari() {
		return cognomsUsuari;
	}

	public void setCognomsUsuari(String cognomsUsuari) {
		this.cognomsUsuari = cognomsUsuari;
	}

	public String getDniNie() {
		return dniNie;
	}

	public void setDniNie(String dniNie) {
		this.dniNie = dniNie;
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

	public String getCodiPostal() {
		return codiPostal;
	}

	public void setCodiPostal(String codiPostal) {
		this.codiPostal = codiPostal;
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

	public String getTipusUsuari() {
		return tipusUsuari;
	}

	public void setTipusUsuari(String tipusUsuari) {
		this.tipusUsuari = tipusUsuari;
	}

	public int getPuntuacioUsuari() {
		return puntuacioUsuari;
	}

	public void setPuntuacioUsuari(int puntuacioUsuari) {
		this.puntuacioUsuari = puntuacioUsuari;
	}

	
	
	/*-------------------------- equals and hashcode Methods --------------------------*/
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adreca == null) ? 0 : adreca.hashCode());
		result = prime * result + ((codiPostal == null) ? 0 : codiPostal.hashCode());
		result = prime * result + ((cognomsUsuari == null) ? 0 : cognomsUsuari.hashCode());
		result = prime * result + ((contrasenya == null) ? 0 : contrasenya.hashCode());
		result = prime * result + ((dniNie == null) ? 0 : dniNie.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + idUsuari;
		result = prime * result + ((nomUsuari == null) ? 0 : nomUsuari.hashCode());
		result = prime * result + ((pais == null) ? 0 : pais.hashCode());
		result = prime * result + ((poblacio == null) ? 0 : poblacio.hashCode());
		result = prime * result + ((provincia == null) ? 0 : provincia.hashCode());
		result = prime * result + puntuacioUsuari;
		result = prime * result + ((telefon == null) ? 0 : telefon.hashCode());
		result = prime * result + ((tipusUsuari == null) ? 0 : tipusUsuari.hashCode());
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
		if (adreca == null) {
			if (other.adreca != null)
				return false;
		} else if (!adreca.equals(other.adreca))
			return false;
		if (codiPostal == null) {
			if (other.codiPostal != null)
				return false;
		} else if (!codiPostal.equals(other.codiPostal))
			return false;
		if (cognomsUsuari == null) {
			if (other.cognomsUsuari != null)
				return false;
		} else if (!cognomsUsuari.equals(other.cognomsUsuari))
			return false;
		if (contrasenya == null) {
			if (other.contrasenya != null)
				return false;
		} else if (!contrasenya.equals(other.contrasenya))
			return false;
		if (dniNie == null) {
			if (other.dniNie != null)
				return false;
		} else if (!dniNie.equals(other.dniNie))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (idUsuari != other.idUsuari)
			return false;
		if (nomUsuari == null) {
			if (other.nomUsuari != null)
				return false;
		} else if (!nomUsuari.equals(other.nomUsuari))
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
		if (puntuacioUsuari != other.puntuacioUsuari)
			return false;
		if (telefon == null) {
			if (other.telefon != null)
				return false;
		} else if (!telefon.equals(other.telefon))
			return false;
		if (tipusUsuari == null) {
			if (other.tipusUsuari != null)
				return false;
		} else if (!tipusUsuari.equals(other.tipusUsuari))
			return false;
		return true;
	}
	
	
	
	
}
