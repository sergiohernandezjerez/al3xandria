package al3xandria.controlador.usuaris;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import al3xandria.encrypt.Hash;
import al3xandria.model.ComunicacioClientServidor;
import al3xandria.model.ControlDeDades;
import al3xandria.model.objects.Usuari;
import al3xandria.strings.WarningStrings;
import al3xandria.vista.centralPanel.AdministradorUsuaris;

/**
 * Classe que controla les accions que fa l'administrado en el panel
 * AdministradorUsuaris
 * 
 * @author SergioHernandez
 *
 */
public class AdministradorUsuarisControlador implements MouseListener {

	private AdministradorUsuaris administradorUsuaris;
	private JTable table;
	private Usuari usuariConnectat;
	private ControlDeDades controlDeDades;
	private ComunicacioClientServidor comunicacioClientServidor;
	private Hash hash = new Hash();
	private int carnet = 0;

	/**
	 * Constructor
	 * 
	 * @param administradorUsuaris
	 * @param usuariConnectat
	 */
	public AdministradorUsuarisControlador(AdministradorUsuaris administradorUsuaris, Usuari usuariConnectat) {
		this.administradorUsuaris = administradorUsuaris;
		this.table = administradorUsuaris.getUsuarisTable();
		this.usuariConnectat = usuariConnectat;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (administradorUsuaris.getCercarButton() == e.getSource()) {
			ferLaCerca();
		}

		// mostra les dades d'un usuari si s'ha seleccionat
		if (administradorUsuaris.getMostrarButton() == e.getSource() && getAccio().equals("default")) {
			if (comprovarSiUsuariSeleccionat()) {
				setDadesUsuarisPerMostrar();
				desactivarDadesUsuaris();
				mostrarDadesUsuaris();
			}

		}
		
		if(administradorUsuaris.getRefrescarLabel() == e.getSource()) {
			refrescarElsUsuaris();
		}

		if (administradorUsuaris.getEsborrarLabel() == e.getSource()) {
			esborrarCampCerca();
		}

		if (administradorUsuaris.getLupaLabel() == e.getSource()) {
			ferLaCerca();
		}

		if (administradorUsuaris.getSeguentIconLabel() == e.getSource()) {
			pasarUsuariSeguent();
		}

		if (administradorUsuaris.getAnteriorIconLabel() == e.getSource()) {
			pasarUsuariAnterior();
		}

		if (administradorUsuaris.getAltaUsuariButton() == e.getSource() && getAccio().equals("default")) {
			setAccio("alta");
			setDadesUsuarisPerAlta();
		}

		// elimina un usuari si s'ha seleccionat
		if (administradorUsuaris.getBaixaUsuariButton() == e.getSource() && getAccio().equals("default")) {
			if (comprovarSiUsuariSeleccionat()) {
				setAccio("baixa");
				avisConfirmarBaixaUsuari();
			}

		}

		// edita un usuari si s'ha seleccionat
		if (administradorUsuaris.getEditarUsuariButton() == e.getSource() && getAccio().equals("default")) {
			if (comprovarSiUsuariSeleccionat()) {
				setAccio("editar");
				setDadesUsuarisPerEditar();
			}

		}

		// accions boto confirmar
		if (administradorUsuaris.getConfirmarButton() == e.getSource()) {
			if (getAccio().equals("alta")) {
				avisConfirmarAltaUsuari();

			}

			if (getAccio().equals("editar")) {
				avisConfirmarUpdateUsuari();
			}
		}

		// acció del botó cancel.lar
		if (administradorUsuaris.getCancellarButton() == e.getSource()) {
			avisCancellacioAccio();
		}

	}

	/**
	 * Torna a omplir la jtable amb tots els usuaris
	 */
	private void refrescarElsUsuaris() {
		administradorUsuaris.llistarUsuaris();
		
	}

	/**
	 * Mostra un avís per modificar un usuari Comproba que totes les dades estiguin
	 * introduides i les mostra totes per informar a l'usuari
	 * 
	 * @author SergioHernandez
	 */
	private void avisConfirmarUpdateUsuari() {
		if (comprovacioDadesAltaUsuari()) {
			String dadesUsuariModificat = dadesUsuariModificat();
			int valor = JOptionPane.showConfirmDialog(administradorUsuaris,
					dadesUsuariModificat + "\n\n" + "Vols modificar l'usuari?", "Dades l'usuari modificat",
					JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null);
			if (valor == JOptionPane.YES_OPTION) {
				if(enviarDadesModificarUsuariAlServidor()) {
					mostraDadesUsuariModificat(dadesUsuariModificat, dadesUsuariToString());
					refrescarElsUsuaris();
					setPanelPerDefecte();
				}else {
					mostraErrorModificacioUsuari();
				}
				
			}
		}

	}

