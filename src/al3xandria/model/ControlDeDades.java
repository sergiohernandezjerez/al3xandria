package al3xandria.model;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.lang.model.element.NestingKind;
import javax.swing.JOptionPane;
import javax.xml.transform.Source;

import al3xandria.strings.WarningStrings;
import al3xandria.vista.headPanel.HeadPanel;

/**
 * Clase per controlar diferents tipus de dades
 * @author SergioHernandez
 *
 */
public class ControlDeDades {

	private String numeroDeCarnet;
	private String dni;
	private String email;
	
	private HeadPanel headPanel;

	public ControlDeDades() {

	}

	
	/**
	 * comprova si el format del DNI es correcte
	 * @param dni --> el dni en format String
	 * @return boolean  --> true si és correcta | false si és incorrecte
	 * @see http://www.interior.gob.es/web/servicios-al-ciudadano/dni/calculo-del-digito-de-control-del-nif-nie
	 * @author SergioHernandez
	 */
	public boolean comprovacioValidezaDNI(String dni) {
		int partNumerica = 0;
		char lletraFinal = 0;
		char[] lletra = { 'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H',
				'L', 'C', 'K', 'E' };
		boolean dniCorrecta = false;
		if(dni.length() == 9) {
			partNumerica = Integer.parseInt(dni.substring(0, 8));
			lletraFinal = dni.charAt(8);
		}
		int resta = partNumerica % 23;

		if (lletra[resta] == lletraFinal) {
			dniCorrecta = true;
		}
		return dniCorrecta;
	}
	
	/**
	 * comprova si el format del NIE es correcte
	 * @param nie --> el nie en format String
	 * @return boolean  --> true si és correcta | false si és incorrecte
	 * @see http://www.interior.gob.es/web/servicios-al-ciudadano/dni/calculo-del-digito-de-control-del-nif-nie
	 * @author SergioHernandez
	 */
	public boolean comprovacioValidezaNIE(String nie) {
		boolean nieCorrecta = false;
		char lletraInicial = 0;
		String partNumericaILletra = "";
		String formatDNI = "";
		if(nie.length() == 9) {
			partNumericaILletra = nie.substring(1, 9);
			lletraInicial = nie.charAt(0);
			if(lletraInicial == 'X') {
				formatDNI = "0" + partNumericaILletra;
			}
			if(lletraInicial == 'Y') {
				formatDNI = "1" + partNumericaILletra;
			}
			if(lletraInicial == 'Z') {
				formatDNI = "2" + partNumericaILletra;
			}
		}
		
		if(comprovacioValidezaDNI(formatDNI)) {
			nieCorrecta = true;
		}
		
		return nieCorrecta;
	}
	
	
	/**
	 * Comprova que el telèfon tingui un format correcte
	 * 9 xifres i que sigui un número
	 * @param telefon
	 * @return --> true: si és correcte | false: si no és correcte
	 * @author SergioHernandez
	 */
	public boolean comprovacioFormatTelefon(String telefon) {
		boolean telefonCorrecte = false;
		if(telefon.length() == 9 && esUnNumero(telefon)) {
			telefonCorrecte = true;
		}
		
		return telefonCorrecte;
	}
	
