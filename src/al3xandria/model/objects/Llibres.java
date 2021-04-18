package al3xandria.model.objects;

public class Llibres {

	private int idLlibre;
	private String isbn;
	private String titol;
	private String dataPublicacio;
	private String edicio;
	private int puntuacio;
	private int numeroDeReserves;
	private String sinopsi;
	private int numeroDePagines;
	private String autor;
	private String editorial;
	private String genere;
	private boolean estaReservat;
	
	
	public Llibres() {
		
	}
	
	
	public Llibres(int idLlibre, String isbn, String titol, String dataPublicacio, String edicio, int puntuacio,
			int numeroDeReserves, String sinopsi, int numeroDePagines, String autor, String editorial, String genere, boolean estaReservat) {
		this.idLlibre = idLlibre;
		this.isbn = isbn;
		this.titol = titol;
		this.dataPublicacio = dataPublicacio;
		this.edicio = edicio;
		this.puntuacio = puntuacio;
		this.numeroDeReserves = numeroDeReserves;
		this.sinopsi = sinopsi;
		this.numeroDePagines = numeroDePagines;
		this.autor = autor;
		this.editorial = editorial;
		this.genere = genere;
		this.estaReservat = estaReservat;
	}
	
	
	
	@Override
	public String toString() {
		return "Llibres [idLlibre=" + idLlibre + ", isbn=" + isbn + ", titol=" + titol + ", dataPublicacio="
				+ dataPublicacio + ", edicio=" + edicio + ", puntuacio=" + puntuacio + ", numeroDeReserves="
				+ numeroDeReserves + ", sinopsi=" + sinopsi + ", numeroDePagines=" + numeroDePagines + ", autor="
				+ autor + ", editorial=" + editorial + ", genere=" + genere + ", reservat=" + estaReservat + "]";
	}


	/*-------------------------- Getters and Setters Methods --------------------------*/
	public int getIdLlibre() {
		return idLlibre;
	}
	public void setIdLlibre(int idLlibre) {
		this.idLlibre = idLlibre;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getTitol() {
		return titol;
	}
	public void setTitol(String titol) {
		this.titol = titol;
	}
	public String getDataPublicacio() {
		return dataPublicacio;
	}
	public void setDataPublicacio(String dataPublicacio) {
		this.dataPublicacio = dataPublicacio;
	}
	public String getEdicio() {
		return edicio;
	}
	public void setEdicio(String edicio) {
		this.edicio = edicio;
	}
	public int getPuntuacio() {
		return puntuacio;
	}
	public void setPuntuacio(int puntuacio) {
		this.puntuacio = puntuacio;
	}
	public int getNumeroDeReserves() {
		return numeroDeReserves;
	}
	public void setNumeroDeReserves(int numeroDeReserves) {
		this.numeroDeReserves = numeroDeReserves;
	}
	public String getSinopsi() {
		return sinopsi;
	}
	public void setSinopsi(String sinopsi) {
		this.sinopsi = sinopsi;
	}
	public int getNumeroDePagines() {
		return numeroDePagines;
	}
	public void setNumeroDePagines(int numeroDePagines) {
		this.numeroDePagines = numeroDePagines;
	}
	
	public String getAutor() {
				
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getEditorial() {
		return editorial;
	}

	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}

	public String getGenere() {
		return genere;
	}

	public void setGenere(String genere) {
		this.genere = genere;
	}
	
	public void setEstaReservat(boolean estaReservat) {
		this.estaReservat = estaReservat;
	}
	
	public boolean getEstaReservat() {
		return estaReservat;
	}


	
	/*-------------------------- equals and hashcode Methods --------------------------*/
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((autor == null) ? 0 : autor.hashCode());
		result = prime * result + ((dataPublicacio == null) ? 0 : dataPublicacio.hashCode());
		result = prime * result + ((edicio == null) ? 0 : edicio.hashCode());
		result = prime * result + ((editorial == null) ? 0 : editorial.hashCode());
		result = prime * result + (estaReservat ? 1231 : 1237);
		result = prime * result + ((genere == null) ? 0 : genere.hashCode());
		result = prime * result + idLlibre;
		result = prime * result + ((isbn == null) ? 0 : isbn.hashCode());
		result = prime * result + numeroDePagines;
		result = prime * result + numeroDeReserves;
		result = prime * result + puntuacio;
		result = prime * result + ((sinopsi == null) ? 0 : sinopsi.hashCode());
		result = prime * result + ((titol == null) ? 0 : titol.hashCode());
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
		Llibres other = (Llibres) obj;
		
		if(autor == null) {
			if(other.autor != null)
				return false;
		}
		if (dataPublicacio == null) {
			if (other.dataPublicacio != null)
				return false;
		} else if (!dataPublicacio.equals(other.dataPublicacio))
			return false;
		if (edicio == null) {
			if (other.edicio != null)
				return false;
		} else if (!edicio.equals(other.edicio))
			return false;
		if (editorial == null) {
			if (other.editorial != null)
				return false;
		} else if (!editorial.equals(other.editorial))
			return false;
		if (estaReservat != other.estaReservat)
			return false;
		if (genere == null) {
			if (other.genere != null)
				return false;
		} else if (!genere.equals(other.genere))
			return false;
		if (idLlibre != other.idLlibre)
			return false;
		if (isbn == null) {
			if (other.isbn != null)
				return false;
		} else if (!isbn.equals(other.isbn))
			return false;
		if (numeroDePagines != other.numeroDePagines)
			return false;
		if (numeroDeReserves != other.numeroDeReserves)
			return false;
		if (puntuacio != other.puntuacio)
			return false;
		if (sinopsi == null) {
			if (other.sinopsi != null)
				return false;
		} else if (!sinopsi.equals(other.sinopsi))
			return false;
		if (titol == null) {
			if (other.titol != null)
				return false;
		} else if (!titol.equals(other.titol))
			return false;
		return true;
	}



	
	
	
	
	
	
}