	/**
	 * Envia les dades al servidor per executar la consulta per modificar 
	 * el usuri
	 * @return 1 si tot ok, 0 si no s'ha pogut fer
	 * @author SergioHernandez
	 */
	private boolean enviarDadesModificarUsuariAlServidor() {
		comunicacioClientServidor = new ComunicacioClientServidor();
		comunicacioClientServidor.iniciarComunicacio(usuariConnectat.getIdSessio()+ ",modificar_usuari," + dadesUsuariToString());
		String rebutString = comunicacioClientServidor.getData();
		if(rebutString.equals("1")) {
			return true;
		}else {
			return false;
		}
	}

	/**
	 * Mostra un error quan no s'ha pogut modificar un usuari
	 */
	private void mostraErrorModificacioUsuari() {
		JOptionPane.showMessageDialog(administradorUsuaris, "No s'ha pogut modificar l'usuari",
				"Error modificació", JOptionPane.ERROR_MESSAGE);
		
	}

	/**
	 * Mostra un avís amb l'informació de l'usuari que s'ha modificat amb les dades
	 * del mateix i la consulta que s'envia al servidor
	 * 
	 * @param dadesUsuariModificat         dades de l'usuari amb salt de linea
	 * @param dadesUsuariModificatToString dades de l'usuari separat per comes
	 * @author SergioHernandez
	 */
	private void mostraDadesUsuariModificat(String dadesUsuariModificat, Object dadesUsuariModificatToString) {
		String idUsuari = getIdUsuari();
		JOptionPane.showMessageDialog(administradorUsuaris,
				"Has modificat un usuari amb aquestes dades: \n" + dadesUsuariModificat
						+ "\nDades enviades al servidor: " + usuariConnectat.getIdSessio() + ",modificar_usuari,"
						+ idUsuari + "," + dadesUsuariModificatToString,
				"Dades usuari modificat", JOptionPane.INFORMATION_MESSAGE);
		setPanelPerDefecte();

	}

	/**
	 * Dades de l'usuari per modificar per enviar com a paràmetre al servidor
	 * 
	 * @return dades de l'usuari separats per comes
	 * @author SergioHernandez
	 */
	private String dadesUsuariToString() {
		String dadesUsuariModificat = administradorUsuaris.getNomField().getText() + ","
				+ administradorUsuaris.getCognomsField().getText() + ","
				+ administradorUsuaris.getDniNieField().getText() + "," 
				+ administradorUsuaris.getEmailField().getText()
				+ "," + Hash.sha1(administradorUsuaris.getContrasenyaToString()) + ","
				+ administradorUsuaris.getAdrecaField().getText() + ","
				+ administradorUsuaris.getCodiPostalField().getText() + ","
				+ administradorUsuaris.getPoblacioField().getText() + ","
				+ administradorUsuaris.getProvinciaComboBox().getSelectedItem() + ","
				+ administradorUsuaris.getPaisField().getText() + "," 
				+ administradorUsuaris.getTelefonField().getText()
				+ "," + administradorUsuaris.getTipusUsuariComboBox().getSelectedItem().toString().toLowerCase() + ","
				+ administradorUsuaris.getActiuCheckBox().isSelected()+ ","
				+ administradorUsuaris.getCarnetField().getText();
		return dadesUsuariModificat;
	}

	/**
	 * Dades de l'usuari per modificar per mostrar en el missatge d'informació
	 * 
	 * @return dades de l'usuari amb salts de linia
	 * @author SergioHernandez
	 */
	private String dadesUsuariModificat() {
		String dadesUsuariModificat = "id: " + administradorUsuaris.getIdUsuariField().getText() + "\n " + "nom: "
				+ administradorUsuaris.getNomField().getText() + "\n " + "cognoms: "
				+ administradorUsuaris.getCognomsField().getText() + "\n " + "dni/nie: "
				+ administradorUsuaris.getDniNieField().getText() + "\n " + "email: "
				+ administradorUsuaris.getEmailField().getText() + "\n " + "contrasenya: "
				+ administradorUsuaris.getContrasenyaToString() + "\n " + "carnet: "
				+ administradorUsuaris.getCarnetField().getText() + "\n " + "adreça: "
				+ administradorUsuaris.getAdrecaField().getText() + "\n " + "cp: "
				+ administradorUsuaris.getCodiPostalField().getText() + "\n " + "població: "
				+ administradorUsuaris.getPoblacioField().getText() + "\n " + "provincia: "
				+ administradorUsuaris.getProvinciaComboBox().getSelectedItem() + "\n" + "pais: "
				+ administradorUsuaris.getPaisField().getText() + "\n " + "telèfon: "
				+ administradorUsuaris.getTelefonField().getText() + "\n " + "tipus usuari: "
				+ administradorUsuaris.getTipusUsuariComboBox().getSelectedItem() + "\n" + "puntuació: "
				+ administradorUsuaris.getPuntuacioField().getText() + "\n " + "actiu?: "
				+ administradorUsuaris.getActiuCheckBox().isSelected() + "\n";
		return dadesUsuariModificat;
	}