	/**
	 * Comprova que el isbn tingui un format correcte
	 * entre 10 i 13 xifres
	 * @param isbn
	 * @return --> true: si és correcte | false: si no és correcte
	 * @author SergioHernandez
	 */
	public boolean comprovacioFormatIsbn(String isbn) {

		boolean isbnCorrecte = false;
		if(esUnNumero(isbn)) {
			if((isbn.length() == 13) || isbn.length() == 10) {
				isbnCorrecte = true;
			}
		}
		
		return isbnCorrecte;
	}


	
	/**
	 * Comprova el format de l'email
	 * @param email --> email introduït per l'usuari
	 * @return boolean --> true si és correcta | false si no és correcte
	 * @author consulta pàgines del sector
	 */
	public boolean comprovacioEmail(String email) {
		boolean emailCorrecte = false;
		
		Pattern emailPattern = Pattern
				.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
		Matcher emailMatcher = emailPattern.matcher(email);
		if (emailMatcher.find()) {
			emailCorrecte = true;
		}

		return emailCorrecte;
	}
	
	
	/**
	 * Comprova si els camps de text JTextField no estan buits
	 * 
	 * @param email       -> camp de text que pertany al email
	 * @param contrasenya -> camp de text que pertany a la contrasenya
	 * @return true si els dos camps estan omplerts | false si no ho estan
	 * @author SergioHernandez
	 */
	public boolean comprovarCampsOmplertsLogin(String email, String contrasenya) {
		ArrayList<String> campsBuits = new ArrayList<String>();
		boolean totOmplert = false;
		if (email.length() == 0) {
			campsBuits.add("Email");
		} 
		if (contrasenya.length() == 0) {
			campsBuits.add("Contrasenya");
		} 
		if(campsBuits.size()>0){
			errorCampBuit(campsBuits.toString());
		}else {
			totOmplert = true;
		}

		return totOmplert;
	}
	
	/**
	 * Comprova la mida de la contrasenya i que la contrasenya
	 *  repetida sigui igual a la contrasenya
	 * @param contrasenya --> contrasenya escullida per l'usuari
	 * @param contrasenyaRepetida --> repetició de la contrasenya com a mètode de seguretat
	 * @return boolean --> true: si tot és correcte | false: si tot no és correcte
	 */
	public boolean comprovarContrasenyaFormulariAltaUsuari(String contrasenya, String contrasenyaRepetida) {
		boolean contrasenyaCorrecta = false;
		if(contrasenya.length() > 7 && contrasenya.equals(contrasenyaRepetida)) {
			
				contrasenyaCorrecta = true;
			}else {
				missatgeErrorContrasenyaNoCoincideix();
			
		}		
		return contrasenyaCorrecta;
	}
	
	/**
	 * Missatge que mostra un avís quan els camps de les contrasenyes 
	 * no coincideixen
	 * 
	 * @author SergioHernandez
	 */
	public void missatgeErrorContrasenyaNoCoincideix() {
		JOptionPane.showMessageDialog(headPanel,
				WarningStrings.getString("FormulariAltausuari.errorContrasenyaNoCoincideix"),
				WarningStrings.getString("FormulariAltausuari.titolErrorContrasenyaNoCoincideix"), JOptionPane.ERROR_MESSAGE);
	}


	/**
	 * Comprova que els camps no estiguin buits al formulari d'alta d'usuari
	 * @param nom
	 * @param cognoms
	 * @param adreca
	 * @param email
	 * @param poblacio
	 * @param codiPostal
	 * @param pais
	 * @param provincia
	 * @param telefon
	 * @param identificador
	 * @param tipusIdentificador
	 * @param tipusUsuari
	 * @param contrasenya
	 * @return true si tots els camps estan omplerts, false si algun camp no está omplert
	 * @author SergioHernandez
	 */
	public boolean comprovarCampsOmplertsFormulariAltaUsuari(String nom, String cognoms, String adreca, String email, 
			String poblacio, String codiPostal, String pais, int provincia, String telefon, String identificador, 
			int tipusIdentificador, int tipusUsuari, String contrasenya) {
		ArrayList<String> campsBuits = new ArrayList<String>();
		boolean totOmplert = false;
		if (nom.length() == 0) {
			campsBuits.add("Nom");
		} 
		if (cognoms.length() == 0) {
			campsBuits.add("Cognoms");
		}
		if (adreca.length() == 0) {
			campsBuits.add("Adreça");
		}
		if (email.length() == 0) {
			campsBuits.add("Email");
		}
		if (poblacio.length() == 0) {
			campsBuits.add("Població");
		}
		if (codiPostal.length() == 0) {
			campsBuits.add("Codi Postal");
		}
		if (pais.length() == 0) {
			campsBuits.add("Pais");
		}
		if (provincia == 0) {
			campsBuits.add("Provincia");
		}
		if (telefon.length() == 0) {
			campsBuits.add("Telèfon");
		}
		if(contrasenya.length() == 0) {
			campsBuits.add("Contrasenya");
		}
		if (identificador.length() == 0) {
			campsBuits.add("Numero Identificador");
		}
		if (tipusIdentificador == 0) {
			campsBuits.add("Tipus Identificador");
		}
		if (tipusUsuari == 0) {
			campsBuits.add("Tipus Usuari");
		}
		if(campsBuits.size()>0){
			errorCampBuit(campsBuits.toString());
		}else {
			totOmplert = true;
		}

		return totOmplert;
	}
	
