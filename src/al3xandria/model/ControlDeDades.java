package al3xandria.model;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import al3xandria.strings.ExternalizeStrings;
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
	 * @return boolean  --> true si �s correcta | false si �s incorrecte
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
	 * @return boolean  --> true si �s correcta | false si �s incorrecte
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
			System.out.println(partNumericaILletra);
			lletraInicial = nie.charAt(0);
			System.out.println(lletraInicial);
			if(lletraInicial == 'X') {
				formatDNI = "0" + partNumericaILletra;
			}
			if(lletraInicial == 'Y') {
				formatDNI = "1" + partNumericaILletra;
			}
			if(lletraInicial == 'Z') {
				formatDNI = "2" + partNumericaILletra;
			}
			System.out.println(formatDNI);
		}
		
		if(comprovacioValidezaDNI(formatDNI)) {
			nieCorrecta = true;
		}
		
		return nieCorrecta;
	}
	
	
	/**
	 * Comprova que el tel�fon tingui un format correcte
	 * 9 xifres i que sigui un n�mero
	 * @param telefon
	 * @return --> true: si �s correcte | false: si no �s correcte
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
	 * Comprova el format de l'email
	 * @param email --> email introdu�t per l'usuari
	 * @return boolean --> true si �s correcta | false si no �s correcte
	 * @author consulta p�gines del sector
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
	 * Comprova si els camps de text JTextField no estan buits
	 * 
	 * @param email       -> camp de text que pertany al email
	 * @param contrasenya -> camp de text que pertany a la contrasenya
	 * @return true si els dos camps estan omplerts | false si no ho estan
	 * @author SergioHernandez
	 */
	public boolean comprovarCampsOmplertsFormulariAltaUsuari(String nom, String cognoms, String adreca, String email, 
			String poblacio, String codiPostal, String pais, String provincia, String telefon, String identificador, 
			int tipusIdentificador, int tipusUsuari) {
		ArrayList<String> campsBuits = new ArrayList<String>();
		boolean totOmplert = false;
		if (nom.length() == 0) {
			campsBuits.add("Nom");
		} 
		if (cognoms.length() == 0) {
			campsBuits.add("Cognoms");
		}
		if (adreca.length() == 0) {
			campsBuits.add("Adre�a");
		}
		if (email.length() == 0) {
			campsBuits.add("Email");
		}
		if (poblacio.length() == 0) {
			campsBuits.add("Poblaci�");
		}
		if (codiPostal.length() == 0) {
			campsBuits.add("Codi Postal");
		}
		if (pais.length() == 0) {
			campsBuits.add("Pais");
		}
		if (provincia.length() == 0) {
			campsBuits.add("Provincia");
		}
		if (telefon.length() == 0) {
			campsBuits.add("Tel�fon");
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
	 * M�tode per comprobar que la dada introdu�da 
	 * �s un n�mero.
	 * @param numero
	 * @return --> true: si �s un n�mero | false: si no �s un n�mero
	 * @author http://lineadecodigo.com/java/validar-si-un-dato-es-numerico-en-java
	 */
	public boolean esUnNumero(String numero) {
		try {
			Integer.parseInt(numero);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
	
	/**
	 * Missatge que mostra un av�s quan el camp tel�fon �s incorrecte
	 * Mida diferent a 9
	 * 
	 * @author SergioHernandez
	 */
	public void errorFormatTelefon() {
		JOptionPane.showMessageDialog(headPanel,
				ExternalizeStrings.getString("FormulariAltaUsuari.errorFormatTelefon"),
				ExternalizeStrings.getString("FormulariAltaUsuari.titolErrorFormatTelefon"), JOptionPane.ERROR_MESSAGE);
	}
	
	/**
	 * Missatge que mostra un av�s quan un camp est� buit
	 * Mostra els camps buits
	 * 
	 * @author SergioHernandez
	 */
	public void errorCampBuit(String campsBuits) {
		JOptionPane.showMessageDialog(headPanel,
				ExternalizeStrings.getString("BotoLoginLogout.missatgeErrorCampBuit") + campsBuits,
				ExternalizeStrings.getString("BotoLoginLogout.titolMissatgeErrorCampBuit"), JOptionPane.ERROR_MESSAGE);
	}
	
	/**
	 * Missatge que mostra un av�s quan el format de l'email no �s correcte
	 * 
	 * @author SergioHernandez
	 */
	public void errorEnElFormatDelEmailIntroduit() {

		JOptionPane.showMessageDialog(headPanel,
				ExternalizeStrings.getString("BotoLoginLogout.missatgeErrorEnElFormatDelEmailIntroduit"),
				ExternalizeStrings.getString("BotoLoginLogout.titolMissatgeErrorEnElFormatDelEmailIntroduit"),
				JOptionPane.ERROR_MESSAGE);
	}
	
	/**
	 * Missatge que mostra un av�s quan el format del document no �s correcte
	 * 
	 * @author SergioHernandez
	 */
	public void errorEnElFormatDelDocumentIntroduit(String document) {

		switch (document) {
		case "DNI":
			JOptionPane.showMessageDialog(headPanel,
					ExternalizeStrings.getString("FormulariAltaUsuari.errorFormatDni"),
					ExternalizeStrings.getString("FormulariAltaUsuari.titolErrorFormatDocument"),
					JOptionPane.ERROR_MESSAGE);
			break;
		case "NIE":
			JOptionPane.showMessageDialog(headPanel,
					ExternalizeStrings.getString("FormulariAltaUsuari.errorFormatNie"),
					ExternalizeStrings.getString("FormulariAltaUsuari.titolErrorFormatDocument"),
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
