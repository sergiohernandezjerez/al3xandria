package al3xandria.model.objects;


/**
 * Clase per gestionar el json i treballar amb Llibres
 * @author SergioHernandez
 *
 */
public class Llibres {

	private int id_llibre;
	private String isbn;
	private String titol;
	private String data_publicacio;
	private String edicio;
	private int puntuacio;
	private int num_reserves;
	private String sinopsis;
	private int num_pagines;
	private String nom_autor;
	private String nom_editorial;
	private String nom_genere;
	private boolean reservat;
	
	
	public Llibres() {
		
	}
	
	
	public Llibres(int idLlibre, String isbn, String titol, String dataPublicacio, String edicio, int puntuacio,
			int numeroDeReserves, String sinopsi, int numeroDePagines, String autor, String editorial, String genere, boolean estaReservat) {
		this.id_llibre = idLlibre;
		this.isbn = isbn;
		this.titol = titol;
		this.data_publicacio = dataPublicacio;
		this.edicio = edicio;
		this.puntuacio = puntuacio;
		this.num_reserves = numeroDeReserves;
		this.sinopsis = sinopsi;
		this.num_pagines = numeroDePagines;
		this.nom_autor = autor;
		this.nom_editorial = editorial;
		this.nom_genere = genere;
		this.reservat = estaReservat;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((data_publicacio == null) ? 0 : data_publicacio.hashCode());
		result = prime * result + ((edicio == null) ? 0 : edicio.hashCode());
		result = prime * result + id_llibre;
		result = prime * result + ((isbn == null) ? 0 : isbn.hashCode());
		result = prime * result
				+ ((nom_autor == null) ? 0 : nom_autor.hashCode());
		result = prime * result
				+ ((nom_editorial == null) ? 0 : nom_editorial.hashCode());
		result = prime * result
				+ ((nom_genere == null) ? 0 : nom_genere.hashCode());
		result = prime * result + num_pagines;
		result = prime * result + num_reserves;
		result = prime * result + puntuacio;
		result = prime * result + (reservat ? 1231 : 1237);
		result = prime * result
				+ ((sinopsis == null) ? 0 : sinopsis.hashCode());
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
		if (data_publicacio == null) {
			if (other.data_publicacio != null)
				return false;
		} else if (!data_publicacio.equals(other.data_publicacio))
			return false;
		if (edicio == null) {
			if (other.edicio != null)
				return false;
		} else if (!edicio.equals(other.edicio))
			return false;
		if (id_llibre != other.id_llibre)
			return false;
		if (isbn == null) {
			if (other.isbn != null)
				return false;
		} else if (!isbn.equals(other.isbn))
			return false;
		if (nom_autor == null) {
			if (other.nom_autor != null)
				return false;
		} else if (!nom_autor.equals(other.nom_autor))
			return false;
		if (nom_editorial == null) {
			if (other.nom_editorial != null)
				return false;
		} else if (!nom_editorial.equals(other.nom_editorial))
			return false;
		if (nom_genere == null) {
			if (other.nom_genere != null)
				return false;
		} else if (!nom_genere.equals(other.nom_genere))
			return false;
		if (num_pagines != other.num_pagines)
			return false;
		if (num_reserves != other.num_reserves)
			return false;
		if (puntuacio != other.puntuacio)
			return false;
		if (reservat != other.reservat)
			return false;
		if (sinopsis == null) {
			if (other.sinopsis != null)
				return false;
		} else if (!sinopsis.equals(other.sinopsis))
			return false;
		if (titol == null) {
			if (other.titol != null)
				return false;
		} else if (!titol.equals(other.titol))
			return false;
		return true;
	}


	public int getId_llibre() {
		return id_llibre;
	}


	public void setId_llibre(int id_llibre) {
		this.id_llibre = id_llibre;
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


	public String getData_publicacio() {
		return data_publicacio;
	}


	public void setData_publicacio(String data_publicacio) {
		this.data_publicacio = data_publicacio;
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


	public int getNum_reserves() {
		return num_reserves;
	}


	public void setNum_reserves(int num_reserves) {
		this.num_reserves = num_reserves;
	}


	public String getSinopsis() {
		return sinopsis;
	}


	public void setSinopsis(String sinopsis) {
		this.sinopsis = sinopsis;
	}


	public int getNum_pagines() {
		return num_pagines;
	}


	public void setNum_pagines(int num_pagines) {
		this.num_pagines = num_pagines;
	}


	public String getNom_autor() {
		return nom_autor;
	}


	public void setNom_autor(String nom_autor) {
		this.nom_autor = nom_autor;
	}


	public String getNom_editorial() {
		return nom_editorial;
	}


	public void setNom_editorial(String nom_editorial) {
		this.nom_editorial = nom_editorial;
	}


	public String getNom_genere() {
		return nom_genere;
	}


	public void setNom_genere(String nom_genere) {
		this.nom_genere = nom_genere;
	}


	public boolean isReservat() {
		return reservat;
	}


	public void setReservat(boolean reservat) {
		this.reservat = reservat;
	}


	@Override
	public String toString() {
		return "Llibres [id_llibre=" + id_llibre + ", isbn=" + isbn + ", titol="
				+ titol + ", data_publicacio=" + data_publicacio + ", edicio="
				+ edicio + ", puntuacio=" + puntuacio + ", num_reserves="
				+ num_reserves + ", sinopsis=" + sinopsis + ", num_pagines="
				+ num_pagines + ", nom_autor=" + nom_autor + ", nom_editorial="
				+ nom_editorial + ", nom_genere=" + nom_genere + ", reservat="
				+ reservat + "]";
	}
	
	
	
	
	
	
	
	
	
	
}