	/**
	 * Mostra un avís per donar d'alta un usuari Comproba que totes les dades
	 * estiguin introduides i les mostra totes per informar a l'usuari
	 * 
	 * @author SergioHernandez
	 */
	private void avisConfirmarAltaUsuari() {

		if (comprovacioDadesAltaUsuari()) {
			String dadesUsuariAlta = dadesUsuariAlta();
			int valor = JOptionPane.showConfirmDialog(administradorUsuaris,
					dadesUsuariAlta + "\n\n" + "Vols afegir l'usuari?", "Dades de l'usuari a afegir",
					JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null);
			if (valor == JOptionPane.YES_OPTION) {
				if(enviarDadesAltaUsuariAlServidor()) {
					//mostraDadesUsuariAlta(dadesUsuariAlta, dadesUsuariToString());
					refrescarElsUsuaris();
					setPanelPerDefecte();
				}else {
					mostraErrorAltaUsuari();
				}
				
			}
		}

	}

	/**
	 * Envia les dades al servidor per executar la consulta per afegir 
	 * el usuari
	 * @return 1 si tot ok, 0 si no s'ha pogut fer
	 * @author SergioHernandez
	 */
	private boolean enviarDadesAltaUsuariAlServidor() {
		comunicacioClientServidor = new ComunicacioClientServidor();
		comunicacioClientServidor.iniciarComunicacio(usuariConnectat.getIdSessio()+ ",insercio_usuari," + dadesUsuariToString());
		String rebutString = comunicacioClientServidor.getData();
		if(rebutString.equals("1")) {
			return true;
		}else {
			return false;
		}
	}

	/**
	 * Mostra un error quan no s'ha pogut afegir un usuari
	 */
	private void mostraErrorAltaUsuari() {
		JOptionPane.showMessageDialog(administradorUsuaris, "No s'ha pogut afegir el llibre",
				"Error alta", JOptionPane.ERROR_MESSAGE);
		
	}

	/**
	 * Comprova que totes les dades introduïdes per fer l'alta d'un usuari siguin
	 * correctes. Camps buits, email i telefon correctes
	 * 
	 * @return true si tot està correcte, false si no està correcte
	 * @author SergioHernandez
	 */
	private boolean comprovacioDadesAltaUsuari() {
		controlDeDades = new ControlDeDades();
		boolean dadesCorrectes = false;
		if (controlDeDades.comprovarCampsOmplertsAltaUsuari(administradorUsuaris.getNomField().getText(),
				administradorUsuaris.getCognomsField().getText(), administradorUsuaris.getAdrecaField().getText(),
				administradorUsuaris.getEmailField().getText(), administradorUsuaris.getPoblacioField().getText(),
				administradorUsuaris.getCodiPostalField().getText(), administradorUsuaris.getPaisField().getText(),
				administradorUsuaris.getProvinciaComboBox().getSelectedIndex(),
				administradorUsuaris.getTelefonField().getText(), administradorUsuaris.getContrasenyaToString(),
				administradorUsuaris.getDniNieField().getText(),
				administradorUsuaris.getTipusUsuariComboBox().getSelectedIndex())) {
			if (controlDeDades.comprovacioEmail(administradorUsuaris.getEmailField().getText())) {

				if (controlDeDades.comprovacioFormatTelefon(administradorUsuaris.getTelefonField().getText())) {

					dadesCorrectes = true;
				} else {
					controlDeDades.errorFormatTelefon();
				}
			} else {
				controlDeDades.errorEnElFormatDelEmailIntroduit();
			}
		}

		return dadesCorrectes;
	}

	/**
	 * Mostra les dades de l'usuari que s'ha donat d'alta i la consulta que
	 * s'enviarà al servidor
	 * 
	 * @param dadesUsuariAlta         dades de l'usuari amb salt de linea
	 * @param dadesUsuariAltaToString dades de l'usuari separat per comes
	 * @author SergioHernandez
	 */
	private void mostraDadesUsuariAlta(String dadesUsuariAlta, Object dadesUsuariAltaToString) {
		JOptionPane.showMessageDialog(administradorUsuaris,
				"Has afegit un usuari amb aquestes dades: \n" + dadesUsuariAlta + "\nDades enviades al servidor: "
						+ usuariConnectat.getIdSessio() + ",insercio_usuari," + dadesUsuariAltaToString,
				"Dades usuari afegit", JOptionPane.INFORMATION_MESSAGE);
		setPanelPerDefecte();

	}

