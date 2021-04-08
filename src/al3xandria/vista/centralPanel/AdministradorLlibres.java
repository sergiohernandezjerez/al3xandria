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

import al3xandria.strings.ExternalizeStrings;
import al3xandria.vista.icons.Icons;

import javax.swing.border.EtchedBorder;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import java.awt.Dimension;
import java.awt.ComponentOrientation;
import java.awt.Cursor;

import javax.swing.ButtonGroup;

public class AdministradorLlibres extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Icons icones;
	
	private JTextField cercaField;
	private JTable llibresTable;
	private JTextField idLlibreField;
	private JTextField titolField;
	private JTextField numeroPaginesField;
	private JTextField numeroReservesField;
	private JTextField puntuacioField;
	private JTextField isbnField;
	private JTextField dataPublicacioField;
	private final ButtonGroup buttonGroup = new ButtonGroup();
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
	private JPanel borderTopPanel;
	private FlowLayout fl_borderTopPanel;
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
	private JLabel nouGenereLabel;
	private JLabel nouEditorialLabel;

	/**
	 * Create the panel.
	 */
	public AdministradorLlibres() {
		icones = new Icons();
		setBounds(new Rectangle(0, 0, 750, 850));
		setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		
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
		
		filtrePerLabel = new JLabel(CentralPanelMessages.getString("AdministradorLlibres.filtrePerLabel.text"));  //$NON-NLS-1$
		filtrePerLabel.setToolTipText(CentralPanelMessages.getString("AdministradorLlibres.filtrePerLabel.toolTipText"));  //$NON-NLS-1$
		filtrePerLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		filtreButtonPanel.add(filtrePerLabel);
		
		titolRadioButton = new JRadioButton(CentralPanelMessages.getString("AdministradorLlibres.titolRadioButton.text"));  //$NON-NLS-1$
		titolRadioButton.setSelected(true);
		buttonGroup.add(titolRadioButton);
		titolRadioButton.setToolTipText(CentralPanelMessages.getString("AdministradorLlibres.titolRadioButton.toolTipText"));  //$NON-NLS-1$
		filtreButtonPanel.add(titolRadioButton);
		
		autorRadioButton = new JRadioButton(CentralPanelMessages.getString("AdministradorLlibres.autorRadioButton.text"));  //$NON-NLS-1$
		buttonGroup.add(autorRadioButton);
		autorRadioButton.setToolTipText(CentralPanelMessages.getString("AdministradorLlibres.autorRadioButton.toolTipText"));  //$NON-NLS-1$
		filtreButtonPanel.add(autorRadioButton);
		
		genereRadioButton = new JRadioButton(CentralPanelMessages.getString("AdministradorLlibres.genereRadioButton.text"));  //$NON-NLS-1$
		buttonGroup.add(genereRadioButton);
		genereRadioButton.setToolTipText(CentralPanelMessages.getString("AdministradorLlibres.genereRadioButton.toolTipText"));  //$NON-NLS-1$
		filtreButtonPanel.add(genereRadioButton);
		
		editorialRadioButton = new JRadioButton(CentralPanelMessages.getString("AdministradorLlibres.editorialRadioButton.text"));  //$NON-NLS-1$
		buttonGroup.add(editorialRadioButton);
		editorialRadioButton.setToolTipText(CentralPanelMessages.getString("AdministradorLlibres.editorialRadioButton.toolTipText"));  //$NON-NLS-1$
		filtreButtonPanel.add(editorialRadioButton);
		
		filtreTextPanel = new JPanel();
		fl_filtreTextPanel = (FlowLayout) filtreTextPanel.getLayout();
		fl_filtreTextPanel.setAlignment(FlowLayout.RIGHT);
		filtresPanel.add(filtreTextPanel, BorderLayout.CENTER);
		
		lupaLabel = new JLabel(""); //$NON-NLS-1$
		lupaLabel.setIcon(icones.getLupaIcon());
		lupaLabel.setHorizontalAlignment(SwingConstants.CENTER);
		filtreTextPanel.add(lupaLabel);
		
		cercaField = new JTextField();
		cercaField.setToolTipText(CentralPanelMessages.getString("AdministradorLlibres.cercaField.toolTipText"));  //$NON-NLS-1$
		filtreTextPanel.add(cercaField);
		cercaField.setColumns(20);
		
		separacioLabel = new JLabel("   "); //$NON-NLS-1$
		filtreTextPanel.add(separacioLabel);
		
		cercarButton = new JButton(CentralPanelMessages.getString("AdministradorLlibres.cercarButton.text"));  //$NON-NLS-1$
		cercarButton.setForeground(new Color(0, 0, 0));
		cercarButton.setBackground(new Color(173, 216, 230));
		cercarButton.setToolTipText(CentralPanelMessages.getString("AdministradorLlibres.cercarButton.toolTipText"));  //$NON-NLS-1$
		filtreTextPanel.add(cercarButton);
		
		llistaTablePanel = new JPanel();
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
							"002", "20000 Leguas", "Perez Reverte", "9879879879879", "Aventura", "Anagrama", "12/05/1789" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$
						},
						{
							"002", "20000 Leguas", "Perez Reverte", "9879879879879", "Aventura", "Anagrama", "12/05/1789" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$
						},
						{
							"002", "20000 Leguas", "Perez Reverte", "9879879879879", "Aventura", "Anagrama", "12/05/1789" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$
						},
						{
							"002", "20000 Leguas", "Perez Reverte", "9879879879879", "Aventura", "Anagrama", "12/05/1789" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$
						},
						{
							"002", "20000 Leguas", "Perez Reverte", "9879879879879", "Aventura", "Anagrama", "12/05/1789" //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$ //$NON-NLS-7$
						}
		            },
		            new String [] {"id", "Titol", "Autor", "isbn", "Genere", "Editorial", "Data"} //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$   
		        ) {

					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;
		           
		        });
		llibresTable.setFocusable(false);
		llistatLlibresScrollPane = new JScrollPane(llibresTable);
		llistatLlibresScrollPane.setPreferredSize(new java.awt.Dimension(0, 100));

		llistatLlibresScrollPane.setBackground(new Color(255, 255, 255));
		llistaTablePanel.add(llistatLlibresScrollPane, BorderLayout.CENTER);
		
		
		accionsButtonsPanel = new JPanel();
		accionsButtonsPanel.setBackground(Color.WHITE);
		llistaTablePanel.add(accionsButtonsPanel, BorderLayout.EAST);
		accionsButtonsPanel.setLayout(new GridLayout(8, 0, 0, 0));
		
		altaLlibreButton = new JButton(CentralPanelMessages.getString("AdministradorLlibres.altaLlibreButton.text"));  //$NON-NLS-1$
		altaLlibreButton.setForeground(Color.WHITE);
		altaLlibreButton.setBackground(Color.decode("#00838f")); //$NON-NLS-1$
		altaLlibreButton.setToolTipText(CentralPanelMessages.getString("AdministradorLlibres.altaLlibreButton.toolTipText"));  //$NON-NLS-1$
		accionsButtonsPanel.add(altaLlibreButton);
		
		baixaLlibreButton = new JButton(CentralPanelMessages.getString("AdministradorLlibres.baixaLlibreButton.text"));  //$NON-NLS-1$
		baixaLlibreButton.setBackground(new Color(165, 42, 42));
		baixaLlibreButton.setForeground(Color.WHITE);
		baixaLlibreButton.setToolTipText(CentralPanelMessages.getString("AdministradorLlibres.baixaLlibreButton.toolTipText"));  //$NON-NLS-1$
		accionsButtonsPanel.add(baixaLlibreButton);
		
		editarLlibreButton = new JButton(CentralPanelMessages.getString("AdministradorLlibres.editarLlibreButton.text"));  //$NON-NLS-1$
		editarLlibreButton.setForeground(Color.WHITE);
		editarLlibreButton.setBackground(Color.decode("#6a1b9a")); //$NON-NLS-1$
		editarLlibreButton.setToolTipText(CentralPanelMessages.getString("AdministradorLlibres.editarLlibreButton.toolTipText"));  //$NON-NLS-1$
		accionsButtonsPanel.add(editarLlibreButton);
		
		dadesLlibrePanel = new JPanel();
		llibresPanel.add(dadesLlibrePanel, BorderLayout.SOUTH);
		dadesLlibrePanel.setLayout(new BorderLayout(0, 0));
		
		dadesLlibreGridPanel = new JPanel();
		dadesLlibreGridPanel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Dades del llibre", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0))); 
		dadesLlibrePanel.add(dadesLlibreGridPanel, BorderLayout.NORTH);
		GridBagLayout gbl_dadesLlibreGridPanel = new GridBagLayout();
		gbl_dadesLlibreGridPanel.columnWidths = new int[]{619, 0};
		gbl_dadesLlibreGridPanel.rowHeights = new int[]{22, 22, 22, 22, 22, 0};
		gbl_dadesLlibreGridPanel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_dadesLlibreGridPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		dadesLlibreGridPanel.setLayout(gbl_dadesLlibreGridPanel);
		
		borderTopPanel = new JPanel();
		fl_borderTopPanel = (FlowLayout) borderTopPanel.getLayout();
		fl_borderTopPanel.setVgap(0);
		fl_borderTopPanel.setHgap(0);
		GridBagConstraints gbc_borderTopPanel = new GridBagConstraints();
		gbc_borderTopPanel.fill = GridBagConstraints.BOTH;
		gbc_borderTopPanel.insets = new Insets(0, 0, 5, 0);
		gbc_borderTopPanel.gridx = 0;
		gbc_borderTopPanel.gridy = 0;
		dadesLlibreGridPanel.add(borderTopPanel, gbc_borderTopPanel);
		
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
		
		idLabel = new JLabel(CentralPanelMessages.getString("AdministradorLlibres.idLabel.text"));  //$NON-NLS-1$
		idPanel.add(idLabel);
		
		idLlibreField = new JTextField();
		idLlibreField.setToolTipText(CentralPanelMessages.getString("AdministradorLlibres.idLlibreField.toolTipText"));  //$NON-NLS-1$
		idPanel.add(idLlibreField);
		idLlibreField.setColumns(3);
		
		titolPanel = new JPanel();
		idTitolDisponiblePanel.add(titolPanel);
		
		titolLabel = new JLabel(CentralPanelMessages.getString("AdministradorLlibres.titolLabel.text"));  //$NON-NLS-1$
		titolPanel.add(titolLabel);
		
		titolField = new JTextField();
		titolField.setToolTipText(CentralPanelMessages.getString("AdministradorLlibres.titolField.toolTipText"));  //$NON-NLS-1$
		titolPanel.add(titolField);
		titolField.setColumns(40);
		
		disponiblePanel = new JPanel();
		idTitolDisponiblePanel.add(disponiblePanel);
		
		reservatLabel = new JLabel(CentralPanelMessages.getString("AdministradorLlibres.reservatLabel.text"));  //$NON-NLS-1$
		reservatLabel.setToolTipText(CentralPanelMessages.getString("AdministradorLlibres.reservatLabel.toolTipText"));  //$NON-NLS-1$
		disponiblePanel.add(reservatLabel);
		
		reservatCheckBox = new JCheckBox(""); //$NON-NLS-1$
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
		
		autorLabel = new JLabel(CentralPanelMessages.getString("AdministradorLlibres.autorLabel.text"));  //$NON-NLS-1$
		autorsPanel.add(autorLabel);
		autorLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		
		autorsComboBox = new JComboBox();
		autorsComboBox.setToolTipText(CentralPanelMessages.getString("AdministradorLlibres.autorsComboBox.toolTipText"));  //$NON-NLS-1$
		autorsPanel.add(autorsComboBox);
		autorsComboBox.addItem("Autors     "); 
		
		afegirAutorLabel = new JLabel(); //$NON-NLS-1$
		afegirAutorLabel.setToolTipText(CentralPanelMessages.getString("AdministradorLlibres.afegirAutorLabel.toolTipText"));  //$NON-NLS-1$
		afegirAutorLabel.setIcon(icones.getAddIcon());
		afegirAutorLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		afegirAutorLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		autorsPanel.add(afegirAutorLabel);
		
		generesPanel = new JPanel();
		autorGenereEditorialPuntuacioPanel.add(generesPanel);
		
		generesLabel = new JLabel(CentralPanelMessages.getString("AdministradorLlibres.generesLabel.text"));  //$NON-NLS-1$
		generesPanel.add(generesLabel);
		generesLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		
		generesComboBox = new JComboBox();
		generesComboBox.setToolTipText(CentralPanelMessages.getString("AdministradorLlibres.generesComboBox.toolTipText"));  //$NON-NLS-1$
		generesPanel.add(generesComboBox);
		generesComboBox.addItem("Gèneres     "); 
		
		nouGenereLabel = new JLabel(); //$NON-NLS-1$
		nouGenereLabel.setToolTipText(CentralPanelMessages.getString("AdministradorLlibres.nouGenereLabel.toolTipText"));  //$NON-NLS-1$
		nouGenereLabel.setIcon(icones.getAddIcon());
		nouGenereLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		nouGenereLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		
		generesPanel.add(nouGenereLabel);
		
		editorialsPanel = new JPanel();
		autorGenereEditorialPuntuacioPanel.add(editorialsPanel);
		
		editorialsLabel = new JLabel(CentralPanelMessages.getString("AdministradorLlibres.editorialsLabel.text"));  //$NON-NLS-1$
		editorialsPanel.add(editorialsLabel);
		editorialsLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		
		editorialsComboBox = new JComboBox();
		editorialsComboBox.setToolTipText(CentralPanelMessages.getString("AdministradorLlibres.editorialsComboBox.toolTipText"));  //$NON-NLS-1$
		editorialsPanel.add(editorialsComboBox);
		editorialsComboBox.addItem("Editorials     "); 
		
		nouEditorialLabel = new JLabel(); //$NON-NLS-1$
		nouEditorialLabel.setToolTipText(CentralPanelMessages.getString("AdministradorLlibres.nouEditorialLabel.toolTipText"));  //$NON-NLS-1$
		nouEditorialLabel.setIcon(icones.getAddIcon());
		nouEditorialLabel.setVerticalAlignment(SwingConstants.BOTTOM);
		nouEditorialLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		editorialsPanel.add(nouEditorialLabel);
		
		puntuacioPanel = new JPanel();
		autorGenereEditorialPuntuacioPanel.add(puntuacioPanel);
		
		puntuacioLabel = new JLabel(CentralPanelMessages.getString("AdministradorLlibres.puntuacioLabel.text"));  //$NON-NLS-1$
		puntuacioPanel.add(puntuacioLabel);
		
		puntuacioField = new JTextField();
		puntuacioField.setToolTipText(CentralPanelMessages.getString("AdministradorLlibres.puntuacioField.toolTipText"));  //$NON-NLS-1$
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
		
		isbnLabel = new JLabel(CentralPanelMessages.getString("AdministradorLlibres.isbnLabel.text"));  //$NON-NLS-1$
		isbnPanel.add(isbnLabel);
		
		isbnField = new JTextField();
		isbnField.setToolTipText(CentralPanelMessages.getString("AdministradorLlibres.isbnField.toolTipText"));  //$NON-NLS-1$
		isbnPanel.add(isbnField);
		isbnField.setColumns(10);
		
		dataPublicacioPanel = new JPanel();
		isbnDataPaginesReservesPanel.add(dataPublicacioPanel);
		
		dataPublicacioLabel = new JLabel(CentralPanelMessages.getString("AdministradorLlibres.dataPublicacioLabel.text"));  //$NON-NLS-1$
		dataPublicacioPanel.add(dataPublicacioLabel);
		
		dataPublicacioField = new JTextField();
		dataPublicacioField.setToolTipText(CentralPanelMessages.getString("AdministradorLlibres.dataPublicacioField.toolTipText"));  //$NON-NLS-1$
		dataPublicacioPanel.add(dataPublicacioField);
		dataPublicacioField.setColumns(10);
		
		numeroPaginesPanel = new JPanel();
		isbnDataPaginesReservesPanel.add(numeroPaginesPanel);
		
		numeroPaginesLabel = new JLabel(CentralPanelMessages.getString("AdministradorLlibres.numeroPaginesLabel.text"));  //$NON-NLS-1$
		numeroPaginesPanel.add(numeroPaginesLabel);
		
		numeroPaginesField = new JTextField();
		numeroPaginesField.setToolTipText(CentralPanelMessages.getString("AdministradorLlibres.numeroPaginesField.toolTipText"));  //$NON-NLS-1$
		numeroPaginesPanel.add(numeroPaginesField);
		numeroPaginesField.setColumns(4);
		
		numeroReservesPanel = new JPanel();
		isbnDataPaginesReservesPanel.add(numeroReservesPanel);
		
		numeroReservesLabel = new JLabel(CentralPanelMessages.getString("AdministradorLlibres.numeroReservesLabel.text"));  //$NON-NLS-1$
		numeroReservesPanel.add(numeroReservesLabel);
		
		numeroReservesField = new JTextField();
		numeroReservesField.setToolTipText(CentralPanelMessages.getString("AdministradorLlibres.numeroReservesField.toolTipText"));  //$NON-NLS-1$
		numeroReservesPanel.add(numeroReservesField);
		numeroReservesField.setColumns(4);
		
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
		
		sinopsisLabel = new JLabel(CentralPanelMessages.getString("AdministradorLlibres.sinopsisLabel.text"));  //$NON-NLS-1$
		sinopsisLabel.setVerticalAlignment(SwingConstants.TOP);
		sinopsisPanel.add(sinopsisLabel);
		
		sinopsisTextArea = new JTextArea();
		sinopsisTextArea.setToolTipText(CentralPanelMessages.getString("AdministradorLlibres.sinopsisTextArea.toolTipText"));  //$NON-NLS-1$
		
		sinopsisTextArea.setColumns(60);
		sinopsisTextArea.setLineWrap(true);
		sinopsisTextArea.setWrapStyleWord(true);
		sinopsisTextArea.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec hendrerit orci enim, in convallis arcu scelerisque vel. Praesent efficitur scelerisque urna at condimentum. Nullam tincidunt finibus dolor, vel semper mi vehicula quis. Duis molestie id risus a vulputate. Fusce laoreet diam et dictum ullamcorper. Fusce sed posuere tellus, ac feugiat risus. Nunc malesuada eu felis pellentesque egestas. Aenean lobortis feugiat varius. Curabitur dapibus aliquam neque sit amet sagittis. Fusce sed venenatis erat."); 
		sinopsisTextArea.setRows(4);
		
		sinopsisScrollPane = new JScrollPane(sinopsisTextArea);
		
		sinopsisPanel.add(sinopsisScrollPane);
		
		

	}
}
