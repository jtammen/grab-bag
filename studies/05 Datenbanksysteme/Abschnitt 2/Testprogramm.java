import java.io.*;								// FÅr Ein- und Ausgabestr˜me
import java.sql.*;								// SQL-Schnittstelle fÅr Java
import oracle.jdbc.*;							// FÅr Datenbank
import java.util.*;								// Mischpacket mit allem m˜glichen Zeug (zum Beispiel Datumsformat)

public class Testprogramm						// Am einfachsten alles in eine Klasse
{
 	public static void main (String args[]){	// String args[] ist ein Feld von Stringreferenzen, die beim Programmaufruf
                                                // erhalten werden k˜nnen (siehe auch:
                                                // http://www.gailer-net.de/tutorials/java/Notes/chap49B/ch49B_9.html)

		boolean fertig = false;					// beendet switch-case Anweisung
		String name = "dbsys34";				// Variablen fÅr Name und Passwort
    	String passwd = "spion!";
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in)); // zum Einlesen von Tastatur n˜tig
		Connection conn = null;
    	Statement  stmt = null;
    	ResultSet  rset = null;

		String line = null;						// Variable fÅr Benutzereingabe
		int wahl = 0;							// Variable fÅr SwitchCase

		// AUSGABE auf die Konsole
		System.out.println("");											// Leerzeile ausgeben
    	System.out.println("--- Dies ist Datenbank-Java-Aufgabe ---"); 			
    	System.out.println("");

		// --- DIALOG mit Benutzer ---
