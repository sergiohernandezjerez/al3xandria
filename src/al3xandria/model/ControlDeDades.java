package al3xandria.model;

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
	 * @param partNumerica
	 * @param lletraFinal
	 * @return boolean  --> true si és correcta | false si és incorrecte
	 * @see https://es.wikipedia.org/wiki/DNI_(Espa%C3%B1a)#:~:text=El%20n%C3%BAmero%20del%20documento%20nacional,dividido%20entre%20el%20n%C3%BAmero%2023.
	 * @author SergioHernandez
	 */
	private boolean comprovacioValidezaDNI(int partNumerica, char lletraFinal) {
		char[] lletra = { 'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H',
				'L', 'C', 'K', 'E' };
		boolean dniCorrecta = false;
		int resta = partNumerica % 23;
		System.out.println(resta);
		if (lletra[resta] == lletraFinal) {
			dniCorrecta = true;
		}
		return dniCorrecta;
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
	public boolean comprovarCampsOmplerts(String email, String contrasenya) {
		boolean totOmplert = false;
		if (email.length() == 0) {
			errorCampEmailBuit();
		} else if (contrasenya.length() == 0) {
			errorCampContrasenyaBuit();
		} else {
			totOmplert = true;
		}

		return totOmplert;
	}
	
	/**
	 * Missatge que mostra un avís quan el camp email està buit
	 * 
	 * @author SergioHernandez
	 */
	public void errorCampEmailBuit() {
		JOptionPane.showMessageDialog(headPanel,
				ExternalizeStrings.getString("BotoLoginLogout.missatgeErrorCampEmailBuit"),
				ExternalizeStrings.getString("BotoLoginLogout.titolMissatgeErrorCampBuit"), JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * Missatge que mostra un avís quan el camp contrasenya està buit
	 * 
	 * @author SergioHernandez
	 */
	public void errorCampContrasenyaBuit() {
		JOptionPane.showMessageDialog(headPanel,
				ExternalizeStrings.getString("BotoLoginLogout.missatgeErrorCampContrasenyaBuit"),
				ExternalizeStrings.getString("BotoLoginLogout.titolMissatgeErrorCampBuit"), JOptionPane.ERROR_MESSAGE);
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
