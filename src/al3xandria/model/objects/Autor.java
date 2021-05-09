package al3xandria.model.objects;

/**
 * Clase per gestionar el json i treballar amb Autor
 * @author SergioHernandez
 *
 */
public class Autor {
	private String nom_autor;
	
	

	public Autor(String nom_autor) {
		this.nom_autor = nom_autor;
	}

	@Override
	public String toString() {
		return "Autor [nom_autor=" + nom_autor + "]";
	}

	public String getNom_autor() {
		return nom_autor;
	}

	public void setNom_autor(String nom_autor) {
		this.nom_autor = nom_autor;
	}
	
	
}
