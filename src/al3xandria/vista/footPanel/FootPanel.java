package al3xandria.vista.footPanel;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import al3xandria.vista.icons.Icons;

import javax.swing.JLabel;

import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.BorderLayout;

/**
 * clase que crear el panel FootPanel
 * 
 * @author SergioHernandez
 *
 */
public class FootPanel extends JPanel {

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
	private JLabel nomLabel;
	private JLabel nomUsuariLabel;
	private JLabel carnetLabel;
	private JLabel carnetUsuariLabel;
	private JPanel connectPanel;
	private JPanel informacioUsuariPanel;
	private JLabel prestecsLabel;
	private JLabel comentarisLabel;
	private JLabel prestecsUsuariLabel;
	private JLabel comentarisUsuariLabel;
	private JLabel puntuacioLabel;
	private JLabel puntuacioUsuariLabel;
	private JLabel reservesLabel;
	private JLabel reservesUsuariLabel;
	private JLabel avisosLabel;
	private JLabel avisosUsuariLabel;
	private JPanel panel;
	private JLabel borderTopLabel;
	private JLabel borderBottomLabel;
	private JLabel borderRightLabel;

	public FootPanel() {
		setBackground(Color.WHITE);
		setBorder(new LineBorder(Color.decode("#00838f")));

		iniciarComponentes();

	}

	private void iniciarComponentes() {
		icons = new Icons();
		setLayout(new BorderLayout(0, 0));

		connectPanel = new JPanel();
		connectPanel.setBackground(Color.WHITE);
		add(connectPanel, BorderLayout.WEST);
		GridBagLayout gbl_connectPanel = new GridBagLayout();
		gbl_connectPanel.columnWidths = new int[] {20, 83, 0};
		gbl_connectPanel.rowHeights = new int[]{22, 22, 22, 0};
		gbl_connectPanel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_connectPanel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		connectPanel.setLayout(gbl_connectPanel);
				estasConectatComLabel = new JLabel(FootPanelMessages.getString("FootPanel.usuariNoConnectat.text")); //$NON-NLS-1$
				estasConectatComLabel.setVerticalAlignment(SwingConstants.BOTTOM);
				GridBagConstraints gbc_estasConectatComLabel = new GridBagConstraints();
				gbc_estasConectatComLabel.fill = GridBagConstraints.BOTH;
				gbc_estasConectatComLabel.insets = new Insets(0, 0, 5, 0);
				gbc_estasConectatComLabel.gridx = 1;
				gbc_estasConectatComLabel.gridy = 0;
				connectPanel.add(estasConectatComLabel, gbc_estasConectatComLabel);
				estasConectatComLabel.setFont(new Font("Tahoma", Font.PLAIN, 9)); 
		
				tipuUsuariLabel = new JLabel(FootPanelMessages.getString("FootPanel.tipusUsuariAnominLabel"));
				tipuUsuariLabel.setVerticalAlignment(SwingConstants.TOP);
				GridBagConstraints gbc_tipuUsuariLabel = new GridBagConstraints();
				gbc_tipuUsuariLabel.fill = GridBagConstraints.BOTH;
				gbc_tipuUsuariLabel.insets = new Insets(0, 0, 5, 0);
				gbc_tipuUsuariLabel.gridx = 1;
				gbc_tipuUsuariLabel.gridy = 1;
				connectPanel.add(tipuUsuariLabel, gbc_tipuUsuariLabel);
				tipuUsuariLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		estatUsuariIcon = new JLabel(""); 
		GridBagConstraints gbc_estatUsuariIcon = new GridBagConstraints();
		gbc_estatUsuariIcon.fill = GridBagConstraints.BOTH;
		gbc_estatUsuariIcon.gridx = 1;
		gbc_estatUsuariIcon.gridy = 2;
		connectPanel.add(estatUsuariIcon, gbc_estatUsuariIcon);


		informacioUsuariPanel = new JPanel();
		informacioUsuariPanel.setBackground(Color.WHITE);
		GridBagLayout gbl_informacioUsuariPanel = new GridBagLayout();
		gbl_informacioUsuariPanel.columnWidths = new int[] {50, 75, 30, 20, 0, 30, 10, 10};
		gbl_informacioUsuariPanel.rowHeights = new int[] {14, 14, 14};
		gbl_informacioUsuariPanel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 };
		gbl_informacioUsuariPanel.rowWeights = new double[] { 0.0, 0.0, 0.0 };
		informacioUsuariPanel.setLayout(gbl_informacioUsuariPanel);
		
