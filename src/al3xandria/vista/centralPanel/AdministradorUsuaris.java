package al3xandria.vista.centralPanel;

import javax.swing.JPanel;
import java.awt.Rectangle;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import al3xandria.controlador.usuaris.AdministradorUsuarisControlador;
import al3xandria.model.objects.CreateUsuaris;
import al3xandria.model.objects.Usuari;
import al3xandria.model.usuaris.UsuarisModel;
import al3xandria.vista.icons.Icons;

import javax.swing.border.EtchedBorder;
import java.awt.ComponentOrientation;
import java.awt.Cursor;

import javax.swing.ButtonGroup;
import javax.swing.JPasswordField;

public class AdministradorUsuaris extends JPanel {

	/**
	 * Classe que crea el jpanel per l'administració d'usuaris
	 * 
	 * @author SergioHernandez
	 */
	private static final long serialVersionUID = 1L;

	private CreateUsuaris createUsuaris;
	private UsuarisModel usuarisModel;
	private Icons icones;
	private int rowActiu;
	private String accio;
	private Usuari usuariConnectat;

	private JTextField cercaField;
	private JTable usuarisTable;
	private JTextField idUsuariField;
	private JTextField nomField;
	private JTextField poblacioField;
	private JTextField paisField;
	private JTextField carnetField;
	private JTextField adrecaField;
	private JTextField codiPostalField;
	private final ButtonGroup filtreButtonGroup = new ButtonGroup();
	private JTextField cognomsField;
	private JTextField dniNieField;
	private JTextField emailField;
	private JPasswordField contrasenyaField;
	private JTextField telefonField;
	private JTextField puntuacioField;
	private JPanel paginadorPanel;
	private FlowLayout fl_paginadorPanel;
	private JLabel anteriorLabel;
	private JLabel anteriorIconLabel;
	private JTextField rowActualField;
	private JLabel ofLabel;
	private JLabel seguentIconLabel;
	private JTextField rowTotalsField;
	private JLabel seguentLabel;
	private JPanel llibresPanel;
	private JPanel filtresPanel;
	private JPanel filtreButtonPanel;
	private FlowLayout fl_filtreButtonPanel;
	private JLabel filtrePerLabel;
	private JRadioButton nomRadioButton;
	private JRadioButton carnetRadioButton;
	private JRadioButton inactiusRadioButton;
	private JComboBox filtreComboBox;
	private JPanel filtreTextPanel;
	private FlowLayout fl_filtreTextPanel;
	private JLabel lupaLabel;
	private JLabel separacioLabel;
	private JButton cercarButton;
	private JPanel llistaTablePanel;
	private JScrollPane llistatLlibresScrollPane;
	private JPanel accionsButtonsPanel;
	private JButton altaUsuariButton;
	private JButton editarUsuariButton;
	private JButton baixaUsuariButton;
	private JPanel dadesLlibrePanel;
	private JPanel dadesLlibreGridPanel;
	private JPanel idNomCognomsActiuPanel;
	private JPanel idPanel;
	private JLabel idLabel;
	private JPanel nomCognomsPanel;
	private JLabel nomLabel;
	private JLabel separacioNomCognomsLabel;
	private JLabel cognomsLabel;
	private JPanel dniEmailContrasenyaCarnetPanel;
	private JPanel dniNiePanel;
	private JLabel dniNieLabel;
	private JPanel emailPanel;
	private JLabel emailLabel;
	private JPanel contrasenyaPanel;
	private JLabel contrasenyaLabel;
	private JLabel mostrarContrasenyaLabel;
	private JPanel carnetPanel;
	private JLabel carnetLabel;
	private JPanel adrecaCodiPostalPoblacioPaisPanel;
	private FlowLayout fl_adrecaCodiPostalPoblacioPaisPanel;
	private JPanel adrecaPanel;
	private JLabel adrecaLabel;
	private JPanel codiPostalPanel;
	private JLabel codiPostalLabel;
	private JPanel poblacioPanel;
	private JLabel poblacioLabel;
	private JPanel provinciaPanel;
	private JComboBox provinciaComboBox;
	private JPanel telefonTipusPuntuacioActiuPanel;
	private FlowLayout fl_telefonTipusPuntuacioActiuPanel;
	private JPanel paisPanel;
	private JLabel paisLabel;
	private JPanel telefonPanel;
	private JLabel telefonLabel;
	private JPanel tipusUsuariPanel;
	private JComboBox tipusUsuariComboBox;
	private JPanel puntuacioPanel;
	private JLabel puntuacioLabel;
	private JPanel actiuPanel;
	private JLabel actiuLabel;
	private JCheckBox actiuCheckBox;
	private JButton mostrarButton;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JButton confirmarButton;
	private JButton cancellarButton;

	private JLabel esborrarLabel;

