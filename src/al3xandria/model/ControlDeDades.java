package al3xandria.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ControlDeDades {

	private String numeroDeCarnet;
	private String dni;
	private String email;

	public ControlDeDades() {

	}

	private boolean comprovacioValidezaDNI(int partNumerica, char lletraFinal) {
		char[] lletra = { 'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H',
				'L', 'C', 'K', 'E' };
		boolean dniCorrecta = false;
		int resta = partNumerica % 23;
		System.out.println(resta);
		if (lletra[resta] == lletraFinal) {
			dniCorrecta = true;
			generaNumeroDeCarnet();
		}
		return dniCorrecta;
	}

	public void creaElDNI(int partNumerica, char lletraFinal) {
		String dni = null;
		if (comprovacioValidezaDNI(partNumerica, lletraFinal)) {
			dni = Integer.toString(partNumerica) + Character.toString(lletraFinal);
		}
		this.dni = dni;
	}

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

	private void generaNumeroDeCarnet() {
		String numeroDeCarnetGenerat = null;

		this.numeroDeCarnet = numeroDeCarnetGenerat;
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