				idSessioLabel = new JLabel(FootPanelMessages.getString("FootPanel.idSessioLabel.text")); //$NON-NLS-1$
				GridBagConstraints gbc_idSessioLabel = new GridBagConstraints();
				gbc_idSessioLabel.anchor = GridBagConstraints.EAST;
				gbc_idSessioLabel.fill = GridBagConstraints.VERTICAL;
				gbc_idSessioLabel.insets = new Insets(0, 0, 5, 5);
				gbc_idSessioLabel.gridx = 0;
				gbc_idSessioLabel.gridy = 0;
				informacioUsuariPanel.add(idSessioLabel, gbc_idSessioLabel);
				idSessioLabel.setHorizontalAlignment(SwingConstants.LEFT);
				idSessioLabel.setBounds(140, 56, 74, 14);
		
				idSessioUsuariLabel = new JLabel();
				GridBagConstraints gbc_idSessioUsuariLabel = new GridBagConstraints();
				gbc_idSessioUsuariLabel.anchor = GridBagConstraints.WEST;
				gbc_idSessioUsuariLabel.fill = GridBagConstraints.VERTICAL;
				gbc_idSessioUsuariLabel.insets = new Insets(0, 0, 5, 5);
				gbc_idSessioUsuariLabel.gridx = 1;
				gbc_idSessioUsuariLabel.gridy = 0;
				informacioUsuariPanel.add(idSessioUsuariLabel, gbc_idSessioUsuariLabel);
				idSessioUsuariLabel.setBounds(224, 53, 136, 20);
								
								puntuacioLabel = new JLabel(FootPanelMessages.getString("FootPanel.puntuacioLabel.text"));  //$NON-NLS-1$
								GridBagConstraints gbc_puntuacioLabel = new GridBagConstraints();
								gbc_puntuacioLabel.anchor = GridBagConstraints.EAST;
								gbc_puntuacioLabel.insets = new Insets(0, 0, 5, 5);
								gbc_puntuacioLabel.gridx = 3;
								gbc_puntuacioLabel.gridy = 0;
								informacioUsuariPanel.add(puntuacioLabel, gbc_puntuacioLabel);
								
								puntuacioUsuariLabel = new JLabel(""); 
								GridBagConstraints gbc_puntuacioUsuariLabel = new GridBagConstraints();
								gbc_puntuacioUsuariLabel.anchor = GridBagConstraints.WEST;
								gbc_puntuacioUsuariLabel.insets = new Insets(0, 0, 5, 5);
								gbc_puntuacioUsuariLabel.gridx = 4;
								gbc_puntuacioUsuariLabel.gridy = 0;
								informacioUsuariPanel.add(puntuacioUsuariLabel, gbc_puntuacioUsuariLabel);
								
								avisosLabel = new JLabel(FootPanelMessages.getString("FootPanel.avisosLabel.text"));  //$NON-NLS-1$
								GridBagConstraints gbc_avisosLabel = new GridBagConstraints();
								gbc_avisosLabel.insets = new Insets(0, 0, 5, 5);
								gbc_avisosLabel.anchor = GridBagConstraints.EAST;
								gbc_avisosLabel.gridx = 6;
								gbc_avisosLabel.gridy = 0;
								informacioUsuariPanel.add(avisosLabel, gbc_avisosLabel);
								
								avisosUsuariLabel = new JLabel(""); 
								GridBagConstraints gbc_avisosUsuariLabel = new GridBagConstraints();
								gbc_avisosUsuariLabel.insets = new Insets(0, 0, 5, 0);
								gbc_avisosUsuariLabel.anchor = GridBagConstraints.WEST;
								gbc_avisosUsuariLabel.gridx = 7;
								gbc_avisosUsuariLabel.gridy = 0;
								informacioUsuariPanel.add(avisosUsuariLabel, gbc_avisosUsuariLabel);
						
