package al3xandria.model.objects;

import java.sql.Date;

public class Llibres {

	private int idLlibre;
	private String isbn;
	private String titol;
	private Date dataPublicacio;
	private String edicio;
	private int puntuacio;
	private int numeroDeReserves;
	private String sinopsi;
	private int numeroDePagines;
	
	
	public Llibres() {
		
	}
	
	
	public Llibres(int idLlibre, String isbn, String titol, Date dataPublicacio, String edicio, int puntuacio,
			int numeroDeReserves, String sinopsi, int numeroDePagines) {
		this.idLlibre = idLlibre;
		this.isbn = isbn;
		this.titol = titol;
		this.dataPublicacio = dataPublicacio;
		this.edicio = edicio;
		this.puntuacio = puntuacio;
		this.numeroDeReserves = numeroDeReserves;
		this.sinopsi = sinopsi;
		this.numeroDePagines = numeroDePagines;
	}
	
	
	@Override
	public String toString() {
		return "Llibres [idLlibre=" + idLlibre + ", isbn=" + isbn + ", titol=" + titol + ", dataPublicacio="
				+ dataPublicacio + ", edicio=" + edicio + ", puntuacio=" + puntuacio + ", numeroDeReserves="
				+ numeroDeReserves + ", sinopsi=" + sinopsi + ", numeroDePagines=" + numeroDePagines + "]";
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
	public Date getDataPublicacio() {
		return dataPublicacio;
	}
	public void setDataPublicacio(Date dataPublicacio) {
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
	
	/*-------------------------- equals and hashcode Methods --------------------------*/
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dataPublicacio == null) ? 0 : dataPublicacio.hashCode());
		result = prime * result + ((edicio == null) ? 0 : edicio.hashCode());
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