	/**
	 * Dades de l'usuari per donar d'alta per mostrar en el missatge d'informació
	 * 
	 * @return dades de l'usuari amb salts de linia
	 * @author SergioHernandez
	 */
	private String dadesUsuariAlta() {
		String dadesUsuariAlta = "nom: " + administradorUsuaris.getNomField().getText() + "\n " + "cognoms: "
				+ administradorUsuaris.getCognomsField().getText() + "\n " + "dni/nie: "
				+ administradorUsuaris.getDniNieField().getText() + "\n " + "email: "
				+ administradorUsuaris.getEmailField().getText() + "\n " + "contrasenya: "
				+ administradorUsuaris.getContrasenyaToString() + "\n " + "carnet: "
				+ administradorUsuaris.getCarnetField().getText() + "\n " + "adreça: "
				+ administradorUsuaris.getAdrecaField().getText() + "\n " + "cp: "
				+ administradorUsuaris.getCodiPostalField().getText() + "\n " + "població: "
				+ administradorUsuaris.getPoblacioField().getText() + "\n " + "provincia: "
				+ administradorUsuaris.getProvinciaComboBox().getSelectedItem() + "\n" + "pais: "
				+ administradorUsuaris.getPaisField().getText() + "\n " + "telèfon: "
				+ administradorUsuaris.getTelefonField().getText() + "\n " + "tipus usuari: "
				+ administradorUsuaris.getTipusUsuariComboBox().getSelectedItem() + "\n" + "puntuació: "
				+ administradorUsuaris.getPuntuacioField().getText() + "\n " + "actiu?: "
				+ administradorUsuaris.getActiuCheckBox().isSelected() + "\n";
		return dadesUsuariAlta;
	}

	/**
	 * Mostra un avís amb l'informació de l'usuari que s'eliminarà
	 * 
	 * @author SergioHernandez
	 */
	private void avisConfirmarBaixaUsuari() {
		int rowAMostrar = administradorUsuaris.getUsuarisTable().getSelectedRow();
		administradorUsuaris.setRowActiu(administradorUsuaris.getUsuarisTable().getSelectedRow());
		setAccio("default");
		String dadesUsuariAEliminar = getDadesRowLUsuari(administradorUsuaris.getRowActiu());
		int valor = JOptionPane.showConfirmDialog(administradorUsuaris,
				dadesUsuariAEliminar + "\n\n" + "Vols eliminar l'usuari?", "Dades del usuari a eliminar",
				JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null);
		if (valor == JOptionPane.YES_OPTION) {
			if(enviarDadesUsuariAEliminarAlServidor()) {
				mostraDadesUsuariAEliminar(dadesUsuariAEliminar, getDadesRowToString(administradorUsuaris.getRowActiu()));

				refrescarElsUsuaris();
			}else {
				mostraErrorEliminarUsuari();
			}
			
		}
	}

	/**
	 * Envia la consulta al servidor per eliminar un usuari
	 * @return true si tot ha anat bé, false si no
	 * @author SergioHernandez
	 */
	private boolean enviarDadesUsuariAEliminarAlServidor() {
		comunicacioClientServidor = new ComunicacioClientServidor();
		comunicacioClientServidor.iniciarComunicacio(usuariConnectat.getIdSessio()+ ",borrar_usuari," + carnet);
		String rebutString = comunicacioClientServidor.getData();
		if(rebutString.equals("1")) {
			return true;
		}else {
			return false;
		}
	}

	/**
	 * Mostra un error quan no s'ha pogut eliminar un usuari
	 * @author SergioHernandez
	 */
	private void mostraErrorEliminarUsuari() {
		JOptionPane.showMessageDialog(administradorUsuaris, "No s'ha pogut eliminar l'usuari",
				"Error eliminació", JOptionPane.ERROR_MESSAGE);
		
	}

	/**
	 * Mostra un avis amb les dades de l'usuari que s'ha eliminat i mostra la
	 * consulta que s'enviarà al servidor
	 * 
	 * @param dadesUsuariAEliminar dades de l'usuari amb salt de linea
	 * @param dadesRowToString     dades de l'usuari separat per comes
	 * @author SergioHernandez
	 */
	private void mostraDadesUsuariAEliminar(String dadesUsuariAEliminar, String dadesRowToString) {
		String idUsuari = getIdUsuari();
		JOptionPane.showMessageDialog(administradorUsuaris,
				"Has esborrat un usuari amb aquestes dades: \n" + dadesUsuariAEliminar
						+ "\nDades enviades al servidor: " + usuariConnectat.getIdSessio() + ",eliminar_usuari,"
						+ idUsuari + "," + dadesRowToString,
				"Dades usuari eliminat", JOptionPane.INFORMATION_MESSAGE);
		setPanelPerDefecte();

	}