								nomLabel = new JLabel(FootPanelMessages.getString("FootPanel.nomLabel.text")); //$NON-NLS-1$
								GridBagConstraints gbc_nomLabel = new GridBagConstraints();
								gbc_nomLabel.anchor = GridBagConstraints.EAST;
								gbc_nomLabel.fill = GridBagConstraints.VERTICAL;
								gbc_nomLabel.insets = new Insets(0, 0, 5, 5);
								gbc_nomLabel.gridx = 0;
								gbc_nomLabel.gridy = 1;
								informacioUsuariPanel.add(nomLabel, gbc_nomLabel);
								nomLabel.setHorizontalAlignment(SwingConstants.LEFT);
								
										nomUsuariLabel = new JLabel(""); 
										GridBagConstraints gbc_nomUsuariLabel = new GridBagConstraints();
										gbc_nomUsuariLabel.anchor = GridBagConstraints.WEST;
										gbc_nomUsuariLabel.fill = GridBagConstraints.VERTICAL;
										gbc_nomUsuariLabel.insets = new Insets(0, 0, 5, 5);
										gbc_nomUsuariLabel.gridx = 1;
										gbc_nomUsuariLabel.gridy = 1;
										informacioUsuariPanel.add(nomUsuariLabel, gbc_nomUsuariLabel);
										nomUsuariLabel.setHorizontalAlignment(SwingConstants.LEFT);
						
								prestecsLabel = new JLabel(FootPanelMessages.getString("FootPanel.prestecsLabel.text")); //$NON-NLS-1$
								prestecsLabel.setHorizontalAlignment(SwingConstants.LEFT);
								GridBagConstraints gbc_prestecsLabel = new GridBagConstraints();
								gbc_prestecsLabel.anchor = GridBagConstraints.EAST;
								gbc_prestecsLabel.insets = new Insets(0, 0, 5, 5);
								gbc_prestecsLabel.gridx = 3;
								gbc_prestecsLabel.gridy = 1;
								informacioUsuariPanel.add(prestecsLabel, gbc_prestecsLabel);
						
						prestecsUsuariLabel = new JLabel(""); 
						GridBagConstraints gbc_prestecsUsuariLabel = new GridBagConstraints();
						gbc_prestecsUsuariLabel.anchor = GridBagConstraints.WEST;
						gbc_prestecsUsuariLabel.insets = new Insets(0, 0, 5, 5);
						gbc_prestecsUsuariLabel.gridx = 4;
						gbc_prestecsUsuariLabel.gridy = 1;
						informacioUsuariPanel.add(prestecsUsuariLabel, gbc_prestecsUsuariLabel);
						
						reservesLabel = new JLabel(FootPanelMessages.getString("FootPanel.reservesLabel.text"));  //$NON-NLS-1$
						GridBagConstraints gbc_reservesLabel = new GridBagConstraints();
						gbc_reservesLabel.anchor = GridBagConstraints.EAST;
						gbc_reservesLabel.insets = new Insets(0, 0, 5, 5);
						gbc_reservesLabel.gridx = 6;
						gbc_reservesLabel.gridy = 1;
						informacioUsuariPanel.add(reservesLabel, gbc_reservesLabel);
						
						reservesUsuariLabel = new JLabel(""); 
						GridBagConstraints gbc_reservesUsuariLabel = new GridBagConstraints();
						gbc_reservesUsuariLabel.anchor = GridBagConstraints.WEST;
						gbc_reservesUsuariLabel.insets = new Insets(0, 0, 5, 0);
						gbc_reservesUsuariLabel.gridx = 7;
						gbc_reservesUsuariLabel.gridy = 1;
						informacioUsuariPanel.add(reservesUsuariLabel, gbc_reservesUsuariLabel);
				
						emailLabel = new JLabel(FootPanelMessages.getString("FootPanel.emailLabel.text")); //$NON-NLS-1$
						GridBagConstraints gbc_emailLabel = new GridBagConstraints();
						gbc_emailLabel.anchor = GridBagConstraints.EAST;
						gbc_emailLabel.fill = GridBagConstraints.VERTICAL;
						gbc_emailLabel.insets = new Insets(0, 0, 0, 5);
						gbc_emailLabel.gridx = 0;
						gbc_emailLabel.gridy = 2;
						informacioUsuariPanel.add(emailLabel, gbc_emailLabel);
						emailLabel.setHorizontalAlignment(SwingConstants.LEFT);
						emailLabel.setBounds(168, 28, 46, 14);
				
