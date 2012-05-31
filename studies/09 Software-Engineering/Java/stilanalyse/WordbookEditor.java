package stilanalyse;

//Alexander Züfle,
//Stefan Rehm

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileFilter;



public class WordbookEditor extends JDialog implements ListSelectionListener, ActionListener 
{

	private JPanel masterPanel = new JPanel();
	private JPanel buttonPanel = new JPanel();
	//private JPanel hauptPanel = new JPanel();
	private static final long serialVersionUID = 6098378824108994528L;
	private JButton worartEinfuegen = new JButton("Wortart Einfügen");	
	private JButton textdat = new JButton("Textdatei einlesen");	
	private JButton loeschen = new JButton("Lösche Wörterbuch");
	private JButton loeschenWort = new JButton ("Lösche Wort");
	private DefaultListModel listModel = new DefaultListModel();
	private JList textList = new JList(listModel);
	private int anzahlWoerterbuecher=0;
	private MVCTableModel table = new MVCTableModel();
	private JTable JTab = new JTable(table);
	private JScrollPane SPJTable = new JScrollPane(JTab);
	private JFileChooser chooser = new JFileChooser();
	private String lastSelected;
	private MVCTableModel lastModel;
	
	public WordbookEditor(MainFrameView frame, String name) 
	{	
	
		super(frame, name,true);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

		//this.getContentPane().add(hauptPanel,"East");

		textList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		textList.addListSelectionListener(this);
		JScrollPane scrollPane= new JScrollPane(textList);
		
		JTab.getSelectionModel().addListSelectionListener(this);
		masterPanel.setLayout(new BorderLayout());
		masterPanel.add(scrollPane, "West");
		masterPanel.add(SPJTable, "Center");
		
		this.setLayout(new BorderLayout());
		this.getContentPane().add(masterPanel,"North");
		this.getContentPane().add(buttonPanel, "South");
		buttonPanel.add(textdat);
		buttonPanel.add(worartEinfuegen);
		//buttonPanel.add(loeschen);
		buttonPanel.add(loeschenWort);
		
		textdat.addActionListener(this);
		worartEinfuegen.addActionListener(this);
		//loeschen.addActionListener(this);
		loeschenWort.addActionListener(this);
		
		this.pack();
		updateWortartenListen();
		
	}
	
	// Wird beim Anklicken eines Wörterbuchs in der linken Liste aufgerufen
	public void valueChanged(ListSelectionEvent e)
	{
		if(e.getSource() == textList)
			//event kommt von der texlist
		{
			if (!e.getValueIsAdjusting()) 
			{ 
				table = new MVCTableModel();
				JTab.setModel(table);
				JTab.getSelectionModel().addListSelectionListener(this);
				
				if (! textList.isSelectionEmpty())
				{
					lastSelected=textList.getSelectedValue().toString();
					lastModel=table;
					table.showWortArt(textList.getSelectedValue().toString());
				}
			}
		}
		
			
	}
	//ende der Funktion	

			

