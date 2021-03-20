package al3xandria.vista.headPanel;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import al3xandria.controlador.login.BotoEsborrarDadesLogin;
import al3xandria.controlador.login.BotoLoginLogout;
import al3xandria.strings.ExternalizeStrings;
import al3xandria.vista.centralPanel.CentralPanel;
import al3xandria.vista.footPanel.FootPanel;
import al3xandria.vista.icons.Icons;

public class HeadPanel extends JPanel{
	
	private JTextField emailintroduitPerLusuari;
	private JTextField contrasenyaIntroduidaPerLusuari;
	private JLabel logoDeLaplicacio;
	private JLabel emailLoginLabel;
	private JLabel contrasenyaLoginLabel;
	private JLabel hasOblidatLaContrasenyaLabel;
	private JButton nouUsuariButton;
	private JLabel esborrarDadesLoginLabel;
	private JButton ferLoginButton;
	private Icons icones;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public HeadPanel(FootPanel footPanel, CentralPanel centralPanel) {
		icones = new Icons();
		
		setBorder(new LineBorder(SystemColor.activeCaption));
		setBounds(10, 3, 963, 99);
		setLayout(null);
		
		logoDeLaplicacio = new JLabel(""); 
		logoDeLaplicacio.setIcon(icones.getLogoAlexandria());
		logoDeLaplicacio.setBounds(15, 11, 300, 75);
		logoDeLaplicacio.setToolTipText(ExternalizeStrings.getString("HeadPanel.logoAl3xandriaToltip")); 
		add(logoDeLaplicacio);
		
		emailLoginLabel = new JLabel(ExternalizeStrings.getString("HeadPanel.emailLabel")); 
		emailLoginLabel.setBounds(512, 29, 46, 14);
		add(emailLoginLabel);
		
		contrasenyaLoginLabel = new JLabel(ExternalizeStrings.getString("HeadPanel.contrasenyaLabel")); 
		contrasenyaLoginLabel.setBounds(695, 29, 84, 14);
		add(contrasenyaLoginLabel);
		
		emailintroduitPerLusuari = new JTextField();
		emailintroduitPerLusuari.setFont(new Font("Tahoma", Font.PLAIN, 11)); 
		emailintroduitPerLusuari.setBounds(505, 49, 180, 27);
		emailintroduitPerLusuari.setToolTipText(ExternalizeStrings.getString("HeadPanel.emailIntroduitPerLusuariToltip")); 
		emailintroduitPerLusuari.setColumns(10);
		add(emailintroduitPerLusuari);
		
		contrasenyaIntroduidaPerLusuari = new JPasswordField();
		contrasenyaIntroduidaPerLusuari.setBounds(695, 49, 120, 27);
		contrasenyaIntroduidaPerLusuari.setToolTipText(ExternalizeStrings.getString("HeadPanel.contrasenyaIntroduidaPerLusuariToltip")); 
		contrasenyaIntroduidaPerLusuari.setColumns(10);
		add(contrasenyaIntroduidaPerLusuari);	
			
		hasOblidatLaContrasenyaLabel = new JLabel(ExternalizeStrings.getString("HeadPanel.hasOblidatContrasenyaLabel")); 
		hasOblidatLaContrasenyaLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		hasOblidatLaContrasenyaLabel.setForeground(new Color(0, 128, 0));
		hasOblidatLaContrasenyaLabel.setFont(new Font("Tahoma", Font.PLAIN, 9)); 
		hasOblidatLaContrasenyaLabel.setBounds(700, 75, 120, 14);
		hasOblidatLaContrasenyaLabel.setToolTipText(ExternalizeStrings.getString("HeadPanel.hasOblidatContrasenyaToltip")); 
		add(hasOblidatLaContrasenyaLabel);
		
		nouUsuariButton = new JButton(ExternalizeStrings.getString("HeadPanel.nouUsuariButton")); 
		nouUsuariButton.setBounds(825, 49, 100, 27);
		nouUsuariButton.setToolTipText(ExternalizeStrings.getString("HeadPanel.nouUsuariButtonToltip")); 
		add(nouUsuariButton);
		
		esborrarDadesLoginLabel = new JLabel(ExternalizeStrings.getString("HeadPanel.cancelLabel")); 
		esborrarDadesLoginLabel.setFont(new Font("Tahoma", Font.PLAIN, 9)); 
		esborrarDadesLoginLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		esborrarDadesLoginLabel.setForeground(Color.RED);
		esborrarDadesLoginLabel.setBounds(646, 75, 47, 14);
		esborrarDadesLoginLabel.setToolTipText(ExternalizeStrings.getString("HeadPanel.cancelLabelToltip")); 
		esborrarDadesLoginLabel.addMouseListener(new BotoEsborrarDadesLogin(this));
		add(esborrarDadesLoginLabel);
				
		ferLoginButton = new JButton(ExternalizeStrings.getString("HeadPanel.loginButton")); 
		ferLoginButton.setBounds(825, 18, 100, 27);
		ferLoginButton.setToolTipText(ExternalizeStrings.getString("HeadPanel.loginButtonToltip")); 
		ferLoginButton.addActionListener(new BotoLoginLogout(this, footPanel, centralPanel));
		add(ferLoginButton);
	}
	

	
	
	/*-------------------------- Getters and Setters Methods --------------------------*/
	public JTextField getEmailintroduitPerLusuari() {
		return emailintroduitPerLusuari;
	}

	public void setEmailintroduitPerLusuari(JTextField emailintroduitPerLusuari) {
		this.emailintroduitPerLusuari = emailintroduitPerLusuari;
	}

	public JTextField getContrasenyaIntroduidaPerLusuari() {
		return contrasenyaIntroduidaPerLusuari;
	}

	public void setContrasenyaIntroduidaPerLusuari(JTextField contrasenyaIntroduidaPerLusuari) {
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

	public JLabel getCancelLoginLabel() {
		return esborrarDadesLoginLabel;
	}

	public void setCancelLoginLabel(JLabel cancelLoginLabel) {
		this.esborrarDadesLoginLabel = cancelLoginLabel;
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

	
	
	
	
	
}