	/**
	 * Create the panel.
	 */
	public AdministradorUsuaris(Usuari usuariConnectat) {
		this.usuariConnectat = usuariConnectat;
		icones = new Icons();
		setBounds(new Rectangle(0, 0, 750, 850));
		setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		iniciarComponents();
		llistarUsuaris();
		omplirCombobox();
	}

	public void iniciarComponents() {
		rowActiu = 0;
		accio = "default";
		llibresPanel = new JPanel();
		add(llibresPanel);
		llibresPanel.setLayout(new BorderLayout(0, 10));

		filtresPanel = new JPanel();
		llibresPanel.add(filtresPanel, BorderLayout.NORTH);
		filtresPanel.setLayout(new BorderLayout(0, 0));

		filtreButtonPanel = new JPanel();
		fl_filtreButtonPanel = (FlowLayout) filtreButtonPanel.getLayout();
		fl_filtreButtonPanel.setAlignment(FlowLayout.LEFT);
		filtresPanel.add(filtreButtonPanel, BorderLayout.WEST);

		filtrePerLabel = new JLabel(CentralPanelMessages.getString("AdministradorUsuaris.filtrePerLabel.text"));
		filtrePerLabel
				.setToolTipText(CentralPanelMessages.getString("AdministradorUsuaris.filtrePerLabel.toolTipText")); //$NON-NLS-1$
		filtrePerLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		filtreButtonPanel.add(filtrePerLabel);

		nomRadioButton = new JRadioButton(CentralPanelMessages.getString("AdministradorUsuaris.nomRadioButton.text"));
		nomRadioButton.setSelected(true);
		filtreButtonGroup.add(nomRadioButton);
		nomRadioButton
				.setToolTipText(CentralPanelMessages.getString("AdministradorUsuaris.nomRadioButton.toolTipText")); //$NON-NLS-1$
		filtreButtonPanel.add(nomRadioButton);

		carnetRadioButton = new JRadioButton(
				CentralPanelMessages.getString("AdministradorUsuaris.carnetRadioButton.text"));
		filtreButtonGroup.add(carnetRadioButton);
		carnetRadioButton
				.setToolTipText(CentralPanelMessages.getString("AdministradorUsuaris.carnetRadioButton.toolTipText")); //$NON-NLS-1$
		filtreButtonPanel.add(carnetRadioButton);

		inactiusRadioButton = new JRadioButton(
				CentralPanelMessages.getString("AdministradorUsuaris.inactiusRadioButton.text"));
		filtreButtonGroup.add(inactiusRadioButton);
		inactiusRadioButton
				.setToolTipText(CentralPanelMessages.getString("AdministradorUsuaris.inactiusRadioButton.toolTipText")); //$NON-NLS-1$
		filtreButtonPanel.add(inactiusRadioButton);

		filtreComboBox = new JComboBox();
		filtreComboBox
				.setToolTipText(CentralPanelMessages.getString("AdministradorUsuaris.filtreComboBox.toolTipText")); //$NON-NLS-1$
		filtreComboBox.addItem("Tipus d'usuari");
		filtreButtonPanel.add(filtreComboBox);

		filtreTextPanel = new JPanel();
		fl_filtreTextPanel = (FlowLayout) filtreTextPanel.getLayout();
		fl_filtreTextPanel.setAlignment(FlowLayout.RIGHT);
		filtresPanel.add(filtreTextPanel, BorderLayout.CENTER);

		lupaLabel = new JLabel("");
		lupaLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lupaLabel.setIcon(icones.getLupaIcon());
		lupaLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lupaLabel.addMouseListener(new AdministradorUsuarisControlador(this, usuariConnectat));
		filtreTextPanel.add(lupaLabel);

		cercaField = new JTextField();
		cercaField.setToolTipText(CentralPanelMessages.getString("AdministradorUsuaris.cercaField.toolTipText")); //$NON-NLS-1$
		filtreTextPanel.add(cercaField);
		cercaField.setColumns(20);

		esborrarLabel = new JLabel("");
		esborrarLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		esborrarLabel
				.setToolTipText(CentralPanelMessages.getString("ConsultaLlibresNoRegistrat.esborrarLabel.toolTipText")); //$NON-NLS-1$
		esborrarLabel.setIcon(icones.getCancelIcon());
		esborrarLabel.setHorizontalAlignment(SwingConstants.CENTER);
		esborrarLabel.addMouseListener(new AdministradorUsuarisControlador(this, usuariConnectat));
		filtreTextPanel.add(esborrarLabel);

		separacioLabel = new JLabel("   ");
		filtreTextPanel.add(separacioLabel);

		cercarButton = new JButton(CentralPanelMessages.getString("AdministradorUsuaris.cercarButton.text"));
		cercarButton.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		cercarButton.setForeground(new Color(0, 0, 0));
		cercarButton.setBackground(new Color(173, 216, 230));
		cercarButton.setToolTipText(CentralPanelMessages.getString("AdministradorUsuaris.cercarButton.toolTipText")); //$NON-NLS-1$
		cercarButton.addMouseListener(new AdministradorUsuarisControlador(this, usuariConnectat));
		filtreTextPanel.add(cercarButton);

		llistaTablePanel = new JPanel();
		llistaTablePanel.setBackground(new Color(255, 255, 255));
		llibresPanel.add(llistaTablePanel, BorderLayout.CENTER);
		llistaTablePanel.setLayout(new BorderLayout(10, 0));

		usuarisTable = new JTable();
		usuarisTable.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		usuarisTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		usuarisTable.setFillsViewportHeight(true);

		usuarisTable.setBackground(new Color(255, 255, 255));

		usuarisTable.setFocusable(false);
		llistatLlibresScrollPane = new JScrollPane(usuarisTable);
		llistatLlibresScrollPane.setPreferredSize(new java.awt.Dimension(0, 100));

		llistatLlibresScrollPane.setBackground(new Color(255, 255, 255));
		llistaTablePanel.add(llistatLlibresScrollPane, BorderLayout.CENTER);

		accionsButtonsPanel = new JPanel();
		accionsButtonsPanel.setBackground(Color.WHITE);
		llistaTablePanel.add(accionsButtonsPanel, BorderLayout.EAST);
		accionsButtonsPanel.setLayout(new GridLayout(8, 0, 0, 0));

		mostrarButton = new JButton(CentralPanelMessages.getString("AdministradorUsuaris.mostrarButton"));
		mostrarButton.setToolTipText(CentralPanelMessages.getString("AdministradorUsuaris.mostrarButton.toolTipText")); //$NON-NLS-1$
		mostrarButton.setForeground(Color.WHITE);
		mostrarButton.setBackground(Color.decode("#00838f"));
		mostrarButton.addMouseListener(new AdministradorUsuarisControlador(this, usuariConnectat));
		accionsButtonsPanel.add(mostrarButton);

		altaUsuariButton = new JButton(CentralPanelMessages.getString("AdministradorUsuaris.altaUsuariButton.text"));
		altaUsuariButton.setForeground(Color.WHITE);
		altaUsuariButton.setBackground(new Color(70, 130, 180));
		altaUsuariButton
				.setToolTipText(CentralPanelMessages.getString("AdministradorUsuaris.altaUsuariButton.toolTipText")); //$NON-NLS-1$
		altaUsuariButton.addMouseListener(new AdministradorUsuarisControlador(this, usuariConnectat));
		accionsButtonsPanel.add(altaUsuariButton);

		editarUsuariButton = new JButton(
				CentralPanelMessages.getString("AdministradorUsuaris.editarUsuariButton.text"));
		editarUsuariButton.setForeground(Color.WHITE);
		editarUsuariButton.setBackground(Color.decode("#6a1b9a"));
		editarUsuariButton
				.setToolTipText(CentralPanelMessages.getString("AdministradorUsuaris.editarUsuariButton.toolTipText")); //$NON-NLS-1$
		editarUsuariButton.addMouseListener(new AdministradorUsuarisControlador(this, usuariConnectat));
		accionsButtonsPanel.add(editarUsuariButton);

		baixaUsuariButton = new JButton(CentralPanelMessages.getString("AdministradorUsuaris.baixaUsuariButton.text"));
		baixaUsuariButton.setBackground(new Color(165, 42, 42));
		baixaUsuariButton.setForeground(Color.WHITE);
		baixaUsuariButton
				.setToolTipText(CentralPanelMessages.getString("AdministradorUsuaris.baixaUsuariButton.toolTipText")); //$NON-NLS-1$
		baixaUsuariButton.addMouseListener(new AdministradorUsuarisControlador(this, usuariConnectat));
		accionsButtonsPanel.add(baixaUsuariButton);

		lblNewLabel = new JLabel(""); //$NON-NLS-1$
		accionsButtonsPanel.add(lblNewLabel);

		lblNewLabel_1 = new JLabel(""); //$NON-NLS-1$
		accionsButtonsPanel.add(lblNewLabel_1);

		confirmarButton = new JButton(CentralPanelMessages.getString("AdministradorUsuaris.btnNewButton.text")); //$NON-NLS-1$
		confirmarButton.setVisible(false);
		confirmarButton
				.setToolTipText(CentralPanelMessages.getString("AdministradorUsuaris.confirmarButton.toolTipText")); //$NON-NLS-1$
		confirmarButton.setBackground(new Color(255, 204, 51));
		confirmarButton
				.setToolTipText(CentralPanelMessages.getString("AdministradorUsuari.confirmarButton.setToolTipText")); //$NON-NLS-1$
		confirmarButton.addMouseListener(new AdministradorUsuarisControlador(this, usuariConnectat));
		accionsButtonsPanel.add(confirmarButton);

		cancellarButton = new JButton(CentralPanelMessages.getString("AdministradorUsuaris.btnNewButton_1.text")); //$NON-NLS-1$
		cancellarButton.setVisible(false);
		cancellarButton
				.setToolTipText(CentralPanelMessages.getString("AdministradorUsuaris.cancellarButton.toolTipText")); //$NON-NLS-1$
		cancellarButton.setForeground(Color.WHITE);
		cancellarButton.setBackground(new Color(255, 0, 0));
		cancellarButton.addMouseListener(new AdministradorUsuarisControlador(this, usuariConnectat));
		accionsButtonsPanel.add(cancellarButton);

		dadesLlibrePanel = new JPanel();
		llibresPanel.add(dadesLlibrePanel, BorderLayout.SOUTH);
		dadesLlibrePanel.setLayout(new BorderLayout(0, 0));

		dadesLlibreGridPanel = new JPanel();
		dadesLlibreGridPanel.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Dades de l'usuari", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		dadesLlibrePanel.add(dadesLlibreGridPanel, BorderLayout.NORTH);
		GridBagLayout gbl_dadesLlibreGridPanel = new GridBagLayout();
		gbl_dadesLlibreGridPanel.columnWidths = new int[] { 619, 0 };
		gbl_dadesLlibreGridPanel.rowHeights = new int[] { 22, 22, 22, 22, 22, 0 };
		gbl_dadesLlibreGridPanel.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gbl_dadesLlibreGridPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		dadesLlibreGridPanel.setLayout(gbl_dadesLlibreGridPanel);

		paginadorPanel = new JPanel();
		paginadorPanel.setVisible(false);
		fl_paginadorPanel = (FlowLayout) paginadorPanel.getLayout();
		fl_paginadorPanel.setVgap(0);
		GridBagConstraints gbc_paginadorPanel1 = new GridBagConstraints();
		gbc_paginadorPanel1.fill = GridBagConstraints.BOTH;
		gbc_paginadorPanel1.insets = new Insets(0, 0, 5, 0);
		gbc_paginadorPanel1.gridx = 0;
		gbc_paginadorPanel1.gridy = 0;
		dadesLlibreGridPanel.add(paginadorPanel, gbc_paginadorPanel1);

		anteriorLabel = new JLabel(CentralPanelMessages.getString("AdministradorUsuari.anteriorLabel")); //$NON-NLS-1$
		paginadorPanel.add(anteriorLabel);

		anteriorIconLabel = new JLabel("");
		anteriorIconLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		anteriorIconLabel.setIcon(icones.getAnteriorIcon());
		anteriorIconLabel
				.setToolTipText(CentralPanelMessages.getString("ConsultaLlibresNoRegistrat.anteriorLabel.toolTipText")); //$NON-NLS-1$
		anteriorIconLabel.addMouseListener(new AdministradorUsuarisControlador(this, usuariConnectat));
		paginadorPanel.add(anteriorIconLabel);

		rowActualField = new JTextField();
		rowActualField.setEditable(false);
		rowActualField.setText("");
		paginadorPanel.add(rowActualField);
		rowActualField.setColumns(2);

		ofLabel = new JLabel(CentralPanelMessages.getString("ConsultaLlibresNoRegistrat.ofLabel.text")); //$NON-NLS-1$
		paginadorPanel.add(ofLabel);

		seguentIconLabel = new JLabel("");
		seguentIconLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		seguentIconLabel.setIcon(icones.getSeguentIcon());
		seguentIconLabel
				.setToolTipText(CentralPanelMessages.getString("ConsultaLlibresNoRegistrat.seguentLabel.toolTipText")); //$NON-NLS-1$
		seguentIconLabel.addMouseListener(new AdministradorUsuarisControlador(this, usuariConnectat));

		rowTotalsField = new JTextField();
		rowTotalsField.setEditable(false);
		rowTotalsField.setText("");
		paginadorPanel.add(rowTotalsField);
		rowTotalsField.setColumns(2);
		paginadorPanel.add(seguentIconLabel);

		seguentLabel = new JLabel(CentralPanelMessages.getString("ConsultaLlibresNoRegistrat.lblNewLabel_1.text")); //$NON-NLS-1$
		paginadorPanel.add(seguentLabel);

		idNomCognomsActiuPanel = new JPanel();
		GridBagConstraints gbc_idNomCognomsActiuPanel = new GridBagConstraints();
		gbc_idNomCognomsActiuPanel.fill = GridBagConstraints.BOTH;
		gbc_idNomCognomsActiuPanel.insets = new Insets(0, 0, 5, 0);
		gbc_idNomCognomsActiuPanel.gridx = 0;
		gbc_idNomCognomsActiuPanel.gridy = 1;
		dadesLlibreGridPanel.add(idNomCognomsActiuPanel, gbc_idNomCognomsActiuPanel);
		idNomCognomsActiuPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 10, 2));

		idPanel = new JPanel();
		idNomCognomsActiuPanel.add(idPanel);

		idLabel = new JLabel(CentralPanelMessages.getString("AdministradorUsuaris.idLabel.text"));
		idPanel.add(idLabel);

		idUsuariField = new JTextField();
		idUsuariField.setEditable(false);
		idUsuariField.setToolTipText(CentralPanelMessages.getString("AdministradorUsuaris.idUsuariField.toolTipText")); //$NON-NLS-1$
		idPanel.add(idUsuariField);
		idUsuariField.setColumns(3);

		nomCognomsPanel = new JPanel();
		idNomCognomsActiuPanel.add(nomCognomsPanel);

		nomLabel = new JLabel(CentralPanelMessages.getString("AdministradorUsuaris.nomLabel.text"));
		nomCognomsPanel.add(nomLabel);

		nomField = new JTextField();
		nomField.setEditable(false);
		nomField.setToolTipText(CentralPanelMessages.getString("AdministradorUsuaris.nomField.toolTipText")); //$NON-NLS-1$
		nomCognomsPanel.add(nomField);
		nomField.setColumns(10);

		separacioNomCognomsLabel = new JLabel("  ");
		nomCognomsPanel.add(separacioNomCognomsLabel);

		cognomsLabel = new JLabel(CentralPanelMessages.getString("AdministradorUsuaris.cognomsLabel.text"));
		nomCognomsPanel.add(cognomsLabel);

		cognomsField = new JTextField();
		cognomsField.setEditable(false);
		cognomsField.setToolTipText(CentralPanelMessages.getString("AdministradorUsuaris.cognomsField.toolTipText")); //$NON-NLS-1$
		nomCognomsPanel.add(cognomsField);
		cognomsField.setColumns(30);

		dniEmailContrasenyaCarnetPanel = new JPanel();
		GridBagConstraints gbc_dniEmailContrasenyaCarnetPanel = new GridBagConstraints();
		gbc_dniEmailContrasenyaCarnetPanel.fill = GridBagConstraints.BOTH;
		gbc_dniEmailContrasenyaCarnetPanel.insets = new Insets(0, 0, 5, 0);
		gbc_dniEmailContrasenyaCarnetPanel.gridx = 0;
		gbc_dniEmailContrasenyaCarnetPanel.gridy = 2;
		dadesLlibreGridPanel.add(dniEmailContrasenyaCarnetPanel, gbc_dniEmailContrasenyaCarnetPanel);
		dniEmailContrasenyaCarnetPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 10, 2));

		dniNiePanel = new JPanel();
		dniEmailContrasenyaCarnetPanel.add(dniNiePanel);

		dniNieLabel = new JLabel(CentralPanelMessages.getString("AdministradorUsuaris.dniNieLabel.text"));
		dniNiePanel.add(dniNieLabel);
		dniNieLabel.setHorizontalAlignment(SwingConstants.RIGHT);

		dniNieField = new JTextField();
		dniNieField.setEditable(false);
		dniNieField.setToolTipText(CentralPanelMessages.getString("AdministradorUsuaris.dniNieField.toolTipText")); //$NON-NLS-1$
		dniNiePanel.add(dniNieField);
		dniNieField.setColumns(8);

		emailPanel = new JPanel();
		dniEmailContrasenyaCarnetPanel.add(emailPanel);

		emailLabel = new JLabel(CentralPanelMessages.getString("AdministradorUsuaris.emailLabel.text"));
		emailPanel.add(emailLabel);
		emailLabel.setHorizontalAlignment(SwingConstants.RIGHT);

		emailField = new JTextField();
		emailField.setEditable(false);
		emailField.setToolTipText(CentralPanelMessages.getString("AdministradorUsuaris.emailField.toolTipText")); //$NON-NLS-1$
		emailPanel.add(emailField);
		emailField.setColumns(15);

		contrasenyaPanel = new JPanel();
		dniEmailContrasenyaCarnetPanel.add(contrasenyaPanel);

		contrasenyaLabel = new JLabel(CentralPanelMessages.getString("AdministradorUsuaris.contrasenyaLabel.text"));
		contrasenyaPanel.add(contrasenyaLabel);
		contrasenyaLabel.setHorizontalAlignment(SwingConstants.RIGHT);

		contrasenyaField = new JPasswordField();
		contrasenyaField.setEditable(false);
		contrasenyaField
				.setToolTipText(CentralPanelMessages.getString("AdministradorUsuaris.contrasenyaField.toolTipText")); //$NON-NLS-1$
		contrasenyaField.setEchoChar('*');
		contrasenyaField.setColumns(10);
		contrasenyaPanel.add(contrasenyaField);

		mostrarContrasenyaLabel = new JLabel("");
		mostrarContrasenyaLabel.setIcon(icones.getMostrarContrasenyaIcon());
		mostrarContrasenyaLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		mostrarContrasenyaLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mostrarContrasenyaLabel.setToolTipText(
				CentralPanelMessages.getString("AdministradorUsuaris.mostrarContrasenyaLabel.toolTipText")); //$NON-NLS-1$
		mostrarContrasenyaLabel.addMouseListener(new AdministradorUsuarisControlador(this, usuariConnectat));
		contrasenyaPanel.add(mostrarContrasenyaLabel);

		carnetPanel = new JPanel();
		dniEmailContrasenyaCarnetPanel.add(carnetPanel);

		carnetLabel = new JLabel(CentralPanelMessages.getString("AdministradorUsuaris.carnetLabel.text"));
		carnetPanel.add(carnetLabel);

		carnetField = new JTextField();
		carnetField.setEditable(false);
		carnetField.setToolTipText(CentralPanelMessages.getString("AdministradorUsuaris.carnetField.toolTipText")); //$NON-NLS-1$
		carnetPanel.add(carnetField);
		carnetField.setColumns(6);

		adrecaCodiPostalPoblacioPaisPanel = new JPanel();
		fl_adrecaCodiPostalPoblacioPaisPanel = (FlowLayout) adrecaCodiPostalPoblacioPaisPanel.getLayout();
		fl_adrecaCodiPostalPoblacioPaisPanel.setVgap(2);
		fl_adrecaCodiPostalPoblacioPaisPanel.setAlignment(FlowLayout.LEADING);
		GridBagConstraints gbc_adrecaCodiPostalPoblacioPaisPanel = new GridBagConstraints();
		gbc_adrecaCodiPostalPoblacioPaisPanel.fill = GridBagConstraints.BOTH;
		gbc_adrecaCodiPostalPoblacioPaisPanel.insets = new Insets(0, 0, 5, 0);
		gbc_adrecaCodiPostalPoblacioPaisPanel.gridx = 0;
		gbc_adrecaCodiPostalPoblacioPaisPanel.gridy = 3;
		dadesLlibreGridPanel.add(adrecaCodiPostalPoblacioPaisPanel, gbc_adrecaCodiPostalPoblacioPaisPanel);

		adrecaPanel = new JPanel();
		adrecaCodiPostalPoblacioPaisPanel.add(adrecaPanel);

		adrecaLabel = new JLabel(CentralPanelMessages.getString("AdministradorUsuaris.adrecaLabel.text"));
		adrecaPanel.add(adrecaLabel);

		adrecaField = new JTextField();
		adrecaField.setEditable(false);
		adrecaField.setToolTipText(CentralPanelMessages.getString("AdministradorUsuaris.adrecaField.toolTipText")); //$NON-NLS-1$
		adrecaPanel.add(adrecaField);
		adrecaField.setColumns(16);

		codiPostalPanel = new JPanel();
		adrecaCodiPostalPoblacioPaisPanel.add(codiPostalPanel);

		codiPostalLabel = new JLabel(CentralPanelMessages.getString("AdministradorUsuaris.codiPostalLabel.text"));
		codiPostalPanel.add(codiPostalLabel);

		codiPostalField = new JTextField();
		codiPostalField.setEditable(false);
		codiPostalField
				.setToolTipText(CentralPanelMessages.getString("AdministradorUsuaris.codiPostalField.toolTipText")); //$NON-NLS-1$
		codiPostalPanel.add(codiPostalField);
		codiPostalField.setColumns(5);

		poblacioPanel = new JPanel();
		adrecaCodiPostalPoblacioPaisPanel.add(poblacioPanel);

		poblacioLabel = new JLabel(CentralPanelMessages.getString("AdministradorUsuaris.poblacioLabel.text"));
		poblacioPanel.add(poblacioLabel);

		poblacioField = new JTextField();
		poblacioField.setEditable(false);
		poblacioField.setToolTipText(CentralPanelMessages.getString("AdministradorUsuaris.poblacioField.toolTipText")); //$NON-NLS-1$
		poblacioPanel.add(poblacioField);
		poblacioField.setColumns(16);

		provinciaPanel = new JPanel();
		adrecaCodiPostalPoblacioPaisPanel.add(provinciaPanel);

		provinciaComboBox = new JComboBox();
		provinciaComboBox.setEnabled(false);
		provinciaComboBox
				.setToolTipText(CentralPanelMessages.getString("AdministradorUsuaris.provinciaComboBox.toolTipText")); //$NON-NLS-1$
		provinciaComboBox.addItem("Provincia");
		provinciaPanel.add(provinciaComboBox);

		telefonTipusPuntuacioActiuPanel = new JPanel();
		fl_telefonTipusPuntuacioActiuPanel = (FlowLayout) telefonTipusPuntuacioActiuPanel.getLayout();
		fl_telefonTipusPuntuacioActiuPanel.setHgap(10);
		fl_telefonTipusPuntuacioActiuPanel.setVgap(2);
		fl_telefonTipusPuntuacioActiuPanel.setAlignment(FlowLayout.LEADING);
		GridBagConstraints gbc_telefonTipusPuntuacioActiuPanel = new GridBagConstraints();
		gbc_telefonTipusPuntuacioActiuPanel.anchor = GridBagConstraints.NORTH;
		gbc_telefonTipusPuntuacioActiuPanel.fill = GridBagConstraints.HORIZONTAL;
		gbc_telefonTipusPuntuacioActiuPanel.gridx = 0;
		gbc_telefonTipusPuntuacioActiuPanel.gridy = 4;
		dadesLlibreGridPanel.add(telefonTipusPuntuacioActiuPanel, gbc_telefonTipusPuntuacioActiuPanel);

		paisPanel = new JPanel();
		telefonTipusPuntuacioActiuPanel.add(paisPanel);

		paisLabel = new JLabel(CentralPanelMessages.getString("AdministradorUsuaris.paisLabel.text"));
		paisPanel.add(paisLabel);

		paisField = new JTextField();
		paisField.setEditable(false);
		paisField.setToolTipText(CentralPanelMessages.getString("AdministradorUsuaris.paisField.toolTipText")); //$NON-NLS-1$
		paisPanel.add(paisField);
		paisField.setColumns(8);

		telefonPanel = new JPanel();
		telefonTipusPuntuacioActiuPanel.add(telefonPanel);

		telefonLabel = new JLabel(CentralPanelMessages.getString("AdministradorUsuaris.telefonLabel.text"));
		telefonPanel.add(telefonLabel);

		telefonField = new JTextField();
		telefonField.setEditable(false);
		telefonField.setToolTipText(CentralPanelMessages.getString("AdministradorUsuaris.telefonField.toolTipText")); //$NON-NLS-1$
		telefonField.setText("");
		telefonPanel.add(telefonField);
		telefonField.setColumns(10);

		tipusUsuariPanel = new JPanel();
		telefonTipusPuntuacioActiuPanel.add(tipusUsuariPanel);

		tipusUsuariComboBox = new JComboBox();
		tipusUsuariComboBox.setEnabled(false);
		tipusUsuariComboBox
				.setToolTipText(CentralPanelMessages.getString("AdministradorUsuaris.tipusUsuariComboBox.toolTipText")); //$NON-NLS-1$
		tipusUsuariComboBox.addItem("Tipus d'usuari");
		tipusUsuariPanel.add(tipusUsuariComboBox);

		puntuacioPanel = new JPanel();
		telefonTipusPuntuacioActiuPanel.add(puntuacioPanel);

		puntuacioLabel = new JLabel(CentralPanelMessages.getString("AdministradorUsuaris.puntuacioLabel.text"));
		puntuacioPanel.add(puntuacioLabel);

		puntuacioField = new JTextField();
		puntuacioField.setEditable(false);
		puntuacioField
				.setToolTipText(CentralPanelMessages.getString("AdministradorUsuaris.puntuacioField.toolTipText")); //$NON-NLS-1$
		puntuacioField.setText("");
		puntuacioPanel.add(puntuacioField);
		puntuacioField.setColumns(5);

		actiuPanel = new JPanel();
		telefonTipusPuntuacioActiuPanel.add(actiuPanel);

		actiuLabel = new JLabel(CentralPanelMessages.getString("AdministradorUsuaris.actiuLabel.text"));
		actiuPanel.add(actiuLabel);
		actiuLabel.setToolTipText(CentralPanelMessages.getString("AdministradorUsuaris.actiuLabel.toolTipText")); //$NON-NLS-1$

		actiuCheckBox = new JCheckBox("");
		actiuCheckBox.setEnabled(false);
		actiuPanel.add(actiuCheckBox);

	}

	/**
	 * Mètode que assigna un model a table i l'omple amb els usuaris
	 * 
	 * @author SergioHernandez
	 */
	private void llistarUsuaris() {
		usuarisModel = new UsuarisModel();
		try {
			usuarisTable.setModel(usuarisModel.consultarTotsElsUsuaris());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Omple els combobox amb dades: tipus usuaris i provincies
	 * 
	 * @author SergioHernandez
	 */
	public void omplirCombobox() {
		createUsuaris = new CreateUsuaris();
		if (tipusUsuariComboBox.getItemCount() == 1) {
			for (String tipusUsuari : createUsuaris.getLlistatTipusUsuaris()) {
				tipusUsuariComboBox.addItem(tipusUsuari);
				filtreComboBox.addItem(tipusUsuari);
			}

			for (String provincia : createUsuaris.getLlistatProvincies()) {
				provinciaComboBox.addItem(provincia);
			}

		}
	}

	public void setRowActiu(int row) {
		this.rowActiu = row;
	}

	/**
	 * controla el row(fila) que està activa
	 * 
	 * @param add        numero de rows que es moure cap endavant
	 * @param rowsTotals totals de rows de la taula
	 */
	public void seguentRowActiu(int add, int rowsTotals) {
		if (rowActiu == rowsTotals) {
			rowActiu = rowsTotals;
		} else {
			setRowActiu(rowActiu + add);
		}
	}

	/**
	 * controla el row(fila) que està activa
	 * 
	 * @param add        numero de rows que es moure cap enrrere
	 * @param rowsTotals totals de rows de la taula
	 */
	public void anteriorRowActiu(int add) {
		if (rowActiu == 0) {
			setRowActiu(0);
		} else {
			setRowActiu(rowActiu - add);
		}
	}

	/**
	 * Converteix la contrasenya introduïda en un String
	 * 
	 * @return String --> contrasenya
	 * @author SergioHernandez
	 */
	public String getContrasenyaToString() {
		String contrasenya = new String(getContrasenyaField().getPassword());
		return contrasenya;
	}

	public JTextField getRowActualField() {
		return rowActualField;
	}

	public JTextField getRowTotalsField() {
		return rowTotalsField;
	}

	public JTable getUsuarisTable() {
		return usuarisTable;
	}

	public JTextField getCercaField() {
		return cercaField;
	}

	public JRadioButton getCarnetRadioButton() {
		return carnetRadioButton;
	}

	public JRadioButton getInactiusRadioButton() {
		return inactiusRadioButton;
	}

	public JRadioButton getNomRadioButton() {
		return nomRadioButton;
	}

	public JComboBox getTipusUsuariComboBox() {
		return tipusUsuariComboBox;
	}

	public int getRowActiu() {
		return rowActiu;
	}

	public JButton getAltaUsuariButton() {
		return altaUsuariButton;
	}

	public JButton getCancellarButton() {
		return cancellarButton;
	}

	public JButton getBaixaUsuariButton() {
		return baixaUsuariButton;
	}

	public JButton getEditarUsuariButton() {
		return editarUsuariButton;
	}

	public JButton getConfirmarButton() {
		return confirmarButton;
	}

	public JButton getCercarButton() {
		return cercarButton;
	}

	public JButton getMostrarButton() {
		return mostrarButton;
	}

	public JLabel getSeguentIconLabel() {
		return seguentIconLabel;
	}

	public JLabel getAnteriorIconLabel() {
		return anteriorIconLabel;
	}

	public JLabel getLupaLabel() {
		return lupaLabel;
	}

	public JLabel getEsborrarLabel() {
		return esborrarLabel;
	}

	public ButtonGroup getFiltreButtonGroup() {
		return filtreButtonGroup;
	}

	public JComboBox getFiltreComboBox() {
		return filtreComboBox;
	}

	public String getAccio() {
		return accio;
	}

	public void setAccio(String accio) {
		this.accio = accio;
	}

	public JPanel getPaginadorPanel() {
		return paginadorPanel;
	}

	public JTextField getIdUsuariField() {
		return idUsuariField;
	}

	public JTextField getNomField() {
		return nomField;
	}

	public JTextField getCognomsField() {
		return cognomsField;
	}

	public JTextField getDniNieField() {
		return dniNieField;
	}

	public JTextField getEmailField() {
		return emailField;
	}

	public JPasswordField getContrasenyaField() {
		return contrasenyaField;
	}

	public JTextField getCarnetField() {
		return carnetField;
	}

	public JTextField getAdrecaField() {
		return adrecaField;
	}

	public JTextField getCodiPostalField() {
		return codiPostalField;
	}

	public JTextField getPoblacioField() {
		return poblacioField;
	}

	public JComboBox getProvinciaComboBox() {
		return provinciaComboBox;
	}

	public JTextField getPaisField() {
		return paisField;
	}

	public JTextField getTelefonField() {
		return telefonField;
	}

	public JTextField getPuntuacioField() {
		return puntuacioField;
	}

	public JCheckBox getActiuCheckBox() {
		return actiuCheckBox;
	}

	public JLabel getMostrarContrasenyaLabel() {
		return mostrarContrasenyaLabel;
	}

	public Usuari getUsuariConnectat() {
		return usuariConnectat;
	}

	public void setUsuariConnectat(Usuari usuariConnectat) {
		this.usuariConnectat = usuariConnectat;
	}

}
