/*
 * Autor : Marco Fahr
 * Date  : 12.12.2005
 * Klasse MainFrame -> Hauptfenster der Anwendung 
 */

package stilanalyse;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.filechooser.FileFilter;

import stilanalyse.DeleteDialogView;

/**
 * @author Marco fahr
 *
 */
public class MainFrameView extends JFrame {
	
	private static final long serialVersionUID = -8026416994513756565L;
	
	/**
	 * 	Anzahl der zu editierenden Romane 
	 */
	public static int size = 0;
	/**
	 *  Feld mit Pfadangaben der einzulesenden Romane
	 */
	public static File[] files;	
	private JTabbedPane tabbedPane;
	private MainFrameController controller;
	private OverviewTabView overviewTab;
	private VergleichRomanTabView vergleichsTabView;
	static WordAnalysisTab wordTab;  
	static SentenceAnalysisTab sentenceAnalysisTab;

	/**
	 *  Konstruktor
	 */
	public MainFrameView() {
		super();
		// TODO: Beim Integrieren eingefügt
        // Window-Listener registrieren
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                try {
					Application.woerterVerwaltung.deinitialisiere("woerterbuch.obj");
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
            }
        });
		controller = new MainFrameController(this);
		wordTab = new WordAnalysisTab();
		sentenceAnalysisTab = new SentenceAnalysisTab();
		getContentPane().setLayout(new BorderLayout());
		initialize();
		getContentPane().add(createTabbedPane(), BorderLayout.CENTER);
		
	}
	
	private void initialize() {
		//setExtendedState(MAXIMIZED_BOTH);
		setLocation(0, 0);
		setSize(1024,750);
		setResizable(false);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension labelSize = this.getSize();
		setLocation((screenSize.width - labelSize.width)/2,(screenSize.height - labelSize.height)/2);
		
		setTitle("Text-Style-Analyser");												
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);						
		setJMenuBar(createMenuBar());	
	}
	
	private JMenuBar createMenuBar() {	
		JMenuBar menuBar = new JMenuBar();
		menuBar.add(createRomanMenu());
		menuBar.add(createOptionsMenu());
		return menuBar;
	}
	 
	private JMenu createRomanMenu() {
		JMenu romanMenu = new JMenu("Romane");
		romanMenu.add(createReadRomanMenuItem());
		romanMenu.add(createDeleteMenuItem());
		return romanMenu;
	}
	
	private JMenu createOptionsMenu()
	{
		JMenu optionsMenu = new JMenu("Optionen");
		optionsMenu.add(createWordEditorMenuItem());
		return optionsMenu;
	}
	
	private JMenuItem createReadRomanMenuItem() {
		JMenuItem readItem = new JMenuItem("Roman einlesen");
		readItem.setActionCommand("String_Var_1");
		readItem.addActionListener(controller);
		return readItem;	
	}
	
	private JMenuItem createDeleteMenuItem() {
		JMenuItem deleteItem = new JMenuItem("Roman löschen");
		deleteItem.setActionCommand("String_Var_2");
		deleteItem.addActionListener(controller);
		return deleteItem;
	}
	
	private JMenuItem createWordEditorMenuItem() {
		JMenuItem wordItem = new JMenuItem("Wörterbucheditor");
		wordItem.setActionCommand("String_Var_3");
		wordItem.addActionListener(controller);
		return wordItem; 
	}
	
	/**
	 * @return chooser
	 */
	public JFileChooser createFileChooser() {
		JFileChooser chooser = new JFileChooser();
		chooser.setFileFilter( new FileFilter() {
	          public boolean accept( File f ) {
	              return f.isDirectory() ||
	                     f.getName().toLowerCase().endsWith(".txt");
	            }
	            public String getDescription() {
	              return "*.txt";
	            }
	          } );
		chooser.setMultiSelectionEnabled(true);
		if(chooser.showOpenDialog(this)!= JFileChooser.APPROVE_OPTION)
			return new JFileChooser();
		files = chooser.getSelectedFiles();
		size = files.length;
		if(!chooser.isOpaque()) {
			createRomanDialog();
		}
		return chooser;
	}
	
	private RomanDialogView createRomanDialog() {
		RomanDialogView dialog = new RomanDialogView(this, "Roman editieren");
		return dialog;
	}
	
	/**
	 * @return LöschenDialog
	 */
	public DeleteDialogView createDeleteDialog() {
		DeleteDialogView dialog = new DeleteDialogView(this, "Romane löschen");
		return dialog;
	}
	
	private JPanel createOverviewPanel() 
	{
		overviewTab = new OverviewTabView();
		return overviewTab.getOverviewPanel();
	}
	
	//Funktion zum Estellen des Roman Vergleichs
	private JPanel createVergleichsPanel() 
	{
		vergleichsTabView = new VergleichRomanTabView();
		return vergleichsTabView;
	}
	
	/**
	 * @return size
	 */
	public int getNumberOfRomansToEdit() {
		return size;
	}
	
	/**
	 * @return files
	 */
	public File[] getFilePaths() {
		return files;
	}
	
	private JTabbedPane createTabbedPane() {
		if (tabbedPane == null) {
			tabbedPane = new JTabbedPane();
			tabbedPane.addTab("Überblick", null, createOverviewPanel(), null);
			tabbedPane.addTab("Wortanalyse", null, wordTab, null);
			tabbedPane.addTab("Satzanalyse", null, sentenceAnalysisTab, null);
			tabbedPane.addTab("Romanvergleich", null, createVergleichsPanel(), null);
		}
		return tabbedPane;
	}
	
	/**
	 * @return sentenceSplitPanel
	 */
	public SentenceAnalysisTab getSentenceAnalysisPanel() {
		SentenceAnalysisTab sentenceSplitPanel = new SentenceAnalysisTab();
		return sentenceSplitPanel;
	}
	
	/**
	 * @return overviewTab
	 */
	public OverviewTabView getOverviewPanel() {
		return overviewTab;
	}
	
	/**
	 * @return vergleichsTabView
	 */
	public VergleichRomanTabView getVergleichsTabView() 
	{
		return vergleichsTabView;
	}
}