	/**
	 * Extrau l'id d'el usuari del camp d'id
	 * 
	 * @return id usuari
	 * @author SergioHernandez
	 */
	private String getIdUsuari() {
		return administradorUsuaris.getIdUsuariField().getText();
	}

	/**
	 * Extreu les dades d'una fila i crea un cadena separada per comes
	 * 
	 * @param row fila de la table
	 * @author SergioHernandez
	 */
	private String getDadesRowToString(int row) {
		String dadesRowToString = getValorCella(row, 0) + "," + getValorCella(row, 1) + "," + getValorCella(row, 2)
				+ "," + getValorCella(row, 3) + "," + getValorCella(row, 4) + "," + getValorCella(row, 5) + ","
				+ getValorCella(row, 6) + "," + getValorCella(row, 7) + "," + getValorCella(row, 8) + ","
				+ getValorCella(row, 9) + "," + getValorCella(row, 10) + "," + getValorCella(row, 11) + ","
				+ getValorCella(row, 12) + "," + getValorCella(row, 13) + "," + getValorCella(row, 14) + ","
				+ getValorCella(row, 15);
		return dadesRowToString;
	}

	/**
	 * Extreu les dades d'una fila i crea un cadena amb salts de linea
	 * 
	 * @param row fila de la table
	 * @author SergioHernandez
	 */
	private String getDadesRowLUsuari(int row) {
		String dadesRowToString = "id: " + getValorCella(row, 0) + "\n" + "nom: " + getValorCella(row, 1) + "\n"
				+ "cognoms: " + getValorCella(row, 2) + "\n" + "dni/nie: " + getValorCella(row, 3) + "\n" + "email: "
				+ getValorCella(row, 4) + "\n" + "contrasenya: " + getValorCella(row, 5) + "\n" + "adreça: "
				+ getValorCella(row, 6) + "\n" + "cp: " + getValorCella(row, 7) + "\n" + "població: "
				+ getValorCella(row, 8) + "\n" + "provincia: " + getValorCella(row, 9) + "\n" + "pais: "
				+ getValorCella(row, 10) + "\n" + "telèfon: " + getValorCella(row, 11) + "\n" + "carnet: "
				+ getValorCella(row, 12) + "\n" + "tipus: " + getValorCella(row, 13) + "\n" + "puntuació: "
				+ getValorCella(row, 14) + "\n" + "actiu?: " + getValorCella(row, 15);
		setCarnet(Integer.parseInt(getValorCella(row, 12)));
		return dadesRowToString;
	}

	/**
	 * Configurar el panel per editar les dades d'un usuari
	 * 
	 * @author SergioHernandez
	 */
	private void setDadesUsuarisPerEditar() {
		administradorUsuaris.omplirCombobox();
		administradorUsuaris.getAltaUsuariButton().setEnabled(false);
		administradorUsuaris.getMostrarButton().setEnabled(false);
		administradorUsuaris.getBaixaUsuariButton().setEnabled(false);
		administradorUsuaris.getConfirmarButton().setVisible(true);
		administradorUsuaris.getCancellarButton().setVisible(true);
		administradorUsuaris.getPaginadorPanel().setVisible(false);
		administradorUsuaris.getActiuCheckBox().setEnabled(true);
		mostrarDadesUsuaris();
		activarDadesUsuaris();

	}

