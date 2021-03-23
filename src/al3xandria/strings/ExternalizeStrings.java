package al3xandria.strings;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Clase que agafa una cadena de text des de un document extern
 * @author Eclipse
 *
 */

public class ExternalizeStrings {
	private static final String BUNDLE_NAME = "al3xandria.strings.messages"; //$NON-NLS-1$

	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);

	private ExternalizeStrings() {
	}

	/**
	 * Extreu una cadena de text des de un document
	 * @param key -> clau de la cadena de text que es vol utilitzar, entre cometes 
	 * @return String --> cadena de text
	 * @author Eclipse
	 */
	public static String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}
}
