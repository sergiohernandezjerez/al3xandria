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
import javax.swing.table.DefaultTableModel;
import al3xandria.vista.icons.Icons;

import javax.swing.border.EtchedBorder;
import javax.swing.JTextArea;
import java.awt.ComponentOrientation;
import javax.swing.ButtonGroup;



public class ConsultaLlibresNoRegistrat extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField cercaField;
	private JTable llibresTable;
	private JTextField idLlibreField;
	private JTextField titolField;
	private JTextField numeroPaginesField;
	private JTextField numeroReservesField;
	private JTextField puntuacioField;
	private JTextField isbnField;
	private JTextField dataPublicacioField;
	
	private Icons icons;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Create the panel.
	 */
	public ConsultaLlibresNoRegistrat() {
		icons = new Icons();
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
		
		JLabel filtrePerLabel = new JLabel(CentralPanelMessages.getString("ConsultaLlibresNoRegistrat.filtrePerLabel.text")); //$NON-NLS-1$
		filtrePerLabel.setToolTipText(CentralPanelMessages.getString("ConsultaLlibresNoRegistrat.filtrePerLabel.toolTipText")); //$NON-NLS-1$
		filtrePerLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		filtreButtonPanel.add(filtrePerLabel);
		
		JRadioButton titolRadioButton = new JRadioButton(CentralPanelMessages.getString("ConsultaLlibresNoRegistrat.titolRadioButton.text")); //$NON-NLS-1$
		titolRadioButton.setSelected(true);
		buttonGroup.add(titolRadioButton);
		titolRadioButton.setToolTipText(CentralPanelMessages.getString("ConsultaLlibresNoRegistrat.titolRadioButton.toolTipText")); //$NON-NLS-1$
		filtreButtonPanel.add(titolRadioButton);
		
		JRadioButton autorRadioButton = new JRadioButton(CentralPanelMessages.getString("ConsultaLlibresNoRegistrat.autorRadioButton.text")); //$NON-NLS-1$
		buttonGroup.add(autorRadioButton);
		autorRadioButton.setToolTipText(CentralPanelMessages.getString("ConsultaLlibresNoRegistrat.autorRadioButton.toolTipText")); //$NON-NLS-1$
		filtreButtonPanel.add(autorRadioButton);
		
		JRadioButton genereRadioButton = new JRadioButton(CentralPanelMessages.getString("ConsultaLlibresNoRegistrat.genereRadioButton.text")); //$NON-NLS-1$
		buttonGroup.add(genereRadioButton);
		genereRadioButton.setToolTipText(CentralPanelMessages.getString("ConsultaLlibresNoRegistrat.genereRadioButton.toolTipText")); //$NON-NLS-1$
		filtreButtonPanel.add(genereRadioButton);
		
		JRadioButton editorialRadioButton = new JRadioButton(CentralPanelMessages.getString("ConsultaLlibresNoRegistrat.editorialRadioButton.text")); //$NON-NLS-1$
		buttonGroup.add(editorialRadioButton);
		editorialRadioButton.setToolTipText(CentralPanelMessages.getString("ConsultaLlibresNoRegistrat.editorialRadioButton.toolTipText")); //$NON-NLS-1$
		filtreButtonPanel.add(editorialRadioButton);
		
		JPanel filtreTextPanel = new JPanel();
		FlowLayout fl_filtreTextPanel = (FlowLayout) filtreTextPanel.getLayout();
		fl_filtreTextPanel.setAlignment(FlowLayout.RIGHT);
		filtresPanel.add(filtreTextPanel, BorderLayout.CENTER);
		
		JLabel lupaLabel = new JLabel("");
		lupaLabel.setIcon(icons.getLupaIcon());
		lupaLabel.setHorizontalAlignment(SwingConstants.CENTER);
		filtreTextPanel.add(lupaLabel);
		
		cercaField = new JTextField();
		cercaField.setToolTipText(CentralPanelMessages.getString("ConsultaLlibresNoRegistrat.cercaField.toolTipText")); //$NON-NLS-1$
		filtreTextPanel.add(cercaField);
		cercaField.setColumns(20);
		
		JLabel separacioLabel = new JLabel("   ");
		filtreTextPanel.add(separacioLabel);
		
		JButton cercarButton = new JButton(CentralPanelMessages.getString("ConsultaLlibresNoRegistrat.cercarButton.text")); //$NON-NLS-1$
		cercarButton.setForeground(Color.BLACK);
		cercarButton.setBackground(new Color(173, 216, 230));
		cercarButton.setToolTipText(CentralPanelMessages.getString("ConsultaLlibresNoRegistrat.cercarButton.toolTipText")); //$NON-NLS-1$
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
							"002", "20000 Leguas", "Perez Reverte", "9879879879879", "Aventura", "Anagrama", "12/05/1789"
						},
						{
							"002", "20000 Leguas", "Perez Reverte", "9879879879879", "Aventura", "Anagrama", "12/05/1789"
						},
						{
							"002", "20000 Leguas", "Perez Reverte", "9879879879879", "Aventura", "Anagrama", "12/05/1789"
						},
						{
							"002", "20000 Leguas", "Perez Reverte", "9879879879879", "Aventura", "Anagrama", "12/05/1789"
						},
						{
							"002", "20000 Leguas", "Perez Reverte", "9879879879879", "Aventura", "Anagrama", "12/05/1789"
						},
						{
							"002", "20000 Leguas", "Perez Reverte", "9879879879879", "Aventura", "Anagrama", "12/05/1789"
						},
						{
							"002", "20000 Leguas", "Perez Reverte", "9879879879879", "Aventura", "Anagrama", "12/05/1789"
						},
						{
							"002", "20000 Leguas", "Perez Reverte", "9879879879879", "Aventura", "Anagrama", "12/05/1789"
						},
						{
							"002", "20000 Leguas", "Perez Reverte", "9879879879879", "Aventura", "Anagrama", "12/05/1789"
						},
						{
							"002", "20000 Leguas", "Perez Reverte", "9879879879879", "Aventura", "Anagrama", "12/05/1789"
						},
						{
							"002", "20000 Leguas", "Perez Reverte", "9879879879879", "Aventura", "Anagrama", "12/05/1789"
						},
						{
							"002", "20000 Leguas", "Perez Reverte", "9879879879879", "Aventura", "Anagrama", "12/05/1789"
						},
						{
							"002", "20000 Leguas", "Perez Reverte", "9879879879879", "Aventura", "Anagrama", "12/05/1789"
						},
						{
							"002", "20000 Leguas", "Perez Reverte", "9879879879879", "Aventura", "Anagrama", "12/05/1789"
						},
						{
							"002", "20000 Leguas", "Perez Reverte", "9879879879879", "Aventura", "Anagrama", "12/05/1789"
						},
						{
							"002", "20000 Leguas", "Perez Reverte", "9879879879879", "Aventura", "Anagrama", "12/05/1789"
						}
		            },
		            new String [] {"id", "Titol", "Autor", "isbn", "Genere", "Editorial", "Data"}
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
		//llistatLlibresScrollPane.setViewportView(llibresTable);
		llistaTablePanel.add(llistatLlibresScrollPane, BorderLayout.CENTER);
		
		
		JPanel accionsButtonsPanel = new JPanel();
		accionsButtonsPanel.setBackground(Color.WHITE);
		llistaTablePanel.add(accionsButtonsPanel, BorderLayout.EAST);
		accionsButtonsPanel.setLayout(new GridLayout(8, 0, 0, 0));
		
		JButton mostrarLlibreButton = new JButton(CentralPanelMessages.getString("ConsultaLlibresNoRegistrat.mostrarLlibreButton.text")); //$NON-NLS-1$
		mostrarLlibreButton.setForeground(Color.WHITE);
		mostrarLlibreButton.setBackground(Color.decode("#00838f"));
		mostrarLlibreButton.setToolTipText(CentralPanelMessages.getString("ConsultaLlibresNoRegistrat.mostrarLlibreButton.toolTipText")); //$NON-NLS-1$
		accionsButtonsPanel.add(mostrarLlibreButton);
		
		JPanel dadesLlibrePanel = new JPanel();
		llibresPanel.add(dadesLlibrePanel, BorderLayout.SOUTH);
		dadesLlibrePanel.setLayout(new BorderLayout(0, 0));
		
		JPanel dadesLlibreGridPanel = new JPanel();
		dadesLlibreGridPanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Dades del llibre", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
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
		
		JPanel idTitolDisponiblePanel = new JPanel();
		GridBagConstraints gbc_idTitolDisponiblePanel = new GridBagConstraints();
		gbc_idTitolDisponiblePanel.fill = GridBagConstraints.BOTH;
		gbc_idTitolDisponiblePanel.insets = new Insets(0, 0, 5, 0);
		gbc_idTitolDisponiblePanel.gridx = 0;
		gbc_idTitolDisponiblePanel.gridy = 1;
		dadesLlibreGridPanel.add(idTitolDisponiblePanel, gbc_idTitolDisponiblePanel);
		idTitolDisponiblePanel.setLayout(new FlowLayout(FlowLayout.LEADING, 10, 2));
		
		JPanel idPanel = new JPanel();
		idTitolDisponiblePanel.add(idPanel);
		
		JLabel idLabel = new JLabel(CentralPanelMessages.getString("ConsultaLlibresNoRegistrat.idLabel.text")); //$NON-NLS-1$
		idPanel.add(idLabel);
		
		idLlibreField = new JTextField();
		idLlibreField.setToolTipText(CentralPanelMessages.getString("ConsultaLlibresNoRegistrat.idLlibreField.toolTipText")); //$NON-NLS-1$
		idPanel.add(idLlibreField);
		idLlibreField.setColumns(3);
		
		JPanel titolPanel = new JPanel();
		idTitolDisponiblePanel.add(titolPanel);
		
		JLabel titolLabel = new JLabel(CentralPanelMessages.getString("ConsultaLlibresNoRegistrat.titolLabel.text")); //$NON-NLS-1$
		titolPanel.add(titolLabel);
		
		titolField = new JTextField();
		titolField.setToolTipText(CentralPanelMessages.getString("ConsultaLlibresNoRegistrat.titolField.toolTipText")); //$NON-NLS-1$
		titolPanel.add(titolField);
		titolField.setColumns(40);
		
		JPanel disponiblePanel = new JPanel();
		idTitolDisponiblePanel.add(disponiblePanel);
		
		JLabel reservatLabel = new JLabel(CentralPanelMessages.getString("ConsultaLlibresNoRegistrat.reservatLabel.text")); //$NON-NLS-1$
		reservatLabel.setToolTipText(CentralPanelMessages.getString("ConsultaLlibresNoRegistrat.reservatLabel.toolTipText")); //$NON-NLS-1$
		disponiblePanel.add(reservatLabel);
		
		JCheckBox reservatCheckBox = new JCheckBox("");
		disponiblePanel.add(reservatCheckBox);
		
		JPanel autorGenereEditorialPuntuacioPanel = new JPanel();
		GridBagConstraints gbc_autorGenereEditorialPuntuacioPanel = new GridBagConstraints();
		gbc_autorGenereEditorialPuntuacioPanel.fill = GridBagConstraints.BOTH;
		gbc_autorGenereEditorialPuntuacioPanel.insets = new Insets(0, 0, 5, 0);
		gbc_autorGenereEditorialPuntuacioPanel.gridx = 0;
		gbc_autorGenereEditorialPuntuacioPanel.gridy = 2;
		dadesLlibreGridPanel.add(autorGenereEditorialPuntuacioPanel, gbc_autorGenereEditorialPuntuacioPanel);
		autorGenereEditorialPuntuacioPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 10, 2));
		
		JPanel autorsPanel = new JPanel();
		autorGenereEditorialPuntuacioPanel.add(autorsPanel);
		
		JLabel autorLabel = new JLabel(CentralPanelMessages.getString("ConsultaLlibresNoRegistrat.autorLabel.text")); //$NON-NLS-1$
		autorsPanel.add(autorLabel);
		autorLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JTextField autorsField = new JTextField();
		autorsField.setColumns(12);
		autorsField.setToolTipText(CentralPanelMessages.getString("ConsultaLlibresNoRegistrat.autorsField.toolTipText")); //$NON-NLS-1$
		autorsPanel.add(autorsField);
		
		JPanel generesPanel = new JPanel();
		autorGenereEditorialPuntuacioPanel.add(generesPanel);
		
		JLabel generesLabel = new JLabel(CentralPanelMessages.getString("ConsultaLlibresNoRegistrat.generesLabel.text")); //$NON-NLS-1$
		generesPanel.add(generesLabel);
		generesLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JTextField genereField = new JTextField();
		genereField.setColumns(12);
		genereField.setToolTipText(CentralPanelMessages.getString("ConsultaLlibresNoRegistrat.genereField.toolTipText")); //$NON-NLS-1$
		generesPanel.add(genereField);
		
		JPanel editorialsPanel = new JPanel();
		autorGenereEditorialPuntuacioPanel.add(editorialsPanel);
		
		JLabel editorialsLabel = new JLabel(CentralPanelMessages.getString("ConsultaLlibresNoRegistrat.editorialsLabel.text")); //$NON-NLS-1$
		editorialsPanel.add(editorialsLabel);
		editorialsLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JTextField editorialField = new JTextField();
		editorialField.setColumns(12);
		editorialField.setToolTipText(CentralPanelMessages.getString("ConsultaLlibresNoRegistrat.editorialField.toolTipText")); //$NON-NLS-1$
		editorialsPanel.add(editorialField);
		
		JPanel puntuacioPanel = new JPanel();
		autorGenereEditorialPuntuacioPanel.add(puntuacioPanel);
		
		JLabel puntuacioLabel = new JLabel(CentralPanelMessages.getString("ConsultaLlibresNoRegistrat.puntuacioLabel.text")); //$NON-NLS-1$
		puntuacioPanel.add(puntuacioLabel);
		
		puntuacioField = new JTextField();
		puntuacioField.setToolTipText(CentralPanelMessages.getString("ConsultaLlibresNoRegistrat.puntuacioField.toolTipText")); //$NON-NLS-1$
		puntuacioPanel.add(puntuacioField);
		puntuacioField.setColumns(4);
		
		
		
		JPanel isbnDataPaginesReservesPanel = new JPanel();
		FlowLayout fl_isbnDataPaginesReservesPanel = (FlowLayout) isbnDataPaginesReservesPanel.getLayout();
		fl_isbnDataPaginesReservesPanel.setHgap(10);
		fl_isbnDataPaginesReservesPanel.setVgap(2);
		fl_isbnDataPaginesReservesPanel.setAlignment(FlowLayout.LEADING);
		GridBagConstraints gbc_isbnDataPaginesReservesPanel = new GridBagConstraints();
		gbc_isbnDataPaginesReservesPanel.fill = GridBagConstraints.BOTH;
		gbc_isbnDataPaginesReservesPanel.insets = new Insets(0, 0, 5, 0);
		gbc_isbnDataPaginesReservesPanel.gridx = 0;
		gbc_isbnDataPaginesReservesPanel.gridy = 3;
		dadesLlibreGridPanel.add(isbnDataPaginesReservesPanel, gbc_isbnDataPaginesReservesPanel);
		
		JPanel isbnPanel = new JPanel();
		isbnDataPaginesReservesPanel.add(isbnPanel);
		
		JLabel isbnLabel = new JLabel(CentralPanelMessages.getString("ConsultaLlibresNoRegistrat.isbnLabel.text")); //$NON-NLS-1$
		isbnPanel.add(isbnLabel);
		
		isbnField = new JTextField();
		isbnField.setToolTipText(CentralPanelMessages.getString("ConsultaLlibresNoRegistrat.isbnField.toolTipText")); //$NON-NLS-1$
		isbnPanel.add(isbnField);
		isbnField.setColumns(10);
		
		JPanel dataPublicacioPanel = new JPanel();
		isbnDataPaginesReservesPanel.add(dataPublicacioPanel);
		
		JLabel dataPublicacioLabel = new JLabel(CentralPanelMessages.getString("ConsultaLlibresNoRegistrat.dataPublicacioLabel.text")); //$NON-NLS-1$
		dataPublicacioPanel.add(dataPublicacioLabel);
		
		dataPublicacioField = new JTextField();
		dataPublicacioField.setToolTipText(CentralPanelMessages.getString("ConsultaLlibresNoRegistrat.dataPublicacioField.toolTipText")); //$NON-NLS-1$
		dataPublicacioPanel.add(dataPublicacioField);
		dataPublicacioField.setColumns(10);
		
		JPanel numeroPaginesPanel = new JPanel();
		isbnDataPaginesReservesPanel.add(numeroPaginesPanel);
		
		JLabel numeroPaginesLabel = new JLabel(CentralPanelMessages.getString("ConsultaLlibresNoRegistrat.numeroPaginesLabel.text")); //$NON-NLS-1$
		numeroPaginesPanel.add(numeroPaginesLabel);
		
		numeroPaginesField = new JTextField();
		numeroPaginesField.setToolTipText(CentralPanelMessages.getString("ConsultaLlibresNoRegistrat.numeroPaginesField.toolTipText")); //$NON-NLS-1$
		numeroPaginesPanel.add(numeroPaginesField);
		numeroPaginesField.setColumns(4);
		
		JPanel numeroReservesPanel = new JPanel();
		isbnDataPaginesReservesPanel.add(numeroReservesPanel);
		
		JLabel numeroReservesLabel = new JLabel(CentralPanelMessages.getString("ConsultaLlibresNoRegistrat.numeroReservesLabel.text")); //$NON-NLS-1$
		numeroReservesPanel.add(numeroReservesLabel);
		
		numeroReservesField = new JTextField();
		numeroReservesField.setToolTipText(CentralPanelMessages.getString("ConsultaLlibresNoRegistrat.numeroReservesField.toolTipText")); //$NON-NLS-1$
		numeroReservesPanel.add(numeroReservesField);
		numeroReservesField.setColumns(4);
		
		JPanel sinopsisPanel = new JPanel();
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
		
		JLabel sinopsisLabel = new JLabel(CentralPanelMessages.getString("ConsultaLlibresNoRegistrat.sinopsisLabel.text")); //$NON-NLS-1$
		sinopsisLabel.setVerticalAlignment(SwingConstants.TOP);
		sinopsisPanel.add(sinopsisLabel);
		
		JTextArea sinopsisTextArea = new JTextArea();
		sinopsisTextArea.setToolTipText(CentralPanelMessages.getString("ConsultaLlibresNoRegistrat.sinopsisTextArea.toolTipText")); //$NON-NLS-1$
		
		sinopsisTextArea.setColumns(60);
		sinopsisTextArea.setLineWrap(true);
		sinopsisTextArea.setWrapStyleWord(true);
		sinopsisTextArea.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec hendrerit orci enim, in convallis arcu scelerisque vel. Praesent efficitur scelerisque urna at condimentum. Nullam tincidunt finibus dolor, vel semper mi vehicula quis. Duis molestie id risus a vulputate. Fusce laoreet diam et dictum ullamcorper. Fusce sed posuere tellus, ac feugiat risus. Nunc malesuada eu felis pellentesque egestas. Aenean lobortis feugiat varius. Curabitur dapibus aliquam neque sit amet sagittis. Fusce sed venenatis erat.");
		sinopsisTextArea.setRows(4);
		
		JScrollPane sinopsisScrollPane = new JScrollPane(sinopsisTextArea);
		
		sinopsisPanel.add(sinopsisScrollPane);
		
		

	}
}
