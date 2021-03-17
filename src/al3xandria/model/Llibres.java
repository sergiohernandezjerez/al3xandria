package al3xandria.model;

public class Llibres {

	private String titol;
	private String autor;
	private String isbn;
	private int pagines;
	private String editorial;
	private String descripcio;
	
	
	public Llibres(String titol, String autor, String isbn, int pagines, String editorial, String descripcio) {
		this.titol = titol;
		this.autor = autor;
		this.isbn = isbn;
		this.pagines = pagines;
		this.editorial = editorial;
		this.descripcio = descripcio;
	}

	
	
	/*-------------------------- Getters and Setters Methods --------------------------*/

	public String getTitol() {
		return titol;
	}


	public void setTitol(String titol) {
		this.titol = titol;
	}


	public String getAutor() {
		return autor;
	}


	public void setAutor(String autor) {
		this.autor = autor;
	}


	public String getIsbn() {
		return isbn;
	}


	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}


	public int getPagines() {
		return pagines;
	}


	public void setPagines(int pagines) {
		this.pagines = pagines;
	}


	public String getEditorial() {
		return editorial;
	}


	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}


	public String getDescripcio() {
		return descripcio;
	}


	public void setDescripcio(String descripcio) {
		this.descripcio = descripcio;
	}
	
	
	
}
