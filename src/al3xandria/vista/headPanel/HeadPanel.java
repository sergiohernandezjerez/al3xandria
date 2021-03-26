package al3xandria.vista.headPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.SystemColor;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import al3xandria.controlador.login.BotoLoginLogout;
import al3xandria.controlador.login.HeadPanelControlador;
import al3xandria.strings.ExternalizeStrings;
import al3xandria.vista.centralPanel.CentralPanel;
import al3xandria.vista.footPanel.FootPanel;
import al3xandria.vista.icons.Icons;

/**
 * clase que crea el HeadPanel
 * 
 * @author SergioHernandez
 *
 */
public class HeadPanel extends JPanel {

	private JTextField emailintroduitPerLusuari;
	private JPasswordField contrasenyaIntroduidaPerLusuari;
	private JLabel logoDeLaplicacio;
	private JLabel emailLoginLabel;
	private JLabel contrasenyaLoginLabel;
	private JLabel hasOblidatLaContrasenyaLabel;
	private JButton nouUsuariButton;
	private JLabel esborrarDadesLoginLabel;
	private JButton ferLoginButton;
	private Icons icones;
	private String tipusUsuari;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel mostrarContrasenya;

	public HeadPanel(FootPanel footPanel, CentralPanel centralPanel) {
		setTipusUsuari(null);
		setBackground(Color.WHITE);
		icones = new Icons();

		setBorder(new LineBorder(SystemColor.activeCaption));
		setLayout(new BorderLayout(0, 0));

		logoDeLaplicacio = new JLabel("");
		logoDeLaplicacio.setIcon(icones.getLogoAlexandria());
		logoDeLaplicacio.setToolTipText(ExternalizeStrings.getString("HeadPanel.logoAl3xandriaToltip"));
		add(logoDeLaplicacio);

		JPanel panelLoginExterior = new JPanel();
		panelLoginExterior.setBackground(Color.WHITE);
		add(panelLoginExterior, BorderLayout.EAST);
		panelLoginExterior.setLayout(new BorderLayout(0, 0));
		
		JLabel borderSuperiorPanelLogin = new JLabel(" ");
		panelLoginExterior.add(borderSuperiorPanelLogin, BorderLayout.NORTH);

		JLabel borderInferiorPanelLogin = new JLabel(" ");
		panelLoginExterior.add(borderInferiorPanelLogin, BorderLayout.SOUTH);

		JLabel borderDretPanelLogin = new JLabel("      ");
		panelLoginExterior.add(borderDretPanelLogin, BorderLayout.EAST);
		
		JPanel panelLoginInterior = new JPanel();
		panelLoginInterior.setBackground(Color.WHITE);
		panelLoginExterior.add(panelLoginInterior, BorderLayout.CENTER);
		GridBagLayout gbl_panelLoginInterior = new GridBagLayout();
		gbl_panelLoginInterior.columnWidths = new int[] { 11, 8, 3, 10 };
		gbl_panelLoginInterior.rowHeights = new int[] { 20, 20, 10, 0 };
		gbl_panelLoginInterior.columnWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_panelLoginInterior.rowWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panelLoginInterior.setLayout(gbl_panelLoginInterior);
		
		
		emailLoginLabel = new JLabel(ExternalizeStrings.getString("HeadPanel.emailLabel"));
		emailLoginLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		GridBagConstraints gbc_emailLoginLabel = new GridBagConstraints();
		gbc_emailLoginLabel.fill = GridBagConstraints.BOTH;
		gbc_emailLoginLabel.insets = new Insets(0, 0, 5, 5);
		gbc_emailLoginLabel.gridx = 0;
		gbc_emailLoginLabel.gridy = 0;
		panelLoginInterior.add(emailLoginLabel, gbc_emailLoginLabel);
		
		JPanel contrasenyaLabelPanel = new JPanel();
		contrasenyaLabelPanel.setBackground(Color.WHITE);
		GridBagConstraints gbc_contrasenyaLabelPanel = new GridBagConstraints();
		gbc_contrasenyaLabelPanel.fill = GridBagConstraints.BOTH;
		gbc_contrasenyaLabelPanel.insets = new Insets(0, 0, 5, 5);
		gbc_contrasenyaLabelPanel.gridx = 1;
		gbc_contrasenyaLabelPanel.gridy = 0;
		panelLoginInterior.add(contrasenyaLabelPanel, gbc_contrasenyaLabelPanel);
		contrasenyaLabelPanel.setLayout(new BorderLayout(0, 0));

		
		contrasenyaLoginLabel = new JLabel(ExternalizeStrings.getString("HeadPanel.contrasenyaLabel"));
		contrasenyaLoginLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		contrasenyaLoginLabel.setHorizontalAlignment(SwingConstants.LEFT);
		contrasenyaLabelPanel.add(contrasenyaLoginLabel);
		
//----
		emailintroduitPerLusuari = new JTextField();
		emailintroduitPerLusuari.setFont(new Font("Tahoma", Font.PLAIN, 11));
		emailintroduitPerLusuari.setToolTipText(ExternalizeStrings.getString("HeadPanel.emailIntroduitPerLusuariToltip"));
		GridBagConstraints gbc_emailintroduitPerLusuari = new GridBagConstraints();
		gbc_emailintroduitPerLusuari.fill = GridBagConstraints.BOTH;
		gbc_emailintroduitPerLusuari.insets = new Insets(0, 0, 5, 5);
		gbc_emailintroduitPerLusuari.gridx = 0;
		gbc_emailintroduitPerLusuari.gridy = 1;
		panelLoginInterior.add(emailintroduitPerLusuari, gbc_emailintroduitPerLusuari);
		emailintroduitPerLusuari.setColumns(17);


		
		contrasenyaIntroduidaPerLusuari = new JPasswordField();
		contrasenyaIntroduidaPerLusuari.setToolTipText(
				ExternalizeStrings.getString("HeadPanel.contrasenyaIntroduidaPerLusuariToltip"));
		contrasenyaIntroduidaPerLusuari.setEchoChar('*');
		GridBagConstraints gbc_contrasenyaIntroduidaPerLusuari = new GridBagConstraints();
		gbc_contrasenyaIntroduidaPerLusuari.fill = GridBagConstraints.BOTH;
		gbc_contrasenyaIntroduidaPerLusuari.insets = new Insets(0, 0, 5, 5);
		gbc_contrasenyaIntroduidaPerLusuari.gridx = 1;
		gbc_contrasenyaIntroduidaPerLusuari.gridy = 1;
		panelLoginInterior.add(contrasenyaIntroduidaPerLusuari, gbc_contrasenyaIntroduidaPerLusuari);
		contrasenyaIntroduidaPerLusuari.setColumns(10);
		

		hasOblidatLaContrasenyaLabel = new JLabel(
				ExternalizeStrings.getString("HeadPanel.hasOblidatContrasenyaLabel"));
		hasOblidatLaContrasenyaLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		hasOblidatLaContrasenyaLabel.setForeground(Color.decode("#00838f"));
		hasOblidatLaContrasenyaLabel.setFont(new Font("Tahoma", Font.PLAIN, 9));
		hasOblidatLaContrasenyaLabel.setToolTipText(ExternalizeStrings.getString("HeadPanel.hasOblidatContrasenyaToltip"));
		hasOblidatLaContrasenyaLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		hasOblidatLaContrasenyaLabel.setVerticalAlignment(SwingConstants.TOP);
		GridBagConstraints gbc_hasOblidatLaContrasenyaLabel = new GridBagConstraints();
		gbc_hasOblidatLaContrasenyaLabel.fill = GridBagConstraints.BOTH;
		gbc_hasOblidatLaContrasenyaLabel.insets = new Insets(0, 0, 0, 5);
		gbc_hasOblidatLaContrasenyaLabel.gridx = 1;
		gbc_hasOblidatLaContrasenyaLabel.gridy = 2;
		panelLoginInterior.add(hasOblidatLaContrasenyaLabel, gbc_hasOblidatLaContrasenyaLabel);
		
		
		
		nouUsuariButton = new JButton(
				ExternalizeStrings.getString("HeadPanel.nouUsuariButton"));
		nouUsuariButton.setForeground(Color.WHITE);
		nouUsuariButton.setBackground(Color.decode("#00838f"));
		nouUsuariButton.setToolTipText(ExternalizeStrings.getString("HeadPanel.nouUsuariButtonToltip"));
		GridBagConstraints gbc_nouUsuariButton = new GridBagConstraints();
		gbc_nouUsuariButton.fill = GridBagConstraints.BOTH;
		gbc_nouUsuariButton.insets = new Insets(0, 0, 5, 0);
		gbc_nouUsuariButton.gridx = 2;
		gbc_nouUsuariButton.gridy = 1;
		panelLoginInterior.add(nouUsuariButton, gbc_nouUsuariButton);

		esborrarDadesLoginLabel = new JLabel(
				ExternalizeStrings.getString("HeadPanel.cancelLabel"));
		esborrarDadesLoginLabel.setFont(new Font("Tahoma", Font.PLAIN, 9));
		esborrarDadesLoginLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		esborrarDadesLoginLabel.setForeground(Color.decode("#38006b"));
		esborrarDadesLoginLabel.setToolTipText(ExternalizeStrings.getString("HeadPanel.cancelLabelToltip"));
		esborrarDadesLoginLabel.addMouseListener(new HeadPanelControlador(this));
		esborrarDadesLoginLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		esborrarDadesLoginLabel.setVerticalAlignment(SwingConstants.TOP);
		GridBagConstraints gbc_esborrarDadesLoginLabel = new GridBagConstraints();
		gbc_esborrarDadesLoginLabel.fill = GridBagConstraints.BOTH;
		gbc_esborrarDadesLoginLabel.insets = new Insets(0, 0, 0, 5);
		gbc_esborrarDadesLoginLabel.gridx = 0;
		gbc_esborrarDadesLoginLabel.gridy = 2;
		panelLoginInterior.add(esborrarDadesLoginLabel, gbc_esborrarDadesLoginLabel);
		
		ferLoginButton = new JButton(
				ExternalizeStrings.getString("HeadPanel.loginButton"));
		ferLoginButton.setForeground(Color.WHITE);
		ferLoginButton.setBackground(Color.decode("#6a1b9a"));
		ferLoginButton.setToolTipText(ExternalizeStrings.getString("HeadPanel.loginButtonToltip"));
		ferLoginButton.addActionListener(new BotoLoginLogout(this, footPanel, centralPanel));
		GridBagConstraints gbc_ferLoginButton = new GridBagConstraints();
		gbc_ferLoginButton.fill = GridBagConstraints.BOTH;
		gbc_ferLoginButton.insets = new Insets(0, 0, 5, 0);
		gbc_ferLoginButton.gridx = 2;
		gbc_ferLoginButton.gridy = 0;
		panelLoginInterior.add(ferLoginButton, gbc_ferLoginButton);

		mostrarContrasenya = new JLabel("");
		mostrarContrasenya.setIcon(icones.getMostrarContrasenyaIcon());
		mostrarContrasenya.setVerticalAlignment(SwingConstants.BOTTOM);
		mostrarContrasenya.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mostrarContrasenya.setToolTipText(ExternalizeStrings.getString("HeadPanel.mostrarContrasenyaToltip"));
		mostrarContrasenya.addMouseListener(new HeadPanelControlador(this));
		mostrarContrasenya.setHorizontalAlignment(SwingConstants.LEFT);
		contrasenyaLabelPanel.add(mostrarContrasenya, BorderLayout.EAST);

		
		
	}
	
	
	/**
	 * Converteix la contrasenya introduïda en un String
	 * @return String  --> contrasenya introduïda per l'usuari
	 * @author SergioHernandez
	 */
	public String getContrasenyaIntroduidaPerLusuariToString() {
		String contrasenya = new String(contrasenyaIntroduidaPerLusuari.getPassword());
		return contrasenya;
	}
	
