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
import al3xandria.controlador.administradorLlibres.AdministradorLlibresControlador;
import al3xandria.model.llibres.LlibresModel;
import al3xandria.model.objects.CreateLlibres;
import al3xandria.model.objects.Usuari;
import al3xandria.vista.icons.Icons;

import javax.swing.border.EtchedBorder;
import javax.swing.JTextArea;
import java.awt.ComponentOrientation;
import java.awt.Cursor;

import javax.swing.ButtonGroup;

public class AdministradorLlibres extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Icons icones;
	private int rowActiu;
	private LlibresModel llibresModel;
	private String accio;
	private CreateLlibres createLlibres;
	private Usuari usuariConnectat;

	private JTextField cercaField;
	private JTable llibresTable;
	private JTextField idLlibreField;
	private JTextField titolField;
	private JTextField numeroPaginesField;
	private JTextField numeroReservesField;
	private JTextField puntuacioField;
	private JTextField isbnField;
	private JTextField dataPublicacioField;
	private final ButtonGroup filtreButtonGroup = new ButtonGroup();
	private JPanel llibresPanel;
	private JPanel filtresPanel;
	private JPanel filtreButtonPanel;
	private JLabel filtrePerLabel;
	private JRadioButton titolRadioButton;
	private JRadioButton autorRadioButton;
	private FlowLayout fl_filtreButtonPanel;
	private JRadioButton genereRadioButton;
	private JRadioButton editorialRadioButton;
	private JPanel filtreTextPanel;
	private JLabel lupaLabel;
	private FlowLayout fl_filtreTextPanel;
	private JLabel separacioLabel;
	private JButton cercarButton;
	private JPanel llistaTablePanel;
	private JScrollPane llistatLlibresScrollPane;
	private JPanel accionsButtonsPanel;
	private JButton altaLlibreButton;
	private JButton baixaLlibreButton;
	private JButton editarLlibreButton;
	private JPanel dadesLlibrePanel;
	private JPanel dadesLlibreGridPanel;
	private JPanel idTitolDisponiblePanel;
	private JPanel idPanel;
	private JLabel idLabel;
	private JPanel titolPanel;
	private JLabel titolLabel;
	private JPanel disponiblePanel;
	private JLabel reservatLabel;
	private JCheckBox reservatCheckBox;
	private JPanel autorGenereEditorialPuntuacioPanel;
	private JPanel autorsPanel;
	private JLabel autorLabel;
	private JComboBox autorsComboBox;
	private JPanel generesPanel;
	private JLabel generesLabel;
	private JComboBox generesComboBox;
	private JPanel editorialsPanel;
	private JLabel editorialsLabel;
	private JComboBox editorialsComboBox;
	private JPanel puntuacioPanel;
	private JLabel puntuacioLabel;
	private JPanel isbnDataPaginesReservesPanel;
	private FlowLayout fl_isbnDataPaginesReservesPanel;
	private JPanel isbnPanel;
	private JLabel isbnLabel;
	private JPanel dataPublicacioPanel;
	private JLabel dataPublicacioLabel;
	private JPanel numeroPaginesPanel;
	private JLabel numeroPaginesLabel;
	private JPanel numeroReservesPanel;
	private JLabel numeroReservesLabel;
	private JPanel sinopsisPanel;
	private JLabel sinopsisLabel;
	private JTextArea sinopsisTextArea;
	private JScrollPane sinopsisScrollPane;
	private JLabel afegirAutorLabel;
	private JLabel afegirGenereLabel;
	private JLabel afegirEditorialLabel;
	private JPanel paginadorPanel;
	private FlowLayout fl_paginadorPanel;
	private JLabel anteriorLabel;
	private JLabel anteriorIconLabel;
	private JTextField rowActualField;
	private JLabel ofLabel;
	private JLabel seguentIconLabel;
	private JTextField rowTotalsField;
	private JLabel seguentLabel;
	private JPanel edicioPanel;
	private JLabel edicioLabel;
	private JTextField edicioField;
	private JLabel esborrarLabel;
	private JButton mostrarLlibreButton;
	private JButton confirmarButton;
	private JButton cancellarButton;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	
	

	/**
	 * Create the panel.
	 */
	public AdministradorLlibres(Usuari usuariConnectat) {
		this.usuariConnectat = usuariConnectat;
		icones = new Icons();
		setBounds(new Rectangle(0, 0, 750, 850));
		setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

		iniciarComponents();
		llistarLlibres();
		
	}
	
		

		private void iniciarComponents() {
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

		filtrePerLabel = new JLabel(CentralPanelMessages.getString("AdministradorLlibres.filtrePerLabel.text")); //$NON-NLS-1$
		filtrePerLabel
				.setToolTipText(CentralPanelMessages.getString("AdministradorLlibres.filtrePerLabel.toolTipText")); //$NON-NLS-1$
		filtrePerLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		filtreButtonPanel.add(filtrePerLabel);

		titolRadioButton = new JRadioButton(
				CentralPanelMessages.getString("AdministradorLlibres.titolRadioButton.text")); //$NON-NLS-1$
		titolRadioButton.setSelected(true);
		filtreButtonGroup.add(titolRadioButton);
		titolRadioButton
				.setToolTipText(CentralPanelMessages.getString("AdministradorLlibres.titolRadioButton.toolTipText")); //$NON-NLS-1$
		filtreButtonPanel.add(titolRadioButton);

		autorRadioButton = new JRadioButton(
				CentralPanelMessages.getString("AdministradorLlibres.autorRadioButton.text")); //$NON-NLS-1$
		filtreButtonGroup.add(autorRadioButton);
		autorRadioButton
				.setToolTipText(CentralPanelMessages.getString("AdministradorLlibres.autorRadioButton.toolTipText")); //$NON-NLS-1$
		filtreButtonPanel.add(autorRadioButton);

		genereRadioButton = new JRadioButton(
				CentralPanelMessages.getString("AdministradorLlibres.genereRadioButton.text")); //$NON-NLS-1$
		filtreButtonGroup.add(genereRadioButton);
		genereRadioButton
				.setToolTipText(CentralPanelMessages.getString("AdministradorLlibres.genereRadioButton.toolTipText")); //$NON-NLS-1$
		filtreButtonPanel.add(genereRadioButton);

		editorialRadioButton = new JRadioButton(
				CentralPanelMessages.getString("AdministradorLlibres.editorialRadioButton.text")); //$NON-NLS-1$
		filtreButtonGroup.add(editorialRadioButton);
		editorialRadioButton.setToolTipText(
				CentralPanelMessages.getString("AdministradorLlibres.editorialRadioButton.toolTipText")); //$NON-NLS-1$
		filtreButtonPanel.add(editorialRadioButton);

		filtreTextPanel = new JPanel();
		fl_filtreTextPanel = (FlowLayout) filtreTextPanel.getLayout();
		fl_filtreTextPanel.setAlignment(FlowLayout.RIGHT);
		filtresPanel.add(filtreTextPanel, BorderLayout.CENTER);

		lupaLabel = new JLabel("");
		lupaLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lupaLabel.setToolTipText(CentralPanelMessages.getString("ConsultaLlibresNoRegistrat.lupaLabel.toolTipText")); //$NON-NLS-1$
		lupaLabel.setIcon(icones.getLupaIcon());
		lupaLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lupaLabel.addMouseListener(new AdministradorLlibresControlador(this, usuariConnectat));
		filtreTextPanel.add(lupaLabel);

		cercaField = new JTextField();
		cercaField.setToolTipText(CentralPanelMessages.getString("ConsultaLlibresNoRegistrat.cercaField.toolTipText")); //$NON-NLS-1$
		filtreTextPanel.add(cercaField);
		cercaField.setColumns(20);

		esborrarLabel = new JLabel("");
		esborrarLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		esborrarLabel
				.setToolTipText(CentralPanelMessages.getString("ConsultaLlibresNoRegistrat.esborrarLabel.toolTipText")); //$NON-NLS-1$
		esborrarLabel.setIcon(icones.getCancelIcon());
		esborrarLabel.setHorizontalAlignment(SwingConstants.CENTER);
		esborrarLabel.addMouseListener(new AdministradorLlibresControlador(this,usuariConnectat));
		filtreTextPanel.add(esborrarLabel);

		separacioLabel = new JLabel("   ");
		filtreTextPanel.add(separacioLabel);

		cercarButton = new JButton(CentralPanelMessages.getString("ConsultaLlibresNoRegistrat.cercarButton.text"));
		cercarButton.setForeground(Color.BLACK);
		cercarButton.setBackground(new Color(173, 216, 230));
		cercarButton
				.setToolTipText(CentralPanelMessages.getString("ConsultaLlibresNoRegistrat.cercarButton.toolTipText")); //$NON-NLS-1$
		cercarButton.addMouseListener(new AdministradorLlibresControlador(this,usuariConnectat));
		filtreTextPanel.add(cercarButton);

		llistaTablePanel = new JPanel();
		llistaTablePanel.setBackground(new Color(255, 255, 255));
		llibresPanel.add(llistaTablePanel, BorderLayout.CENTER);
		llistaTablePanel.setLayout(new BorderLayout(10, 0));

		llibresTable = new JTable();
		llibresTable.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		llibresTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		llibresTable.setFillsViewportHeight(true);

		llibresTable.setBackground(new Color(255, 255, 255));

	
		llibresTable.setFocusable(false);
		llistatLlibresScrollPane = new JScrollPane(llibresTable);
		llistatLlibresScrollPane.setPreferredSize(new java.awt.Dimension(0, 100));

		llistatLlibresScrollPane.setBackground(new Color(255, 255, 255));
		llistaTablePanel.add(llistatLlibresScrollPane, BorderLayout.CENTER);

		accionsButtonsPanel = new JPanel();
		accionsButtonsPanel.setBackground(Color.WHITE);
		llistaTablePanel.add(accionsButtonsPanel, BorderLayout.EAST);
		accionsButtonsPanel.setLayout(new GridLayout(8, 0, 0, 0));

		mostrarLlibreButton = new JButton(
				CentralPanelMessages.getString("ConsultaLlibresNoRegistrat.mostrarLlibreButton.text"));
		mostrarLlibreButton.setForeground(Color.WHITE);
		mostrarLlibreButton.setBackground(Color.decode("#00838f"));
		mostrarLlibreButton.setToolTipText(
				CentralPanelMessages.getString("ConsultaLlibresNoRegistrat.mostrarLlibreButton.toolTipText")); //$NON-NLS-1$
		mostrarLlibreButton.addMouseListener(new AdministradorLlibresControlador(this,usuariConnectat));
		accionsButtonsPanel.add(mostrarLlibreButton);
		
		altaLlibreButton = new JButton(CentralPanelMessages.getString("AdministradorLlibres.altaLlibreButton.text")); //$NON-NLS-1$
		altaLlibreButton.setForeground(Color.WHITE);
		altaLlibreButton.setBackground(new Color(70, 130, 180)); //$NON-NLS-1$
		altaLlibreButton.addMouseListener(new AdministradorLlibresControlador(this,usuariConnectat));
		altaLlibreButton
				.setToolTipText(CentralPanelMessages.getString("AdministradorLlibres.altaLlibreButton.toolTipText")); //$NON-NLS-1$
		accionsButtonsPanel.add(altaLlibreButton);

		baixaLlibreButton = new JButton(CentralPanelMessages.getString("AdministradorLlibres.baixaLlibreButton.text")); //$NON-NLS-1$
		baixaLlibreButton.setBackground(new Color(165, 42, 42));
		baixaLlibreButton.setForeground(Color.WHITE);
		baixaLlibreButton.addMouseListener(new AdministradorLlibresControlador(this,usuariConnectat));
		baixaLlibreButton
				.setToolTipText(CentralPanelMessages.getString("AdministradorLlibres.baixaLlibreButton.toolTipText")); //$NON-NLS-1$
		accionsButtonsPanel.add(baixaLlibreButton);

		editarLlibreButton = new JButton(
				CentralPanelMessages.getString("AdministradorLlibres.editarLlibreButton.text")); //$NON-NLS-1$
		editarLlibreButton.setForeground(Color.WHITE);
		editarLlibreButton.setBackground(Color.decode("#6a1b9a")); //$NON-NLS-1$
		editarLlibreButton.addMouseListener(new AdministradorLlibresControlador(this,usuariConnectat));
		editarLlibreButton
				.setToolTipText(CentralPanelMessages.getString("AdministradorLlibres.editarLlibreButton.toolTipText")); //$NON-NLS-1$
		accionsButtonsPanel.add(editarLlibreButton);
		
		confirmarButton = new JButton("Confirmar");
		confirmarButton.setForeground(Color.WHITE);
		confirmarButton.setBackground(new Color(255, 204, 51));
		confirmarButton.setToolTipText("Confirmar l'acció"); //$NON-NLS-1$
		confirmarButton.addMouseListener(new AdministradorLlibresControlador(this,usuariConnectat));
		confirmarButton.setVisible(false);
		
		lblNewLabel = new JLabel("");
		accionsButtonsPanel.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("");
		accionsButtonsPanel.add(lblNewLabel_1);
		accionsButtonsPanel.add(confirmarButton);
		
		cancellarButton = new JButton("Cancel.lar"); //$NON-NLS-1$
		cancellarButton.setForeground(Color.WHITE);
		cancellarButton.setBackground(new Color(255, 0, 0));
		cancellarButton.setToolTipText("Cancel.lar l'acció"); //$NON-NLS-1$
		cancellarButton.addMouseListener(new AdministradorLlibresControlador(this,usuariConnectat));
		cancellarButton.setVisible(false);
		accionsButtonsPanel.add(cancellarButton);

		dadesLlibrePanel = new JPanel();
		llibresPanel.add(dadesLlibrePanel, BorderLayout.SOUTH);
		dadesLlibrePanel.setLayout(new BorderLayout(0, 0));

		dadesLlibreGridPanel = new JPanel();
		dadesLlibreGridPanel.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Dades del llibre", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		dadesLlibrePanel.add(dadesLlibreGridPanel, BorderLayout.NORTH);
		GridBagLayout gbl_dadesLlibreGridPanel = new GridBagLayout();
		gbl_dadesLlibreGridPanel.columnWidths = new int[] { 619, 0 };
		gbl_dadesLlibreGridPanel.rowHeights = new int[] { 22, 22, 22, 22, 22, 0 };
		gbl_dadesLlibreGridPanel.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gbl_dadesLlibreGridPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		dadesLlibreGridPanel.setLayout(gbl_dadesLlibreGridPanel);

		paginadorPanel = new JPanel();
		fl_paginadorPanel = (FlowLayout) paginadorPanel.getLayout();
		fl_paginadorPanel.setVgap(0);
		GridBagConstraints gbc_paginadorPanel1 = new GridBagConstraints();
		gbc_paginadorPanel1.fill = GridBagConstraints.BOTH;
		gbc_paginadorPanel1.insets = new Insets(0, 0, 5, 0);
		gbc_paginadorPanel1.gridx = 0;
		gbc_paginadorPanel1.gridy = 0;
		paginadorPanel.setVisible(false);
		dadesLlibreGridPanel.add(paginadorPanel, gbc_paginadorPanel1);

		anteriorLabel = new JLabel("anterior"); //$NON-NLS-1$
		paginadorPanel.add(anteriorLabel);

		anteriorIconLabel = new JLabel("");
		anteriorIconLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		anteriorIconLabel.setIcon(icones.getAnteriorIcon());
		anteriorIconLabel
				.setToolTipText(CentralPanelMessages.getString("ConsultaLlibresNoRegistrat.anteriorLabel.toolTipText")); //$NON-NLS-1$
		anteriorIconLabel.addMouseListener(new AdministradorLlibresControlador(this,usuariConnectat));
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
		seguentIconLabel.addMouseListener(new AdministradorLlibresControlador(this,usuariConnectat));

		rowTotalsField = new JTextField();
		rowTotalsField.setEditable(false);
		rowTotalsField.setText("");
		paginadorPanel.add(rowTotalsField);
		rowTotalsField.setColumns(2);
		paginadorPanel.add(seguentIconLabel);

		seguentLabel = new JLabel(CentralPanelMessages.getString("ConsultaLlibresNoRegistrat.lblNewLabel_1.text")); //$NON-NLS-1$
		paginadorPanel.add(seguentLabel);

		idTitolDisponiblePanel = new JPanel();
		GridBagConstraints gbc_idTitolDisponiblePanel = new GridBagConstraints();
		gbc_idTitolDisponiblePanel.fill = GridBagConstraints.BOTH;
		gbc_idTitolDisponiblePanel.insets = new Insets(0, 0, 5, 0);
		gbc_idTitolDisponiblePanel.gridx = 0;
		gbc_idTitolDisponiblePanel.gridy = 1;
		dadesLlibreGridPanel.add(idTitolDisponiblePanel, gbc_idTitolDisponiblePanel);
		idTitolDisponiblePanel.setLayout(new FlowLayout(FlowLayout.LEADING, 10, 2));

		idPanel = new JPanel();
		idTitolDisponiblePanel.add(idPanel);

		idLabel = new JLabel(CentralPanelMessages.getString("AdministradorLlibres.idLabel.text")); //$NON-NLS-1$
		idPanel.add(idLabel);

		idLlibreField = new JTextField();
		idLlibreField.setEditable(false);
		idLlibreField.setToolTipText(CentralPanelMessages.getString("AdministradorLlibres.idLlibreField.toolTipText")); //$NON-NLS-1$
		idPanel.add(idLlibreField);
		idLlibreField.setColumns(3);

		titolPanel = new JPanel();
		idTitolDisponiblePanel.add(titolPanel);

		titolLabel = new JLabel(CentralPanelMessages.getString("AdministradorLlibres.titolLabel.text")); //$NON-NLS-1$
		titolPanel.add(titolLabel);

		titolField = new JTextField();
		titolField.setEditable(false);
		titolField.setToolTipText(CentralPanelMessages.getString("AdministradorLlibres.titolField.toolTipText")); //$NON-NLS-1$
		titolPanel.add(titolField);
		titolField.setColumns(40);

		disponiblePanel = new JPanel();
		idTitolDisponiblePanel.add(disponiblePanel);

		reservatLabel = new JLabel(CentralPanelMessages.getString("AdministradorLlibres.reservatLabel.text")); //$NON-NLS-1$
		reservatLabel.setToolTipText(CentralPanelMessages.getString("AdministradorLlibres.reservatLabel.toolTipText")); //$NON-NLS-1$
		disponiblePanel.add(reservatLabel);

		reservatCheckBox = new JCheckBox(""); //$NON-NLS-1$
		reservatCheckBox.setEnabled(false);
		disponiblePanel.add(reservatCheckBox);

		autorGenereEditorialPuntuacioPanel = new JPanel();
		GridBagConstraints gbc_autorGenereEditorialPuntuacioPanel = new GridBagConstraints();
		gbc_autorGenereEditorialPuntuacioPanel.fill = GridBagConstraints.BOTH;
		gbc_autorGenereEditorialPuntuacioPanel.insets = new Insets(0, 0, 5, 0);
		gbc_autorGenereEditorialPuntuacioPanel.gridx = 0;
		gbc_autorGenereEditorialPuntuacioPanel.gridy = 2;
		dadesLlibreGridPanel.add(autorGenereEditorialPuntuacioPanel, gbc_autorGenereEditorialPuntuacioPanel);
		autorGenereEditorialPuntuacioPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 10, 2));

		autorsPanel = new JPanel();
		autorGenereEditorialPuntuacioPanel.add(autorsPanel);

		autorLabel = new JLabel(CentralPanelMessages.getString("AdministradorLlibres.autorLabel.text")); //$NON-NLS-1$
		autorsPanel.add(autorLabel);
		autorLabel.setHorizontalAlignment(SwingConstants.RIGHT);

		autorsComboBox = new JComboBox();
		autorsComboBox.setEnabled(false);
		autorsComboBox
				.setToolTipText(CentralPanelMessages.getString("AdministradorLlibres.autorsComboBox.toolTipText")); //$NON-NLS-1$
		autorsPanel.add(autorsComboBox);
		autorsComboBox.addItem("Autors     ");

		afegirAutorLabel = new JLabel(); // $NON-NLS-1$
		afegirAutorLabel.setEnabled(false);
		afegirAutorLabel
				.setToolTipText(CentralPanelMessages.getString("AdministradorLlibres.afegirAutorLabel.toolTipText")); //$NON-NLS-1$
		afegirAutorLabel.setIcon(icones.getAddIcon());
		afegirAutorLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		afegirAutorLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		afegirAutorLabel.addMouseListener(new AdministradorLlibresControlador(this,usuariConnectat));
		autorsPanel.add(afegirAutorLabel);

		generesPanel = new JPanel();
		autorGenereEditorialPuntuacioPanel.add(generesPanel);

		generesLabel = new JLabel(CentralPanelMessages.getString("AdministradorLlibres.generesLabel.text")); //$NON-NLS-1$
		generesPanel.add(generesLabel);
		generesLabel.setHorizontalAlignment(SwingConstants.RIGHT);

		generesComboBox = new JComboBox();
		generesComboBox.setEnabled(false);
		generesComboBox
				.setToolTipText(CentralPanelMessages.getString("AdministradorLlibres.generesComboBox.toolTipText")); //$NON-NLS-1$
		generesPanel.add(generesComboBox);
		generesComboBox.addItem("Gèneres     ");

		afegirGenereLabel = new JLabel(); // $NON-NLS-1$
		afegirGenereLabel.setEnabled(false);
		afegirGenereLabel
				.setToolTipText(CentralPanelMessages.getString("AdministradorLlibres.nouGenereLabel.toolTipText")); //$NON-NLS-1$
		afegirGenereLabel.setIcon(icones.getAddIcon());
		afegirGenereLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		afegirGenereLabel.addMouseListener(new AdministradorLlibresControlador(this,usuariConnectat));
		afegirGenereLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		generesPanel.add(afegirGenereLabel);

		editorialsPanel = new JPanel();
		autorGenereEditorialPuntuacioPanel.add(editorialsPanel);

		editorialsLabel = new JLabel(CentralPanelMessages.getString("AdministradorLlibres.editorialsLabel.text")); //$NON-NLS-1$
		editorialsPanel.add(editorialsLabel);
		editorialsLabel.setHorizontalAlignment(SwingConstants.RIGHT);

		editorialsComboBox = new JComboBox();
		editorialsComboBox.setEnabled(false);
		editorialsComboBox
				.setToolTipText(CentralPanelMessages.getString("AdministradorLlibres.editorialsComboBox.toolTipText")); //$NON-NLS-1$
		editorialsPanel.add(editorialsComboBox);
		editorialsComboBox.addItem("Editorials     ");

		afegirEditorialLabel = new JLabel(); // $NON-NLS-1$
		afegirEditorialLabel.setEnabled(false);
		afegirEditorialLabel
				.setToolTipText(CentralPanelMessages.getString("AdministradorLlibres.nouEditorialLabel.toolTipText")); //$NON-NLS-1$
		afegirEditorialLabel.setIcon(icones.getAddIcon());
		afegirEditorialLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		afegirEditorialLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		afegirEditorialLabel.addMouseListener(new AdministradorLlibresControlador(this,usuariConnectat));
		editorialsPanel.add(afegirEditorialLabel);

		puntuacioPanel = new JPanel();
		autorGenereEditorialPuntuacioPanel.add(puntuacioPanel);

		puntuacioLabel = new JLabel(CentralPanelMessages.getString("AdministradorLlibres.puntuacioLabel.text")); //$NON-NLS-1$
		puntuacioPanel.add(puntuacioLabel);

		puntuacioField = new JTextField();
		puntuacioField.setEditable(false);
		puntuacioField
				.setToolTipText(CentralPanelMessages.getString("AdministradorLlibres.puntuacioField.toolTipText")); //$NON-NLS-1$
		puntuacioPanel.add(puntuacioField);
		puntuacioField.setColumns(4);

		isbnDataPaginesReservesPanel = new JPanel();
		fl_isbnDataPaginesReservesPanel = (FlowLayout) isbnDataPaginesReservesPanel.getLayout();
		fl_isbnDataPaginesReservesPanel.setHgap(10);
		fl_isbnDataPaginesReservesPanel.setVgap(2);
		fl_isbnDataPaginesReservesPanel.setAlignment(FlowLayout.LEADING);
		GridBagConstraints gbc_isbnDataPaginesReservesPanel = new GridBagConstraints();
		gbc_isbnDataPaginesReservesPanel.fill = GridBagConstraints.BOTH;
		gbc_isbnDataPaginesReservesPanel.insets = new Insets(0, 0, 5, 0);
		gbc_isbnDataPaginesReservesPanel.gridx = 0;
		gbc_isbnDataPaginesReservesPanel.gridy = 3;
		dadesLlibreGridPanel.add(isbnDataPaginesReservesPanel, gbc_isbnDataPaginesReservesPanel);

		isbnPanel = new JPanel();
		isbnDataPaginesReservesPanel.add(isbnPanel);

		isbnLabel = new JLabel(CentralPanelMessages.getString("AdministradorLlibres.isbnLabel.text")); //$NON-NLS-1$
		isbnPanel.add(isbnLabel);

		isbnField = new JTextField();
		isbnField.setEditable(false);
		isbnField.setToolTipText(CentralPanelMessages.getString("AdministradorLlibres.isbnField.toolTipText")); //$NON-NLS-1$
		isbnPanel.add(isbnField);
		isbnField.setColumns(9);

		edicioPanel = new JPanel();
		isbnDataPaginesReservesPanel.add(edicioPanel);

		edicioLabel = new JLabel(CentralPanelMessages.getString("ConsultaLlibresNoRegistrat.lblNewLabel.text"));
		edicioPanel.add(edicioLabel);

		edicioField = new JTextField();
		edicioField.setEditable(false);
		edicioField.setToolTipText(CentralPanelMessages.getString("ConsultaLlibresNoRegistrat.textField.toolTipText")); //$NON-NLS-1$
		edicioField.setText("");
		edicioPanel.add(edicioField);
		edicioField.setColumns(6);
		
		dataPublicacioPanel = new JPanel();
		isbnDataPaginesReservesPanel.add(dataPublicacioPanel);

		dataPublicacioLabel = new JLabel(
				CentralPanelMessages.getString("AdministradorLlibres.dataPublicacioLabel.text")); //$NON-NLS-1$
		dataPublicacioPanel.add(dataPublicacioLabel);

		dataPublicacioField = new JTextField();
		dataPublicacioField.setEditable(false);
		dataPublicacioField
				.setToolTipText(CentralPanelMessages.getString("AdministradorLlibres.dataPublicacioField.toolTipText")); //$NON-NLS-1$
		dataPublicacioPanel.add(dataPublicacioField);
		dataPublicacioField.setColumns(6);

		numeroPaginesPanel = new JPanel();
		isbnDataPaginesReservesPanel.add(numeroPaginesPanel);

		numeroPaginesLabel = new JLabel(CentralPanelMessages.getString("AdministradorLlibres.numeroPaginesLabel.text")); //$NON-NLS-1$
		numeroPaginesPanel.add(numeroPaginesLabel);

		numeroPaginesField = new JTextField();
		numeroPaginesField.setEditable(false);
		numeroPaginesField
				.setToolTipText(CentralPanelMessages.getString("AdministradorLlibres.numeroPaginesField.toolTipText")); //$NON-NLS-1$
		numeroPaginesPanel.add(numeroPaginesField);
		numeroPaginesField.setColumns(3);

		numeroReservesPanel = new JPanel();
		isbnDataPaginesReservesPanel.add(numeroReservesPanel);

		numeroReservesLabel = new JLabel(
				CentralPanelMessages.getString("AdministradorLlibres.numeroReservesLabel.text")); //$NON-NLS-1$
		numeroReservesPanel.add(numeroReservesLabel);

		numeroReservesField = new JTextField();
		numeroReservesField.setEditable(false);
		numeroReservesField
				.setToolTipText(CentralPanelMessages.getString("AdministradorLlibres.numeroReservesField.toolTipText")); //$NON-NLS-1$
		numeroReservesPanel.add(numeroReservesField);
		numeroReservesField.setColumns(2);

		sinopsisPanel = new JPanel();
		FlowLayout fl_sinopsisPanel = (FlowLayout) sinopsisPanel.getLayout();
		fl_sinopsisPanel.setHgap(10);
		fl_sinopsisPanel.setVgap(2);
		fl_sinopsisPanel.setAlignment(FlowLayout.LEADING);
		GridBagConstraints gbc_sinopsisPanel = new GridBagConstraints();
		gbc_sinopsisPanel.anchor = GridBagConstraints.NORTH;
		gbc_sinopsisPanel.fill = GridBagConstraints.HORIZONTAL;
		gbc_sinopsisPanel.gridx = 0;
		gbc_sinopsisPanel.gridy = 4;
		dadesLlibreGridPanel.add(sinopsisPanel, gbc_sinopsisPanel);

		sinopsisLabel = new JLabel(CentralPanelMessages.getString("AdministradorLlibres.sinopsisLabel.text")); //$NON-NLS-1$
		sinopsisLabel.setVerticalAlignment(SwingConstants.TOP);
		sinopsisPanel.add(sinopsisLabel);

		sinopsisTextArea = new JTextArea();
		sinopsisTextArea.setEditable(false);
		sinopsisTextArea
				.setToolTipText(CentralPanelMessages.getString("AdministradorLlibres.sinopsisTextArea.toolTipText")); //$NON-NLS-1$

		sinopsisTextArea.setColumns(60);
		sinopsisTextArea.setLineWrap(true);
		sinopsisTextArea.setWrapStyleWord(true);
		sinopsisTextArea.setText("");
		sinopsisTextArea.setRows(4);

		sinopsisScrollPane = new JScrollPane(sinopsisTextArea);

		sinopsisPanel.add(sinopsisScrollPane);

	}
	
	/**
	 * Mètode que assigna un model a table i l'omple amb els llibres
	 * @author SergioHernandez
	 */
	private void llistarLlibres() {
		llibresModel = new LlibresModel();
		try {
			llibresTable.setModel(llibresModel.consultarTotsElsLlibres());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * Mètode que omple els combobox de autors, gènere i editorials
	 * @author SergioHernandez
	 */
	public void omplirComboBox() {
		//Per fer proves omplia els combobox desde un resultset
//		for(String genere:llibresModel.consultarTotsElsGeneres()) {
//			generesComboBox.addItem(genere);
//		}
//		
//		for(String autor:llibresModel.consultarTotsElsAutors()) {
//			autorsComboBox.addItem(autor);
//		}
//		
//		for(String editorial:llibresModel.consultarTotesLesEditorials()) {
//			editorialsComboBox.addItem(editorial);
//		}
		
		//Per entregar el TEA3 els omplo amb addItem i CreateLlibres()
		createLlibres = new CreateLlibres();
		if(generesComboBox.getItemCount() == 1) {
			for(String genere : createLlibres.getGeneres()) {
				generesComboBox.addItem(genere);
			}
			
			for(String autor : createLlibres.getAutors()) {
				autorsComboBox.addItem(autor);
			}
			
			for(String editorial : createLlibres.getEditorials()) {
				editorialsComboBox.addItem(editorial);
			}
		}
	
	}
	
		
	
	/*-------------------------- Getters and Setters Methods --------------------------*/
	public JLabel getLupaLabel() {
		return lupaLabel;
	}

	public JLabel getEsborrarLabel() {
		return esborrarLabel;
	}

	public JTextField getCercaField() {
		return cercaField;
	}

	public JButton getCercarButton() {
		return cercarButton;
	}

	public JButton getMostrarLlibreButton() {
		return mostrarLlibreButton;
	}

	public ButtonGroup getFiltreButtonGroup() {
		return filtreButtonGroup;
	}

	public JRadioButton getTitolRadioButton() {
		return titolRadioButton;
	}

	public JRadioButton getGenereRadioButton() {
		return genereRadioButton;
	}

	public JRadioButton getEditorialRadioButton() {
		return editorialRadioButton;
	}

	public JRadioButton getAutorRadioButton() {
		return autorRadioButton;
	}

	public JTable getLlibresTable() {
		return llibresTable;
	}

	public JTextField getIdLlibreField() {
		return idLlibreField;
	}

	public void setIdLlibreField(JTextField idLlibreField) {
		this.idLlibreField = idLlibreField;
	}
	
	public JComboBox getAutorsComboBox() {
		return autorsComboBox;
	}
	
	public JComboBox getEditorialsComboBox() {
		return editorialsComboBox;
	}
	
	public JComboBox getGeneresComboBox() {
		return generesComboBox;
	}

	public JTextField getTitolField() {
		return titolField;
	}

	public void setTitolField(JTextField titolField) {
		this.titolField = titolField;
	}

	public JTextField getNumeroPaginesField() {
		return numeroPaginesField;
	}

	public void setNumeroPaginesField(JTextField numeroPaginesField) {
		this.numeroPaginesField = numeroPaginesField;
	}

	public JTextField getNumeroReservesField() {
		return numeroReservesField;
	}

	public void setNumeroReservesField(JTextField numeroReservesField) {
		this.numeroReservesField = numeroReservesField;
	}

	public JTextField getPuntuacioField() {
		return puntuacioField;
	}

	public void setPuntuacioField(JTextField puntuacioField) {
		this.puntuacioField = puntuacioField;
	}

	public JTextField getIsbnField() {
		return isbnField;
	}

	public void setIsbnField(JTextField isbnField) {
		this.isbnField = isbnField;
	}

	public JTextField getDataPublicacioField() {
		return dataPublicacioField;
	}

	public void setDataPublicacioField(JTextField dataPublicacioField) {
		this.dataPublicacioField = dataPublicacioField;
	}

	public JCheckBox getReservatCheckBox() {
		return reservatCheckBox;
	}

	public void setReservatCheckBox(JCheckBox reservatCheckBox) {
		this.reservatCheckBox = reservatCheckBox;
	}

	
	public JTextArea getSinopsisTextArea() {
		return sinopsisTextArea;
	}

	public void setSinopsisTextArea(JTextArea sinopsisTextArea) {
		this.sinopsisTextArea = sinopsisTextArea;
	}

	public JTextField getEdicioField() {
		return edicioField;
	}

	public JLabel getAnteriorIconLabel() {
		return anteriorIconLabel;
	}

	public JLabel getSeguentIconLabel() {
		return seguentIconLabel;
	}

	public JPanel getPaginadorPanel() {
		return paginadorPanel;
	}

	public void setRowActiu(int row) {
		this.rowActiu  = row;
	}

	public void seguentRowActiu(int add, int rowsTotals) {
		if (rowActiu == rowsTotals) {
			rowActiu = rowsTotals;
		} else {
			setRowActiu(rowActiu + add);
		}
	}

	public void anteriorRowActiu(int add) {
		if (rowActiu == 0) {
			setRowActiu(0);
		} else {
			setRowActiu(rowActiu - add);
		}
	}

	public int getRowActiu() {
		return rowActiu;
	}

	public JTextField getRowActualField() {
		return rowActualField;
	}

	public JTextField getRowTotalsField() {
		return rowTotalsField;
	}
	
	public JButton getAltaLlibreButton() {
		return altaLlibreButton;
	}
	
	public JButton getBaixaLlibreButton() {
		return baixaLlibreButton;
	}
	
	public JButton getEditarLlibreButton() {
		return editarLlibreButton;
	}
	
	public JLabel getAfegirAutorLabel() {
		return afegirAutorLabel;
	}
	
	public JLabel getAfegirEditorialLabel() {
		return afegirEditorialLabel;
	}
	
	public JLabel getAfegirGenereLabel() {
		return afegirGenereLabel;
	}
	
	public JButton getConfirmarButton() {
		return confirmarButton;
	}
	
	public JButton getCancellarButton() {
		return cancellarButton;
	}
	
	public String getAccio() {
		return accio;
	}
	
	public void setAccio(String accio) {
		this.accio = accio;
	}
	
	public Usuari getUsuariConnectat() {
		return usuariConnectat;
	}
	
	public void setUsuariConnectat(Usuari usuariConnectat) {
		this.usuariConnectat = usuariConnectat;
	}
}