	/**
	 * Comprova si un usuari està seleccionat
	 * 
	 * @return true si ho està, false si no
	 * @author SergioHernandez
	 */
	public boolean comprovarSiUsuariSeleccionat() {
		int rowAMostrar = administradorUsuaris.getUsuarisTable().getSelectedRow();
		if (rowAMostrar == -1) {
			errorUsuariNoSeleccionat();
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Configurar el panel per inserir les dades d'un usuari per fer l'alta
	 * 
	 * @author SergioHernandez
	 */
	private void setDadesUsuarisPerAlta() {
		administradorUsuaris.omplirCombobox();
		administradorUsuaris.getEditarUsuariButton().setEnabled(false);
		administradorUsuaris.getMostrarButton().setEnabled(false);
		administradorUsuaris.getBaixaUsuariButton().setEnabled(false);
		administradorUsuaris.getConfirmarButton().setVisible(true);
		administradorUsuaris.getCancellarButton().setVisible(true);
		administradorUsuaris.getPaginadorPanel().setVisible(false);
		esborrarDadesUsuaris();
		activarDadesUsuaris();

	}

	/**
	 * Activa els camps per que estiguin editables
	 * 
	 * @author SergioHernandez
	 */
	private void activarDadesUsuaris() {
		administradorUsuaris.getIdUsuariField().setEditable(false);
		administradorUsuaris.getNomField().setEditable(true);
		administradorUsuaris.getCognomsField().setEditable(true);
		administradorUsuaris.getDniNieField().setEditable(true);
		administradorUsuaris.getEmailField().setEditable(true);
		administradorUsuaris.getContrasenyaField().setEditable(true);
		administradorUsuaris.getCarnetField().setEditable(false);
		administradorUsuaris.getAdrecaField().setEditable(true);
		administradorUsuaris.getCodiPostalField().setEditable(true);
		administradorUsuaris.getPoblacioField().setEditable(true);
		administradorUsuaris.getProvinciaComboBox().setEnabled(true);
		administradorUsuaris.getPaisField().setEditable(true);
		administradorUsuaris.getTelefonField().setEditable(true);
		administradorUsuaris.getTipusUsuariComboBox().setEnabled(true);
		administradorUsuaris.getPuntuacioField().setEditable(true);
		administradorUsuaris.getActiuCheckBox().setEnabled(true);

	}

	/**
	 * Esborra els camps amb l'informació que hi hagi
	 * 
	 * @author SergioHernandez
	 */
	private void esborrarDadesUsuaris() {
		administradorUsuaris.getIdUsuariField().setText("");
		administradorUsuaris.getNomField().setText("");
		administradorUsuaris.getCognomsField().setText("");
		administradorUsuaris.getDniNieField().setText("");
		administradorUsuaris.getEmailField().setText("");
		administradorUsuaris.getContrasenyaField().setText("");
		administradorUsuaris.getCarnetField().setText("");
		administradorUsuaris.getAdrecaField().setText("");
		administradorUsuaris.getCodiPostalField().setText("");
		administradorUsuaris.getPoblacioField().setText("");
		administradorUsuaris.getProvinciaComboBox().setSelectedIndex(0);
		administradorUsuaris.getPaisField().setText("");
		administradorUsuaris.getTelefonField().setText("");
		administradorUsuaris.getTipusUsuariComboBox().setSelectedIndex(0);
		administradorUsuaris.getPuntuacioField().setText("");
		administradorUsuaris.getActiuCheckBox().setSelected(false);

	}

	/**
	 * Mostra la informació de l'usuari següent
	 * 
	 * @author SergioHernandez
	 */
	private void pasarUsuariSeguent() {
		if (administradorUsuaris.getRowActiu() + 1 == table.getRowCount()) {
			administradorUsuaris.setRowActiu(table.getRowCount() - 1);
		} else {
			administradorUsuaris.seguentRowActiu(1, table.getRowCount());
			getDadesRow(administradorUsuaris.getRowActiu());

		}

	}

	/**
	 * Mostra la informació de l'usuari anterior
	 * 
	 * @author SergioHernandez
	 */
	private void pasarUsuariAnterior() {
		administradorUsuaris.anteriorRowActiu(1);
		if (administradorUsuaris.getRowActiu() >= 0) {
			getDadesRow(administradorUsuaris.getRowActiu());
		} else {
			administradorUsuaris.setRowActiu(0);
		}
	}

	/**
	 * Missatge que informa que l'acció actual es cancel.larà Alta, editar o baixa
	 * 
	 * @author SergioHernandez
	 */
	public void avisCancellacioAccio() {
		int valor = JOptionPane.showConfirmDialog(administradorUsuaris,
				"Vols cancel.lar l'acció " + getAccio() + "? Perdrás tota la informació", "Cancel.lar " + getAccio(),
				JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null);
		if (valor == JOptionPane.YES_OPTION) {
			setPanelPerDefecte();
		}

	}

	/**
	 * Posa el panel de les dades d'un usuari per defecte
	 * 
	 * @author SergioHernandez
	 */
	private void setPanelPerDefecte() {
		setAccio("default");
		esborrarDadesUsuaris();
		desactivarDadesUsuaris();
		administradorUsuaris.getAltaUsuariButton().setEnabled(true);
		administradorUsuaris.getMostrarButton().setEnabled(true);
		administradorUsuaris.getBaixaUsuariButton().setEnabled(true);
		administradorUsuaris.getEditarUsuariButton().setEnabled(true);
		administradorUsuaris.getConfirmarButton().setVisible(false);
		administradorUsuaris.getCancellarButton().setVisible(false);
		administradorUsuaris.getPaginadorPanel().setVisible(false);
		administradorUsuaris.getActiuCheckBox().setEnabled(false);

	}

	/**
	 * Esborra el camp de cerca
	 * 
	 * @author SergioHernandez
	 */
	private void esborrarCampCerca() {
		administradorUsuaris.getCercaField().setText("");
		administradorUsuaris.getCercaField().requestFocus();

	}

	/**
	 * Mostra les dades de l'usuari seleccionat
	 * 
	 * @author SergioHernandez
	 */
	private void mostrarDadesUsuaris() {
		int rowAMostrar = administradorUsuaris.getUsuarisTable().getSelectedRow();

		administradorUsuaris.setRowActiu(administradorUsuaris.getUsuarisTable().getSelectedRow());
		getDadesRow(rowAMostrar);

	}

	/**
	 * Extreu les dades d'una fila i les introdueix als camps de l'usuari per
	 * mostrar, o editar
	 * 
	 * @param row fila de la table
	 * @author SergioHernandez
	 */
	private void getDadesRow(int row) {
		administradorUsuaris.getRowActualField().setText(String.valueOf(administradorUsuaris.getRowActiu() + 1));
		administradorUsuaris.getRowTotalsField().setText(String.valueOf(table.getRowCount()));
		administradorUsuaris.getUsuarisTable().setRowSelectionInterval(row, row);
		administradorUsuaris.getIdUsuariField().setText(getValorCella(row, 0));
		administradorUsuaris.getNomField().setText(getValorCella(row, 1));
		administradorUsuaris.getCognomsField().setText(getValorCella(row, 2));
		administradorUsuaris.getDniNieField().setText(getValorCella(row, 3));
		administradorUsuaris.getEmailField().setText(getValorCella(row, 4));
		administradorUsuaris.getContrasenyaField().setText(getValorCella(row, 5));

		administradorUsuaris.getAdrecaField().setText(getValorCella(row, 6));

		administradorUsuaris.getCodiPostalField().setText(getValorCella(row, 7));
		administradorUsuaris.getPoblacioField().setText(getValorCella(row, 8));
		administradorUsuaris.getProvinciaComboBox().setSelectedItem(getValorCella(row, 9));
		administradorUsuaris.getPaisField().setText(getValorCella(row, 10));
		administradorUsuaris.getTelefonField().setText(getValorCella(row, 11));
		administradorUsuaris.getCarnetField().setText(getValorCella(row, 12));
		administradorUsuaris.getTipusUsuariComboBox().setSelectedItem(getValorCella(row, 13));
		administradorUsuaris.getPuntuacioField().setText(getValorCella(row, 14));
		if (getValorCella(row, 15).equals("true")) {
			administradorUsuaris.getActiuCheckBox().setSelected(true);
		} else {
			administradorUsuaris.getActiuCheckBox().setSelected(false);
		}

	}

	/**
	 * Converteix la informació d'una cel.la en un String
	 * 
	 * @param row    fila de la table
	 * @param column columna de la table
	 * @return la informació de la cel.la en string
	 * @author SergioHernandez
	 */
	private String getValorCella(int row, int column) {
		return table.getValueAt(row, column).toString();
	}

	/**
	 * Mostra un error si no s'ha seleccionat cap usuari
	 * 
	 * @author SergioHernandez
	 */
	private void errorUsuariNoSeleccionat() {
		JOptionPane.showMessageDialog(administradorUsuaris, "No has seleccionat cap usuari",
				WarningStrings.getString("Error selecció usuari"), JOptionPane.ERROR_MESSAGE);

	}

	/**
	 * Desactiva els camps per que no estiguin editables
	 * 
	 * @author SergioHernandez
	 */
	private void desactivarDadesUsuaris() {
		administradorUsuaris.getIdUsuariField().setEditable(false);
		administradorUsuaris.getNomField().setEditable(false);
		administradorUsuaris.getCognomsField().setEditable(false);
		administradorUsuaris.getDniNieField().setEditable(false);
		administradorUsuaris.getEmailField().setEditable(false);
		administradorUsuaris.getContrasenyaField().setEditable(false);
		administradorUsuaris.getCarnetField().setEditable(false);
		administradorUsuaris.getAdrecaField().setEditable(false);
		administradorUsuaris.getCodiPostalField().setEditable(false);
		administradorUsuaris.getPoblacioField().setEditable(false);
		administradorUsuaris.getProvinciaComboBox().setEnabled(false);
		administradorUsuaris.getPaisField().setEditable(false);
		administradorUsuaris.getTelefonField().setEditable(false);
		administradorUsuaris.getTipusUsuariComboBox().setEnabled(false);
		administradorUsuaris.getPuntuacioField().setEditable(false);
		administradorUsuaris.getActiuCheckBox().setEnabled(false);

	}

	/**
	 * Configurar el panel per mostra les dades d'un usuari
	 * 
	 * @author SergioHernandez
	 */
	private void setDadesUsuarisPerMostrar() {
		administradorUsuaris.getPaginadorPanel().setVisible(true);
		administradorUsuaris.getConfirmarButton().setVisible(false);
		administradorUsuaris.getCancellarButton().setVisible(false);
		administradorUsuaris.omplirCombobox();
	}

	/**
	 * Fa la cerca amb els valor introduïts En aquest cas només mostro la consulta
	 * que s'enviarà al servidor
	 * 
	 * @author SergioHernandez
	 */
	private void ferLaCerca() {
		String textDeLaCerca = administradorUsuaris.getCercaField().getText();
		String[] filtre = getFiltre();
		String tipusUsuariString = administradorUsuaris.getFiltreComboBox().getSelectedItem().toString().toLowerCase();
		

		if (textDeLaCerca.length() == 0) {
			errorCampCercaBuit();
		}else if(filtre[0].equals("carnet") && !isNumeric(textDeLaCerca)){
			errorCampCercaCarnetIncorrecta();
		}else {
			// usuarisModel = new UsuarisModel();
			// usuarisModel.consultarTotsElsUsuarisPerFiltre(filtre + "," +
			// tipusUsuariString + "," + textDeLaCerca );

			// S'envia l'string de cerca al servidor
//			mostraLaCerca(
//					"Filtre: " + filtre + "\n" + "Tipus usuari: " + tipusUsuariString + "\n" + "Text de la cerca: "
//							+ textDeLaCerca + "\n" + "Valors enviats al servidor: " + usuariConnectat.getIdSessio()
//							+ ",consulta_usuari_" + filtre + "," + tipusUsuariString + "," + textDeLaCerca);
			administradorUsuaris.llistarUsuarisConsulta(filtre, textDeLaCerca);

		}

	}

	/**
	 * Mostra un missatge d'error si el text de cerca no és
	 *  númeric
	 */
	private void errorCampCercaCarnetIncorrecta() {
		JOptionPane.showMessageDialog(administradorUsuaris,
				"El camp de cerca per carnet ha de ser numéric",
				"Error cerca carnet",
				JOptionPane.ERROR_MESSAGE);
		
	}

	/**
	 * Missatge que mostra la consulta que s'envia al servidor
	 * 
	 * @param cerca consulta
	 * @author SergioHernandez
	 */
	private void mostraLaCerca(String cerca) {
		JOptionPane.showMessageDialog(administradorUsuaris, cerca, "Dades de la cerca",
				JOptionPane.INFORMATION_MESSAGE);
	}

	/**
	 * Mostra un error si el camp de cerca està buit
	 * 
	 * @author SergioHernandez
	 */
	private void errorCampCercaBuit() {
		JOptionPane.showMessageDialog(administradorUsuaris,
				WarningStrings.getString("ConsultaLlibresNoRegistrat.missatgeCampCercaBuit"),
				WarningStrings.getString("ConsultaLlibresNoRegistrat.titolMissatgeCampCercaBuit"),
				JOptionPane.ERROR_MESSAGE);

	}

	/**
	 * Extreu el filtre que s'ha seleccionat per fer la cerca
	 * 
	 * @return el nom del filtre
	 * @author SergioHernandez
	 */
	private String[] getFiltre() {
		String[] filtre = new String[2];
		ButtonGroup grup = administradorUsuaris.getFiltreButtonGroup();
		if (grup.getSelection().equals(administradorUsuaris.getNomRadioButton().getModel())) {
			filtre[0] = "nom";
		}
		if (grup.getSelection().equals(administradorUsuaris.getCarnetRadioButton().getModel())) {
			filtre[0] = "carnet";
		}
		if (grup.getSelection().equals(administradorUsuaris.getDniNieRadioButton().getModel())) {
			filtre[0] = "dni/nie";
		}
		if(administradorUsuaris.getFiltreComboBox().getSelectedIndex() == 0) {
			filtre[1] = "tots";
		}
		if(administradorUsuaris.getFiltreComboBox().getSelectedIndex() == 1) {
			filtre[1] = "usuari";
		}
		if(administradorUsuaris.getFiltreComboBox().getSelectedIndex() == 2) {
			filtre[1] = "administrador";
		}

		return filtre;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		if (administradorUsuaris.getMostrarContrasenyaLabel() == e.getSource()) {
			administradorUsuaris.getContrasenyaField().setEchoChar((char) 0);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		if (administradorUsuaris.getMostrarContrasenyaLabel() == e.getSource()) {
			administradorUsuaris.getContrasenyaField().setEchoChar('*');
		}
	}

	public String getAccio() {
		return administradorUsuaris.getAccio();

	}

	public void setAccio(String accio) {
		administradorUsuaris.setAccio(accio);
	}
	
	public void setCarnet(int carnet) {
		this.carnet = carnet;
	}
	
	/**
	 * Comprova que un String sigui un número
	 * @param cadena
	 * @return
	 * @author https://es.stackoverflow.com/questions/92139/c%C3%B3mo-verificar-que-el-valor-de-una-variable-string-es-un-integer-en-java
	 */
	 public static boolean isNumeric(String cadena) {

	        boolean resultado;

	        try {
	            Integer.parseInt(cadena);
	            resultado = true;
	        } catch (NumberFormatException excepcion) {
	            resultado = false;
	        }

	        return resultado;
	    }

}
