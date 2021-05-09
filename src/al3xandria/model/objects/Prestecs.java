package al3xandria.model.objects;

/**
 * Clase per gestionar el json i treballar amb prestecs
 * @author SergioHernandez
 *
 */
public class Prestecs {

	private int id_prestec;
	private String titol;
	private String nom_usuari;
	private String data_inici;
	private String data_final;
	private int num_renovacio;
	
	
	public Prestecs(int id_prestec, String titol, String nom_usuari,
			String data_inici, String data_final, int num_renovacio) {
		this.id_prestec = id_prestec;
		this.titol = titol;
		this.nom_usuari = nom_usuari;
		this.data_inici = data_inici;
		this.data_final = data_final;
		this.num_renovacio = num_renovacio;
	}
	
	
	
	
	@Override
	public String toString() {
		return "Prestecs [id_prestec=" + id_prestec + ", nom_llibre="
				+ titol + ", nom_usuari=" + nom_usuari + ", data_inici="
				+ data_inici + ", data_final=" + data_final + ", num_renovacio="
				+ num_renovacio + "]";
	}
	public int getId_prestec() {
		return id_prestec;
	}
	public void setId_prestec(int id_prestec) {
		this.id_prestec = id_prestec;
	}
	public String gettitol() {
		return titol;
	}
	public void settitol(String titol) {
		this.titol = titol;
	}
	public String getNom_usuari() {
		return nom_usuari;
	}
	public void setNom_usuari(String nom_usuari) {
		this.nom_usuari = nom_usuari;
	}
	public String getData_inici() {
		return data_inici;
	}
	public void setData_inici(String data_inici) {
		this.data_inici = data_inici;
	}
	public String getData_final() {
		return data_final;
	}
	public void setData_final(String data_final) {
		this.data_final = data_final;
	}
	public int getNum_renovacio() {
		return num_renovacio;
	}
	public void setNum_renovacio(int num_renovacio) {
		this.num_renovacio = num_renovacio;
	}
	
	
}
