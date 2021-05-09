package al3xandria.model.objects;


/**
 * Clase per gestionar el json i treballar amb generes
 * @author SergioHernandez
 *
 */
public class Genere {

	private String nom_genere;

	public Genere(String nom_genere) {
		this.nom_genere = nom_genere;
	}

	@Override
	public String toString() {
		return "Genere [nom_genere=" + nom_genere + "]";
	}

	public String getNom_genere() {
		return nom_genere;
	}

	public void setNom_genere(String nom_genere) {
		this.nom_genere = nom_genere;
	}

}