	/**
	 * Comprova que els camps no estiguin buits al administrador d'usuaris
	 * @param nom
	 * @param cognoms
	 * @param adreca
	 * @param email
	 * @param poblacio
	 * @param codiPostal
	 * @param pais
	 * @param provincia
	 * @param telefon
	 * @param identificador
	 * @param tipusIdentificador
	 * @param tipusUsuari
	 * @param contrasenya
	 * @return true si tots els camps estan omplerts, false si algun camp no está omplert
	 * @author SergioHernandez
	 */
	public boolean comprovarCampsOmplertsAltaUsuari(String nom, String cognoms, String adreca, String email, 
			String poblacio, String codiPostal, String pais, int provincia, String telefon, 
			String contrasenya, String identificador, 
			String carnet, int tipusUsuari) {
		ArrayList<String> campsBuits = new ArrayList<String>();
		boolean totOmplert = false;
		if (nom.length() == 0) {
			campsBuits.add("Nom");
		} 
		if (cognoms.length() == 0) {
			campsBuits.add("Cognoms");
		}
		if (adreca.length() == 0) {
			campsBuits.add("Adreça");
		}
		if (email.length() == 0) {
			campsBuits.add("Email");
		}
		if (poblacio.length() == 0) {
			campsBuits.add("Població");
		}
		if (codiPostal.length() == 0) {
			campsBuits.add("Codi Postal");
		}
		if (pais.length() == 0) {
			campsBuits.add("Pais");
		}
		if (provincia == 0) {
			campsBuits.add("Provincia");
		}
		if (telefon.length() == 0) {
			campsBuits.add("Telèfon");
		}
		if(contrasenya.length() == 0) {
			campsBuits.add("Contrasenya");
		}
		if (identificador.length() == 0) {
			campsBuits.add("Numero Identificador");
		}
		if (carnet.length() == 0) {
			campsBuits.add("Carnet");
		}
		if (tipusUsuari == 0) {
			campsBuits.add("Tipus Usuari");
		}
		if(campsBuits.size()>0){
			errorCampBuit(campsBuits.toString());
		}else {
			totOmplert = true;
		}

		return totOmplert;
	}
	