	public void esborraEmailContrasenyaIntroduits() {
		emailintroduitPerLusuari.setText("");
		contrasenyaIntroduidaPerLusuari.setText("");
	}
	

	/*-------------------------- Getters and Setters Methods --------------------------*/
	public JTextField getEmailintroduitPerLusuari() {
		return emailintroduitPerLusuari;
	}

	public void setEmailintroduitPerLusuari(JTextField emailintroduitPerLusuari) {
		this.emailintroduitPerLusuari = emailintroduitPerLusuari;
	}

	public JPasswordField getContrasenyaIntroduidaPerLusuari() {
		return contrasenyaIntroduidaPerLusuari;
	}

	
	public void setContrasenyaIntroduidaPerLusuari(JPasswordField contrasenyaIntroduidaPerLusuari) {
		this.contrasenyaIntroduidaPerLusuari = contrasenyaIntroduidaPerLusuari;
	}

	public JLabel getLogoDeLaplicacio() {
		return logoDeLaplicacio;
	}

	public void setLogoDeLaplicacio(JLabel logoDeLaplicacio) {
		this.logoDeLaplicacio = logoDeLaplicacio;
	}

	public JLabel getEmailLoginLabel() {
		return emailLoginLabel;
	}

	public void setEmailLoginLabel(JLabel emailLoginLabel) {
		this.emailLoginLabel = emailLoginLabel;
	}

