package al3xandria.model.usuarisDEPRECATED;

/**
 * Clase main per probar la gestió d'usuaris. 
 * No es fa servir al programa
 * @author SergioHernandez
 *
 */
public class UsuariMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		GestioUsuaris generaUsuaris = new GestioUsuaris();
		Usuari usuari = new Usuari();
		
		usuari = generaUsuaris.buscarUsuari("pepe@pepe.com", "pepe");
		
		System.out.println("main: " + usuari);
	}

}