						emailUsuariLabel = new JLabel();
						GridBagConstraints gbc_emailUsuariLabel = new GridBagConstraints();
						gbc_emailUsuariLabel.anchor = GridBagConstraints.WEST;
						gbc_emailUsuariLabel.insets = new Insets(0, 0, 0, 5);
						gbc_emailUsuariLabel.fill = GridBagConstraints.VERTICAL;
						gbc_emailUsuariLabel.gridx = 1;
						gbc_emailUsuariLabel.gridy = 2;
						informacioUsuariPanel.add(emailUsuariLabel, gbc_emailUsuariLabel);
						emailUsuariLabel.setBounds(224, 25, 136, 20);
						
								carnetLabel = new JLabel(FootPanelMessages.getString("FootPanel.carnetLabel.text")); //$NON-NLS-1$
								GridBagConstraints gbc_carnetLabel = new GridBagConstraints();
								gbc_carnetLabel.anchor = GridBagConstraints.EAST;
								gbc_carnetLabel.insets = new Insets(0, 0, 0, 5);
								gbc_carnetLabel.fill = GridBagConstraints.VERTICAL;
								gbc_carnetLabel.gridx = 3;
								gbc_carnetLabel.gridy = 2;
								informacioUsuariPanel.add(carnetLabel, gbc_carnetLabel);
						
								carnetUsuariLabel = new JLabel(""); 
								GridBagConstraints gbc_carnetUsuariLabel = new GridBagConstraints();
								gbc_carnetUsuariLabel.anchor = GridBagConstraints.WEST;
								gbc_carnetUsuariLabel.insets = new Insets(0, 0, 0, 5);
								gbc_carnetUsuariLabel.fill = GridBagConstraints.VERTICAL;
								gbc_carnetUsuariLabel.gridx = 4;
								gbc_carnetUsuariLabel.gridy = 2;
								informacioUsuariPanel.add(carnetUsuariLabel, gbc_carnetUsuariLabel);
								carnetUsuariLabel.setHorizontalAlignment(SwingConstants.LEFT);
				
						comentarisLabel = new JLabel(FootPanelMessages.getString("FootPanel.comentarisLabel.text")); //$NON-NLS-1$
						GridBagConstraints gbc_comentarisLabel = new GridBagConstraints();
						gbc_comentarisLabel.anchor = GridBagConstraints.EAST;
						gbc_comentarisLabel.insets = new Insets(0, 0, 0, 5);
						gbc_comentarisLabel.gridx = 6;
						gbc_comentarisLabel.gridy = 2;
						informacioUsuariPanel.add(comentarisLabel, gbc_comentarisLabel);
				
				comentarisUsuariLabel = new JLabel(""); 
				GridBagConstraints gbc_comentarisUsuariLabel = new GridBagConstraints();
				gbc_comentarisUsuariLabel.anchor = GridBagConstraints.WEST;
				gbc_comentarisUsuariLabel.gridx = 7;
				gbc_comentarisUsuariLabel.gridy = 2;
				informacioUsuariPanel.add(comentarisUsuariLabel, gbc_comentarisUsuariLabel);
		
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		add(panel, BorderLayout.EAST);
		panel.setLayout(new BorderLayout(0, 0));
		
		borderTopLabel = new JLabel("    "); 
		borderTopLabel.setBackground(Color.WHITE);
		panel.add(borderTopLabel, BorderLayout.NORTH);
		
		borderBottomLabel = new JLabel("    "); 
		borderBottomLabel.setBackground(Color.WHITE);
		panel.add(borderBottomLabel, BorderLayout.SOUTH);
		
		borderRightLabel = new JLabel("          "); 
		panel.add(borderRightLabel, BorderLayout.EAST);
		
		panel.add(informacioUsuariPanel, BorderLayout.CENTER);
		setUsuariIconOff();

	}

	/**
	 * Posa l'icone d'usuari actiu en ON
	 * 
	 * @author SergioHernandez
	 */
	public void setUsuariIconOn() {
		estatUsuariIcon.setIcon(icons.getUsuariConectatIcon());
	}

	/**
	 * Posa l'icone d'usuari actiu en OFF
	 * 
	 * @author SergioHernandez
	 */
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
