package al3xandria.vista.headPanel;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import al3xandria.controlador.formulariAltaUsuari.FormulariAltaControlador;
import al3xandria.controlador.principal.ComportamentTancarAplicacio;
import al3xandria.strings.WarningStrings;
import al3xandria.vista.icons.Icons;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JFormattedTextField;


/**
 * Classe per representar el formulari d'alta d'usuari
 * @author SergioHernandez
 *
 */
public class FormulariAltaUsuari extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nomField;
	private JTextField cognomsField;
	private JTextField dniNieField;
	private JTextField emailField;
	private JTextField adrecaField;
	private JTextField codiPostalField;
	private JTextField poblacioField;
	private JTextField paisField;
	private JTextField telefonField;
	
	private Icons icones;
	private String tipusAccio;
	private JLabel nomLabel;
	private JLabel cognomsLabel;
	private JLabel emailLabel;
	private JLabel adrecaLabel;
	private JLabel logoDeLaplicacio;
	private JLabel informacioLabel;
	private JComboBox<String> tipusUsuariComboBox;
	private JButton cancellarButton;
	private JButton enviarButton;
	private JButton esborrarButton;
	private JLabel titolLabel;
	private JLabel tipusUsuariLabel;
	private JLabel telefonLabel;
	private JLabel paisLabel;
	private JLabel provinciaLabel;
	private JLabel poblacioLabel;
	private JLabel codiPostalLabel;
	private JComboBox<String> dniNieComboBox;
	private JLabel repetirContrasenyaLabel;
	private JPasswordField repetirContrasenyaField;
	private JPasswordField contrasenyaField;
	private JComboBox<String> provinciaComboBox;
	private JLabel contrasenyaLabel;
	private JLabel mostrarContrasenyaIcon;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {					
					FormulariAltaUsuari frame = new FormulariAltaUsuari();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	

	/**
	 * Create the frame.
	 */
	public FormulariAltaUsuari() {
		setTipusAccio("alta");
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(new FormulariAltaControlador(this));
		icones = new Icons();
		setTitle("Formulari alta usuari"); 
		setBounds(100, 100, 600, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		nomLabel = new JLabel(HeadPanelMessages.getString("FormulariAltaUsuari.nomLabel.text"));  //$NON-NLS-1$
		nomLabel.setBounds(40, 142, 46, 14);
		contentPane.add(nomLabel);
		
		cognomsLabel = new JLabel(HeadPanelMessages.getString("FormulariAltaUsuari.cognomsLabel.text"));   //$NON-NLS-1$
		cognomsLabel.setBounds(320, 142, 64, 14);
		contentPane.add(cognomsLabel);
		
		dniNieComboBox = new JComboBox();
		dniNieComboBox.setFont(new Font("Dialog", Font.BOLD, 10));
		dniNieComboBox.setToolTipText(HeadPanelMessages.getString("FormulariAltaUsuari.dniNieComboBox.toolTipText"));   //$NON-NLS-1$
		dniNieComboBox.setModel(new DefaultComboBoxModel(new String[] {"Dni/Nie", "DNI", "NIE"}));   //$NON-NLS-3$
		dniNieComboBox.setBounds(38, 286, 74, 22);
		dniNieComboBox.addItemListener(new FormulariAltaControlador(this));
		contentPane.add(dniNieComboBox);
		
		emailLabel = new JLabel(HeadPanelMessages.getString("FormulariAltaUsuari.emailLabel.text"));   //$NON-NLS-1$
		emailLabel.setBounds(40, 172, 46, 14);
		contentPane.add(emailLabel);
		
		adrecaLabel = new JLabel(HeadPanelMessages.getString("FormulariAltaUsuari.adrecaLabel.text"));   //$NON-NLS-1$
		adrecaLabel.setBounds(40, 200, 64, 14);
		contentPane.add(adrecaLabel);
		
		codiPostalLabel = new JLabel(HeadPanelMessages.getString("FormulariAltaUsuari.codiPostalLabel.text"));   //$NON-NLS-1$
		codiPostalLabel.setBounds(40, 230, 64, 14);
		contentPane.add(codiPostalLabel);
		
		poblacioLabel = new JLabel(HeadPanelMessages.getString("FormulariAltaUsuari.poblacioLabel.text"));   //$NON-NLS-1$
		poblacioLabel.setBounds(320, 200, 64, 14);
		contentPane.add(poblacioLabel);
		
		provinciaLabel = new JLabel(HeadPanelMessages.getString("FormulariAltaUsuari.provinciaLabel.text"));   //$NON-NLS-1$
		provinciaLabel.setBounds(40, 260, 64, 14);
		contentPane.add(provinciaLabel);
		
		paisLabel = new JLabel(HeadPanelMessages.getString("FormulariAltaUsuari.paisLabel.text"));   //$NON-NLS-1$
		paisLabel.setBounds(320, 230, 46, 14);
		contentPane.add(paisLabel);
		
		telefonLabel = new JLabel(HeadPanelMessages.getString("FormulariAltaUsuari.telefonLabel.text"));   //$NON-NLS-1$
		telefonLabel.setBounds(320, 172, 64, 14);
		contentPane.add(telefonLabel);
		
		tipusUsuariLabel = new JLabel(HeadPanelMessages.getString("FormulariAltaUsuari.tipusUsuariLabel.text"));   //$NON-NLS-1$
		tipusUsuariLabel.setBounds(40, 320, 74, 14);
		contentPane.add(tipusUsuariLabel);
		
		titolLabel = new JLabel(HeadPanelMessages.getString("FormulariAltaUsuari.titolLabel.text"));   //$NON-NLS-1$
		titolLabel.setFont(new Font("Tahoma", Font.PLAIN, 26));  
		titolLabel.setBounds(166, 93, 249, 32);
		contentPane.add(titolLabel);
		
		nomField = new JTextField();
		nomField.setFont(new Font("Tahoma", Font.PLAIN, 12)); 
		nomField.setToolTipText(HeadPanelMessages.getString("FormulariAltaUsuari.nomField.toolTipText"));   //$NON-NLS-1$
		nomField.setBounds(117, 136, 140, 24);
		contentPane.add(nomField);
		nomField.setColumns(10);
		
		cognomsField = new JTextField();
		cognomsField.setFont(new Font("Tahoma", Font.PLAIN, 12)); 
		cognomsField.setToolTipText(HeadPanelMessages.getString("FormulariAltaUsuari.cognomsField.toolTipText"));   //$NON-NLS-1$
		cognomsField.setColumns(10);
		cognomsField.setBounds(394, 136, 140, 24);
		contentPane.add(cognomsField);
		
		dniNieField = new JTextField();
		dniNieField.setFont(new Font("Tahoma", Font.PLAIN, 12)); 
		dniNieField.setToolTipText(HeadPanelMessages.getString("FormulariAltaUsuari.dniNieField.toolTipText"));  //$NON-NLS-1$
		dniNieField.setColumns(10);
		dniNieField.setBounds(117, 286, 140, 24);
		contentPane.add(dniNieField);
		
		emailField = new JTextField();
		emailField.setFont(new Font("Tahoma", Font.PLAIN, 12)); 
		emailField.setToolTipText(HeadPanelMessages.getString("FormulariAltaUsuari.emailField.toolTipText"));   //$NON-NLS-1$
		emailField.setColumns(10);
		emailField.setBounds(117, 166, 140, 24);
		contentPane.add(emailField);
		
		adrecaField = new JTextField();
		adrecaField.setFont(new Font("Tahoma", Font.PLAIN, 12)); 
		adrecaField.setToolTipText(HeadPanelMessages.getString("FormulariAltaUsuari.adrecaField.toolTipText"));   //$NON-NLS-1$
		adrecaField.setColumns(10);
		adrecaField.setBounds(117, 196, 140, 24);
		contentPane.add(adrecaField);
		
		codiPostalField = new JTextField();
		codiPostalField.setFont(new Font("Tahoma", Font.PLAIN, 12)); 
		codiPostalField.setToolTipText(HeadPanelMessages.getString("FormulariAltaUsuari.codiPostalField.toolTipText"));   //$NON-NLS-1$
		codiPostalField.setColumns(10);
		codiPostalField.setBounds(117, 226, 140, 24);
		contentPane.add(codiPostalField);
		
		poblacioField = new JTextField();
		poblacioField.setFont(new Font("Tahoma", Font.PLAIN, 12)); 
		poblacioField.setToolTipText(HeadPanelMessages.getString("FormulariAltaUsuari.poblacioField.toolTipText"));   //$NON-NLS-1$
		poblacioField.setColumns(10);
		poblacioField.setBounds(394, 196, 140, 24);
		contentPane.add(poblacioField);
		
		paisField = new JTextField();
		paisField.setFont(new Font("Tahoma", Font.PLAIN, 12)); 
		paisField.setToolTipText(HeadPanelMessages.getString("FormulariAltaUsuari.paisField.toolTipText"));   //$NON-NLS-1$
		paisField.setColumns(10);
		paisField.setBounds(394, 226, 140, 24);
		contentPane.add(paisField);
		
		telefonField = new JTextField();
		telefonField.setFont(new Font("Tahoma", Font.PLAIN, 12)); 
		telefonField.setToolTipText(HeadPanelMessages.getString("FormulariAltaUsuari.telefonField.toolTipText"));   //$NON-NLS-1$
		telefonField.setColumns(10);
		telefonField.setBounds(394, 166, 140, 24);
		contentPane.add(telefonField);
		
		esborrarButton = new JButton(HeadPanelMessages.getString("FormulariAltaUsuari.esborrarButton.text"));   //$NON-NLS-1$
		esborrarButton.setForeground(Color.WHITE);
		esborrarButton.setBackground(Color.decode("#6a1b9a")); 
		esborrarButton.setToolTipText(HeadPanelMessages.getString("FormulariAltaUsuari.esborrarButton.toolTipText"));   //$NON-NLS-1$
		esborrarButton.setBounds(431, 366, 91, 30);
		esborrarButton.addMouseListener(new FormulariAltaControlador(this));
		contentPane.add(esborrarButton);
		
		enviarButton = new JButton(HeadPanelMessages.getString("FormulariAltaUsuari.enviarButton.text"));   //$NON-NLS-1$
		enviarButton.setForeground(Color.WHITE);
		enviarButton.setBackground(Color.decode("#00838f")); 
		enviarButton.setToolTipText(HeadPanelMessages.getString("FormulariAltaUsuari.enviarButton.toolTipText"));   //$NON-NLS-1$
		enviarButton.setBounds(324, 407, 91, 30);
		enviarButton.addMouseListener(new FormulariAltaControlador(this));
		contentPane.add(enviarButton);
		
		cancellarButton = new JButton(HeadPanelMessages.getString("FormulariAltaUsuari.cancellarButton.text"));   //$NON-NLS-1$
		cancellarButton.setHorizontalAlignment(SwingConstants.LEFT);
		cancellarButton.setBackground(new Color(165, 42, 42));
		cancellarButton.setForeground(Color.WHITE);
		cancellarButton.setToolTipText(HeadPanelMessages.getString("FormulariAltaUsuari.cancellarButton.toolTipText"));   //$NON-NLS-1$
		cancellarButton.setBounds(431, 407, 91, 30);
		cancellarButton.addMouseListener(new FormulariAltaControlador(this));
		contentPane.add(cancellarButton);
		
		tipusUsuariComboBox = new JComboBox<String>();
		tipusUsuariComboBox.setModel(new DefaultComboBoxModel(new String[] {"Selecciona....", "Estudiant", "Professor"}));   //$NON-NLS-3$
		
		tipusUsuariComboBox.setToolTipText(HeadPanelMessages.getString("FormulariAltaUsuari.tipusUsuariComboBox.toolTipText"));   //$NON-NLS-1$
		tipusUsuariComboBox.setBounds(117, 316, 140, 22);
		contentPane.add(tipusUsuariComboBox);
		
		informacioLabel = new JLabel(HeadPanelMessages.getString("FormulariAltaUsuari.informacioLabel.text"));   //$NON-NLS-1$
		informacioLabel.setFont(new Font("Tahoma", Font.PLAIN, 10)); 
		informacioLabel.setForeground(Color.RED);
		informacioLabel.setBounds(40, 436, 347, 14);
		contentPane.add(informacioLabel);
		
		logoDeLaplicacio = new JLabel(""); 
		logoDeLaplicacio.setHorizontalAlignment(SwingConstants.CENTER);
		logoDeLaplicacio.setIcon(icones.getLogoAlexandria());
		logoDeLaplicacio.setToolTipText(HeadPanelMessages.getString("FormulariAltaUsuari.logoDeLaplicacio.toolTipText"));  //$NON-NLS-1$
		logoDeLaplicacio.setBounds(0, 11, 614, 71);
		contentPane.add(logoDeLaplicacio);
		
		contrasenyaLabel = new JLabel(HeadPanelMessages.getString("FormulariAltaUsuari.contrasenyaLabel.text"));   //$NON-NLS-1$
		contrasenyaLabel.setBounds(270, 289, 114, 14);
		contentPane.add(contrasenyaLabel);
		
		contrasenyaField = new JPasswordField();
		contrasenyaField.setEchoChar('*');
		contrasenyaField.setToolTipText(HeadPanelMessages.getString("FormulariAltaUsuari.contrasenyaField.toolTipText"));   //$NON-NLS-1$
		contrasenyaField.setText(""); 
		contrasenyaField.setBounds(394, 286, 140, 24);
		contentPane.add(contrasenyaField);
		
		mostrarContrasenyaIcon = new JLabel("");
		mostrarContrasenyaIcon.setToolTipText(HeadPanelMessages.getString("FormulariAltaUsuari.mostrarContrasenyaIcon.toolTipText")); //$NON-NLS-1$
		mostrarContrasenyaIcon.setBounds(539, 288, 33, 16);
		mostrarContrasenyaIcon.setIcon(icones.getMostrarContrasenyaIcon());
		mostrarContrasenyaIcon.addMouseListener(new FormulariAltaControlador(this));
		contentPane.add(mostrarContrasenyaIcon);
		
		repetirContrasenyaLabel = new JLabel(HeadPanelMessages.getString("FormulariAltaUsuari.repetirContrasenyaLabel.text"));   //$NON-NLS-1$
		repetirContrasenyaLabel.setBounds(270, 320, 115, 14);
		contentPane.add(repetirContrasenyaLabel);
		
		repetirContrasenyaField = new JPasswordField();
		repetirContrasenyaField.setEchoChar('*');
		repetirContrasenyaField.setToolTipText(HeadPanelMessages.getString("FormulariAltaUsuari.repetirContrasenyaField.toolTipText"));   //$NON-NLS-1$
		repetirContrasenyaField.setText(""); 
		repetirContrasenyaField.setBounds(394, 316, 140, 24);
		contentPane.add(repetirContrasenyaField);
		
		provinciaComboBox = new JComboBox();
		provinciaComboBox.setToolTipText(HeadPanelMessages.getString("FormulariAltaUsuari.provinciaComboBox.toolTipText"));   //$NON-NLS-1$
		provinciaComboBox.setModel(new DefaultComboBoxModel(new String[] {"Selecciona...", "Barcelona", "Girona", "Lleida", "Tarragona"}));     
		provinciaComboBox.setBounds(117, 256, 140, 22);
		contentPane.add(provinciaComboBox);
		
	}

	
	/*-------------------------- Getters and Setters Methods --------------------------*/
	public JTextField getNomField() {
		return nomField;
	}

	public void setNomField(JTextField nomField) {
		this.nomField = nomField;
	}

	public JTextField getCognomsField() {
		return cognomsField;
	}

	public void setCognomsField(JTextField cognomsField) {
		this.cognomsField = cognomsField;
	}

	public JTextField getDniNieField() {
		return dniNieField;
	}

	public void setDniNieField(JTextField dniNieField) {
		this.dniNieField = dniNieField;
	}

	public JTextField getEmailField() {
		return emailField;
	}

	public void setEmailField(JTextField emailField) {
		this.emailField = emailField;
	}

	public JTextField getAdrecaField() {
		return adrecaField;
	}

	public void setAdrecaField(JTextField adrecaField) {
		this.adrecaField = adrecaField;
	}

	public JTextField getCodiPostalField() {
		return codiPostalField;
	}

	public void setCodiPostalField(JTextField codiPostalField) {
		this.codiPostalField = codiPostalField;
	}

	public JTextField getPoblacioField() {
		return poblacioField;
	}

	public void setPoblacioField(JTextField poblacioField) {
		this.poblacioField = poblacioField;
	}

	public JTextField getPaisField() {
		return paisField;
	}

	public void setPaisField(JTextField paisField) {
		this.paisField = paisField;
	}

	public JTextField getTelefonField() {
		return telefonField;
	}

	public void setTelefonField(JTextField telefonField) {
		this.telefonField = telefonField;
	}

	public JLabel getInformacioLabel() {
		return informacioLabel;
	}

	public void setInformacioLabel(JLabel informacioLabel) {
		this.informacioLabel = informacioLabel;
	}

	public JButton getCancellarButton() {
		return cancellarButton;
	}

	public void setCancellarButton(JButton cancellarButton) {
		this.cancellarButton = cancellarButton;
	}

	public JButton getEnviarButton() {
		return enviarButton;
	}

	public void setEnviarButton(JButton enviarButton) {
		this.enviarButton = enviarButton;
	}

	public JButton getEsborrarButton() {
		return esborrarButton;
	}

	public void setEsborrarButton(JButton esborrarButton) {
		this.esborrarButton = esborrarButton;
	}
	
	public JComboBox<String> getTipusUsuariComboBox() {
		return tipusUsuariComboBox;
	}
	
	public JComboBox<String> getDniNieComboBox() {
		return dniNieComboBox;
	}
	
	public JComboBox<String> getProvinciaComboBox() {
		return provinciaComboBox;
	}
	
	public JPasswordField getContrasenyaField() {
		return contrasenyaField;
	}
	
	public JPasswordField getRepetirContrasenyaField() {
		return repetirContrasenyaField;
	}
		
	/**
	 * Converteix la contrasenya introduïda en un String
	 * @return String  --> contrasenya introduïda per l'usuari
	 * @author SergioHernandez
	 */
	public String getContrasenyaFieldToString() {
		String contrasenya = new String(contrasenyaField.getPassword());
		return contrasenya;
	}
	/**
	 * Converteix la contrasenya introduïda en un String
	 * @return String  --> contrasenya introduïda per l'usuari
	 * @author SergioHernandez
	 */
	public String getRepetirContrasenyaFieldToString() {
		String contrasenya = new String(repetirContrasenyaField.getPassword());
		return contrasenya;
	}
	
	public JLabel getContrasenyaLabel() {
		return contrasenyaLabel;
	}
	
	public JLabel getTitolLabel() {
		return titolLabel;
	}
	
	public JLabel getMostrarContrasenyaIcon() {
		return mostrarContrasenyaIcon;
	}
	
	public String getTipusAccio() {
		return tipusAccio;
	}
	
	public void setTipusAccio(String tipusAccio) {
		this.tipusAccio = tipusAccio;
	}
}