	public JLabel getContrasenyaLoginLabel() {
		return contrasenyaLoginLabel;
	}

	public void setContrasenyaLoginLabel(JLabel contrasenyaLoginLabel) {
		this.contrasenyaLoginLabel = contrasenyaLoginLabel;
	}

	public JLabel getHasOblidatLaContrasenyaLabel() {
		return hasOblidatLaContrasenyaLabel;
	}

	public void setHasOblidatLaContrasenyaLabel(JLabel hasOblidatLaContrasenyaLabel) {
		this.hasOblidatLaContrasenyaLabel = hasOblidatLaContrasenyaLabel;
	}

	public JButton getNouUsuariButton() {
		return nouUsuariButton;
	}

	public void setNouUsuariButton(JButton nouUsuariButton) {
		this.nouUsuariButton = nouUsuariButton;
	}

	public JLabel getEsborrarDadesLoginLabel() {
		return esborrarDadesLoginLabel;
	}

	public void setEsborrarDadesLoginLabel(JLabel esborrarDadesLoginLabel) {
		this.esborrarDadesLoginLabel = esborrarDadesLoginLabel;
	}

	public JButton getFerLoginButton() {
		return ferLoginButton;
	}

	public void setFerLoginButton(JButton ferLoginButton) {
		this.ferLoginButton = ferLoginButton;
	}

	public Icons getIcones() {
		return icones;
	}

	public void setIcones(Icons icones) {
		this.icones = icones;
	}

	public void setMostrarContrasenya(JLabel mostrarContrasenya) {
		this.mostrarContrasenya = mostrarContrasenya;
	}

	public JLabel getMostrarContrasenya() {
		return mostrarContrasenya;
	}

	public void setTipusUsuari(String tipusUsuari) {
		this.tipusUsuari = tipusUsuari;
	}
	
	public String getTipusUsuari() {
		return tipusUsuari;
	}
	
	
}