	public boolean comprovarCampsOmplertsAltaLlibre(String titol, int autor, 
			int genere, int editorial,
			String isbn, String edicio, String dataPublicacio, String numPagines) {
		ArrayList<String> campsBuits = new ArrayList<String>();
		boolean totOmplert = false;
		if (titol.length() == 0) {
			campsBuits.add("Títol");
		} 
		if (autor == 0) {
			campsBuits.add("Autor");
		}
		if (genere == 0) {
			campsBuits.add("Genere");
		}
		if (editorial == 0) {
			campsBuits.add("Editorial");
		}
		if (isbn.length() == 0) {
			campsBuits.add("Isbn");
		}
		if (edicio.length() == 0) {
			campsBuits.add("Edició");
		}
		if (dataPublicacio.length() == 0) {
			campsBuits.add("Data publicació");
		}
		if (numPagines.length() == 0) {
			campsBuits.add("Núm. pàgines");
		}
		
		if(campsBuits.size()>0){
			errorCampBuit(campsBuits.toString());
		}else {
			totOmplert = true;
		}

		return totOmplert;
	}
	
	
	/**
	 * Mètode per comprobar que la dada introduïda 
	 * és un número.
	 * @param numero
	 * @return --> true: si és un número | false: si no és un número
	 * @author http://lineadecodigo.com/java/validar-si-un-dato-es-numerico-en-java
	 */
	public boolean esUnNumero(String numero) {
		try {
			Long.parseLong(numero);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
	
	/**
	 * Missatge que mostra un avís quan el camp telèfon és incorrecte
	 * Mida diferent a 9
	 * 
	 * @author SergioHernandez
	 */
	public void errorFormatTelefon() {
		JOptionPane.showMessageDialog(headPanel,
				WarningStrings.getString("FormulariAltaUsuari.errorFormatTelefon"),
				WarningStrings.getString("FormulariAltaUsuari.titolErrorFormatTelefon"), JOptionPane.ERROR_MESSAGE);
	}
	
	/**
	 * Missatge que mostra un avís quan el camp isbn és incorrecte
	 * La mida ha de ser de 10 o 13 xifres
	 * 
	 * @author SergioHernandez
	 */
	public void errorFormatIsbn() {
		JOptionPane.showMessageDialog(headPanel,"Format del Isbn incorrecte.\n Ha de ser un numero de 10 o 13 xifres",
				"Error format Isbn", JOptionPane.ERROR_MESSAGE);
	}
	
	/**
	 * Missatge que mostra un avís quan un camp està buit
	 * Mostra els camps buits
	 * 
	 * @author SergioHernandez
	 */
	public void errorCampBuit(String campsBuits) {
		JOptionPane.showMessageDialog(headPanel,
				WarningStrings.getString("BotoLoginLogout.missatgeErrorCampBuit") + campsBuits,
				WarningStrings.getString("BotoLoginLogout.titolMissatgeErrorCampBuit"), JOptionPane.ERROR_MESSAGE);
	}
	
	/**
	 * Missatge que mostra un avís quan el format de l'email no és correcte
	 * 
	 * @author SergioHernandez
	 */
	public void errorEnElFormatDelEmailIntroduit() {

		JOptionPane.showMessageDialog(headPanel,
				WarningStrings.getString("BotoLoginLogout.missatgeErrorEnElFormatDelEmailIntroduit"),
				WarningStrings.getString("BotoLoginLogout.titolMissatgeErrorEnElFormatDelEmailIntroduit"),
				JOptionPane.ERROR_MESSAGE);
	}
	
	/**
	 * Missatge que mostra un avís quan el format del document no és correcte
	 * 
	 * @author SergioHernandez
	 */
	public void errorEnElFormatDelDocumentIntroduit(String document) {

		switch (document) {
		case "DNI":
			JOptionPane.showMessageDialog(headPanel,
					WarningStrings.getString("FormulariAltaUsuari.errorFormatDni"),
					WarningStrings.getString("FormulariAltaUsuari.titolErrorFormatDocument"),
					JOptionPane.ERROR_MESSAGE);
			break;
		case "NIE":
			JOptionPane.showMessageDialog(headPanel,
					WarningStrings.getString("FormulariAltaUsuari.errorFormatNie"),
					WarningStrings.getString("FormulariAltaUsuari.titolErrorFormatDocument"),
					JOptionPane.ERROR_MESSAGE);
			break;

		default:
			break;
		}
		
	}
	
	

	/*-------------------------- Getters and Setters Methods --------------------------*/
	public String getNumeroDeCarnet() {
		return numeroDeCarnet;
	}

	public void setNumeroDeCarnet(String numeroDeCarnet) {
		this.numeroDeCarnet = numeroDeCarnet;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
