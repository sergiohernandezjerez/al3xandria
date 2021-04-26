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
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import al3xandria.controlador.consultaLlibres.ConsultaLlibresControlador;
import al3xandria.model.llibres.LlibresModel;
import al3xandria.model.objects.Usuari;
import al3xandria.vista.icons.Icons;

import javax.swing.JTextArea;
import java.awt.ComponentOrientation;
import java.awt.Cursor;

import javax.swing.ButtonGroup;

public class ConsultaLlibres extends JPanel {

	/**
	 * Classe que crea el Jpanel per consultar els llibres per els usuaris
	 * registrats
	 * 
	 * @author SergioHernandez
	 */
	private static final long serialVersionUID = 1L;
	private int rowActiu;

	private LlibresModel llibresModel;
	private Icons icons;
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
	private FlowLayout fl_filtreButtonPanel;
	private JLabel filtrePerLabel;
	private JRadioButton titolRadioButton;
	private JRadioButton autorRadioButton;
	private JRadioButton genereRadioButton;
	private JRadioButton editorialRadioButton;
	private JPanel filtreTextPanel;
	private FlowLayout fl_filtreTextPanel;
	private JLabel lupaLabel;
	private JButton cercarButton;
	private JLabel separacioLabel;
	private JPanel llistaTablePanel;
	private JScrollPane llistatLlibresScrollPane;
	private JPanel accionsButtonsPanel;
	private JButton mostrarLlibreButton;
	private JButton prestecsLlibreButton;
	private JButton llogarLlibreButton;
	private JButton cancellarButton;
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
	private JTextField autorsField;
	private JPanel generesPanel;
	private JLabel generesLabel;
	private JTextField genereField;
	private JPanel editorialsPanel;
	private JLabel editorialsLabel;
	private JTextField editorialField;
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
	private FlowLayout fl_sinopsisPanel;
	private JLabel sinopsisLabel;
	private JTextArea sinopsisTextArea;
	private JScrollPane sinopsisScrollPane;

	private JPanel paginadorPanel;

	private FlowLayout fl_paginadorPanel;

	private JLabel anteriorLabel;

	private JLabel anteriorIconLabel;

	private JTextField rowActualField;

	private JLabel ofLabel;

	private JLabel seguentIconLabel;

	private JTextField rowTotalsField;

	private JLabel seguentLabel;

	private JLabel esborrarLabel;

	private JPanel edicioPanel;

	private JLabel edicioLabel;

	private JTextField edicioField;
	private JButton reservarButton;

	/**
	 * Create the panel.
	 */
	public ConsultaLlibres(Usuari usuariConnectat) {
		this.usuariConnectat = usuariConnectat;
		icons = new Icons();
		setBounds(new Rectangle(0, 0, 750, 850));
		setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

		iniciarComponents();
		llistarLlibres();
	}

