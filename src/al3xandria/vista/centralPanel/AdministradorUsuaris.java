package al3xandria.vista.centralPanel;

import javax.swing.JPanel;
import java.awt.Rectangle;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import javax.swing.Icon;

import java.awt.GridLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.TableColumnModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import al3xandria.controlador.login.HeadPanelControlador;
import al3xandria.strings.ExternalizeStrings;
import al3xandria.vista.icons.Icons;

import javax.swing.border.EtchedBorder;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import java.awt.Dimension;
import java.awt.ComponentOrientation;
import java.awt.Cursor;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPasswordField;

public class AdministradorUsuaris extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField cercaField;
	private JTable llibresTable;
	private JTextField idUsuariField;
	private JTextField nomField;
	private JTextField poblacioField;
	private JTextField paisField;
	private JTextField carnetField;
	private JTextField adrecaField;
	private JTextField codiPostalField;
	
	private Icons icones;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField cognomsField;
	private JTextField dniNieField;
	private JTextField emailField;
	private JPasswordField contrasenyaField;
	private JTextField telefonField;
	private JTextField puntuacioField;

	/**
	 * Create the panel.
	 */
	public AdministradorUsuaris() {
		icones = new Icons();
		setBounds(new Rectangle(0, 0, 750, 850));
		setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		
		JPanel llibresPanel = new JPanel();
		add(llibresPanel);
		llibresPanel.setLayout(new BorderLayout(0, 10));
		
		
		JPanel filtresPanel = new JPanel();
		llibresPanel.add(filtresPanel, BorderLayout.NORTH);
		filtresPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel filtreButtonPanel = new JPanel();
		FlowLayout fl_filtreButtonPanel = (FlowLayout) filtreButtonPanel.getLayout();
		fl_filtreButtonPanel.setAlignment(FlowLayout.LEFT);
		filtresPanel.add(filtreButtonPanel, BorderLayout.WEST);
		
		JLabel filtrePerLabel = new JLabel(CentralPanelMessages.getString("AdministradorUsuaris.filtrePerLabel.text")); //$NON-NLS-1$
		filtrePerLabel.setToolTipText(CentralPanelMessages.getString("AdministradorUsuaris.filtrePerLabel.toolTipText")); //$NON-NLS-1$
		filtrePerLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		filtreButtonPanel.add(filtrePerLabel);
		
		JRadioButton nomRadioButton = new JRadioButton(CentralPanelMessages.getString("AdministradorUsuaris.nomRadioButton.text")); //$NON-NLS-1$
		nomRadioButton.setSelected(true);
		buttonGroup.add(nomRadioButton);
		nomRadioButton.setToolTipText(CentralPanelMessages.getString("AdministradorUsuaris.nomRadioButton.toolTipText")); //$NON-NLS-1$
		filtreButtonPanel.add(nomRadioButton);
		
		JRadioButton carnetRadioButton = new JRadioButton(CentralPanelMessages.getString("AdministradorUsuaris.carnetRadioButton.text")); //$NON-NLS-1$
		buttonGroup.add(carnetRadioButton);
		carnetRadioButton.setToolTipText(CentralPanelMessages.getString("AdministradorUsuaris.carnetRadioButton.toolTipText")); //$NON-NLS-1$
		filtreButtonPanel.add(carnetRadioButton);
		
		JRadioButton inactiusRadioButton = new JRadioButton(CentralPanelMessages.getString("AdministradorUsuaris.inactiusRadioButton.text")); //$NON-NLS-1$
		buttonGroup.add(inactiusRadioButton);
		inactiusRadioButton.setToolTipText(CentralPanelMessages.getString("AdministradorUsuaris.inactiusRadioButton.toolTipText")); //$NON-NLS-1$
		filtreButtonPanel.add(inactiusRadioButton);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Tipus usuari", "Estudiant", "Professor", "Administrador"}));
		filtreButtonPanel.add(comboBox);
		
		JPanel filtreTextPanel = new JPanel();
		FlowLayout fl_filtreTextPanel = (FlowLayout) filtreTextPanel.getLayout();
		fl_filtreTextPanel.setAlignment(FlowLayout.RIGHT);
		filtresPanel.add(filtreTextPanel, BorderLayout.CENTER);
		
		JLabel lupaLabel = new JLabel("");
		lupaLabel.setIcon(icones.getLupaIcon());
		lupaLabel.setHorizontalAlignment(SwingConstants.CENTER);
		filtreTextPanel.add(lupaLabel);
		
		cercaField = new JTextField();
		cercaField.setToolTipText(CentralPanelMessages.getString("AdministradorUsuaris.cercaField.toolTipText")); //$NON-NLS-1$
		filtreTextPanel.add(cercaField);
		cercaField.setColumns(20);
		
		JLabel separacioLabel = new JLabel("   ");
		filtreTextPanel.add(separacioLabel);
		
		JButton cercarButton = new JButton(CentralPanelMessages.getString("AdministradorUsuaris.cercarButton.text"));  //$NON-NLS-1$
		cercarButton.setForeground(new Color(0, 0, 0));
		cercarButton.setBackground(new Color(173, 216, 230));
		cercarButton.setToolTipText(CentralPanelMessages.getString("AdministradorUsuaris.cercarButton.toolTipText")); //$NON-NLS-1$
		filtreTextPanel.add(cercarButton);
		
		JPanel llistaTablePanel = new JPanel();
		llistaTablePanel.setBackground(new Color(255, 255, 255));
		llibresPanel.add(llistaTablePanel, BorderLayout.CENTER);
		llistaTablePanel.setLayout(new BorderLayout(10, 0));
		
		
		
		
		llibresTable = new JTable();
		llibresTable.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		llibresTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		llibresTable.setFillsViewportHeight(true);

		llibresTable.setBackground(new Color(255, 255, 255));
		
		llibresTable.setModel(new DefaultTableModel(
		            new Object [][] {
		            	{
							"002", "pepe", "Perez Reverte", "93456789", "524789654Y", "48749", "true"
						},
						{
							"002", "pepe", "Perez Reverte", "93456789", "524789654Y", "48749", "true"
						},
						{
							"002", "pepe", "Perez Reverte", "93456789", "524789654Y", "48749", "true"
						},
						{
							"002", "pepe", "Perez Reverte", "93456789", "524789654Y", "48749", "true"
						},
						{
							"002", "pepe", "Perez Reverte", "93456789", "524789654Y", "48749", "true"
						},
						{
							"002", "pepe", "Perez Reverte", "93456789", "524789654Y", "48749", "true"
						},
						{
							"002", "pepe", "Perez Reverte", "93456789", "524789654Y", "48749", "true"
						},
						{
							"002", "pepe", "Perez Reverte", "93456789", "524789654Y", "48749", "true"
						},
		            },
		            new String [] {"id", "Nom", "Cognom", "Telèfon", "Dni/Nie", "Carnet", "Actiu"}
		        ) {

					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;
		           
		        });
		llibresTable.setFocusable(false);
		JScrollPane llistatLlibresScrollPane = new JScrollPane(llibresTable);
		llistatLlibresScrollPane.setPreferredSize(new java.awt.Dimension(0, 100));

		llistatLlibresScrollPane.setBackground(new Color(255, 255, 255));
		llistaTablePanel.add(llistatLlibresScrollPane, BorderLayout.CENTER);
		
		
		JPanel accionsButtonsPanel = new JPanel();
		accionsButtonsPanel.setBackground(Color.WHITE);
		llistaTablePanel.add(accionsButtonsPanel, BorderLayout.EAST);
		accionsButtonsPanel.setLayout(new GridLayout(8, 0, 0, 0));
		
		JButton altaUsuariButton = new JButton(CentralPanelMessages.getString("AdministradorUsuaris.altaUsuariButton.text")); //$NON-NLS-1$
		altaUsuariButton.setForeground(Color.WHITE);
		altaUsuariButton.setBackground(Color.decode("#00838f"));
		altaUsuariButton.setToolTipText(CentralPanelMessages.getString("AdministradorUsuaris.altaUsuariButton.toolTipText")); //$NON-NLS-1$
		accionsButtonsPanel.add(altaUsuariButton);
		
		JButton editarUsuariButton = new JButton(CentralPanelMessages.getString("AdministradorUsuaris.editarUsuariButton.text")); //$NON-NLS-1$
		editarUsuariButton.setForeground(Color.WHITE);
		editarUsuariButton.setBackground(Color.decode("#6a1b9a"));
		editarUsuariButton.setToolTipText(CentralPanelMessages.getString("AdministradorUsuaris.editarUsuariButton.toolTipText")); //$NON-NLS-1$
		accionsButtonsPanel.add(editarUsuariButton);
		
		JButton baixaUsuariButton = new JButton(CentralPanelMessages.getString("AdministradorUsuaris.baixaUsuariButton.text")); //$NON-NLS-1$
		baixaUsuariButton.setBackground(new Color(165, 42, 42));
		baixaUsuariButton.setForeground(Color.WHITE);
		baixaUsuariButton.setToolTipText(CentralPanelMessages.getString("AdministradorUsuaris.baixaUsuariButton.toolTipText")); //$NON-NLS-1$
		accionsButtonsPanel.add(baixaUsuariButton);
		
		
		
		JPanel dadesLlibrePanel = new JPanel();
		llibresPanel.add(dadesLlibrePanel, BorderLayout.SOUTH);
		dadesLlibrePanel.setLayout(new BorderLayout(0, 0));
		
		JPanel dadesLlibreGridPanel = new JPanel();
		dadesLlibreGridPanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Dades de l'usuari", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		dadesLlibrePanel.add(dadesLlibreGridPanel, BorderLayout.NORTH);
		GridBagLayout gbl_dadesLlibreGridPanel = new GridBagLayout();
		gbl_dadesLlibreGridPanel.columnWidths = new int[]{619, 0};
		gbl_dadesLlibreGridPanel.rowHeights = new int[]{22, 22, 22, 22, 22, 0};
		gbl_dadesLlibreGridPanel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_dadesLlibreGridPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		dadesLlibreGridPanel.setLayout(gbl_dadesLlibreGridPanel);
		
		JPanel borderTopPanel = new JPanel();
		FlowLayout fl_borderTopPanel = (FlowLayout) borderTopPanel.getLayout();
		fl_borderTopPanel.setVgap(0);
		fl_borderTopPanel.setHgap(0);
		GridBagConstraints gbc_borderTopPanel = new GridBagConstraints();
		gbc_borderTopPanel.fill = GridBagConstraints.BOTH;
		gbc_borderTopPanel.insets = new Insets(0, 0, 5, 0);
		gbc_borderTopPanel.gridx = 0;
		gbc_borderTopPanel.gridy = 0;
		dadesLlibreGridPanel.add(borderTopPanel, gbc_borderTopPanel);
		
		JPanel idNomCognomsActiuPanel = new JPanel();
		GridBagConstraints gbc_idNomCognomsActiuPanel = new GridBagConstraints();
		gbc_idNomCognomsActiuPanel.fill = GridBagConstraints.BOTH;
		gbc_idNomCognomsActiuPanel.insets = new Insets(0, 0, 5, 0);
		gbc_idNomCognomsActiuPanel.gridx = 0;
		gbc_idNomCognomsActiuPanel.gridy = 1;
		dadesLlibreGridPanel.add(idNomCognomsActiuPanel, gbc_idNomCognomsActiuPanel);
		idNomCognomsActiuPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 10, 2));
		
		JPanel idPanel = new JPanel();
		idNomCognomsActiuPanel.add(idPanel);
		
		JLabel idLabel = new JLabel(CentralPanelMessages.getString("AdministradorUsuaris.idLabel.text")); //$NON-NLS-1$
		idPanel.add(idLabel);
		
		idUsuariField = new JTextField();
		idUsuariField.setToolTipText(CentralPanelMessages.getString("AdministradorUsuaris.idUsuariField.toolTipText")); //$NON-NLS-1$
		idPanel.add(idUsuariField);
		idUsuariField.setColumns(3);
		
		JPanel nomCognomsPanel = new JPanel();
		idNomCognomsActiuPanel.add(nomCognomsPanel);
		
		JLabel nomLabel = new JLabel(CentralPanelMessages.getString("AdministradorUsuaris.nomLabel.text")); //$NON-NLS-1$
		nomCognomsPanel.add(nomLabel);
		
		nomField = new JTextField();
		nomField.setToolTipText(CentralPanelMessages.getString("AdministradorUsuaris.nomField.toolTipText")); //$NON-NLS-1$
		nomCognomsPanel.add(nomField);
		nomField.setColumns(10);
		
		JLabel separacioNomCognomsLabel = new JLabel("  ");
		nomCognomsPanel.add(separacioNomCognomsLabel);
		
		JLabel cognomsLabel = new JLabel(CentralPanelMessages.getString("AdministradorUsuaris.cognomsLabel.text")); //$NON-NLS-1$
		nomCognomsPanel.add(cognomsLabel);
		
		cognomsField = new JTextField();
		cognomsField.setToolTipText(CentralPanelMessages.getString("AdministradorUsuaris.cognomsField.toolTipText")); //$NON-NLS-1$
		nomCognomsPanel.add(cognomsField);
		cognomsField.setColumns(30);
		
		JPanel dniEmailContrasenyaCarnetPanel = new JPanel();
		GridBagConstraints gbc_dniEmailContrasenyaCarnetPanel = new GridBagConstraints();
		gbc_dniEmailContrasenyaCarnetPanel.fill = GridBagConstraints.BOTH;
		gbc_dniEmailContrasenyaCarnetPanel.insets = new Insets(0, 0, 5, 0);
		gbc_dniEmailContrasenyaCarnetPanel.gridx = 0;
		gbc_dniEmailContrasenyaCarnetPanel.gridy = 2;
		dadesLlibreGridPanel.add(dniEmailContrasenyaCarnetPanel, gbc_dniEmailContrasenyaCarnetPanel);
		dniEmailContrasenyaCarnetPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 10, 2));
		
		JPanel dniNiePanel = new JPanel();
		dniEmailContrasenyaCarnetPanel.add(dniNiePanel);
		
		JLabel dniNieLabel = new JLabel(CentralPanelMessages.getString("AdministradorUsuaris.dniNieLabel.text")); //$NON-NLS-1$
		dniNiePanel.add(dniNieLabel);
		dniNieLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		
		dniNieField = new JTextField();
		dniNieField.setToolTipText(CentralPanelMessages.getString("AdministradorUsuaris.dniNieField.toolTipText")); //$NON-NLS-1$
		dniNiePanel.add(dniNieField);
		dniNieField.setColumns(8);
		
		JPanel emailPanel = new JPanel();
		dniEmailContrasenyaCarnetPanel.add(emailPanel);
		
		JLabel emailLabel = new JLabel(CentralPanelMessages.getString("AdministradorUsuaris.emailLabel.text")); //$NON-NLS-1$
		emailPanel.add(emailLabel);
		emailLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		
		emailField = new JTextField();
		emailField.setToolTipText(CentralPanelMessages.getString("AdministradorUsuaris.emailField.toolTipText")); //$NON-NLS-1$
		emailPanel.add(emailField);
		emailField.setColumns(15);
		
		JPanel contrasenyaPanel = new JPanel();
		dniEmailContrasenyaCarnetPanel.add(contrasenyaPanel);
		
		JLabel contrasenyaLabel = new JLabel(CentralPanelMessages.getString("AdministradorUsuaris.contrasenyaLabel.text")); //$NON-NLS-1$
		contrasenyaPanel.add(contrasenyaLabel);
		contrasenyaLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		
		contrasenyaField = new JPasswordField();
		contrasenyaField.setToolTipText(CentralPanelMessages.getString("AdministradorUsuaris.contrasenyaField.toolTipText")); //$NON-NLS-1$
		contrasenyaField.setEchoChar('*');
		contrasenyaField.setColumns(10);
		contrasenyaPanel.add(contrasenyaField);
		
		JLabel mostrarContrasenyaLabel = new JLabel("");
		mostrarContrasenyaLabel.setIcon(icones.getMostrarContrasenyaIcon());
		mostrarContrasenyaLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		mostrarContrasenyaLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mostrarContrasenyaLabel.setToolTipText(CentralPanelMessages.getString("AdministradorUsuaris.mostrarContrasenyaLabel.toolTipText")); //$NON-NLS-1$
		contrasenyaPanel.add(mostrarContrasenyaLabel);
		
		JPanel carnetPanel = new JPanel();
		dniEmailContrasenyaCarnetPanel.add(carnetPanel);
		
		JLabel carnetLabel = new JLabel(CentralPanelMessages.getString("AdministradorUsuaris.carnetLabel.text")); //$NON-NLS-1$
		carnetPanel.add(carnetLabel);
		
		carnetField = new JTextField();
		carnetField.setToolTipText(CentralPanelMessages.getString("AdministradorUsuaris.carnetField.toolTipText")); //$NON-NLS-1$
		carnetPanel.add(carnetField);
		carnetField.setColumns(6);
		
		
		
		JPanel adrecaCodiPostalPoblacioPaisPanel = new JPanel();
		FlowLayout fl_adrecaCodiPostalPoblacioPaisPanel = (FlowLayout) adrecaCodiPostalPoblacioPaisPanel.getLayout();
		fl_adrecaCodiPostalPoblacioPaisPanel.setVgap(2);
		fl_adrecaCodiPostalPoblacioPaisPanel.setAlignment(FlowLayout.LEADING);
		GridBagConstraints gbc_adrecaCodiPostalPoblacioPaisPanel = new GridBagConstraints();
		gbc_adrecaCodiPostalPoblacioPaisPanel.fill = GridBagConstraints.BOTH;
		gbc_adrecaCodiPostalPoblacioPaisPanel.insets = new Insets(0, 0, 5, 0);
		gbc_adrecaCodiPostalPoblacioPaisPanel.gridx = 0;
		gbc_adrecaCodiPostalPoblacioPaisPanel.gridy = 3;
		dadesLlibreGridPanel.add(adrecaCodiPostalPoblacioPaisPanel, gbc_adrecaCodiPostalPoblacioPaisPanel);
		
		JPanel adrecaPanel = new JPanel();
		adrecaCodiPostalPoblacioPaisPanel.add(adrecaPanel);
		
		JLabel adrecaLabel = new JLabel(CentralPanelMessages.getString("AdministradorUsuaris.adrecaLabel.text")); //$NON-NLS-1$
		adrecaPanel.add(adrecaLabel);
		
		adrecaField = new JTextField();
		adrecaField.setToolTipText(CentralPanelMessages.getString("AdministradorUsuaris.adrecaField.toolTipText")); //$NON-NLS-1$
		adrecaPanel.add(adrecaField);
		adrecaField.setColumns(16);
		
		JPanel codiPostalPanel = new JPanel();
		adrecaCodiPostalPoblacioPaisPanel.add(codiPostalPanel);
		
		JLabel codiPostalLabel = new JLabel(CentralPanelMessages.getString("AdministradorUsuaris.codiPostalLabel.text"));  //$NON-NLS-1$
		codiPostalPanel.add(codiPostalLabel);
		
		codiPostalField = new JTextField();
		codiPostalField.setToolTipText(CentralPanelMessages.getString("AdministradorUsuaris.codiPostalField.toolTipText"));  //$NON-NLS-1$
		codiPostalPanel.add(codiPostalField);
		codiPostalField.setColumns(5);
		
		JPanel poblacioPanel = new JPanel();
		adrecaCodiPostalPoblacioPaisPanel.add(poblacioPanel);
		
		JLabel poblacioLabel = new JLabel(CentralPanelMessages.getString("AdministradorUsuaris.poblacioLabel.text"));  //$NON-NLS-1$
		poblacioPanel.add(poblacioLabel);
		
		poblacioField = new JTextField();
		poblacioField.setToolTipText(CentralPanelMessages.getString("AdministradorUsuaris.poblacioField.toolTipText"));  //$NON-NLS-1$
		poblacioPanel.add(poblacioField);
		poblacioField.setColumns(16);
		
		JPanel provinciaPanel = new JPanel();
		adrecaCodiPostalPoblacioPaisPanel.add(provinciaPanel);
		
		JComboBox provinciaComboBox = new JComboBox();
		provinciaComboBox.setToolTipText(CentralPanelMessages.getString("AdministradorUsuaris.provinciaComboBox.toolTipText"));  //$NON-NLS-1$
		provinciaComboBox.setModel(new DefaultComboBoxModel(new String[] {"Provincia...", "Barcelona", "Girona", "Lleida", "Tarragona"}));
		provinciaPanel.add(provinciaComboBox);
		
		JPanel telefonTipusPuntuacioActiuPanel = new JPanel();
		FlowLayout fl_telefonTipusPuntuacioActiuPanel = (FlowLayout) telefonTipusPuntuacioActiuPanel.getLayout();
		fl_telefonTipusPuntuacioActiuPanel.setHgap(10);
		fl_telefonTipusPuntuacioActiuPanel.setVgap(2);
		fl_telefonTipusPuntuacioActiuPanel.setAlignment(FlowLayout.LEADING);
		GridBagConstraints gbc_telefonTipusPuntuacioActiuPanel = new GridBagConstraints();
		gbc_telefonTipusPuntuacioActiuPanel.anchor = GridBagConstraints.NORTH;
		gbc_telefonTipusPuntuacioActiuPanel.fill = GridBagConstraints.HORIZONTAL;
		gbc_telefonTipusPuntuacioActiuPanel.gridx = 0;
		gbc_telefonTipusPuntuacioActiuPanel.gridy = 4;
		dadesLlibreGridPanel.add(telefonTipusPuntuacioActiuPanel, gbc_telefonTipusPuntuacioActiuPanel);
		
		JPanel paisPanel = new JPanel();
		telefonTipusPuntuacioActiuPanel.add(paisPanel);
		
		JLabel paisLabel = new JLabel(CentralPanelMessages.getString("AdministradorUsuaris.paisLabel.text"));  //$NON-NLS-1$
		paisPanel.add(paisLabel);
		
		paisField = new JTextField();
		paisField.setToolTipText(CentralPanelMessages.getString("AdministradorUsuaris.paisField.toolTipText"));  //$NON-NLS-1$
		paisPanel.add(paisField);
		paisField.setColumns(8);
		
		JPanel telefonPanel = new JPanel();
		telefonTipusPuntuacioActiuPanel.add(telefonPanel);
		
		JLabel telefonLabel = new JLabel(CentralPanelMessages.getString("AdministradorUsuaris.telefonLabel.text")); //$NON-NLS-1$
		telefonPanel.add(telefonLabel);
		
		telefonField = new JTextField();
		telefonField.setToolTipText(CentralPanelMessages.getString("AdministradorUsuaris.telefonField.toolTipText")); //$NON-NLS-1$
		telefonField.setText("");
		telefonPanel.add(telefonField);
		telefonField.setColumns(10);
		
		JPanel tipusUsuariPanel = new JPanel();
		telefonTipusPuntuacioActiuPanel.add(tipusUsuariPanel);
		
		JComboBox tipusUsuariComboBox = new JComboBox();
		tipusUsuariComboBox.setToolTipText(CentralPanelMessages.getString("AdministradorUsuaris.tipusUsuariComboBox.toolTipText")); //$NON-NLS-1$
		tipusUsuariComboBox.setModel(new DefaultComboBoxModel(new String[] {"Tipus usuari...", "Estudiant", "Professor", "Administrador"}));
		tipusUsuariPanel.add(tipusUsuariComboBox);
		
		JPanel puntuacioPanel = new JPanel();
		telefonTipusPuntuacioActiuPanel.add(puntuacioPanel);
		
		JLabel puntuacioLabel = new JLabel(CentralPanelMessages.getString("AdministradorUsuaris.puntuacioLabel.text"));  //$NON-NLS-1$
		puntuacioPanel.add(puntuacioLabel);
		
		puntuacioField = new JTextField();
		puntuacioField.setToolTipText(CentralPanelMessages.getString("AdministradorUsuaris.puntuacioField.toolTipText")); //$NON-NLS-1$
		puntuacioField.setText("");
		puntuacioPanel.add(puntuacioField);
		puntuacioField.setColumns(5);
		
		JPanel actiuPanel = new JPanel();
		telefonTipusPuntuacioActiuPanel.add(actiuPanel);
		
		JLabel actiuLabel = new JLabel(CentralPanelMessages.getString("AdministradorUsuaris.actiuLabel.text")); //$NON-NLS-1$
		actiuPanel.add(actiuLabel);
		actiuLabel.setToolTipText(CentralPanelMessages.getString("AdministradorUsuaris.actiuLabel.toolTipText")); //$NON-NLS-1$
		
		JCheckBox actiuCheckBox = new JCheckBox("");
		actiuPanel.add(actiuCheckBox);
		
		

	}
}