/*		try {
      		System.out.print("Benutzername: ");
      		name = in.readLine();					// Einlesen der Eingabe "Benutzername"
			System.out.print("Passwort: ");
			passwd = in.readLine();					// Einlesen der Eingabe "Passwort"
		}
		// --- Abfangen des m˜glichen Fehlers ---
		catch (IOException e) {
			System.out.println("Fehler beim Lesen der Eingabe: " + e);
			System.exit(-1);
    	}*/

		// --- Block fÅr Verbindung mit Datenbank ---
		try {
      		DriverManager.registerDriver(new oracle.jdbc.OracleDriver());		// Treiber laden
			String url = "jdbc:oracle:thin:@rzdb01.fh-konstanz.de:1521:o9204";	// String fÅr DB-Connection (vgl.Skript)
			conn = DriverManager.getConnection(url, name, passwd);				// Verbindung erstellen

			conn.setTransactionIsolation(conn.TRANSACTION_SERIALIZABLE);		// Transaction Isolations-Level setzen: Serialisierbarkeit als Korrektheitskriterium																				
			conn.setAutoCommit(false);											// Kein automatisches Commit nach jedem SQL statement
			System.out.println("... zur Datenbank verbunden!");					// Ausgabe fÅr den Benutzer
			conn.setAutoCommit(true);											// Automatisches speichern aktivieren
			stmt = conn.createStatement();										// Statement-Objekt erzeugen

			while (!fertig)
			{
				System.out.println("\nHallo Benutzer! Was wollen Sie tun?");
				System.out.println("1: Kunde einfuegen");
                System.out.println("2: Kunde suchen");
                System.out.println("3: Ferienwohnung einfuegen");
                System.out.println("4: Ferienwohnung suchen");
                System.out.println("5: Ferienwohnung buchen");
				System.out.println("Zum Beenden bitte \"0\" druecken");

				// Einlesen der Benutzerwahl
				try {
					line = in.readLine();					
				}
				// Fehler beim Einlesen
				catch(IOException ioe) {
					System.out.println("Fehler beim Lesen der Eingabe: " + ioe);
					fertig = true;
					continue;
				}
				// Konvertierung in int-Wert
				try {
					wahl = Integer.parseInt(line);
				}
				catch(NumberFormatException nfe) {
					System.out.println("Falsche Eingabe!");
					continue;
				}
				switch (wahl)
				{
                    case 0:
                        fertig = true;
                        break;

                    // {{{ Kunde einfuegen
                    case 1:
                        String vorname = null, nachname = null, plz = null, 
                               stadt = null, strasse = null, hsnr = null,
                               blz = null, kontonr = null, landcode = null;

                        try {
                            System.out.println("Bitte Daten des Kunden eingeben.");
                            System.out.print("Vorname: "); vorname = in.readLine();
                            System.out.print("Nachname: "); nachname = in.readLine();
                            System.out.print("PLZ (max. 5 Stellen): "); plz = in.readLine();
                            System.out.print("Stadt: "); stadt = in.readLine();
                            System.out.print("Strasse: "); strasse = in.readLine();
                            System.out.print("Hausnr.: "); hsnr = in.readLine();
                            System.out.print("BLZ: "); blz = in.readLine();
                            System.out.print("Kontonr.: "); kontonr = in.readLine();
                            System.out.print("Landkuerzel (de, fr, it, ch): "); landcode = in.readLine();
                        }
                        catch (IOException e) {
                            System.out.println("Fehler beim Lesen der Eingabe: " + e);
                            System.exit(-1);
                        }

                        // Daten einfuegen
                        stmt.executeUpdate("INSERT INTO kunde " +
                                           "VALUES ( (SELECT MAX(kdnr)+1 FROM kunde), '" + vorname  + "', " +
                                           "         '" + nachname + "', " +
                                           "         "  + Integer.parseInt(plz) + ", " +
                                           "         '" + stadt + "', " +
                                           "         '" + strasse + "', " +
                                           "         '" + hsnr  + "', " +
                                           "         "  + Integer.parseInt(blz)  + ", " +
                                           "         "  + Integer.parseInt(kontonr)  + ", " +
                                           "         '" + landcode  + "' )");
                        
                        System.out.println("Der Kunde wurde erfolgreich eingefuegt!");
    
                        break;
                        // }}}

                    // {{{ Kunde suchen
                    case 2:
                        String suchVorname = null, suchNachname = null;
                        
                        try {
                            System.out.println("Bitte Namen des zu suchenden Kunden eingeben.");
                            System.out.print("Vorname: "); suchVorname = in.readLine();
                            System.out.print("Nachname: "); suchNachname = in.readLine();
                        }
                        catch (IOException e) {
                            System.out.println("Fehler beim Lesen der Eingabe: " + e);
                            System.exit(-1);
                        }

                        // ... und Abfrage senden
                        String sSql = "SELECT * FROM kunde " +
                                      "WHERE vorname LIKE '%" + suchVorname + "%' AND " +
                                      "nachname LIKE '%" + suchNachname + "%'";
                        rset = stmt.executeQuery(sSql);

                        System.out.println("[DEBUG] " + sSql);
                        System.out.println("Suchergebnisse: ");
                        while ( rset.next() )
                        {
                            System.out.println(rset.getInt("kdnr") + " " +
                                               rset.getString("vorname") + " " +
                                               rset.getString("nachname") + " " +
                                               rset.getInt("zip") + " " +
                                               rset.getString("stadt") + " " +
                                               rset.getString("strasse") + " " +
                                               rset.getString("hsnr") + " " +
                                               rset.getInt("blz") + " " +
                                               rset.getInt("kontonr") + " " +
                                               rset.getString("landcode"));
/*
                            System.out.format("5%d 25%s 25%s 5%d 25%s 25%s 4%s 15%d 15%d 2%s",
                                              rset.getInt("kdnr"),
                                              rset.getString("vorname"),
                                              rset.getString("nachname"),
                                              rset.getInt("zip"),
                                              rset.getString("stadt"),
                                              rset.getString("strasse"),
                                              rset.getString("hsnr"),
                                              rset.getInt("blz"),
                                              rset.getInt("kontonr"),
                                              rset.getString("landcode"));
*/
                        }
						break;
                    // }}}

                    // {{{ Wohnung einfuegen
                    case 3:
                        String zimmer = null, groesse = null, preis = null, sauna = null, schwbad = null;
                        plz = null; landcode = null;

                        try {
                            System.out.println("Bitte Daten der Wohnung eingeben.");
                            System.out.print("Anzahl Zimmer: "); zimmer = in.readLine();
                            System.out.print("Groesse [qm]: "); groesse = in.readLine();
                            System.out.print("Preis/Tag [EUR]: "); preis = in.readLine();
                            System.out.print("Landkuerzel (de, fr, it, ch): "); landcode = in.readLine();
                            System.out.print("Sauna? [0 = nein, 1 = ja]: "); sauna = in.readLine();
                            System.out.print("Schwimmbad? [0 = nein, 1 = ja]: "); sauna = in.readLine();
                        }
                        catch (IOException e) {
                            System.out.println("Fehler beim Lesen der Eingabe: " + e);
                            System.exit(-1);
                        }

                        // Daten einfuegen
                        stmt.executeUpdate("INSERT INTO ferienwohnung " +
                                           "VALUES ( (SELECT MAX(fwnr)+1 FROM ferienwohnung), " + 
                                           "         "  + Integer.parseInt(zimmer)  + ", " +
                                           "         "  + Integer.parseInt(groesse) + ", " +
                                           "         "  + Integer.parseInt(plz) + ", " +
                                           "         "  + Integer.parseInt(preis) + ", " +
                                           "         '" + landcode  + "', " +
                                           "         "  + Integer.parseInt(sauna)  + ", " + 
                                           "         "  + Integer.parseInt(schwbad) + ")");
                        
                        System.out.println("Die Wohnung wurde erfolgreich eingefuegt!");                        
						break;
                    // }}}

                    // {{{ Wohnung suchen
                    case 4:
                        String datumAnreise = null, datumAbreise = null;
                        zimmer = null;
                        
                        try {
                            System.out.println("Bitte Daten der zu suchenden Wohnung eingeben.");
                            System.out.print("Landkuerzel (de, fr, it, ch): "); landcode = in.readLine();
                            System.out.print("Anzahl Zimmer: "); zimmer = in.readLine();
                            System.out.print("Anreisedatum [YYYY-MM-DD]: "); datumAnreise = in.readLine();
                            System.out.print("Abreisedatum [YYYY-MM-DD]: "); datumAbreise = in.readLine();
                        }
                        catch (IOException e) {
                            System.out.println("Fehler beim Lesen der Eingabe: " + e);
                            System.exit(-1);
                        }

                        // Abfrage senden
                        sSql = "SELECT f.* FROM ferienwohnung f " +
                               "LEFT OUTER JOIN buchung b " +
                               "ON ( b.fwnr = f.fwnr ) " +
                               "WHERE " +
                               "(" +
                               " (     b.von < TO_DATE('" + datumAnreise + "', 'YYYY-MM-DD') " +
                               "   AND b.bis < TO_DATE('" + datumAbreise + "', 'YYYY-MM-DD') ) " +
                               " OR " +
                               " (     b.von > TO_DATE('" + datumAnreise + "', 'YYYY-MM-DD') " +
                               " AND b.bis > TO_DATE('" + datumAbreise + "', 'YYYY-MM-DD') ) " +
                               " OR " +
                               " ( b.bunr IS NULL ) " +
                               ")" +
                              "AND f.anz_zimmer = " + Integer.parseInt(zimmer);
                        rset = stmt.executeQuery(sSql);

                        System.out.println("[DEBUG] " + sSql);
                        System.out.println("Suchergebnisse: ");
                        while ( rset.next() )
                        {
                            System.out.println(rset.getInt("fwnr") + " " +
                                               rset.getString("anz_zimmer") + " " +
                                               rset.getString("groesse") + " " +
                                               rset.getInt("preis") + " " +
                                               rset.getString("landcode") + " " +
                                               rset.getString("hat_sauna") + " " +
                                               rset.getString("hat_schwb"));
                        }
						break;	
                    // }}}

                    // {{{ Wohnung buchen
                    case 5:
                        String kdnr = null, fwnr = null;
                        datumAnreise = null; datumAbreise = null;
                        
                        try {
                            System.out.println("Bitte Daten der Buchung eingeben.");
                            System.out.print("Kunden-Nr.: "); kdnr = in.readLine();
                            System.out.print("Ferienwohnung-Nr.: "); fwnr = in.readLine();
                            System.out.print("Anreisedatum [YYYY-MM-DD]: "); datumAnreise = in.readLine();
                            System.out.print("Abreisedatum [YYYY-MM-DD]: "); datumAbreise = in.readLine();
                        }
                        catch (IOException e) {
                            System.out.println("Fehler beim Lesen der Eingabe: " + e);
                            System.exit(-1);
                        }

                        // Pruefen, ob Kunde/Fw existent?

                        // Daten einfuegen
                        stmt.executeUpdate("INSERT INTO buchung " +
                                           "VALUES ( (SELECT MAX(bunr)+1 FROM buchung), " +
                                           "         SYSDATE, " +
                                           "         TO_DATE('" + datumAnreise + "', 'YYYY-MM-DD'), " +
                                           "         TO_DATE('" + datumAbreise + "', 'YYYY-MM-DD'), " +
                                           "         "  + Integer.parseInt(kdnr)  + ", " +
                                           "         "  + Integer.parseInt(fwnr)  + " )");
                        
                        System.out.println("Die Buchung wurde erfolgreich eingefuegt!");                        
						break;
                    // }}}
				}
			}
			// Verbindung trennen
			stmt.close();
			conn.close();									
		}
		catch (SQLException se)								// Fehler abfangen
		{
			System.out.println("");
			System.out.println("SQL Exception occurred while establishing connection to DBS: " + se.getMessage());
			System.out.println("- SQL state  : " + se.getSQLState());
			System.out.println("- Message    : " + se.getMessage());
			System.out.println("- Vendor code: " + se.getErrorCode());
			System.out.println("");
			System.out.println("EXITING WITH FAILURE ... !!!");
			System.out.println("");
			System.exit(-1);
		}
		System.out.println("");
    		System.out.println("PROGRAM FINISHED!!!");
    }
}
