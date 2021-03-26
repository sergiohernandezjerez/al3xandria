package al3xandria.vista.footPanel;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import al3xandria.strings.ExternalizeStrings;
import al3xandria.vista.icons.Icons;

import javax.swing.JLabel;

import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.GridLayout;
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
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private JLabel lblNewLabel_7;
	private JPanel panel;
	private JLabel lblNewLabel_8;
	private JLabel lblNewLabel_9;
	private JLabel lblNewLabel_10;

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
		gbl_connectPanel.rowHeights = new int[]{29, 29, 29, 0};
		gbl_connectPanel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_connectPanel.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		connectPanel.setLayout(gbl_connectPanel);
				estasConectatComLabel = new JLabel(ExternalizeStrings.getString("FootPanel.estasConectatComLabelNoConectat"));
				estasConectatComLabel.setVerticalAlignment(SwingConstants.BOTTOM);
				GridBagConstraints gbc_estasConectatComLabel = new GridBagConstraints();
				gbc_estasConectatComLabel.fill = GridBagConstraints.BOTH;
				gbc_estasConectatComLabel.insets = new Insets(0, 0, 5, 0);
				gbc_estasConectatComLabel.gridx = 1;
				gbc_estasConectatComLabel.gridy = 0;
				connectPanel.add(estasConectatComLabel, gbc_estasConectatComLabel);
				estasConectatComLabel.setFont(new Font("Tahoma", Font.PLAIN, 9));
		
		
				tipuUsuariLabel = new JLabel(ExternalizeStrings.getString("FootPanel.tipusUsuariAnominLabel"));
				tipuUsuariLabel.setVerticalAlignment(SwingConstants.TOP);
				GridBagConstraints gbc_tipuUsuariLabel = new GridBagConstraints();
				gbc_tipuUsuariLabel.fill = GridBagConstraints.BOTH;
				gbc_tipuUsuariLabel.insets = new Insets(0, 0, 5, 0);
				gbc_tipuUsuariLabel.gridx = 1;
				gbc_tipuUsuariLabel.gridy = 1;
				connectPanel.add(tipuUsuariLabel, gbc_tipuUsuariLabel);
				tipuUsuariLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		estatUsuariIcon = new JLabel(ExternalizeStrings.getString("FootPanel.estatUsuariIcon.text")); //$NON-NLS-1$
		GridBagConstraints gbc_estatUsuariIcon = new GridBagConstraints();
		gbc_estatUsuariIcon.fill = GridBagConstraints.BOTH;
		gbc_estatUsuariIcon.gridx = 1;
		gbc_estatUsuariIcon.gridy = 2;
		connectPanel.add(estatUsuariIcon, gbc_estatUsuariIcon);


		informacioUsuariPanel = new JPanel();
		informacioUsuariPanel.setBackground(Color.WHITE);
		//add(informacioUsuariPanel, BorderLayout.EAST);
		GridBagLayout gbl_informacioUsuariPanel = new GridBagLayout();
		gbl_informacioUsuariPanel.columnWidths = new int[] {50, 75, 30, 20, 10, 30};
		gbl_informacioUsuariPanel.rowHeights = new int[] {14, 14, 14, 14, 0};
		gbl_informacioUsuariPanel.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_informacioUsuariPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0 };
		informacioUsuariPanel.setLayout(gbl_informacioUsuariPanel);
		
				idSessioLabel = new JLabel(ExternalizeStrings.getString("FootPanel.idSessioUsuariConectat"));
				GridBagConstraints gbc_idSessioLabel = new GridBagConstraints();
				gbc_idSessioLabel.anchor = GridBagConstraints.WEST;
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
								
								lblNewLabel_2 = new JLabel(ExternalizeStrings.getString("FootPanel.lblNewLabel_2.text")); //$NON-NLS-1$
								GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
								gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
								gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
								gbc_lblNewLabel_2.gridx = 3;
								gbc_lblNewLabel_2.gridy = 0;
								informacioUsuariPanel.add(lblNewLabel_2, gbc_lblNewLabel_2);
								
								lblNewLabel_3 = new JLabel(ExternalizeStrings.getString("FootPanel.lblNewLabel_3.text")); //$NON-NLS-1$
								GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
								gbc_lblNewLabel_3.anchor = GridBagConstraints.EAST;
								gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
								gbc_lblNewLabel_3.gridx = 4;
								gbc_lblNewLabel_3.gridy = 0;
								informacioUsuariPanel.add(lblNewLabel_3, gbc_lblNewLabel_3);
						
								nomLabel = new JLabel(ExternalizeStrings.getString("FootPanel.nomUsuariConectat"));
								GridBagConstraints gbc_nomLabel = new GridBagConstraints();
								gbc_nomLabel.anchor = GridBagConstraints.WEST;
								gbc_nomLabel.fill = GridBagConstraints.VERTICAL;
								gbc_nomLabel.insets = new Insets(0, 0, 5, 5);
								gbc_nomLabel.gridx = 0;
								gbc_nomLabel.gridy = 1;
								informacioUsuariPanel.add(nomLabel, gbc_nomLabel);
								nomLabel.setHorizontalAlignment(SwingConstants.LEFT);
								
										nomUsuariLabel = new JLabel("nom usuari");
										GridBagConstraints gbc_nomUsuariLabel = new GridBagConstraints();
										gbc_nomUsuariLabel.anchor = GridBagConstraints.WEST;
										gbc_nomUsuariLabel.fill = GridBagConstraints.VERTICAL;
										gbc_nomUsuariLabel.insets = new Insets(0, 0, 5, 5);
										gbc_nomUsuariLabel.gridx = 1;
										gbc_nomUsuariLabel.gridy = 1;
										informacioUsuariPanel.add(nomUsuariLabel, gbc_nomUsuariLabel);
										nomUsuariLabel.setHorizontalAlignment(SwingConstants.LEFT);
						
								prestecsLabel = new JLabel(ExternalizeStrings.getString("FootPanel.prestecUsuariConectat"));
								prestecsLabel.setHorizontalAlignment(SwingConstants.LEFT);
								GridBagConstraints gbc_prestecsLabel = new GridBagConstraints();
								gbc_prestecsLabel.anchor = GridBagConstraints.EAST;
								gbc_prestecsLabel.insets = new Insets(0, 0, 5, 5);
								gbc_prestecsLabel.gridx = 3;
								gbc_prestecsLabel.gridy = 1;
								informacioUsuariPanel.add(prestecsLabel, gbc_prestecsLabel);
						
						prestecsUsuariLabel = new JLabel(ExternalizeStrings.getString("FootPanel.prestecsUsuariLabel.text")); //$NON-NLS-1$
						GridBagConstraints gbc_prestecsUsuariLabel = new GridBagConstraints();
						gbc_prestecsUsuariLabel.anchor = GridBagConstraints.EAST;
						gbc_prestecsUsuariLabel.insets = new Insets(0, 0, 5, 5);
						gbc_prestecsUsuariLabel.gridx = 4;
						gbc_prestecsUsuariLabel.gridy = 1;
						informacioUsuariPanel.add(prestecsUsuariLabel, gbc_prestecsUsuariLabel);
				
						emailLabel = new JLabel(ExternalizeStrings.getString("FootPanel.emailUsuariConectat"));
						GridBagConstraints gbc_emailLabel = new GridBagConstraints();
						gbc_emailLabel.anchor = GridBagConstraints.WEST;
						gbc_emailLabel.fill = GridBagConstraints.VERTICAL;
						gbc_emailLabel.insets = new Insets(0, 0, 5, 5);
						gbc_emailLabel.gridx = 0;
						gbc_emailLabel.gridy = 2;
						informacioUsuariPanel.add(emailLabel, gbc_emailLabel);
						emailLabel.setHorizontalAlignment(SwingConstants.LEFT);
						emailLabel.setBounds(168, 28, 46, 14);
				
						emailUsuariLabel = new JLabel();
						GridBagConstraints gbc_emailUsuariLabel = new GridBagConstraints();
						gbc_emailUsuariLabel.anchor = GridBagConstraints.WEST;
						gbc_emailUsuariLabel.insets = new Insets(0, 0, 5, 5);
						gbc_emailUsuariLabel.fill = GridBagConstraints.VERTICAL;
						gbc_emailUsuariLabel.gridx = 1;
						gbc_emailUsuariLabel.gridy = 2;
						informacioUsuariPanel.add(emailUsuariLabel, gbc_emailUsuariLabel);
						emailUsuariLabel.setBounds(224, 25, 136, 20);
				
						comentarisLabel = new JLabel(ExternalizeStrings.getString("FootPanel.comentarisUsuariConectat"));
						GridBagConstraints gbc_comentarisLabel = new GridBagConstraints();
						gbc_comentarisLabel.anchor = GridBagConstraints.EAST;
						gbc_comentarisLabel.insets = new Insets(0, 0, 5, 5);
						gbc_comentarisLabel.gridx = 3;
						gbc_comentarisLabel.gridy = 2;
						informacioUsuariPanel.add(comentarisLabel, gbc_comentarisLabel);
				
				comentarisUsuariLabel = new JLabel(ExternalizeStrings.getString("FootPanel.comentarisUsuariLabel.text")); //$NON-NLS-1$
				GridBagConstraints gbc_comentarisUsuariLabel = new GridBagConstraints();
				gbc_comentarisUsuariLabel.anchor = GridBagConstraints.EAST;
				gbc_comentarisUsuariLabel.insets = new Insets(0, 0, 5, 5);
				gbc_comentarisUsuariLabel.gridx = 4;
				gbc_comentarisUsuariLabel.gridy = 2;
				informacioUsuariPanel.add(comentarisUsuariLabel, gbc_comentarisUsuariLabel);
		
				carnetLabel = new JLabel(ExternalizeStrings.getString("FootPanel.carnetUsuariConectat"));
				GridBagConstraints gbc_carnetLabel = new GridBagConstraints();
				gbc_carnetLabel.anchor = GridBagConstraints.WEST;
				gbc_carnetLabel.insets = new Insets(0, 0, 5, 5);
				gbc_carnetLabel.fill = GridBagConstraints.VERTICAL;
				gbc_carnetLabel.gridx = 0;
				gbc_carnetLabel.gridy = 3;
				informacioUsuariPanel.add(carnetLabel, gbc_carnetLabel);
		
				carnetUsuariLabel = new JLabel("carnet usuari");
				GridBagConstraints gbc_carnetUsuariLabel = new GridBagConstraints();
				gbc_carnetUsuariLabel.anchor = GridBagConstraints.WEST;
				gbc_carnetUsuariLabel.insets = new Insets(0, 0, 5, 5);
				gbc_carnetUsuariLabel.fill = GridBagConstraints.VERTICAL;
				gbc_carnetUsuariLabel.gridx = 1;
				gbc_carnetUsuariLabel.gridy = 3;
				informacioUsuariPanel.add(carnetUsuariLabel, gbc_carnetUsuariLabel);
				carnetUsuariLabel.setHorizontalAlignment(SwingConstants.LEFT);
		
		lblNewLabel_4 = new JLabel(ExternalizeStrings.getString("FootPanel.lblNewLabel_4.text")); //$NON-NLS-1$
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridx = 3;
		gbc_lblNewLabel_4.gridy = 3;
		informacioUsuariPanel.add(lblNewLabel_4, gbc_lblNewLabel_4);
		
		lblNewLabel_5 = new JLabel(ExternalizeStrings.getString("FootPanel.lblNewLabel_5.text")); //$NON-NLS-1$
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_5.gridx = 4;
		gbc_lblNewLabel_5.gridy = 3;
		informacioUsuariPanel.add(lblNewLabel_5, gbc_lblNewLabel_5);
		
		lblNewLabel = new JLabel(ExternalizeStrings.getString("FootPanel.lblNewLabel.text")); //$NON-NLS-1$
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 4;
		informacioUsuariPanel.add(lblNewLabel, gbc_lblNewLabel);
		
		lblNewLabel_1 = new JLabel(ExternalizeStrings.getString("FootPanel.lblNewLabel_1.text")); //$NON-NLS-1$
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 4;
		informacioUsuariPanel.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		lblNewLabel_6 = new JLabel(ExternalizeStrings.getString("FootPanel.lblNewLabel_6.text")); //$NON-NLS-1$
		GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
		gbc_lblNewLabel_6.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_6.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_6.gridx = 3;
		gbc_lblNewLabel_6.gridy = 4;
		informacioUsuariPanel.add(lblNewLabel_6, gbc_lblNewLabel_6);
		
		lblNewLabel_7 = new JLabel(ExternalizeStrings.getString("FootPanel.lblNewLabel_7.text")); //$NON-NLS-1$
		GridBagConstraints gbc_lblNewLabel_7 = new GridBagConstraints();
		gbc_lblNewLabel_7.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_7.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_7.gridx = 4;
		gbc_lblNewLabel_7.gridy = 4;
		informacioUsuariPanel.add(lblNewLabel_7, gbc_lblNewLabel_7);
		
		panel = new JPanel();
		panel.setBackground(Color.WHITE);
		add(panel, BorderLayout.EAST);
		panel.setLayout(new BorderLayout(0, 0));
		
		lblNewLabel_8 = new JLabel(ExternalizeStrings.getString("FootPanel.lblNewLabel_8.text")); //$NON-NLS-1$
		lblNewLabel_8.setBackground(Color.WHITE);
		panel.add(lblNewLabel_8, BorderLayout.NORTH);
		
		lblNewLabel_9 = new JLabel(ExternalizeStrings.getString("FootPanel.lblNewLabel_9.text")); //$NON-NLS-1$
		lblNewLabel_9.setBackground(Color.WHITE);
		panel.add(lblNewLabel_9, BorderLayout.SOUTH);
		
		lblNewLabel_10 = new JLabel(ExternalizeStrings.getString("FootPanel.lblNewLabel_10.text")); //$NON-NLS-1$
		panel.add(lblNewLabel_10, BorderLayout.EAST);
		
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
