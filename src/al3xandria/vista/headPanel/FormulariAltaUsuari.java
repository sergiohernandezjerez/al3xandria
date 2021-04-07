package al3xandria.vista.headPanel;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import al3xandria.controlador.FormulariAltaControlador;
import al3xandria.strings.ExternalizeStrings;
import al3xandria.vista.icons.Icons;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;
import javax.swing.SwingConstants;


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
	private JTextField provinciaField;
	private JTextField paisField;
	private JTextField telefonField;
	
	private Icons icones;
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
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		icones = new Icons();
		setTitle(ExternalizeStrings.getString("FormulariAltaUsuari.titol"));
		setBounds(100, 100, 582, 456);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		nomLabel = new JLabel(ExternalizeStrings.getString("FormulariAltaUsuari.nomLabel")); //$NON-NLS-1$
		nomLabel.setBounds(40, 142, 46, 14);
		contentPane.add(nomLabel);
		
		cognomsLabel = new JLabel(ExternalizeStrings.getString("FormulariAltaUsuari.cognomsLabel")); //$NON-NLS-1$
		cognomsLabel.setBounds(308, 142, 64, 14);
		contentPane.add(cognomsLabel);
		
		dniNieComboBox = new JComboBox();
		dniNieComboBox.setToolTipText(ExternalizeStrings.getString("FormulariAltaUsuari.comboBox.toolTipText")); //$NON-NLS-1$
		dniNieComboBox.setModel(new DefaultComboBoxModel(new String[] {"Identificador", "DNI", "NIE"}));
		dniNieComboBox.setBounds(40, 296, 100, 22);
		dniNieComboBox.addItemListener(new FormulariAltaControlador(this));
		contentPane.add(dniNieComboBox);
		
		emailLabel = new JLabel(ExternalizeStrings.getString("FormulariAltaUsuari.emailLabel")); //$NON-NLS-1$
		emailLabel.setBounds(308, 173, 46, 14);
		contentPane.add(emailLabel);
		
		adrecaLabel = new JLabel(ExternalizeStrings.getString("FormulariAltaUsuari.adrecaLabel")); //$NON-NLS-1$
		adrecaLabel.setBounds(40, 173, 64, 14);
		contentPane.add(adrecaLabel);
		
		codiPostalLabel = new JLabel(ExternalizeStrings.getString("FormulariAltaUsuari.codiPostalLabel")); //$NON-NLS-1$
		codiPostalLabel.setBounds(308, 204, 64, 14);
		contentPane.add(codiPostalLabel);
		
		poblacioLabel = new JLabel(ExternalizeStrings.getString("FormulariAltaUsuari.poblacioLabel")); //$NON-NLS-1$
		poblacioLabel.setBounds(40, 204, 64, 14);
		contentPane.add(poblacioLabel);
		
		provinciaLabel = new JLabel(ExternalizeStrings.getString("FormulariAltaUsuari.provinciaLabel")); //$NON-NLS-1$
		provinciaLabel.setBounds(40, 235, 64, 14);
		contentPane.add(provinciaLabel);
		
		paisLabel = new JLabel(ExternalizeStrings.getString("FormulariAltaUsuari.paisLabel")); //$NON-NLS-1$
		paisLabel.setBounds(308, 235, 46, 14);
		contentPane.add(paisLabel);
		
		telefonLabel = new JLabel(ExternalizeStrings.getString("FormulariAltaUsuari.telefonLabel")); //$NON-NLS-1$
		telefonLabel.setBounds(40, 265, 64, 14);
		contentPane.add(telefonLabel);
		
		tipusUsuariLabel = new JLabel(ExternalizeStrings.getString("FormulariAltaUsuari.tipusUsuariLabel")); //$NON-NLS-1$
		tipusUsuariLabel.setBounds(40, 334, 74, 14);
		contentPane.add(tipusUsuariLabel);
		
		titolLabel = new JLabel(ExternalizeStrings.getString("FormulariAltaUsuari.titolLabel")); //$NON-NLS-1$
		titolLabel.setFont(new Font("Tahoma", Font.PLAIN, 26)); 
		titolLabel.setBounds(166, 93, 249, 32);
		contentPane.add(titolLabel);
		
		nomField = new JTextField();
		nomField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		nomField.setToolTipText(ExternalizeStrings.getString("FormulariAltaUsuari.nomFieldToltip")); //$NON-NLS-1$
		nomField.setBounds(117, 136, 140, 24);
		contentPane.add(nomField);
		nomField.setColumns(10);
		
		cognomsField = new JTextField();
		cognomsField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		cognomsField.setToolTipText(ExternalizeStrings.getString("FormulariAltaUsuari.cognomsFieldToltip")); //$NON-NLS-1$
		cognomsField.setColumns(10);
		cognomsField.setBounds(382, 136, 140, 24);
		contentPane.add(cognomsField);
		
		dniNieField = new JTextField();
		dniNieField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		dniNieField.setToolTipText(ExternalizeStrings.getString("FormulariAltaUsuari.dniNieFieldToltipDefault"));
		dniNieField.setColumns(10);
		dniNieField.setBounds(150, 295, 140, 24);
		contentPane.add(dniNieField);
		
		emailField = new JTextField();
		emailField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		emailField.setToolTipText(ExternalizeStrings.getString("FormulariAltaUsuari.emailFieldToltip")); //$NON-NLS-1$
		emailField.setColumns(10);
		emailField.setBounds(382, 167, 140, 24);
		contentPane.add(emailField);
		
		adrecaField = new JTextField();
		adrecaField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		adrecaField.setToolTipText(ExternalizeStrings.getString("FormulariAltaUsuari.adrecaFieldToltip")); //$NON-NLS-1$
		adrecaField.setColumns(10);
		adrecaField.setBounds(117, 168, 140, 24);
		contentPane.add(adrecaField);
		
		codiPostalField = new JTextField();
		codiPostalField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		codiPostalField.setToolTipText(ExternalizeStrings.getString("FormulariAltaUsuari.codiPostalFieldToltip")); //$NON-NLS-1$
		codiPostalField.setColumns(10);
		codiPostalField.setBounds(382, 198, 140, 24);
		contentPane.add(codiPostalField);
		
		poblacioField = new JTextField();
		poblacioField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		poblacioField.setToolTipText(ExternalizeStrings.getString("FormulariAltaUsuari.poblacioFieldToltip")); //$NON-NLS-1$
		poblacioField.setColumns(10);
		poblacioField.setBounds(117, 199, 140, 24);
		contentPane.add(poblacioField);
		
		provinciaField = new JTextField();
		provinciaField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		provinciaField.setToolTipText(ExternalizeStrings.getString("FormulariAltaUsuari.provinciaFieldToltip")); //$NON-NLS-1$
		provinciaField.setColumns(10);
		provinciaField.setBounds(117, 230, 140, 24);
		contentPane.add(provinciaField);
		
		paisField = new JTextField();
		paisField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		paisField.setToolTipText(ExternalizeStrings.getString("FormulariAltaUsuari.paisFieldToltip")); //$NON-NLS-1$
		paisField.setColumns(10);
		paisField.setBounds(382, 230, 140, 24);
		contentPane.add(paisField);
		
		telefonField = new JTextField();
		telefonField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		telefonField.setToolTipText(ExternalizeStrings.getString("FormulariAltaUsuari.telefonFieldToltip")); //$NON-NLS-1$
		telefonField.setColumns(10);
		telefonField.setBounds(117, 260, 140, 24);
		contentPane.add(telefonField);
		
		esborrarButton = new JButton(ExternalizeStrings.getString("FormulariAltaUsuari.esborrarButton")); //$NON-NLS-1$
		esborrarButton.setForeground(Color.WHITE);
		esborrarButton.setBackground(Color.decode("#6a1b9a"));
		esborrarButton.setToolTipText(ExternalizeStrings.getString("FormulariAltaUsuari.esborrarButtonToltip")); //$NON-NLS-1$
		esborrarButton.setBounds(431, 326, 91, 30);
		esborrarButton.addMouseListener(new FormulariAltaControlador(this));
		contentPane.add(esborrarButton);
		
		enviarButton = new JButton(ExternalizeStrings.getString("FormulariAltaUsuari.enviarButton")); //$NON-NLS-1$
		enviarButton.setForeground(Color.WHITE);
		enviarButton.setBackground(Color.decode("#00838f"));
		enviarButton.setToolTipText(ExternalizeStrings.getString("FormulariAltaUsuari.enviarButtonToltip")); //$NON-NLS-1$
		enviarButton.setBounds(324, 367, 91, 30);
		enviarButton.addMouseListener(new FormulariAltaControlador(this));
		contentPane.add(enviarButton);
		
		cancellarButton = new JButton(ExternalizeStrings.getString("FormulariAltaUsuari.cancellarButton")); //$NON-NLS-1$
		cancellarButton.setHorizontalAlignment(SwingConstants.LEFT);
		cancellarButton.setBackground(new Color(165, 42, 42));
		cancellarButton.setForeground(Color.WHITE);
		cancellarButton.setToolTipText(ExternalizeStrings.getString("FormulariAltaUsuari.cancellarButtonToltip")); //$NON-NLS-1$
		cancellarButton.setBounds(431, 367, 91, 30);
		cancellarButton.addMouseListener(new FormulariAltaControlador(this));
		contentPane.add(cancellarButton);
		
		tipusUsuariComboBox = new JComboBox<String>();
		tipusUsuariComboBox.setModel(new DefaultComboBoxModel(new String[] {"Selecciona....", "Estudiant", "Professor"}));
		
		tipusUsuariComboBox.setToolTipText(ExternalizeStrings.getString("FormulariAltaUsuari.tipusUsuariComboBoxToltip")); //$NON-NLS-1$
		tipusUsuariComboBox.setBounds(150, 330, 140, 22);
		contentPane.add(tipusUsuariComboBox);
		
		informacioLabel = new JLabel(ExternalizeStrings.getString("FormulariAltaUsuari.informacioLabel")); //$NON-NLS-1$
		informacioLabel.setFont(new Font("Tahoma", Font.PLAIN, 10));
		informacioLabel.setForeground(Color.RED);
		informacioLabel.setBounds(40, 392, 347, 14);
		contentPane.add(informacioLabel);
		
		logoDeLaplicacio = new JLabel(""); //$NON-NLS-1$
		logoDeLaplicacio.setHorizontalAlignment(SwingConstants.CENTER);
		logoDeLaplicacio.setIcon(icones.getLogoAlexandria());
		logoDeLaplicacio.setToolTipText(ExternalizeStrings.getString(ExternalizeStrings.getString("FormulariAltaUsuari.47"))); //$NON-NLS-1$
		logoDeLaplicacio.setBounds(0, 11, 614, 71);
		contentPane.add(logoDeLaplicacio);
		
		
	}

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

	public JTextField getProvinciaField() {
		return provinciaField;
	}

	public void setProvinciaField(JTextField provinciaField) {
		this.provinciaField = provinciaField;
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
}
