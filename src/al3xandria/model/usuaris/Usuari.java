package al3xandria.model.usuaris;

import java.util.Observable;

public class Usuari extends Observable{

	private String email;
private String contrasenya;
	private String tipus;
	private String idSessio;
	
	public Usuari() {
		
	}
	
	public Usuari(String email, String contrasenya, String tipus) {
		this.email = email;
		this.contrasenya = contrasenya;
		this.tipus = tipus;
		this.idSessio = null;
	}

	
	@Override
	public String toString() {
		return "Usuari [email=" + email + ", contrasenya=" + contrasenya + ", tipus=" + tipus + ", idConexio="
				+ idSessio + "]";
	}
	
	
	/*-------------------------- equals and hashcode Methods --------------------------*/
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((contrasenya == null) ? 0 : contrasenya.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((idSessio == null) ? 0 : idSessio.hashCode());
		result = prime * result + ((tipus == null) ? 0 : tipus.hashCode());
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
		if (contrasenya == null) {
			if (other.contrasenya != null)
				return false;
		} else if (!contrasenya.equals(other.contrasenya))
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
		if (tipus == null) {
			if (other.tipus != null)
				return false;
		} else if (!tipus.equals(other.tipus))
			return false;
		return true;
	}


/*-------------------------- Getters and Setters Methods --------------------------*/

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

	public String getTipus() {
		return tipus;
	}

	public void setTipus(String tipus) {
		this.tipus = tipus;
	}

	public String getIdSessio() {
		return idSessio;
	}

	public void setIdSessio(String idSessio) {
		this.idSessio = idSessio;
	}
	
}
