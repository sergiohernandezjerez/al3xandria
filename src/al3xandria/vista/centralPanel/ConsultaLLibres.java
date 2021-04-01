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

import al3xandria.vista.icons.Icons;

import javax.swing.border.EtchedBorder;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import java.awt.Dimension;
import java.awt.ComponentOrientation;

public class ConsultaLlibres extends JPanel {

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

	/**
	 * Create the panel.
	 */
	public ConsultaLlibres() {
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
		
		JLabel filtrePerLabel = new JLabel("Filtrar per:");
		filtrePerLabel.setToolTipText("Escull el filtre que vols utilitzar");
		filtrePerLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		filtreButtonPanel.add(filtrePerLabel);
		
		JRadioButton titolRadioButton = new JRadioButton("T\u00EDtol");
		titolRadioButton.setToolTipText("Filtrar per t\u00EDtol");
		filtreButtonPanel.add(titolRadioButton);
		
		JRadioButton autorRadioButton = new JRadioButton("Autor");
		autorRadioButton.setToolTipText("Filtra per autor");
		filtreButtonPanel.add(autorRadioButton);
		
		JRadioButton genereRadioButton = new JRadioButton("G\u00E8nere");
		genereRadioButton.setToolTipText("Filtre per g\u00E8nere");
		filtreButtonPanel.add(genereRadioButton);
		
		JRadioButton editorialRadioButton = new JRadioButton("Editorial");
		editorialRadioButton.setToolTipText("Filtra per editorial");
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
		cercaField.setToolTipText("Introduiex la teva cerca");
		filtreTextPanel.add(cercaField);
		cercaField.setColumns(20);
		
		JLabel separacioLabel = new JLabel("   ");
		filtreTextPanel.add(separacioLabel);
		
		JButton mostrarButton = new JButton("Mostrar");
		mostrarButton.setToolTipText("Prem per mostrar la consulta");
		filtreTextPanel.add(mostrarButton);
		
		JPanel llistaTablePanel = new JPanel();
		llistaTablePanel.setBackground(new Color(255, 255, 255));
		llibresPanel.add(llistaTablePanel, BorderLayout.CENTER);
		llistaTablePanel.setLayout(new BorderLayout(10, 0));
		
		
		
		String[] columns = {"id", "Titol", "Autor", "isbn", "Genere", "Editorial", "Data"};
		String[][] data = {
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
		};
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
		
		JButton altaLlibreButton = new JButton("Alta");
		altaLlibreButton.setToolTipText("Prem per afegir un llibre");
		accionsButtonsPanel.add(altaLlibreButton);
		
		JButton baixaLlibreButton = new JButton("Baixa");
		baixaLlibreButton.setToolTipText("Prem per eliminar el llibre seleccionat");
		accionsButtonsPanel.add(baixaLlibreButton);
		
		JButton editarLlibreButton = new JButton("Editar");
		editarLlibreButton.setToolTipText("Prem per editar el llibre seleccionat");
		accionsButtonsPanel.add(editarLlibreButton);
		
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
		
		JLabel idLabel = new JLabel("ID");
		idPanel.add(idLabel);
		
		idLlibreField = new JTextField();
		idLlibreField.setToolTipText("id del llibre");
		idPanel.add(idLlibreField);
		idLlibreField.setColumns(3);
		
		JPanel titolPanel = new JPanel();
		idTitolDisponiblePanel.add(titolPanel);
		
		JLabel titolLabel = new JLabel("T\u00EDtol");
		titolPanel.add(titolLabel);
		
		titolField = new JTextField();
		titolField.setToolTipText("T\u00EDtol del llibre");
		titolPanel.add(titolField);
		titolField.setColumns(40);
		
		JPanel disponiblePanel = new JPanel();
		idTitolDisponiblePanel.add(disponiblePanel);
		
		JLabel reservatLabel = new JLabel("Reservat?");
		reservatLabel.setToolTipText("Informa si el llibre est\u00E0 reservat");
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
		
		JLabel autorLabel = new JLabel("Autor");
		autorsPanel.add(autorLabel);
		autorLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JComboBox autorsComboBox = new JComboBox();
		autorsComboBox.setToolTipText("Selecciona un autor");
		autorsPanel.add(autorsComboBox);
		autorsComboBox.addItem("Autors     ");
		
		JPanel generesPanel = new JPanel();
		autorGenereEditorialPuntuacioPanel.add(generesPanel);
		
		JLabel generesLabel = new JLabel("G\u00E8nere");
		generesPanel.add(generesLabel);
		generesLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JComboBox generesComboBox = new JComboBox();
		generesComboBox.setToolTipText("Selecciona un g\u00E8nere");
		generesPanel.add(generesComboBox);
		generesComboBox.addItem("Gèneres     ");
		
		JPanel editorialsPanel = new JPanel();
		autorGenereEditorialPuntuacioPanel.add(editorialsPanel);
		
		JLabel editorialsLabel = new JLabel("Editorial");
		editorialsPanel.add(editorialsLabel);
		editorialsLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		
		JComboBox editorialsComboBox = new JComboBox();
		editorialsComboBox.setToolTipText("Selecciona una editorial");
		editorialsPanel.add(editorialsComboBox);
		editorialsComboBox.addItem("Editorials     ");
		
		JPanel puntuacioPanel = new JPanel();
		autorGenereEditorialPuntuacioPanel.add(puntuacioPanel);
		
		JLabel puntuacioLabel = new JLabel("Puntuaci\u00F3");
		puntuacioPanel.add(puntuacioLabel);
		
		puntuacioField = new JTextField();
		puntuacioField.setToolTipText("Puntuaci\u00F3 del llibre segons els lectors");
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
		
		JLabel isbnLabel = new JLabel("Isbn");
		isbnPanel.add(isbnLabel);
		
		isbnField = new JTextField();
		isbnField.setToolTipText("Isbn del llibre");
		isbnPanel.add(isbnField);
		isbnField.setColumns(10);
		
		JPanel dataPublicacioPanel = new JPanel();
		isbnDataPaginesReservesPanel.add(dataPublicacioPanel);
		
		JLabel dataPublicacioLabel = new JLabel("Data publicaci\u00F3");
		dataPublicacioPanel.add(dataPublicacioLabel);
		
		dataPublicacioField = new JTextField();
		dataPublicacioField.setToolTipText("Data de publicaci\u00F3 del llibre");
		dataPublicacioPanel.add(dataPublicacioField);
		dataPublicacioField.setColumns(10);
		
		JPanel numeroPaginesPanel = new JPanel();
		isbnDataPaginesReservesPanel.add(numeroPaginesPanel);
		
		JLabel numeroPaginesLabel = new JLabel("N\u00FAm. p\u00E0gines");
		numeroPaginesPanel.add(numeroPaginesLabel);
		
		numeroPaginesField = new JTextField();
		numeroPaginesField.setToolTipText("N\u00FAmero de p\u00E0gines del llibre");
		numeroPaginesPanel.add(numeroPaginesField);
		numeroPaginesField.setColumns(4);
		
		JPanel numeroReservesPanel = new JPanel();
		isbnDataPaginesReservesPanel.add(numeroReservesPanel);
		
		JLabel numeroReservesLabel = new JLabel("N\u00FAm. reserves");
		numeroReservesPanel.add(numeroReservesLabel);
		
		numeroReservesField = new JTextField();
		numeroReservesField.setToolTipText("N\u00FAmero de vegades que s'ha reservat el llibre");
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
		
		JLabel sinopsisLabel = new JLabel("Sinopsis");
		sinopsisLabel.setVerticalAlignment(SwingConstants.TOP);
		sinopsisPanel.add(sinopsisLabel);
		
		JTextArea sinopsisTextArea = new JTextArea();
		sinopsisTextArea.setToolTipText("Sinopsis del llibre");
		
		sinopsisTextArea.setColumns(60);
		sinopsisTextArea.setLineWrap(true);
		sinopsisTextArea.setWrapStyleWord(true);
		sinopsisTextArea.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec hendrerit orci enim, in convallis arcu scelerisque vel. Praesent efficitur scelerisque urna at condimentum. Nullam tincidunt finibus dolor, vel semper mi vehicula quis. Duis molestie id risus a vulputate. Fusce laoreet diam et dictum ullamcorper. Fusce sed posuere tellus, ac feugiat risus. Nunc malesuada eu felis pellentesque egestas. Aenean lobortis feugiat varius. Curabitur dapibus aliquam neque sit amet sagittis. Fusce sed venenatis erat.");
		sinopsisTextArea.setRows(4);
		
		JScrollPane sinopsisScrollPane = new JScrollPane(sinopsisTextArea);
		
		sinopsisPanel.add(sinopsisScrollPane);
		
		

	}
}
