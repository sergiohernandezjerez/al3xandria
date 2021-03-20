package al3xandria.vista.footPanel;

import java.awt.SystemColor;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import al3xandria.strings.ExternalizeStrings;
import al3xandria.vista.icons.Icons;

import javax.swing.JLabel;

import java.awt.Font;
import javax.swing.SwingConstants;

public class FootPanel extends JPanel{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Icons icons;
	
	private JLabel emailUsuariLabel;
	private JLabel idSessioUsuariLabel;
	private JLabel estasConectatComLabel;
	private JLabel tipuUsuariLabel;
	private JLabel emailLabel;
	private JLabel idSessioLabel;
	private JLabel estatUsuariIcon;
	

	public FootPanel() {
		setBorder(new LineBorder(SystemColor.activeCaption));
		setBounds(10, 674, 963, 84);
		setLayout(null);
		
		iniciarComponentes();
		
	}
	
	private void iniciarComponentes() {
		icons = new Icons();
		estasConectatComLabel = new JLabel(ExternalizeStrings.getString("FootPanel.estasConectatComLabelNoConectat")); 
		estasConectatComLabel.setFont(new Font("Tahoma", Font.PLAIN, 9)); //$NON-NLS-1$
		estasConectatComLabel.setBounds(10, 15, 114, 17);
		add(estasConectatComLabel);
		
		tipuUsuariLabel = new JLabel(ExternalizeStrings.getString("FootPanel.tipusUsuariAnominLabel")); 
		tipuUsuariLabel.setFont(new Font("Tahoma", Font.PLAIN, 14)); //$NON-NLS-1$
		tipuUsuariLabel.setBounds(10, 30, 92, 14);
		add(tipuUsuariLabel);
		
		emailLabel = new JLabel(ExternalizeStrings.getString("FootPanel.emailUsuariConectat"));
		emailLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		emailLabel.setBounds(168, 28, 46, 14);
		add(emailLabel);
		
		idSessioLabel = new JLabel(ExternalizeStrings.getString("FootPanel.idSessioUsuariConectat")); 
		idSessioLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		idSessioLabel.setBounds(140, 56, 74, 14);
		add(idSessioLabel);
		
		emailUsuariLabel = new JLabel();
		emailUsuariLabel.setBounds(224, 25, 136, 20);
		add(emailUsuariLabel);

		
		idSessioUsuariLabel = new JLabel();
		idSessioUsuariLabel.setBounds(224, 53, 136, 20);
		add(idSessioUsuariLabel);

		
		estatUsuariIcon = new JLabel(""); 
		setUsuariIconOff();
		estatUsuariIcon.setBounds(10, 50, 46, 32);
		add(estatUsuariIcon);

		
	}
	
	public void setUsuariIconOn() {
		estatUsuariIcon.setIcon(icons.getUsuariConectatIcon());
	}
	
	public void setUsuariIconOff() {
		estatUsuariIcon.setIcon(icons.getUsuariNoConectatIcon());
	}

	
	/*-------------------------- Getters and Setters Methods --------------------------*/
	public JLabel getEmailUsuariLabel() {
		return emailUsuariLabel;
	}

	public void setEmailUsuariLabel(JLabel emailField) {
		this.emailUsuariLabel = emailField;
	}

	public JLabel getIdSessioUsuariLabel() {
		return idSessioUsuariLabel;
	}

	public void setIdSessioUsuariLabel(JLabel idSessioField) {
		this.idSessioUsuariLabel = idSessioField;
	}

	public JLabel getEstasConectatComLabel() {
		return estasConectatComLabel;
	}

	public void setEstasConectatComLabel(JLabel estasConectatComLabel) {
		this.estasConectatComLabel = estasConectatComLabel;
	}

	public JLabel getTipuUsuariLabel() {
		return tipuUsuariLabel;
	}

	public void setTipuUsuariLabel(JLabel tipuUsuariLabel) {
		this.tipuUsuariLabel = tipuUsuariLabel;
	}

	public JLabel getEmailLabel() {
		return emailLabel;
	}

	public void setEmailLabel(JLabel emailLabel) {
		this.emailLabel = emailLabel;
	}

	public JLabel getIdSessioLabel() {
		return idSessioLabel;
	}

	public void setIdSessioLabel(JLabel idSessioLabel) {
		this.idSessioLabel = idSessioLabel;
	}

	
	
	
	
	
}
