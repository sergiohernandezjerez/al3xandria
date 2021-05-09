package al3xandria.model.objects;

/**
 * Clase per gestionar el json i treballar amb Editorials
 * @author SergioHernandez
 *
 */
public class Editorial {
	private String nom_editorial;

	public Editorial(String nom_editorial) {
		this.nom_editorial = nom_editorial;
	}

	@Override
	public String toString() {
		return "Editorial [nom_editorial=" + nom_editorial + "]";
	}

	public String getNom_editorial() {
		return nom_editorial;
	}

	public void setNom_editorial(String nom_editorial) {
		this.nom_editorial = nom_editorial;
	}
}