	private void iniciarComponents() {
		rowActiu = 0;
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

		filtrePerLabel = new JLabel(CentralPanelMessages.getString("ConsultaLlibres.filtrePerLabel.text"));
		filtrePerLabel.setToolTipText(CentralPanelMessages.getString("ConsultaLlibres.filtrePerLabel.toolTipText")); //$NON-NLS-1$
		filtrePerLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		filtreButtonPanel.add(filtrePerLabel);

		titolRadioButton = new JRadioButton(CentralPanelMessages.getString("ConsultaLlibres.titolRadioButton.text"));
		titolRadioButton.setSelected(true);
		filtreButtonGroup.add(titolRadioButton);
		titolRadioButton.setToolTipText(CentralPanelMessages.getString("ConsultaLlibres.titolRadioButton.toolTipText")); //$NON-NLS-1$
		filtreButtonPanel.add(titolRadioButton);

		autorRadioButton = new JRadioButton(CentralPanelMessages.getString("ConsultaLlibres.autorRadioButton.text"));
		filtreButtonGroup.add(autorRadioButton);
		autorRadioButton.setToolTipText(CentralPanelMessages.getString("ConsultaLlibres.autorRadioButton.toolTipText")); //$NON-NLS-1$
		filtreButtonPanel.add(autorRadioButton);

		genereRadioButton = new JRadioButton(CentralPanelMessages.getString("ConsultaLlibres.genereRadioButton.text"));
		filtreButtonGroup.add(genereRadioButton);
		genereRadioButton
				.setToolTipText(CentralPanelMessages.getString("ConsultaLlibres.genereRadioButton.toolTipText")); //$NON-NLS-1$
		filtreButtonPanel.add(genereRadioButton);

		editorialRadioButton = new JRadioButton(
				CentralPanelMessages.getString("ConsultaLlibres.editorialRadioButton.text"));
		filtreButtonGroup.add(editorialRadioButton);
		editorialRadioButton
				.setToolTipText(CentralPanelMessages.getString("ConsultaLlibres.editorialRadioButton.toolTipText")); //$NON-NLS-1$
		filtreButtonPanel.add(editorialRadioButton);

		filtreTextPanel = new JPanel();
		fl_filtreTextPanel = (FlowLayout) filtreTextPanel.getLayout();
		fl_filtreTextPanel.setAlignment(FlowLayout.RIGHT);
		filtresPanel.add(filtreTextPanel, BorderLayout.CENTER);

		lupaLabel = new JLabel("");
		lupaLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lupaLabel.setToolTipText(CentralPanelMessages.getString("ConsultaLlibresNoRegistrat.lupaLabel.toolTipText")); //$NON-NLS-1$
		lupaLabel.setIcon(icons.getLupaIcon());
		lupaLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lupaLabel.addMouseListener(new ConsultaLlibresControlador(this, usuariConnectat));
		filtreTextPanel.add(lupaLabel);

		cercaField = new JTextField();
		cercaField.setToolTipText(CentralPanelMessages.getString("ConsultaLlibresNoRegistrat.cercaField.toolTipText")); //$NON-NLS-1$
		filtreTextPanel.add(cercaField);
		cercaField.setColumns(20);

		esborrarLabel = new JLabel("");
		esborrarLabel
				.setToolTipText(CentralPanelMessages.getString("ConsultaLlibresNoRegistrat.esborrarLabel.toolTipText")); //$NON-NLS-1$
		esborrarLabel.setIcon(icons.getCancelIcon());
		esborrarLabel.setHorizontalAlignment(SwingConstants.CENTER);
		esborrarLabel.addMouseListener(new ConsultaLlibresControlador(this, usuariConnectat));
		filtreTextPanel.add(esborrarLabel);

		separacioLabel = new JLabel("   ");
		filtreTextPanel.add(separacioLabel);

		cercarButton = new JButton(CentralPanelMessages.getString("ConsultaLlibresNoRegistrat.cercarButton.text"));
		cercarButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		cercarButton.setForeground(Color.BLACK);
		cercarButton.setBackground(new Color(173, 216, 230));
		cercarButton
				.setToolTipText(CentralPanelMessages.getString("ConsultaLlibresNoRegistrat.cercarButton.toolTipText")); //$NON-NLS-1$
		cercarButton.addMouseListener(new ConsultaLlibresControlador(this, usuariConnectat));
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
		mostrarLlibreButton.addMouseListener(new ConsultaLlibresControlador(this, usuariConnectat));
		accionsButtonsPanel.add(mostrarLlibreButton);

		dadesLlibrePanel = new JPanel();
		llibresPanel.add(dadesLlibrePanel, BorderLayout.SOUTH);
		dadesLlibrePanel.setLayout(new BorderLayout(0, 0));

		dadesLlibreGridPanel = new JPanel();
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
		GridBagConstraints gbc_paginadorPanel = new GridBagConstraints();
		gbc_paginadorPanel.fill = GridBagConstraints.BOTH;
		gbc_paginadorPanel.insets = new Insets(0, 0, 5, 0);
		gbc_paginadorPanel.gridx = 0;
		gbc_paginadorPanel.gridy = 0;
		paginadorPanel.setVisible(false);
		dadesLlibreGridPanel.add(paginadorPanel, gbc_paginadorPanel);

		anteriorLabel = new JLabel(CentralPanelMessages.getString("ConsultaLlibresNoRegistrat.lblNewLabel.text")); //$NON-NLS-1$
		paginadorPanel.add(anteriorLabel);

		anteriorIconLabel = new JLabel("");
		anteriorIconLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		anteriorIconLabel.setIcon(icons.getAnteriorIcon());
		anteriorIconLabel
				.setToolTipText(CentralPanelMessages.getString("ConsultaLlibresNoRegistrat.anteriorLabel.toolTipText")); //$NON-NLS-1$
		anteriorIconLabel.addMouseListener(new ConsultaLlibresControlador(this, usuariConnectat));
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
		seguentIconLabel.setIcon(icons.getSeguentIcon());
		seguentIconLabel
				.setToolTipText(CentralPanelMessages.getString("ConsultaLlibresNoRegistrat.seguentLabel.toolTipText")); //$NON-NLS-1$
		seguentIconLabel.addMouseListener(new ConsultaLlibresControlador(this, usuariConnectat));

		rowTotalsField = new JTextField();
		rowTotalsField.setEditable(false);
		rowTotalsField.setText("");
		paginadorPanel.add(rowTotalsField);
		rowTotalsField.setColumns(2);
		paginadorPanel.add(seguentIconLabel);

		seguentLabel = new JLabel(CentralPanelMessages.getString("ConsultaLlibresNoRegistrat.lblNewLabel_1.text")); //$NON-NLS-1$
		paginadorPanel.add(seguentLabel);

		prestecsLlibreButton = new JButton(CentralPanelMessages.getString("ConsultaLlibres.prestecsLlibreButton.text"));
		prestecsLlibreButton.setForeground(Color.WHITE);
		prestecsLlibreButton.setBackground(Color.decode("#6a1b9a"));
		prestecsLlibreButton
				.setToolTipText(CentralPanelMessages.getString("ConsultaLlibres.prestecsLlibreButton.toolTipText")); //$NON-NLS-1$
		prestecsLlibreButton.addMouseListener(new ConsultaLlibresControlador(this, usuariConnectat));
		accionsButtonsPanel.add(prestecsLlibreButton);

		llogarLlibreButton = new JButton(CentralPanelMessages.getString("ConsultaLlibres.llogarLlibreButton.text"));
		llogarLlibreButton.setForeground(new Color(255, 255, 255));
		llogarLlibreButton.setBackground(new Color(222, 184, 135));
		llogarLlibreButton
				.setToolTipText(CentralPanelMessages.getString("ConsultaLlibres.llogarLlibreButton.toolTipText")); //$NON-NLS-1$
		llogarLlibreButton.addMouseListener(new ConsultaLlibresControlador(this, usuariConnectat));
		accionsButtonsPanel.add(llogarLlibreButton);
		
		reservarButton = new JButton(CentralPanelMessages.getString("ConsultaLlibres.btnNewButton.text"));
		reservarButton.setForeground(new Color(255, 255, 255));
		reservarButton.setBackground(new Color(0, 100, 0));
		reservarButton.addMouseListener(new ConsultaLlibresControlador(this, usuariConnectat));
		accionsButtonsPanel.add(reservarButton);

		JLabel lblNewLabel_2 = new JLabel(" ");
		accionsButtonsPanel.add(lblNewLabel_2);

		JLabel lblNewLabel_1 = new JLabel(" ");
		accionsButtonsPanel.add(lblNewLabel_1);

		JLabel lblNewLabel = new JLabel(" ");
		accionsButtonsPanel.add(lblNewLabel);

		cancellarButton = new JButton(CentralPanelMessages.getString("ConsultaLlibres.cancellarButton.text"));
		cancellarButton.setVisible(false);
		cancellarButton.setEnabled(false);
		cancellarButton.setBackground(new Color(165, 42, 42));
		cancellarButton.setForeground(Color.WHITE);
		cancellarButton.setToolTipText(CentralPanelMessages.getString("ConsultaLlibres.cancellarButton.toolTipText"));
		cancellarButton.addMouseListener(new ConsultaLlibresControlador(this, usuariConnectat));
		accionsButtonsPanel.add(cancellarButton);

		dadesLlibrePanel = new JPanel();
		llibresPanel.add(dadesLlibrePanel, BorderLayout.SOUTH);
		dadesLlibrePanel.setLayout(new BorderLayout(0, 0));

		dadesLlibreGridPanel = new JPanel();
		dadesLlibreGridPanel.setBorder(new TitledBorder(null, "Dades llibre", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		dadesLlibrePanel.add(dadesLlibreGridPanel, BorderLayout.NORTH);
		GridBagLayout gbl_dadesLlibreGridPanel1 = new GridBagLayout();
		gbl_dadesLlibreGridPanel1.columnWidths = new int[] { 619, 0 };
		gbl_dadesLlibreGridPanel1.rowHeights = new int[] { 22, 22, 22, 22, 22, 0 };
		gbl_dadesLlibreGridPanel1.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gbl_dadesLlibreGridPanel1.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		dadesLlibreGridPanel.setLayout(gbl_dadesLlibreGridPanel1);

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

		anteriorLabel = new JLabel(CentralPanelMessages.getString("ConsultaLlibres.anteriorLabel")); //$NON-NLS-1$
		paginadorPanel.add(anteriorLabel);

		anteriorIconLabel = new JLabel("");
		anteriorIconLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		anteriorIconLabel.setIcon(icons.getAnteriorIcon());
		anteriorIconLabel
				.setToolTipText(CentralPanelMessages.getString("ConsultaLlibresNoRegistrat.anteriorLabel.toolTipText")); //$NON-NLS-1$
		anteriorIconLabel.addMouseListener(new ConsultaLlibresControlador(this, usuariConnectat));
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
		seguentIconLabel.setIcon(icons.getSeguentIcon());
		seguentIconLabel
				.setToolTipText(CentralPanelMessages.getString("ConsultaLlibresNoRegistrat.seguentLabel.toolTipText")); //$NON-NLS-1$
		seguentIconLabel.addMouseListener(new ConsultaLlibresControlador(this, usuariConnectat));

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

		idLabel = new JLabel(CentralPanelMessages.getString("ConsultaLlibres.idLabel.text"));
		idPanel.add(idLabel);

		idLlibreField = new JTextField();
		idLlibreField.setEditable(false);
		idLlibreField.setToolTipText(CentralPanelMessages.getString("ConsultaLlibres.idLlibreField.toolTipText")); //$NON-NLS-1$
		idPanel.add(idLlibreField);
		idLlibreField.setColumns(3);

		titolPanel = new JPanel();
		idTitolDisponiblePanel.add(titolPanel);

		titolLabel = new JLabel(CentralPanelMessages.getString("ConsultaLlibres.titolLabel.text"));
		titolPanel.add(titolLabel);

		titolField = new JTextField();
		titolField.setEditable(false);
		titolField.setToolTipText(CentralPanelMessages.getString("ConsultaLlibres.titolField.toolTipText")); //$NON-NLS-1$
		titolPanel.add(titolField);
		titolField.setColumns(40);

		disponiblePanel = new JPanel();
		idTitolDisponiblePanel.add(disponiblePanel);

		reservatLabel = new JLabel(CentralPanelMessages.getString("ConsultaLlibres.reservatLabel.text"));
		reservatLabel.setToolTipText(CentralPanelMessages.getString("ConsultaLlibres.reservatLabel.toolTipText")); //$NON-NLS-1$
		disponiblePanel.add(reservatLabel);

		reservatCheckBox = new JCheckBox("");
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

		autorLabel = new JLabel(CentralPanelMessages.getString("ConsultaLlibres.autorLabel.text"));
		autorsPanel.add(autorLabel);
		autorLabel.setHorizontalAlignment(SwingConstants.RIGHT);

		autorsField = new JTextField();
		autorsField.setEditable(false);
		autorsField.setColumns(12);
		autorsField.setToolTipText(CentralPanelMessages.getString("ConsultaLlibres.autorsField.toolTipText")); //$NON-NLS-1$
		autorsPanel.add(autorsField);

		generesPanel = new JPanel();
		autorGenereEditorialPuntuacioPanel.add(generesPanel);

		generesLabel = new JLabel(CentralPanelMessages.getString("ConsultaLlibres.generesLabel.text"));
		generesPanel.add(generesLabel);
		generesLabel.setHorizontalAlignment(SwingConstants.RIGHT);

		genereField = new JTextField();
		genereField.setEditable(false);
		genereField.setColumns(12);
		genereField.setToolTipText(CentralPanelMessages.getString("ConsultaLlibres.genereField.toolTipText")); //$NON-NLS-1$
		generesPanel.add(genereField);

		editorialsPanel = new JPanel();
		autorGenereEditorialPuntuacioPanel.add(editorialsPanel);

		editorialsLabel = new JLabel(CentralPanelMessages.getString("ConsultaLlibres.editorialsLabel.text"));
		editorialsPanel.add(editorialsLabel);
		editorialsLabel.setHorizontalAlignment(SwingConstants.RIGHT);

		editorialField = new JTextField();
		editorialField.setEditable(false);
		editorialField.setColumns(12);
		editorialField.setToolTipText(CentralPanelMessages.getString("ConsultaLlibres.editorialField.toolTipText")); //$NON-NLS-1$
		editorialsPanel.add(editorialField);

		puntuacioPanel = new JPanel();
		autorGenereEditorialPuntuacioPanel.add(puntuacioPanel);

		puntuacioLabel = new JLabel(CentralPanelMessages.getString("ConsultaLlibres.puntuacioLabel.text"));
		puntuacioPanel.add(puntuacioLabel);

		puntuacioField = new JTextField();
		puntuacioField.setEditable(false);
		puntuacioField.setToolTipText(CentralPanelMessages.getString("ConsultaLlibres.puntuacioField.toolTipText")); //$NON-NLS-1$
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

		isbnLabel = new JLabel(CentralPanelMessages.getString("ConsultaLlibres.isbnLabel.text"));
		isbnPanel.add(isbnLabel);

		isbnField = new JTextField();
		isbnField.setEditable(false);
		isbnField.setToolTipText(CentralPanelMessages.getString("ConsultaLlibres.isbnField.toolTipText")); //$NON-NLS-1$
		isbnPanel.add(isbnField);
		isbnField.setColumns(8);

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

		dataPublicacioLabel = new JLabel(CentralPanelMessages.getString("ConsultaLlibres.dataPublicacioLabel.text"));
		dataPublicacioPanel.add(dataPublicacioLabel);

		dataPublicacioField = new JTextField();
		dataPublicacioField.setEditable(false);
		dataPublicacioField
				.setToolTipText(CentralPanelMessages.getString("ConsultaLlibres.dataPublicacioField.toolTipText")); //$NON-NLS-1$
		dataPublicacioPanel.add(dataPublicacioField);
		dataPublicacioField.setColumns(6);

		numeroPaginesPanel = new JPanel();
		isbnDataPaginesReservesPanel.add(numeroPaginesPanel);

		numeroPaginesLabel = new JLabel(CentralPanelMessages.getString("ConsultaLlibres.numeroPaginesLabel.text"));
		numeroPaginesPanel.add(numeroPaginesLabel);

		numeroPaginesField = new JTextField();
		numeroPaginesField.setEditable(false);
		numeroPaginesField
				.setToolTipText(CentralPanelMessages.getString("ConsultaLlibres.numeroPaginesField.toolTipText")); //$NON-NLS-1$
		numeroPaginesPanel.add(numeroPaginesField);
		numeroPaginesField.setColumns(3);

		numeroReservesPanel = new JPanel();
		isbnDataPaginesReservesPanel.add(numeroReservesPanel);

		numeroReservesLabel = new JLabel(CentralPanelMessages.getString("ConsultaLlibres.numeroReservesLabel.text"));
		numeroReservesPanel.add(numeroReservesLabel);

		numeroReservesField = new JTextField();
		numeroReservesField.setEditable(false);
		numeroReservesField
				.setToolTipText(CentralPanelMessages.getString("ConsultaLlibres.numeroReservesField.toolTipText")); //$NON-NLS-1$
		numeroReservesPanel.add(numeroReservesField);
		numeroReservesField.setColumns(2);

		sinopsisPanel = new JPanel();
		fl_sinopsisPanel = (FlowLayout) sinopsisPanel.getLayout();
		fl_sinopsisPanel.setHgap(10);
		fl_sinopsisPanel.setVgap(2);
		fl_sinopsisPanel.setAlignment(FlowLayout.LEADING);
		GridBagConstraints gbc_sinopsisPanel = new GridBagConstraints();
		gbc_sinopsisPanel.anchor = GridBagConstraints.NORTH;
		gbc_sinopsisPanel.fill = GridBagConstraints.HORIZONTAL;
		gbc_sinopsisPanel.gridx = 0;
		gbc_sinopsisPanel.gridy = 4;
		dadesLlibreGridPanel.add(sinopsisPanel, gbc_sinopsisPanel);

		sinopsisLabel = new JLabel(CentralPanelMessages.getString("ConsultaLlibres.sinopsisLabel.text"));
		sinopsisLabel.setVerticalAlignment(SwingConstants.TOP);
		sinopsisPanel.add(sinopsisLabel);

		sinopsisTextArea = new JTextArea();
		sinopsisTextArea.setEditable(false);
		sinopsisTextArea.setToolTipText(CentralPanelMessages.getString("ConsultaLlibres.sinopsisTextArea.toolTipText")); //$NON-NLS-1$

		sinopsisTextArea.setColumns(60);
		sinopsisTextArea.setLineWrap(true);
		sinopsisTextArea.setWrapStyleWord(true);
		sinopsisTextArea.setText("");
		sinopsisTextArea.setRows(4);

		sinopsisScrollPane = new JScrollPane(sinopsisTextArea);

		sinopsisPanel.add(sinopsisScrollPane);

	}

	private void llistarLlibres() {
		llibresModel = new LlibresModel();
		try {
			llibresTable.setModel(llibresModel.consultarTotsElsLlibres());
		} catch (Exception e) {
			e.printStackTrace();
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

	public JTextField getAutorsField() {
		return autorsField;
	}

	public void setAutorsField(JTextField autorsField) {
		this.autorsField = autorsField;
	}

	public JTextField getGenereField() {
		return genereField;
	}

	public void setGenereField(JTextField genereField) {
		this.genereField = genereField;
	}

	public JTextField getEditorialField() {
		return editorialField;
	}

	public void setEditorialField(JTextField editorialField) {
		this.editorialField = editorialField;
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

	public int getRowActiu() {
		return rowActiu;
	}

	public JTextField getRowActualField() {
		return rowActualField;
	}

	public JTextField getRowTotalsField() {
		return rowTotalsField;
	}

	public Usuari getUsuariConnectat() {
		return usuariConnectat;
	}

	public void setUsuariConnectat(Usuari usuariConnectat) {
		this.usuariConnectat = usuariConnectat;
	}
	
	public JButton getReservarButton() {
		return reservarButton;
	}
	
	public JButton getLlogarLlibreButton() {
		return llogarLlibreButton;
	}
	
	public JButton getPrestecsLlibreButton() {
		return prestecsLlibreButton;
	}
	
	public JButton getCancellarButton() {
		return cancellarButton;
	}
}