	private void updateWortartenListen()			// Holt Wortarten aus Woerterbuch und trägt sie in Liste ein.
	{
		HashMap map = new HashMap();
		map=Application.woerterVerwaltung.getWortarten();
		
		Iterator itr = map.entrySet().iterator();
		listModel.clear();							// Liste komplett löschen
		anzahlWoerterbuecher=0;
		while ( itr.hasNext() )
		{
			Map.Entry entry = (Map.Entry) itr.next();
		
			listModel.addElement(entry.getKey());		// Ein Eintrag in Listmodel zufügen
			anzahlWoerterbuecher++;
		}
	
	}
	public void actionPerformed (ActionEvent event) 
	{
		this.setAlwaysOnTop(false);
		if (event.getSource() == textdat)
		{
			
			
			//sebis
			//file chooser Filter setzen
			chooser.setFileFilter( new FileFilter() {
		          public boolean accept( File f ) {
		              return f.isDirectory() ||
		                     f.getName().toLowerCase().endsWith(".txt");
		            }
		            public String getDescription() {
		              return "*.txt";
		            }
		          } );
			int returnVal = chooser.showOpenDialog(this);
			
		    if(returnVal == JFileChooser.APPROVE_OPTION) {
		       String wbName = JOptionPane.showInputDialog(this, "Bitte Name des neuen Wörterbuchs angeben:" );
		      
		       
		       
		       //SebiS
		       //Exceptionhandler
		       
		       try{
					 Application.woerterVerwaltung.fuegeWortartEin(wbName);
				 }
				 catch(WortartBereitsVorhandenException eWBV)
				 {
					 JOptionPane.showMessageDialog(new JDialog(),"Wortart bereits vorhanden","Achtung",JOptionPane.WARNING_MESSAGE);
					 
				 }
		       
		       try {
		    	   Application.woerterVerwaltung.weiseTextdateiZu(chooser.getSelectedFile().getAbsoluteFile().toString(), wbName);
		       }

		       
		       catch( WortartNichtVorhandenException eWort)
		       {
		    	   JOptionPane.showMessageDialog(new JDialog(),"Wortart nicht vorhanden!","Achtung",JOptionPane.WARNING_MESSAGE);
		       }
		       //TODO: s.o.: Diese Exception muss die WBVerwaltung noch werfen, macht sie aber nicht!!
		       //catch( WortartBereitsVorhandenException ewbv)
		//	{
		//		JOptionPane.showMessageDialog(new JDialog(),"Wortart bereits vorhanden!","Achtung",JOptionPane.WARNING_MESSAGE);
		//	}
		//	
		       catch(FileNotFoundException eFile)
		       {
		    	   JOptionPane.showMessageDialog(new JDialog(),"Datei nicht vorhanden!","Achtung",JOptionPane.WARNING_MESSAGE);
		       }
		       catch (IOException eIO)
		       {
		    	   JOptionPane.showMessageDialog(new JDialog(),"IOException!","Achtung",JOptionPane.WARNING_MESSAGE);
		       }
		    	   
		       this.setAlwaysOnTop(true);
		       updateWortartenListen();
		    }
		}
		
		//TODO: Wortartlöschen ist nicht in der Woerterbuchverwaltung implementiert
		if (event.getSource()== loeschen)
		{
			String wortArtZuLoeschen= (String) textList.getSelectedValue();
			if (wortArtZuLoeschen!=null)
			{
				if (JOptionPane.showConfirmDialog(null, "Wortart " + wortArtZuLoeschen + " wirklich löschen?", "Bestätigen", JOptionPane.YES_NO_OPTION)==0) 
				{
					
					JOptionPane.showMessageDialog(null, "Die Wortart " + wortArtZuLoeschen + " kann leider nicht " +
							"gelöscht werdern da die Funktion in der Wörterbuchverwaltung nicht implementiert wurde!", "Achtung", JOptionPane.INFORMATION_MESSAGE);
					//SebiS
					//Exceptionhandler
					/*
					try{
					Application.woerterVerwaltung.loescheWort("", wortArtZuLoeschen);
					}
					catch ( WortNichtVorhandenException eWort)
					{
						 JOptionPane.showMessageDialog(new JDialog(),"Wort nicht vorhanden!","Achtung",JOptionPane.WARNING_MESSAGE);
					}
					catch (WortartNichtVorhandenException eWArt)
					{
						 JOptionPane.showMessageDialog(new JDialog(),"Worart nicht vorhanden!","Achtung",JOptionPane.WARNING_MESSAGE);
					}
					*/
				}
			}
			else
			{
				JOptionPane.showMessageDialog(new JDialog(),"Kein Wörterbuch ausgewählt!","Achtung",JOptionPane.WARNING_MESSAGE);
			}
		}
		
		if (event.getSource()== loeschenWort)
		{
//			SebiS
			try
			{
				String wortZuLoeschen= (String) JTab.getValueAt(JTab.getSelectedRow(),0);
				System.out.println(wortZuLoeschen);
			
				if (JOptionPane.showConfirmDialog(null, "Wort " + wortZuLoeschen + " wirklich löschen?", "Bestätigen", JOptionPane.YES_NO_OPTION)==0) 
				{

					
//					SebiS
					//Exceptionhandler
					try{
					Application.woerterVerwaltung.loescheWort(wortZuLoeschen);
					}
					catch ( WortNichtVorhandenException eWort)
					{
						 JOptionPane.showMessageDialog(new JDialog(),"Wort nicht vorhanden!","Achtung",JOptionPane.WARNING_MESSAGE);
					}
					
					lastModel.showWortArt(lastSelected);		// Tabelle aktualisieren
							
				}
			}
			catch (Exception e)
			{
				 JOptionPane.showMessageDialog(new JDialog(),"Kein Wort ausgewählt!","Achtung",JOptionPane.WARNING_MESSAGE);
			}
			

				
		
			this.setAlwaysOnTop(true);
		}
		if (event.getSource() == worartEinfuegen)
		{
			 String wbName = JOptionPane.showInputDialog(this, "Bitte Name des neuen Wörterbuchs angeben:" );
			 if (wbName.equals("")) JOptionPane.showMessageDialog(new JDialog(),"Kein Wort ausgewählt!","Achtung",JOptionPane.WARNING_MESSAGE);
			 else{
				 try{
					 Application.woerterVerwaltung.fuegeWortartEin(wbName);
				 }
			 	catch(WortartBereitsVorhandenException eWBV)
			 	{
			 		JOptionPane.showMessageDialog(new JDialog(),"Wortart bereits vorhanden","Achtung",JOptionPane.WARNING_MESSAGE);
				 
			 	}
			 	updateWortartenListen();
			 }
		}
	}
	

}	

