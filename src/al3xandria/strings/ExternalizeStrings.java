package al3xandria.strings;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

// Messages.getString("AdministradorComentaris.0")

public class ExternalizeStrings {
	private static final String BUNDLE_NAME = "al3xandria.strings.messages"; //$NON-NLS-1$

	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);

	private ExternalizeStrings() {
	}

	public static String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}
}
