package al3xandria.model.usuaris;

public class UsuariMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		GestioUsuaris generaUsuaris = new GestioUsuaris();
		Usuari usuari = new Usuari();
		
		usuari = generaUsuaris.buscarUsuari("pepe@pepe.com", "pepe");
		
		System.out.println("main: " + usuari);
	}

}
